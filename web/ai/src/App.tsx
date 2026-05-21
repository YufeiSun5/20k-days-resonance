import { FormEvent, useEffect, useLayoutEffect, useMemo, useRef, useState } from 'react'
import { fetchSkillNames, streamChat } from './apiClient'
import { COPY } from './copy'
import { MarkdownMessage } from './MarkdownMessage'
import { createLocalId, getConversationId, getOrCreateUserId, resetUserId, setConversationId } from './storage'
import { useTheme } from './theme'
import type { AgentDonePayload, AgentRoundRecord, AnswerMeta, ChatMessage, SourceItem, ThemePreference } from './types'

export function App() {
  const { preference, resolvedTheme, setPreference } = useTheme()
  const [userId, setUserId] = useState(getOrCreateUserId)
  const [conversationId, setCurrentConversationId] = useState(getConversationId)
  const [messages, setMessages] = useState<ChatMessage[]>([])
  const [sources, setSources] = useState<SourceItem[]>([])
  const [agentRounds, setAgentRounds] = useState<AgentRoundRecord[]>([])
  const [answerMeta, setAnswerMeta] = useState<AnswerMeta | null>(null)
  const [agentDone, setAgentDone] = useState<AgentDonePayload | null>(null)
  const [predictions, setPredictions] = useState<string[]>([])
  const [question, setQuestion] = useState('')
  const [status, setStatus] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const [skillNames, setSkillNames] = useState<string[]>([])
  const activeAssistantId = useRef('')
  const composerRef = useRef<HTMLFormElement | null>(null)

  const shortUserId = useMemo(() => `${userId.slice(0, 8)}...${userId.slice(-6)}`, [userId])

  useEffect(() => {
    let cancelled = false

    fetchSkillNames()
      .then(names => {
        if (!cancelled) {
          setSkillNames(names)
        }
      })
      .catch(() => {
        if (!cancelled) {
          setSkillNames([])
        }
      })

    return () => {
      cancelled = true
    }
  }, [])

  useLayoutEffect(() => {
    const composer = composerRef.current
    if (!composer) return

    // CN: 底部输入栏是固定定位，必须用真实高度反推内容安全距离，窗口变化和手机键盘变化都不能靠写死值。
    // EN: The composer is fixed; content spacing must use its real height, not a hard-coded value, so resize and mobile keyboard changes stay correct.
    // JP: 下部入力欄は fixed 配置なので、固定値ではなく実際の高さから本文の安全余白を決めます。リサイズやモバイルキーボードにも追従します。
    const syncComposerHeight = () => {
      const height = Math.ceil(composer.getBoundingClientRect().height)
      document.documentElement.style.setProperty('--composer-height', `${height}px`)
    }

    syncComposerHeight()
    const observer = new ResizeObserver(syncComposerHeight)
    observer.observe(composer)
    window.addEventListener('resize', syncComposerHeight)
    window.visualViewport?.addEventListener('resize', syncComposerHeight)

    return () => {
      observer.disconnect()
      window.removeEventListener('resize', syncComposerHeight)
      window.visualViewport?.removeEventListener('resize', syncComposerHeight)
    }
  }, [])

  function resetIdentity() {
    const nextUserId = resetUserId()
    setUserId(nextUserId)
    setCurrentConversationId('')
    setMessages([])
    setSources([])
    setAgentRounds([])
    setAnswerMeta(null)
    setAgentDone(null)
    setPredictions([])
    setError('')
    setStatus(COPY.identityResetDone)
  }

  async function ask(nextQuestion = question) {
    const trimmed = nextQuestion.trim()
    if (!trimmed || loading) return

    const userMessage: ChatMessage = {
      id: createLocalId(),
      role: 'user',
      text: trimmed
    }
    const assistantMessage: ChatMessage = {
      id: createLocalId(),
      role: 'assistant',
      text: '',
      pending: true
    }
    activeAssistantId.current = assistantMessage.id

    setQuestion('')
    setLoading(true)
    setError('')
    setStatus(COPY.generating)
    setSources([])
    setAgentRounds([])
    setAnswerMeta(null)
    setAgentDone(null)
    setPredictions([])
    setMessages(previous => [...previous, userMessage, assistantMessage])

    try {
      await streamChat(
        {
          userId,
          conversationId: conversationId || undefined,
          question: trimmed,
          skillNames: skillNames.length > 0 ? skillNames : undefined
        },
        {
          conversation: payload => {
            setConversationId(payload.id)
            setCurrentConversationId(payload.id)
          },
          sources: setSources,
          agentRound: payload => {
            setAgentRounds(previous => [
              ...previous.filter(record => record.round !== payload.round),
              { round: payload.round, requests: payload.requests || [], hits: [] }
            ])
          },
          agentHits: payload => {
            setAgentRounds(previous =>
              previous.map(record => (record.round === payload.round ? { ...record, hits: payload.hits || [] } : record))
            )
          },
          answerMeta: payload => {
            setAnswerMeta(payload)
          },
          agentDone: payload => {
            setAgentDone(payload)
          },
          predictions: setPredictions,
          delta: text => {
            setMessages(previous =>
              previous.map(message =>
                message.id === activeAssistantId.current
                  ? { ...message, text: message.text + text, pending: false }
                  : message
              )
            )
          },
          done: () => {
            setLoading(false)
            setStatus('')
            setMessages(previous => previous.map(message => ({ ...message, pending: false })))
          },
          error: message => {
            setLoading(false)
            setError(message)
            setStatus('')
            patchEmptyAssistant(message)
          }
        }
      )
    } catch (reason) {
      const message = reason instanceof Error ? reason.message : String(reason)
      setLoading(false)
      setError(message)
      setStatus('')
      patchEmptyAssistant(message)
    }
  }

  function patchEmptyAssistant(text: string) {
    setMessages(previous =>
      previous.map(message =>
        message.id === activeAssistantId.current && !message.text ? { ...message, text, pending: false } : message
      )
    )
  }

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    ask()
  }

  return (
    <div className="app-shell">
      <div className="landscape-layer" aria-hidden="true" />

      <header className="topbar">
        <div className="brand-block">
          <span className="brand-kicker">{COPY.product}</span>
          <h1>{COPY.title}</h1>
          <p>
            {COPY.userPrefix} {shortUserId}
          </p>
        </div>

        <div className="top-actions">
          <ThemeToggle preference={preference} resolvedTheme={resolvedTheme} onChange={setPreference} />
          <button className="ghost-button" type="button" onClick={resetIdentity}>
            {COPY.resetIdentity}
          </button>
        </div>
      </header>

      <main className="chat-stage" aria-live="polite">
        {messages.length === 0 ? <EmptyState onAsk={ask} /> : messages.map(message => <MessageBubble key={message.id} message={message} />)}
      </main>

      <aside className="context-strip">
        <AnswerMetaPanel meta={answerMeta} />
        <AgentTracePanel rounds={agentRounds} done={agentDone} />
        <SourcesPanel sources={sources} />
        <PredictionsPanel predictions={predictions} onPick={ask} />
      </aside>

      <footer className="site-footer">
        {/* CN: 网站备案号必须挂在页面底部，并链接到工信部备案官网首页。 */}
        {/* EN: The site ICP filing number must stay in the page footer and link to the MIIT filing portal. */}
        {/* JP: サイトの ICP 備案番号はページ下部に表示し、工信部の備案ポータルへリンクします。 */}
        <a href={COPY.icp.url} target="_blank" rel="noreferrer" title={COPY.icp.title}>
          {COPY.icp.number}
        </a>
      </footer>

      <form className="composer" ref={composerRef} onSubmit={submit}>
        <div className="composer-inner">
          <div className="status-line">
            <span>{error || status || COPY.idleStatus}</span>
          </div>
          <div className="input-row">
            <textarea
              value={question}
              onChange={event => setQuestion(event.target.value)}
              onKeyDown={event => {
                if (event.key === 'Enter' && (event.ctrlKey || event.metaKey)) {
                  event.preventDefault()
                  ask()
                }
              }}
              maxLength={1200}
              placeholder={COPY.composerPlaceholder}
            />
            <button className="send-button" type="submit" disabled={loading || !question.trim()}>
              {loading ? COPY.sending : COPY.send}
            </button>
          </div>
        </div>
      </form>
    </div>
  )
}

function ThemeToggle({
  preference,
  resolvedTheme,
  onChange
}: {
  preference: ThemePreference
  resolvedTheme: string
  onChange: (preference: ThemePreference) => void
}) {
  return (
    <div className="theme-toggle" aria-label={`${COPY.currentTheme} ${resolvedTheme}`}>
      {(Object.keys(COPY.themeLabels) as ThemePreference[]).map(option => (
        <button
          key={option}
          type="button"
          className={preference === option ? 'active' : ''}
          onClick={() => onChange(option)}
        >
          {COPY.themeLabels[option]}
        </button>
      ))}
    </div>
  )
}

function EmptyState({ onAsk }: { onAsk: (question: string) => void }) {
  return (
    <section className="empty-state">
      <div className="empty-mark" aria-hidden="true" />
      <h2>{COPY.emptyTitle}</h2>
      <p>{COPY.emptyBody}</p>
      <div className="prompt-row">
        {COPY.samplePrompts.map(prompt => (
          <button key={prompt} type="button" onClick={() => onAsk(prompt)}>
            {prompt}
          </button>
        ))}
      </div>
    </section>
  )
}

function MessageBubble({ message }: { message: ChatMessage }) {
  return (
    <article className={`message ${message.role}`}>
      <div className="message-meta">{message.role === 'user' ? COPY.roles.user : COPY.roles.assistant}</div>
      <div className="bubble">
        {message.text ? <MarkdownMessage text={message.text} /> : <span className="typing">{COPY.typing}</span>}
      </div>
    </article>
  )
}

function AnswerMetaPanel({ meta }: { meta: AnswerMeta | null }) {
  const [expanded, setExpanded] = useState(false)
  if (!meta) return null

  const chips = [
    labelProduct(meta.productScope),
    labelAnswerType(meta.answerType),
    labelEvidence(meta.evidenceLevel),
    ...(meta.assumptions || []).slice(0, 1)
  ].filter(Boolean)
  const details = [...(meta.assumptions || []), ...(meta.clarifyingQuestions || [])]

  return (
    <section className="panel-row answer-meta-panel">
      <button className="panel-summary" type="button" onClick={() => setExpanded(value => !value)}>
        <span>
          <b>{COPY.answerMeta}</b>
          {chips.length ? ` · ${chips.join(' · ')}` : ''}
        </span>
        <small>{expanded ? COPY.collapse : COPY.expand}</small>
      </button>
      <div className="summary-chips answer-meta-chips" aria-label={COPY.answerMeta}>
        {chips.map(chip => (
          <span key={chip}>{chip}</span>
        ))}
      </div>
      {expanded && details.length ? (
        <div className="meta-detail">
          {(meta.assumptions || []).map(item => (
            <p key={`assumption-${item}`}>
              <b>{COPY.assumption}</b>
              {item}
            </p>
          ))}
          {(meta.clarifyingQuestions || []).map(item => (
            <p key={`question-${item}`}>
              <b>{COPY.needMoreInfo}</b>
              {item}
            </p>
          ))}
        </div>
      ) : null}
    </section>
  )
}

function AgentTracePanel({ rounds, done }: { rounds: AgentRoundRecord[]; done: AgentDonePayload | null }) {
  const [expanded, setExpanded] = useState(false)
  if (rounds.length === 0 && !done) return null
  const hitCount = rounds.reduce((total, round) => total + round.hits.length, 0)
  const usedSkills = done?.usedSkills || []
  const stopReason = formatStopReason(done?.stopReason)

  return (
    <section className="panel-row">
      <button className="panel-summary" type="button" onClick={() => setExpanded(value => !value)}>
        <span>
          <b>{COPY.agentTrace}</b>
          {` · ${done?.roundCount || rounds.length} ${COPY.agentTraceSummary} · ${hitCount} hits`}
          {stopReason ? ` · ${stopReason}` : ''}
        </span>
        <small>{expanded ? COPY.collapse : COPY.expand}</small>
      </button>
      {usedSkills.length ? (
        <div className="summary-chips" aria-label={COPY.agentTrace}>
          {usedSkills.slice(0, 6).map(skill => (
            <span key={skill}>{skill}</span>
          ))}
          {usedSkills.length > 6 ? <span>+{usedSkills.length - 6}</span> : null}
        </div>
      ) : null}
      {expanded ? (
        <>
          <div className="trace-list">
            {rounds
              .slice()
              .sort((left, right) => left.round - right.round)
              .map(round => (
                <article className="trace-card" key={round.round}>
                  <strong>
                    {COPY.agentRound} {round.round}
                  </strong>
                  <div className="trace-block">
                    {round.requests.map((request, index) => (
                      <p key={`${round.round}-${request.skill}-${index}`}>
                        <b>{request.skill || 'skill'}</b>
                        {request.keywords?.length ? ` · ${request.keywords.join(' / ')}` : ''}
                        {request.headings?.length ? ` · ${request.headings.join(' / ')}` : ''}
                        {request.reason ? ` · ${request.reason}` : ''}
                      </p>
                    ))}
                  </div>
                  <div className="trace-hits">
                    {round.hits.length === 0 ? (
                      <span>{COPY.agentNoHits}</span>
                    ) : (
                      round.hits.map((hit, index) => (
                        <span key={`${hit.path}-${hit.heading}-${index}`}>
                          {hit.skillName || 'skill'} · {hit.heading || hit.path || 'chunk'}
                        </span>
                      ))
                    )}
                  </div>
                </article>
              ))}
          </div>
          {done ? (
            <p className="trace-summary">
              {COPY.agentStopReason}: {stopReason || COPY.agentDone}
              {usedSkills.length ? ` · ${COPY.usedSkills}: ${usedSkills.join(', ')}` : ''}
            </p>
          ) : null}
        </>
      ) : null}
    </section>
  )
}

function labelAnswerType(value?: string): string {
  if (value === 'troubleshooting') return COPY.answerTypes.troubleshooting
  if (value === 'function_usage') return COPY.answerTypes.functionUsage
  if (value === 'code_recipe') return COPY.answerTypes.codeRecipe
  if (value === 'product_diff') return COPY.answerTypes.productDiff
  if (value === 'concept') return COPY.answerTypes.concept
  return COPY.answerTypes.unknown
}

function labelProduct(value?: string): string {
  if (!value || value === 'unclear') return COPY.productScopes.unclear
  return value
}

function labelEvidence(value?: string): string {
  if (value === 'high') return COPY.evidence.high
  if (value === 'medium') return COPY.evidence.medium
  if (value === 'fallback') return COPY.evidence.fallback
  return COPY.evidence.low
}

function formatStopReason(reason?: string): string {
  if (!reason) return ''
  const normalized = reason.trim().toLowerCase()
  if (normalized === 'final_answer') return COPY.agentFinalAnswer
  if (normalized === 'force_final') return COPY.agentForceFinal
  if (normalized === 'fallback') return COPY.agentFallback
  if (normalized === 'fallback_single_round') return COPY.agentFallback
  if (normalized === 'max_rounds') return COPY.agentMaxRounds
  if (normalized === 'force_final_max_rounds') return COPY.agentMaxRounds
  if (normalized === 'no_new_context') return COPY.agentNoNewContext
  if (normalized === 'force_final_no_new_context') return COPY.agentNoNewContext
  return reason.split('_').join(' ')
}

function SourcesPanel({ sources }: { sources: SourceItem[] }) {
  const [expanded, setExpanded] = useState(false)
  if (sources.length === 0) return null

  return (
    <section className="panel-row">
      <button className="panel-summary" type="button" onClick={() => setExpanded(value => !value)}>
        <span>
          <b>{COPY.sources}</b>
          {` · ${sources.length} ${COPY.sourcesSummary}`}
        </span>
        <small>{expanded ? COPY.collapse : COPY.expand}</small>
      </button>
      <div className="summary-chips" aria-label={COPY.sources}>
        {Array.from(new Set(sources.map(source => source.skillName).filter(Boolean)))
          .slice(0, 8)
          .map(skill => (
            <span key={skill}>{skill}</span>
          ))}
      </div>
      {expanded ? (
        <div className="scroll-cards">
          {sources.map((source, index) => (
            <article className="source-card" key={`${source.path}-${source.heading}-${index}`}>
              <strong>{source.skillName || 'skill'}</strong>
              <span>
                {source.path || ''}
                {source.heading ? ` · ${source.heading}` : ''}
              </span>
              <p>{source.excerpt || ''}</p>
            </article>
          ))}
        </div>
      ) : null}
    </section>
  )
}

function PredictionsPanel({ predictions, onPick }: { predictions: string[]; onPick: (question: string) => void }) {
  if (predictions.length === 0) return null

  return (
    <section className="panel-row predictions-panel">
      <h2>{COPY.predictions}</h2>
      <div className="prediction-row">
        {predictions.map(prediction => (
          <button key={prediction} type="button" onClick={() => onPick(prediction)}>
            {prediction}
          </button>
        ))}
      </div>
    </section>
  )
}
