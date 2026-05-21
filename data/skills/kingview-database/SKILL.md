---
name: kingview-database
description: "组态王（北京亚控）数据库访问与历史库参考手册。使用场景：在组态王中通过SQL访问外部关系数据库（ODBC）；使用组态王历史库记录和查询历史数据；配置变量的历史记录参数；使用DDE与其他应用程序交换数据；查询SQL访问函数（SQLConnect、SQLSelect、SQLInsert等）。触发词：组态王SQL、数据库访问、ODBC、历史库、历史数据、SQLConnect、SQLSelect、SQLInsert、SQLUpdate、DDE、动态数据交换、历史记录、数据查询。"
argument-hint: "具体问题，例如：如何连接SQL Server数据库、历史库怎么配置记录间隔、SQLSelect函数参数说明"
---

# 组态王：数据库访问与历史库

本 Skill 提供组态王 7.5 **SQL数据库访问、历史库和DDE**的完整参考，涵盖第十八章、第十九章、第二十四章。

## 适用场景

- 使用 **DDE**（动态数据交换）与 Excel 等应用程序实时交换数据
- 通过 **ODBC** 访问外部关系数据库（SQL Server、Access 等）：
  - `SQLConnect` / `SQLDisconnect` — 连接/断开数据库
  - `SQLSelect` — 查询记录
  - `SQLInsert` — 插入记录
  - `SQLUpdate` — 更新记录
  - `SQLDelete` — 删除记录
  - `SQLNext` / `SQLFirst` — 遍历结果集
- 配置组态王**历史库**：变量历史记录属性、存储路径、存储间隔
- 查询历史数据（历史趋势和历史报表）

## 内容结构

完整文档位于 [./references/kingview-database-history.md](./references/kingview-database-history.md)：

| 章节 | 内容 |
|------|------|
| 第十八章 | DDE：组态王作为 DDE 客户端/服务器与其他程序交换数据 |
| 第十九章 | 数据库访问（SQL）：ODBC 配置、全部 SQL 访问函数详解 |
| 第二十四章 | 历史库：历史记录配置、存储管理、数据查询接口 |

## 使用方法

读取 `./references/kingview-database-history.md`，按章节或函数名定位。

## 实战坑点与案例补充

### 历史报警查询优先路径

- 当需求是“按时间范围查询历史报警记录”时，优先考虑 `KVADODBGrid + Alarm 表`，而不是试图用报警窗口代替数据库查询界面。
- `SQLSelect()` 适合手工做记录体绑定和结果遍历；如果目的是在画面上直接展示查询结果，`KVADODBGrid` 更省配置、更接近最终界面。

### Alarm 表查询的关键格式

- `Alarm` 是报警事件表，关键时间字段为 `AlarmTime`。
- `KingView 7.5` 中 `AlarmTime` 存的是 `UTC` 时间，按时间区间查时必须先对本地时间调用 `ConvertLocalTimeToUTC()`。
- `ConvertLocalTimeToUTC()` 的入参规定格式为 `YYYY/MM/DD HH:MM:SS`，使用横线格式可能导致查询失败。
- `KVADODBGrid.Where` 中的时间条件应写为：`AlarmTime >=#起始UTC# And AlarmTime <#结束UTC#`。
- 不要把时间写成 `AlarmTime >= '2026/5/1 00:00:00'` 这种单引号格式；该场景下应使用 `#` 包围时间字面量。
- 不要对 `AlarmTime` 使用 `like` 模糊匹配；官方说明当前仅明确支持“全部查询”和“按时间区间查询”。

### KVADODBGrid 常见误区

- 设置新查询条件后，应调用 `FetchData()` 执行查询。
- `RefreshData()` 只用于按上次条件重新刷新。
- `Refresh()` 不是 `KVADODBGrid` 的有效方法，调用会报“变量/方法未定义”。
- 脚本执行时，含控件的画面必须已经打开运行，否则会出现控件属性或方法调用失败。

### 故障排查模板

1. 先验证空条件是否正常：

```c
GridAlarm.Where = "";
GridAlarm.FetchData();
```

2. 若空条件成功，先写死时间区间做最小验证：

```c
string str1;
string str2;
string whe1;

str1 = ConvertLocalTimeToUTC("2026/5/1 00:00:00");
str2 = ConvertLocalTimeToUTC("2026/5/11 00:00:00");

whe1 = "AlarmTime >=#" + str1 + "# And AlarmTime <#" + str2 + "#";

GridAlarm.Where = whe1;
GridAlarm.FetchData();
```

3. 若写死时间成功，再替换为日期选择器/时间选择器输出。
4. 若失败，优先核对：
  - 控件名是否真实存在
  - 时间字符串是否使用 `/`
  - `Where` 中是否使用 `#`
  - 是否已经做 `ConvertLocalTimeToUTC()`

### 本次案例结论

- 需求：在现有画面新增历史报警时间范围查询功能。
- 误区：先尝试用报警窗口直接做历史查询，随后误用 `GridAlarm.Refresh()`。
- 正解：改为 `KVADODBGrid` 查询 `Alarm` 表，时间由选择器读取，拼接为 `YYYY/MM/DD HH:MM:SS`，转 `UTC` 后以 `#时间#` 形式写入 `Where`，最终通过 `FetchData()` 成功返回结果。

## 相关 Skill

| Skill | 内容 |
|---|---|
| `kingview-scripting` | 在命令语言中调用 SQL 函数 |
| `kingview-variables` | 变量的历史记录属性配置 |
| `kingview-curves-alarm` | 历史趋势曲线查询历史数据 |
