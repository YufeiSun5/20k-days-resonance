---
name: kingscada-functions
description: "KingScada（北京亚控）命令语言函数速查手册。使用场景：查询 KingSCADA 内置函数用法、参数说明、返回值；编写命令语言脚本；排查函数调用错误；生成 KingSCADA 脚本代码。涵盖：画面操作函数、用户操作函数、配方操作函数、SQL访问函数、字符串函数、日期时间函数、数学函数、系统函数、文件操作函数、资源函数、数据集函数、报警函数、其它函数。触发词：KingScada函数、命令语言、ShowPicture、LogOn、SQL访问、字符串函数、日期时间、系统函数、报警函数、脚本函数、KingSCADA脚本。"
---

# KingScada 命令语言函数速查手册

本 Skill 提供北京亚控 KingScada SCADA 系统中**全部内置命令语言函数**的完整参考知识，包括函数签名、功能说明、参数说明和返回值。

## 适用场景

- 查询某个函数的**参数含义和返回值**
- 在画面脚本/数据改变脚本中**编写或调试命令语言代码**
- 排查函数调用报错（参数类型错误、不支持的函数等）
- 生成 KingSCADA 脚本代码片段

## 函数分类

完整文档位于 [./references/command-language-function-quickref.md](./references/command-language-function-quickref.md)，共 13 大类：

| 类别 | 内容 |
|------|------|
| 一、画面操作函数 | ShowPicture、ClosePicture、PrintWindow 等 |
| 二、用户操作函数 | LogOn、LogOff、CheckUser、AddUser 等 |
| 三、配方操作函数 | 配方读写、配方切换（客户端不支持） |
| 四、SQL访问函数 | 数据库查询与写入（客户端不支持） |
| 五、字符串函数 | 字符串截取、拼接、查找、转换 |
| 六、日期时间函数 | 获取/格式化日期时间 |
| 七、数学函数 | 三角函数、取整、幂运算等 |
| 八、系统函数 | 退出程序、系统信息、运行状态 |
| 九、文件操作函数 | 文件读写、路径操作 |
| 十、资源函数 | 音频、图像等资源操作 |
| 十一、数据集函数 | 数据集读写与遍历 |
| 十二、报警函数 | 报警确认、报警查询 |
| 十三、其它函数 | 杂项辅助函数 |

> **注意**：配方相关函数和 SQL 访问相关函数在**客户端工程中不支持**。
> 所有函数的字符串参数最大长度为 **255 字符**，超过时自动截取。

## 使用方法

当用户询问 KingScada 函数相关问题时：

1. **加载参考文档**：读取 `./references/command-language-function-quickref.md`
2. **定位函数**：按函数名或所属类别在文档中检索
3. **给出具体答案**：引用函数签名、参数说明和示例说明

## 与曲线图表 Skill 的关系

曲线/图表相关函数（TrendXXX、HTSetXXX 系列）记录在 `kingscada-curves-charts` Skill 中；本 Skill 涵盖所有其他通用函数。

---

## 实战经验：报警窗口 `AlarmWindow.Query()`

`AlarmWindow` 控件除了实时/历史显示外，还可以做**条件查询**。要点：

### 配置步骤

1. 控件 `WindowType` 设置为"查询"模式
2. 报警服务器配置为 KingSCADA **默认数据库**（即使不显式配置默认库也会同时存储；若依赖外部 MySQL 等，可能因外部库失效导致无数据）
3. 拼出条件 SQL 后调用 `AlarmWindow1.Query(sql);`

### 拼 SQL 的三个坑

1. **时间字面量用 `#` 包裹，不是单引号**：
   ```
   AlarmTime Between #2026-04-01 00:00:00# and #2026-04-02 00:00:00#
   ```
2. **不能以 `select * from` 开头**：直接写表名 + WHERE 条件即可，例如：
   ```
   Alarm where AlarmTime Between #...# and #...# and GroupName = 'RootNode'
   ```
3. **运行时切换 `WindowType` 的顺序**：如果有"实时/历史/查询"切换需求，必须把**实时或历史**设为初始 WindowType，再切到"查询"；反过来（查询在前再切其他）切换后数据不显示。

### 示例代码

```c
string starttime = UIDateTime1.Value;
string endtime   = UIDateTime2.Value;
string groupName = "and GroupName = 'RootNode'";  // 按下拉框动态拼

string sql = "Alarm where AlarmTime Between #" + starttime + "# and #" + endtime + "#  " + groupName;
AlarmWindow1.Query(sql);
```

参考截图：![报警窗口查询示例](./references/alarm_window_demo.png)
