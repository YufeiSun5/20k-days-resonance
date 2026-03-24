# [开发进度记忆库]
# - 已完成: 后端三服务基础骨架、分库 SQL 结构、VS Code 一键运行/调试配置、最小业务接口、真实最小测试、前端最小闭环页、云端 Docker 部署、Nginx + HTTPS、微信开发者工具联调、体验版打通。
# - 当前线上拓扑: 80/443 -> Nginx -> gateway-service(8080) -> core-service(8081) / profile-service(8082)。
# - 当前数据库基线: resonance_profile 与 resonance_core；云端 PostgreSQL 应只绑定服务器本机 127.0.0.1:15432，不直接暴露公网 5432。
# - 当前前端状态: 首页已从联调工作台收口成体验版首页，默认云端 HTTPS 接口为 https://sunyufei5.art。
# - 当前目标: 收口云端运行维护流程、稳定 DBeaver/SSH 查库路径、继续推进正式发布前的产品化与登录链路收口。
# - 待办: 正式登录链路、BSSID 真能力接入、正式审核前的隐私/产品页收口、云服务器 compose 文件与本地仓库持续同步机制。
