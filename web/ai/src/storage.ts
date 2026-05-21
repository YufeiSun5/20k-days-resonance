const USER_KEY = '20k-ai-web-user-id'
const CONVERSATION_KEY = '20k-ai-web-conversation-id'
const THEME_KEY = '20k-ai-web-theme'

export function getOrCreateUserId(): string {
  const existing = localStorage.getItem(USER_KEY)
  if (existing) return existing

  const id = createLocalId()
  localStorage.setItem(USER_KEY, id)
  return id
}

export function resetUserId(): string {
  localStorage.removeItem(USER_KEY)
  localStorage.removeItem(CONVERSATION_KEY)
  return getOrCreateUserId()
}

export function getConversationId(): string {
  return localStorage.getItem(CONVERSATION_KEY) || ''
}

export function setConversationId(id: string): void {
  localStorage.setItem(CONVERSATION_KEY, id)
}

export function getThemePreference(): string {
  return localStorage.getItem(THEME_KEY) || 'system'
}

export function setThemePreference(value: string): void {
  localStorage.setItem(THEME_KEY, value)
}

export function createLocalId(): string {
  if (globalThis.crypto?.randomUUID) return globalThis.crypto.randomUUID()
  return fallbackUuid()
}

function fallbackUuid(): string {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, char => {
    const value = Math.floor(Math.random() * 16)
    const next = char === 'x' ? value : (value & 0x3) | 0x8
    return next.toString(16)
  })
}
