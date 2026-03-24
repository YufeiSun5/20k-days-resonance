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

## 3. 工程写作与注释约束 (Engineering Writing Rules)

- **CN**: 只要是实际承载业务功能的位置，例如 Controller、Service、Entity、核心页面、核心状态流转、关键算法段、核心 SQL，都必须补充中英日三种语言的注释或说明。
- **EN**: Any location that carries real business functionality, such as controllers, services, entities, core pages, key state transitions, critical algorithm blocks, and core SQL, must include trilingual comments or descriptions in Chinese, English, and Japanese.
- **JP**: 実際の業務機能を担う箇所、たとえば Controller、Service、Entity、主要ページ、重要な状態遷移、重要アルゴリズム部分、主要 SQL には、中国語・英語・日本語の三言語コメントまたは説明を付けること。

- **CN**: 代码和注释风格默认采用 Linus Benedict Torvalds 风格，强调直接、具体、少废话、反对花哨抽象包装；先把逻辑写清楚，再谈形式。
- **EN**: Code and comments should default to a Linus Benedict Torvalds style: direct, concrete, low-fluff, and hostile to decorative abstraction. Clarity of logic comes before presentation.
- **JP**: コードとコメントのスタイルは、Linus Benedict Torvalds 風を基本とし、直接的・具体的・簡潔で、飾り立てた抽象化を避けること。見た目より先にロジックの明瞭さを優先すること。

- **CN**: 实际功能模块必须提供对应测试用例，至少要让人能直接验证功能是否可用；如果暂时不写完整自动化测试，也要保留最小可执行验证路径。
- **EN**: Functional modules must provide corresponding test coverage, at minimum enough for a human to directly verify that the feature works; if full automation is deferred, keep a minimal executable verification path.
- **JP**: 実際の機能モジュールには対応するテストを必ず用意し、少なくとも人が直接動作確認できる状態を保つこと。完全な自動テストを後回しにする場合でも、最小限の実行可能な検証経路は残すこと。

## 4. 当前项目状态 (Current Project State)

- **CN**: 当前已完成本地后端三服务、分库 SQL、VS Code 调试链路、最小业务接口、前端最小闭环、腾讯云 Docker 部署、Nginx + HTTPS 域名接入、微信开发者工具与体验版打通。
- **EN**: The project has already completed the local three-service backend, split-database SQL, VS Code debug flow, minimum business APIs, frontend minimum loop, Tencent Cloud Docker deployment, Nginx + HTTPS domain ingress, and WeChat DevTools / trial build validation.
- **JP**: 現時点で、ローカル三サービスバックエンド、分割 DB SQL、VS Code デバッグ経路、最小業務 API、フロント最小閉ループ、Tencent Cloud Docker 配置、Nginx + HTTPS ドメイン入口、WeChat DevTools と体験版の動作確認まで完了しています。

- **CN**: 当前线上拓扑为 `80/443 -> Nginx -> gateway-service:8080 -> core/profile`，PostgreSQL 推荐只绑定服务器本机 `127.0.0.1:15432`，不直接暴露公网数据库端口。
- **EN**: The current online topology is `80/443 -> Nginx -> gateway-service:8080 -> core/profile`, and PostgreSQL should stay bound only to server-local `127.0.0.1:15432` instead of exposing a public database port.
- **JP**: 現在のオンライン構成は `80/443 -> Nginx -> gateway-service:8080 -> core/profile` であり、PostgreSQL は公網へ公開せずサーバローカル `127.0.0.1:15432` のみにバインドする前提です。

- **CN**: 当前前端首页已经从联调工作台收口成体验版首页，但账户链路仍是最小 mock 登录形态，后续如果走正式审核，需要继续把产品页和正式登录链路补全。
- **EN**: The frontend home page has already been tightened from a debug workbench into a trial-home experience, but the account flow is still a minimum mock-login path and should be replaced before formal review.
- **JP**: フロントのホーム画面はデバッグ用ワークベンチから体験版ホームへ収束済みですが、アカウント経路はまだ最小の mock-login なので、本審査前に正式ログイン経路へ置き換える必要があります。

## 5. 哪些内容写成 instructions，哪些写成 skills (What Belongs in Instructions vs Skills)

- **CN**: 长期稳定、默认必须遵守的规则写进 instructions，例如服务边界、端口暴露策略、三语注释要求、更新后最低验证标准。
- **EN**: Long-lived rules that should be obeyed by default go into instructions, such as service boundaries, port exposure policy, trilingual comment requirements, and minimum post-change verification.
- **JP**: 長期的に安定し、デフォルトで守るべき規則は instructions に入れます。たとえばサービス境界、ポート公開方針、三言語コメント要件、変更後の最低検証基準などです。

- **CN**: 可重复执行、带明确步骤的流程写进 skills，例如腾讯云部署、Nginx 接证书、DBeaver 走 SSH 连库、后端单服务更新、小程序体验版上传。
- **EN**: Repeatable workflows with concrete steps belong in skills, such as Tencent Cloud deployment, Nginx certificate wiring, DBeaver over SSH, one-service backend updates, and Mini Program trial upload.
- **JP**: 手順が明確で繰り返し実行するワークフローは skills に入れます。Tencent Cloud 配置、Nginx 証明書接続、DBeaver の SSH 接続、単一サービス更新、Mini Program 体験版アップロードなどです。