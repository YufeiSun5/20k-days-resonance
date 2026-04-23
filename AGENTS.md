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

- **CN**: 当前前端首页已经从联调工作台收口成体验版首页；微信正式登录代码骨架已接入，前端在小程序环境会走 `Taro.login()`，后端会用 `jscode2session` 换取 `openid` 复用或创建用户，但云端环境变量与真机联调还需要完成最后验证。
- **EN**: The frontend home page has already been tightened from a debug workbench into a trial-home experience; the formal WeChat login skeleton is now wired in, with the Mini Program using `Taro.login()` and the backend exchanging `jscode2session` for `openid` to reuse or create users, but cloud environment variables and real-device validation still need final verification.
- **JP**: フロントのホーム画面はデバッグ用ワークベンチから体験版ホームへ収束済みです。さらに正式な WeChat ログインのコード骨格も接続済みで、ミニプログラム側は `Taro.login()` を使い、バックエンド側は `jscode2session` で `openid` を取得して既存ユーザーの再利用または新規作成を行いますが、クラウド環境変数と実機検証はまだ最終確認が必要です。

## 5. 哪些内容写成 instructions，哪些写成 skills (What Belongs in Instructions vs Skills)

- **CN**: 长期稳定、默认必须遵守的规则写进 instructions，例如服务边界、端口暴露策略、三语注释要求、更新后最低验证标准。
- **EN**: Long-lived rules that should be obeyed by default go into instructions, such as service boundaries, port exposure policy, trilingual comment requirements, and minimum post-change verification.
- **JP**: 長期的に安定し、デフォルトで守るべき規則は instructions に入れます。たとえばサービス境界、ポート公開方針、三言語コメント要件、変更後の最低検証基準などです。

- **CN**: 可重复执行、带明确步骤的流程写进 skills，例如腾讯云部署、Nginx 接证书、DBeaver 走 SSH 连库、后端单服务更新、小程序体验版上传。
- **EN**: Repeatable workflows with concrete steps belong in skills, such as Tencent Cloud deployment, Nginx certificate wiring, DBeaver over SSH, one-service backend updates, and Mini Program trial upload.
- **JP**: 手順が明確で繰り返し実行するワークフローは skills に入れます。Tencent Cloud 配置、Nginx 証明書接続、DBeaver の SSH 接続、単一サービス更新、Mini Program 体験版アップロードなどです。

## 6. 通用执行规则 (Tool-Agnostic Execution Rules)

- **CN**: 本仓库的长期规则以 `AGENTS.md` 与 `instructions/` 目录为准，不把单一编辑器专属文件当成唯一事实来源；如需兼容不同编辑器，可以增加各自适配文件，但内容应回指并服从这里的规则。
- **EN**: Long-lived repository rules are anchored in `AGENTS.md` and the `instructions/` directory. Editor-specific files must not become the only source of truth; adapter files may exist for different tools, but they should defer to the rules defined here.
- **JP**: このリポジトリの長期ルールは `AGENTS.md` と `instructions/` ディレクトリを正本とします。特定エディタ専用ファイルを唯一の事実源にしてはいけません。各ツール向けの適応ファイルは作ってよいですが、ここに書かれた規則へ従わせること。

- **CN**: 前端国际化默认要求是：所有正式 UI 文本优先走 i18n 资源，不把中文、英文、日文直接硬编码在业务组件里；只有临时调试、日志、占位验证代码才允许短期例外，收口前必须清掉。
- **EN**: Frontend internationalization default rule: all production UI text should come from i18n resources instead of being hard-coded in business components. Short-lived exceptions are only acceptable for temporary debugging, logs, or validation stubs and must be removed before the feature is considered complete.
- **JP**: フロントの国際化に関する既定ルールは、本番 UI 文言を i18n リソースから取得し、業務コンポーネントへ中国語・英語・日本語を直書きしないことです。短期の例外は一時的なデバッグ、ログ、検証用スタブに限り、収束前に必ず除去します。

- **CN**: BSSID 相关能力默认要求是：进入后端前先做 SHA-256 脱敏，前端接入时优先封装成可复用的 Hook 或等价能力模块，而不是把 Wi-Fi 获取和散列逻辑直接撒在页面里。
- **EN**: BSSID-related functionality must be SHA-256 hashed before reaching the backend. On the frontend, Wi-Fi acquisition and hashing should be wrapped in a reusable Hook or equivalent capability module rather than scattered across pages.
- **JP**: BSSID 関連機能は、バックエンドへ送る前に必ず SHA-256 で脱敏すること。フロントでは Wi-Fi 取得とハッシュ化のロジックをページへ散らさず、再利用可能な Hook または同等の機能モジュールとしてまとめること。

- **CN**: 阶段性工作做完后，必须主动回看这次变更是否需要同步更新 `skills/`、`instructions/` 和 `MEMORY.md`；凡是新增了长期约束、稳定流程、项目现状，就不要只改代码不补文档记忆。
- **EN**: After finishing a meaningful stage of work, proactively review whether the change requires updates to `skills/`, `instructions/`, and `MEMORY.md`. If the work introduced a long-lived rule, a stable workflow, or a new project-state fact, do not leave the code updated while the project knowledge remains stale.
- **JP**: ひと区切りの作業が終わったら、その変更に合わせて `skills/`、`instructions/`、`MEMORY.md` を更新すべきか必ず自発的に見直すこと。長期ルール、安定した手順、現在のプロジェクト状態が変わったなら、コードだけ直して知識資産を放置しないこと。