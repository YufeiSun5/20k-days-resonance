---
name: kingscada-dataset
description: "KingScada（北京亚控）数据集函数应用例程。使用场景：在 KingSCADA 中连接 SQL Server 等关系数据库查询大批量数据；使用 KDB 系列函数（KDBStatusTest、KDBGetDataset、KDBExecuteStatement 等）操作数据集；将查询结果绑定到 ListView、报表等控件；查询变量历史记录、原始数据、采样值。触发词：KingScada数据集、KDB函数、KDBGetDataset、KDBStatusTest、数据库连接字符串、查询数据库、数据集函数、ListView数据绑定、变量历史查询、采样值查询。"
---

# KingScada 数据集函数应用例程

本 Skill 提供北京亚控 KingScada 中**数据集函数的完整实战例程**，重点说明如何通过 KDB 系列函数连接关系数据库（以 SQL Server 2005 为例）并查询数据，同样适用于其他关系库。

> **注意**：数据集函数和 SQL 访问函数在**客户端工程中不支持**。

## 适用场景

- 在 KingSCADA 脚本中**连接数据库**并执行 SQL 查询
- 使用 `KDBGetDataset` / `KDBExecuteStatement` / `KDBSQLExecuteFromFile` 获取数据集
- 将数据集内容写入 **ListView 控件、报表**等画面元素
- 查询变量的**原始数据、特定时刻值、采样值**
- 配置**连接字符串**（Provider / User ID / Data Source）

## 例程章节

完整带代码的例程位于 [./references/dataset-function-examples.md](./references/dataset-function-examples.md)：

| 章节 | 内容 |
|------|------|
| 应用背景 | 数据集方案 vs 记录体方案的适用场景 |
| 创建连接字符串 | SQL Server 连接字符串格式与配置 |
| 查询数据库 | 完整脚本：判断连接→获取数据集→读取单元格内容 |
| 查询数据库某字段内容 | 按列名提取字段值 |
| 查询变量原始数据 | 历史数据查询脚本 |
| 查询变量特定时刻的原始数据 | 按时间戳精确查询 |
| 查询变量采样值 | 采样值批量读取 |

## 核心函数（简要）

| 函数 | 说明 |
|------|------|
| `KDBStatusTest(ConnectStr)` | 测试数据库连接是否成功，返回 bool |
| `KDBGetDataset(DataSet, ConnectStr, SqlStr)` | 执行 SQL 并将结果存入数据集变量 |
| `KDBExecuteStatement(ConnectStr, SqlStr)` | 执行 SQL 语句（不返回数据集） |
| `KDBSQLExecuteFromFile(DataSet, ConnectStr, FilePath)` | 从文件读取 SQL 并执行 |

> 26 个数据集函数的完整签名和参数说明请参考 `kingscada-functions` Skill（十一、数据集函数章节）。

## 使用方法

1. **加载例程文档**：读取 `./references/dataset-function-examples.md`
2. **定位对应章节**：按用户描述的场景匹配章节
3. **提取完整脚本**：例程中包含可直接复用的命令语言代码，结合用户实际变量名/连接字符串调整后输出

## 相关 Skill

- `kingscada-kh`：KingHistorian KDB-SQL 语法完整参考，含系统变量（SamplingMode/CalculationMode）和已验证查询模板
- `kingscada-functions`：KingScada 内置函数手册，含 KDB 数据集函数完整签名

---

## 实战经验：`KDBKHSampleData2` 完整参数清单

这个函数是项目里直接走 KH 原生采样查询的主力，参数较多容易记混，整理如下：

```c
bool result = KDBKHSampleData2(
    "KH_Dataset",          // DatasetName：数据集名称
    ConnectStr,            // 工业库连接字符串（DSN/ServerAddress/Port/UID/pwd/NetworkTimeout）
    1,                     // iTagNamesType：1=变量名称组，2=csv文件路径
    strTagNames,           // 变量名（多个用英文逗号分隔）或 csv 文件路径
    strStartTime,          // 起始时间，格式 "YYYY-MM-DD HH:mm:ss"
    strEndTime,            // 终止时间，同上
    0,                     // iSamplingMode：0=最近点采样, 1=线性插值采样
    iSamplingInterval,     // 采样间隔（毫秒）
    1,                     // iTimeStampFormat：见下表
    "",                    // strRowStatistic：列统计 "min,max,averg" 任意排列，可空
    "",                    // strColStatistic：行统计同上
    ""                     // strTagStatisticRange：变量统计范围，例 "1,3,5-9,13"，空=全部
);
```

### iTimeStampFormat 取值表

| 值 | 格式 | 值 | 格式 |
|----|------|----|------|
| 0  | YYYY-MM-DD hh:mm:ss.xxx | 9  | hh:mm |
| 1  | YYYY-MM-DD hh:mm:ss     | 10 | mm:ss |
| 2  | YYYY-MM-DD hh:mm        | 11 | ss.xxx |
| 3  | YYYY-MM-DD hh           | 12 | YYYY |
| 4  | YYYY-MM-DD              | 13 | MM |
| 5  | YYYY-MM                 | 14 | DD |
| 6  | MM-DD                   | 15 | hh |
| 7  | hh:mm:ss.xxx            | 16 | mm |
| 8  | hh:mm:ss                | 17 | ss |
|    |                         | 18 | xxx |

### 常用查询周期

| 周期 | iSamplingInterval（毫秒） |
|------|---------------------------|
| 1 小时 | `3600 * 1000` |
| 1 天 | `24 * 3600 * 1000` |
| 1 月（按 30 天估算） | `30 * 24 * 3600 * 1000` |

### 把数据集绑定到报表

```c
Report1.SetDataset2("KH_Dataset", 3, 1);  // 数据集名 / 起始行 / 起始列
```

### 注意事项

- 调用前先用 `KDBStatusTest(ConnectStr)` 确认连接
- 查询返回行数用 `KDBGetDatasetRows("KH_Dataset")` 取，遍历前先判 `> 0`
- 取单元格值用 `KDBGetCellValueByFieldName(数据集名, 行号, 列名)`，列名就是变量名 / `DataTime`
- 整点 24:00 时间戳会被省略时分秒，需手动补齐（详见 `kingscada-kh` 的实战经验）
