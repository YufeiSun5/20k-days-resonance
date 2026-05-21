-- KingHistorian / KDB-SQL 已验证可复用查询
-- 说明：
-- 1. 多条语句批量执行时，每条语句末尾都要加英文分号 ;
-- 2. History 表做聚合计算时，DataValue 建议先 CAST 为 float
-- 3. GROUP BY 不支持直接写函数表达式，需先用子查询展开为普通列


-- 1. 查询变量目录，确认 TagName 是否存在
SELECT TOP 50 TagName, TagId, DataType, Description, CollectorName
FROM Tag
ORDER BY TagName;


-- 2. 模糊搜索变量名
SELECT TagName, TagId, Description
FROM Tag
WHERE TagName LIKE '%PAC%'
ORDER BY TagName;


-- 3. 查询实时表 Realtime
SELECT TOP 20 TagName, DataTime, DataValue, DataQuality, DataVersion
FROM Realtime
ORDER BY DataTime DESC;


-- 4. 查询某个变量的实时值
SELECT TOP 20 TagName, DataTime, DataValue, DataQuality
FROM Realtime
WHERE TagName = 'PAC加药机_1#沉淀池1#流量'
ORDER BY DataTime DESC;


-- 5. 查询某个变量的历史原始值
SET @@SamplingMode = 'RawByTime';
SET @@SamplingInterval = 60000;
SELECT TOP 20 TagName, DataTime, DataValue, DataQuality
FROM History
WHERE TagName = 'PAC加药机_1#沉淀池1#流量'
  AND DataTime BETWEEN '2026-03-01 00:00:00' AND '2026-03-11 23:59:59'
ORDER BY DataTime;


-- 6. 查询某个变量的历史原始值，只看好质量数据
SET @@SamplingMode = 'RawByTime';
SET @@SamplingInterval = 60000;
SELECT TOP 50 TagName, DataTime, DataValue, DataQuality
FROM History
WHERE TagName = 'PAC加药机_1#沉淀池1#流量'
  AND DataTime BETWEEN '2026-03-01 00:00:00' AND '2026-03-11 23:59:59'
  AND DataQuality = 192
ORDER BY DataTime;


-- 7. 计算某个变量在时间范围内的平均值、最大值、最小值
SELECT
    AVG(CAST(DataValue AS float)) AS AvgValue,
    MAX(CAST(DataValue AS float)) AS MaxValue,
    MIN(CAST(DataValue AS float)) AS MinValue
FROM History
WHERE TagName = 'PAC加药机_1#沉淀池1#流量'
  AND DataTime BETWEEN '2026-03-01 00:00:00' AND '2026-03-11 23:59:59';


-- 8. 按天取平均值（计算模式）
SET @@SamplingMode = 'Calculated';
SET @@CalculationMode = 'RawAverage';
SET @@SamplingInterval = 86400000;
SELECT TagName, DataTime, DataValue
FROM History
WHERE TagName = 'PAC加药机_1#沉淀池1#流量'
  AND DataTime BETWEEN '2026-03-01 00:00:00' AND '2026-03-11 23:59:59'
ORDER BY DataTime;


-- 9. 多变量按天取平均值
SET @@SamplingMode = 'Calculated';
SET @@CalculationMode = 'RawAverage';
SET @@SamplingInterval = 86400000;
SELECT TagName, DataTime, DataValue
FROM History
WHERE TagName IN (
    'PAC加药机_1#沉淀池1#流量',
    'PAC加药机_1#沉淀池2#流量'
)
  AND DataTime BETWEEN '2026-03-01 00:00:00' AND '2026-03-11 23:59:59'
ORDER BY TagName, DataTime;


-- 10. 按小时分组计算平均值
-- 说明：GROUP BY 不能直接写 YEAR(DataTime) 这类函数，需先在子查询中展开
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
        YEAR(DataTime) AS YearValue,
        MONTH(DataTime) AS MonthValue,
        DAY(DataTime) AS DayValue,
        HOUR(DataTime) AS HourValue
    FROM History
    WHERE TagName = 'PAC加药机_1#沉淀池1#流量'
      AND DataTime BETWEEN '2026-03-10 00:00:00' AND '2026-03-11 00:00:00'
) AS T
GROUP BY TagName, YearValue, MonthValue, DayValue, HourValue
ORDER BY DayValue, HourValue;


-- 11. 仅测试执行器是否支持多语句批处理
SET @@SamplingMode = 'RawByTime';
SET @@SamplingInterval = 60000;
SELECT TOP 5 TagName, DataTime, DataValue
FROM History
WHERE TagName = 'PAC加药机_1#沉淀池1#流量';