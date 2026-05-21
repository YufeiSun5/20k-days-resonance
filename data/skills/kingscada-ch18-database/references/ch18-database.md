第十八章  数据库访问

# 第十八章 数据库访问

 

 

KingSCADA SQL访问功能实现KingSCADA和其他外部数据库之间的数据传输。提供2种访问方式：一种是记录体的方式，即变量和数据库表中的字段建立绑定关系；一种是数据集方式。

记录体方式就是通过记录体配置项，实现把数据库里面的字段和KingSCADA的变量建立绑定关系。绑定后，可以通过SQL插入或更新函数将绑定变量插入或更新到数据库的对应字段中；也可以通过SQL查询函数将数据库中对应字段数据查询并保存到绑定变量中，实现KingSCADA和关系数据库的数据交互。记录体方式只能在KingSCADA服务器端应用。

数据集方式就是通过KingSCADA提供的一系列数据集函数，执行标准SQL语句，实现KingSCADA与关系数据库之间的增、查、删、改等所有数据交互功能，尤其可以通过数据集函数，实现从数据库内查询出符合SQL语句的数据集合，并在报表、曲线中进行批量数据展示。数据集方式支持在KingSCADA服务器端、C/S客户端、WEB上使用。

下面具体介绍2种方式的具体使用。

---

第十八章  数据库访问

## 18.1     记录体方式

记录体方式是通过KingSCADA访问管理器来配置实现的，包括“表格模板”和“记录体”两部分。通过表格模板在数据库表中建立相应的表格，通过记录体建立数据库字段和KingSCADA变量之间的联系。同时还可以通过SQL相关函数直接操作数据库中的数据。

KingSCADA SQL访问管理器在工程设计器的树型目录中，如图所示：

图18-1
SQL访问管理器

SQL访问管理器包括两部分：表格模板和记录体。下面将具体介绍这两部分。

---

第十八章  数据库访问

### 18.1.1    表格模板

在表格模板中建立数据库表格的结构，即数据库表格中有哪些字段、字段的名称、字段的类型、字段的长度等信息。表格模板配置过程如下：

**第一步：创建表格模板**

在KingSCADA工程设计器的树型目录中选择“通用数据源”→“ODBC数据源”→“表格模板”选项，在右侧编辑区中单击“新建”按钮弹出对话框，如图所示：

图18-2
表格模板编辑对话框

**第二步：编辑表格模板**

**模板名称：**设置表格模板的名称，长度不超过32个字符且唯一。

**模板描述：**该模板的注释信息，长度不超过64个字符。

**字段名称：**字段是用于定义数据库表格的一个列，此项是设置字段的名称，长度不超过32个字符。

**数据类型：**设置字段的类型，根据需要在下拉列表框中选择相应的类型。

**索引类型：**索引功能是数据库用于加速字段中搜索与排序的速度，也可能使更新速度变慢，根据需要在下拉列表框中选择相应的类型，包括唯一索引、非唯一索引和无索引三种类型。

**数据长度：**当数据类型选择字符串时，该选项有效，用来设置数据类型的长度。

**允许空：**单击此项前的复选框，该框中出现
‘√’表示选中，表示数据记录到数据库的表格中该字段可以有空值，否则表示该字段的数据不能为空值。

**“添加字段”按钮：**当一个字段设置完毕后，单击此按钮可以将字段以及相关信息添加到字段列表框中。

**“删除字段”按钮：**在字段列表框中选中某行字段，单击此按钮可将该字段删除。

**“修改字段”按钮：**在字段列表框中选中某行字段，重新编辑完后，单击此按钮保存修改的内容。

**“上移”按钮：**在字段列表框中选中某行字段，单击此按钮可使字段的位置向上移动。

**“下移”按钮：**在字段列表框中选中某行字段，单击此按钮可使字段的位置向下移动。

**第三步：**设置完成后单击“确定”按钮，完成表格模板的配置，该模板会自动增加到模板编辑区中，如图所示：

图18-3
表格模板编辑区

**第四步：删除表格模板**

在图18-3表格模板编辑区中选中预删除的模板，单击“删除”按钮即可将该模板删除。

**第五步：拷贝、粘贴表格模板**

在图18-3表格模板编辑区中选中一表格模板，单击“拷贝”、“粘贴”按钮可以实现应用内部或应用之间表格模板拷贝和粘贴的功能。

---

第十八章  数据库访问

### 18.1.2    记录体

记录体是用来建立数据库表格字段与KingSCADA变量之间的对应关系，即KingSCADA中的哪个变量值写到数据库中的哪个字段中去。

记录体配置过程如下：

**第一步：创建记录体**

在KingSCADA工程设计器的树型目录中选择“通用数据源”→“ODBC数据源”→“记录体”选项，在右侧编辑区中单击“新建”按钮弹出对话框，如图所示：

图18-4
记录体编辑对话框

**第二步：编辑记录体**

**记录体名称****：**设置记录体的名称，长度不超过32个字符且唯一。

**描述：**该记录体的注释信息，长度不超过64个字符。

**字段名：**数据库表格中的列名称，该名称要与数据库表格中的字段名称一致。

**绑定变量：**KingSCADA数据词典中变量的名称，单击按钮，在弹出的变量列表中选择变量。

**“添加字段”按钮：**当一个字段名和KingSCADA变量的对应关系设置完毕后，单击此按钮可以将该信息添加到字段列表框中。

**“删除字段”按钮：**在字段列表框中选中某行字段，单击此按钮可将该信息删除。

**“修改字段”按钮：**在字段列表框中选中某行字段，重新编辑完后，单击此按钮保存修改的内容。

**“上移”按钮：**在字段列表框中选中某行字段，单击此按钮可将该信息的位置向上移动。

**“下移”按钮：**在字段列表框中选中某行字段，单击此按钮可将该信息的位置向下移动。

**第三步：**设置完成后单击“确定”按钮，完成记录体的配置，该记录体会自动增加到记录体编辑区中，如图所示：

图18-5
记录体编辑区

**第四步：删除记录体**

在图18-5记录体编辑区中选中预删除的记录体，单击“删除”按钮即可将该记录体删除。

**第五步：拷贝、粘贴记录体**

在图18-5记录体编辑区中选中一记录体，单击“拷贝”、“粘贴”按钮可以实现应用内部或应用之间记录体拷贝和粘贴的功能。

---

第十八章  数据库访问

### 18.1.3    访问数据库过程

KingSCADA通过记录体方式访问数据库的操作过程如下：

---

第十八章  数据库访问

#### 18.1.3.1   创建数据源

在使用KingSCADA与数据库进行数据通讯之前，必须首先建立ODBC数据源。下面以Access数据库为例介绍如何建立数据源，

**第一步：**在数据库中新建一个数据库（文件名：mydb.mdb）。

**第二步：**在WINDOWS（以WINDOWS XP为例）控制面板中，双击管理工具中的“数据源（ODBC）”，出现ODBC数据源管理器如图所示：

图18-6
ODBC数据源管理器

***注意：上述是32******位操作系统创建ODBC******数据源方式，如果是64******为操作系统，需要利用C:\Windows\SysWOW64\odbcad32.exe******路径下创建ODBC******数据源，否则会连接失败***

**第三步：**选择“系统DSN”标签页，单击“添加”按钮，弹出“创建新数据源”对话框，如图所示：

图18-7
数据源类型选择窗口

**第四步：**选择所需的数据库类型（如：Microsoft Access Driver（\*.mdb）），单击“完成”按钮，弹出对话框如图所示：

图18-8
设置数据源信息窗口

在图18-8“数据源名”中输入新建的数据源名称（如mine），单击“选择”按钮，弹出对话框如图所示：

图18-9
选择连接的数据库

在此对话框中选择已建好的数据库，将数据库（即mydb.mdb）与数据源（mine）链接，单击“确定”按钮逐级返回。到此一个数据源建立完毕。

---

第十八章  数据库访问

#### 18.1.3.2   创建表格模板

创建表格模板过程如下：

**第一步：**在KingSCADA工程设计器的树型目录中选择“通用数据源”→“ODBC数据源”→“表格模板”选项→在右侧编辑区中单击“新建”按钮弹出创建表格模板对话框，在对话框中建立三个字段，如图所示：

图18-10
创建表格模板对话框

**第二步：**单击“确定”按钮完成表格模板的创建。

建立表格模板的目的是定义数据库表格的格式，在后面用到SQLCreatTable（）函数时以此格式在Access数据库中建立表格。

---

第十八章  数据库访问

#### 18.1.3.3   创建记录体

创建记录体过程如下：

**第一步：**在KingSCADA工程设计器的树型目录选择“通用数据源”→“ODBC数据源”→“记录体”选项→在右侧编辑区中单击“新建”按钮弹出创建记录体对话框。对话框设置如图所示：

图18-11
创建记录体对话框

记录体中定义了Access数据库表格字段与KingSCADA变量之间的对应关系，对应关系如下所示：

|  |  |
| --- | --- |
| **Access****数据库表格字段** | **KingSCADA****变量** |
| 日期字段 | \\local\$Date |
| 时间字段 | \\local\$Time |
| 液位值 | \\local\液位 |

 

即：将KingSCADA中\\local\$Date变量值写到Access数据库表格日期字段中；将\\local\$Time变量值写到Access数据库表格时间字段中；将\\local\液位变量值写到Access数据库表格液位值字段中；

**第二步：**单击“确定”按钮完成记录体的创建。

---

第十八章  数据库访问

#### 18.1.3.4   连接数据库

**第一步：**在KingSCADA工程设计器的数据词典中新建一内存整型变量：

变量名：DeviceID

变量类型：内存整型

**第二步：**新建一画面，名称为“数据库操作画面”，并在画面上添加一按钮，按钮文本为“数据库连接”，在按钮的弹起事件中输入如下命令语言，如图所示：

图18-12
数据库连接脚本

上述脚本的作用是使KingSCADA与mine数据源建立了连接（即与数据库. mdb建立了连接）。

在实际应用中将此命令写到工程设计器 →脚本
→系统脚本→应用程序脚本 →启动时中，即系统开始运行就连接到数据库上。

---

第十八章  数据库访问

#### 18.1.3.5   创建数据库表格

在数据库操作画面中添加一按钮，按钮文本为“创建数据库表格”，在按钮的弹起事件中输入如下命令语言，如图所示：

图18-13
创建数据库表格脚本

上述脚本的作用是以表格模板“Table1”的格式在数据库中建立名为“KingTable”的表格。在生成的KingTable表格中，将生成三个字段，字段名称分别为：日期，时间，液位值。每个字段的类型、长度及索引类型与表格模板“Table1”中的定义一致。

此脚本只需执行一次即可，如果表格模板有改动，需要先将数据库中的表格删除才能重新创建。

***注意：表格模板中设置的字段名称不能与数据中的关键字冲突（如：date******、time******等），否则会创建失败***

---

第十八章  数据库访问

#### 18.1.3.6   插入记录

在数据库操作画面中添加一按钮，按钮文本为“插入记录”，在按钮的弹起事件中输入如下命令语言，如图所示：

图18-14
插入记录脚本

上述脚本的作用是在表格KingTable中插入一个新的记录。在KingSCADA运行环境中按下此按钮后，KingSCADA会将Bind1中关联的KingSCADA变量的当前值插入到Access数据库表格“KingTable”中，从而生成一条记录，达到了将KingSCADA数据写到外部数据库中的目的。

---

第十八章  数据库访问

#### 18.1.3.7   查询记录

查询数据库中的记录可以通过SQL函数来实现，即SQLFirst、SQLNext、SQLPrev和SQLLast函数，下面将具体介绍操作过程。

**第一步：**用户如果需要将数据库中的数据调入KingSCADA来显示，需要另外建立一个记录体，此记录体的字段名称要和数据库表格中的字段名称一致，连接的变量类型与数据库中字段的类型一致。在工程设计器的数据词典中定义三个内存变量：

a、变量名：记录日期

变量类型：内存字符串

初始值：空

b、变量名：记录时间

变量类型：内存字符串

初始值：空

c、变量名：液位返回值

变量类型：内存实型

初始值：0

**第二步：**新建一画面，名称为“数据库查询画面”，并在画面上添加三个文本图素，在文本图素的“字符串输出”、“模拟量值输出”动画中分别连接变量\\local\记录日期、\\local\记录时间、\\local\液位返回值，用来显示查询出来的结果。

**第三步：**在工程设计器中定义一个记录体，记录体窗口属性设置，如图所示：

图18-15
记录体属性设置对话框

**第四步：**在画面中添加一按钮，按钮文本为“得到选择集”，在按钮的弹起事件中输入如下脚本，如图所示：

图18-16
记录查询脚本

此脚本的作用是：以记录体Bind2中定义的格式返回KingTable表格中第一条数据记录。

**第五步：**在画面上添加四个按钮，按钮属性设置如下：

a、按钮文本：第一条记录

“弹起时”动画连接：SQLFirst( DeviceID );

b、按钮文本：下一条记录

“弹起时”动画连接：SQLNext( DeviceID );

c、按钮文本：上一条记录

“弹起时”动画连接：SQLPrev( DeviceID );

d、按钮文本：最后一条记录

“弹起时”动画连接：SQLLast( DeviceID );

上述命令语言的作用分别为查询库数据中第一条记录、下一条记录、上一条记录和最后一条记录从而达到了数据查询的目的。

---

第十八章  数据库访问

## 18.2     数据集方式

数据集方式是指KingSCADA通过ADO（OLEDB或ODBC）接口方式与数据库交互，即动态插入

、更新、删除记录、获得符合条件的数据集合，可以通过报表、曲线或其它控件展示，或将数据集中的某个字段值赋给KingSCADA变量。

---

第十八章  数据库访问

### 18.2.1    访问数据库过程

KingSCADA无论是服务器还是C/S、B/S的客户端使用ODBC/OLEDB方式连接关系库，电脑上必须安装关系库对应的ODBC或者OLEDB驱动，一般操作系统会默认提供Access、SQL server驱动，其它关系库驱动需要用户手动安装。

注意：如果C/S、B/S客户端较多不想安装关系库驱动，可以使用18.2.2提供的方案

---

第十八章  数据库访问

#### 18.2.1.1   创建连接字符串

KingSCADA在存取数据之前必须先创建连接字符串，下面介绍创建连接字符串的方法

新建一个文本文档，将其后缀txt改为udl，注意：可能会出现一个警告，指出更改文件扩展名会导致文件变得不可用。忽略此警告

该文档鼠标右键选择打开方式，选择OLE DB Core
Services，数据连接属性对话框打开，显示以下选项卡：“提供程序”、“连接”、“高级”和“所有”。

在“提供程序”选项卡上选择要使用的数据库驱动，点击“下一步”跳转到“连接”选项卡

输入数据源、用户名及密码，点击“测试连接”，提示连接成功，点击确定完成连接配置

该文档鼠标右键选择打开方式，选择记事本，红色标注部分即为获得的连接字符串

注意：不同的关系库驱动获得连接字符串不同，只要按照上述步骤即可

---

第十八章  数据库访问

#### 18.2.1.2   查询数据库

1)       
在KingSCADA中新建一副画面，取名“DataQuery”；

2)       
在画面中添加一个报表图素（名称“Report1”），一个按钮（名称“Query”）；

3)       
在按钮的“左键按下”中添加脚本如下：

String ConnectStr,SqlStr;//定义两个字符串变量

String ConnectStr= "Provider=Microsoft.Jet.OLEDB.4.0;Data
Source=C:\Program Files\KingSCADA\My Projects\Data\failure.mdb;Persist Security
Info=False"

String SqlStr="select\*from
hisdatafileinform";//SQL语句，从表“hisdatafileinform”中选择数据

KDBGetDataset("Dataset1",
ConnectStr,SqlStr);// 此函数用于获得一个符合条件的数据集"Dataset1"，该方法从数据库查询一次之后就断开与数据库连接，适合不频繁与数据库连接的操作，如果需要频繁的访问数据库，该函数不合适，会导致数据库连接断开

Report1.SetDataset1("Dataset1");//清除掉报表图素中原有的数据，将符合条件的数据集"Dataset1"显示在Report1报表图素中，默认显示的起始单元格是报表图素中第一行第一列对应的单元格

点击之后，运行系统，点击“Query”可以看到查询出来的数据如下图：

查询结果

---

第十八章  数据库访问

#### 18.2.1.3   插入更新记录

在实际工程中对于设备的控制，我们需要在数据库中记录“设备的名称”，“开始时间”，“开始时温度”，“结束时间”，“结束时压力”等相关信息。需要在设备操作开始时写入“开始时间”、“设备名称”以及一些相关变量，在设备停止时需要在开始时写入的记录上补充上“结束时间”，“结束时压力”等相关信息。简言之，就是分两次记录完一条完整的数据。下面就是例子的具体制作过程。

1)    
新建一个Access数据库文件“EqManage”，并在里面建立一个表“EqManage”六个字段如下图：

2) 
在KingSCADA中新建一个工程“Equipment”，画面“control”，然后将建立的数据库放到工程目录下，例如本例中KingSCADA安装在C盘中则把数据库文件放到目录“C:\Program Files\KingSCADA\My Projects\Equipment\Equipment”下面。

3)    
在画面中添加一个报表图素，四个按钮，然后为按钮改名，最后布局如下：

4)    
定义四个画面变量“sb1kz”
bool型，默认值为FALSE；“sb2kz” bool型，默认值为FALSE；“NextXh1”，默认值为0；“NextXh1”，默认值为0。

5)    
添加脚本按钮“sb1
Start”中添加如下脚本：

string
ConnectStr,SqlStr,StartTime;

int MaxXh;

ConnectStr="Provider=Microsoft.Jet.OLEDB.4.0;Data
Source="+InfoAppDir()+"EqManage.mdb;Persist Security
Info=False";

SqlStr="SELECT
\* FROM EqManage order by xh Desc";//数据集依Xh为基准逆序排列

KDBGetDataset("Dataset1",
ConnectStr,SqlStr);

MaxXh=StrToInt(KDBGetCellValueByFieldId("Dataset1",
0, 0));//找到Xh最大的值，并转为整形

NextXh1=MaxXh+1; //在Xh最大的数据MaxXh后面添加一条记录，新纪录的Xh为NextXh1=MaxXh+1

StartTime=GetSystemtimeDateString()+"
"+GetSystemtimeTimeString(1);

SqlStr="Insert
into Eqmanage
values('"+NextXh1+"','sb1','"+StartTime+"','','18','')";

KDBExecuteStatement(ConnectStr,
SqlStr);

//找到添加的Xh为NextXh1的记录并显示在报表中

SqlStr="SELECT
\* FROM EqManage where xh="+NextXh1+"";

KDBGetDataset("Dataset1",
ConnectStr,SqlStr);

Report1.SetDataset1("Dataset1");

KDBClearDataset("Dataset1");//清空数据集

b1kz=1;//使能sb1Stop按钮

 

按钮“sb1 Stop”中添加如下脚本：

        string
ConnectStr,SqlStr,StopTime;

StopTime=GetSystemtimeDateString()+"
"+GetSystemtimeTimeString(1);

ConnectStr="Provider=Microsoft.Jet.OLEDB.4.0;Data
Source="+InfoAppDir()+"EqManage.mdb;Persist Security
Info=False";

SqlStr="UPDATE Eqmanage SET StopTime=
'"+StopTime+"',StopPressure = '99' where xh =
"+NextXh1+"";//更新新添加的记录

KDBExecuteStatement(ConnectStr, SqlStr);

 

//将更新后的数据显示在报表中

SqlStr="SELECT \* FROM EqManage where
xh="+NextXh1+"";

KDBGetDataset("Dataset1", ConnectStr,SqlStr);

Report1.SetDataset1("Dataset1");

KDBClearDataset("Dataset1");

 

sb1kz=0;//使能按钮，令sb1 Start使能

 

按钮“sb2 Start”中添加如下脚本：

      string ConnectStr,SqlStr,StartTime;

int MaxXh;

ConnectStr="Provider=Microsoft.Jet.OLEDB.4.0;Data
Source="+InfoAppDir()+"EqManage.mdb;Persist Security Info=False";

SqlStr="SELECT
\* FROM EqManage order by xh Desc";//数据集依Xh为基准逆序排列

KDBGetDataset("Dataset1",
ConnectStr,SqlStr);

MaxXh=StrToInt(KDBGetCellValueByFieldId("Dataset1",
0, 0));//找到Xh最大的值，并转为整形

 

//在Xh最大的数据MaxXh后面添加一条记录，新纪录的Xh为NextXh1=MaxXh+1

NextXh2=MaxXh+1;

StartTime=GetSystemtimeDateString()+"
"+GetSystemtimeTimeString(1);

SqlStr="Insert
into Eqmanage
values('"+NextXh2+"','sb2','"+StartTime+"','','28','')";

KDBExecuteStatement(ConnectStr,
SqlStr);

 

//找到添加的Xh为NextXh1的记录并显示在报表中

SqlStr="SELECT
\* FROM EqManage where xh="+NextXh2+"";

KDBGetDataset("Dataset1",
ConnectStr,SqlStr);

Report1.SetDataset1("Dataset1");

KDBClearDataset("Dataset1");//清空数据集

 

sb2kz=1;//使能“sb2 stop” 按钮

 

按钮“sb2 Stop”中添加如下脚本：

     string ConnectStr,SqlStr,StopTime;

StopTime=GetSystemtimeDateString()+"
"+GetSystemtimeTimeString(1);

ConnectStr="Provider=Microsoft.Jet.OLEDB.4.0;Data
Source="+InfoAppDir()+"EqManage.mdb;Persist Security
Info=False";

SqlStr="UPDATE Eqmanage SET StopTime=
'"+StopTime+"',StopPressure = '88' where xh =
"+NextXh2+"";//更新新添加的记录

KDBExecuteStatement(ConnectStr,
SqlStr);

 

//将更新后的数据显示在报表中

SqlStr="SELECT
\* FROM EqManage where xh="+NextXh2+"";

KDBGetDataset("Dataset1",
ConnectStr,SqlStr);

Report1.SetDataset1("Dataset1");

KDBClearDataset("Dataset1");

sb2kz=0;//使能按钮，令按钮“sb Start“使能

 

6)    
运行使用。运行后的画面如下：

        开始时只有“sb1Start”与“sb2Start”可用，另外两个按钮颜色处于灰度

         当按下“sb1Start”后，“sb1Stop”使能，同时“sb1Start”变为灰度。报表中显示写入的字段值，没有写入的字段值为空

        当按下“sb1Stop”后，“sb1Start”使能，同时“sb1Start”变为灰度。报表中显示更新后的数据。

    
当“sb1Start”与“sb2Start”先后按下后，再按相应的停止按钮对相互的数据更新互不影响。这是

因为在脚本中我们将这两个设备的写入的索引值（对应数据库中的Xh）分别用两个画面变量

“NextXh1”，“NextXh2”来标记，更新数据的时候是以这两个变量来寻找记录的，所以设备1、

设备2之间的启停互不影响数据的更新。

---

第十八章  数据库访问

### 18.2.2    KingSCADA服务端代理客户端查询关系库

KingSCADA服务端提供可代理客户端查询关系库功能，只需要服务端电脑中存在关系库驱动，客户端电脑可以不用再安装关系库驱动。这种方式尤为适用B/S模式

KS代理服务器可以为多个（配置多个IP及端口）。若公网发布时，则对应监听端口需要在公网上做映射，同时将对应的公网IP及端口填写到配置文件中。

    
具体配置如下：

1）
配置服务端和客户端的kxConfig.ini文件

需要配置的字段说明如下：

    
KSSQLServerIPNum=2     //配置代理的KS服务端的IP和端口对的个数

KSSQLServerIP1=172.16.2.113  //该字段仅客户端使用，用于指定代理的KS服务端的IP。

KSSQLNetworkListenPort1=19888   //该字段客户端和服务器都需要使用，用于指定代理的KS服务端的监听端口

KSSQLServerIP2=119.57.151.179  //该字段仅客户端使用，用于指定代理的KS服务端的IP。

KSSQLNetworkListenPort2=19888  //该字段客户端和服务器都需要使用，用于指定代理的KS服务端的监听端口

 

KSSQLTimeout=60000          
//该字段仅客户端使用，用于指定与代理的KS服务端的连接超时时间。

 

EnableKSSQLMode=0    //该字段表示是否启用代理，启用代理时客户端该字段设置为1，代理的KS服务端设置为0，不启用代理时都设置为0。

     EnableKSSQLModeInServer=0  //该字段表示是否监听端口，启用代理时代理的KS服务端必须设置为1，客户端设置为0，不启用代理时都设置为0。

 

            
2）作为代理的KS服务端需要安装客户端所要访问关系库的驱动。

3）客户端工程对于关系库的操作和以前的相同。

   
**注意**：对于B/S客户端，如果代理的KS服务端和web服务器是同一台机器，web发布的时候需要依据客户端的方式修改配置文件，然后发布工程，然后再依据代理的KS服务端的方式修改配置文件

【例】

1.      
电脑A做服务器，IP为172.16.14.243，电脑B为客户端，IP为172.16.14.175；

2.       服务端所在电脑A中SCADA的安装文件的bin文件夹下KxConfig.ini中修改EnableKSSQLModeInServer的值为1即可：

KSSQLServerIPNum=1

KSSQLServerIP=127.0.0.1 

KSSQLTimeout=60000         

KSSQLNetworkListenPort=19888  

EnableKSSQLMode=0

EnableKSSQLModeInServer=1

3.      
A中服务器工程通过ODBC方式连接Oracle关系库，建ODBC数据源OracleAlarm；

4.      
新建工程sever1，新建画面test1，中添加报表Report1，添加按钮，按钮脚本中填写：

string ConnectStr;

string SqlStr;

ConnectStr="DSN=OracleAlarm;UID=SYSTEM;PWD=Kingview1;DBQ=ORCL;";

SqlStr="select \* from alarm";

/\*bool\*/ KDBGetDataset("Dataset",
ConnectStr, SqlStr);

Report1.SetDataset1("Dataset");

5.      
新建客户端工程client，添加server1，画面与服务端工程画面相同；

6.      
将客户端工程client拷贝到B电脑上，并配置B电脑中SCADA的安装文件下KxConfig.ini文件为：

KSSQLServerIPNum=1

 

KSSQLServerIP=172.16.14.243 

KSSQLTimeout=60000     

KSSQLNetworkListenPort=19888  

EnableKSSQLMode=1

EnableKSSQLModeInServer=0

7.      
运行客户端工程，点击按钮可直接查询Oracle中alarm表中的内容；

8.      
若不想使用代理方式查询，则配置服务端与客户端的EnableKSSQLMode=0即可；

注：除了通用的数据集函数，KDBKHRawData、KDBKHSampleData1、KDBKHSampleData2三个函数也增加了服务端代理查询功能。

---

第十八章  数据库访问

### 18.2.3    数据集方式应用实例

参见附录一 应用例程中的数据集方式应用例程章节。