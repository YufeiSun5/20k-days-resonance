---
name: kingscada-curves-charts
description: "KingScada（北京亚控）曲线与图表参考手册。使用场景：配置趋势曲线、历史趋势、XY曲线图、棒图、报表；查询趋势曲线属性、函数、命令语言；解决 KingSCADA 曲线组件报错；生成 KingSCADA 曲线相关代码或配置。触发词：KingScada、KingSCADA、亚控、趋势曲线、历史趋势、实时趋势、XY曲线、棒图、曲线图表、曲线函数、数据报表。"
---

# KingScada 第14章：曲线与图表

本 Skill 提供北京亚控 KingScada SCADA 系统中**曲线与图表**功能的完整参考知识。

## 适用场景

- 在 KingScada 画面中**创建/配置趋势曲线**（实时趋势、历史趋势）
- 配置 **XY 曲线图素**、**棒图**
- 使用**命令语言函数**操作曲线（如 `TrendPause()`、`TrendResum()`、`HTSetXXX()` 系列）
- 解决曲线组件的**属性配置问题**
- 生成曲线相关的**配置方案或代码片段**
- 解答关于**历史数据查询**和**数据报表**的问题

## 内容结构

完整文档位于 [./references/ch14-curves-charts.md](./references/ch14-curves-charts.md)，涵盖：

| 章节 | 内容 |
|------|------|
| 14.1 趋势曲线 | 创建、属性配置、标题区/绘图区/时间轴详细参数 |
| 14.1.x | 趋势曲线命令语言函数全表（TrendXXX 系列） |
| 14.2 XY 曲线图素 | 创建 XY 曲线、属性与函数 |
| 14.3 棒图 | 棒图创建与配置 |
| 附录 | 函数参数说明、枚举值含义表 |

## 使用方法

当用户询问 KingScada 曲线/图表相关问题时：

1. **加载参考文档**：读取 `./references/ch14-curves-charts.md`
2. **定位相关章节**：根据问题关键词（函数名、属性名、组件名）在文档中检索
3. **给出具体答案**：引用文档中的参数表格、步骤说明或代码示例回答
4. **注意版本**：文档对应 KingScada 默认版本，如用户使用特定版本请额外确认

## 快速参考 — 常用函数

| 函数 | 说明 |
|------|------|
| `TrendPause()` | 暂停/恢复实时趋势曲线滚动 |
| `TrendResum()` | 恢复趋势曲线 |
| `HTSetXXX()` 系列 | 设置历史趋势时间轴范围 |
| `TrendViewBegin()` | 设置趋势查看起始时间 |
| `TrendViewSpan()` | 设置趋势查看时间跨度 |

> 详细函数签名、参数类型和示例见参考文档。

## 相关 Skill

通用命令语言函数（画面操作、用户管理、字符串、日期时间、系统、报警等）请参考 `kingscada-functions` Skill。

---

## 实战经验（开发踩坑）

### 1. XYChart 控件运行时的硬性限制

使用 `XYChart` 控件做多曲线绘制时，以下操作**只能在设计期完成**，运行时无法通过命令语言改：

- **曲线数量必须预设**：把"理论最大条数"的曲线全部先建出来再隐藏；运行时不能 `AddSeries` 之类的动态添加。
- **Legend 数量必须预设**：和曲线一样，运行时不能新增 LegendItem。
- **曲线/Legend 的样式（颜色、线型、字体等）必须设计期定**：脚本里改不了。
- **加点方式只能逐点**：用 `XYChart1.AddNewPointEX(time, y, seriesIndex)` 一个个 push，不存在批量传数组的接口。
- **没有 tooltip 悬浮显示**：原生不支持鼠标悬停显示数值。
- **修改 LegendItem.Text 的前提**：必须先把 `XYChart1.Legend1.LegendItemN.InformationMode = 1`，否则赋值无效。

参考截图：![XYChart 多曲线示例](./references/xychart_demo.png)

### 2. XYChart Y 轴"漂亮刻度"算法

`SetIndexYAxisRange` 直接传 `max/4` 会出现 7.3、123.7 这种难看的刻度。下面这段算法把刻度对齐到 `1·10^k / 2·10^k / 2.5·10^k / 5·10^k`：

```c
float max = /* 数据中的最大值 */;
double rawStep = max / 4;
double log = LogN(rawStep, 10);
double k = Trunc(log);
double base = Pow(10, k);

double[6] candidates = { base/2, base*1, base*2, base*2.5, base*5, base*10 };
double niceStep = candidates[0];
double minDiff = Abs(candidates[0] - rawStep);
for (int i = 0; i < 6; i++) {
    double diff = Abs(candidates[i] - rawStep);
    if (diff < minDiff || (diff == minDiff && candidates[i] > niceStep)) {
        minDiff = diff;
        niceStep = candidates[i];
    }
}
XYChart1.SetIndexYAxisRange(niceStep * 6, 0, 0);
```

### 3. 多曲线绑定 KH 数据集的标准模板

清空 → 设置 X 轴范围 → 查 KH → 双层 for（外层变量、内层时间点）逐点 `AddNewPointEX` → 修改 LegendItem.Text。完整代码参考 `output/开发细节/content.md` 第 4 节 demo。

### 4. 报表模板的多模板用法

KS 的报表模板可以**在报表里绘制完成后导出为模板文件**。如果有多套模板的需求（例如日报、月报、年报），可以分别绘制 → 分别导出 → 运行时按需导入加载。

参考截图：![报表模板导出/导入示意](./references/report_template_demo.png)
