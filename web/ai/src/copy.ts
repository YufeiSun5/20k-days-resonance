export const COPY = {
  product: '20,000 Days',
  title: 'KingSCADA笔记快查',
  userPrefix: '匿名身份',
  resetIdentity: '重置身份',
  identityResetDone: '已重置匿名身份',
  generating: '正在生成回答...',
  idleStatus: '直接提问，后端会按匿名 userId 隔离会话。',
  composerPlaceholder: '问一个 KingSCADA / KingHistorian / 组态王问题',
  send: '发送',
  sending: '生成中',
  emptyTitle: '把问题放进来，先跑通答案。',
  emptyBody: '页面只生成本地匿名 userId，不走微信登录。接口仍使用线上 gateway 的 SSE 流式回答。',
  samplePrompts: ['DataQuality=192 是什么意思？', 'RawByTime 和 Calculated 怎么选？', '组态王报表怎么写入单元格？'],
  roles: {
    user: '你',
    assistant: 'AI'
  },
  typing: '正在整理回答...',
  agentTrace: '检索过程',
  agentRound: '第',
  agentNoHits: '本轮没有新增命中',
  agentStopReason: '停止原因',
  agentDone: '已完成',
  agentFinalAnswer: '已生成最终答案',
  agentForceFinal: '已强制收束回答',
  agentFallback: '已切回单轮检索',
  agentMaxRounds: '达到最大轮数',
  agentNoNewContext: '连续无新命中',
  usedSkills: '使用 skill',
  answerMeta: '答案画像',
  assumption: '假设：',
  needMoreInfo: '待确认：',
  answerTypes: {
    troubleshooting: '排障',
    functionUsage: '函数用法',
    codeRecipe: '代码示例',
    productDiff: '产品差异',
    concept: '概念解释',
    unknown: '通用问答'
  },
  productScopes: {
    unclear: '产品未明确'
  },
  evidence: {
    high: '证据较充分',
    medium: '证据中等',
    low: '证据较少',
    fallback: '兜底检索'
  },
  agentTraceSummary: '轮检索',
  sources: '引用来源',
  sourcesSummary: '条引用',
  expand: '展开',
  collapse: '收起',
  predictions: '继续追问',
  copy: '复制',
  copied: '已复制',
  icp: {
    number: '辽ICP备2026006037号',
    url: 'https://beian.miit.gov.cn/',
    title: '工信部备案管理系统'
  },
  themeLabels: {
    system: '跟随系统',
    day: '白天',
    night: '黑夜'
  },
  currentTheme: '当前主题'
} as const
