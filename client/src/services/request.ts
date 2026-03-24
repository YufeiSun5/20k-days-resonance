import Taro from '@tarojs/taro'

const API_BASE_URL_KEY = 'api-base-url'
const DEFAULT_API_BASE_URL = 'https://sunyufei5.art'
const LEGACY_API_BASE_URLS = new Set([
  'http://127.0.0.1:8080',
  'http://49.232.169.142:8080'
])

// CN: 这里统一维护前端请求入口，先把 baseURL 和请求失败规则钉死，避免页面里到处散落 fetch 细节。
// EN: This file is the single request entry for the frontend. Lock baseURL handling and failure rules here so page code does not scatter transport details everywhere.
// JP: ここでフロントのリクエスト入口を一元管理します。baseURL と失敗時の扱いをここに固定し、ページ側へ通信詳細を散らさないようにします。
export function getApiBaseUrl() {
  const savedUrl = Taro.getStorageSync<string>(API_BASE_URL_KEY)?.trim()

  // CN: 老缓存里如果还挂着 localhost 或公网临时 IP，就直接迁到正式 HTTPS 域名，别让旧调试值继续拖后腿。
  // EN: If old storage still points to localhost or the temporary public IP, migrate it to the formal HTTPS domain immediately so stale debug values stop sabotaging the build.
  // JP: 古い保存値が localhost や一時的な公網 IP を指しているなら、正式な HTTPS ドメインへ即座に移行し、古いデバッグ値が足を引っ張らないようにします。
  if (!savedUrl || LEGACY_API_BASE_URLS.has(savedUrl)) {
    Taro.setStorageSync(API_BASE_URL_KEY, DEFAULT_API_BASE_URL)
    return DEFAULT_API_BASE_URL
  }

  return savedUrl
}

export function setApiBaseUrl(url: string) {
  Taro.setStorageSync(API_BASE_URL_KEY, url.trim())
}

export async function request<T>(options: {
  url: string
  method?: 'GET' | 'POST'
  data?: unknown
  headers?: Record<string, string>
}): Promise<T> {
  // CN: 最小闭环阶段先统一按 JSON 走，先把联调跑通，不在这里过早引入复杂拦截器体系。
  // EN: During the minimum-loop phase, keep everything on plain JSON first. Get integration working before introducing a heavy interceptor stack.
  // JP: 最小閉ループ段階ではまず JSON に統一します。過剰なインターセプター構成を入れる前に、まず連携を通します。
  const response = await Taro.request<T>({
    url: `${getApiBaseUrl()}${options.url}`,
    method: options.method || 'GET',
    data: options.data,
    header: {
      'Content-Type': 'application/json',
      ...(options.headers || {})
    }
  })

  if (response.statusCode < 200 || response.statusCode >= 300) {
    throw new Error(`request failed: ${response.statusCode}`)
  }

  return response.data
}