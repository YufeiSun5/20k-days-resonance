# 北京亚控 Skills 路引

北京亚控相关 Skills 索引。当前包含 KingSCADA/KS 集合完整手册 39 个、KingSCADA 精选专题 5 个、KingHistorian 1 个、组态王 8 个。遇到问题时按场景选对应 Skill。

---

## 产品边界与同名函数规则

这份知识库同时包含 KingSCADA、KingHistorian、组态王（KingView 7.5）资料。三者函数名、脚本术语、数据库访问方式有重名或近似情况，检索和回答时必须先确定产品范围。

1. 用户明确说 `KingSCADA`、`King Scada`、`KS` 或问 `kingscada-*` skill 时，只能优先检索 `kingscada-*`。不要用 `kingview-*` 的函数、参数或脚本语法补 KingSCADA 答案。
2. 用户明确说 `组态王`、`KingView`、`King View` 时，只能优先检索 `kingview-*`。不要把 KingSCADA 的 KDB/DataSet/报表方法当成组态王能力。
3. 用户明确说 `KingHistorian`、`KH`、`工业库`、`History/Reatime/Tag 表` 或 KDB-SQL 时，优先检索 `kingscada-kh`；如果问题是 KingSCADA 工程里调用历史库，再配合 `kingscada-dataset`、`kingscada-ch18-database`、`kingscada-ch23-history`。
4. 如果用户问“KingSCADA 的某个函数怎么用”，但该函数只在 `kingview-*` 中命中，不能直接回答“KingSCADA 支持”。应说明：当前 KingSCADA skill 未检索到该函数，只有组态王资料中存在类似函数；需要用户确认是否实际问组态王，或提供 KingSCADA 版本/手册位置。
5. 如果用户没有说明产品，只能根据问题词判断；无法判断时先检索最可能产品，并在答案里标明“以下基于某产品资料”。不要混合不同产品的函数签名。

常见容易混淆点：

| 问法 / 关键词 | KingSCADA 应查 | 组态王应查 | 备注 |
|---|---|---|---|
| `SQLSelect` / `SQLConnect` / `SQLInsert` | 只有明确问 KingSCADA 且 KingSCADA skill 命中时才回答 | `kingview-database` | 这些是组态王数据库章节高频函数，不能自动迁移到 KingSCADA |
| `KDBGetDataset` / `KDBExecuteStatement` / `KDBClearDataset` | `kingscada-ch18-database`、`kingscada-dataset`、`kingscada-functions` | 不作为组态王函数回答 | KingSCADA 数据集/数据库访问常用 |
| `DataSets` / `SetDataSetParameter` / `FillBindRecordsetData` | `kingscada-ch15-reports` | 不作为组态王报表函数回答 | KingSCADA 报表可视化数据集能力 |
| `ReportSetCellString` / `ReportGetCellString` 等 `ReportXXX` | 只有 KingSCADA skill 命中时才回答 | `kingview-reports-recipes` | 组态王报表函数容易和 KingSCADA 报表方法混淆 |
| `History` / `Realtime` / `Tag` 表、`@@SamplingMode` | `kingscada-kh` | 不作为普通组态王历史库 SQL 回答 | 这是 KingHistorian KDB-SQL 语义 |

---

## 问题类型路由与回答契约

Agent 需要先判断问题类型，再决定查哪些 Skill。不要把所有问题都当成文档摘要。

| 问题类型 | 常见问法 | 优先 Skill | 回答要求 |
|---|---|---|---|
| 排障 / 失败 / 不生效 | `失败`、`报错`、`不显示`、`不刷新`、`问号`、`不能用` | 对应章节 + `kingscada-appendix-faq` | 先给最可能原因和最短排查路径，再列 1-3 个关键追问 |
| 函数 / 方法用法 | `怎么用`、`参数怎么填`、`有没有这个函数`、`能不能调用` | 产品对应函数 Skill + 对应章节 | 先声明产品范围和是否有该产品证据；不能用其他产品同名函数补答案 |
| 代码 / 脚本例子 | `写一个`、`完整例子`、`可复制`、`脚本` | `kingscada-scripting` + 函数/章节例程 | 先给最小可复制代码，再说明需要替换/预先创建的变量、控件、连接串 |
| 产品差异 / 语法差异 | `区别`、`差别`、`不通用`、`KS和KSP`、`KingView和KingSCADA` | 两边产品对应 Skill | 用表格比较，明确适用产品和不能迁移的能力 |
| 概念解释 | `是什么`、`是啥`、`什么意思`、`解释` | 精确术语所在 Skill | 先给一句话定义，再给适用范围和小例子 |

真实高频问法别名：

| 用户问法 | 应理解为 | 优先 Skill |
|---|---|---|
| `Scada网页发布失败`、`web发布失败`、`网页发布问号` | KingSCADA 传统 Web 发布排障；如果提到现代浏览器/Portal 再分流 | `kingscada-ch22-client-web`、`kingscada-appendix-faq`、必要时 `kingscada-ch30-portal` |
| `RawByTime 和 Calculated 怎么选` | KingHistorian KDB-SQL 采样模式/计算模式选择 | `kingscada-kh` |
| `查询关系库脚本`、`写一个关系库查询` | KingSCADA KDB/DataSet 访问关系数据库 | `kingscada-dataset`、`kingscada-ch18-database`、`kingscada-functions` |
| `按钮颜色能不能代码改` | KingSCADA 控件/动画连接/脚本属性问题 | `kingscada-ch05-controls`、`kingscada-ch10-animation`、`kingscada-functions` |
| `VarRefAddress` | 先查 KingSCADA 函数/脚本；如仅组态王命中必须说明边界 | `kingscada-functions`、必要时 `kingview-scripting` |
| `KS和KSP语法不通用` | KingSCADA C/S 与 Portal/KSP 支持差异 | `kingscada-ch30-portal`、`kingscada-scripting`、`kingscada-functions` |

Web 发布分流规则：

1. 用户只说 `Scada网页发布失败`、`Web发布失败` 时，默认按 **KingSCADA 传统 Web 发布** 排查，优先查 `kingscada-ch22-client-web` 和 `kingscada-appendix-faq`。
2. 如果用户提到 `Portal`、`KSP`、`现代浏览器`、`Chrome/Edge/Firefox`，再查 `kingscada-ch30-portal`。
3. 回答时不要直接把传统 Web 发布和 Portal 混成一个方案；先说明“以下按传统 Web 发布排查”，再提示 Portal 是另一条路线。

---

## 速查表

### KingSCADA

| 场景 | 使用 Skill |
|------|-----------|
| 按 KS 集合原始手册章节查资料 | 优先使用 `kingscada-ch01-welcome` 到 `kingscada-ch30-portal`，以及附录/例程类 `kingscada-*` |
| 写命令语言脚本，不知道语法 / 数据类型 / 控制流 | `kingscada-scripting` |
| 查某个内置函数怎么用（参数、返回值） | `kingscada-functions` |
| 配置趋势曲线、历史趋势、XY曲线图、棒图、报表 | `kingscada-curves-charts` |
| 用 KDB 函数连数据库、查历史采样值、绑定 ListView | `kingscada-dataset` |
| 写 KDB-SQL 查询 KingHistorian 历史库 / 实时库 | `kingscada-kh` |

### KS 集合完整手册

| 手册 | 使用 Skill |
|------|-----------|
| 第1章 欢迎使用SCADA | `kingscada-ch01-welcome` |
| 第2章 工程设计器 | `kingscada-ch02-project-designer` |
| 第3章 画面编辑器 | `kingscada-ch03-display-editor` |
| 第4章 精灵图 | `kingscada-ch04-symbols` |
| 第5章 控件 | `kingscada-ch05-controls` |
| 第6章 用户安全管理系统 | `kingscada-ch06-security` |
| 第7章 运行系统 | `kingscada-ch07-runtime` |
| 第8章 授权配置与使用 | `kingscada-ch08-licensing` |
| 第9章 变量 | `kingscada-ch09-variables` |
| 第10章 动画连接 | `kingscada-ch10-animation` |
| 第11章 脚本 | `kingscada-ch11-scripting` |
| 第12章 报警与事件系统 | `kingscada-ch12-alarm-event` |
| 第13章 模型 | `kingscada-ch13-models` |
| 第14章 曲线与图表 | `kingscada-ch14-curves-charts` |
| 第15章 报表系统 | `kingscada-ch15-reports` |
| 第16章 配方管理 | `kingscada-ch16-recipes` |
| 第17章 其他程序访问SCADA数据的方式 | `kingscada-ch17-data-access` |
| 第18章 数据库访问 | `kingscada-ch18-database` |
| 第19章 网络 | `kingscada-ch19-network` |
| 第20章 冗余系统 | `kingscada-ch20-redundancy` |
| 第21章 资源管理与应用国际化 | `kingscada-ch21-resources-i18n` |
| 第22章 客户端应用打包和web化 | `kingscada-ch22-client-web` |
| 第23章 历史记录 | `kingscada-ch23-history` |
| 第24章 信息窗口 | `kingscada-ch24-info-window` |
| 第25章 附加工具 | `kingscada-ch25-tools` |
| 第26章 应用运行管理器 | `kingscada-ch26-app-run-manager` |
| 第27章 远程部署工具 | `kingscada-ch27-remote-deploy` |
| 第28章 负载均衡工具KingSCADASLB | `kingscada-ch28-slb` |
| 第29章 GIS系统 | `kingscada-ch29-gis` |
| 第30章 KingSCADA-Portal瘦客户端系统 | `kingscada-ch30-portal` |
| 附录二 常见问题解决方法 | `kingscada-appendix-faq` |
| 附录三 版本新增功能 | `kingscada-appendix-new-features` |
| 历史库导出工具使用手册 | `kingscada-history-export` |
| 命令语言函数速查手册 | `kingscada-command-functions` |
| 数据集函数应用例程 | `kingscada-dataset-examples` |
| 数据模型应用例程 | `kingscada-data-model-examples` |
| SCADA Function Manual | `kingscada-function-manual` |
| SCADA User Manual | `kingscada-user-manual` |
| web应用例程 | `kingscada-web-examples` |

### 组态王（Kingview 7.5）

| 场景 | 使用 Skill |
|------|-----------|
| 安装组态王、新建工程、工程管理器/工程浏览器 | `kingview-intro` |
| 定义变量、配置I/O变量、管理数据词典 | `kingview-variables` |
| 绘制图形画面、配置动画连接、使用图库/控件 | `kingview-graphics` |
| 配置趋势曲线、报警定义、报警窗口 | `kingview-curves-alarm` |
| 编写命令语言脚本、配置运行系统 | `kingview-scripting` |
| 配方管理、报表设计、用户权限安全管理 | `kingview-reports-recipes` |
| SQL数据库访问、历史库记录、DDE交换 | `kingview-database` |
| 网络/OPC/冗余/Web发布/移动客户端等高级功能 | `kingview-network-advanced` |

---

## 各 Skill 详情

### kingscada-scripting — 脚本语法
**文件**：`kingscada-scripting/SKILL.md`
命令语言脚本的语法规则：数据类型（int / float / string / bool / 数组）、运算符、if / for / while / switch 控制流、全局事件脚本与局部事件脚本的区别、脚本编辑器使用。

> **注意**：所有变量声明必须集中在脚本最顶部，不能夹在可执行语句之间。

---

### kingscada-functions — 内置函数速查
**文件**：`kingscada-functions/SKILL.md`
全部内置命令语言函数的签名、参数、返回值，涵盖 13 类：

- 画面操作（ShowPicture、ClosePicture …）
- 用户操作（LogOn、LogOff …）
- 字符串（StrLeft、StrMid、StrFormat …）
- 日期时间（GetYear、GetHour、DateTimeToStr …）
- **数学**（LogN、Pow、Int、Abs、Sqrt、Exp …）
- SQL 访问、配方、报警、系统、文件、资源、数据集、其它

---

### kingscada-curves-charts — 曲线与图表
**文件**：`kingscada-curves-charts/SKILL.md`
第 14 章全部曲线与图表组件：趋势曲线、历史趋势、XY 曲线图、棒图、数据报表。包含属性配置、命令语言函数（TrendXXX / XYChart 方法）和常见问题。

**XY 曲线常用方法**：
- `AddNewPoint(x, y, Index)` — 写入数据点
- `ClearAll()` / `Clear(Index)` — 清空
- `SetIndexYAxisRange(YMax, YMin, Y轴序号)` — 设置 Y 轴范围
- `SetXAxisRange(XMax, XMin)` — 设置 X 轴范围

---

### kingscada-dataset — 数据集函数
**文件**：`kingscada-dataset/SKILL.md`
KDB 系列函数连接关系数据库（SQL Server 等）的完整例程：连接字符串、KDBStatusTest / KDBGetDataset / KDBExecuteStatement 用法、查询结果绑定到 ListView / 报表控件。

> 查询 KingHistorian 历史库请优先用 `kingscada-kh`。

---

### kingscada-kh — KingHistorian KDB-SQL
**文件**：`kingscada-kh/SKILL.md`
KingHistorian 工业实时数据库的完整 KDB-SQL 语法参考：

- 三张系统表：`Tag`、`Realtime`、`History`
- 系统变量：`@@SamplingMode`（8 种）、`@@CalculationMode`（21 种）、`@@SamplingInterval`
- 时间算术：`DATEADD(HOUR, n, dt)` 或 `dt + INTERVAL n HOUR`
- 10 个常用查询模板（实时值、历史原始、按小时/天聚合、多变量、质量过滤）
- 已验证语法示例：`kingscada-kh/references/kh-sql-verified-syntax.md`

---

## KingSCADA Skills 依赖关系

```
kingscada-scripting
    └─ 语法基础，其他所有 Skill 的脚本都依赖此处语法规则

kingscada-functions
    └─ 函数签名，写脚本时配合 scripting 使用

kingscada-curves-charts
    └─ 图表方法调用，需要 functions + scripting

kingscada-dataset
    └─ KDB 函数例程，深度查询参考 kingscada-kh

kingscada-kh
    └─ 历史库 SQL，结果处理参考 kingscada-dataset
```

---

## 组态王（Kingview 7.5）Skills 详情

### kingview-intro — 安装入门与工程管理
**文件**：`kingview-intro/SKILL.md`
第1-4章：安装/卸载、授权、工程管理器（新建/备份/恢复工程）、工程浏览器（开发环境结构）。

---

### kingview-variables — 变量定义与管理
**文件**：`kingview-variables/SKILL.md`
第5章：内存变量、I/O变量、系统变量的定义步骤，变量属性（类型、报警、记录），数据词典的导入导出。

---

### kingview-graphics — 图形画面与动画
**文件**：`kingview-graphics/SKILL.md`
第7章+第13章+第14章：绘制图形、配置全部动画连接类型（填充/位置/大小/值显示/按钮/滑杆），图库向导，ActiveX控件嵌入。

---

### kingview-curves-alarm — 趋势曲线与报警
**文件**：`kingview-curves-alarm/SKILL.md`
第8章+第9章：实时/历史趋势曲线配置、笔属性，报警定义（越限/偏差/变化率），报警窗口，报警日志。

---

### kingview-scripting — 命令语言与运行系统
**文件**：`kingview-scripting/SKILL.md`
第10-12章：五种命令语言类型（应用程序/数据改变/事件/画面/按键），脚本语法，TouchVew运行系统配置，信息窗口调试。

---

### kingview-reports-recipes — 配方报表与安全管理
**文件**：`kingview-reports-recipes/SKILL.md`
第15-17章：配方定义与下载/上传，报表控件设计与打印（ReportXXX函数），用户权限与操作安全管理。

---

### kingview-database — 数据库访问与历史库
**文件**：`kingview-database/SKILL.md`
第18章+第19章+第24章：DDE动态数据交换，ODBC连接外部数据库（SQLConnect/SQLSelect/SQLInsert等函数），历史库配置与查询。

---

### kingview-network-advanced — 网络与高级功能
**文件**：`kingview-network-advanced/SKILL.md`
第20-35章：OPC客户端、C/S网络架构、冗余系统、Web发布、移动客户端、XML导入导出、电子签名、批次管理、多语言、HTTP Server、WebService等。
