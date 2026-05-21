数据集函数应用例程

# 数据集函数应用例程

具体例程工程参见：安装目录KingSCADA3.5x\Eample\数据集函数应用例程

---

数据集函数应用例程

## 应用背景

在一些大型项目中KingSCADA一般要查询大批量的数据，使用记录体的方式需要建很多KingSCADA变量与之对应，给查询和使用带来不便，使用数据集方式满足这类需求，本例程详细介绍了26个数据集函数的使用方法

本例程关系库使用SQL Server2005，其它关系库这里不做详述，原理都一样。

---

数据集函数应用例程

## 例程

---

数据集函数应用例程

### 创建连接字符串

  具体方法参见18.2.1.1章节创建连接字符串。

---

数据集函数应用例程

### 查询数据库

1)    创建画面，画面中放置ListvView控件、报表、按钮、文本框、组合框、单选框、日期时间

2)    创建画面变量，具体如下

|  |  |  |  |  |
| --- | --- | --- | --- | --- |
| 变量名 | 类型 | 默认值 | 描述 | |
| ConnectStr | String | Provider=SQLNCLI.1;Password=sa;Persist Security Info=True;User ID=sa;Initial Catalog=数据库1;Data Source=172.16.3.200 | | 连接数据库字符串 |
| SqlStr | String | select \* from Student | | SQL语句 |
| ErrorMessage | String | 0 | | 输出错误信息 |
| ReturnValue | bool | false | | 函数返回值 |
| DataSet1 | String | 0 | | 获得的数据集 |
| FieldId | Int | 2 | | 获取单元格列号 |
| DataSetContent | String | 0 | | 获得数据集当前行中指定列的内容 |
| DataSetValue0 | String | 0 | | 数据集第0行Fieldid列值 |
| DataSetValue2 | String | 0 | | 数据集第2行Fieldid列值 |
| DataSetValue4 | String | 0 | | 数据集第4行Fieldid列值 |
| DataSetValue6 | String | 0 | | 数据集第6行Fieldid列值 |
| DataSetValue7 | String | 0 | | 数据集第7行Fieldid列值 |
| DataSetValue | String | 0 | | 数据集某行Fieldid列值 |
|  |  |  |  |  |

3)    在“连接数据库获得数据集”按钮单击事件脚本中添加如下脚本：

方法1：

string strSqlFilePath;

strSqlFilePath="e:\text.txt";//存放SQL语句文件路径

bool a;

a = /\*bool\*/ KDBStatusTest(ConnectStr); //判断连接数据库是否成功，成功执行SQL语句获得数据集，否则；输出错误信息连接数据库失败

if(a == true)

{

ReturnValue=KDBGetDataset(DataSet1,ConnectStr,SqlStr);

 //ReturnValue=/\*bool\*/ KDBExecuteStatement(ConnectStr,
SqlStr);

 //ReturnValue=/\*bool\*/ KDBSQLExecuteFromFile(Dataset1,ConnectStr,strSqlFilePath);//实际应用过程中3个函数均可以使用

if(ReturnValue == false)//判断获得数据集是否成功，失败输出错误信息，成功用listview控件展示

      {

    
ErrorMessage="获得数据集失败";

   
}

    
else

   
{

      
ListView.Control.SetDataset(DataSet1);

      
ErrorMessage="获得数据集成功";

   
}

}

else

{

ErrorMessage="连接数据库失败";

}

方法2：

bool a;

a= /\*bool\*/ KDBGetConnectID("strConnectID", ConnectStr);//判断连接数据库是否成功，成功执行SQL语句获得数据集，否则；输出错误信息连接数据库失败

if(a == true)

{

    ReturnValue=/\*bool\*/ KDBGetDataset1("Dataset1","strConnectID",SqlStr);

    //ReturnValue=/\*bool\*/ KDBExecuteStatement1("strConnectID",SqlStr);

//ReturnValue=/\*bool\*/ KDBSQLExecuteFromFile(Dataset1,ConnectStr,strSqlFilePath);//实际应用过程中3个函数均可以使用

    if(ReturnValue ==
false)//判断获得数据集是否成功，失败输出错误信息，成功用listview控件展示

      {

     ErrorMessage="获得数据集失败";

    }

     else

    {

  ListView.Control.SetDataset(DataSet1);

     ErrorMessage="获得数据集成功";

    }

}

else

{

ErrorMessage="连接数据库失败";

}

2种方法均可实现，实际应用过程中使用那种方法合适，参见函数帮助文档中KDBGetConnectID说明，如果使用方法2时，在画面关闭是脚本中执行断开连接操作，/\*bool\*/ KDBDisConnect("strConnectID");

点击之后，运行系统，点击“连接数据库获得数据集”可以看到查询出来的数据如下图

---

数据集函数应用例程

### 查询数据库某字段内容

“获得数据集字段内容”按钮脚本内容如下：

//脚本中用CreatArray函数创建了一个数组，用于存放获得的数据集的内容

string
StrArray1;

/\*int\*/
CreatArray(StrArray1, 3, 10);//创建类型为string，长度为10的数组DataSetContent

KDBGetDataset("DataSet2",ConnectStr,SqlStr);

int cols = /\*int\*/ KDBGetDatasetCols("DataSet2");//获得数据集的总列数

int Rows = /\*int\*/ KDBGetDatasetRows("DataSet2");//获得数据集的总行数

for(int i=0; i<=Rows; i++)

{

     DataSetContent=KDBGetCurRowValueByFieldId("DataSet2",FieldId);//获得当前行FieldId列单元格的内容，默认第一行

     //DataSetContent=/\*string\*/
KDBGetCurRowValueByFieldName("Dataset2",
string FieldName);//也可以通过列名称获得单元格内容

     /\*int\*/
SetArrayElement(StrArray1, i, DataSetContent);//将获得的单元格值赋给数组第i个元素

     bool
a = /\*bool\*/ KDBDatasetMoveNext("DataSet2");//将获得的数据集移动到下一行，用于循环

  if(a==false)//用于判断，返回值为false说明目前行是数据集的最后一行

  {

       /\*bool\*/ KDBDatasetMoveFirst("DataSet2");

       /\*bool\*/ KDBDatasetMoveLast("DataSet2");

       /\*bool\*/ KDBDatasetMovePrev("DataSet2");

       DataSetValue=/\*string\*/ KDBGetCurRowValueByFieldId("DataSet2",FieldId);//当前行为数据集倒数第二行，返回值与DataSetValue6一致

     }    

}

//得到数组元素值存放与变量中

DataSetValue0 = /\*string\*/ GetArrayElement(StrArray1, 0);

DataSetValue2 = /\*string\*/ GetArrayElement(StrArray1, 2);

DataSetValue4 = /\*string\*/ GetArrayElement(StrArray1, 4);

DataSetValue6 = /\*string\*/ GetArrayElement(StrArray1,6);

DataSetValue7 = /\*string\*/ GetArrayElement(StrArray1, 7);

 

if(DataSetValue6==/\*string\*/ KDBGetCellValueByFieldId("DataSet2",6, FieldId))

//if(DataSetValue6==/\*string\*/ /\*string\*/
KDBGetCellValueByFieldName("DataSet2",6,
FieldName))

{

     ErrorMessage="验证一致";

}

脚本中创建了一个数组，用于存放数据集内容，并将数组元素值分别赋给画面变量，执行结果如下：

---

数据集函数应用例程

### 查询变量原始数据

有些变量变化很少或非常缓慢，很长时间里，也只能得到很少的有效数据，对历史库进行采样查询时，如果采样间隔太小，会得到很多重复的数据，如果采样间隔太大，会遗漏掉有效数据，所以提供得到原始数据数据集的函数，下面介绍查询KS历史库原始数据的三个函数，查询工业库的原始数据方法与KS一样，本例程不做介绍

“获得某1变量的原始数据”脚本内容如下：

//定义字符串变量，便于数据集函数参数的使用

string
strStartTime,strEndTime;//查询的起始时间、终止时间

string
strTagName;//待查询的变量

string
strRowStatistic;//行列统计

string
TimeStampFormat;//时间戳

int iTimeStampFormat,iTagNamesType;

strStartTime=UIDateTime1.Value;

strEndTime=UIDateTime2.Value;//把日期时间的值赋给定义的起始时间、终止时间

strTagName=待查询变量.Text;//待查询变量文本框内容赋给strTagName

strRowStatistic= 行列统计.SelectedText;//单选框所选择的内容赋给strRowStatistic

TimeStampFormat=时间戳格式.GetCurrentText();

iTimeStampFormat = /\*int\*/ StrToInt(TimeStampFormat);//时间戳格式组合框内容赋给TimeStampFormat并转换为整型便于函数的使用

/\*bool\*/ KDBKSRawData("DataSet1",strTagName,strStartTime,strEndTime,iTimeStampFormat,strRowStatistic);

Report1.SetDataset1("DataSet1");

运行态下输入待查询的变量，起始时间、终止时间，选择时间戳格式、行列统计（可以为空），执行按钮脚本后就可得到结果

---

数据集函数应用例程

### 查询变量特定时刻的原始数据

脚本内容如下：

//定义字符串变量，便于数据集函数参数的使用

string
strTagName;//待查询的变量或变量组

string
strRowStatistic,
strColStatistic;//行列统计

string
TimeStampFormat;//时间戳

string
TagNamesType;//待查询的变量组格式类型，1为变量组，2为从CSV文件中获取

string
strHistTime;//历史时刻时间字符串

string
strTagStatisticRange;//变量统计范围

int iTimeStampFormat,iTagNamesType;

strTagName=待查询变量.Text;//待查询变量文本框内容赋给strTagName

strRowStatistic= strColStatistic =行列统计.SelectedText;//单选框所选择的内容赋给strRowStatistic

TimeStampFormat=时间戳格式.GetCurrentText();

iTimeStampFormat = /\*int\*/ StrToInt(TimeStampFormat);//时间戳格式组合框内容赋给TimeStampFormat并转换为整型便于函数的使用

TagNamesType=变量格式类型.GetCurrentText();

iTagNamesType = /\*int\*/
StrToInt(TagNamesType);//变量格式类型组合框内容赋给TagNamesType并转换为整型便于函数的使用

strHistTime=时间字符串.Text;

strTagStatisticRange=变量统计范围.Text;

/\*bool\*/ KDBKSSampleData1("Dataset2",iTagNamesType, strTagName,strHistTime,iTimeStampFormat,strColStatistic, strTagStatisticRange);

Report1.SetDataset1("Dataset2");

 

运行态下输入待查询变量格式类型、待查询变量、某一历史时刻、时间戳格式、列统计、变量统计范围，执行结果如下：

---

数据集函数应用例程

### 查询变量采样值

脚本内容如下：

//定义字符串变量，便于数据集函数参数的使用

string
strStartTime,strEndTime;//查询的起始时间、终止时间

string
strTagName;//待查询的变量或变量组

string
strRowStatistic,
strColStatistic;//行列统计

string
TimeStampFormat;//时间戳

string
TagNamesType;//待查询的变量组格式类型，1为变量组，2为从CSV文件中获取

string
strTagStatisticRange;//变量统计范围

int iTimeStampFormat,iTagNamesType;

strStartTime=UIDateTime1.Value;

strEndTime=UIDateTime2.Value;//把日期时间的值赋给定义的起始时间、终止时间

strTagName=待查询变量.Text;//待查询变量文本框内容赋给strTagName

strRowStatistic= strColStatistic =行列统计.SelectedText;//单选框所选择的内容赋给strRowStatistic

TimeStampFormat=时间戳格式.GetCurrentText();

iTimeStampFormat = /\*int\*/ StrToInt(TimeStampFormat);//时间戳格式组合框内容赋给TimeStampFormat并转换为整型便于函数的使用

TagNamesType=变量格式类型.GetCurrentText();

iTagNamesType = /\*int\*/
StrToInt(TagNamesType);//变量格式类型组合框内容赋给TagNamesType并转换为整型便于函数的使用

strTagStatisticRange=变量统计范围.Text;

bool a =/\*bool\*/ KDBKSSampleData2("Dataset3",iTagNamesType, strTagName,strStartTime,strEndTime,10,iTimeStampFormat,strRowStatistic,strColStatistic,strTagStatisticRange);

Report1.SetDataset1("Dataset3");

脚本中设置采样间隔为10毫秒

实际应用中可以修改输入参数，获得不同的数据集合