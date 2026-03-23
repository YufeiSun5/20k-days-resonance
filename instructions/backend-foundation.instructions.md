# [后端基础指令]

## 当前服务边界
- gateway-service: API 网关，不持有独立数据库。
- core-service: 核心业务服务，负责 journals、wifi_space、journal_view_event、recommendation_log、journal_embedding。
- profile-service: 账户与身份服务，负责 users、auth_identity。

## 当前环境基线
- Java: 工作区固定使用 server/.vfox/sdks/java 下的 Java 21。
- Maven: 工作区固定使用 server/.vfox/sdks/maven/bin/mvn.cmd。
- Postgres: 本地 Docker，用户名 postgres，密码 root。
- Redis: 本地 Docker，默认 localhost:6379。

## 当前端口约定
- gateway-service: 8080
- core-service: 8081
- profile-service: 8082
- gateway debug: 5005
- core debug: 5006
- profile debug: 5007

## 当前数据库拆分
- resonance_profile: 账户与身份数据。
- resonance_core: 日记、空间、推荐、AI 嵌入数据。
- 严禁跨库外键；core 中的 user_id 仅保存 UUID 值，不写 REFERENCES users(id)。

## 开发优先级
1. 先保证本地 Docker 基础设施可用。
2. 先实现 profile/core 的最小业务接口，再接 gateway 转发。
3. AI 分析仍视为外部 API，不单独拆库。

## 修改后端时的默认动作
1. 保持三服务边界不被随意打穿。
2. 新增表或字段时，优先更新 sql/profile 或 sql/core 下的版本化 SQL。
3. 涉及启动或调试链路时，同时检查 .vscode/tasks.json 与 .vscode/launch.json 是否仍然匹配。