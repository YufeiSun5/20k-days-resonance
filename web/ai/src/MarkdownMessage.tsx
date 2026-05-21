import { useState } from 'react'
import { COPY } from './copy'

type MarkdownMessageProps = {
  text: string
}

type Segment =
  | { type: 'code'; language: string; content: string; key: string }
  | { type: 'text'; content: string; key: string }

type Block =
  | { type: 'heading'; level: number; content: string; key: string }
  | { type: 'paragraph'; content: string; key: string }
  | { type: 'unordered-list'; items: string[]; key: string }
  | { type: 'ordered-list'; items: string[]; key: string }
  | { type: 'table'; rows: string[][]; key: string }
  | { type: 'quote'; content: string; key: string }

export function MarkdownMessage({ text }: MarkdownMessageProps) {
  return (
    <div className="markdown-message">
      {splitFencedBlocks(text).map(segment =>
        segment.type === 'code' ? (
          <CodeBlock key={segment.key} language={segment.language} code={segment.content} />
        ) : (
          <TextBlock key={segment.key} text={segment.content} />
        )
      )}
    </div>
  )
}

function splitFencedBlocks(text: string): Segment[] {
  const segments: Segment[] = []
  const fence = /```([a-zA-Z0-9_-]*)\n?([\s\S]*?)```/g
  let index = 0
  let match: RegExpExecArray | null

  while ((match = fence.exec(text))) {
    if (match.index > index) {
      segments.push({ type: 'text', content: text.slice(index, match.index), key: `text-${index}` })
    }
    segments.push({
      type: 'code',
      language: match[1] || 'text',
      content: match[2].replace(/\n$/, ''),
      key: `code-${match.index}`
    })
    index = fence.lastIndex
  }

  if (index < text.length || segments.length === 0) {
    segments.push({ type: 'text', content: text.slice(index), key: `text-${index}` })
  }

  return segments
}

function TextBlock({ text }: { text: string }) {
  return <>{parseBlocks(text).map(renderBlock)}</>
}

function parseBlocks(text: string): Block[] {
  const blocks: Block[] = []
  const lines = text.replace(/\r\n/g, '\n').split('\n')
  let index = 0

  while (index < lines.length) {
    const line = lines[index]
    const trimmed = line.trim()
    if (!trimmed) {
      index++
      continue
    }

    const heading = /^(#{1,4})\s+(.+)$/.exec(trimmed)
    if (heading) {
      blocks.push({ type: 'heading', level: heading[1].length, content: heading[2], key: `h-${index}` })
      index++
      continue
    }

    if (isTableStart(lines, index)) {
      const rows: string[][] = []
      while (index < lines.length && isTableRow(lines[index])) {
        if (!isTableSeparator(lines[index])) {
          rows.push(splitTableRow(lines[index]))
        }
        index++
      }
      if (rows.length > 0) {
        blocks.push({ type: 'table', rows, key: `table-${index}` })
      }
      continue
    }

    if (/^[-*]\s+/.test(trimmed)) {
      const items: string[] = []
      while (index < lines.length && /^[-*]\s+/.test(lines[index].trim())) {
        items.push(lines[index].trim().replace(/^[-*]\s+/, ''))
        index++
      }
      blocks.push({ type: 'unordered-list', items, key: `ul-${index}` })
      continue
    }

    if (/^\d+[.)]\s+/.test(trimmed)) {
      const items: string[] = []
      while (index < lines.length && /^\d+[.)]\s+/.test(lines[index].trim())) {
        items.push(lines[index].trim().replace(/^\d+[.)]\s+/, ''))
        index++
      }
      blocks.push({ type: 'ordered-list', items, key: `ol-${index}` })
      continue
    }

    if (/^>\s?/.test(trimmed)) {
      const quotes: string[] = []
      while (index < lines.length && /^>\s?/.test(lines[index].trim())) {
        quotes.push(lines[index].trim().replace(/^>\s?/, ''))
        index++
      }
      blocks.push({ type: 'quote', content: quotes.join('\n'), key: `quote-${index}` })
      continue
    }

    const paragraph: string[] = []
    while (index < lines.length && shouldContinueParagraph(lines, index)) {
      paragraph.push(lines[index].trim())
      index++
    }
    blocks.push({ type: 'paragraph', content: paragraph.join('\n'), key: `p-${index}` })
  }

  return blocks
}

function shouldContinueParagraph(lines: string[], index: number) {
  const trimmed = lines[index].trim()
  return (
    trimmed.length > 0 &&
    !/^(#{1,4})\s+/.test(trimmed) &&
    !/^[-*]\s+/.test(trimmed) &&
    !/^\d+[.)]\s+/.test(trimmed) &&
    !/^>\s?/.test(trimmed) &&
    !isTableStart(lines, index)
  )
}

function renderBlock(block: Block) {
  if (block.type === 'heading') {
    const Tag = `h${Math.min(block.level + 2, 6)}` as keyof JSX.IntrinsicElements
    return <Tag key={block.key}>{renderInline(block.content)}</Tag>
  }
  if (block.type === 'unordered-list') {
    return (
      <ul key={block.key}>
        {block.items.map((item, index) => (
          <li key={index}>{renderInline(item)}</li>
        ))}
      </ul>
    )
  }
  if (block.type === 'ordered-list') {
    return (
      <ol key={block.key}>
        {block.items.map((item, index) => (
          <li key={index}>{renderInline(item)}</li>
        ))}
      </ol>
    )
  }
  if (block.type === 'table') {
    const [head, ...body] = block.rows
    return (
      <div className="markdown-table-wrap" key={block.key}>
        <table>
          <thead>
            <tr>
              {head.map((cell, index) => (
                <th key={index}>{renderInline(cell)}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {body.map((row, rowIndex) => (
              <tr key={rowIndex}>
                {row.map((cell, cellIndex) => (
                  <td key={cellIndex}>{renderInline(cell)}</td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    )
  }
  if (block.type === 'quote') {
    return <blockquote key={block.key}>{renderInline(block.content)}</blockquote>
  }
  return <p key={block.key}>{renderInline(block.content)}</p>
}

function isTableStart(lines: string[], index: number) {
  return isTableRow(lines[index]) && index + 1 < lines.length && isTableSeparator(lines[index + 1])
}

function isTableRow(line: string) {
  const trimmed = line.trim()
  return trimmed.startsWith('|') && trimmed.endsWith('|') && trimmed.includes('|')
}

function isTableSeparator(line: string) {
  return /^\|?\s*:?-{3,}:?\s*(\|\s*:?-{3,}:?\s*)+\|?$/.test(line.trim())
}

function splitTableRow(line: string) {
  return line
    .trim()
    .replace(/^\|/, '')
    .replace(/\|$/, '')
    .split('|')
    .map(cell => cell.trim())
}

function renderInline(text: string) {
  const parts = text.split(/(`[^`]+`|\*\*[^*]+\*\*)/g)
  return parts.map((part, index) => {
    if (part.startsWith('`') && part.endsWith('`')) {
      return <code key={index}>{part.slice(1, -1)}</code>
    }
    if (part.startsWith('**') && part.endsWith('**')) {
      return <strong key={index}>{part.slice(2, -2)}</strong>
    }
    return <span key={index}>{part}</span>
  })
}

function CodeBlock({ language, code }: { language: string; code: string }) {
  const [copied, setCopied] = useState(false)

  async function copyCode() {
    try {
      await navigator.clipboard.writeText(code)
    } catch {
      const textarea = document.createElement('textarea')
      textarea.value = code
      textarea.style.position = 'fixed'
      textarea.style.opacity = '0'
      document.body.appendChild(textarea)
      textarea.select()
      document.execCommand('copy')
      textarea.remove()
    }
    setCopied(true)
    window.setTimeout(() => setCopied(false), 1400)
  }

  return (
    <figure className="code-block">
      <figcaption>
        <span>{language}</span>
        <button type="button" onClick={copyCode}>
          {copied ? COPY.copied : COPY.copy}
        </button>
      </figcaption>
      <pre>
        <code>{code}</code>
      </pre>
    </figure>
  )
}
