import type { AgentDonePayload, AgentHit, AgentSearchRequest, AnswerMeta, ConversationPayload, SourceItem } from './types'

export type ChatStreamRequest = {
  userId: string
  conversationId?: string
  question: string
  skillNames?: string[]
}

export type StreamHandlers = {
  conversation: (payload: ConversationPayload) => void
  sources: (payload: SourceItem[]) => void
  agentRound: (payload: { round: number; requests: AgentSearchRequest[] }) => void
  agentHits: (payload: { round: number; hits: AgentHit[] }) => void
  answerMeta: (payload: AnswerMeta) => void
  agentDone: (payload: AgentDonePayload) => void
  predictions: (payload: string[]) => void
  delta: (text: string) => void
  done: () => void
  error: (message: string) => void
}

// CN: SSE 是网页版的核心业务通道，保持后端接口不变，只解析事件并向 UI 递增推送。
// EN: SSE is the web page's core business channel; keep the backend contract unchanged and incrementally push parsed events into the UI.
// JP: SSE は Web 版の主要な業務チャネルです。バックエンド契約は変えず、解析したイベントを UI へ逐次反映します。
export async function streamChat(request: ChatStreamRequest, handlers: StreamHandlers): Promise<void> {
  const response = await fetch('/api/core/ai/chat/stream', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Accept: 'text/event-stream'
    },
    body: JSON.stringify(request)
  })

  if (!response.ok || !response.body) {
    throw new Error(`request failed: ${response.status}`)
  }

  const parse = createSseParser(handlers)
  const reader = response.body.getReader()
  const decoder = new TextDecoder('utf-8')

  while (true) {
    const result = await reader.read()
    if (result.done) break
    parse(decoder.decode(result.value, { stream: true }))
  }
}

// CN: skill 列表由后端从 data/skills 动态返回，新增知识库目录不再需要改前端代码。
// EN: The backend returns skills dynamically from data/skills, so new knowledge folders do not require frontend edits.
// JP: skill 一覧は data/skills からバックエンドが動的に返すため、新しいナレッジディレクトリ追加時にフロント修正は不要です。
export async function fetchSkillNames(): Promise<string[]> {
  const response = await fetch('/api/core/ai/skills')
  if (!response.ok) {
    throw new Error(`skills request failed: ${response.status}`)
  }
  return response.json()
}

function createSseParser(handlers: StreamHandlers): (chunk: string) => void {
  let buffer = ''

  return chunk => {
    buffer += chunk
    const events = buffer.split('\n\n')
    buffer = events.pop() || ''
    events.forEach(raw => dispatchSse(raw, handlers))
  }
}

function dispatchSse(raw: string, handlers: StreamHandlers): void {
  const lines = raw.split('\n')
  const event = lines.find(line => line.startsWith('event:'))?.slice(6).trim()
  const data = lines
    .filter(line => line.startsWith('data:'))
    .map(line => line.slice(5).trim())
    .join('\n')

  if (!event || !data) return

  try {
    const payload = JSON.parse(data)
    if (event === 'conversation') handlers.conversation(payload)
    if (event === 'sources') handlers.sources(payload)
    if (event === 'agent_round') handlers.agentRound(payload)
    if (event === 'agent_hits') handlers.agentHits(payload)
    if (event === 'answer_meta') handlers.answerMeta(payload)
    if (event === 'agent_done') handlers.agentDone(payload)
    if (event === 'predictions') handlers.predictions(payload)
    if (event === 'delta') handlers.delta(payload.text || '')
    if (event === 'done') handlers.done()
    if (event === 'error') handlers.error(payload.message || 'AI request failed')
  } catch (error) {
    handlers.error(error instanceof Error ? error.message : String(error))
  }
}
