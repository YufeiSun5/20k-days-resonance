const { test, expect } = require('@playwright/test')

const baseUrl = process.env.WEB_AI_URL || 'http://127.0.0.1:4173/ai/'
const screenshotPath = process.env.WEB_AI_SCREENSHOT || 'D:/DEV_D/Spring4/20k-days-resonance/output-web-ai-ui.png'

test('renders markdown and keeps trace/source details collapsed by default', async ({ page }) => {
  await page.setViewportSize({ width: 1440, height: 1000 })
  await page.route('**/api/core/ai/skills', route =>
    route.fulfill({
      contentType: 'application/json',
      body: JSON.stringify(['kingscada-functions', 'kingscada-ch18-database'])
    })
  )
  await page.route('**/api/core/ai/chat/stream', route => {
    const events = [
      ['conversation', { id: 'demo', userId: 'demo' }],
      [
        'agent_round',
        {
          round: 1,
          requests: [
            {
              skill: 'kingscada-functions',
              keywords: ['SQLSelect'],
              headings: ['SQL访问函数'],
              reason: '验证 Markdown 和折叠摘要'
            }
          ]
        }
      ],
      [
        'agent_hits',
        {
          round: 1,
          hits: [
            {
              skillName: 'kingscada-functions',
              path: 'kingscada-functions/references/demo.md',
              heading: 'SQLSelect',
              excerpt: '命中片段'
            }
          ]
        }
      ],
      [
        'delta',
        {
          text: [
            '# SQLSelect 用法',
            '',
            '**结论**：这是一个选择集查询函数。',
            '',
            '1. 先 `SQLConnect()` 建立连接。',
            '2. 再调用 `SQLSelect(...)`。',
            '',
            '| 参数 | 说明 |',
            '|---|---|',
            '| nConnectID | 连接号 |',
            '| strTableName | 表名 |',
            '',
            '> 注意产品边界。',
            '',
            '```c',
            'SQLSelect(DeviceID, "KingTable", "Bind1", "", "");',
            '```'
          ].join('\n')
        }
      ],
      [
        'answer_meta',
        {
          answerType: 'function_usage',
          productScope: 'KingSCADA',
          evidenceLevel: 'medium',
          assumptions: ['按 KingSCADA 函数资料回答'],
          clarifyingQuestions: ['你使用的是哪个版本？']
        }
      ],
      [
        'sources',
        [
          {
            skillName: 'kingscada-functions',
            path: 'kingscada-functions/references/demo.md',
            heading: 'SQLSelect',
            excerpt: '访问数据库，得到一个特定的选择集。'
          }
        ]
      ],
      ['agent_done', { roundCount: 1, usedSkills: ['kingscada-functions'], stopReason: 'final_answer' }],
      ['predictions', ['继续问一个例子']],
      ['done', { conversationId: 'demo' }]
    ]
    const body = events.map(([event, data]) => `event:${event}\ndata:${JSON.stringify(data)}\n\n`).join('')
    return route.fulfill({
      status: 200,
      headers: { 'Content-Type': 'text/event-stream' },
      body
    })
  })

  await page.goto(baseUrl)
  await page.fill('textarea', '测试 markdown')
  await page.click('button.send-button')

  await expect(page.locator('.markdown-message h3')).toHaveCount(1)
  await expect(page.locator('.markdown-message ol')).toHaveCount(1)
  await expect(page.locator('.markdown-message table')).toHaveCount(1)
  await expect(page.locator('.markdown-message blockquote')).toHaveCount(1)
  await expect(page.locator('.code-block')).toHaveCount(1)
  await expect(page.locator('.panel-summary')).toHaveCount(3)
  await expect(page.locator('.answer-meta-chips')).toContainText('KingSCADA')
  await expect(page.locator('.answer-meta-chips')).toContainText('函数用法')
  await expect(page.locator('.source-card')).toHaveCount(0)
  await expect(page.locator('.trace-card')).toHaveCount(0)

  await page.screenshot({ path: screenshotPath, fullPage: true })
})
