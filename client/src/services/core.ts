import { request } from './request'

// CN: core 服务层先只包住写日记、读最近日记、拿推荐三件事，让首页能尽快跑通最小业务闭环。
// EN: The core service layer wraps only three things for now: create journal, read recent journals, and get recommendation. That is enough to unlock the minimum business loop on the home page.
// JP: core サービス層は今のところ三つだけを包みます。日記作成、最近の日記取得、推薦取得です。これでホーム画面の最小業務ループを早く通せます。
export interface CreateJournalRequest {
  userId: string
  title?: string
  content: string
  originalWeekday: number
}

export interface JournalResponse {
  id: string
  userId: string
  title?: string
  content: string
  originalWeekday: number
  emotionScore?: number
  insightKeywords: string[]
  visibility: string
  createdAt: string
}

export interface RecommendJournalRequest {
  userId: string
  hashedBssid?: string
  ssidName?: string
  weekday?: number
}

export interface RecommendationResponse {
  found: boolean
  recommendationType: string
  message: string
  hashedBssid?: string
  spaceId?: string
  sourceWeekday?: number
  journal?: JournalResponse
}

export function createJournal(data: CreateJournalRequest) {
  return request<JournalResponse>({
    url: '/api/core/journals',
    method: 'POST',
    data
  })
}

export function getRecentJournals(userId: string) {
  return request<JournalResponse[]>({
    url: `/api/core/journals/users/${userId}`
  })
}

export function recommendJournal(data: RecommendJournalRequest) {
  // CN: 这里先接受 mock hashedBssid，让前端在真机 Wi-Fi 接入前也能验证推荐链路。
  // EN: Accept a mock hashedBssid here first so the frontend can verify the recommendation flow before real-device Wi-Fi integration exists.
  // JP: ここではまず mock hashedBssid を受け入れ、実機 Wi-Fi 連携前でもフロントが推薦経路を検証できるようにします。
  return request<RecommendationResponse>({
    url: '/api/core/resonance/recommend',
    method: 'POST',
    data
  })
}