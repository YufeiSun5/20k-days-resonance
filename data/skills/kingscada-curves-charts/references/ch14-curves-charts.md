第十四章 曲线与图表

# 第十四章  曲线与图表

---

第十四章 曲线与图表

## 14.1 趋势曲线

趋势曲线用来反应数据变量随时间的变化情况。趋势曲线有两种：实时趋势曲线和历史趋势曲线。这两种曲线外形都类似于坐标纸，X轴代表时间，Y轴代表变量值。所不同的是，在画面程序运行时，实时趋势曲线随时间变化自动卷动，且自动查询时间轴时间内的变量历史数据，填充满整个曲线图素后继续绘制曲线，以快速反应变量的变化，但是不能随时间轴"回卷"，不能查阅变量的历史数据；历史趋势曲线可以完成历史数据的查看工作，但它不会自动卷动，而需要通过命令语言来辅助实现查阅功能。当趋势曲线图素在历史和实时模式下不断切换时，实时曲线不会清空重新从头绘制，而是延续历史曲线继续绘制。一个画面中可定义数量不限的趋势曲线，在同一个趋势曲线中最多可同时显示64个变量的变化情况。下面将具体介绍趋势曲线的使用方法。

---

第十四章 曲线与图表

### 14.1.1    创建趋势曲线

在KingSCADA画面编辑器中打开任一画面，单击"对象"菜单 →"扩展"→"趋势曲线"命令或直接单击工具箱中的图标→ 鼠标移到画面中，光标呈‘+’状，按住鼠标左键并拖动即可生成趋势曲线窗口，如图所示：

图14- 1 趋势曲线图素

趋势曲线由三部分组成，分别是：

² 
标题区

² 
绘图区：包括网格区、曲线、图例区和数据轴

² 
时间轴：包括游标

除了图素本身的属性外，每个区域都有自己的属性，下面将分别介绍这些属性的使用方法。

---

第十四章 曲线与图表

### 14.1.2    趋势曲线图素属性

在画面中选择趋势曲线图素，右侧会弹出图素属性对话框，如图所示：

         

图14- 2 趋势曲线图素属性

**Name****：**趋势曲线的名称

**MemberAccess****：**设置该图素能否能在脚本中访问。true：能在脚本中访问，false：不能在脚本中访问

**Comment****：**可以加注描述信息，缺省为空

**Size****：**通过设置宽度和高度的像素值确定图素的大小

**Location****：**位置，通过设置X轴和Y轴的像素值确定图素在画面中的位置

**BorderStyle****：**边框风格，设置趋势趋线图素边框的显示风格

**Background****：**背景，设置趋势曲线图素背景显示颜色及风格

**ChartAreaCount****：**绘图区数目，一个趋势曲线图素可最多划分为4个绘图区

**TimeAxis Count****：**时间轴数目，设置趋势曲线图素时间轴的数目，最多为4个

**RefreshFrequance****：**趋势曲线实时模式下，曲线刷新的频率，以毫秒为单位。

**TopHeight****：**绘图区距离趋势曲线图素上边缘的高度，最小设置为10。

**BottomHeight****：**绘图区距离趋势曲线图素下边缘的高度，最小设置为10。

**EditLock****：**编辑锁定，设置在开发环境该图素是否可以编辑。

**EditVisible****：**编辑可见，设置在开发环境该图素显示与否

**SecurityPriority****：**设置趋势曲线的安全级别

**SecuritySection****：**设置趋势曲线的安全域

**RefreshMode****：**趋势曲线实时模式下，曲线绘制的方向，可设置为自右刷新和自左刷新。

**LButtonZoomMode****：**趋势曲线历史模式下，通过鼠标左键框选进行区域放大，单击鼠标右键进行还原。有四个选项：无、X轴方向、Y轴方向、XY轴方向。

无：不支持鼠标左键框选区域放大；

X轴方向：鼠标左键框选完，鼠标弹出时，该绘图区的时间轴的最大最小值变为框选区域的X轴方向的最大最小时间。

Y轴方向：鼠标左键框选完，鼠标弹出时，该绘图区的数据轴的最大最小值变为框选区域的Y轴方向的最大最小值。

XY轴方向：鼠标左键框选完，鼠标弹出时，该绘图区的时间轴的最大最小值变为框选区域的X轴方向的最大最小时间，数据轴的最大最小值变为框选区域的Y轴方向的最大最小值。即框选区域填充整个绘图区。

当趋势曲线设置为历史模式的情况下，属性里才会出现LButtonZoomMode属性。

**TrendsMode****：**曲线模式，设置此图素显示曲线的模式，包括实时模式（0）和历史模式（1）

**ShowBorder****：**显示边框，设置趋势曲线图素的边框显示与否

**Visible****：**可见性，设置趋势曲线图素在运行环境中显示与否

**Enable****：**可用性，设置趋势曲线图素在运行环境中是否可以进行操作

**TabIndex****：**Tab键索引号，即在开发环境中使用Tab键切换图素的顺序

**HotKey****：**热键

**EnableTooltip****：**设置显示提示文本

**Tooltiptext****：**提示文本内容，当鼠标移动到图素对象上时显示的文本提示信息，最大长度128字符

---

第十四章 曲线与图表

### 14.1.3    趋势曲线标题区

选中趋势曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击标题区，右侧弹出属性对话框，如图所示：

图14- 3 趋势曲线标题区属性

**Name****：**标题区对象的名称，该名称不可编辑

**Location****：**通过设置X轴和Y轴的像素值确定标题区在趋势曲线图素中的位置，此位置是个相对位置，是相对趋势曲线图素的位置，范围为趋势曲线图素边界

**Size****：**通过设置宽度和高度的像素值确定标题区的大小，范围为趋势曲线图素边界

**BackColor****：**背景颜色，设置标题区的背景颜色

**Font****：**字体，设置标题区文字的字体

**TextBrush****：**字体颜色，设置标题区文字的颜色

**BorderStyle****：**边框风格，设置标题区的边框显示风格

**Text****：**文字，设置标题区显示的文本内容，长度限制为128个字符

**FillBackground****：**显示背景颜色，设置标题区的背景颜色显示与否

**ShowBorder****：**显示边框，设置标题区边框显示与否

**AutoFit****：**自动调整边框大小，当选择true时，边框大小随字体的大小自动调整，但是不能手动拉伸边框，选择false时，可以手动拉伸边框大小

**Visible****：**设置在运行环境中标题区显示与否

---

第十四章 曲线与图表

### 14.1.4    趋势曲线绘图区

趋势曲线的绘图区可以划分多个区域，每个区域都可以作为一个独立的绘图区来使用，且每个区域都可以设置自己的属性，绘图区包括：

² 
网格区

² 
曲线

² 
图例区

² 
数据轴

---

第十四章 曲线与图表

#### 14.1.4.1   绘图区属性

选中趋势曲线图素 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击绘图区，如图所示：

图14- 4 趋势曲线绘图区

绘图区属性对话框，如图所示：

图14- 5 趋势曲线绘图区属性

**Name****：**绘图区对象的名称，该名称不可编辑

**CurveCount****：**曲线数目，设置绘图区中的曲线条数，最多支持曲线条数为16

**DataAxisCount****：**数据轴数目，设置绘图区中的数据轴的数量，最多支持数量为4

**Visible****：**设置在运行环境绘图区显示与否。

**Location****：**通过X轴和Y轴的像素值确定绘图区在趋势曲线图素中的位置，可手动修改

**Size****：**通过宽度和高度的像素值确定标题区的大小，系统自动确定，不可手动修改

***注：在绘图区处于编辑的状态下（即选中绘图区），单击鼠标右键，出现的右键菜单包括"******添加绘图区"******、"******删除绘图区"******（若当前绘图区没有曲线，则没有该命令）、"******添加曲线""******添加数据轴"******。多个绘图区效果如图所示：***

图14- 6 多个绘图区效果

---

第十四章 曲线与图表

#### 14.1.4.2   网格区属性

选中趋势曲线图素 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 在绘图区内单击网格区，如图所示：

图14- 7 网格区

网格区属性对话框，如图所示：

图14- 8 网格区属性

**Name****：**网格区对象的名称，该名称不可编辑

**Background****：**设置网格区的背景颜色

**ShowMajorGrid****：**显示主网格，设置在网格区中是否显示主网格，true表示显示，false表示不显示。选择true时下列属性有效：

**MajorGridColor****：**设置主网格线显示的颜色，**ShowMajorGrid**属性为true时有效

**MajorGridStyle****：**设置主网格线显示的风格，**ShowMajorGrid**属性为true时有效

**MajorGridColumns****：**设置主网格线显示的列数，**ShowMajorGrid**属性为true时有效，可设置的数值范围是2~100

**MajorGridRows****：**设置主网格线显示的行数，**ShowMajorGrid**主网格属性为true时有效，可设置的数值范围是2~100

**ShowMinorGrid****：**显示副网格，设置在绘图区中是否显示副网格，true表示显示，false表示不显示

**MinorGrid Color****：**设置副网格线的颜色，**ShowMinorGrid**属性为true时有效

**MinorGrid Style****：**设置副网格线显示的风格，**ShowMinorGrid**属性为true时有效

**MinorGrid Columns****：**设置副网格线显示的列数，**ShowMinorGrid**属性为true时有效，可设置的数值范围是0~100

**MinorGrid Rows****：**设置副网格线显示的行数，**ShowMinorGrid**属性为true时有效，可设置的数值范围是0~100

**FillBackground****：**设置网格区的背景颜色显示与否

**Visible****：**设置在运行环境中网格区显示与否

**Location****：**位置，通过设置X轴和Y轴的像素值确定绘图区在趋势曲线图素中的位置，此位置是个相对位置，是相对趋势曲线图素的位置，范围为：趋势曲线图素边界。

**Size****：**大小，通过设置宽度和高度的像素值确定网格区的大小，范围为趋势曲线图素边界。

***注：在网格区处于编辑的状态下（即选中网格区），单击鼠标右键，右键菜单中包括"******添加曲线"******、"******删除曲线"******命令。***

---

第十四章 曲线与图表

#### 14.1.4.3   曲线属性

选中网格区 → 单击鼠标右键 → 在弹出的右键菜单中执行"添加曲线"命令 → 在网格区内单击曲线，曲线属性对话框，如图所示。

图14- 9 曲线属性

**Name****：**曲线对象的名称，该名称不可编辑，默认为Curve1、Curve2

**CurveStyle****：**设置曲线的显示风格，包括模拟、阶梯、逻辑和棒图

**TimeAxis****：**在下拉列表框中选择该曲线对应的时间轴，时间轴最多为2个，是在趋势曲线图素属性对话框中的"时间轴数目"中设定的

**DataAxis****：**在下拉列表框中选择该曲线对应的数据轴，数据轴最多为4个，是在绘图区属性对话框中的"数据轴数目"中设定的

**DataSource****：**数据源，设置曲线对应的变量，单击按钮，在弹出的变量列表中选择该曲线所对应的变量（包括引用变量，注意只有在实时模式下才能添加引用变量），或者单击按钮，在弹出的属性选择对话框中选择字符串类型的自定义属性作为曲线的数据源，比如"\\local\Tag1"

**ShowLabel****：**显示标签，设置在曲线上是否显示曲线点的数值。true表示显示，false表示不显示，效果如图所示。选择true时下列属性有效：

 

  

不显示标签                 
显示标签

**LabelTextColor****：** 标签文本颜色，设置曲线点数值的颜色，"**ShowLabel"**属性为true时有效

**LabelFont****：**设置曲线点数值的字体，"**ShowLabel"**属性为true时有效

**DecimalNum****：**设置曲线点数值显示的小数位数，可设置的最大位数是10。"**ShowLabel"**属性为true时有效

**Visible****：**设置曲线在运行系统下是否可见，该属性只在运行系统下起作用，在开发系统下即使设置为false也不会隐藏曲线（当在开发系统下Visible设置为false时，该曲线对应的图例前的复选框中的‘√’会取消，如图所示：）

图14- 10开发系统下Curve1曲线Visible属性为false

**InvalidDrawType****：**无效数据绘制方式，设置曲线在下面情况下显示的方式。

² 
曲线关联的变量的采集设备通讯失败；

² 
曲线关联的变量的质量戳为坏；

² 
运行系统退出。

显示方式可以选择如下方式中的一种：

² 
不绘制：从上一个有效数据点到下一个有效数据点间不采用任何连线方式。

² 
直线：从上一个有效数据点到下一个有效数据点间采用直线进行绘制，并可在**"InvalidLineStyle"**属性中配置无效数据区域的线型。

² 
区域填充：从上一个有效数据点到下一个有效数据点间采用填充区域的方式进行绘制，并可在"**InvalidAreaStyle**"属性中配置无效区域的填充颜色。

**InvalidLineStyle****：**无效数据线型

**InvalidAreaStyle****：**无效数据区域风格

**Line Style****：**当曲线连线可见时，设置连线的线型。说明：趋势曲线是没有起始箭头的，即设置的起始箭头在开发态和运行态下都不显示。

***注：在曲线处于编辑的状态下（即选中曲线），单击鼠标右键，右键菜单中包括"******添加曲线"******、"******删除曲线"******命令。***

---

第十四章 曲线与图表

#### 14.1.4.4   图例区属性

图例区是显示曲线说明信息的区域，每一个绘图区均包含一个图例区，且该区域是随着曲线的添加自动生成的。

选中趋势曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击图例区，如图所示：

图14- 11图例区

图例区属性对话框，如图所示：

图14- 12 图例区属性

**Name****：**图例区对象的名称，该名称不可编辑

**Brush****：**画刷，设置图例区背景颜色。

**BorderStyle****：**设置边框显示的样式，"**ShowBorder**"属性为true时有效

**FillBackgroud****：**设置图例区的背景颜色是否显示

**ShowBorder****：**设置图例区边框显示与否，true表示可见，false表示不可见

**AutoFit****：**自动调整边框大小，当选择true时，边框大小随字体的大小自动调整，但是不能手动拉伸边框，选择false时，可以手动拉伸边框大小

**Visible****：**设置在系统在运行环境下，图例区是否可见，true表示可见，false表示不可见。

**Location****：**通过设置X轴和Y轴的像素值确定图例区在趋势曲线图素中的位置，此位置是个相对位置，是相对趋势曲线图素的位置，范围为趋势曲线图素边界。

**Size****：**通过设置宽度和高度的像素值确定图例区的大小，范围为趋势曲线图素边界。

以上是图例区的属性介绍，下面介绍一下图例区中的图例项属性，在图例区可以单独选中某一图例项，如图所示：

图14- 13 选中图例项Curve2

在图例区处于编辑的状态下（即选中图例区），单击图例项，弹出属性对话框，如图所示：

图14- 14 图例项属性

**Name****：**图例项对象的名称，该名称不可编辑

**LabelFont****：**设置图例项文本字体及颜色

**LabelColor****：**设置图例项文本的颜色

**Text****：**设置图例项文本内容，可设置的字符长度为64个字符

**ShowCheckBox****：**显示复选框，设置在该图例项中是否显示复选框，当选择true时，显示复选框，单击复选框，该框中的‘√’消失，在开发系统中该图例项对应的曲线不会消失，仍然可见，只有在运行系统中该图例项对应的曲线会消失。如果不希望操作人员在运行环境中改变曲线的可见性，开发人员可以将此项设置为false

**InformationMode****：**设置图例项显示的文本内容。默认：显示数据源，自定义：显示图例项的Text属性值

**Visible****：**设置在运行环境中该图例项显示与否。

**Location****：**该项是由系统自动确定的，不可手动修改。

**Size****：**该项是由系统自动确定的，不可手动修改。

---

第十四章 曲线与图表

#### 14.1.4.5   数据轴属性

数据轴是用来标识曲线数值范围的坐标轴，属于绘图区的一部分。每个绘图区最多可以设置4个数据轴，是在绘图区属性对话框中的"**DataAxisCount**"属性中设置的。

选中趋势曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击数据轴，如图所示：

图14- 15 数据轴

数据轴属性对话框，如图所示：

图14- 16 数据轴属性

**Name****：**数据轴对象的名称，该名称不可编辑

**LineColor****：**设置轴线的颜色

**LabelTextColor****：**设置标记文本显示的颜色

**LabelFont****：**设置标记文本的字体

**ScaleMarkCount****：**设置数据轴刻度的数目，范围：2~100

**MinValue****：**设置数据轴的显示最小值，参考可设定范围-2147483648, 2147483648

**MaxValue****：**设置数据轴的显示最大值，参考可设定范围-2147483648,
2147483648

**ScaleCountPerLable****：**设置数据轴每个标记包含的刻度数目

**DecimalNum****：**设置标记文本显示的小数位数，可设置的最大位数是10

**LabelDock****：**设置标签所在的位置，即标签字体位于数据轴左侧或右侧，通过鼠标拖曳操作，能够使数值轴沿时间轴水平方向进行调整

**ZoomRatio****：**缩放比例，按照百分比对数值轴进行缩放，默认为100%，即不进行缩放

**ShowType****：**显示方式，设置数据轴显示数据的方式，包括百分比显示、实际值显示和自适应显示

² 
**百分比显示：**曲线的数值按照对应变量值在工程值范围中的百分比数进行显示，数据轴的显示范围是0-100。例如变量的工程值范围为0-200，而曲线的某一点的值为50，则显示的坐标值为25（即（50
/ （200-0））\* 100 = 25），系统自动将工程值变为百分数值画出历史曲线

² 
**实际值显示：**曲线的数值按照实际值显示，数据轴的显示范围是属性对话框中设置的"最小值"~最大值"，只有曲线的数值处于这个设置区间的线段才能显示

²  **自适应显示：**按照当前的曲线值自适应调整Y轴显示范围

**ShowUnit****：**设置数据轴的单位是否显示

**Unit****：**设置数据轴的单位，可设置字符长度为15个字符

**MinimumScopeMinValueForAuto****：**自适应显示方式最小量程的下限

**MinimumScopeMaxValueForAuto****：**自适应显示方式最小量程的上限

**MinimumScopeMinValueForAuto****：**自适应显示方式达到最小量程下限后调整的百分比下限

**MinimumScopeMaxPercentForAuto****：**自适应显示方式达到最小量程上限后调整的百分比上限

**注：如：MinimumScopeMaxValueForAuto****设置5****，MinimumScopeMaxPercentForAuto****设置50%****，则数据轴最大值-****当前曲线最大值<5****时，数据轴最大值增加（数据轴最大值-****数据轴最小值）的50%****。**

**MinimumScopeMinValueForAuto****和MinimumScopeMinValueForAuto****的用法与以上两属性相同。**

**ShowLine****：**设置数据轴线是否显示

**ShowLabel****：**设置数据轴标记是否显示

**Visible****：**设置运行环境中数据轴显示与否

**Location****：**通过X轴和Y轴的像素值确定数据轴在趋势曲线图素中的位置，此位置是个相对位置，是相对趋势曲线图素的位置，范围为趋势曲线图素边界，其中X轴坐标可以设置，Y轴坐标不可以手动设置

**Size****：**通过宽度和高度的像素值确定数据轴的大小，系统自动确定，不可手动修改。

***注：在数值轴处于编辑的状态下（即选中数值轴），单击鼠标右键，出现的右键菜单包括"******添加数值轴"******、"******删除数值轴"******，执行"******增加数值轴"******命令会自动添加一个数值轴。***

---

第十四章 曲线与图表

### 14.1.5     趋势曲线时间轴

时间轴是用来标识曲线所展示的时间段的坐标轴，在历史趋势图素中最多可以显示两个时间轴。

***注：在时间轴处于编辑的状态下（即选中时间轴），单击鼠标右键，出现的右键菜单包括"******添加时间轴"******、"******删除时间轴"******，执行"******添加时间轴"******命令会自动添加一个时间轴。***

---

第十四章 曲线与图表

#### 14.1.5.1   时间轴属性

选中趋势曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击时间轴，如图所示：

图14- 17 时间轴

时间轴属性对话框，如图所示：

图14- 18 时间轴属性

**Name****：**设置时间轴对象的名称，默认为TimeAxis1、TimeAxis2，不允许编辑

**LineColor****：**设置轴线的颜色

**LabelTextColor****：**设置标记文本显示的颜色

**LabelFont****：**设置标记文本的字体

**TimeParams****：**设置时间轴的时间参数，单击按钮，弹出对话框，如图所示：

图14- 19 时间参数设置对话框

上面的"设置时间参数"对话框提供了2种设置时间轴范围的方法。

1.        
固定的时间跨度：不设置起止时间，只设置时间跨度。运行态下，历史曲线保留首次查询时间，首次查询的时间为当前时间向前一个时间跨度，该跨度即为这里设置的时间跨度。

2.        
固定的时间参数：设置时间跨度和绝对的起止时间。运行态下，历史曲线按照该处的时间跨度查询起始时间和结束时间之间的数据。

**ScaleMarkCount****：**设置时间轴刻度的数目，范围：2~100

**ScaleCountPerLable****：**设置时间轴每个标记包含的刻度数目

**LabelFormat****：**在下拉列表框中选择时间轴显示的风格

**LabelDock****：**为标签所在的位置，即标签字体位于时间轴上端还是时间轴下端，通过鼠标拖曳操作，能够使时间轴沿数据轴垂直方向进行调整

**TextAngle****：**设置时间轴标记文字显示方向，即倾斜角度，范围是0~359度。

**在开发态的设置：**

**运行态设置：**

TrendChart1.TimeAxis1.TextAngle=20;

**ShowLine****：**设置时间轴线是否显示

**ShowLabel****：**设置时间轴标记是否显示

**Visible****：**设置运行环境中时间轴显示与否

**Location****：**通过X轴和Y轴的像素值确定时间轴在趋势曲线图素中的位置，此位置是个相对位置，是相对趋势曲线图素的位置，范围为趋势曲线图素边界，其中Y轴坐标可以设置，X轴坐标不可以手动设置

**Size****：**通过设置宽度和高度的像素值确定时间轴的大小，由系统自动确定，不可手动修改

---

第十四章 曲线与图表

#### 14.1.5.2   游标属性

游标是附着在时间轴上的，帮助用户查看趋势曲线某一具体时刻的数值及其它信息的工具，在趋势曲线图素中一共有两个游标，左游标和右游标，下面将具体介绍游标的使用方法。

选中趋势曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 选中游标对象，即可通过拖曳游标查看趋势曲线某一具体时刻的数值及其它信息。

游标属性对话框，如图所示：

图14- 20 游标属性

**Name****：**游标对象的名称，该名称不可编辑，默认是Cursor1和Cursor2

**LineColor****：**设置游标杆的颜色

**LabelFont****：**设置游标和曲线焦点信息显示的字体

**LabelBrush****：**设置游标和曲线焦点信息显示的字体颜色

**LabelBordStyle****：**设置游标和曲线焦点的信息窗的边框线显示方式

**LabelBackgroud****：**设置游标和曲线焦点的信息窗的背景颜色

**DecimalNum****：**设置游标和曲线焦点的信息中数值信息的小数位数，可设置的最大位数是10

**ShowVarName****：**设置在游标和曲线焦点的信息窗中是否显示曲线对象的名称，该名称是在曲线属性对话框中"名称"属性中设置的。true表示显示，false表示不显示

**ShowValue****：**设置在游标和曲线焦点的信息窗中是否显示曲线的数值，true表示显示，false表示不显示

**ShowTime****：**设置在游标和曲线焦点的信息窗中是否显示曲线的时间。true表示显示，false表示不显示

**ShowQualityStamp****：**设置在游标和曲线焦点的信息窗中是否显示质量戳，质量戳含义如下：

²  0x00040000：正确数据

²  0x10000000：设备没有连接

²  0x20000000：设备连接

²  0x30000000：网络没有连接

²  0x40000000：网络连接

²  0x50000000：记录使能

²  0x60000000：记录不使能

²  0x70000000：开机

²  0x80000000：关机

**ShowMemo****：**输入在游标和曲线焦点的信息窗中显示的注释信息。

**ShowCursorName****：**设置在游标和曲线焦点的信息中是否显示游标对象的名称。true表示显示，false表示不显示。

**MultiLine****：**设置在游标和曲线焦点的信息是否以多行的形式显示，选择true以多行形式显示，选择false以单行形式显示。

**ShowExactTagValue****：**设置显示真正的变量值还是显示计算值。true表示显示真正的变量值，即前一个点的值，false表示显示两点之间的计算值。

**LabelPosition****：**设置游标和曲线焦点信息窗显示的位置。

²  跟随曲线：跟随曲线显示

²  游标上端：并排显示在游标上端

²  游标下端：并排显示在游标下端

²  自定义：可由用户自己设置显示的位置，当选择此项时，可以使用鼠标拖拽信息窗口到画面的任何位置

**LabelShowType****：**设置游标和曲线焦点信息窗显示的方式。

²  不显示：不显示信息窗

²  总显示：一直显示信息窗

²  移动时显示：游标移动时显示信息窗

**ShowLableBorder****：**设置游标和曲线焦点的信息窗的框线显示与否

**FillBackgroud****：**设置游标和曲线焦点的信息窗的背景颜色显示与否

**Visible****：**设置在运行环境是否显示游标

**Location****：**通过X轴和Y轴的像素值确定游标在趋势曲线图素中的位置，此位置是个相对位置，是相对趋势曲线图素的位置，范围为趋势曲线图素边界。其中X轴坐标可以设置，Y轴坐标不可以手动设置

**Size****：**通过X轴和Y轴的像素值确定游标的大小，由系统自动确定，不可手动修改

**注：**运行态增加鼠标点击移动游标功能，点击绘图区种的任意一点，离该点近的X轴游标移动至该点的横坐标处。

---

第十四章 曲线与图表

### 14.1.6     趋势曲线事件

在KingSCADA运行环境中，在用户操作趋势曲线图素的过程中，如游标的移动、曲线的更换、时间轴时间设定的变化、数据轴设定的变化以及曲线实时与历史数据进行切换时，都可以产生一系列事件，通过这些事件的触发执行一系列脚本，实现对趋势曲线图素属性的修改或脚本的执行。设置方法如下：

在KingSCADA画面中选中趋势曲线图素 →
单击连接窗口中的 图标→ 在弹出的菜单中选择"事件"命令 → 弹出二级菜单，如图所示：

图14- 21 趋势曲线图素事件

在二级菜单中包括如下事件：

² 
游标事件

² 
曲线更换事件

² 
时间轴变化事件

² 
数据轴变化事件

² 
实时/历史曲线切换事件

---

第十四章 曲线与图表

#### 14.1.6.1   游标事件

**1****、游标可产生的事件包括：**

获得游标时刻：[On](#_游标移动事件)FocusOnCursor(String
CursorName,Long CurrentCursorTime);

游标移动时：[On](#_游标移动事件)CursorMoving(String
CursorName,Long CurrentCursorTime);

释放游标时刻：[On](#_游标移动事件)ReleaseCursor（String CursorName,Long
CurrentCursorTime);

**2****、事件中可使用游标的参数说明：**

CursorName：游标名称，字符串类型

CurrentCursorTime：游标所在位置的时间，长整型

**3****、调用执行方式：**

在运行环境中，使用鼠标操作游标但尚未移动时，可触发"获得游标时刻"事件。

在运行环境中，当鼠标拖动游标移动时，产生"游标移动"事件。

在运行环境中，当移动结束，并且鼠标离开游标时刻，可触发"释放游标时刻"事件。

---

第十四章 曲线与图表

#### 14.1.6.2   曲线更换事件

**1****、曲线更换事件：**

[On](#_游标移动事件)ChangeCurve（String CurveName,Long CurveChangeTime, String CurveYName, String oldCurveDataSource, String newCurveDataSource）

**2****、参数说明：**

CurveName：被更换的曲线名，字符串类型，值范围为Curve1~Curve64

CurveChangeTime：更换曲线时刻的时间，长整型

CurveYName：被更换曲线的数据轴名称，字符串类型

oldCurveDataSource：曲线原数据源，即曲线关联的原变量，字符串类型

newCurveDataSource：曲线新数据源，即曲线关联的新变量，字符串类型

**3****、调用执行方式：**

在运行环境中，当更改曲线关联的变量时，可触发"曲线更换"事件。

---

第十四章 曲线与图表

#### 14.1.6.3   时间轴变化事件

**1****、时间轴变化事件包括：**

起始时间变化：

OnChangeStartTime(String TimeAxisName, Long oldStartTime,Long
newStartTime)

时间轴长度变化：

OnChangeTimeScope(String TimeAxisName,Long oldTimeScope,Long
newTimeScope)

**2****、参数说明：**

TimeAxisName：当前时间轴名称，字符串类型

OldStartTime：原开始时间，长整型

NewStartTime：新开始时间，长整型

OldTimeScope：原时间长度，长整型

NewTimeScope：新时间长度，长整型

**3****、调用执行方式：**

在运行环境中，当趋势曲线图素的时间轴的起始时间改变时，可触发"起始时间变化"事件。

在运行环境中，当趋势曲线图素的时间轴的时间长度改变时，可触发"时间长度变化"事件。

---

第十四章 曲线与图表

#### 14.1.6.4   数据轴变化事件

**1****、数据轴变化事件包括：**

数据轴最大值变化：

[On](#_游标移动事件)ChangeYmaxValue(String DataAxisName,Float oldYMaxValue,Float
newYMaxValue)

数据轴最小值变化：

[On](#_游标移动事件)ChangeYminValue(String DataAxisName,Float oldYMinValue,Float
newYMinValue)

数据轴显示方式变化：

[On](#_游标移动事件)ChangeYDisplay(String DataAxisName,String
strDisplayMode,Float oldYScope, Float newYScope)

**2****、参数说明：**

DataAxisName：数据轴名称，字符串类型

oldYMaxValue：数据轴原最大值，浮点型

newYMaxValue：数据轴新最大值，浮点型

oldYMinValue：数据轴原最小值，浮点型

newYMinValue：数据轴新最小值，浮点型

strDisplayMode：数据轴显示方式，字符串类型

oldYScope：数据轴原显示范围，浮点型

newYScope：数据轴新显示范围，浮点型

**3****、调用执行方式：**

在运行环境中，当趋势曲线图素的数据轴的最大值改变时，可触发"数据轴最大值变化"事件。

在运行环境中，当趋势曲线图素的数据轴的最小值改变时，可触发"数据轴最小值变化"事件。

在运行环境中，当趋势曲线图素的数据轴显示方式发生变化时，可触发"数据轴显示方式变化"事件。

---

第十四章 曲线与图表

#### 14.1.6.5   实时/历史曲线切换事件

**1****、实时/****历史曲线事件包括：**

实时趋势切换到历史趋势：[On](#_游标移动事件)RealToHist()

历史趋势切换到实时趋势：[On](#_游标移动事件)HistToReal()

**2****、调用执行方式：**

在运行环境中，当趋势曲线图素由实时趋势切换到历史趋势时，可触发"实时趋势切换到历史趋势"事件。

在运行环境中，当趋势曲线图素由历史趋势切换到实时趋势时，可触发"历史趋势切换到实时趋势"事件。

---

第十四章 曲线与图表

### 14.1.7    趋势曲线图素方法

趋势曲线图素方法包括：增加曲线、删除曲线、修改时间轴、修改数据轴等等。通过调用方法，可以实现在线修改趋势曲线图素的功能，下面将具体介绍方法的种类及方法的使用。

---

第十四章 曲线与图表

#### 14.1.7.1   增删变更图元类

一、增删绘图区

**1****、添加绘图区函数**

string AddArea()

返回值说明：返回新增绘图区的名称。

示例：TrendChart1.AddArea()

 

**2****、删除绘图区函数**

bool DeleteArea(string AreaName)

返回值说明：

true---成功

false---失败

示例：TrendChart1.DeleteArea("ChartArea1");

 

二、增删曲线

**1****、添加数据源为某变量的曲线。**

String AddCurve1(String AreaName，String TimeAxisName，String DataAxisName，String VariateName)；

AreaName：曲线所在绘图区名称，字符串类型，如ChartArea1、ChartArea2、ChartArea3、ChartArea4

TimeAxisName：曲线所关联时间轴名称，字符串类型，如TimeAxis1、TimeAxis2

DataAxisName：曲线所关联数据轴名称，字符串类型，如DataAxis1、DataAxis2……DataAxis16

VariateName：曲线所关联的变量名，字符串类型，如为空，则该曲线不关联任何变量返回值说明：

返回值说明：

添加成功：返回值为曲线名称

添加失败：返回空字符串

示例：

TrendChart1.AddCurve1("ChartArea1", "TimeAxis1", "DataAxis1", "Tag1");

其中：TrendChart1是趋势曲线图素的名称

 

**2****、添加数据源为某数据集的曲线。**

String AddCurve2(String AreaName, String TimeAxisName，String DataAxisName，String DataSetName)；

AreaName：曲线所在绘图区名称，字符串类型，如ChartArea1、ChartArea2、ChartArea3、ChartArea4

TimeAxisName：曲线所关联时间轴名称，字符串类型，如TimeAxis1、TimeAxis2

1、如果数据集的时间范围>时间轴的范围，或部分重合，显示的曲线就是部分数据，然后，再将时间轴范围改大，能显示出剩下的数据；

2、如果数据集的时间范围=<时间轴的范围，显示的曲线就是数据集全部数据，能完整显示数据集数据；

3、如果数据集的时间范围和时间轴范围完全没有交集，则无法绘制和显示曲线，在执行了ChangeCurveDataSet（）函数之后，再改变时间轴范围为数据集时间范围，也无法显示曲线。所以用户要在执行ChangeCurveDataSet（）函数脚本前，应设置好时间轴的时间范围。

DataAxisName：曲线所关联数据轴名称，字符串类型，如DataAxis1、DataAxis2……DataAxis16

DataSetName：数据集名称。

返回值说明：

添加成功：返回值为曲线名称

添加失败：返回空字符串

示例：

KDBGetDataset(MyDataset", "DSN=khserver", "select
\* from history");  //执行函数得到一个名为"MyDataset"的数据集。

TrendChart1.AddCurve2("ChartArea1", "TimeAxis1",
"DataAxis1", "MyDataSet");

***注：其中参数"MyDataset"******是数据集名称，该数据集至少要有2******个字段，第1******个字段为数值型（值字段），第2******个字段日期时间型（时间戳字段），如果有第3******个字段，要为数值型（质量戳字段），第3******个字段可以省略，如果没有则默认所有数据的质量戳为GOOD******。***

 

**3****、异步查询工业库中的变量历史数据并绘制曲线**

int AddCurve3(string AreaName,string TimeAxisName,string
DataAxisName,string strConnect,int iTagNameMode,string strTagNames);

 

AreaName：曲线所在绘图区名称，字符串类型，如ChartArea1、ChartArea2、ChartArea3、ChartArea4

TimeAxisName：曲线所关联时间轴名称，字符串类型，如TimeAxis1、TimeAxis2

DataAxisName：曲线所关联数据轴名称，字符串类型，如DataAxis1、DataAxis2……

strConnect：工业库连接字符串，

"ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0"，其中超时单位为毫秒，0表示不超时退出。

iTagNameMode：strTagNames格式类型，1=变量名称组，2=csv文件路径。

strTagNames：工业库变量名称组和或者csv文件路径，多个变量时用‘，’分隔，如“Tag1，Tag2,Tag3”。

CurveStyle： 0模拟 1梯形 2逻辑 3棒图。

InvalidDrawType：0 不绘制 1 直线 3区域填充。

InvalidLineType：0实线 ；1点线 ；2段划线； 3划线1 ； 4划线2；  5长划线1 ； 6长划线2； 7点划线；8点长划线； 9点点划线 ；10 流水线形 ；11 线性个数

返回值：

-1：参数错误。

-2：工业库连接失败。

-3：获取绘图区参数错误。

0：成功。

**4****、变更曲线关联的变量**

Bool ChangeCurveDataSource(String CurveName, String NewDataSourceName)；

CurverName：曲线名称。

NewDataSourceName：关联的新数据源名称。

返回值说明：

1---变更成功

0---变更失败

示例：

TrendChart1.ChangeCurveDataSource ("Curve1", "Datasource1");

 

**5****、变更曲线关联的数据集**

Bool ChangeCurveDataSet(String CurveName, String DataSetName);

CurverName：曲线名称。

DataSetName：数据集名称。

返回值说明：

1---变更成功

0---变更失败

示例：

KDBGetDataset("MyDataset", "DSN=KingSCADADB", "select
\* from Table\_LotCols");  //执行函数得到一个名为"MyDataset"的数据集。

TrendChart1.ChangeCurveDataSet ("Curve1", "MyDataset");

***注：***

***1******、如果数据集的时间范围>******时间轴的范围，或部分重合，显示的曲线就是部分数据，然后，再将时间轴范围改大，能显示出剩下的数据；***

***2******、如果数据集的时间范围=<******时间轴的范围，显示的曲线就是数据集全部数据，能完整显示数据集数据；***

***3******、如果数据集的时间范围和时间轴范围完全没有交集，则无法绘制和显示曲线，在执行了ChangeCurveDataSet******（）函数之后，再改变时间轴范围为数据集时间范围，也无法显示曲线。所以，用户要在执行ChangeCurveDataSet******（）函数脚本前，应设置好时间轴的时间范围。***

 

**6****、删除曲线**

Bool DeleteCurve (String CurveName)；

CurveName：曲线名称。

返回值说明：

1---删除成功

0---删除失败

示例：

TrendChart1.DeleteCurve("Curve1");

 

**7****、清除当前所绘制的曲线**

void ClearCurrentCurves()

示例：

TrendChart1.ClearCurrentCurves();

*注：此清除函数仅对实时曲线有效果。*

**8****、删除绘图区所有曲线**

Bool DeleteCurves(string AreaName)

AreaName：曲线所在绘图区名称，字符串类型，如ChartArea1、ChartArea2

返回值：

True：成功。

False：失败。

---

第十四章 曲线与图表

#### 14.1.7.2   设置类

一、设置趋势模式

**1****、设置趋势曲线模式函数**

int SetTrendsMode(int TrendsMode)

TrendsMode：0=实时模式，1=历史模式

返回值说明：

0---成功

-1---失败

**2****、设置实时模式刷新参数**

Int SetRealModeRefreshParam(int RefreshMode，long RefreshFrequence)

RefreshMode：刷新模式，0表示自右刷新，1表示自左刷新

RefreshFrequence：刷新频率，单位为毫秒。范围：>500。

返回值说明：

0---成功

-1--- RefreshMode参数值非法

-2= RefreshFrequence参数值非法

**3****、设置曲线颜色**

Int SetCurveColorStyle(string CurveName,long nColorRed, long
nColorGreen, long nColorBlue)

CurveName：曲线名称

nColorRed：红色素值（范围：0--255）

nColorGreen：绿色素值（范围：0--255）

nColorBlue：兰色素值（范围：0--255）

返回值说明：

0：成功

-1：输入的曲线不在范围内。

-2：输入的色素值不在范围内。

**4****、设置数据轴标记文本显示的小数位数**

Bool SetDataAxisDecimalNum(string DataAxisName,int DecimalNum)

DataAxisName：字符串类型，数据轴名称

DecimalNum：整数，设置标记文本显示的小数位数，可设置的最大位数是10。

返回值说明：

1---成功

0---失败

示例：

TrendChart1.SetDataAxisDecimalNum("DataAxis1",2);将数据轴显示文本的数据小数位数设置为2位。

**5****、设置曲线绘制模式**

Bool SetCurveStyle(LPCTSTR szTagName, int iCurveStyle)

szTagName为对应曲线的变量名称；

iCurveStyle对应线型：模拟（0）、梯形（1）、逻辑（2）、棒图（3）

返回值：

True---成功

False---失败

**二、设置绘图区**

**1****、设置绘图区背景颜色**

bool SetAreaBackground(string AreaName,BrushProperty Background)

AreaName：绘图区名称。

Background：背景颜色，Color类型，是画面中用户自定义的Color属性的属性名称，如下图矩形所示：

图14-22

返回值说明：

true---成功

false---失败

示例：

TrendChart1.SetAreaBackground("ChartArea1",CustomProperty1);

**2.** **设置主网格线显示的列数**

SetMajorGridColums(string
AreaName,int MajorGridColumnsNum)

AreaName：绘图区名称。

MajorGridColumnsNum：设置主网格线列数

示例：

TrendChart1.SetMajorGridColums("ChartArea1",20);  
//设置绘图区1的主网格列数为20

**3.****设置主网格线显示的行数**

SetMajorGridRows(string
AreaName,int MajorGridRowsNum)

AreaName：绘图区名称。

MajorGridRowsNum：设置主网格线行数

TrendChart1. SetMajorGridRows
("ChartArea1",20);   //设置绘图区1的主网格行数为20

 

**三、设置单条曲线属性**

**1****、设置曲线可见性，动态设置某曲线是否可见。**

void SetCurveVisible（string CurveName，bool Visible）

CurveName：曲线名称。

Visible：曲线是否可见标志，True表示该曲线可见，False表示该曲线不可见。

示例：

TrendChart1.SetCurveVisible("Curve1",True);

 

**2****、设置曲线属性**

Int SetCurveProperty（string CurveName，int InvalidDrawType，bool ShowValue，bool Visible）

CurveName：曲线名称。

InvalidDrawType：无效数据绘制类型，1=None（空），2=InvalidLine，3=FillInvalidArea（填充区域）

ShowValue：是否在曲线上显示各个点值属性，True=显示，False=不显示。

Visible：曲线是否可见属性，True=该曲线可见，False=该曲线不可见。

返回值说明：

0---成功

-1--- InvalidDrawType参数值非法

示例：

TrendChart1.SetCurveProperty("Curve1",2,False,True);

注：变量数据点的质量戳类型包括Good、Bad、开机、关机。

根据数据点的质量戳，趋势曲线分为有效曲线和无效曲线，定义：

 

**3****、设置曲线线条样式**

bool SetCurveLineStyle(string CurveName，PenProperty CurveStyle)

CurveName：曲线名称。

CurveStyle：曲线线条样式，Pen类型，是画面中用户自定义的Pen属性的属性名称。如下图矩形所示：

图14-23

返回值说明：

True ---成功

False ---失败

示例：

TrendChart1.SetCurveLineStyle( "Curve1", CustomProperty1);

 

**4.** **设置曲线线条样式子属性**

bool SetCurveLineStyle1(string CurveName，PenProperty CurveStyle，int iSubPropertyType)

CurveName：曲线名称。

CurveStyle：曲线线条样式属性变量。Pen类型，是画面中用户自定义的Pen属性的属性名称（与SetCurveLineStyle中的CurveStyle意义相同）。

iSubPropertyType：0=设置所有的子属性，1=设置类型子属性，2=设置宽度子属性，3=设置起始箭头子属性，4=设置终止箭头子属性，5=设置拐点子属性，6=设置画刷子属性。

返回值说明：

True ---成功

False ---失败

**注：iSubPropertyType****为非0****值时，只将曲线线条样式的某个子属性值修改为CurveStyle****对应的子属性值，其它子属性保持原值不变。**

**5****、设置曲线宽度**

Bool SetCurveWidth(string CurveName,float fWidth)

CurveName：曲线名称，图例名称。

fWidth：曲线宽度，大于0的浮点数

返回值：

True：成功。

False：失败。

**四、设置图例区属性**

**1****、设置图例区可见性，动态设置某图例区是否可见。**

SetLegendAreaVisible（String LegendAreaName，bool Visible）

LegendAreaName：图例区的名称。

Visible：图例区是否可见标志，True=图例区可见，False=图例区不可见。

示例：

TrendChart1.SetLegendAreaVisible("Legend1",True);

 

**2****、设置图例区属性。**

SetLegendAreaProperty（String LegendAreaName，bool FillBackground，bool ShowBorder，bool Visible）

LegendAreaName：图例区的名称。

FillBackground：图例区的背景颜色是否显示，True=显示，False=不显示

ShowBorder：图例区的边框是否显示，True=显示，False=不显示

Visible：图例区是否可见属性，True=图例区可见，False=图例区不可见。

示例：

TrendChart1.SetLegendAreaStatus("Legend1",False,False,True);

 

**3****、设置图例区子项自定义文本**

bool SetLegendItemCustomText(string CurveName,string LegendItemCustomText)

CurveName：曲线名称

LegendItemCustomText：图例子项文本

返回值说明：

true---成功

false---失败

 

**五、设置时间轴游标属性**

**1****、设置曲线数据tip****的ShowMemo****属性**

bool SetCursorShowMemo(string CursorName,string strShowMemo)      

CursorName：游标名称。

strShowMemo：数据tip的ShowMemo字符串。

返回值说明：

true---成功

false---失败

示例：

TrendChart1.SetCursorShowMemo("Cursor1","WaterTemp");

 

**六、设置时间轴属性**

**1****、设置时间轴的起始时间，终止时间不变**

void SetStartTimeAxis（String TimeAxisName，Long Time）

TimeAxisName：时间轴名称。

Time：设置时间轴的起始时间秒数，该时间秒数为从指定时间到1970年1月1日0时之间的秒数（使用ConvertTimeToSecond（）函数获取）。

示例：

Long time

time= ConvertTimeToSecond (2009,1,21,0,0,0,1);

TrendChart1.SetStartTimeAxis("TimeAxis1",time);

 

**2****、****设置时间轴终止时间为当前时间**

void SetEndTimeAsCurrent (String TimeAxisName，Bool ChangeStartTime)

TimeAxisName：时间轴名称。

ChangeStartTime：是否改变起始时间标识量。True：时间轴的起始与终止之间的跨度大小不变，但起始时间值则重新计算设定。False：不改变起始时间，则时间轴的起始与终止之间的跨度大小被修改。

示例：

TrendChart1.SetEndTimeAsCurrent("TimeAxis1",True);

 

**3****、设置时间轴起始时间，终止时间不变**

Bool SetStringStartTimeAxis(string TimeAxisName，string StartTime)

TimeAxisName：时间轴名称。

StartTime：起始时间字符串。格式：yyyy-mm-dd hh:mm:ss。

返回值：

true---成功

false---失败

 

**4****、设置时间轴终止时间，起始时间不变。**

Bool SetStringEndTimeAxis (string TimeAxisName，string EndTime)

TimeAxisName：时间轴名称。

EndTime：结束时间字符串。格式：yyyy-mm-dd hh:mm:ss。

返回值：

true---成功

false---失败

 

**5****、****设置时间轴的起始时间和结束时间，起始时间和结束时间的范围没有限制，超过系统当前时间，也可设进去**

void SetTimeAxisParam2（String TimeAxisName，String strStartTime，String strEndTime)

TimeAxisName：时间轴名称。

strStartTime：时间轴起始时间字符串，格式：yyyy-mm-dd hh:mm:ss。

strEndTimet：时间轴结束时间字符串，格式：yyyy-mm-dd hh:mm:ss。

 

**6****、****设置时间轴的起始时间和时间跨度**

void SetTimeAxisParam1（String TimeAxisName，Long StartTime，Long TimeSpan，Long SpanUnit)

TimeAxisName：时间轴名称。

StartTime：时间轴起始时间秒数。

TimeSpan：时间轴的起始与终止之间的跨度大小。

SpanUnit：时间跨度大小单位：

 

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 0 | 秒 |
| 1 | 分 |
| 2 | 时 |
| 3 | 日 |

示例：

Long time

time=ConvertTimeToSecond（2009，1，21，0，0，0，0）

TrendChart1.SetTimeAxisParam1("TimeAxis1"，time，10，1);

此方法设定时间轴TimeAxis1的起始时间为time，时间跨度为10分钟。

 

**7****、****弹出时间轴参数设置对话框**

Bool SetTimeAxisDialog（String TimeAxisName）

TimeAxisName：时间轴名称。

返回值说明：

True---设置成功

False---设置失败

示例：

TrendChart1.SetTimeAxisDialog("TimeAxis1");

执行此方法后弹出时间轴参数设置对话框，在对话框里，可以设置时间轴的起始时间、结束时间、时间跨度大小、跨度单位，方便运行态下，用户改变查询时间范围。

**8****、设置时间标签数**

SetTimeAxisScaleMarkCount(string TimeAxisName,int ScaleMarkCount)

TimeAxisName：时间轴名称。

ScaleMarkCount：时间标签个数。

示例：

TrendChart1.SetTimeAxisScaleMarkCount("TimeAxis1",20); 
//设置时间轴1的时间标签个数为20

**9****、设置时间轴文本的倾斜角度**

SetTimeAxisTextAngle(string TimeAxisName,int TextAngle)

TimeAxisName：时间轴名称。

TextAngle：显示的倾斜角度。

示例：

TrendChart1.SetTimeAxisTextAngle("TimeAxis1",10)  //设置时间轴1的时间标签的倾斜角度10度，显示效果如下图：

 

**七、设置数据轴属性**

**1****、设置数据轴类型**

bool SetDataAxisType(string DataAxisName,int ShowType)

DataAxisName：数据轴名称

ShowType：数据轴类型，0=百分比，1=实际值，2=自适应。

示例：

TrendChart1.SetDataAxisType("DataAxis1",1);

 

**2****、设置数据轴范围，该方法对自适应类型的数据轴无效**

bool SetDataAxisRange(string DataAxisName，float MinValue，float MaxValue)

DataAxisName：数据轴名称

MinValue：数据轴最小值，单位为数据轴所设单位，如果是百分比模式，单位就是%。百分比类型的数据轴MinValue可以<0%。

MaxValue：数据轴最大值，单位为数据轴所设单位，如果是百分比模式，单位就是%。百分比类型的数据轴MaxValue可以>100%。

示例：

TrendChart1. SetDataAxisRange ("DataAxisName",-10,160);

**3****、设置数据轴显示值的小数位数**

SetDataAxisDecimalNum(string DataAxisName,int DecimalNum)

DataAxisName：数据轴名称

DecimalNum：设置的小数位个数

示例：

TrendChart1.SetDataAxisDecimalNum("DataAxis1",2);  //设置数据轴显示值的小数位数为2，显示效果如下图：

 

**八、设置鼠标键模式函数**

**1****、设置运行态下曲线绘图区内鼠标键模式**

Int SetMouseKeyMode(int nMouseKeyMode)

nMouseKeyMode：曲线绘图区Grid区内的鼠标键模式属性值。

0-----一般模式：

默认模式，曲线绘图区Grid区接受鼠标左右键、单击、双击操作事件，光标为箭头。

1------可移动模式（曲线在历史模式下才有效，实时模式无效）：

鼠标放在曲线绘图区Grid区里时，鼠标左右键、单击、双击操作事件无效，光标变为小手，可上下左右拖拽曲线。左右拖曲线，时间轴左右滚动；上下拖曲线，数据轴上下滚动。

 

**九、保存与恢复趋势图表参数**

**1****、更新趋势图表参数保存文件，将当前的趋势图表设置参数保存到文件中。**

int SaveTrendChartParam（string strfilename）；

strfilename：文件路径和文件全名（文件的扩展名是.cht），允许为空，空=工程路径\picture\默认文件名。

返回值说明：

0---成功

-1---失败，strfilename文件路径不存在，或扩展名不正确。

 

**2****、恢复趋势图表参数，趋势图表所有的参数恢复为参数保存文件里保存的参数。**

int RestoreTrendChartParam（string strfilename）；

strfilename：文件路径和文件全名（文件的扩展名是.cht），允许为空，空=工程路径\picture\默认文件名。

返回值说明：

0---成功

-1---失败，strfilename文件不存在。

---

第十四章 曲线与图表

#### 14.1.7.3   获取类

**一、获取属性**

**1****、获取时间轴参数方法1**

long GetTimeAxisParam1（String TimeAxisName，int iParamType）

TimeAxisName：时间轴名称。

iParamType：返回时间轴参数类型。

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 获取时间轴起始时间秒数 |
| 2 | 获取时间轴终止时间秒数 |
| 3 | 获取时间轴跨度 |

返回值说明：

返回时间轴的起始或终止时间，或时间跨度，单位：秒；

-1---TimeAxisName 不存在；

-2--- iParamType参数非法。

 

**2****、获取时间轴参数方法2**

string GetTimeAxisParam2（String TimeAxisName，int iParamType）

TimeAxisName：时间轴名称。

iParamType：返回时间轴参数类型。

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 获取时间轴起始时间字符串 |
| 2 | 获取时间轴终止时间字符串 |

返回值说明：

返回时间轴的起始时间字符串，或终止时间字符串。格式：yyyy-mm-dd
hh:mm:ss；

""---TimeAxisName不存在，或iParamType参数非法。

 

**3****、根据曲线名称获取数据源名称**

string GetDataSourceByCurveName(string CurveName)

CurveName：曲线名称

返回值说明：返回曲线对应的数据源变量名称。

 

**4****、根据曲线名称获取图例子项默认文本**

string GetLegendItemDefaultTextByCurveName(string CurveName)

CurveName：曲线名称

返回值说明：返回曲线对应的图例子项默认文本。

 

**5****、根据曲线名称获取图例子项自定义文本**

string GetLegendItemCustomTextByCurveName(string CurveName)

CurveName：曲线名称

返回值说明：返回曲线对应的图例子项自定义文本。

 

**6****、根据数据源名称获取曲线名称**

string GetCurveNameByDataSource(string DataSource)

DataSource：数据源变量名称

返回值说明：返回数据源对应的曲线名称，如同一个数据源变量有多条曲线，返回遍历到的第1条曲线名称。

 

**二、获取数据**

**1****、获取曲线在时间轴最左端的值**

Float .GetCurveLeftValue(string CurveName)

CurveName：曲线名称。

返回值说明：返回时间轴最左端的曲线值。

示例：Float b = HistTrend1.GetCurveLeftValue("Curve1");

 

**2****、获取曲线在时间轴最右端的值**

Float .GetCurveRightValue(string CurveName)

CurveName：曲线名称。

返回值说明：返回时间轴最右端的曲线值。

示例：Float b = HistTrend1.GetCurveRightValue("Curve1");

 

**3****、获取曲线在时间轴范围内的统计值**

Float GetCurveStatisticInTimeAxis (string CurveName, int iStatisticType，int iValueType)

CurveName：曲线名称。

iStatisticType：获取统计值类型：

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 获取时间轴范围内趋势的最小值 |
| 2 | 获取时间轴范围内趋势的最大值 |
| 3 | 获取时间轴范围内趋势的平均值 |

iValueType：获取值类型， 1表示获取实际值， 2表示获取百分比值。

返回值说明：返回时间轴范围内的曲线最小值、最大值或平均值。iValueType=1：返回实际值，iValueType=2：返回百分比值（按变量的实际量程范围计算）。无论数据轴是实际值模式，还是自适应模式，还是百分比模式，实际值、百分比值都可以获取。-1=输入参数非法。也有可能算出来的统计值=-1，无法区别。

 

**4****、获取某曲线某时间游标处的值**

Float GetCurveValueAtCursor(string CurveName,string CursorName)

CurveName：曲线名称

CursorName：游标名称

返回值说明：返回游标处的曲线值。

示例：TrendChart1.GetCurveValueAtCursor("Curve1","Cursor1");

 

**5****、获取某曲线在2****个时间游标之间的统计值。**

Float GetCurveStatisticInCursors(string CurveName，int iStatisticType)

CurveName：曲线名称。

iStatisticType：获取统计值类型：

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 获取两个游标间趋势的最小值 |
| 2 | 获取两个游标间趋势的最大值 |
| 3 | 获取两个游标间趋势的平均值 |

返回值说明：返回两个游标之间的最小值、最大值或平均值。

当曲线是实时模式时，时间游标对应的数据在变，返回的是瞬时的统计值。

-1=输入参数非法。也有可能算出来的统计值=-1，无法区别。

当2个时间游标之间的数据全部是无效数据时，返回的统计值是离左游标最近的一个有效值。

 

**三、获取动态时间**

**1****、获得时间游标所在位置时间**

String GetTimeAtCursor(String CursorName, String Format)

CursorName：游标名称。

Format：返回游标时间字符串的格式设置。

Date：以Windows控制面板相同格式显示日期

Time：以Windows控制面板相同格式显示时间

DateTime：以Windows控制面板相同格式显示日期时间

返回值说明：返回游标时间字符串。

示例：String s =
HistTrend1.GetTimeAtCursor("Cursor1", "Date");

---

第十四章 曲线与图表

#### 14.1.7.4   滚动类

**一、单轴滚动**

**1****、时间轴方向的滚动**

① 时间轴向过去滚动，将时间轴整体向过去移一段范围，起始时间和结束时间都减小某一个值。

void TimeAxisScrollPast（String TimeAxisName，Long ScrollRange)

TimeAxisName：时间轴名称。

ScrollRange：滚动幅度，单位为秒。大于0的整形，如果输入小数，脚本引擎会自动取整（去掉小数点后的数字），如果输入负数，等同于0处理。

示例：HistTrend1.TimeAxisScrollPast("TimeAxis1",600);

此方法将时间轴TimeAxis1向过去滚10分钟，起始时间和结束时间都减少10分钟。

 

② 时间轴向未来滚动，将时间轴整体向未来移一段范围，起始时间和结束时间都增加某一个值。

void TimeAxisScrollLater（String TimeAxisName，Long ScrollRange)

TimeAxisName：时间轴名称。

ScrollRange：滚动幅度，单位为秒。大于0的整形，如果输入小数，脚本引擎会自动取整（去掉小数点后的数字），如果输入负数，等同于0处理。

示例：HistTrend1.
TimeAxisScrollLater("TimeAxis1",600);

此方法将时间轴TimeAxis1向未来滚10分钟，起始时间和结束时间都增加10分钟。

 

**2****、数据轴方向的滚动**

① 数据轴向值减小方向滚动，将数据轴整体向值减小方向移一段范围，MinValue和MaxValue都减小某一个值。

void DataAxisScrollDecrease（String DataAxisName，Long ScrollRange)

DataAxisName：数据轴名称。

ScrollRange：滚动幅度，单位为数据轴所设单位，如果是百分比模式，单位就是%。大于0的整形，如果输入小数，脚本引擎会自动取整（去掉小数点后的数字），如果输入负数，等同于0处理。

示例：HistTrend1.DataAxisScrollDecrease("DataAxis1",20);

数据轴的最小值和最大值都减小20个单位。

 

② 数据轴向值增大方向滚动函数，将数据轴整体向值增大方向移动一段范围，MinValue和MaxValue都增大某一个值。

void DataAxisScrollIncrease（String DataAxisName，Long ScrollRange)

DataAxisName：数据轴名称。

ScrollRange：滚动幅度，单位为数据轴所设单位，如果是百分比模式，单位就是%。大于0的整形，如果输入小数，脚本引擎会自动取整（去掉小数点后的数字），如果输入负数，等同于0处理。

示例：HistTrend1. DataAxisScrollIncrease("DataAxis1",20);

数据轴的最小值和最大值都增大20个单位。

---

第十四章 曲线与图表

#### 14.1.7.5   缩放类

**一、单轴缩放**

**1****、时间轴方向的缩放**

① 扩大时间轴跨度，显示更长时间的曲线，曲线更密集。

void ZoomOutTimeAxis (String TimeAxisName，int iZoomType，int iZoomRangeUnit，Long ZoomRange)

TimeAxisName：时间轴名称。

iZoomType：时间轴缩放类型

 

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 保持时间轴起始时间与缩放前不变 |
| 2 | 保持时间轴终止时间与缩放前不变 |
| 3 | 保持时间轴中心时间与缩放前不变 |

iZoomRangeUnit：时间轴缩放幅度单位

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 秒 |
| 2 | %（是指当前时间轴跨度的百分之多少） |

ZoomRange：缩放幅度。大于0的整形，如果输入小数，脚本引擎会自动取整（去掉小数点后的数字），如果输入负数，等同于0处理。

iZoomRangeUnit=1时，时间轴缩放幅度为ZoomRange秒。

iZoomType =1或2：ZoomRange>=1，否则不缩放。

iZoomType =3：ZoomRange>=2，否则不缩放。

iZoomRangeUnit=2时，时间轴缩放幅度为Int（ZoomRange%\*当前时间轴跨度）秒：

iZoomType =1或2：Int（ZoomRange%\*当前时间轴跨度）>=1，否则不缩放。

iZoomType =3：Int（ZoomRange%\*当前时间轴跨度）>=2，否则不缩放。

示例：HistTrend1.ZoomOutTimeAxis
("TimeAxis1",3,1,600);

时间轴中心时间不变，对时间轴进行扩展，起始时间、终止时间各扩展5分钟。

 

② 缩小时间轴跨度函数，显示较少时间里的曲线，曲线密度变稀疏。

void ZoomInTimeAxis (String TimeAxisName，int iZoomType，int iZoomRangeUnit，Long ZoomRange)

TimeAxisName：时间轴名称。

iZoomType：时间轴缩放类型

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 保持时间轴起始时间与缩放前不变 |
| 2 | 保持时间轴终止时间与缩放前不变 |
| 3 | 保持时间轴中心时间与缩放前不变 |

iZoomRangeUnit：时间轴缩放幅度单位

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 秒 |
| 2 | %（是指当前时间轴跨度的百分之多少） |

ZoomRange：缩放幅度。整形，如果输入小数，脚本引擎会自动取整（去掉小数点后的数字），如果输入负数，等同于0处理。当iZoomRangeUnit=2时，ZoomRange只能>0，<100，大于和等于100时，函数执行无效。

iZoomRangeUnit=1时，时间轴缩放幅度为ZoomRange秒。

iZoomType =1或2：ZoomRange>=1，否则不缩放。

iZoomType =3：ZoomRange>=2，否则不缩放。

iZoomRangeUnit=2时，时间轴缩放幅度为Int（ZoomRange%\*当前时间轴跨度）秒：

iZoomType =1或2：Int（ZoomRange%\*当前时间轴跨度）>=1，否则不缩放。

iZoomType =3：Int（ZoomRange%\*当前时间轴跨度）>=2，否则不缩放。

示例：HistTrend1.ZoomInTimeAxis
("TimeAxis1",1,1,600);

时间轴起始时间不变，缩短时间轴跨度，终止时间缩短10分钟。

 

**2****、数据轴方向的缩放**

① 扩大数据轴范围，使曲线在数据轴方向显示更多的数据，曲线在数据轴方向被缩小。该方法对自适应类型的数据轴无效。

void ZoomOutDataAxis（String DataAxisName，int iZoomType，int iZoomRangeUnit，Float ZoomRange）

DataAxisName：要扩大的数据轴名称。

iZoomType：数据轴缩放类型：

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 保持Data轴最小值与缩放前不变 |
| 2 | 保持Data轴最大值与缩放前不变 |
| 3 | 保持Data轴中心值与缩放前不变 |

iZoomRangeUnit：数据轴缩放幅度单位

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 数据轴所设单位，如果是百分比模式，单位就是%。 |
| 2 | %（是指当前数据轴跨度的百分之多少） |

ZoomRange：缩放幅度，>0，如果输入负数，等同于0处理。

iZoomRangeUnit=1时：数据轴如果是实际值模式，缩放幅度按ZoomRange实际值缩放。数据轴如果是百分比模式，缩放幅度按满量程的ZoomRange%缩放。如果数据轴是自适应模式，则函数无效。

iZoomRangeUnit=2时：缩放幅度按当前数据轴跨度的ZoomRange%缩放。

示例：

当数据轴为百分比模式，当前数据轴范围20%~80%：

HistTrend1.ZoomOutDataAxis("DataAxis1",1,1,10);

数据轴以最小值为基准放大10%，数据轴范围变为20%~90%。

HistTrend1.ZoomOutDataAxis("DataAxis1",1,2,10);

数据轴以最小值为基准，当前数据轴跨度60%，放大10%，数据轴范围变为20%~86%。

 

② 缩小数据轴范围函数，减少数据轴方向曲线数据，曲线在数据轴方向被放大。该函数对自适应类型的数据轴无效。

void ZoomInDataAxis（String DataAxisName，int iZoomType，int iZoomRangeUnit，Float ZoomRange）

DataAxisName：要缩小的数据轴名称。

iZoomType：数据轴缩放类型：

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 保持Data轴最小值与缩放前不变 |
| 2 | 保持Data轴最大值与缩放前不变 |
| 3 | 保持Data轴中心值与缩放前不变 |

iZoomRangeUnit：数据轴缩放幅度单位

|  |  |
| --- | --- |
| 参数值 | 含义 |
| 1 | 数据轴所设单位，如果是百分比模式，单位就是%。 |
| 2 | %（是指当前数据轴跨度的百分之多少） |

ZoomRange：缩放幅度，>0，如果输入负数，等同于0处理。当iZoomRangeUnit=2时，ZoomRange只能>0并且<100，大于和等于100时，函数执行无效。

iZoomRangeUnit=1时：数据轴如果是实际值或自适应模式，缩放幅度按ZoomRange实际值缩放。数据轴如果是百分比模式，缩放幅度按满量程的ZoomRange%缩放。

iZoomRangeUnit=2时：缩放幅度按当前数据轴跨度的ZoomRange%缩放。

示例：

当数据轴为百分比模式，当前数据轴范围20%~80%：

HistTrend1. ZoomInDataAxis（"DataAxis1"，1，1，10)；

数据轴以最小值为基准缩小10%，数据轴范围变为20%~70%。

HistTrend1. ZoomInDataAxis（"DataAxis1"，1，2，10)；

数据轴以最小值为基准，当前数据轴跨度60%，缩小10%，数据轴范围变为20%~74%。

 

**二、时间轴和数据轴双轴同时缩放**

**1****、****区域放大函数**

对用户选中的区域（鼠标拉矩形）进行局部放大，矩形选择框内的曲线进行时间轴和Y轴的同时放大，将选中区域扩大到整个绘图区（选中区域的时间范围成为时间轴的范围，选中区域的数据范围成为数据轴的范围。

void AreaZoom();

 

**2****、****区域还原函数**

如果用户执行了区域放大函数，点击还原函数，让曲线的时间轴和数据轴回到区域放大前的状态，如果执行了区域放大之后，还执行了单轴缩放，也是还原到执行区域放大前的状态。如果执行了多次区域放大，则是回到第一次区域放大前的状态。

void AreaZoomReturn();

---

第十四章 曲线与图表

#### 14.1.7.6   输出类

**一、打印输出**

1、打印预览函数，弹出预览窗口，预览并设置打印效果。

void PrintPreview();

HistTrend1.PrintPreview();

 

2、打印曲线，对整个趋势曲线图素进行打印。

void PrintChart();

示例：HistTrend1 PrintChart();

---

第十四章 曲线与图表

## 14.2XY曲线图素

KingSCADA的XY曲线是根据横纵坐标在绘图区中进行描绘曲线的一种扩展图素，最多可以设置64条曲线，XY曲线绑定在相关的纵轴上，最多可设置16条纵轴，功能十分强大。

---

第十四章 曲线与图表

### 14.2.1    创建XY曲线图素

在KingSCADA画面编辑器中打开任一画面，单击"对象"菜单 →"扩展"→"XY chart"或直接单击工具箱中的图标→ 鼠标移到画面中，光标呈‘+’状，按住鼠标左键并拖动即可产生XY曲线窗口，如图所示：

图14-
24  XY曲线图素

XY曲线同趋势曲线类似，包含内部组成对象。其组成部分有：

² 
标题区：可设定该曲线图素的标题内容及标题属性

² 
绘图区：绘图区中包括坐标区、曲线和游标。

² 
坐标区：具有相应的横轴和纵轴，可通过属性窗口设定轴的属性，可具有多纵轴，最多16条

² 
曲线：曲线需要绑定数据轴，最多64条曲线

² 
游标：显示曲线的值(x,y的值)

² 
图例区：根据设定的曲线自动添加相应的图例，但可以自定义图例区的属性

---

第十四章 曲线与图表

### 14.2.2    XY曲线图素属性

在画面中选择XY曲线图素，右侧会弹出图素属性对话框，如图所示：

图14-
25  XY曲线图素属性

**Name****：**XY曲线的名称，缺省值为"XYChart1"

**CurveCount****：**曲线条数，最多可设置64条

**YAxisCount****：**Y轴数目，最多可设置16条

其他属性含义与趋势曲线类似。

---

第十四章 曲线与图表

### 14.2.3    XY曲线标题区

选中XY曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击标题区，弹出标题区属性对话框，如图所示：

图14-
26  XY曲线图素标题区属性

属性含义与趋势曲线类似。

---

第十四章 曲线与图表

### 14.2.4    XY曲线绘图区

选中XY曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击绘图区，如图所示：

图14-27 
XY曲线图素绘图区

绘图区属性对话框，如图所示：

图14-28 
XY曲线图素绘图区属性

XY曲线仅提供唯一的绘图区，属性含义与趋势曲线类似，区别是XY趋势曲线的绘图区中的Location和Size属性是可以手动修改的，而趋势曲线绘图区中的Location和Size是不能手动修改的。

---

第十四章 曲线与图表

### 14.2.5    XY曲线网格区属性

选中XY曲线图素 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 在绘图区内单击网格区，网格区属性对话框，如图所示：

图14-29 网格区属性

属性含义与趋势曲线类似，这里不再详述。

---

第十四章 曲线与图表

### 14.2.6    X坐标轴属性

选中XY曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击X轴，X轴属性如图所示：

图14-30 
XY曲线图素X坐标轴属性

**Name****：**默认为X，不允许修改，绘图区中仅有1条X轴

**ShowAsTime****：**可设X轴为时间轴，属性为true时X轴变为时间轴，默认为false，即在配置X轴范围时采用Double类型的配置方式，当X轴为时间轴时，则使用日期时间窗口进行配置最大最小值，并允许X轴使用时间数据，因此需要设定其时间显示格式，时间格式有以下几种：

²  
YYYY-MM-DD hh:mm:ss

²  
YYYY-MM-DD

²  
hh:mm:ss

²  
hh:mm

²  
mm:ss

²  
YYYY

²  
MM

²  
DD

²  
hh

²  
mm

²  
ss

**Dock****：**设置X轴位于绘图区的位置，有上下两种选择

其他属性含义与趋势曲线类似，但XY趋势曲线的X轴的Location和Size都不能进行修改。

---

第十四章 曲线与图表

### 14.2.7    Y坐标轴属性

选中XY曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击Y轴，属性如图所示：

图14-
31  XY曲线图素Y坐标轴属性

**Name****：**Y轴名称，不允许修改，最多可设置16条Y轴。

**Unit****：在ShowUnit**属性为true时，设置Y轴的单位，也可作为备注信息，长度要求在15个字符以内

**Dock****：**设置Y轴位于绘图区的位置，有左右两种选择

其他属性含义与趋势曲线类似，XY趋势曲线的Y轴的Location和Size都不能进行修改。

---

第十四章 曲线与图表

### 14.2.8    图例区属性

选中XY曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击图例区，属性如图所示：

图14-32 
XY曲线图素图例区属性

XY曲线图例区和图例项的属性含义都与趋势曲线类似。

---

第十四章 曲线与图表

### 14.2.9    曲线属性

选中XY曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 在绘图区内单击曲线，曲线属性对话框，如图所示：

图14-33 
XY曲线图素曲线属性

XY曲线中允许增加64条曲线，对每条曲线都可以进行属性设置

**MaxPointCount****：**设置绘制曲线的最大点数，允许绘制的最大点数为10000点

**ShowPoint****：**设置是否显示曲线数据点，true：显示，false：不显示

其他属性含义与趋势曲线类似。

---

第十四章 曲线与图表

### 14.2.10            游标属性

游标是附着在坐标轴上的，用户进行操作，可以查看某一具体时刻曲线的值以及其他信息的工具，XY曲线每个坐标轴上有一个游标。

选中XY曲线窗口 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 选中游标对象，即可通过拖曳游标查看趋势曲线某一具体时刻的数值及其它信息。

游标属性对话框，如图所示：

图14-34 
XY曲线图素游标属性

游标属性含义与趋势曲线类似。

---

第十四章 曲线与图表

### 14.2.11            XY曲线事件

在KingSCADA运行环境中，在用户操作XY曲线图素的过程中，同趋势曲线一样，产生一系列事件，如游标的移动、X轴和Y轴的移动变化等。通过这些事件的触发执行一系列脚本，实现对XY曲线图素属性的修改或脚本的执行。设置方法如下：

在KingSCADA画面中选中XY曲线图素 → 单击"连接"窗口中的 图标→ 在弹出的菜单中选择"事件"命令 → 此时弹出二级菜单，如图所示：

图14-35 
XY曲线图素事件列表

在二级菜单中包括如下事件：

² 
游标移动事件

² 
X轴移动事件

² 
Y轴移动事件

---

第十四章 曲线与图表

#### 14.2.11.1            游标移动事件

**1****、游标可产生事件有：**

获得游标时刻：OnFocusOnCursor(String
CursorName,Float CursorPosition);

游标移动时刻：OnCursorMoving(String
CursorName,Float CursorPosition);

释放游标时刻：OnReleaseCursor（String CursorName,Float
CursorPosition);

**2****、事件中可使用游标的参数：**

CursorName：游标名称，字符串类型

CursorPosition：游标所在的位置的值，浮点型

**3****、调用执行方式：**

在运行环境中，使用鼠标操作游标但尚未移动时，可触发"获得游标时刻"事件。

在运行环境中，当鼠标拖动游标移动时，产生"游标移动"事件。

在运行环境中，当移动结束，并且鼠标离开游标时刻，可触发"释放游标时刻"事件。

---

第十四章 曲线与图表

#### 14.2.11.2            X轴移动事件

**1****、X****轴移动事件包括：**

X轴起始值变化：OnXScrollLeft()

X轴终止值变化：OnXScrollRight()

**2****、参数说明：**

**3****、调用执行方式：**

该事件发生的场景主要是对X轴进行左右卷动操作，在运行环境中，通过脚本使用卷动X轴的函数进行操作

---

第十四章 曲线与图表

#### 14.2.11.3            Y轴移动事件

**1****、Y****轴移动事件包括：**

Y轴起始值变化：OnYScrollDown()

Y轴终止值变化：OnYScrollUp()

**2****、参数说明：**

**3****、调用执行方式：**

该事件发生的场景主要是对Y轴进行上下卷动操作，在运行环境中，通过脚本使用卷动Y轴的函数进行操作

---

第十四章 曲线与图表

### 14.2.12            XY曲线图素方法

XY曲线图素方法包括：增加曲线、删除曲线、修改时间轴、修改数据轴等等。通过调用这些方法，可以实现在线修改XY曲线图素的功能，下面将具体介绍方法的种类及方法的使用。

---

第十四章 曲线与图表

#### 14.2.12.1            新增数据点-横坐标为数值类型

**方法名称：**

Void AddNewPoint(float x, float y, long Index)

**方法功能：**

给指定曲线添加一个数据点，可以在程序开始时定义要显示的曲线，但是横坐标只能为数字或者日期。

**参数说明：**

x：设置数据点的x轴坐标值

y ：设置数据点的y轴坐标值

Index：给出X、Y轴曲线图素中的曲线索引号，取值范围0-63

**返回值：**

无

***注意：最多可以增加1024******个点，当增加到1024******个点后，不再增加点，不能实现把之前的点自动删除，增加新点，保持1024******点不变***

---

第十四章 曲线与图表

#### 14.2.12.2            新增数据点-新增x轴名称

**方法名称：**

Void AddNewPoint2(float  x, float  y,
long  Index, string  szPName)

**方法功能：**

给指定曲线添加一个数据点，可以在程序开始时定义要显示的曲线，设置数据点的X轴名称。

**参数说明：**

x：设置数据点的x轴坐标值

y ：设置数据点的y轴坐标值

Index：给出X、Y轴曲线图素中的曲线索引号，取值范围0-63

szPName：设置点的X轴名称

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.3            删除曲线数据

**方法名称：**

Void Clear( long Index )

**方法功能：**

清除一条曲线数据

**参数说明：**

Index：给出X、Y轴曲线图素中的曲线索引号，取值范围0-63

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.4            删除所有曲线数据

**方法名称：**

Void ClearAll()

**方法功能：**

清除所有曲线数据

**参数说明：**

无

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.5            获得指定点曲线数目

**方法名称：**

Long GetDataCount( long Index )

**方法功能：**

返回某一曲线点的数目

**参数说明：**

Index：给出X、Y轴曲线图素中的曲线索引号，取值范围0-63

**返回值：**

返回曲线索引号为Index的数据点数目

---

第十四章 曲线与图表

#### 14.2.12.6            获得指定X轴值对应Y轴值

**方法名称：**

Float GetValueAt( float x, long Index)

**方法功能：**

返回对应于X轴点值的Y值点插值

**参数说明：**

X：X轴坐标值

Index：给出X、Y轴曲线图素中的曲线索引号，取值范围0-63

**返回值：**

相应于X值的Y轴的值

---

第十四章 曲线与图表

#### 14.2.12.7            获得指定点对应X轴值

**方法名称：**

Float GetValueX( long DataIndex, long Index )

**方法功能：**

返回曲线中设定点的X轴值

**参数说明：**

DataIndex ：数据点的序号

Index：给出X轴曲线图素中的曲线索引号，取值范围0-63

**返回值：**

返回序号为DataIndex的数据点的X轴的值

---

第十四章 曲线与图表

#### 14.2.12.8            获得指定点对应Y轴值

**方法名称：**

Float GetValueY( long DataIndex, long Index )

**方法功能：**

返回曲线中设定点的Y轴值

**参数说明：**

DataIndex ：数据点的序号

Index：给出X轴曲线图素中的曲线索引号，取值范围0-63

**返回值：**

返回序号为DataIndex的数据点的Y轴的值

---

第十四章 曲线与图表

#### 14.2.12.9            加载数据

**方法名称：**

Int LoadFromFile( string FileName )

**方法功能：**

从文件中加载数据,文件格式与SaveToFile保存的文件格式相同。

**参数说明：**

FileName：文件名

**返回值：**

0：加载成功

-1：表示文件无法打开（当文件不存在或者文件路径不正确时，会导致无法打开该文件）

-2：表示文件无效（当文件不属于XY曲线导入的文件类型）

-3：表示文件格式不正确（当文件的内容不是按照规定的格式进行的，或者文件中数据不能够正确的进行曲线的绘制）

---

第十四章 曲线与图表

#### 14.2.12.10        保存数据

**方法名称：**

Int SaveToFile( string FileName )

**方法功能：**

保存数据到文件，文件的格式是.csv格式的。

如果FileName参数只是一个文件名，则会在所在工程路径下新建该文件；如果文件已存在，则覆盖已有文件。

如果FileName参数是带有目录路径的文件名，如果该目录路径存在，则会在该目录路径下新建该文件，如果文件已存在，则覆盖已有文件；如果该目录路径不存在，则新建文件失败，保存数据失败。

**参数说明：**

FileName：文件名，扩展名：.csv

**返回值：**

0：保存成功

-1：文件存储路径不存在（文件路径错误、文件名称错误）

-2：表示写入异常（当文件为只读或文件正在被访问情况下，将导致写入文件失败）

---

第十四章 曲线与图表

#### 14.2.12.11        预览曲线

**方法名称：**

Void PrintPreview()

**方法功能：**

预览当前曲线

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.12        打印曲线

**方法名称：**

Void Print（）

**方法功能：**

打印当前曲线

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.13        X轴左移

**方法名称：**

Void
ScrollLeft(float Ratio)

**方法功能：**

可将X轴左移一个跨度

**参数说明：**

Ratio：每次要移动的长度

**返回值：**

无返回值

---

第十四章 曲线与图表

#### 14.2.12.14        X轴右移

**方法名称：**

Void
ScrollRight(float Ratio)

**方法功能：**

可将X轴右移一个跨度

**参数说明：**

Ratio：每次要移动的长度

**返回值：**

无返回值

---

第十四章 曲线与图表

#### 14.2.12.15        Y轴上移

**方法名称：**

Void ScrollUp(float
Ratio)

**方法功能：**

可将Y轴上移一个跨度

**参数说明：**

Ratio：每次要移动的长度

**返回值：**

无返回值

---

第十四章 曲线与图表

#### 14.2.12.16        Y轴下移

**方法名称：**

Void ScrollDown(float
Ratio)

**方法功能：**

可将Y轴下移一个跨度

**参数说明：**

Ratio：每次要移动的长度

**返回值：**

无返回值

---

第十四章 曲线与图表

#### 14.2.12.17        设置曲线颜色

**方法名称：**

Int SetCurvesColorStyle(long Index, long
nColorRed, long nColorGreen, long nColorBlue)

**方法功能：**

设置指定曲线的颜色，采用RGB格式设置颜色

**参数说明：**

Index：曲线序号(范围：0-63)

nColorRed：红色素值（范围：0-255）

nColorGreen：绿色素值（范围：0-255）

nColorBlue：兰色素值（范围：0-255）

**返回值：**

0：成功

-1：输入的曲线不再范围内

-2：输入的色素值不再范围内

---

第十四章 曲线与图表

#### 14.2.12.18        设置曲线对应的Y轴

**方法名称：**

Int SetCurvesMapToYAxisIndex(long Index,
long DataIndex)

**方法功能：**

设置指定的曲线同Y轴的对应关系

**参数说明：**

Index：曲线序号(范围：0-63)

DataIndex：Y轴序号(范围：0-15)

**返回值：**

0：成功

-1：输入的曲线不再范围内

-2：输入的Y轴序号不再范围内

---

第十四章 曲线与图表

#### 14.2.12.19        设置Y轴的最大最小值

**方法名称：**

Void SetIndexYAxisRange(float YMax,float YMin,long Index);

**方法功能：**

设置Y轴的最大最小值

**参数说明：**

YMax：Y轴的最大值

YMin：Y轴的最小值

Index：Y轴序号(范围：0-15)

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.20        设置X轴的最大最小值

**方法名称：**

Void SetXAxisRange(float XMax,float XMin)

**方法功能：**

设置X轴的最大最小值

**参数说明：**

XMax：X轴的最大值

XMin：X轴的最小值

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.21        设置X轴时间格式

**方法名称：**

Long SetXAxisShowTimeFormat(long
nShowTime )

**方法功能：**

设置X轴用时间格式显示（"时：分：秒"），并且最小值为0

**参数说明：**

nShowTime：是否显示时间

0：正常按数据显示

1：显示时间

**返回值：**

0：成功

1：输入的数据越界, 即输入的参数不符合参数说明

---

第十四章 曲线与图表

#### 14.2.12.22        绘图区放大1倍

**方法名称：**

Void ZoomIn()

**方法功能：**

绘图区放大1倍

**参数说明：**

无

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.23        绘图区缩小1倍

**方法名称：**

Void ZoomOut()

**方法功能：**

绘图区缩小1倍

**参数说明：**

无

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.24        区域放大

**方法名称：**

Void ZoomPart()

**方法功能：**

使用鼠标在绘图区中选取一部分曲线，执行该方法，对选中的区域进行放大，显示到当前绘图区中

**参数说明：**

无

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.25        恢复区域原大小

**方法名称：**

Void ZoomResume()

**方法功能：**

恢复区域原大小

**参数说明：**

无

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.26        添加数据集

**方法名称：**

AddNewDataSet(String
strDataSetName,Long nIndex)

**方法功能：**

将符合条件的数据集显示在XY曲线图素中。

**参数说明：**

DataSetName：数据集的名称

Index：曲线序号（范围：0-63）

该方法可以读取数字型数据也可以读取非数字型数据并将数据显示在X轴上。

**举例：**

KDBGetDataset("MyDataset", "DSN=KingSCADADB",
"select \* from Table\_LotCols");  //执行函数得到一个名为"MyDataset"的数据集。

XYChart1.
AddNewDataSet ("MyDataset",2); //将上述得到的数据集显示在XY曲线图素中且对应的曲线序号为2。

---

第十四章 曲线与图表

#### 14.2.12.27        设置数据轴X轴的刻度个数

**方法名称：**

SetScaleMarkCount(long
nCount)

**方法功能：**

设置数据轴X包含的刻度个数。

**参数说明：**

nCount：刻度个数，有效范围2-100

**举例：**

XYChart1.SetScaleMarkCount(20); //设置曲线的数据轴的刻度个数为20个。

---

第十四章 曲线与图表

#### 14.2.12.28        设置数据轴X轴的标签个数

**方法名称：**

SetScaleCountPerLabel(int
nCount)

**方法功能：**

设置数据轴X包含的标签个数。

**参数说明：**

nCount：每个标签包含的刻度个数

**举例：**

SetScaleCountPerLabel (5); //设置曲线的数据轴每个标签的刻度数为5。

---

第十四章 曲线与图表

#### 14.2.12.29        设置X轴的最大最小值

**方法名称：**

Void
SetXAxisRangeEX(string XMax,string XMin)

**方法功能：**

设置X轴的最大最小值

**参数说明：**

XMax：X轴的最大值 字符串类型

XMin：X轴的最小值 字符串类型

**返回值：**

无

---

第十四章 曲线与图表

#### 14.2.12.30        新增数据点-横坐标为字符串类型

Void
AddNewPointEX(string x,float y,long Index)

**方法功能：**

给指定曲线添加一个数据点，可以在程序开始时定义要显示的曲线，但是横坐标只能为数字或者日期。

**参数说明：**

x：设置数据点的x轴坐标值字符串类型

y ：设置数据点的y轴坐标值

Index：给出X、Y轴曲线图素中的曲线索引号，取值范围0-63

**返回值：**

无

---

第十四章 曲线与图表

## 14.3棒图

棒图是指用图形的变化表现与之关联的数据的变化的绘图图表。KingSCADA中的棒图图形可以是二维条形图、三维条形图或饼图。

---

第十四章 曲线与图表

### 14.3.1    创建棒图

在KingSCADA画面编辑器中打开任一画面，单击"对象"菜单 →"扩展"→"棒图"命令或直接单击工具箱中的图标→ 鼠标移到画面中，光标呈‘+’状，按住鼠标左键并拖动即可生成棒图图表，如图所示：

图14-36 棒图图表

棒图图表由5个部分组成，分别是：

² 
标题区

² 
绘图区：包括数据轴、系列轴、网格区和系列区

² 
数据轴标题

² 
系列轴标题

² 
图例区

除了图表本身的属性外，每个区域都有自己的属性，下面将分别介绍这些属性的使用方法。

---

第十四章 曲线与图表

### 14.3.2    棒图图表属性

在画面中选择棒图图表，右侧会弹出图表属性对话框，如图所示：

图14-37 棒图图表属性

**Name****：**棒图图表对象的名称

**MemberAccess****：**该图表是否能在脚本中访问。true表示能在脚本中访问；false表示不能在脚本中访问

**Comment****：**可以加注描述信息，缺省为空

**Location****：**位置，通过设置X轴和Y轴的像素值确定图素在画面中的位置

**Size****：**通过设置宽度和高度的像素值确定图素的大小

**CapFont****：**图表中字体大小的限值，在运行环境中，当每个系列中设置的字体大小大于该限值时，系列中设置的字体大小不再起作用，而是使用该限值。在系列中设置字体大小的属性如图所示：

图14-38 系列中设置字体大小的属性

**BorderStyle****：**边框风格，设置棒图图表边框的显示风格

**Background****：**背景，设置棒图图表背景显示颜色及风格

**SeriesItemCount****：**设置图表中各个系列的系列项数量，如下图所示：

    

图14-39 系列数分别为3和5

**SeriesCount****：**设置图表中的系列数量（设为0无效），如下图所示：

    

图14-40 系列组数分别为1和2

**EditLock****：**编辑锁定，设置在开发环境该图素是否可以编辑

**EditVisible****：**编辑可见，设置在开发环境该图素显示与否

**SecurityPriority****：**设置棒图图表的安全级别

**SecuritySection****：**设置棒图图表的安全区域

**ChartType****：**设置图表显示类型，有棒图和饼图两个选项

**AutofixYAxis****：**设置Y轴自适应显示，默认为False，需设置系列中的所有系列项，自适应才有效

**ShowBorder****：**设置棒图图表的边框显示与否

**Visible****：**可见性，设置棒图图表在运行环境中显示与否

**Enable****：**可用性，设置棒图图表在运行环境中是否可以进行操作

**TabIndex****：**Tab键索引号，即在开发环境中使用Tab键切换图素的顺序

**HotKey****：**热键

**EnableTooltip****：**设置显示提示文本

**Tooltiptext****：**提示文本内容，当鼠标移动到图素对象上时显示的文本提示信息，最大长度为128字符

---

第十四章 曲线与图表

### 14.3.3    棒图图表的标题区

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击标题区，右侧弹出标题区属性对话框，如图所示：

图14-41 标题区属性窗口

**Name****：**标题区对象的名称，该名称不可编辑

**Location****：**通过设置X轴和Y轴的像素值确定标题区在棒图图表中的位置，此位置是相对位置，是相对棒图图表的位置，范围为棒图图表边界

**Size****：**通过设置宽度和高度的像素值确定标题区的大小，范围为棒图图表边界

**BackColor****：**背景颜色，设置标题区的背景颜色

**Font****：**字体，设置标题区文字的字体

**TextBrush****：**字体颜色，设置标题区文字的颜色

**BorderStyle****：**边框风格，设置标题区的边框显示风格

**Text****：**文字，设置标题区显示的文本内容，长度限制为128个字符

**FillBackground****：**显示背景颜色，设置标题区的背景颜色显示与否

**ShowBorder****：**显示边框，设置标题区的边框显示与否

**AutoFit****：**自动调整边框大小，当选择true时，边框大小随字体的大小自动调整，但是不能手动拉伸边框，选择false时，可以手动拉伸边框大小

**Visible****：**设置在运行环境中标题区显示与否

---

第十四章 曲线与图表

### 14.3.4    棒图图表的绘图区

一个棒图图表只有唯一一个绘图区，绘图区包括：

² 
数据轴

² 
系列轴

² 
网格区

² 
系列区

---

第十四章 曲线与图表

#### 14.3.4.1   绘图区属性

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击绘图区，绘图区如图14-42所示：

图14-42 棒图图表的绘图区

绘图区属性对话框，如图14-43所示：

图14- 43 绘图区属性窗口

**Name****：**绘图区对象名称，该名称不可编辑

**Location****：**通过设置X轴和Y轴的像素值确定绘图区在棒图图表中的位置，此位置是个相对位置，是相对趋势曲线图素的位置，范围为棒图图表边界

**Size****：**通过设置宽度和高度的像素值确定绘图区的大小，范围为棒图图表边界

**Visible****：**设置在运行环境绘图区显示与否

---

第十四章 曲线与图表

#### 14.3.4.2   数据轴

数据轴是用来限定棒图图表所显示的数据范围的坐标轴，属于绘图区的一部分。

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击数据轴，右侧弹出数据轴属性对话框，如图14-44所示：

图14- 44 数据轴属性窗口

**Name****：**数据轴对象的名称，该名称不可编辑

**Location****：**以X轴和Y轴的像素值表示数据轴在棒图图表中的位置，位置不可修改

**Size****：**以宽度和高度的像素值表示数据轴的大小，大小不可修改

**LineColor****：**设置轴线的颜色

**LabelTextColor****：**设置标记文本显示的颜色

**LabelFont****：**设置标记文本的字体

**ScaleMarkCount****：**设置数据轴刻度的数目，范围：2~100

**MinValue****：**设置数据轴显示的最小值，参考可设定范围-2147483648,
2147483648

**MaxValue****：**设置数据轴显示的最大值，参考可设定范围-2147483648,
2147483648

**ScaleCountPerLable****：**设置数据轴每个标记包含的刻度数目

**DecimalNum****：**设置标记文本显示的小数位数，可设置的最大位数是10

**Dock****：**设置数据轴停靠位置，可选择停靠在网格区的左侧或右侧

**ShowUnit****：**设置数据轴的单位显示与否

**Unit****：**设置数据轴的单位，可设置字符长度为15个字符

**ShowLine****：**设置数据轴线显示与否

**ShowLabel****：**设置数据轴标记显示与否

**Visible****：**设置在运行环境中数据轴显示与否

---

第十四章 曲线与图表

#### 14.3.4.3   系列轴

系列轴如下图所示，属于绘图区的一部分。

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击系列轴，右侧弹出系列轴属性对话框，如图14-45所示：

图14- 45 系列轴属性窗口

**Name****：**系列轴对象的名称，该名称不可编辑

**Localtion****：**以X轴和Y轴的像素值表示系列轴在棒图图表中的位置，位置不可修改

**Size****：**以宽度和高度的像素值表示系列轴的大小，大小不可修改

**LineColor****：**设置轴线的颜色

**LabelTextColor****：**设置标记文本显示的颜色

**LabelFont****：**设置标记文本的字体

**LabelItem****：**通过标签对话框来设置系列轴上显示的标记名称，如：1月、2月、3月、4月、5月

**Dock****：**设置系列轴停靠位置，可选择停靠在网格区的顶部或底部

**TextAngle****：**设置系列轴标记的旋转角度，范围为0~359度

**ShowLine****：**设置系列轴线显示与否

**ShowLabel****：**设置系列轴标记显示与否

**Visible****：**设置在运行环境中系列轴显示与否

---

第十四章 曲线与图表

#### 14.3.4.4   系列区

系列区属于绘图区的一部分，系列区中包含多个系列，系列的数量由棒图图表属性窗口中的SeriesCount属性决定。每个系列都有自己的属性设置窗口，如图14-46所示：

    

图14- 46 系列区中的系列数分别为1和2

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击任一系列，右侧弹出该系列属性对话框，如图14-47所示：

图14- 47 系列属性窗口

**Name****：**系列对象的名称，该名称不可编辑

**BorderColor****：**设置系列的边框颜色

**Brush****：**设置系列的画刷颜色

**ValueLabelTextColor****：**设置系列值标签文本颜色

**ValueLabelFont****：**设置系列值标签文本字体

**ShowBorder****：**设置系列边框颜色显示与否

**ShowValueLabel****：**设置系列值标签文本显示与否

**DecimalNum****：**设置系列值显示的小数位数

**ValueLabelPosition****：**设置系列值标签位置，可选择显示在系列的上部、中部和下部

**DisplayType****：**设置系列显示的类型，可选择二维或三维

**DrawType****：**设置系列绘制模式，可选择条状、圆柱、圆锥

**Visible****：**设置在运行环境中系列轴显示与否

---

第十四章 曲线与图表

#### 14.3.4.5   网格区

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击网格区，右侧弹出网格区属性对话框，如图14-48所示：

图14- 48 网格区属性窗口

**Name****：**网格区对象的名称，该名称不可编辑

**Location****：**通过设置X轴和Y轴的像素值确定网格区在棒图图表中的位置，此位置是个相对位置，是相对棒图图表的位置，范围为棒图图表的边界

**Size****：**通过设置宽度和高度的像素值确定网格区的大小，范围为棒图图表边界

**Background****：**设置网格区的背景颜色

**MajorGridColor****：**设置主网格线显示的颜色，**ShowMajorGrid**属性为true时有效

**MajorGridStyle****：**设置主网格线显示的风格，**ShowMajorGrid**属性为true时有效

**MinorGridColor****：**设置副网格线的颜色，**ShowMinorGrid**属性为true时有效

**MinorGridStyle****：**设置副网格线显示的风格，**ShowMinorGrid**属性为true时有效

**MajorGridColumns****：**设置主网格线显示的列数，**ShowMajorGrid**属性为true时有效，可设置的数值范围是2~100

**MajorGridRows****：**设置主网格线显示的行数，**ShowMajorGrid**属性为true时有效，可设置的数值范围是2~100

**MinorGridColumns****：**设置副网格线显示的列数，**ShowMinorGrid**属性为true时有效，可设置的数值范围是0~100

**MinorGridRows****：**设置副网格线显示的行数，**ShowMinorGrid**属性为true时有效，可设置的数值范围是0~100

**FillBackground****：**设置网格区的背景颜色显示与否

**ShowMajorGrid****：**设置主网格线显示与否

**ShowMinorGrid****：**设置副网格线显示与否

**Visible****：**设置在运行环境中网格区显示与否

---

第十四章 曲线与图表

### 14.3.5    棒图图表的数据轴标题

棒图图表的数据轴标题如图14-49红色矩形所示：

图14- 49 棒图图表中的数据轴标题

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击数据轴标题，右侧弹出数据轴标题属性对话框，如图14-50所示：

图14- 50 数据轴标题属性窗口

**Name****：**数据轴标题对象的名称，该名称不可编辑

**Location****：**通过设置X轴和Y轴的像素值确定数据轴标题在棒图图表中的位置，此位置是个相对位置，是相对棒图图表的位置，范围为棒图图表的边界

**Size****：**通过设置宽度和高度的像素值确定数据轴标题的大小，范围为棒图图表边界

**BackColor****：**设置数据轴标题区的背景颜色

**Font****：**设置数据轴标题区文字的字体

**TextBrush****：**设置数据轴标题区文字的颜色

**BorderStyle****：**设置数据轴标题区的边框显示风格

**Text****：**设置数据轴标题区显示的文本内容，长度限制为128个字符

**TextOrientation****：**设置数据轴标题区文本显示的方向，可选择横向或纵向

**FillBackground****：**设置数据轴标题区的背景颜色显示与否

**ShowBorder****：**设置数据轴标题区的边框显示与否

**AutoFit****：**自动调整边框大小，当选择true时，边框大小随字体的大小自动调整，但是不能手动拉伸边框，选择false时，可以手动拉伸边框大小

**Visible****：**设置在运行环境中数据轴标题显示与否

---

第十四章 曲线与图表

### 14.3.6    棒图图表的系列轴标题

棒图图表的数据轴标题如图14-51红色矩形所示：

图14- 51棒图图表中的系列轴标题

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击系列轴标题，右侧弹出系列轴标题属性对话框，如图14-52所示：

图14- 52系列轴标题属性窗口

**Name****：**系列轴标题对象的名称，该名称不可编辑

**Location****：**通过设置X轴和Y轴的像素值确定系列轴标题在棒图图表中的位置，此位置是个相对位置，是相对棒图图表的位置，范围为棒图图表的边界

**Size****：**通过设置宽度和高度的像素值确定系列轴标题的大小，范围为棒图图表边界

**BackColor****：**设置系列轴标题区的背景颜色

**Font****：**设置系列轴标题区文字的字体

**TextBrush****：**设置系列轴标题区文字的颜色

**BorderStyle****：**设置系列轴标题区的边框显示风格

**Text****：**设置系列轴标题区显示的文本内容，长度限制为128个字符

**TextOrientation****：**设置系列轴标题区文本显示的方向，可选择横向或纵向

**FillBackground****：**设置系列轴标题区的背景颜色显示与否

**ShowBorder****：**设置系列轴标题区的边框显示与否

**AutoFit****：**自动调整边框大小，当选择true时，边框大小随字体的大小自动调整，但是不能手动拉伸边框，选择false时，可以手动拉伸边框大小

**Visible****：**设置在运行环境中系列轴标题显示与否

---

第十四章 曲线与图表

### 14.3.7    棒图图表的图例区

棒图图表的图例区如图14-53红色矩形所示：

图14- 53 棒图图表的图例区

选中棒图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击图例区，右侧弹出图例区属性对话框，如图14-54所示：

图14- 54 图例区属性窗口

**Name****：**图例区对象的名称，该名称不可编辑

**Location****：**通过设置X轴和Y轴的像素值确定图例区在棒图图表中的位置，此位置是个相对位置，是相对棒图图表的位置，范围为棒图图表的边界

**Size****：**通过设置宽度和高度的像素值确定图例区的大小，范围为棒图图表边界

**Brush****：**设置图例区背景颜色

**BorderStyle****：**设置边框显示的样式

**FillBackground****：**设置图例区的背景颜色显示与否

**ShowBorder****：**设置图例区边框显示与否

**AutoFit****：**自动调整边框大小，当选择true时，边框大小随字体的大小自动调整，但是不能手动拉伸边框，选择false时，可以手动拉伸边框大小

**Visible****：**设置在系统在运行环境下，图例区可见与否

以上是图例区的属性介绍，下面介绍一下图例区中的图例项属性，在图例区可以单独选中某一图例项，如图所示：

图14- 55 图例区中的图例项

在图例区处于编辑的状态下（即选中图例区），单击图例项，弹出属性对话框，如图14-56所示：

图14- 56 图例项属性窗口

**Name****：**图例项对象的名称，该名称不可编辑

**LabelFont****：**设置图例项字体以及大小

**LabelColor****：**设置图例项字体颜色

**Text****：**设置图例项文本内容，，长度限制为64个字符，默认显示的文本内容是Series1、Series2。当InformationMode属性设置为"自定义"的话，该文本内容可以有用户自行设置。

**ShowCheckBox****：**设置在该图例项中是否显示复选框，true表示显示，false表示不显示，当选择true时，单击复选框，该框中出现‘√’，表示选中，此时，该图例项对应的系列消失。此功能适用于运行环境中，如果不希望操作人员在运行环境中改变系列的可见性，开发人员可以将此项设置为false

**InformationMode****：**设置图例项文本内容显示方式，选择"默认"项时，文本内容为Series1、Series2；选择"自定义"项时，文本内容可以由用户自行设置。该属性与Text属性配合使用。

**Visible****：**设置在运行环境中该图例项显示与否

---

第十四章 曲线与图表

### 14.3.8    饼图图表属性

当棒图图表属性窗口中的ChartType属性设置为"饼图"，即显示类型为饼图时，使用方法及属性设置与显示类型为棒图基本一致，在此不再赘述。下面只介绍饼图特有的属性。

饼图对象如图所示：

图14- 57饼图图表

选中饼图图表 → 单击鼠标右键 → 在弹出的右键菜单中执行"编辑"命令 → 单击饼图区，右侧弹出饼图区属性对话框，如图所示：

图14- 58饼区属性窗口

**1****、****AutoLableFont****：**标签字体自适应属性，当为true的时候，标签字体大小根据标签数目，从14 ~ 7 磅依次减小。为False时，其字体按照ValueLableFont属性的配置。

**2****、ItemsColor****：**单击该属性弹出对话框，如图所示：

图14- 59 饼图项颜色设置对话框

该对话框用来设置饼图各项颜色。

**3****、****ExtendDist****：**标签往外延伸距离属性，值范围为1 ~ 10。即椭圆对应扇形中心角度圆弧中心位置，向外延伸ExtendDist/10 个对应角度半径。ExtendDist的值不同，显示图效果如下：

图14- 60 ExtendDist为1的显示图

 

图14- 61 ExtendDist为10的显示图

**4****、****DataDisplay****：**标签显示内容属性，可以多选，如下图所示。

图14- 62标签显示项

**显示****Series**：显示序号1,2,3…

**显示项名**：显示每一项的名称

**显示实际值**：显示每一个的实际值

**显示百分比**：显示每一项的百分比

**显示连线**：显示标签和圆弧之间的连线

---

第十四章 曲线与图表

### 14.3.9    棒图图表方法

---

第十四章 曲线与图表

#### 14.3.9.1   棒图方法

**（1****）方法名称：**

String AddSeries();

**方法功能：**

根据当前系列组的形式，在绘图区上动态增加一个系列

**返回值：**

字符串类型：

添加成功：返回系列名称，如Series1、Series2、Series3等

添加失败：返回空字符串

**调用格式：**

CylinderChar1**.**AddSeries();

其中：CylinderChar1是棒图图表名称

**（2****）方法名称：**

Bool DeleteSeries (string SeriesName);

**方法功能：**

动态删除指定的系列

**参数说明：**

SeriesName：预删除的系列名称，字符串类型，如Series1、Series2、Series3

**返回值：**

离散型：

添加成功：返回True

添加失败：返回False

**调用格式：**

CylinderChar1.DeleteSeries("Series1");

其中：CylinderChar1是棒图图表名称

**（3****）方法名称：**

Bool SetDataValueForCylinder (string SeriesName, int Index, double
Value);

**方法功能：**

动态设置指定系列中某一系列项的数值

**参数说明：**

SeriesName：指定系列的名称，字符串类型

Index：系列项的索引号，范围0~63

Value：设置的数值，也可以是实型或整型变量

**返回值：**

离散型：

1：设置成功

0：设置失败

**调用格式：**

CylinderChar1.SetDataValue("Series1",1,Tag1);

**（4****）方法名称：**

Bool SetAllDrawType (Int Option);

**方法功能：**

动态设置全部系列绘制模式，有条状、圆柱、圆锥三种选择

**参数说明：**

Option：

0：设置系列绘制模式为Histogram，即条状

1：设置系列绘制模式为Column，即圆柱

2：设置系列绘制模式为Taper，即圆锥

**返回值：**

离散型：

1：设置成功

0：设置失败

**调用格式：**

CylinderChar1. SetAllDrawType (0);

**（5****）方法名称：**

Bool SetSeriesDrawType (String SeriesName, Int Option);

**方法功能：**

动态设置指定系列的绘制模式

**参数说明：**

SeriesName：指定的系列名称，字符串类型

Option：

0：设置系列绘制模式为Histogram，即条状

1：设置系列绘制模式为Column，即圆柱

2：设置系列绘制模式为Taper，即圆锥

**返回值：**

离散型：

1：设置成功

0：设置失败

**调用格式：**

CylinderChar1.SetAllDrawType ("Series1",0);

**（6****）方法名称**

Void AddDataSet(string RecordsetName,int
Option);

**方法功能：**

以棒图的方式显示数据集中的数据

**参数说明：**

RecordsetName：数据集名称，字符串类型

Option：

0：表示按数据集行显示棒图序列

1：表示按数据集列显示棒图序列

说明：当数据集中记录为多条时，该记录集中应该包含数据类型字段如"A产量"、"B产量"、"C产量"，同时应该包括相应对比的条目，如"2001年"、"2002年"、"2003年"，如下表所示：

|  |  |  |  |
| --- | --- | --- | --- |
| 年份 | A产量 | B产量 | C产量 |
| 2001 | 1 | 1 | 1 |
| 2002 | 2 | 2 | 2 |
| 2003 | 3 | 3 | 3 |

那么按照取得记录集行产生棒图序列，则产生如下三个棒图系列：

如果按照记录集列产生棒图序列，则产生如下三个棒图系列：

**返回值：**

无

**调用格式：**

KDBGetDataset("MyDataset", "DSN=KingSCADADB",
"select \* from Table\_LotCols");  //执行函数得到一个名为"MyDataset"的数据集。

CylinderChar1.AddDataSet("MyDataset",1);

***注：1******、当按行显示棒图系列时，数据集中行数的范围：1<******行数<17******，否则无法显示；***

***2******、当按列显示棒图系列时，数据集中列数的范围：1<******列数<17******，否则无法显示；***

***3******、对于饼图而言，只显示棒图中的一个系列。***

**（7****）方法名称：**

Int SetSeriesColorStyle(string SeriesName, long nColorRed, long
nColorGreen, long nColorBlue)

**方法功能：**

设置指定棒图系统的颜色，采用RGB格式设置纯颜色。

**参数说明：**

SeriesName：系列名称；

nColorRed：红色素值，（范围：0--255）；

nColorGreen：绿色素值，（范围：0--255）；

nColorBlue：蓝色素值，（范围：0--255）；

**返回值说明：**

整型：

0：成功

-1：输入的系列名不存在；

-2：输入的色素值不在范围内；

-3：当前是饼图系列。

CylinderChart1.SetSeriesColorStyle("Series1",100,0,0);

**（8****）方法名称：**

bool SetLegendItemCustomText(string SeriesName,string
LegendItemCustomText)

**方法功能：**

设置图例区图例子项显示文本

**参数说明：**

SeriesName,：系列名称

LegendItemCustomText：图例子项文本

**返回值说明：**

离散型：

true---成功

false---失败

**（9****）方法名称：**

bool SetLableItemText(string SeriesName, int index，string LableItemText)

**方法功能：**

修改饼图的标签名称，标签可显示值和百分比以及序号和项名称。

**参数说明：**

SeriesName：系列名称

index：系列项的索引号，范围0~63

LableItemText：labelitem显示文本

**返回值说明：**

离散型：

true---成功

false---失败

**（10****）方法名称：**

Double  SetMaxValue（double MaxValue）

**方法功能：**

设置棒图数据轴最大值。

**参数说明：**

MaxValue：double型，需要设置的最大值。

**返回值说明：**

实型：

返回当前设置的最大值。

**调用格式：**

CylinderChar1.SetMaxValue (50.00);

**（11****）方法名称：**

Double  GetMaxValue（void）

**方法功能：**

获得棒图数据轴最大值。

**参数说明：**

无

**返回值说明：**

实型：

返回当前设置的最大值。

**调用格式：**

CylinderChar1.GetMaxValue ();

**（12****）方法名称：**

Double  SetMinValue（double MinValue）

**方法功能：**

设置棒图数据轴最小值。

**参数说明：**

MinValue：double型，需要设置的最小值。

**返回值说明：**

实型：

返回当前设置的最小值。

**调用格式：**

CylinderChar1.SetMinValue (10.00);

**（13****）方法名称：**

Double  GetMinValue（void）

**方法功能：**

获得棒图数据轴最小值。

**参数说明：**

无

**返回值说明：**

实型：

返回当前设置的最小值。

**调用格式：**

CylinderChar1. GetMinValue ();

**（13****）方法名称：**

bool SetCylinderVisible(string SeriesName, bool bVisible)

**方法功能：**

设置某一棒图系列的显隐性。

**参数说明：**

SeriesName 字符串类型，棒图系列名称；

bVisible 布尔型，是否显示，为true时显示，为false时隐藏。

**返回值说明：**

布尔型，

True，成功；

false，失败。

**调用格式：**

CylinderChar1.SetCylinderVisible("Series2", 0);   
//设置系列2在 运行态隐藏

注：如果图例区的系列项设置不可选中，即属性ShowCheckBox为false，那么调用该方法无效。

**（14****）方法名称：**

PrintChart()；

**方法功能：**

打印棒图。

**参数说明：**

无

**返回值说明：**

无

**调用格式：**

CylinderChar1. PrintChart ()；  //打印棒图图标1

**（15****）方法名称：**

PrintView()；

**方法功能：**

打印预览棒图。

**参数说明：**

无

**返回值说明：**

无

**调用格式：**

CylinderChar1. PrintView()；  //打印预览棒图图标1

**（16****）方法名称：**

SetLabelItemText(int LabelItemIndex,string ItemText)；

**方法功能：**

设置系列项中lable字符串

**参数说明：**

LabelItemIndex：系列项的索引，从0开始

ItemText：设置的内容

**返回值说明：**

无

**调用格式：**

CylinderChart1.SetLabelItemText(0,"一系列");  //打印预览棒图图标1

设置之前与设置之后对比图如下：

（17）方法名称：

SetSeriesCylinderColor(string SeriesName,long nIndex,long
nColorRed,long nColorGreen,long nColorBlue)

方法功能：

设置指定棒图系统某个棒图的颜色，采用RGB格式设置纯颜色

参数说明：

SeriesName：系列名称；

nIndex：棒图索引（从0开始）；

nColorRed：红色素值，（范围：0--255）；

nColorGreen：绿色素值，（范围：0--255）；

nColorBlue：蓝色素值，（范围：0--255）；

返回值说明：

整型：

0：成功

-1：输入的系列名不存在；

-2：输入的色素值不在范围内；

-3：当前是饼图系列。

如图：按钮1、2、3分别设置索引为1、2、3的棒图的颜色。

如：CylinderChart1.SetSeriesCylinderColor("Series1",1,100,0,0);

---

第十四章 曲线与图表

#### 14.3.9.2   饼图方法

**（1****）方法名称：**

Bool SetItemColor(int ItemIndex, Color Color);

**方法功能：**

动态设置饼图中指定项（即指定成份）的颜色

**参数说明：**

Itemindex：指定项的索引号，从0开始

Color：Color类型的自定义属性的名称。如下图矩形框所示：

图14-64

**返回值：**

离散型：

1：设置成功

0：设置失败

**调用格式：**

CylinderChar1.SetItemColor(1,CustomProperty1);

**（2****）方法名称**

Bool SetDataValueForPie(int ItemIndex, double Value);//设定某个部分的数值

**方法功能：**

动态设置饼图中指定项的数值

**参数说明：**

Itemindex：指定项的索引号，从0开始

Value：设置的数值，也可以是实型或整型变量

**返回值：**

离散型：

1：设置成功

0：设置失败

**调用格式：**

CylinderChar1.SetDataValueForPie(1,Tag1);

**（3****）方法名称：**

Void ChangePieToCylinder();

**方法功能：**

将饼图中的数据作为棒图的一个系列来显示

**返回值：**

无

**调用格式：**

CylinderChar1.ChangePieToCylinder();

**（4****）方法名称**

Void ChangeCylinderToPie(int
SeriesNum);

**方法功能：**

将棒图中指定系列的数据转换到饼图中显示，，转化时只取第一个序列的数据

**参数说明：**

SeriesNum：指定的系列序号，从0开始

**返回值：**

无

**调用格式：**

CylinderChar1.
ChangeCylinderToPie(1);