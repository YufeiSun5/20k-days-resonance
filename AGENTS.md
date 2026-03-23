# [CN/EN/JP] 20,000 Days: Spatial Resonance 业务逻辑大脑

## 1. 项目愿景 (Project Vision)
- **CN**: 将人生具象化为2万天存量，通过 Wi-Fi 场景触发“空间共振”记忆。
- **JP**: 人生を2万日の在庫として可視化し、Wi-Fiコンテキストを通じて「空間共鳴」メモリをトリガーします。

## 2. 核心算法逻辑 (Core Algorithms)

### A. 人生余额计算 (Life Balance)
- **公式**: $Balance = 20,000 - (CurrentDate - BirthDate)$
- **输入**: 用户出生日期 (YYYY-MM-DD)。
- **输出**: 剩余天数整数值及进度条百分比。

### B. 空间共振算法 (Spatial Resonance - Contextual Filtering)
- **定义**: 一种基于地理位置（Wi-Fi BSSID）的隐性协同过滤。
- **触发逻辑**: 
  1. **行为追踪**: 当用户 A 在 Wi-Fi (BSSID: X) 下查看“礼拜一”的日记。
  2. **空间记忆**: 后端将 `resonance:wifi:X` 的值设为 `Monday`，过期时间 1 小时。
  3. **共振推荐**: 当同空间下的用户 B 打开小程序，系统检测到相同的 BSSID X，自动推荐其本人在“礼拜一”写下的感悟。
- **技术实现**: 前端解析 BSSID -> 后端 Redis 缓存热点 Weekday -> 数据库回溯用户历史。

### C. AI 语义分析 (Semantic Analysis)
- **库**: Spring AI (OpenAI/Ollama)。
- **任务**: 在用户保存日记时，提取情感分值及“支取感悟”关键词，存储为 Vector Embeddings (pgvector)。