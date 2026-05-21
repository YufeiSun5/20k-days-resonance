export type ThemePreference = 'system' | 'day' | 'night'
export type ResolvedTheme = 'day' | 'night'

export type ChatMessage = {
  id: string
  role: 'user' | 'assistant'
  text: string
  pending?: boolean
}

export type SourceItem = {
  skillName?: string
  path?: string
  heading?: string
  excerpt?: string
  score?: number
  lines?: string
}

export type ConversationPayload = {
  id: string
  userId: string
  title?: string
}

export type AgentSearchRequest = {
  skill?: string
  keywords?: string[]
  headings?: string[]
  reason?: string
}

export type AgentHit = SourceItem

export type AgentRoundRecord = {
  round: number
  requests: AgentSearchRequest[]
  hits: AgentHit[]
}

export type AgentDonePayload = {
  usedSkills?: string[]
  roundCount?: number
  stopReason?: string
  error?: string
}

export type AnswerMeta = {
  answerType?: string
  productScope?: string
  evidenceLevel?: string
  assumptions?: string[]
  clarifyingQuestions?: string[]
}
