import { request } from './request'

// CN: profile 服务层先只暴露最小用户联调接口，不在第一版里引入真正的登录协议。
// EN: The profile service layer exposes only the minimum user integration APIs first. Real authentication protocol can come later.
// JP: profile サービス層はまず最小限のユーザー連携 API だけを公開します。本物の認証プロトコルは後で入れます。
export interface MockLoginRequest {
  displayName: string
  locale: string
  provider: 'wechat_miniapp' | 'email'
}

export interface UserProfileResponse {
  id: string
  displayName: string
  avatarUrl?: string
  birthDate?: string
  locale: string
  status: string
  createdAt: string
  updatedAt: string
}

export function mockLogin(data: MockLoginRequest) {
  return request<UserProfileResponse>({
    url: '/api/profile/users/mock-login',
    method: 'POST',
    data
  })
}

export function getUser(userId: string) {
  // CN: 显式按 userId 取用户，避免前端过早依赖“当前登录态一定存在”的假设。
  // EN: Fetch the user by explicit userId so the frontend does not prematurely depend on an implicit current-session assumption.
  // JP: userId を明示して取得し、フロントが暗黙のログインセッション前提に早く依存しないようにします。
  return request<UserProfileResponse>({
    url: `/api/profile/users/${userId}`
  })
}