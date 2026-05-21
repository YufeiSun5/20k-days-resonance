---
name: kingview-reports-recipes
description: "组态王（北京亚控）配方、报表与系统安全管理参考手册。使用场景：配置组态王配方（下载/上传配方数据到设备）；使用报表控件设计打印报表；设置用户权限和系统安全管理；查询报表函数（ReportXXX系列）和配方函数。触发词：组态王配方、配方管理、报表系统、报表控件、打印报表、用户权限、系统安全、操作权限、ReportSetCellString、ReportPrint、配方上传、配方下载。"
argument-hint: "具体问题，例如：如何配置一个配方并下载到PLC、报表控件怎么绑定变量、如何限制用户操作权限"
---

# 组态王：配方、报表与安全管理

本 Skill 提供组态王 7.5 **配方管理、报表系统和系统安全**的完整参考，涵盖第十五章、第十六章、第十七章。

## 适用场景

- 配置**配方**：定义配方变量、成分组、配方数据，下载/上传到 I/O 设备
- 设计**报表**：使用报表控件创建日报、班报，绑定变量，设置打印格式
- 调用**报表函数**：`ReportSetCellString`、`ReportPrint`、`ReportLoad`、`ReportSave` 等
- 配置**系统安全管理**：
  - 用户账号与密码管理
  - 操作权限级别设置
  - 登录/注销控制
- 安全区域设置（控制某些画面/操作的访问权限）

## 内容结构

完整文档位于 [./references/kingview-reports-recipes-security.md](./references/kingview-reports-recipes-security.md)：

| 章节 | 内容 |
|------|------|
| 第十五章 | 配方管理：配方定义、配方数据编辑、下载上传操作 |
| 第十六章 | 系统安全管理：用户、权限、登录控制 |
| 第十七章 | 报表系统：报表控件属性、报表函数、打印与保存 |

## 使用方法

读取 `./references/kingview-reports-recipes-security.md`，按章节或关键词定位。

## 实战坑点与案例补充

### 两种报表填充方式对比

组态王历史数据报表有两种主流实现方式，选哪种取决于场景：

| 方式 | 函数 | 优点 | 适用场景 |
|---|---|---|---|
| 方式 A：直接填充 | `ReportSetHistData4()` | 代码短、自动填行、官方支持 | 按等间隔时间批量查所有变量 |
| 方式 B：逐行遍历 | `GetHistData() + WHILE` | 可自定义时间格式、灵活计算 | 需要在时间列显示自定义格式或做补零 |

---

### 方式 A：ReportSetHistData4（推荐，简洁）

```c
LONG tStart;
LONG tEnd;

// 读取起止时间（4个DTPicker控件）
tStart = HTConvertTime(Ctrl0.Year, Ctrl0.Month, Ctrl0.Day, Ctrl1.Hour, Ctrl1.Minute, Ctrl1.Second);
tEnd   = HTConvertTime(Ctrl0003.Year, Ctrl0003.Month, Ctrl0003.Day, Ctrl0005.Hour, Ctrl0005.Minute, Ctrl0005.Second);

// 第1列带时间列（最后参数=1表示输出时间）
ReportSetHistData4("report0", "一次供温", tStart, tEnd, 2, 1, 1);

// 后续列只写值（最后参数=0）
ReportSetHistData4("report0", "一次回温", tStart, tEnd, 2, 3, 0);
ReportSetHistData4("report0", "二次供温", tStart, tEnd, 2, 4, 0);
// ... 其余变量同理
```

**关键坑点：**
- `ReportSetHistData4` 的第 5 参数是起始行号（含表头为 1，数据从 2 开始）。
- 第 6 参数是列号。
- 第 7 参数：`1` = 同时写入时间列，`0` = 只写数据值。
- 每个变量单独调用一次，时间戳范围对齐即可，不需要手工写循环。

---

### 方式 B：GetHistData + WHILE 逐行遍历

```c
LONG iY; LONG iM; LONG iD; LONG iHour;
LONG iEY; LONG iEM; LONG iED; LONG iEH;
LONG tCur; LONG tEnd;
LONG iRow; LONG iDaysInMonth;
STRING sMM; STRING sDD; STRING sHour; STRING sTime; STRING sVal;

// 读取起止时间
iY = Ctrl0.Year; iM = Ctrl0.Month; iD = Ctrl0.Day; iHour = Ctrl1.Hour;
iEY = Ctrl0003.Year; iEM = Ctrl0003.Month; iED = Ctrl0003.Day; iEH = Ctrl0005.Hour;

tCur = HTConvertTime(iY, iM, iD, iHour, 0, 0);
tEnd = HTConvertTime(iEY, iEM, iED, iEH, 0, 0);

iRow = 2;
WHILE (tCur <= tEnd)
{
    // 格式化时间为 MM-DD HH:00
    IF (iM < 10) { sMM = "0" + StrFromInt(iM, 10); } ELSE { sMM = StrFromInt(iM, 10); }
    IF (iD < 10) { sDD = "0" + StrFromInt(iD, 10); } ELSE { sDD = StrFromInt(iD, 10); }
    IF (iHour < 10) { sHour = "0" + StrFromInt(iHour, 10); } ELSE { sHour = StrFromInt(iHour, 10); }
    sTime = sMM + "-" + sDD + " " + sHour + ":00";
    ReportSetCellString("report0", iRow, 1, sTime);

    sVal = GetHistData("\\local\\一次供温", iY, iM, iD, iHour, 0, 0);
    ReportSetCellString("report0", iRow, 2, sVal);
    // ... 其余变量同理

    // 小时 +1，跨日跨月进位
    iHour = iHour + 1;
    IF (iHour >= 24)
    {
        iHour = 0;
        iD = iD + 1;
        iDaysInMonth = 31;
        IF (iM == 4 || iM == 6 || iM == 9 || iM == 11) { iDaysInMonth = 30; }
        IF (iM == 2)
        {
            iDaysInMonth = 28;
            IF (iY % 4 == 0) { IF (iY % 100 != 0) { iDaysInMonth = 29; } IF (iY % 400 == 0) { iDaysInMonth = 29; } }
        }
        IF (iD > iDaysInMonth) { iD = 1; iM = iM + 1; IF (iM > 12) { iM = 1; iY = iY + 1; } }
    }
    tCur = HTConvertTime(iY, iM, iD, iHour, 0, 0);
    iRow = iRow + 1;
}
```

---

### 动态调整报表行数

按查询时间范围动态设置行数，避免上次查询的旧数据残留：

```c
LONG nRows; LONG iEndRow;
nRows   = (tEnd - tStart) / 3600 + 1;  // 每小时一行
iEndRow = 1 + nRows;                   // 含表头第1行
ReportSetRows("report0", iEndRow);
```

---

### 画面打开时的报表默认初始化

画面打开时 DTPicker 控件尚未初始化，**不能在打开脚本里读控件属性**。应改用系统变量 `$Year/$Month/$Day/$Hour` 推算默认时间范围：

```c
LONG iEY; LONG iEM; LONG iED; LONG iEH;
LONG tEnd; LONG tStart;
LONG iY; LONG iM; LONG iD; LONG iHour; LONG iDaysInMonth;

// 结束时间 = 当前整点
iEY = $Year; iEM = $Month; iED = $Day; iEH = $Hour;
tEnd = HTConvertTime(iEY, iEM, iED, iEH, 0, 0);
tStart = tEnd - 86400;  // 前推 24 小时

// 推算起始日期（前一天同小时）
iY = iEY; iM = iEM; iD = iED - 1; iHour = iEH;
IF (iD < 1)
{
    iM = iM - 1;
    IF (iM < 1) { iM = 12; iY = iY - 1; }
    iDaysInMonth = 31;
    IF (iM == 4 || iM == 6 || iM == 9 || iM == 11) { iDaysInMonth = 30; }
    IF (iM == 2)
    {
        iDaysInMonth = 28;
        IF (iY % 4 == 0) { IF (iY % 100 != 0) { iDaysInMonth = 29; } IF (iY % 400 == 0) { iDaysInMonth = 29; } }
    }
    iD = iDaysInMonth;
}
// 此处继续调用 ReportSetHistData4 或 GetHistData+WHILE 填充数据
```

**关键：画面打开时不要读 `Ctrl0.Year` 等控件属性，控件此时还没初始化。**

## 相关 Skill

| Skill | 内容 |
|---|---|
| `kingview-variables` | 报表绑定的变量定义 |
| `kingview-scripting` | 调用报表/配方函数的命令语言脚本 |
| `kingview-curves-alarm` | 报警数据输出到报表 |
