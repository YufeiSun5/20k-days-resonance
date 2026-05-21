---
name: kingview-curves-alarm
description: "组态王（北京亚控）趋势曲线与报警事件参考手册。使用场景：配置实时趋势曲线和历史趋势曲线；设置变量的报警定义（越限、偏差、变化率报警）；配置报警窗口显示报警列表；查询报警和事件日志；使用趋势曲线控件的属性和方法。触发词：组态王趋势曲线、历史趋势、实时趋势、报警窗口、报警定义、越限报警、报警确认、事件系统、报警日志、曲线控件、趋势控件。"
argument-hint: "具体问题，例如：如何添加历史趋势曲线、报警定义的越限参数怎么设置、报警窗口如何显示"
---

# 组态王：趋势曲线与报警事件

本 Skill 提供组态王 7.5 **趋势曲线和报警事件系统**的完整参考，涵盖第八章（曲线）和第九章（报警）。

## 适用场景

- 配置**实时趋势曲线**和**历史趋势曲线**控件
- 设置曲线的笔（变量绑定、颜色、量程）
- 配置变量的**报警定义**：
  - 模拟量：高高限、高限、低限、低低限、变化率
  - 离散量：状态变化报警
  - 偏差报警
- 使用**报警窗口**控件显示和确认报警
- 报警和事件的**日志记录**与查询
- 报警分组与优先级设置

## 内容结构

完整文档位于 [./references/kingview-curves-alarm.md](./references/kingview-curves-alarm.md)：

| 章节 | 内容 |
|------|------|
| 第八章 | 趋势曲线：实时趋势、历史趋势、XY曲线、棒图配置与属性 |
| 第九章 | 报警和事件系统：报警定义、报警窗口、报警记录、事件查询 |

## 使用方法

读取 `./references/kingview-curves-alarm.md`，按章节或关键词定位。

## 实战坑点与结论

- `KVHTrend` 历史曲线单个控件最多支持 `16` 条曲线。现场如果某一类物理量超过 `16` 条，不要硬塞到一个控件里，直接拆成两个历史曲线控件最稳。例如温度点位 `18` 条时，可按“一次侧 10 条 + 二次侧 8 条”拆分。
- 历史曲线按固定起止时间查询，优先用 `SetTimeParamString()` 或 `SetTimeDataumBound()`；若继续使用 `SetTimeParam()`，要特别注意 `lXWidth` 的单位和版本差异。文档已注明 `6.60` 下 `sXWidthUnit` 实际必须传 `0`，时间轴长度改传秒数。
- 组态王本身没有稳定通用的“内置日期时间选择器”工作流。历史曲线查询界面实际可用的两种方案是：
  - 使用 `Microsoft Date and Time Picker Control` 分别放开始日期、开始时间、结束日期、结束时间四个控件；
  - 或直接用字符串/数值输入框让操作员输入年月日时分。
- `DTPicker` 更适合做查询时间输入，但其 `Value` 不是普通数值变量，不建议期待“直接绑定一个字符串变量就够了”；在按钮脚本里直接读取 `.Year/.Month/.Day/.Hour/.Minute/.Second` 最稳。
- `CkvrealTimeCurves` 实时曲线出现“用户校验失败”时，优先排查服务端 IP、运行环境和端口，而不是先怀疑变量名：
  - 服务端 IP 不要填 `127.0.0.1` 或留空，应填本机实际网卡 IP；
  - `TouchVew` 需要已经运行；
  - 防火墙需放行相关端口（现场案例为 `2001`）；
  - 实时曲线通常还要手工点一次“开始”；
  - Y 轴最大值/最小值往往需要手动设置，否则曲线可能看起来“没数据”。
- 报警窗口适合运行期显示与筛选，不适合作为任意时间范围的历史报警查询器。实际项目中，用户常以为“历史报警窗”能在系统重启后继续按时间范围追溯所有记录；更稳妥的做法是直接查询报警库表 `Alarm`。
- 历史报警按时间区间查询，优先使用 `KVADODBGrid` 控件，而不是依赖报警窗口的运行期缓冲显示。
- `KVADODBGrid` 不存在 `Refresh()` 方法。修改查询条件后应调用 `FetchData()`；按上次条件重新刷新才用 `RefreshData()`。
- `KingView 7.5` 的报警库存储时间字段为 `AlarmTime`，且保存的是 `UTC` 时间，不再沿用旧版本分离的日期字段和时间字段。
- 使用 `ConvertLocalTimeToUTC()` 时，入参时间字符串必须写成 `YYYY/MM/DD HH:MM:SS` 或仅日期，不能写成 `YYYY-MM-DD HH:MM:SS`。
- `AlarmTime` 的时间条件必须写成 `AlarmTime >=#...# And AlarmTime <#...#` 这一类格式，时间字面量要用 `#` 包围，不能用单引号。
- 当前官方示例只明确支持“全部查询”和“按时间区间查询”两种形式；涉及 `AlarmTime` 时不要写 `like` 模糊匹配。
- 在命令语言中调用控件属性和方法时，含该控件的画面必须处于运行状态，否则会出现属性/方法调用失败。
- 组态王命令语言里变量声明宜一行一个，避免在项目中因为脚本编辑器兼容性或书写习惯导致解析失败。

## 现场案例：历史报警时间区间查询

### 适用场景

- 需求：在画面上新增“开始日期 + 开始时间 + 结束日期 + 结束时间 + 查询按钮”，查询历史报警。
- 已知条件：
  - 日期选择器：`Ctrl0071`、`Ctrl0069`
  - 时间选择器：`Ctrl0070`、`Ctrl0068`
  - 历史报警表格：`GridAlarm`
  - 数据表：`Alarm`

### 最终可用脚本

```c
long iSY;
long iSM;
long iSD;
long iSH;
long iSMin;
long iSSec;

long iEY;
long iEM;
long iED;
long iEH;
long iEMin;
long iESec;

string sSM;
string sSD;
string sSH;
string sSMin;
string sSSec;

string sEM;
string sED;
string sEH;
string sEMin;
string sESec;

string strLocalStart;
string strLocalEnd;
string strUTCStart;
string strUTCEnd;
string whe1;

iSY = Ctrl0071.Year;
iSM = Ctrl0071.Month;
iSD = Ctrl0071.Day;
iSH = Ctrl0070.Hour;
iSMin = Ctrl0070.Minute;
iSSec = Ctrl0070.Second;

iEY = Ctrl0069.Year;
iEM = Ctrl0069.Month;
iED = Ctrl0069.Day;
iEH = Ctrl0068.Hour;
iEMin = Ctrl0068.Minute;
iESec = Ctrl0068.Second;

IF (iSM < 10) sSM = "0" + StrFromInt(iSM,10); ELSE sSM = StrFromInt(iSM,10);
IF (iSD < 10) sSD = "0" + StrFromInt(iSD,10); ELSE sSD = StrFromInt(iSD,10);
IF (iSH < 10) sSH = "0" + StrFromInt(iSH,10); ELSE sSH = StrFromInt(iSH,10);
IF (iSMin < 10) sSMin = "0" + StrFromInt(iSMin,10); ELSE sSMin = StrFromInt(iSMin,10);
IF (iSSec < 10) sSSec = "0" + StrFromInt(iSSec,10); ELSE sSSec = StrFromInt(iSSec,10);

IF (iEM < 10) sEM = "0" + StrFromInt(iEM,10); ELSE sEM = StrFromInt(iEM,10);
IF (iED < 10) sED = "0" + StrFromInt(iED,10); ELSE sED = StrFromInt(iED,10);
IF (iEH < 10) sEH = "0" + StrFromInt(iEH,10); ELSE sEH = StrFromInt(iEH,10);
IF (iEMin < 10) sEMin = "0" + StrFromInt(iEMin,10); ELSE sEMin = StrFromInt(iEMin,10);
IF (iESec < 10) sESec = "0" + StrFromInt(iESec,10); ELSE sESec = StrFromInt(iESec,10);

strLocalStart = StrFromInt(iSY,10) + "/" + sSM + "/" + sSD + " " + sSH + ":" + sSMin + ":" + sSSec;
strLocalEnd = StrFromInt(iEY,10) + "/" + sEM + "/" + sED + " " + sEH + ":" + sEMin + ":" + sESec;

strUTCStart = ConvertLocalTimeToUTC(strLocalStart);
strUTCEnd = ConvertLocalTimeToUTC(strLocalEnd);

whe1 = "AlarmTime >=#" + strUTCStart + "# And AlarmTime <=#" + strUTCEnd + "#";

GridAlarm.Where = whe1;
GridAlarm.FetchData();
```

### 排查顺序

1. 先用 `GridAlarm.Where = ""; GridAlarm.FetchData();` 验证控件连接是否正常。
2. 若空条件成功、带时间失败，优先检查时间格式、`#` 包围符和 `UTC` 转换。
3. 若报“调用方法 FetchData 失败”，先排除 `Where` 语法错误，再检查控件名是否与画面一致。
4. 若报“变量 xxx 未定义”，先排查是否误用了不存在的方法，如 `GridAlarm.Refresh()`。

### 推荐显示列

- `AlarmTime`：报警时间
- `TagComment`：变量描述
- `AlarmText`：报警文本
- `AlarmType`：报警类型
- `AlarmValue`：报警值
- `LimitValue`：限值
- `Pri`：优先级
- `EventType`：事件类型
- `OperatorName`：操作员

## 现场案例：历史曲线画面打开时初始化

### 适用场景

画面打开时需要：① 先隐藏多余曲线只显示默认 1 条，② 自动把时间轴设为最近 N 小时，不需要操作员手动操作。

### 注意事项

- 画面打开脚本里**不能读 DTPicker 控件属性**（控件未初始化），必须用 `$Year/$Month/$Day/$Hour` 系统变量推算。
- `ShowCurve(index, 0/1)` 会重置时间轴，必须在 `ShowCurve` 之后再调用 `SetTimeParamString`。

### 模板脚本（画面显示命令语言）

```c
STRING sStart; STRING sEnd;
LONG iY; LONG iM; LONG iD; LONG iHour;
LONG iEY; LONG iEM; LONG iED; LONG iEH;
LONG iDaysInMonth;

// 第一步：隐藏多余曲线，只显示第1条
Ctrl0010.ShowCurve(1, 1);
Ctrl0010.ShowCurve(2, 0);
Ctrl0010.ShowCurve(3, 0);
// ... 其余曲线同理（最多 16 条）

// 第二步：结束时间 = 当前整点
iEY = $Year; iEM = $Month; iED = $Day; iEH = $Hour;

// 第三步：起始时间 = 当前整点 - 12 小时
iY = iEY; iM = iEM; iD = iED; iHour = iEH - 12;
IF (iHour < 0)
{
    iHour = iHour + 24;
    iD = iD - 1;
    IF (iD < 1)
    {
        iM = iM - 1;
        IF (iM < 1) { iM = 12; iY = iY - 1; }
        IF (iM == 1 || iM == 3 || iM == 5 || iM == 7 || iM == 8 || iM == 10 || iM == 12) { iDaysInMonth = 31; }
        ELSE IF (iM == 4 || iM == 6 || iM == 9 || iM == 11) { iDaysInMonth = 30; }
        ELSE IF (iY % 4 == 0) { iDaysInMonth = 29; }
        ELSE { iDaysInMonth = 28; }
        iD = iDaysInMonth;
    }
}

// 第四步：拼接并设置时间轴（ShowCurve 之后再调用，否则时间轴会被重置）
sStart = StrFromInt(iY, 10) + "-" + StrFromInt(iM, 10) + "-" + StrFromInt(iD, 10) + " "
       + StrFromInt(iHour, 10) + ":00:00:000";
sEnd   = StrFromInt(iEY, 10) + "-" + StrFromInt(iEM, 10) + "-" + StrFromInt(iED, 10) + " "
       + StrFromInt(iEH, 10) + ":00:00:000";
Ctrl0010.SetTimeParamString(sStart, sEnd);
```

## 现场案例：历史趋势时间范围查询

### 适用场景

- 需求：在历史温度曲线画面中新增“开始日期 + 开始时间 + 结束日期 + 结束时间 + 查询按钮”。
- 控件：历史曲线控件 `KVHTrend`。
- 推荐输入方式：四个 `DTPicker` 控件，而不是把日期时间都手工拼到变量里。

### 推荐脚本写法

下面示例直接从四个时间控件读取属性，再调用 `SetTimeParamString()`：

```c
string sStart;
string sEnd;

sStart = StrFromInt(CtrlStartDate.Year, 10) + "-"
    + StrFromInt(CtrlStartDate.Month, 10) + "-"
    + StrFromInt(CtrlStartDate.Day, 10) + " "
    + StrFromInt(CtrlStartTime.Hour, 10) + ":"
    + StrFromInt(CtrlStartTime.Minute, 10) + ":"
    + StrFromInt(CtrlStartTime.Second, 10) + ":000";

sEnd = StrFromInt(CtrlEndDate.Year, 10) + "-"
  + StrFromInt(CtrlEndDate.Month, 10) + "-"
  + StrFromInt(CtrlEndDate.Day, 10) + " "
  + StrFromInt(CtrlEndTime.Hour, 10) + ":"
  + StrFromInt(CtrlEndTime.Minute, 10) + ":"
  + StrFromInt(CtrlEndTime.Second, 10) + ":000";

Ctrl10.SetTimeParamString(sStart, sEnd);
```

### 结论

- 日期和时间之间必须有空格。
- `SetTimeParamString()` 使用 `YYYY-M-D HH:MM:SS:ms` 这一类格式更直接，适合和 `DTPicker` 配合。
- 若需要严格按秒宽度控制时间轴，再改用 `HTConvertTime() + SetTimeParam()`。

## 相关 Skill

| Skill | 内容 |
|---|---|
| `kingview-variables` | 变量的报警属性定义 |
| `kingview-reports-recipes` | 报警数据的报表输出 |
| `kingview-database` | 历史库中查询报警记录 |
