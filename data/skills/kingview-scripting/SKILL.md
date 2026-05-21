---
name: kingview-scripting
description: "组态王（北京亚控）命令语言与运行系统参考手册。使用场景：编写组态王命令语言脚本（应用程序命令语言、数据改变命令语言、事件命令语言、画面命令语言、按键命令语言）；查询命令语言语法和内置函数；配置运行系统（TouchVew）参数；使用信息窗口调试输出。触发词：组态王命令语言、组态王脚本、应用程序命令语言、数据改变命令语言、事件命令语言、画面命令语言、TouchVew、运行系统、运行参数、信息窗口、组态王函数、ReportXXX、StrXXX。"
argument-hint: "具体问题，例如：命令语言有哪几种类型、如何写数据改变脚本、运行系统怎么配置自动启动"
---

# 组态王：命令语言与运行系统

本 Skill 提供组态王 7.5 **命令语言脚本、运行系统配置和信息窗口**的完整参考，涵盖第十章、第十一章、第十二章。

## 适用场景

- 了解组态王命令语言的**五种类型**及触发时机：
  - 应用程序命令语言（启动/退出/周期）
  - 数据改变命令语言
  - 事件命令语言
  - 画面命令语言（显示/隐藏/周期）
  - 按键命令语言
- 编写命令语言脚本（语法、变量引用、内置函数调用）
- 配置**运行系统**（TouchVew）：启动参数、初始画面、运行权限
- 使用**信息窗口**（日志输出）进行调试

## 内容结构

完整文档位于 [./references/kingview-scripting-runtime.md](./references/kingview-scripting-runtime.md)：

| 章节 | 内容 |
|------|------|
| 第十章 | 命令语言程序：五种类型详解、脚本语法、函数调用 |
| 第十一章 | 运行系统：TouchVew 配置、启动参数、运行模式 |
| 第十二章 | 信息窗口：日志输出与调试 |

## 使用方法

读取 `./references/kingview-scripting-runtime.md`，按章节或关键词定位。

## 实战坑点与脚本习惯

- 组态王命令语言不要按常见高级语言习惯去写；现场排查时，最常见的问题不是逻辑错，而是“语法以为支持，实际不支持”。
- 实际项目里应默认遵守以下写法：
  - 循环优先使用 `WHILE`，不要预设存在 `for`；
  - 数值转字符串用 `StrFromInt()` 或相关转换函数，不要直接把数值和字符串拼接；
  - 字符串转数值优先查文档确认，现场经验里不要假设存在 `Val()` 这一类函数，常改用 `StrToReal()`；
  - 字符串拼接使用 `+`；
  - 变量声明尽量一行一个，减少脚本编辑器兼容性问题；
  - 控件方法调用前，确保该控件所在画面已经打开运行。

### 时间控件读取示例

命令按钮中直接读取 `DTPicker` 的年月日时分秒：

```c
long iY;
long iM;
long iD;
long iH;
long iMin;
long iS;

iY = Ctrl0.Year;
iM = Ctrl0.Month;
iD = Ctrl0.Day;
iH = Ctrl1.Hour;
iMin = Ctrl1.Minute;
iS = Ctrl1.Second;
```

### 时间戳计算示例

历史查询或报表中，可直接使用 `HTConvertTime()`：

```c
long tStart;
long tEnd;

tStart = HTConvertTime(iY, iM, iD, iH, iMin, iS);
tEnd = HTConvertTime(iEY, iEM, iED, iEH, iEMin, iESec);
```

### 报表按小时遍历示例结论

- 组态王脚本适合“读起止时间 -> 转时间戳 -> `WHILE` 循环按小时累加”的模式。
- 跨天、跨月、闰年进位通常要手工写，不要默认存在高级日期库。
- 若只是给曲线控件传时间范围，优先调用控件现成方法，不要自己在脚本里重复实现完整的时间轴逻辑。

## 相关 Skill

| Skill | 内容 |
|---|---|
| `kingview-graphics` | 按钮/画面中的命令语言触发点 |
| `kingview-database` | 脚本中调用 SQL 访问函数 |
| `kingview-reports-recipes` | 脚本中调用报表/配方函数 |
