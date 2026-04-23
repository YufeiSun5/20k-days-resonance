import { useState } from 'react'
import { Button, Image, Input, Text, Textarea, View } from '@tarojs/components'
import Taro, { useLoad } from '@tarojs/taro'
import { createJournal, getRecentJournals, recommendJournal, type JournalResponse, type RecommendationResponse } from '../../services/core'
import { mockLogin, type UserProfileResponse, wechatLogin } from '../../services/profile'
import { getApiBaseUrl } from '../../services/request'
import './index.scss'

const WEEKDAY_LABELS = ['周一', '周二', '周三', '周四', '周五', '周六', '周日']

function formatCreatedAt(value?: string) {
  if (!value) {
    return '刚刚'
  }

  const date = new Date(value)

  if (Number.isNaN(date.getTime())) {
    return value
  }

  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  const hour = String(date.getHours()).padStart(2, '0')
  const minute = String(date.getMinutes()).padStart(2, '0')

  return `${year}-${month}-${day} ${hour}:${minute}`
}

// CN: 首页现在收口成个人记事本首页，不再把原始接口调试细节直接摊给用户，但底层仍沿用现有最小闭环能力。
// EN: The home page is now tightened into a personal notebook home instead of exposing raw API-debug details, while the existing minimum-loop backend remains in use underneath.
// JP: ホーム画面は raw な API デバッグ画面ではなく個人記録のトップへ収束させますが、下では既存の最小閉ループ能力をそのまま使います。
export default function Index () {
  const isWeapp = process.env.TARO_ENV === 'weapp'
  const [sessionDisplayName, setSessionDisplayName] = useState('')
  const [sessionAvatarUrl, setSessionAvatarUrl] = useState('')
  const [spaceId, setSpaceId] = useState('demo-space-home')
  const [weekday, setWeekday] = useState('1')
  const [journalTitle, setJournalTitle] = useState('礼拜一')
  const [journalContent, setJournalContent] = useState('今天先把最小闭环跑通。')
  const [currentUser, setCurrentUser] = useState<UserProfileResponse | null>(null)
  const [lastJournal, setLastJournal] = useState<JournalResponse | null>(null)
  const [recommendation, setRecommendation] = useState<RecommendationResponse | null>(null)
  const [recentJournals, setRecentJournals] = useState<JournalResponse[]>([])
  const [statusText, setStatusText] = useState('云端记事本已连接，可以开始记录。')

  useLoad(() => {
    console.log(`Notebook ready: ${getApiBaseUrl()}`)
  })

  function handleChooseAvatar(event: { detail: { avatarUrl?: string } }) {
    const avatarUrl = event.detail.avatarUrl?.trim()
    if (!avatarUrl) {
      return
    }
    setSessionAvatarUrl(avatarUrl)
    setStatusText('已获取当前微信头像，仅在本次会话里使用。')
  }

  async function handleMockLogin () {
    // CN: 先拿到稳定 userId，后面的所有写日记和推荐请求都靠这个主键串起来。
    // EN: Get a stable userId first. Every later journal and recommendation request hangs off this key.
    // JP: まず安定した userId を取得します。後続の日記作成と推薦リクエストはすべてこのキーにぶら下がります。
    try {
      setStatusText(isWeapp ? '正在发起微信登录...' : '正在创建 mock 用户...')
      const user = isWeapp
        ? await loginWithWechat()
        : await mockLogin({
            displayName: 'Notebook User',
            locale: 'zh-CN',
            provider: 'wechat_miniapp'
          })
      setCurrentUser(user)
      setRecentJournals([])
      setRecommendation(null)
      setStatusText(`${isWeapp ? '微信登录' : 'mock-login'} 成功，userId=${user.id}`)
    } catch (error) {
      setStatusText(`${isWeapp ? '微信登录' : 'mock-login'} 失败: ${String(error)}`)
    }
  }

  async function loginWithWechat () {
    const loginResult = await Taro.login()
    if (!loginResult.code) {
      throw new Error('wechat login code missing')
    }
    return wechatLogin({
      code: loginResult.code,
      locale: 'zh-CN'
    })
  }

  async function handleCreateJournal () {
    if (!currentUser) {
      setStatusText('请先 mock-login')
      return
    }
    // CN: 最小闭环先只写一条简单日记，目标是验证后端路径，不是先做复杂编辑器。
    // EN: Write a simple journal first. The goal here is to verify backend flow, not to build a complex editor prematurely.
    // JP: まずは単純な日記を一件だけ書き込みます。目的はバックエンド経路の検証であり、先に複雑なエディタを作ることではありません。
    try {
      setStatusText('正在写入日记...')
      const journal = await createJournal({
        userId: currentUser.id,
        title: journalTitle,
        content: journalContent,
        originalWeekday: Number(weekday)
      })
      setLastJournal(journal)
      setJournalContent('')
      setStatusText(`日记写入成功，journalId=${journal.id}`)
      const journals = await getRecentJournals(currentUser.id)
      setRecentJournals(journals)
    } catch (error) {
      setStatusText(`日记写入失败: ${String(error)}`)
    }
  }

  async function handleRecommend () {
    if (!currentUser) {
      setStatusText('请先 mock-login')
      return
    }
    // CN: 推荐阶段先传 mock spaceId，等真机接上 Wi-Fi 能力后再换成真实 hashedBssid。
    // EN: Pass a mock spaceId for recommendation first. Swap it for a real hashedBssid only after real-device Wi-Fi capability is wired in.
    // JP: 推薦段階ではまず mock spaceId を送ります。実機 Wi-Fi 機能が繋がってから本物の hashedBssid に差し替えます。
    try {
      setStatusText('正在请求推荐...')
      const result = await recommendJournal({
        userId: currentUser.id,
        hashedBssid: spaceId,
        ssidName: 'Mock WiFi',
        weekday: Number(weekday)
      })
      setRecommendation(result)
      setStatusText(`推荐请求完成: ${result.recommendationType}`)
    } catch (error) {
      setStatusText(`推荐请求失败: ${String(error)}`)
    }
  }

  async function handleLoadRecent () {
    if (!currentUser) {
      setStatusText('请先 mock-login')
      return
    }
    // CN: 这里主要用来确认“刚写进去的数据确实能从后端再读出来”。
    // EN: This is mainly for proving that the data just written can actually be read back from the backend.
    // JP: ここは「今書いたデータが本当にバックエンドから読み返せるか」を確認するためのものです。
    try {
      setStatusText('正在读取最近日记...')
      const journals = await getRecentJournals(currentUser.id)
      setRecentJournals(journals)
      setStatusText(`读取完成，共 ${journals.length} 条`)
    } catch (error) {
      setStatusText(`读取最近日记失败: ${String(error)}`)
    }
  }

  const weekdayNumber = Number(weekday)
  const weekdayLabel = WEEKDAY_LABELS[(weekdayNumber - 1 + 7) % 7] || '周一'
  const identityName = sessionDisplayName.trim() || currentUser?.displayName || '尚未填写昵称'
  const identityTip = isWeapp
    ? '头像和昵称只在当前会话里使用，不写回后端数据库。'
    : '当前不是微信小程序环境，只能先用手动昵称继续。'

  return (
    <View className='index'>
      <View className='hero-card'>
        <Text className='hero-kicker'>20,000 Days</Text>
        <Text className='hero-title'>你的日常，不该散在聊天记录里</Text>
        <Text className='hero-subtitle'>把今天记下来，再用一个熟悉的空间线索，把以前的自己重新翻出来。</Text>
        <View className='hero-meta-row'>
          <View className='hero-meta-chip'>
            <Text className='hero-meta-label'>当前空间</Text>
            <Text className='hero-meta-value'>{spaceId}</Text>
          </View>
          <View className='hero-meta-chip'>
            <Text className='hero-meta-label'>回看星期</Text>
            <Text className='hero-meta-value'>{weekdayLabel}</Text>
          </View>
        </View>
      </View>

      <View className='panel'>
        <Text className='panel-title'>我的身份</Text>
        <Text className='panel-copy'>先确认当前记录者。微信头像和昵称只拿来做当前会话展示，不写进后端；后端现在只用微信登录拿到稳定 userId，把你的记录串起来。</Text>
        <View className='identity-picker'>
          <View className='avatar-preview-shell'>
            {sessionAvatarUrl ? <Image className='avatar-preview' src={sessionAvatarUrl} mode='aspectFill' /> : <Text className='avatar-placeholder'>头像</Text>}
          </View>
          <View className='identity-picker-body'>
            {isWeapp ? (
              <Button className='action-button secondary avatar-button' openType='chooseAvatar' onChooseAvatar={handleChooseAvatar}>获取微信头像</Button>
            ) : null}
            <Input
              className='field-input'
              value={sessionDisplayName}
              type={isWeapp ? 'nickname' : 'text'}
              onInput={(event) => setSessionDisplayName(event.detail.value)}
              placeholder='填写或选择当前昵称'
            />
            <Text className='panel-copy subtle-copy'>{identityTip}</Text>
          </View>
        </View>
        <Button className='action-button' onClick={handleMockLogin}>{isWeapp ? '微信进入我的记事本' : '进入我的记事本'}</Button>
        <View className='summary-card'>
          <Text className='summary-label'>当前用户</Text>
          <Text className='summary-value'>{identityName}</Text>
          <Text className='summary-subvalue'>{currentUser ? `${currentUser.id} · 已拿到后端 userId` : '点击上方按钮后生成个人 userId'}</Text>
        </View>
      </View>

      <View className='panel'>
        <Text className='panel-title'>写下今天</Text>
        <Text className='panel-copy'>先别追求复杂格式。第一版就是把想法留下来，后面再做结构化和编辑体验。</Text>
        <Input className='field-input' value={journalTitle} onInput={(event) => setJournalTitle(event.detail.value)} placeholder='今天想给这条记录起什么标题' />
        <Textarea className='field-textarea' value={journalContent} onInput={(event) => setJournalContent(event.detail.value)} maxlength={500} placeholder='写点今天发生的事、情绪、想法，或者一句你以后会想再看见的话。' />
        <Button className='action-button' onClick={handleCreateJournal}>保存这条记录</Button>
        <View className='summary-grid'>
          <View className='summary-card compact'>
            <Text className='summary-label'>最近写入</Text>
            <Text className='summary-value'>{lastJournal?.title || '还没有新记录'}</Text>
            <Text className='summary-subvalue'>{formatCreatedAt(lastJournal?.createdAt)}</Text>
          </View>
          <View className='summary-card compact'>
            <Text className='summary-label'>累计读取</Text>
            <Text className='summary-value'>{recentJournals.length} 条</Text>
            <Text className='summary-subvalue'>每次写完都会自动刷新</Text>
          </View>
        </View>
      </View>

      <View className='panel'>
        <Text className='panel-title'>空间回响</Text>
        <Text className='panel-copy'>这一步先用可控的空间标识验证推荐链路。等以后接真机 Wi-Fi 能力，再把这里换成真实的 BSSID 哈希。</Text>
        <Input className='field-input' value={spaceId} onInput={(event) => setSpaceId(event.detail.value)} placeholder='空间标识，例如 demo-space-home' />
        <Input className='field-input' value={weekday} onInput={(event) => setWeekday(event.detail.value)} placeholder='回看星期 1-7' type='number' />
        <View className='button-row'>
          <Button className='action-button half' onClick={handleRecommend}>获取一条回响</Button>
          <Button className='action-button secondary half' onClick={handleLoadRecent}>刷新最近记录</Button>
        </View>
        <View className='resonance-card'>
          <Text className='summary-label'>本次推荐</Text>
          <Text className='resonance-title'>{recommendation?.journal?.title || '还没有触发回响'}</Text>
          <Text className='resonance-copy'>{recommendation?.journal?.content || '输入空间标识和星期后，试着把以前的自己捞出来。'}</Text>
          <Text className='summary-subvalue'>{recommendation ? `${recommendation.recommendationType} · ${recommendation.message}` : '当前没有推荐结果'}</Text>
        </View>
      </View>

      <View className='panel status-panel'>
        <Text className='panel-title'>当前状态</Text>
        <Text className='status-text'>{statusText}</Text>
      </View>

      <View className='panel'>
        <Text className='panel-title'>最近记录</Text>
        <Text className='panel-copy'>先把最近几条内容明确地展示出来，而不是把原始 JSON 丢给用户自己读。</Text>
        {recentJournals.length > 0 ? recentJournals.map((journal) => (
          <View className='journal-card' key={journal.id}>
            <View className='journal-card-header'>
              <Text className='journal-card-title'>{journal.title || '未命名记录'}</Text>
              <Text className='journal-card-date'>{formatCreatedAt(journal.createdAt)}</Text>
            </View>
            <Text className='journal-card-content'>{journal.content}</Text>
          </View>
        )) : <Text className='empty-text'>还没有历史记录，先写下今天的第一条。</Text>}
      </View>
    </View>
  )
}
