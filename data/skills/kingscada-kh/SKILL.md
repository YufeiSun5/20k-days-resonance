---
name: kingscada-kh
description: "KingHistorian（亚控工业实时数据库 / KH）KDB-SQL 查询语法参考手册。使用场景：查询 KingHistorian 历史库、实时库；编写 KDB-SQL 语句操作 Tag/Realtime/History 表；配置采样模式 (SamplingMode)、计算模式 (CalculationMode)、采样间隔 (SamplingInterval) 等系统变量；按天/小时统计聚合数据；过滤数据质量；解决 GROUP BY 函数表达式限制问题。触发词：KingHistorian、KH、KDB、KDB-SQL、历史数据库、工业实时数据库、Tag表、Realtime表、History表、SamplingMode、CalculationMode、SamplingInterval、历史数据查询、实时数据查询、按天取均值、数据质量戳。"
---

# KingHistorian KDB-SQL 查询参考手册

本 Skill 提供北京亚控 **KingHistorian（KH）工业实时数据库**的完整 KDB-SQL 语法参考和已验证查询示例。

> KDB-SQL 参照 SQL-92 标准，关键字**不区分大小写**，仅用于数据**检索**，不支持 UPDATE/DELETE 等写操作。

---

## 系统表概览

| 表名 | 用途 |
|------|------|
| `Tag` | 变量目录：TagName、TagId、DataType、Description、CollectorName |
| `Realtime` | 实时值：TagName、DataTime、DataValue、DataQuality |
| `History` | 历史数据（受系统变量控制采样行为） |
| `Collector` | 采集器信息 |

---

## 关键系统变量（`SET @@变量名 = 值;`）

| 变量 | 说明 |
|------|------|
| `@@SamplingMode` | 采样模式（见下表），默认 `'RawByTime'` |
| `@@SamplingInterval` | 采样间隔，单位**毫秒**（仅 Interpolated/Calculated/Stepped/Trend 有效） |
| `@@SamplingNumber` | 采样点数（仅 `RawByNumber` 有效），默认 1000 |
| `@@CalculationMode` | 计算模式（仅 `Calculated` 有效） |
| `@@DataVersion` | 数据版本 0-32767（仅 RawByTime/RawByNumber 有效） |

### SamplingMode 取值

| 值 | 含义 |
|----|------|
| `'RawByTime'` | 按时间间隔取原始数据（**默认**） |
| `'RawByNumber'` | 按点数取原始数据 |
| `'Interpolated'` | 线性插值 |
| `'Calculated'` | 按计算模式聚合 |
| `'Stepped'` | 步进插值 |
| `'Trend'` | 趋势曲线模式 |
| `'CurrentValue'` | 当前值 |

### CalculationMode 常用取值

| 值 | 含义 |
|----|------|
| `'RawAverage'` | 算术平均值 |
| `'Average'` | 时间加权平均值 |
| `'RawTotal'` | 原始值求和 |
| `'Total'` | 时间加权求和 |
| `'Minimum'` / `'Maximum'` | 最小/最大值 |
| `'Count'` | 原始值计数 |
| `'Start'` / `'End'` | 区间第一/最后一个原始数据 |
| `'Delta'` | 第一个好数据与最后一个好数据之差 |
| `'PercentGood'` / `'PercentBad'` | 好/坏数据时间百分比 |

---

## 常见查询模板

### 1. 查询变量目录
```sql
SELECT TOP 50 TagName, TagId, DataType, Description, CollectorName
FROM Tag
ORDER BY TagName;
```

### 2. 模糊搜索变量名
```sql
SELECT TagName, TagId, Description
FROM Tag
WHERE TagName LIKE '%关键词%'
ORDER BY TagName;
```

### 3. 查询实时值
```sql
SELECT TOP 20 TagName, DataTime, DataValue, DataQuality
FROM Realtime
ORDER BY DataTime DESC;
```

### 4. 查询历史原始数据（时间范围）
```sql
SET @@SamplingMode = 'RawByTime';
SET @@SamplingInterval = 60000;  -- 60 秒间隔（毫秒）
SELECT TOP 100 TagName, DataTime, DataValue, DataQuality
FROM History
WHERE TagName = '变量名'
  AND DataTime BETWEEN '2026-01-01 00:00:00' AND '2026-01-31 23:59:59'
ORDER BY DataTime;
```

### 5. 只取好质量数据（DataQuality = 192）
```sql
SET @@SamplingMode = 'RawByTime';
SET @@SamplingInterval = 60000;
SELECT TOP 100 TagName, DataTime, DataValue, DataQuality
FROM History
WHERE TagName = '变量名'
  AND DataTime BETWEEN '2026-01-01 00:00:00' AND '2026-01-31 23:59:59'
  AND DataQuality = 192
ORDER BY DataTime;
```

### 6. 计算某变量的均值/最大/最小（直接聚合）
```sql
SELECT
    AVG(CAST(DataValue AS float)) AS AvgValue,
    MAX(CAST(DataValue AS float)) AS MaxValue,
    MIN(CAST(DataValue AS float)) AS MinValue
FROM History
WHERE TagName = '变量名'
  AND DataTime BETWEEN '2026-01-01 00:00:00' AND '2026-01-31 23:59:59';
```

### 7. 按天取算术平均值（Calculated 模式）
```sql
SET @@SamplingMode = 'Calculated';
SET @@CalculationMode = 'RawAverage';
SET @@SamplingInterval = 86400000;  -- 1天 = 24*3600*1000 毫秒
SELECT TagName, DataTime, DataValue
FROM History
WHERE TagName = '变量名'
  AND DataTime BETWEEN '2026-01-01 00:00:00' AND '2026-01-31 23:59:59'
ORDER BY DataTime;
```

### 8. 多变量按天取平均值
```sql
SET @@SamplingMode = 'Calculated';
SET @@CalculationMode = 'RawAverage';
SET @@SamplingInterval = 86400000;
SELECT TagName, DataTime, DataValue
FROM History
WHERE TagName IN ('变量1', '变量2', '变量3')
  AND DataTime BETWEEN '2026-01-01 00:00:00' AND '2026-01-31 23:59:59'
ORDER BY TagName, DataTime;
```

### 9. 按小时分组统计（GROUP BY 子查询展开法）

> **重要**：KDB-SQL 的 `GROUP BY` **不支持**直接写函数表达式（如 `YEAR(DataTime)`），必须先在子查询中展开为普通列。

```sql
SELECT
    TagName,
    YearValue,
    MonthValue,
    DayValue,
    HourValue,
    AVG(CAST(DataValue AS float)) AS HourAvgValue
FROM (
    SELECT
        TagName,
        DataValue,
        YEAR(DataTime)  AS YearValue,
        MONTH(DataTime) AS MonthValue,
        DAY(DataTime)   AS DayValue,
        HOUR(DataTime)  AS HourValue
    FROM History
    WHERE TagName = '变量名'
      AND DataTime BETWEEN '2026-01-01 00:00:00' AND '2026-01-02 00:00:00'
) AS T
GROUP BY TagName, YearValue, MonthValue, DayValue, HourValue
ORDER BY DayValue, HourValue;
```

### 10. 多语句批处理（每句末尾加分号）
```sql
SET @@SamplingMode = 'RawByTime';
SET @@SamplingInterval = 60000;
SELECT TOP 5 TagName, DataTime, DataValue
FROM History
WHERE TagName = '变量名';
```

---

## 重要注意事项

1. **多语句批量执行**：每条语句末尾都必须加英文分号 `;`
2. **History 表聚合**：DataValue 建议先 `CAST(DataValue AS float)` 再做 AVG/MAX/MIN
3. **GROUP BY 限制**：不支持直接写函数表达式，需先用子查询展开为普通列
4. **SamplingMode 生效范围**：SET 系统变量只对紧跟的 SELECT 有效（同一批处理内）
5. **DataQuality = 192** 表示好质量数据
6. **时间格式**：使用单引号括起，格式 `'YYYY-MM-DD HH:mm:ss'`

---

## 参考文档

详细 KDB-SQL 语法、数据类型、子查询、联接、事务等完整说明：

| 文档 | 内容 |
|------|------|
| [kh-ch07-sql-syntax.md](./references/kh-ch07-sql-syntax.md) | KDB-SQL 完整语法参考（SELECT/WHERE/GROUP BY/子查询/联接/事务/系统变量） |
| [kh-sql-verified-syntax.md](./references/kh-sql-verified-syntax.md) | 已验证可复用的 11 条标准查询模板 |
| [kh-ch01-welcome.md](./references/kh-ch01-welcome.md) | KingHistorian 系统组件概览 |
| [kh-ch02-getting-started.md](./references/kh-ch02-getting-started.md) | 采集器配置、用户登录、SQL 查询工具使用 |

## 相关 Skill

- `kingscada-dataset`：在 KingSCADA 命令语言脚本中通过 KDB 函数调用 KingHistorian 查询
- `kingscada-functions`：KingScada 内置函数，含 KDB 数据集函数的完整签名列表

---

## 实战经验（开发踩坑）

### 1. KH 原生查询 vs KS 类 SQL

虽然 KingSCADA 提供了类 SQL 语句来访问 KH，但**效率明显低于 KH 原生查询**（通过 `KDBKHSampleData2` 等 KDB 函数直接访问）。生产项目中**优先用 KH 原生函数**，能按毫秒级采样间隔取等间隔点（例如按小时/按天聚合）。

### 2. 24:00 整点会丢失时分秒，需手动补齐

KH 原生查询在遇到 `xxxx-xx-xx 24:00:00` 这种整点时，返回的时间字符串会**自动省略时分秒**（变成只剩日期 `yyyy-MM-dd`）。如果下游脚本/控件需要完整时间戳，必须自己补：

```c
string time = StrReplace(KDBGetCellValueByFieldName("KH_Dataset", j, "DataTime"), "/", "-", 0, 0, 0);
if (StrLen(time) <= 10) {
    time = time + " 00:00:00";
}
```

### 3. 项目中常用的连接字符串格式

```
DSN=SQL_KH; ServerAddress=192.200.200.230; ServerPort=5678; UID=sa; pwd=sa; NetworkTimeout=0
```

注意 `NetworkTimeout=0` 表示永不超时，长时间大批量历史查询时建议保留。
