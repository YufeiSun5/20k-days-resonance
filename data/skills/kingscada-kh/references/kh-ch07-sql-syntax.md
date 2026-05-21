第7章 SQL语法

# 第7章     SQL语法

说明

 

 

在本章节中所使用的语法记号是BNF(“Backus Naur
Form”或“Backus
Normal Form”)的一个扩展版本。

在这个国际标准中使用的BNF版本使用如下的符号：

::= 定义操作符。它用于产生式当中用于分隔元素和定义该元素的规则。被定义的规则出现在此操作符的左边而规则式出现在它的右边。

[]  方括号表示一个规则定义式当中的可选元素。出现在该括号当中的部分既可以显式指明也可以省略掉。

{} 花括号用于规则定义式当中对元素进行分组。出现在花括号当中的部分必须被显式指明。

|  选择操作符。这个竖线符指明了出现在该符号右边的部分与左边部分是互相可替换的。如果竖线出现的位置没有被花括号或者方括号括起来，它指出这个产生式规则定义的元素的一个完全可替换性。如果竖线出现的位置有被花括号或者方括号括起来，它指出在这对花括号或方括号之间的内容的可替换性。

… 省略号指明了它作用于公式中的元素可以重复出现任意次数。如果省略号紧跟着一个闭花括号“}”出现，那么它作用于包含在这个闭括号和相对应的开括号”{”之间的部分。如果一个省略号出现在任意其他元素之后，那么它仅作用于那个元素。

空格符用于分隔语法元素。多个空格符和换行符被认为是一个单个的空白符。

成对的花括号和方括号可以被嵌套任意次，并且选择操作符可以在这种嵌套的任意深度中出现。

Copyright
2013-2016

---

第7章 SQL语法

## 7.1     了解KDB-SQL

Copyright
2013-2016

---

第7章 SQL语法

### 7.1.1  SQL简介

结构化查询语言（Structured Query
Language，SQL）提供了一套用来输入、查看和更改关系数据库内容的命令，是关系数据库中使用得最广泛的工具。尽管SQL被称为结构化查询语言，但该语言的功能不仅仅限于数据查询，而是涵盖了数据库系统从设计到运行、维护的各个方面。SQL的主要功能包括：

●     
数据定义（Data
Definition）定义数据存放的结构，以及数据项之间的关系

●     
数据检索（Data
Retrieval）从数据库中检索数据，并使用这些数据

●     
数据操纵（Data
Manipulation）增加、修改或删除数据库中的数据

尽管SQL语言拥有一些引人注目的特性而且易于使用，它所提供的最大优点还在于它在数据库厂家之间的广泛应用。SQL-92是国际标准化组织（ISO）和美国国家标准协会（ANSI）共同通过的一项SQL标准。SQL-92标准是当今数据库应用领域流行最广泛的标准，也是当今关系型数据库所支持的基本标准之一。最新的标准还有SQL99。Microsoft
SQL Server所使用的SQL语言规范为Transaction-SQL，简称为T-SQL。T-SQL是在兼容SQL-92的基础上添加了大量的扩展语法。

Copyright
2013-2016

---

第7章 SQL语法

### 7.1.2       KDB-SQL简介

KDB-SQL是亚控公司工业实时数据库产品所使用的SQL语言规范，主要参照了SQL-92标准。KDB-SQL语言主要是用来检索数据库中的数据，用来显示，分析及做报表等功能。

Copyright
2013-2016

---

第7章 SQL语法

### 7.1.3  KDB-SQL句法

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.3.1 分大小写

在KDB-SQL中，关键字以及数据库对象名都是不区分大小写的。换句话说就是，语言解释程序不在大写字母和小写字母编写的内容之间作区分。

 

B 例

下面的语句显示了查询工业实时数据库系统表Realtime中存储的实时数据信息。

SELECT \*  FROM realtime

和这条语句：

SELECT \*  FROM REALTIME

以及这样的语句：

Select \*   FROM RealTime

都是相同的。用户可以用在视觉上那个最吸引自己的方式编写SQL代码，各种方式之间没有区别。

 

 

 

 

 

 

 

 

 

B 例

  
下面的语句显示了查询实时数据表中的数据变量名和数据采集时间

   
SELECT tagname, datatime

   
FROM Realtime

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.3.2 空白

在KDB-SQL中，空白是被忽略的。就查询中通过空白来分隔的独立的元素而言，工业实时数据库将要对它们作语法分析。没有一种限制要求把语句中的全部元素都放置在单独一行上，使用制表符来缩进代码部分或在每一个子句结束后键入一行来填充。

 

B 例如下面的语句全都是一样的：

      
SELECT \* FROM Realtime

      
--------------------------------------

SELECT \*

      
FROM Realtime

      
--------------------------------------

 SELECT

 \*

      FROM

      Realtime

只要在语句的各个元素之间存在一些空白，KDB-SQL就不考虑语句的分隔方式。很明显，下面的语句是无效的：

SELECT \* FROMRealtime

本手册中，按照约定将会把KDB-SQL查询的每个子句都单放一行，所以，有若干子句的SELECT语句会像下面这样：

 

B 例：

     SELECT \*

      FROM Tag

       
WHERE SourceAddress = ‘OPC\_Simudevice.i1Val.i1Val0’

       
ORDER BY tagId

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.3.3 嵌套

在KDB-SQL中还提供了查询嵌套。也就是说，查询可以嵌入其他查询中，输出结果将用来代替嵌入式函数调用或查询。嵌套是用KDB-SQL编写代码的基本性能，它允许编写功能极为强大的查询。

有两种类型的元素可以嵌入SQL查询中：其他SQL查询和函数调用。当查询嵌入到SQL查询中时，它称为子查询。关于子查询会在后面章节中单独说明。嵌套的原理还适用于函数调用。如果两个函数将要应用到单个表达式中，其中的一个函数调用将嵌入到另一个之中。

 

B 例如剪裁字符串右侧的空白并将其中所有的字母转换成大写字母

    SELECT RTRIM(UPPER(‘Example string  ’))

   
---------------------------------------------------------------

       
EXAMPLE STRING

其中，对UPPER()函数的调用嵌入在对RTRIM()函数的调用中。RTRIM()函数用来将所有的空白从字符串尾部删除，UPPER()函数将字符串中所有的小写字母都转换成大写字母。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.3.4 引用字符串

字符串在KDB-SQL语言中使用时，它们被包围在单引号中。例如，如果希望将一列中的某个值与某个字符串或另一列中的某个值作比较：

 

B 例：

     
SELECT \*

     
FROM Realtime

  WHERE tagname = ‘Tempvalue’

包围单词Tempvalue的单引号表明了tagname列中的值应当与单词Tempvalue比较。如果表达式的结构像这样：

  SELECT \*

  FROM Realtime

  WHERE tagname = Tempvalue

则是将Tempvalue作为一列，而非字符串。tagname列中的值将与Tempvalue列中的值进行比较。

如果列中包含了数字，则不使用引号。例如将数字数据类型列中的值与下面这样的数字比较：

 

B 例：

  SELECT \*

  FROM Realtime

  WHERE dataquality = 192

当希望使用包含了单引号的字符串时将会发生什么呢？你必须“避开”它，以便不会将它当作字符串的结尾。

 

B 例：

  SELECT \*

  FROM Realtime

  WHERE tagname = ‘container’s temperature’

  这种结构无效，因为KDB-SQL解释程序将假定被比较字符串是container，而s temperature则仅仅是字符串过渡状态中的一个片段，并且将会返回错误。此时你需要在一行上用两个单独的引号（’’）来表示名称中的单引号是作比较的字符串的一部分，而不是结束字符串的字符。

  SELECT \*

  FROM Realtime

  WHERE tagname = ‘container’’s temperature’

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.3.5 括号

KDB-SQL中标准组合运算符是括号。它们用来建立数学表达式和复杂对比中的优先顺序，从查询的剩余部分中分开子查询。

 

例：包含了子查询的查询

   SELECT \*

   FROM Collector

   WHERE CollectorName IN

       (SELECT CollectorName

        FROM Tag)

可以看到，括号用来将子查询从查询的剩余部分分隔开来。子查询首先被评估，产生的结果用于查询的剩余部分中。同理，括号用在数学表达式中，它们的效果与在代数方程式中的效果相同，确保了表达式的一个部分在另一个部分之前评估。括号中项目的评估顺序在KDB-SQL中和在代数中的顺序相同。内部括号在外部括号之前评估。当同级优先顺序上存在多组括号时，工业实时数据库中内建的查询优化程序将决定表达式被评估的顺序。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.3.6 语句列表

KDB-SQL支持以分号（；）结束符标记的多个语句的顺序表。语句和分号之间可以用空白符填充。例如，对于查找表TABLE1和TABLE2，可写成如下形式：

SELECT \* FROM TABLE1；

SELECT \* FROM TABLE2

这样系统以批处理方式执行这两条语句。当然也可以写成

SELECT \* FROM TABLE1

SELECT \* FROM TABLE2

这时系统执行完语句1后，不会自动去执行语句2，而是等待要求执行语句2的命令。

Copyright
2013-2016

---

第7章 SQL语法

### 7.1.4  KDB-SQL语法

KDB-SQL具有一些大多数语句都使用或受之影响的语法元素：

● 标识符  

诸如表、列等对象的名称

● 数据类型

定义数据对象（如列）所包含的数据类型

●    
函数

语法元素，它可以有零个、一个或多个输入值，并返回一个标量值或表格形式的值的集合

● 表达式

是工业实时数据库可解析为单个值的语法单元

● 运算符

与一个或多个简单表达式一起使用构造一个更为复杂的表达式

● 注释

        插入到 KDB-SQL 语句或脚本中解释语句作用的文本，不执行注释。

● 保留关键字

保留下来由工业实时数据库使用的词，数据库中的对象名不应使用这些字词。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.4.1 标识符

工业实时数据库对象的名称被看成是该对象的标识符。表、列以及索引都有标识符。对象标识符是在定义对象时创建的，标识符随后用于引用该对象。

标识符有两类：

● 常规标识符

符合标识符的格式规则。在KDB-SQL语句中使用常规标识符时不用将其分隔。

 

B 例：

        SELECT \*

        FROM Realtime

        WHERE TagName =
‘OPC\_Simudevice.i1Val.i1Val0’

● 分隔标识符

包含在双引号（“”）或者方括号（[]）内。符合标识符格式规则的标识符可以分隔，也可以不分隔。

 

   B 例：

      SELECT \*

      FROM [Realtime]

      WHERE [TagName] =
‘OPC\_Simudevice.i1Val.i1Val0’

在KDB-SQL语句中，对不符合所有标识符规则的标识符必须进行分隔。

 

   B 例：

      SELECT \*

      FROM [My Table]  ---------------标识符My Table中包含一个空格，且使用了TABLE关键字

      WHERE [VALUES] = 12  --------标识符VALUES为保留关键字

上面语句中的My Table以及VALUES都不符合标识符规则，所以如果在需要使用时，需要用分隔标识符分隔。

Copyright
2013-2016

---

第7章 SQL语法

##### 7.1.4.1.1   标识符规则

l        
常规标识符规则

在工业实时数据库中，常规标识符的格式规则是：

1.        
第一个字符必须是下列字符之一：

n        
Unicode 标准 2.0 所定义的字母。Unicode 中定义的字母包括拉丁字母 a-z 和
A-Z，以及来自其它语言的字母字符。

n        
下划线（\_）或者数字符号（#）。

n        
后续字符可以是：

n        
Unicode 标准 2.0 所定义的字母。

n        
来自基本拉丁字母或其它国家/地区脚本的十进制数字。

n        
at符号（@）、美元符号（$）、数字符号（#）或下划线（\_）。

2.        
标识符不能是KDB-SQL的保留关键字。

3.        
不允许嵌入空格或其它特殊符号。

l        
分隔标识符规则

 符合常规标识符格式规则的标识符可以使用分隔符，也可以不使用分隔符。不符合标识符格式规则的标识符必须使用分隔符。在KDB-SQL中，如果标识符包含特殊字符，则应该使用分隔标识符。KDB-SQL中的特殊字符包含：

|  |  |  |
| --- | --- | --- |
| 百分号（%） | and号（&） | 冒号（：） |
| 问号（？） | 大于号（>） | 小于号（<） |
| 左圆括号（（） | 右圆括号（）） | 撇号（’） |
| 加号（+） | 减号（-） | 等号（=） |
| 斜杠（/） | 星号（\*） | 逗号（，） |
| 句号（.） | 分号（；） | 竖线（|） |
| 左括号（[） | 右括号（]） | 双撇（”） |

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.4.2 数据类型

包含数据的对象都具有一个相关的数据类型，此数据类型定义对象所能包含的数据种类（字符、整数等）。在工业实时数据库中，每个列、表达式都有一个相关的数据类型。KDB-SQL的数据类型参考了SQL-92和T-SQL及MySQL等数据库的相关方案，但并不是兼容并包，而是各有取舍。

KDB-SQL含有以下基本数据类型：

|  |  |  |  |  |
| --- | --- | --- | --- | --- |
| bigint | binary | bit | blob | char |
| date | decimal | float | int | image |
| nchar | nclob | ntext | numeric | real |
| smallint | text | time | timestamp | tinyint |
| varchar | variant |  |  |  |

存储在工业实时数据库中的所有数据必须与上述这些基本数据类型之一相兼容。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.4.3 函数

工业实时数据库具有执行某些运算的内置函数。函数分类有：

● 聚合函数

     执行的操作是将多个值合并为一个值。例如COUNT、SUM、MAX和MIN

● 日期和时间函数

     操作timestamp、date及time值

● 数学函数

     执行三角、几何和其他数学运算

● 字符串函数

     操作char、varchar以及nchar值

 

在KDB-SQL中，函数可用于或包含在：

● 使用SELECT语句查询的选择列表中，返回一个值。

   B 例：

      SELECT COUNT(\*)

      FROM Realtime

●     
SELECT语句的WHERE子句搜索条件中，以限制合乎查询条件的行。

 

  B 例：

     SELECT TagName

     FROM Tag

     WHERE CollectorType >

        (SELECT
MIN(CollectorType)

         FROM Collector)

● 任一表达式中

使用函数时总是带有圆括号，即使没有参数也是如此。函数可以嵌套（一个函数用于另一个函数的内部）

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.4.4 表达式

表达式是标识符、值和运算符的组合，工业实时数据库可以对其求值以得到结果。在KDB-SQL中，表达式可以是常量、函数、列名以及子查询，还可以用运算符对这些实体进行组合以生成表达式。访问或更改数据时，可在多个不同的位置使用数据。例如可以将表达式用作要搜索的数据的一部分（在查询中）或查找满足一组条件的表达式的搜索条件。

 

B 例：

   SELECT TagName  ------对于结果集的每一行，TagName都具有一个值，它是一个表达式

   FROM Realtime

表达式还可以是计算，如(TagId + 15)。

 

B 例：

   SELECT TagName, CollectorName, SourceAddress, TagId+15

   FROM Tag

   其中，TagName、CollectorName、SourceAddress、TagId +15均为表达式。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.4.5 运算符

运算符是一种符号，用来指定要在一个或多个表达式中执行的操作。在KDB-SQL中使用下列几类运算符：

● 算术运算符

 算术运算符在两个表达式上执行数学运算，这两个表达式可以是数字数据类型分类的任何数据类型。

|  |  |
| --- | --- |
| 运算符 | 含义 |
| +（加） | 加法：两个数相加，返回优先级较高的参数的数据类型 |
| -（减） | 减法：两个数相减，返回优先级较高的参数的数据类型 |
| \*（乘） | 乘法：两个数相乘（算术乘法运算符），返回优先级较高的参数的数据类型 |
| /（除） | 除法：用一个数除以另一个数（算术除法运算符），返回优先级较高的参数的数据类型。如果用一个整数去除另一个整数，其结果是一个整数，小数部分被截断，即所得的商 |
| %（模） | 取模：返回两个整数相除后的整数余数 |

● 赋值运算符

  
KDB-SQL中有一个赋值运算符，即等号（=）。

 

   B 例：

     
DECLARE @tint int;

     
SET @tint = 2;   --------把2赋值给自定义变量tint

   下面语句中的=则不是赋值运算符，而是比较运算符=

 

   B 例：

     
SELECT TagName = ‘OPC\_Simudevice.i1Val.i1Val0’

     
FROM Realtime   -----判断age是否为25，返回布尔值，即TRUE或FALSE

● 比较运算符

   比较运算符测试两个表达式是否相同。

|  |  |
| --- | --- |
| 运算符 | 含义 |
| =（等于） | 等于：比较两个表达式。当比较非空表达式时，如果两个操作数相等，则结果为TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为NULL，则结果为NULL。 |
| >（大于） | 大于：比较两个表达式。当比较非空表达式时，如果左操作数的值大于右操作数的值，则结果为TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为NULL，则结果为NULL。 |
| <（小于） | 小于：比较两个表达式。当比较非空表达式时，如果左操作数的值小于右操作数的值，则结果为TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为NULL，则结果为NULL。 |
| >=（大于等于） | 大于等于：比较两个表达式。当比较非空表达式时，如果左操作数的值大于或等于右操作数的值，则结果为TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为NULL，则结果为NULL。 |
| <=（小于等于） | 小于等于：比较两个表达式。当比较非空表达式时，如果左操作数的值小于或等于右操作数的值，则结果为TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为NULL，则结果为NULL。 |
| <>（不等于） | 不等于：比较两个表达式。当比较非空表达式时，如果左操作数的值不等于右操作数的值，则结果为TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为NULL，则结果为NULL。 |

● 逻辑运算符

   在KDB-SQL中，逻辑运算符对某个条件进行测试，以获得其真实情况。逻辑运算符和比较运算符一样，返回带有TRUE或FALSE值的布尔数据类型。

|  |  |
| --- | --- |
| 运算符 | 含义 |
| ALL | 如果一系列的比较都为TRUE，那么就为TRUE。 |
| AND | 如果两个布尔表达式都为TRUE，那么就为TRUE。当语句中有多个逻辑运算符时，AND运算符将首先计算。可以通过使用括号更改计算次序。 |
| ANY | 如果一系列的比较中任何一个为TRUE，那么就为TRUE。 |
| BETWEEN | 如果操作数在某个范围之内，那么就为TRUE。 |
| IN | 如果操作数等于表达式列表中的一个，那么就为TRUE。 |
| LIKE | 如果操作数与一种模式相匹配，那么就为TRUE。 |
| NOT | 对任何其它布尔运算符的值取反 |
| OR | 如果两个布尔表达式中的一个为TRUE，那么就为TRUE。当在一个语句中使用多个逻辑运算符时，在AND运算符之后求OR运算符的值。但是，通过使用括号可以更改求值的顺序。 |
| SOME | 如果在一系列比较中有些为TRUE，那么就为TRUE。 |

当一个复杂的表达式有多个运算符时，运算符优先性决定执行运算的先后次序。在KDB-SQL中有下面这些优先等级，在较低等级的运算符之前先对较高等级的运算符进行求值。由上至下，等级逐渐降低：

◆     
\*（乘）、/（除）、%（模）

◆     
+（加）、-（减）

◆     
=、>、<、>=、<=、<> 比较运算符

◆     
NOT

◆     
AND

◆     
ALL、ANY、BETWEEN、IN、LIKE、OR、SOME

◆     
=（赋值）

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.4.6 注释

注释是程序代码中不执行的文本字符串（也称为注解）。注释可用于说明代码或暂时禁用正在进行诊断的KDB-SQL语句。使用注释对代码进行说明，可使程序代码更易于维护。在KDB-SQL中使用SQL-92标准的注释指示符—双连字符（--）。从双连字符开始到行尾均为注释。对于多行注释，必须在每个注释行的开始使用双连字符。

 

B 例如：

   --从表中选择所有的行和所有的列

  
SELECT \*

  
FROM Realtime

Copyright
2013-2016

---

第7章 SQL语法

#### 7.1.4.7 保留关键字

KDB-SQL使用保留关键字定义、操作和访问数据库。保留关键字是工业实时数据库使用的KDB-SQL语言语法的一部分，用于分析和理解KDB-SQL语句。尽管在KDB-SQL脚本中，使用保留关键字作为标识符和对象名在语法上是可行的，但规定只能使用分隔标识符。建议不要使用保留关键字作为标识符和对象名。

下表列出了工业实时数据库的保留关键字：

|  |  |  |
| --- | --- | --- |
| ALL | FROM | POSITION |
| AND | FULL | PRECISION |
| AS | GROUP | PRIMARY |
| ASC | HAVING | REAL |
| AT | HOUR | RIGHT |
| AVG | IN | ROLLBACK |
| BETWEEN | INNER | RTRIM |
| BIGINT | INSERT | SECOND |
| BIT\_LENGTH | INT | SELECT |
| BY | INTEGER | SET |
| CAST | INTO | SMALLINT |
| CHAR | IS | SUBSTRING |
| CHARACTER | ISOLATION | SUM |
| CHAR\_LENGTH | JOIN | TABLE |
| CHARACTER\_LENGTH | KEY | TIME |
| COMMIT | LEFT | TIMESTAMP |
| COMMITTED | LEVEL | TIMEZONE\_HOUR |
| CONVERT | LIKE | TIMEZONE\_MINUTE |
| COUNT | LOCAL | TINYINT |
| CREATE | LOWER | TOP |
| CROSS | LTRIM | TRANSACTION |
| CURRENT\_DATE | MAX | TRIM |
| CURRENT\_TIME | MIN | TRUE |
| CURRENT\_TIMESTAMP | MINUTE | UNCOMMITTED |
| DATE | MONTH | UPDATE |
| DAY | NATIONAL | UPPER |
| DEC | NATURAL | USING |
| DECIMAL | NCHAR | VALUES |
| DEFAULT | NOT | VARCHAR |
| DELETE | NULL | WHERE |
| DESC | NUMERIC | WORK |
| DISTINCT | OCTET\_LENGTH | YEAR |
| DOUBLE | ON | ZONE |
| DROP | ONLY |  |
| ESCAPE | OR |  |
| EXTRACT | ORDER |  |
| FALSE | OUTER |  |
| FLOAT | PERCENT |  |

Copyright
2013-2016

---

第7章 SQL语法

## 7.2     如何使用SELECT语句

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.1  SELECT语句概述

在设计、创建和填充了数据库之后，下一步该是访问其中存储的数据了。工业实时数据库提供了单独的语句SELECT来检索存储在数据库中的数据。SELECT语句是KDB-SQL语言中最重要和最复杂的语句。在KDB-SQL中，SELECT语句主要用于从数据库中找出所需要的数据。

在KDB-SQL中，SELECT语句由一系列子句组成，这些子句允许你从单个表或多个表中选取单行或多行数据。可以指定数据选取的条件，也可以对查询到的结果作排序或汇总。KDB-SQL语言提供的SELECT语句总共包括6个子句。虽然SELECT语句的完整语法较复杂，但是其主要的子句可归纳如下：

SELECT select\_list

FROM table\_source

[WHERE search\_condition]

[GROUP BY group\_by\_expression]

[HAVING search\_condition]

[ORDER BY order\_expression [ASC | DESC] ]

可以在查询之间使用UNION运算符，以将查询的结果组合成单个结果集。

其中，SELECT子句和FROM子句是不可缺省的，必须包含在每个查询语句中，其它子句只有当用到相应功能时才选用。

SELECT语句中每个子句代表的意义如下：

l        
SELECT子句

指定需要查询的数据项，这些数据项可能是来自表中的列名，或由KDB-SQL在执行查询时计算出的表达式。

l        
FROM子句

指明数据的来源，即要查找的数据存放在哪些表中。

l        
WHERE子句

列出搜索条件，规定要查找的数据所应满足的条件。只有符合条件的行才向结果集提供数据。不符合条件的行中的数据不会被使用。

l        
GROUP BY子句

根据
group\_by\_expression 的值将结果集分成组，即将在某些列上具有相同取值的行当作一组，每一组仅产生一个查询结果。

l        
HAVING子句

与GROUP BY子句配合使用，用于限制分组的条件，即只有使HAVING子句指定的条件满足的分组才能出现在查询结果中。HAVING 子句前面不必有 GROUP BY 子句。

l        
ORDER BY子句

用于将查询结果按一列或多列中的数据排序输出。若在某个查询语句中省略ORDER BY子句，则产生的查询结果是无序的。ASC 和
DESC 关键字用于指定行是按升序还是按降序排序。

 

下面显示了一个典型的SELECT语句的示例：

B 例：

    SELECT TagName, TagId, CollectorName

    FROM Tag

    WHERE SourceAddress = ‘Simudevice.i1Val.i1Val0’

    ORDER BY TagId

在该条查询语句中，FROM子句中指定的表名为Tag，说明查找的数据保存在变量表Tag中；SELECT子句后面列出了Tag表中的三个列名，说明只希望了解每个变量的变量名（TagName）、变量ID（TagId）、采集器名称（CollectorName）；“数据源地址为Simudevice.i1Val.i1Val0”的条件则由WHERE子句中的搜索条件SourceAddress =
‘Simudevice.i1Val.i1Val0’体现；查询结果按变量ID由低到高的顺序排列的要求由ORDER BY TagId实现。

SELECT语句的查询结果是通过依次使用子句产生的，必须按照正确的顺序指定 SELECT 语句中的子句。以上例来分析：首先依据FROM子句确定查询数据的来源，即存放数据的表名，上例中的表名为Tag；然后依次扫描表中的每一行，判断每一行是否能使WHERE子句中的搜索条件为“真”，在上例中即判断SourceAddress的值是否为Simudevice.i1Val.i1Val0，保留满足条件的行，忽略不满足搜索条件的行；对于保留的每一行，根据SELECT子句中列出的内容，抽取相应的数据值组成新的行，上例中取每一行在TagName、TagId、CollectorName三列上的分量值组成新的行。最后根据ORDER BY子句中设定的条件对查询结果排序。

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.2  SELECT语法格式

select\_statement

::= SELECT [ALL | DISTINCT]

       [ { TOP integer | TOP integer
PERCENT} ]

       <select\_list>

    FROM { <table\_source> }[ ,……n ]

    [ WHERE <search\_condition> ]

    [ GROUP BY group\_by\_expression [ ,……n ] ]

    [ HAVING <search\_condition> ]

    [ORDER BY { order\_by\_expression | column\_position
[ASC | DESC ] }

     [ ,……n ] ]

由于SELECT语句的复杂性，下面按子句说明详细的语法元素和参数。

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.3  SELECT子句

指定由查询返回的列。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.3.1 SELECT子句语法格式

SELECT [ ALL | DISTINCT ]

     [ TOP n [ PERCENT ] ]

     < select\_list >

< select\_list > ::=

  { \*

     |  { table\_name | table\_alias }.\*

     |  { column\_name | expression }

        [ [AS] column\_alias ]

} [ ,……n ]

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.3.2 SELECT子句参数说明

l       
ALL

指定在结果集中可以显示重复行。ALL是默认设置。

l       
DISTINCT

指定在结果集中不显示重复行，只能显示唯一行。空值被认为相等，不论遇到多少个空值，在结果中只返回一个空值
NULL。如果没有指定 DISTINCT，那么将返回所有行，包括重复的行。

l       
TOP n [PERCENT]

指定只从查询结果集中输出前n行。n是介于0和4294967295之间的整数。如果还指定了PERCENT，则只从结果集中输出前百分之n行。当指定带 PERCENT 时，n 必须是介于 0 和 100
之间的整数。

如果查询包含 ORDER BY 子句，将输出由 ORDER BY 子句排序的前 n 行（或前百分之
n 行）。如果查询没有 ORDER BY 子句，行的顺序将任意。

l       
< select\_list >

查询结果返回的列。选择列表是以逗号分隔的一系列表达式。每个表达式定义结果集中的一列。结果集中列的排列顺序与选择列表中表达式的排列顺序相同。

n       
\*

指定返回在FROM子句中指定的所有表的所有列，并按它们在表中的顺序返回。

n       
{ table\_name | table\_alias }.\*

将\*号的作用域限制为指定的表或表的别名。table\_name为在FROM子句中指定的数据库中的现存表的表名。table\_alias为在FROM子句中指定的数据库中的现存表的别名。table\_alias必须符合标识符规则。如果在FROM子句中指定了表的别名table\_alias，则只能使用table\_alias，而不能使用table\_name。

n       
column\_name

    是要返回的列名。当 FROM 子句中的多个表内有包含重复名的列时，要通过表名或表的别名限定column\_name以避免二义性引用。例如，在工业实时数据库中创建了两个表Table1和Table2，表内都有名为Col的列。如果在查询中联接这两个表，可以在选择列表中将Table1的Col指定为Table1.Col，而将Table2的Col指定为Table2.Col。

    一般来说，对于执行具有列清单的查询来说，选择清单比选择“\*”的速度更快，即使在选择清单中包含表中的每一列时也是如此。

n       
expression

是列名、常量、函数以及由运算符连接的列名、常量和函数的任意组合，或者是子查询。

包括在查询中的表达式并不一定要包括列。数字、数学表达式和字符串全都可以包括在SELECT子句中。也可以将列名作为所使用的表达式的组成部分包括在SELECT子句中。但不能将列的别名作为表达式的组成部分包括在SELECT子句中。

n       
AS

        可选的 AS 关键字可用于更改名称，或当表达式没有名称时为其指定一个 名称。

n       
column\_alias

    是查询结果集内替换列名的可选名，也称为列的别名。KDB-SQL不支持指定重复的列别名。列别名必须满足标识符规则。例如，可以为名为au\_lname的列指定别名，如“LName”或“FirstName”，而不能写成字符串格式’LName’或‘FirstName’。

    别名还可用于为表达式结果指定名称。

    当使用column\_alias时，列名和列的别名之间可以有AS，也可以没有AS关键字。

    column\_alias可用于 ORDER BY 子句。然而，不能用于 WHERE、GROUP
BY 或 HAVING 子句。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.3.3 SELECT子句示例

1.     
使用\*检索所有的行和列

 

B 例：

    SELECT \*

    FROM Realtime

 

    SELECT Realtime.\*

    FROM Realtime

    上面两个例子都是从authors表中返回所有的行和列。

2.     
使用选择列表检索

 

B 例：

        SELECT TagName, DataTime, DataVersion, DataQuality, DataValue

FROM Realtime

从表Realtime中返回所有的行和列，等价于例1。

B 例：

    SELECT TagName, DataTime, DataValue

    FROM Realtime

    从表Realtime中返回TagName、DataTime和DataValue三列的所有的行。

 

B 例：

    SELECT TagName, DataValue, DataQuality,
DataVersion, DataTime

    FROM Realtime

    从表Realtim中返回TagName、DataValue、DataQuality、DataVersion和DataTime五列的所有的行，并且按照在SELECT选择列表中的顺序显示。

B 例：

    SELECT Tag\_Name, Data\_Time, Data\_Value

    FROM Realtime

    出现在SELECT选择列表中的列名必须是存储在目标表中的列的列名。在本查询中，Tag\_Name、Data\_Time、Data\_Value不是表Realtime中的列名，都是无效列名，所以查询失败。

3.     
在选择列表中指定表达式或子查询

 

B 例：

    SELECT 'Index', TagId

    FROM Tag

    在选择列表中指定字符串'Index'，返回到结果集中第一列的数据为字符串Index。

 

B 例：

    SELECT TagId-2

    FROM Tag

    在选择列表中指定返回每行列TagId的值与2的差值。

 

B 例：

    SELECT TagId/0

    FROM Tag

    如果在选择列表中指定的表达式遇到算术错误（溢出、被零除），则查询失败。

下面的例子显示了在选择列表中指定子查询

 

B 例：

    SELECT TagName, (SELECT COUNT(\*) FROM Tag) AS
total

    FROM Realtime

    在选择列表中指定了SELECT子查询，该子查询返回表Tag的总行数，并以total作为返回后的列名。

 

B 例：

    SELECT (SELECT TagName, TagId FROM Tag) AS
TagInfo

    FROM Tag

    在选择列表指定的子查询只允许在它的选择列表中指定一个表达式，所以本查询失败。

 

B 例：

    SELECT (SELECT SourceAddress FROM Tag) AS
TagSource

    FROM Tag

    在选择列表指定的子查询不允许返回值多于一个，所以本查询失败。

4.     
在选择列表中使用别名

 

B 例：

    SELECT TagName AS Tag\_Name, DataTime AS
Data\_Time, DataValue AS Data\_Value

    FROM Realtime

    从表Realtime中返回TagName列、DataTime列、DataValue列，并以Tag\_Name、Data\_Time、Data\_Value分别作为各列的列名。

 

B 例 ：

    SELECT TagName AS Tags Name, DataValue AS 'Data\_Value'

    FROM Realtime

    作为列的别名，table\_alias必须符合标识符规则：Tags  Name不符合常规标识符规则，应使用双引号（“”）或方括号（[]）作为分隔标识符；使用单引号的'Data\_Value'是字符串，而不是标识符，所以也不能作为列的别名出现。

5.     
使用约束条件限制查询返回的列

在SELECT子句中，可以使用ALL、DISTINCT、TOP、PERCENT限制查询返回的列

 

B 例：

    SELECT ALL CollectorName

    FROM Tag

 

    SELECT CollectorName

    FROM Tag

    从表Tag中返回所有行的CollectorName列，包含重复行。在KDB-SQL中，缺省时的约束为ALL。所以上面的两个代码示例返回相同的结果集。

 

B 例：

    SELECT DISTINCT CollectorName

    FROM Tag

    从表Tag中检索CollectorName列，使用了DISTINCT防止检索重复的CollectorName列值。

下面的示例显示了在SELECT子句中使用TOP n [PERCENT]限制条件，其中n必须是整数。如果还指定了PERCENT，则n必须是0-100之间的整数。

 

B 例：

    SELECT TOP 10 TagName

    FROM Tag

    从表Tag中返回前10行的TagName列值。

 

B 例：

    SELECT TOP 10 PERCENT TagName

    FROM Tag

    从表Tag中返回所有行的前10%的TagName列值。

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.4  FROM子句

指定从其中检索行的表。需要 FROM 子句，除非选择列表只包含常量、变量和算术表达式（没有列名）。用 FROM 子句可以：

l        
列出选择列表和
WHERE 子句中所引用的列所在的表。可用 AS 子句为表的名称指定别名。

l        
指定联接类型。这些类型由
ON 子句中指定的联接条件限定。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.4.1 FROM子句语法格式

FROM {
<table\_source> }[ ,……n ]

<table\_source>
::= table\_name [ [ AS ] table\_alias ]

                
| derived\_table [ AS ] table\_alias [ ( column\_alias [, ……n ] ) ]

                
| < joined\_table >

<
joined\_table > ::=

  
< table\_source > < join\_type > < table\_source >  ON <
search\_condition >

   |
< table\_source > CROSS JOIN < table\_source >

< join\_type
> ::=

   [
INNER | { { LEFT | RIGHT | FULL } [ OUTER ] } ]

JOIN

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.4.2 FROM子句参数说明

l       
table\_source

指定用于 SELECT 语句的表、派生表和联接表。

n       
table\_name

指定要从其中检索行的表的表名。table\_name必须是数据库中现存表的表名。不能在FROM子句中指定重复名称。

n       
AS

    可选的 AS 关键字可用于指定一个表或子查询的别名。

n       
table\_alias

    指定要从其中检索行的表名的别名，为方便起见而使用，或用于区分自联接或子查询中的表。table\_alias必须符合标识符规则。

    table\_alias通常是一个缩短了的表名，用于在联接中引用表中的特定列。如果联接中的多个表中有相同名称的列存在，则KDB-SQL要求必须使用表名或别名来限定列名。如果定义了别名则不能再使用表名。

n       
derived\_table

是嵌套SELECT语句，用作对外部查询的输入。SELECT语句从该子查询返回的结果集中选择列。如果在FROM子句中指定了derived\_table，则必须为derived\_table指定table\_alias。

n       
column\_alias

用于替换derived\_table结果集内列名的可选别名。在选择列表中放入每个列的一个别名，并将整个列别名列表用圆括号括起来。列别名的个数必须与结果集内的列个数一致。不能指定重复的列别名。column\_alias必须符合标识符规则。

l       
< joined\_table >

    是两个或更多表的积的结果集。关于表的联接的具体信息和示例，请参见使用联接表。

l       
< join\_type >

指定联接操作的类型。

n       
INNER

    指定返回所有相匹配的行对。废弃两个表中不匹配的行。如果未指定联接类型，则这是默认设置。

n       
LEFT [ OUTER ]

    指定除所有由内联接返回的行外，所有来自左表的不符合指定条件的行也包含在结果集内。来自左表的输出列设置为 NULL。

n       
RIGHT [ OUTER ]

    指定除所有由内联接返回的行外，所有来自右表的不符合指定条件的行也包含在结果集内。来自右表的输出列设置为 NULL。

n       
FULL [ OUTER ]

如果来自左表或右表的某行与选择准则不匹配，则指定在结果集内包含该行，并且将与另一个表对应的输出列设置为 NULL。除此之外，结果集中还包含通常由内联接返回的所有行。

n       
JOIN

指明所指定的联接操作应在给定的表之间执行。

l       
ON < search\_conditon >

    指定联接所基于的条件。此条件可指定任何谓词，但通常使用列和比较运算符。

l       
CROSS JOIN

    指定两个表的矢量积。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.4.3 FROM子句示例

1.       
使用简单的FROM子句

 

B 例：

    SELECT TagName, DataTime, DataValue

    FROM Realtime

    从表Realtime中检索TagName、DataTime、DataValue三列。其中表Realtime中必须包含要检索的三列，否则检索失败。

 

B 例：

    SELECT \*

    FROM Real

    目标表必须是数据库中的现存表。表Real在数据库中不存在，所以检索失败。

2.       
指定表的别名

 

B 例：

    SELECT TagName, DataTime, DataValue

    FROM Realtime AS R

   

    SELECT TagName, DataTime, DataValue

    FROM Realtime R

    上面的两个代码示例都是指定表Realtime的别名为R，其中关键字AS是可选的。

 

B 例：

    SELECT TagName, DataTime, DataValue

    FROM Realtime AS Real  Time

    表的别名必须符合标识符规则。上例中Real  Time不符合常规标识符规则，可以使用双引号（“”）或方括号（[]）作为分隔标识符来使用。

3.       
在FROM子句中指定多个表

下面的示例显示了在FROM子句中指定自联接

 

B 例：

SELECT T.TagName, Tag.TagName

FROM Tag AS T, Tag

在FROM子句中不能指定重复的表名或别名，所以在本例中为其中的一个表Tag指定别名T。由于两个表中都包含TagName列，所以必须将其作用域限定为特定的表：第一列是检索第一个表Tag的TagName列，第二列是检索第二个表Tag的TagName列。

 

B 例：

    SELECT TagName, Tag.CollectorName, Tag.CollectorType,
CollectorID, Collector.CollectorName, Collector.CollectorType

    FROM Tag, Collector

   

    SELECT TagName, T.CollectorName, T.CollectorType,
CollectorID, C.CollectorName, C.CollectorType

    FROM Tag AS T, Collector AS C

    上面的两个代码示例都是从表Tag和Collector中检索TagName、CollectorID、CollectorName和CollectorType列。对于两个表都包含的CollectorName和CollectorType列：第一个示例采用直接使用表名的方式将作用域限定为特定的表；第二个示例采用使用表的别名的方式将作用域限定为特定的表。

 

 

 

 

 

 

 

 

 

4.      
在FROM子句中指定derived\_table

derived\_table是SELECT子查询。可以在FROM子句中指定derived\_table作为检索数据的来源。

 

B 例：

SELECT TagName, DataTime, DataValue

FROM (SELECT Top 100 \* FROM Realtime) AS R

在FROM子句中指定SELECT子查询，并将该子查询的结果集作为检索数据的来源。必须为SELECT子查询指定别名。

 

B 例：

SELECT TagName

FROM (SELECT TagName, TagId FROM Tag)  AS T

   

SELECT CollectorName

FROM (SELECT TagName, TagId FROM Tag) AS T

    当在FROM子句中指定了SELECT子查询作为检索数据的来源时，所检索的列必须在子查询返回的结果集中。上面的第一个代码示例从子查询返回的结果集中检索TagName列，检索成功；而第二个代码示例则是检索一个不存在于子查询返回结果集的列，检索失败。

   

 

 

 

 

 

下面的示例显示了为子查询返回的结果集中的列指定列别名

 

B 例：

    SELECT Tag\_Name, Data\_Value

    FROM (SELECT TagName AS Tag\_Name, DataTime AS
Data\_Time, DataValue AS Data\_Value

           FROM
Realtime) AS R

   

   
SELECT Tag\_Name, Data\_Value

    FROM (SELECT TagName, DataTime, DataValue FROM
Realtime) AS R

          (Tag\_Name,
Data\_Time, Data\_Value)

    上面两个代码示例都为子查询返回的列指定了别名：第一个代码示例直接在子查询中指定列的别名；第二个代码示例则指定了一个列别名列表，与子查询结果集内的列相对应。

 

   

 

 

 

 

 

 

B 例：

   
SELECT TagName, DataValue

    FROM (SELECT TagName, DataTime, DataValue FROM
Realtime) AS R

          (Tag\_Name,
Data\_Time, Data\_Value)

    如果为SELECT子查询指定了列别名，则在检索列时，只能使用列别名。所以本查询中，由于指定了列别名Tag\_Name、Data\_Time、Data\_Value，所以不能再使用原来的列名TagName、DataTime、DataValue。

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.5  WHERE子句

指定用于限制返回的行的搜索条件。只有符合条件的行才向结果集提供数据。不符合条件的行中的数据不会被使用。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.5.1 WHERE子句语法格式

WHERE <
search\_condition >

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.5.2 WHERE子句参数说明

l       
< sarch\_condition >

定义要返回的行所应满足的条件。对搜索条件中可以包含的谓词数量没有限制。有关搜索条件和谓词的更多信息，请参见搜索条件。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.5.3 WHERE子句示例

1.      
使用简单的WHERE子句

 

B 例：

    SELECT CollectorName, CollectorType,
CollectorStatus

    FROM Collector

    WHERE CollectorID > 5

 

    SELECT CollectorName, CollectorType,
CollectorStatus

    FROM Collector

    WHERE CollectorID > '5'

    上面两个示例都是从表Collector中检索满足条件CollectorID>5的行，并从满足条件的行中提取CollectorName、CollectorType、CollectorStatus三列。在检验数字数据时。在本例中需要注意的是不必使用单引号标记来包围被比较的数据：第一个代码示例是基于数字比较的查询；而第二个代码示例将数字包括在单引号中，是将数字用作字符串来查询。

2.      
在WHERE子句中执行计算

 

B 例：

    SELECT CollectorName, CollectorType, CollectorStatus

    FROM Collector

    WHERE (CollectorID - 5) > 0

    从表Collector中检索那些列CollectorID的值大于5的行，并从这些行中提取CollectorName、CollectorType、CollectorStatus三列。

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.6  GROUP BY子句

将结果集分成组，即将在某些列上具有相同取值的行当作一组，使每一组的各行得到唯一的结果。GROUP BY子句通常会和ORDER BY以及HAVING子句一起使用。单独使用GROUP BY子句，其作用相当于在SELECT子句中加上DISTINCT限制符，可以剔除查询结果中在指定列上出现的重复值。

如果SELECT子句< select\_list >中包含聚合函数，则GROUP BY子句计算每组的汇总值。关于聚合函数的详细信息，请参见函数部分。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.6.1 GROUP BY子句语法格式

GROUP BY group\_by\_expression [, ……n ]

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.6.2 GROUP BY子句参数说明

l       
group\_by\_expression

    是对其执行分组的表达式。group\_by\_expression也称为分组列。group\_by\_expression可以是列或选择列表中任一非聚合表达式内的所有列。在选择列表内定义的列的别名不能用于指定分组列。如果分组列包含一个空值，那么该行将成为结果中的一个组。如果分组列包含多个空值，那么这些空值将放入一个组中。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.6.3 GROUP BY子句示例

1.       
使用GROUP BY

 

B 例：

    SELECT CollectorName, Count(TagName) AS Total

    FROM Tag

    GROUP BY CollectorName

    由于使用了GROUP BY子句，只为每个Collector各返回一个使用该采集器的变量总数的行。

 

B 例：

    SELECT CollectorName AS Collector\_Name,
CollectorType AS Collector\_Type

    FROM Tag

    GROUP BY CollectorName, CollectorType

    列别名不能用在GROUP BY子句中。如果按照下面的语句执行则检索失败：

    SELECT CollectorName AS Collector\_Name,
CollectorType AS Collector\_Type

    FROM Tag

    GROUP BY Collector\_Name, Collector\_Type

  

       

       

       

       

       

 

 

 

    在GROUP BY子句中，不能使用除列名以外的其它表达式。下面的代码示例显示了错误的查询：

 

B 例：

    SELECT CollectorName, CollectorType, (TagId +
100) AS Tag\_ID

    FROM Tag

    GROUP BY CollectorName, CollectorType, TagId+100

 

B 例：

    SELECT CollectorName, CollectorType,
SourceAddress

    FROM Tag

    GROUP BY CollectorName, CollectorType

    当指定GROUP BY子句时，只要出现在SELECT子句的选择列表中的不是作为聚合函数参数的列名，就必须列在GROUP BY子句中。在上面的示例中，由于列SourceAddress既不包含在聚合函数中，也不包含在GROUP BY子句中，导致查询失败。

2.       
使用GROUP BY和WHERE

    可以在包含 GROUP BY 子句的查询中使用 WHERE 子句。在进行任何分组之前，将消除不符合 WHERE 子句条件的行。

 

    B 例：

        SELECT CollectorName,
CollectorType, SourceAddress

        FROM Tag

        WHERE DataType < 8

        GROUP BY CollectorName,
CollectorType, SourceAddress

        只有数据类型小于8的行才包含在查询结果所显示的组中。

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.7  HAVING子句

指定组的搜索条件，限定查询中出现的组。与WHERE子句在查询结果分组之前筛选查询结果不同，HAVING子句是在对查询结果分组之后对结果进行筛选。HAVING子句中的表达式适用于每一个分组（而不是单独的行），这些组是一个整体。HAVIGN子句与WHERE子句的另一个不同是，在HAVING子句搜索条件中可以使用聚合函数，而在WHERE子句搜索条件中则不可以使用聚合函数。

HAVING子句通常和GROUP BY子句一起使用。如果不使用GROUP BY子句，HAVING的行为与WHERE子句一样，此时系统认为满足WHERE条件的所有行构成一个分组，HAVING决定是否输出这个分组的结果。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.7.1 HAVING子句语法格式

HAVING < search\_condition >

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.7.2 HAVING子句参数说明

l       
< search\_condition >

指定组或聚合应满足的搜索条件。只有符合条件的组才出现在查询中。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.7.3 HAVING子句示例

1.       
使用HAVING子句

下面的示例显示了不带聚合函数的HAVING子句

B 例：

SELECT CollectorName, SourceAddress, Count(TagName) AS total

FROM Tag

GROUP BY CollectorName, SourceAddress

HAVING SourceAddress = ‘Simudevice.i1Val.i1Val0’

由于使用了HAVING子句，该子句按采集器分组Tag表中的行，并且消除那些数据源地址不为Simudevice.i1Val.i1Val0的分组。

下面的示例显示了带聚合函数的HAVING子句

 

B 例：

    SELECT CollectorName

    FROM Tag

    GROUP BY CollectorName

    HAVING COUNT(\*) < 5000

    该子句按采集器分组Tag表中的行，并且消除所属采集器变量个数大于5000的组。

 

B 例：

    SELECT CollectorName, CollectorType

    FROM Tag

    GROUP BY CollectorName, CollectorType

    HAVING SourceAddress = ‘Simudevice.i1Val.i1Val0’

    当HAVING子句和GROUP BY子句一起使用时，没有在GROUP BY子句中出现的列一定不能在HAVING子句中出现。由于列SourceAddress并没有出现在GROUP BY子句中，导致检索失败。

 

B 例：

    SELECT CollectorName, Count(TagName) AS total

    FROM Tag

    GROUP BY CollectorName

    HAVING total > 1000

    列别名不能在HAVING子句中使用。在HAVING子句中使用在SELECT子句中指定的列别名total，导致检索失败。

2.       
使用HAVING和WHERE

    WHERE 子句搜索条件在进行分组操作之前应用；而 HAVING 搜索条件在进行分组操作之后应用。

 

    B 例：

        SELECT CollectorName,
Count(TagName) AS total

        FROM Tag

        WHERE DataType > 4

        GROUP BY CollectorName

        HAVING Count(TagName)
< 5000

        首先消除那些数据类型大于4的行，然后再按照采集器名称分组剩余的行，并且消除那些所属采集器变量总数大于5000的分组。

Copyright
2013-2016

---

第7章 SQL语法

### 7.2.8  ORDER BY子句

指定结果集的排序。空值被视为最低的可能值。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.8.1 ORDER BY子句语法格式

ORDER BY { order\_by\_expression [ ASC | DESC ] } [, ……n ]

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.8.2 ORDER BY子句参数说明

l       
order\_by\_expression

指定要排序的列。可以将排序列指定为列名或列的别名（如果指定了列的别名，则不能再用列名），或者指定为代表选择列表内的列名或别名的位置编号。当指定位置编号时，最左边位置的编号为1，相邻的下一位置编号为2，依次类推。排序列表中的列必须是唯一的。在ORDR BY子句中，还可以指定多个排序列，表示同时按多个列值对输出结果排序。

 

   

 

 

 

 

 

 

 

l       
ASC

指定按递增顺序，从最低值到最高值对指定列中的值进行排序。缺省情况是ASC。

l       
DESC

指定按递减顺序，从最高值到最低值对指定列中的值进行排序。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.2.8.3 ORDER BY子句示例

1.       
使用ORDER BY子句

 

B 例：

  SELECT TagName, DataTime, DataValue

 FROM Realtime

 ORDER BY TagName

    从表Realtime中检索TagName、DataTime和DataValue三列，并按TagName列的值以升序排序。

 

B 例：

    SELECT TagName AS Tag\_Name, DataTime AS
Data\_Time, DataValue AS Data\_Value

    FROM Realtime

    ORDER BY Tag\_Name

    如果在SELECT的选择列表指定了列别名，则在ORDER BY子句中只能使用列别名，而不能再使用原来的列名。所以下面的代码示例显示了一个失败的查询：

    SELECT TagName AS Tag\_Name, DataTime AS
Data\_Time, DataValue AS Data\_Value

    FROM Realtime

    ORDER BY TagName

 

B 例：

    SELECT DataTime, DataValue

    FROM Realtime

    ORDER BY TagName

    如果指定了ORDER BY子句，则在ORDER BY子句中指定的排序列必须也出现在SELECT子句的选择列表中。由于在ORDER BY子句中指定的TagName列没有出现在选择列表中，导致检索失败。

2.       
在ORDER BY子句中指定多个排序列

还可以指定多个排序列，表示同时按多个列值对输出结果排序。

 

B 例：

SELECT CollectorName, CollectorType, SourceAddress

FROM Tag

ORDER BY CollectorName ASC, CollectorType DESC, SourceAddress ASC

在ORDER BY子句中指定了三个排序列：对CollectorName列值以升序排序，在此基础上再对CollectorType列值以降序排序，最后对SourceAddress列值以升序排序。

当在ORDER BY子句中指定多个排序列时，排序列表中的列名不能重复，必须是唯一的。

 

B 例：

SELECT CollectorName, CollectorType, SourceAddress

        FROM Tag

        ORDER BY CollectorName,
CollectorName

    由于在ORDER BY子句中指定了重复的CollectorName列名，导致检索失败。

3.       
使用列的位置编号排序

 

B 例：

SELECT CollectorName, CollectorType, SourceAddress

FROM Tag

ORDER BY 1 ASC, 2 DESC, 3 ASC

在ORDER BY子句中指定了检索的三个列的位置编号，分别对应：对CollectorName列值以升序排序，在此基础上再对CollectorType列值以降序排序，最后对SourceAddress列值以升序排序。本例与前面一例的排序方式一致。

在ORDER BY子句中只能指定列名、列别名或位置编号，不能指定其它任何表达式。

 

B 例：

    SELECT TagName, TagId+2

    FROM Tag

    ORDER TagId+2

由于不能在ORDER BY子句中指定表达式，导致检索失败。对于本例，可以为表达式指定别名：

    SELECT TagName, (TagId+2) AS Tag\_ID

    FROM Tag

    ORDER BY Tag\_ID

    也可以直接在ORDER BY子句中指定列编号：

    SELECT TagName, TagId+2

    FROM Tag

    ORDER BY 1, 2

    都可以检索成功，并按照排序列表排序。

当在FROM子句中指定多个表时，如果列名或列别名不能明确区分列时，可以在ORDER
BY子句中使用列的位置编号进行排序

 

B 例：

    SELECT T.CollectorName, C.CollectorName

    FROM Tag AS T, Collector AS C

    ORDER BY T.CollectorName

表Tag和Collector中都包含CollectorName列。本例是在ORDER BY子句中将它的作用域指定为表Tag，但这在KDB-SQL中是不允许的，导致检索失败。对于本例，当然可以利用列别名来解决，但此处使用列的位置编号来解决：

    SELECT T.CollectorName, C.CollectorName

    FROM Tag AS T, Collector AS C

    ORDER BY 1

4.       
使用ORDER BY和GROUP BY

ORDER BY子句可以与GROUP BY子句共同使用对分组查询结果进行排序

 

B 例：

    SELECT CollectorName, Count(TagName)

    FROM Tag

    WHERE DataType < 8

    GROUP BY CollectorName

    HAVING COUNT(\*)  > 5000

    ORDER BY 2

首先从表Tag中选择数据类型小于8的行，然后按照采集器名称分组这些行，并且消除所属采集器变量个数小于5000的分组，最后按照变量个数的总和以升序对分组查询结果排序。

Copyright
2013-2016

---

第7章 SQL语法

## 7.3     如何使用搜索条件

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.1  搜索条件概述

在上一章中，我们曾提到WHERE子句和HAVING子句：[ WHERE < search\_condition
> ]和[ HAVING
< search\_conditon > ]。其中的search\_condition就是搜索条件。搜索条件指定了 SELECT 语句、查询表达式或子查询的结果集内所返回的行的条件。对于WHERE子句，指定要检索的行，对于HAVING子句，指定要检索的组。

KDB-SQL提供了丰富的手段用于构造搜索条件，从结构上来说，搜索条件主要可分成以下几类：

l      
比较条件

l      
空值条件

l      
范围条件

l      
组属条件

l      
模式匹配

l      
带限定符的比较条件

l      
存在条件

l      
逻辑条件

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.2  搜索条件语法格式

< search\_condition > ::=

  < predicate | logic\_boolean\_predicate > [, ……n ]

< predicate > ::=

  { Comparison\_predicate

   | Null\_predicate

   | Between\_predicate

   | In\_predicate

   | Like\_predicate

   | Quantified\_comparison\_predicate

   | Exists\_predicate

   | Boolean\_predicate

}

由于搜索条件的复杂性，下面按照各搜索条件说明详细的语法元素和参数。

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.3  比较条件

用于判定表的某一列是否与某个常量满足某种关系，返回TRUE或FALSE。比较条件是查询语句中使用最频繁的一类条件。在KDB-SQL中，共支持6种比较运算符：=（等于）、<>（不等于）、>（大于）、>=（大于等于）、<（小于）、<=（小于等于）。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.3.1 语法格式

Comparison\_predicate ::=

expression { = | <> | > | >= | < |<= } expression

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.3.2 参数说明

l        
expression

是列名、常量、函数、变量、标量子查询，或者是由运算符或子查询连接的列名、常量和函数的任意组合。

l        
=

是用于测试两个表达式是否相等的运算符。当比较非空表达式时，如果两个操作数相等，则结果为 TRUE；否则结果为 FALSE。如果两个操作数中有一个或者两个都为 NULL，则结果为 NULL。

l        
<> 

是用于测试两个表达式彼此不相等的条件的运算符。当比较非空表达式时，如果左边操作数的数值不等于右边的操作数，则结果为 TRUE；否则结果为 FALSE。如果两个操作数中有一个或者两个都为 NULL，则结果为 NULL。

l        
> 

是用于测试一个表达式大于另一个的条件的运算符。当比较非空表达式时，如果左边操作数的值大于右边的操作数，则结果为 TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为 NULL，则结果为 NULL。

l        
>=

是用于测试一个表达式大于或等于另一个的条件的运算符。当比较非空表达式时，如果左边操作数的值大于或等于右边的操作数，则结果为 TRUE；否则结果为 FALSE。如果两个操作数中有一个或者两个都为 NULL，则结果为 NULL。

l        
< 

是用于测试一个表达式小于另一个的条件的运算符。当比较非空表达式时，如果左边操作数的值小于右边的操作数，则结果为 TRUE；否则结果为FALSE。如果两个操作数中有一个或者两个都为 NULL，则结果为 NULL。

l        
<=

是用于测试一个表达式小于或等于另一个的条件的运算符。当比较非空表达式时，如果左边操作数的值小于或等于右边的操作数，则结果为 TRUE；否则结果为 FALSE。如果两个操作数中有一个或者两个都为 NULL，则结果为 NULL。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.3.3 用法示例

1.       
在WHERE条件中使用比较运算符

只有符合比较条件的行才向结果集提供数据。不符合比较条件的行中的数据不会被使用。

 

B 例：

SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

比较条件中最常见的形式是某一列值与一个常量的比较。本查询从表Realtime中检索变量名为OPC\_Simudevice.i1Val.i1Val0的行，并提取TagName、DataTime、DataValue三列。

 

B 例：

   
SELECT TagName, TagId, Description

   
FROM Tag

   
WHERE DataType - 4 >= 0

        从表Tag中检索数据类型大于等于4的行，并提取TagName, TagId,
Description三列。

 

B 例：

    SELECT TagName, DataTime ,DataValue

    FROM Realtime

    WHERE (SELECT DataType FROM Tag

           
WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0') > 4

        在比较条件中，还可以指定标量子查询的比较。本查询中在WHERE子句中比较标量子查询的结果是否大于4，如果大于4，即为真，则返回表Realtime中的所有列，并提取TagName, DataTime,
DataValue三列；如果为假，则不返回任何数据。

在KDB-SQL中，不支持!=作为不等号使用。若要作不等条件判定，只能指定<>。

在使用比较条件时，如果在计算表达式中出现算数错误（溢出或被零除），执行都将会失败。并且在指定比较条件时，要确保比较运算符两边的表达式数据类型可以相互兼容。

 

B 例：

    SELECT TagName, TagId, CollectorName

    FROM Tag

    WHERE CollectorType = 'OPCCollector'

        由于CollectorType列的数据类型为Tinyint，所以不能将它与字符串进行比较，二者不能相互兼容，导致查询失败。

2.       
在HAVING子句中使用比较运算符

在HAVING子句中指定比较条件与在WHERE子句中指定比较条件一样，但在HAVING子句中可以指定聚合函数

 

B 例：

SELECT CollectorName, Count(TagName)

FROM Tag

GROUP BY CollectorName

HAVING Count(TagName) > 5000

在表Tag中按采集器名称分组，并消除那些所属采集器变量个数小于5000的组。

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.4  空值条件

判定某一列的值是否为空值。空值（NULL）是一种特殊的值，表示某个值不确定或不存在。NULL既不等同于零也不是空格。在查询过程中，经常会将空值作为查询条件。在KDB-SQL中，若要确定表达式是否为 NULL，请使用 IS NULL 或 IS NOT NULL，而不要用比较运算符（例如 = 或
<>）。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.4.1 语法格式

Null\_predicate ::=

   expression IS [ NOT ] NULL

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.4.2 参数说明

l       
expression

是列名、常量、函数、变量、标量子查询，或者是由运算符或子查询连接的列名、常量和函数的任意组合。

l       
IS [ NOT ] NULL

根据所使用的关键字指定对空值或非空值的搜索。如果有一个参数为 NULL 或两个参数都为 NULL，那么比较运算符返回 UNKNOWN。

n       
如果 expression的值是 NULL，则 IS NULL 返回 TRUE；否则，返回 FALSE。

n       
如果 expression的值是 NULL，则 IS NOT NULL 返回 FALSE；否则，返回 TRUE。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.4.3 用法示例

1.        
使用IS NULL

下面的示例显示了检索未登记居住城市的作者的记录

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE Description IS NULL

    在录入数据时，如果没有记录变量描述信息，则Description这一项就为NULL。这样，可以通过判定Description的值是否为NULL，就可以找到还未记录描述信息的变量信息。

2.        
使用IS NOT
NULL

下面的示例显示了检索已经记录了描述信息的变量的记录

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE Description IS NOT NULL

通过指定IS
NOT NULL，就可以找到已经记录了描述信息的变量信息。

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.5  范围条件

判定一个表达式的结果是否在某个范围之内。在KDB-SQL中构造范围条件时，应指定关键字BETWEEN和AND判定某个表达式值是否在某个区间范围之内。区间范围包括上限和下限。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.5.1 语法格式

Between\_predicate
::=

  
test\_expression [ NOT ] BETWEEN begin\_expression AND end\_expression

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.5.2 参数说明

l       
test\_expression

是用来在由 begin\_expression和 end\_expression定义的范围内进行测试的表达式，可以是列名、常量、函数、变量、标量子查询，或者是由运算符或子查询连接的列名、常量和函数的任意组合。test\_expression必须与
begin\_expression 和 end\_expression具有相同的数据类型。

l       
begin\_expression

可以是列名、常量、函数、变量、标量子查询，或者是由运算符或子查询连接的列名、常量和函数的任意组合。begin\_expression必须与
test\_expression和
end\_expression具有相同的数据类型。

l       
end\_expression

可以是列名、常量、函数、变量、标量子查询，或者是由运算符或子查询连接的列名、常量和函数的任意组合。end\_expression必须与
test\_expression和 begin\_expression具有相同的数据类型。

l       
[ NOT ] BETWEEN  AND

指定值的包含范围。使用AND将下限值（begin\_expression）和上限值（end\_expression）分开，表示test\_expression应该处于由begin\_expression和end\_expression指定的范围内。

n        
如果 test\_expression的值大于或等于 begin\_expression的值并且小于或等于 end\_expression的值，则 BETWEEN 返回TRUE。

n        
如果test\_expression的值小于begin\_expression的值或者大于 end\_expression的值，则 NOT
BETWEEN 返回TRUE。

n        
如果BETWEEN 或 NOT BETWEEN 的输入为 NULL，则结果是 UNKNOWN。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.5.3 用法示例

1.       
使用BETWEEN

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE TagId BETWEEN 255 AND 455

    从表Tag中检索出变量ID大于等于255且小于等于455的行，并提取TagName, TagId,
Description三列。

2.       
使用NOT
BETWEEN

如果要检索某个范围之外的值，则可以使用NOT BETWEEN

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE TagId NOT BETWEEN 255 AND 455

从表Tag中检索出变量ID小于255或大于455的行，并提取TagName, TagId,
Description三列

也可以使用关系比较运算符（<和>）来代替NOT BETWEEN

 

B 例：

 SELECT TagName, TagId, Description

 FROM Tag

 WHERE TagId < 255 OR TagId> 455

3.       
内含范围返回与两个指定值匹配的值。排除范围不返回与两个指定值匹配的值。BETWEEN 关键字指定用于搜索的内含范围。若要指定排除范围，请使用大于和小于运算符（>和<）

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE TagId BETWEEN 255 AND 455

    指定了BETWEEN关键字，搜索内含范围，即包括变量ID为255和455的行。如果指定搜索排除范围

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE TagId > 255 AND TagId < 455

    该代码示例所返回的结果与上面的代码示例不同，因为这些运算符不包括与限定范围的值相匹配的行，即不包括变量ID为255和455的行。

    若要想使用关系运算符来代替BETWEEN，可以使用>=和<=

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE TagId >= 25 AND TagId <= 45

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.6  组属条件

判定一个表达式的值是否属于一个集合。在KDB-SQL中构造组属条件时，应指定关键字IN判定一个表达式的值是否与一组值中的一个相等。组属判定的含义与集合运算中的成员判定一样，可以将用括号括起来的一组常数看作一个集合，IN关键字相当于判定表达式的结果是否是这个集合中的成员，如果是集合中的成员，结果为真，否则结果为假。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.6.1 语法格式

In\_predicate ::=

  
test\_expression [ NOT ] IN ( subquery | expression [, ……n ] )

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.6.2 参数说明

l       
test\_expression

搜索表达式可以是常量或列名，或是由运算符或子查询连接的列名、常量和函数的任意组合。

l       
subquery

是包含某列结果集的受限子查询，即如果没有指定TOP关键字，则不允许使用ORDER BY子句。可以返回一个或多个值。返回值必须和test\_expression具有相同的数据类型。

l       
expression [, ……n ]

一个表达式列表，通常情况下是一组常量。将值列表放在圆括号内。必须和test\_expression具有相同的数据类型。

l       
[ NOT ] IN

    根据表达式是包含在列表内还是排除在列表外，指定对表达式的搜索，确定给定的值是否与子查询或列表中的值相匹配。

n       
如果 test\_expression与 subquery 返回的任何值相等，或与逗号分隔的列表中的任何 expression相等，那么结果值就为TRUE。否则，结果值为FALSE。

n       
使用NOT IN对返回值取反。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.6.3 用法示例

1.       
指定值列表构造IN或NOT IN条件

 

B 例：

    SELECT TagName, TagId, Description

 FROM Tag

 WHERE DataType IN (2, 4, 6, 8, 10)

    从表Tag中检索数据类型与值列表中的任一值相等的行，并提取TagName, TagId, Description三列。

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE DataType NOT IN (2, 4, 6, 8, 10)

    与上例检索满足包含在列表内不同，本例是检索那些满足排除在列表外的行。指定的值列表中的值必须与被测试的值具有相同或相兼容的数据类型。

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE DataType IN ('INT',
'SMALLINT')

    由于DataType列的数据类型为TinyInt，无法转换为字符串型，导致查询失败。

 

2.       
对比OR和IN

可以使用逻辑运算符OR来代替IN构造组属条件，如对于前例，还可以使用OR

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE DataType = 2 OR DataType = 4 OR DataType = 6 OR DataType = 8
OR DataType = 10

3.       
将IN与子查询一起使用

下面的示例显示了指定SELECT子查询的IN条件

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorName IN (SELECT CollectorName

FROM Collector)

        在Collector表中检索所有的行的CollectorName，然后从表Tag中选择CollectorName与Collector检索结果匹配的所有变量的TagName, TagId,
Description。

4.       
将NOT IN与子查询一起使用

NOT IN将找到那些与子查询检索结果不匹配的行

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorName  NOT IN (SELECT CollectorName

FROM Collector)

从表Tag中选择那些CollectorName与Collector检索结果不匹配的所有变量的TagName, TagId,
Description。

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.7  模式匹配

判断某一列值是否与一个模式字符串类似。模式字符串由两列字符组成：一类是普通符号，在检索过程中需要与列值相应位置上的字符精确匹配，也就是事先能完全确定的内容；另一类是所谓的通配符，在查找过程中可与多种字符匹配，是事先不能完全确定的内容。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.7.1 语法格式

Like\_predicate
::=

  
test\_expression [ NOT ] LIKE string\_expression

                 
[ESCAPE 'escape\_character' ]

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.7.2 参数说明

l        
test\_expression

是任何有效的字符串数据类型表达式。

l        
string\_expression

是任何有效的字符串数据类型表达式，可以包含KDB-SQL所支持的有效通配符。

|  |  |  |
| --- | --- | --- |
| 通配符 | 含义 | 示例 |
| % | 包含零个或更多字符的任意字符串 | WHERE au\_lname LIKE 'Rin%'将查找满足au\_lname以Rin开头的所有作者 |
| \_（下划线） | 任何单个字符 | WHERE au\_lname LIKE '\_\_nger'将查找满足au\_lname以nger结尾的所有6个字符的作者 |

当使用 LIKE 进行字符串比较时，模式字符串中的所有字符都有意义，包括起始或尾随空格。如果查询中的比较要返回包含"abc "（abc 后有一个空格）的所有行，则将不会返回包含"abc"（abc 后没有空格）的列所在行。但是可以忽略模式所要匹配的表达式中的尾随空格。如果查询中的比较要返回包含"abc"（abc 后没有空格）的所有行，则将返回以"abc"开始且具有零个或多个尾随空格的所有行

l        
[ NOT ] LIKE

        确定给定的字符串是否与指定的模式匹配。模式可以包含常规字符和通配符字符。模式匹配过程中，常规字符必须与字符串中指定的字符完全匹配。然而，可使用字符串的任意片段匹配通配符。

n       
如果test\_expression匹配指定模式，LIKE将返回TRUE，否则返回FALSE。

n       
使用NOT LIKE对返回值取反

l        
ESCAPE

        允许在字符串中搜索通配符而不是将其作为通配符使用。例如，WHERE
au\_lname LIKE ‘Ringe/\_ \_’  ESCAPE ‘/’，将查找以Ringe\_开头，包含7个字符的au\_lname，其中第一个\_不是作为通配符使用，而是常规字符的一部分。

l        
escape\_character

是放在通配符前表示此为常规字符的字符。escape\_character没有默认值，并且必须仅包含一个字符。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.7.3 用法示例

1.       
使用带%通配符的[ NOT ] LIKE

%表示包含零个或更多字符的任意字符串

 

B 例：

SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName LIKE 'OPC\_SimuDevice.i1Val%'

从表Realtime中检索满足TagName以OPC\_Simudevice.i1Val开头的所有行，并提取TagName, DataTime,
DataValue三列。

 

B 例：

SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName  NOT  LIKE 'OPC\_SimuDevice.i1Val%'

    从表Tag中检索TagName不是以OPC\_Simudevice.i1Val开头的所有行。

2.       
使用带\_通配符的[ NOT ] LIKE

\_表示任何单个字符

 

B 例：

SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName LIKE '\_i1Val'

从表Realtime中检索满足TagName以i1Val结尾的共6个字符的所有行，并提取TagName, DataTime,
DataValue三列。

 

B 例：

SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName NOT LIKE '\_i1Val'

从表Realtime中检索TagName不是以i1Val结尾的或不是6个字符的所有行。

3.       
使用ESCAPE子句

使用ESCAPE可搜索包含一个或多个特殊通配符的字符串

 

B 例：

SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName LIKE 'OPC/\_Simudevice.i1Val.i1Val\_' 
ESCAPE('/')

从表Realtime中检索满足TagName以OPC\_Simudevice.i1Val.i1Val开头，共27个字符的所有行，并提取TagName,
DataTime,  DataValue三列。

4.       
同时使用%、\_和转义查找

 

B 例：

SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName LIKE '%/\_Simudevice.i1Val.i1Val\_' 
ESCAPE('/')

    在LIKE条件中同时制定了%、\_和转义查找，从表Realtime中检索满足LIKE条件的行。

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.8  带限定符的比较条件

KDB-SQL所支持的限定符包括ALL、SOME、ANY。这些限定符通常与比较运算符和子查询一起使用。以 > 比较运算符为例，>ALL 表示大于每一个值；换句话说，大于最大值。例如，>ALL
(1, 2, 3) 表示大于 3。>ANY 表示至少大于一个值，也就是大于最小值。因此 >ANY
(1, 2, 3) 表示大于 1。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.8.1 语法格式

Quantified\_comparison\_predicate
::=

  
expression { = | <> | > | >= | < | <= } { ALL | SOME | ANY }
subquery

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.8.2 参数说明

l        
expression

    是列名、常量、函数、变量、标量子查询，或者是由运算符或子查询连接的列名、常量和函数的任意组合。

l        
{ = | <> | > | >= | < | <= }

是比较运算符

l        
ALL

用标量值与单列集中的值进行比较。要使带有 >ALL 的子查询中的某行满足外部查询中指定的条件，引入子查询的列中的值必须大于由子查询返回的值的列表中的每个值。

n       
如果子查询检索的所有值满足比较运算，则返回TRUE。

n       
如果不是所有值都满足比较运算或子查询没有给外部语句返回行，则返回FALSE。

l        
SOME | ANY

用标量值与单列集中的值进行比较。>ANY (SOME)表示要使某一行满足外部查询中指定的条件，引入子查询的列中的值必须至少大于由子查询返回的值的列表中的一个值

n        
如果子查询检索的任何值都满足比较运算，则返回TRUE。

n        
如果子查询内没有值满足比较运算或子查询没有给外部语句返回行，则返回 FALSE。

l        
subquery

    是返回单列结果集的子查询。返回列的数据类型必须与expression的数据类型相同。是受限的SELECT语句，即如果没有指定TOP关键字，则不允许使用ORDER BY子句。如果子查询不返回任何值，那么整个查询将不会返回任何值。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.8.3 用法示例

1.       
使用ALL

    如果所有给定的比较对（expression, x）均为 TRUE，其中
x 是单列集中的值，则返回TRUE；否则返回FALSE。

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE CollectorType > ALL

           
(SELECT CollectorType

            
FROM Collector

            
WHERE CollectorId > 4)

扫描表Tag中第一行的CollectorType值，如果该值大于Collector的单列结果集中的每一个值，则往下扫描第二行，依次扫描每一行。只有那些CollectorType值大于Collector单列结果集的所有值的行，才能被检索到。

2.       
使用SOME | ANY

    对于 ANY 对 (expression,
x)，其中 x 是单列集中的值，当指定的比较是TRUE时，SOME
或 ANY 返回 TRUE。否则返回 FALSE。

 

        B 例：

           
SELECT TagName, TagId, Description

           
FROM Tag

           
WHERE CollectorType > SOME

               
(SELECT CollectorType

                
FROM Collector

                
WHERE CollectorId > 4)

        扫描表Tag中第一行的CollectorType值，如果该值大于Collector的单列结果集中的任一值，则往下扫描第二行，依次扫描每一行。只有那些CollectorType值不大于Collector单列结果集的所有值的行，才会被剔除。上例也可以指定关键字ANY，作用一样

        SELECT TagName, TagId,
Description

        FROM Tag

        WHERE CollectorType >
ANY

               
(SELECT CollectorType

                
FROM Collector

                
WHERE CollectorId > 4)

3.       
比较ANY | SOME
| ALL与IN

    =ANY 运算符与 IN 等效。下面的示例显示了使用 IN

 

    B 例：

        SELECT TagName, TagId,
Description

        FROM Tag

        WHERE CollectorName IN

           
(SELECT CollectorName

            
FROM Collector)

    也可以使用= ANY

 

    B 例：

        SELECT TagName, TagId,
Description

        FROM Tag

        WHERE CollectorName = ANY

           (SELECT
CollectorName

           
FROM Collector)

    但是，< >ANY 运算符与 NOT IN 有所不同：< >ANY 表示不等于 a，或不等于
b，或不等于 c。而 NOT IN 表示不等于 a，且不等于 b，且不等于 c。但 <>ALL 与 NOT IN 意义相同。下面的示例显示了这一点

 

    B 例：

        SELECT TagName, TagId,
Description

        FROM Tag

        WHERE CollectorName
<> ANY

           (SELECT
CollectorName

           
FROM Collector)

        内部查询找出所有现有的采集器名称，然后对于每个采集器，外部查询查找不属于该采集器的变量。

    但是，如果在该查询中使用 NOT IN，查询结果就不一样了

 

    B 例：

        SELECT TagName, TagId,
Description

        FROM Tag

        WHERE CollectorName NOT
IN

           (SELECT
CollectorName

           
FROM Collector)

还可以通过使用 <>ALL 运算符获得相同的结果，该运算符与 NOT IN 等效

 

    B 例：

        SELECT TagName, TagId,
Description

        FROM Tag

        WHERE CollectorName
<> ALL

           (SELECT
CollectorName

           
FROM Collector)

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.9  存在条件

与子查询一起使用，测试由子查询返回的行是否存在。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.9.1 语法格式

Exists\_predicate
::=

   [
NOT ] EXISTS subquery

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.9.2 参数说明

l       
[ NOT ] EXISTS

指定一个子查询，检测行的存在。如果子查询包含行，则返回TRUE。

l       
subquery

是一个受限的 SELECT 语句，如果没有指定TOP关键字，则不允许使用ORDER BY子句。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.9.3 用法示例

1.       
将EXISTS与无关子查询一起使用

EXISTS子句可以与无关子查询一起使用，但这么做的意义不大

下面的示例显示在子查询中指定静态值

 

B 例：

SELECT TagName

FROM Realtime

WHERE EXISTS (SELECT 2)

返回全部结果集，通过使用 EXISTS 仍取值为 TRUE。

 

B 例：

SELECT TagName

FROM Realtime

WHERE EXISTS (SELECT NULL)

返回全部结果集，通过使用 EXISTS 仍取值为 TRUE。

2.       
比较使用EXISTS和IN的查询

下面的示例显示了两个语义类似的查询。第一个查询使用 EXISTS 而第二个查询使用 IN。注意两个查询返回相同的信息

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE EXISTS

   (SELECT \*

    FROM Collector

    WHERE CollectorName = Tag.CollectorName

    AND CollectorID = 4)

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorName IN

     (SELECT CollectorName

      FROM Collector

      WHERE CollectorID = 4)

3.       
比较使用EXISTS和 = ANY

下面的示例显示两种查询方法：第一种方法使用EXISTS，第二种方法使用= ANY。注意这两种方法返回相同的信息

 

B 例：

    SELECT
TagName, TagId, Description

FROM Tag

WHERE EXISTS

       (SELECT \*

        FROM
Collector

       
WHERE Tag.CollectorName = Collector.CollectorName)

 

B 例：

    SELECT
TagName, TagId, Description

FROM Tag

WHERE CollectorName = ANY

      (SELECT CollectorName

       FROM Collector)

4.       
使用NOT EXISTS

    NOT EXISTS 的作用与 EXISTS 正相反。如果子查询没有返回行，则满足 NOT EXISTS 中的 WHERE 子句

   下面的示例显示了这一方法

 

        B 例：

               
SELECT TagName, TagId, Description

FROM Tag

WHERE NOT EXISTS

                
(SELECT \*

           
      FROM Collector

                 
WHERE CollectorName = Tag.CollectorName

                 
AND CollectorType = 4)

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.10 布尔谓词

判定取值为 TRUE、FALSE 或 UNKNOWN 的表达式。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.10.1          语法格式

Boolean\_predicate
::=

  
expression IS [ NOT ] { TRUE | FALSE | UNKNOWN }

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.10.2          参数说明

l        
expression

    是列名、常量、函数、变量、标量子查询，或者是由运算符或子查询连接的列名、常量和函数的任意组合。

l        
IS [ NOT ]

测试是否与指定的TRUE、FALSE或UNKNOWN一致。

l        
TRUE | FALSE | UNKNOWN

TRUE表示为真。FALSE表示为假。UNKNOWN表示为未知。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.10.3          用法示例

1.       
使用TRUE

可以指定关键字TRUE，判定某个条件是否为真

 

B 例：

        SELECT
TagName, TagId, Description

            FROM
Tag

WHERE (DataType > 5) IS TRUE

        如果比较条件DataType>5为真，则从表Tag中检索数据类型大于5的所有行。

 

B 例：

   
SELECT TagName, TagId, Description

FROM Tag

WHERE (DataType > 5) IS NOT TRUE

    如果比较条件DataType > 5不成立，即不为真，则从表Tag中检索数据类型小于等于5的所有行。

2.       
使用FALSE

可以指定关键字FALSE，判定某个条件是否为假

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE (DataType > 5) IS FALSE

如果比较条件DataType>5为假，即不成立，则从表Tag中检索数据类型小于等于5的所有行。

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE (DataType > 5) IS NOT FALSE

    如果比较条件DataType > 5为真，即条件成立，则从表Tag中检索数据类型大于5的所有行。

3.       
使用UNKNOWN

可以指定关键字UNKNOWN，判定某个条件的状态为未知。一般当条件中出现NULL时，会出现这种未知状态。

 

B 例：

SELECT TagName, TagId

FROM Tag

WHERE (Description<> NULL) IS UNKNOWN

在比较条件中，如果两个操作数中有一个或者两个都为 NULL，则条件的状态就为UNKNOWN。

 

B 例：

SELECT TagName, TagId

FROM Tag

WHERE (Description <> NULL) IS NOT UNKNOWN

指定了NOT关键字，对返回布尔结果求反。

Copyright
2013-2016

---

第7章 SQL语法

### 7.3.11 逻辑条件

前面所介绍的几类条件都属于简单条件，每个条件只能表达一个方面的含义，而当需要作一个复杂的查询时，查询的条件往往需要用几组条件才能表达出来。因此需要有一种方法将几个孤立的搜索条件组合成一个复杂条件。

在KDB-SQL中可以利用逻辑运算符将简单条件组合成更复杂的条件。KDB-SQL所支持的逻辑运算符共有三种，分别是NOT、AND和OR，其中AND和OR为二元运算符，连接两个搜索条件，NOT为一元运算符，只作用于一个搜索条件。当同一查询语句的WHERE子句出现多个逻辑运算时，三种逻辑运算的优先级从高到低的顺序为NOT、AND、OR，即最先计算NOT，再计算AND，最后计算OR。不过为了增强可读性，避免出现二义，最好加括号已明确表示出不同的优先顺序。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.11.1          语法格式

logic\_boolean\_predicate

   ::= NOT boolean\_expression

      
| boolean\_expression AND boolean\_expression

      
| boolean\_expression OR boolean\_expression

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.11.2          参数说明

l       
boolean\_expression

是任何有效的布尔表达式

l       
NOT

用来对某一表达式的布尔值进行反转，即当搜索条件为真时，运算结果为假；否则运算结果为真。

下面的表显示使用 NOT 运算符比较 TRUE 和
FALSE 值的结果

|  |  |
| --- | --- |
|  | NOT |
| TRUE | FALSE |
| FALSE | TRUE |
| UNKNOWN | UNKNOWN |

l       
AND

    组合两个条件并在两个条件都是 TRUE 时取值为 TRUE，即当两个搜索条件的结果都为真时，总的结果为真，否则结果为假。

    下表概括了使用 AND 运算符比较 TRUE 和
FALSE 时的结果

|  |  |  |  |
| --- | --- | --- | --- |
|  | TRUE | FALSE | UNKNOWN |
| TRUE | TRUE | FALSE | UNKNOWN |
| FALSE | FALSE | FALSE | FALSE |
| UNKNOWN | UNKNOWN | FALSE | UNKNOWN |

l       
OR

    组合两个条件并在任何一个条件是 TRUE 时取值为 TRUE，即当两个搜索条件均为假时，运算结果为假，否则运算结果为真。当在一个语句中使用多个逻辑运算符时，在 AND 运算符之后求 OR 运算符的值。但是，通过使用括号可以更改求值的顺序。

    下表概括了使用OR运算符比较
TRUE 和 FALSE 时的结果

  

|  |  |  |  |
| --- | --- | --- | --- |
|  | TRUE | FALSE | UNKNOWN |
| TRUE | TRUE | TRUE | TRUE |
| FALSE | TRUE | FALSE | UNKNOWN |
| UNKNOWN | TRUE | UNKNOWN | UNKNOWN |

Copyright
2013-2016

---

第7章 SQL语法

#### 7.3.11.3          用法示例

1.       
使用NOT

当搜索条件为真时，运算结果为假；否则运算结果为真。

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE NOT DataType > 5

    从表DataType中检索年龄不大于，即小于等于5的所有行，并提取TagName, TagId和Description三列。

2.       
使用AND

当两个搜索条件的结果都为真时，总的结果为真，否则结果为假。

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE DataType > 5 AND SourceAddress = 'Simudevice.i1Val.i1Val0'

    从表Tag中检索数据类型大于5并且数据源地址为Simudevice.i1Val.i1Val0的所有变量的信息。两个条件必须都成立的行才能被检索到。

3.       
使用OR

当两个搜索条件均为假时，运算结果为假，否则运算结果为真。

 

B 例：

    SELECT TagName, TagId, Description

    FROM Tag

    WHERE DataType > 5 OR SourceAddress = 'Simudevice.i1Val.i1Val0'

    从表Tag中检索数据类型大于5，或者数据源地址为Simudevice.i1Val.i1Val0的所有变量的信息。与上例不同，返回的结果集既包含数据类型大于5，但数据源地址不是Simudevice.i1Val.i1Val0的变量，也包含数据类型小于5，但数据源地址为Simudevice.i1Val.i1Val0的变量。

4.       
组合使用NOT、AND、OR

当出现多个逻辑运算时，三种逻辑运算的优先级从高到低的顺序为NOT、AND、OR，即最先计算NOT，再计算AND，最后计算OR。但是，通过使用括号可以更改求值的顺序。

 

B 例：

    SELECT
TagName, TagId, Description, CollectorName

FROM Tag

WHERE DataType
> 5 OR SourceAddress = 'Simudevice.i1Val.i1Val0' AND CollectorID < 4

检索时，首先检索数据源地址为Simudevice.i1Val.i1Val0，并且所属采集器ID小于4的所有变量，然后再从剩余的行中检索数据类型大于5的所有变量，最后的结果集是这两个检索结果的合集。

 

B 例：

SELECT TagName, TagId, Description, CollectorName

FROM Tag

WHERE (DataType
> 5 OR SourceAddress = 'Simudevice.i1Val.i1Val0')  AND CollectorID <
4

与上例不同，由于使用了括号改变了求值的顺序：首先检索数据类型大于5，或者数据源地址为Simudevice.i1Val.i1Val0的所有变量；然后从检索的结果集中，再选择那些所属采集器ID小于4的变量。

 

B 例：

    SELECT
TagName, TagId, Description, CollectorName

FROM Tag

WHERE NOT
DataType > 5 OR SourceAddress = 'Simudevice.i1Val.i1Val0' AND CollectorID
< 4

    与前例不同的是，本例添加了NOT关键字：首先检索数据类型不大于5的所有变量，然后从剩余的行中选择那些数据源地址为Simudevice.i1Val.i1Val0，且所属采集器ID小于4的所有变量，最后的结果集为两次检索结果集的合集。

 

B 例：

SELECT TagName, TagId, Description, CollectorName

FROM Tag

    WHERE NOT DataType > 5
OR (SourceAddress = 'Simudevice.i1Val.i1Val0' AND CollectorID < 4)

    与上例的结果集相同，只是在检索时，由于使用了括号，会先检索满足括号内条件的行。

 

B 例：

           
SELECT TagName, TagId, Description, CollectorName

           
FROM Tag

    WHERE NOT (DataType >
5 OR SourceAddress = 'Simudevice.i1Val.i1Val0') AND CollectorID < 4

    由于使用了括号，检索时会先选择那些满足括号内条件的行：首先计算括号内条件的布尔值，然后对其求反，即检索那些数据类型小于等于5，且数据源地址为Simudevice.i1Val.i1Val0的所有变量；然后从检索的结果集中选择所属采集器ID小于4的变量信息。

Copyright
2013-2016

---

第7章 SQL语法

## 7.4     如何使用子查询

Copyright
2013-2016

---

第7章 SQL语法

### 7.4.1  子查询概述

从最简单的意义来看，子查询（subquery）是嵌套在另一个KDB-SQL语句中并提供嵌套它的语句所使用的数据的SELECT语句。它返回单个值且嵌套在 SELECT语句或其它子查询中。任何允许使用表达式的地方都可以使用子查询。子查询也称为内部查询或内部选择，而包含子查询的语句也称为外部查询或外部选择。从形式上看，子查询语句与一般的SELECT语句没有太大的差别，主要差别是子查询语句必须用括号括起来，再有一个限制是，子查询语句中不得带有ORDER BY子句，除非指定了TOP关键字。

在KDB-SQL中有三种基本的子查询。它们是：

l       
在通过 IN 引入的列表或者由 ANY 或 ALL 修改的比较运算符的列表上进行操作。

l       
通过无修改的比较运算符引入，并且必须返回单个值。

l       
通过 EXISTS 引入的存在测试。

Copyright
2013-2016

---

第7章 SQL语法

### 7.4.2  子查询类型

子查询一般可分为相关子查询和无关子查询。

l        
相关子查询（correlated
subquery）

指子查询的处理要依赖外部查询为它提供的值。子查询使用从外部查询接收到的值来执行，由子查询返回的数据反向插入外围查询中进行比较。

处理包含相关子查询的查询语句，需要外部查询和子查询交叉进行：先用外部查询定位一行数据，这样子查询的引用就有了确定的值，然后执行子查询，得到结果后判定外层查询定位的那行数据是否满足条件，而后再取下一行数据，再执行子查询，直到外部查询涉及的数据都检查完成为止。

下面的示例显示了相关子查询的例子

 

B 例：

       
SELECT T.TagName, T.TagId

FROM Tag as T

WHERE CollectorName =

                     (SELECT
CollectorName

                      FROM
Collector AS C

      
       WHERE T.CollectorType = C.CollectorType)

   
在子查询的WHERE子句中引用了外层查询的值T.CollectorType。

l        
无关子查询（noncorrelated
subquery）

是在外部查询之前进行判定，由子查询返回的数据被外部查询使用。无关子查询的处理过程相对来说比较简单，一般可以分为两步：

n       
首先处理子查询，得到查询结果。

n       
然后以子查询的结果作为常量再处理外部查询。

因此可以看出，包含无关子查询的查询语句，其内部查询与外部查询的处理是独立的，可以顺序进行。

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorName IN

   (SELECT CollectorName

    FROM Tag

    WHERE CollectorType > 4)

在子查询中使用的CollectorType属于子查询中的Tag表，而非使用的外部查询的Tag表的CollectorType。

Copyright
2013-2016

---

第7章 SQL语法

### 7.4.3  子查询应用

在KDB-SQL中，子查询可以用在SELECT语句的WHERE子句中作为条件的一部分，还可以用在SELECT语句的HAVING子句中。

下面分别介绍子查询在这些语句中的应用。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.4.3.1 WHERE子句中的子查询

子查询用的最频繁的场合是SELECT语句的WHERE子句，作为搜索条件的一部分。一般有以下三种形式：

l        
将子查询的结果与一个表达式的值比较。

l        
判断子查询的结果中是否包含某一表达式的值。

l        
判断子查询的结果是否为空。

子查询的返回结果必须是原子值，即不能是由多个属性值组成的。其可以分为三种情形：

l        
无值

表示子查询没有找到任何一行，等价于NULL值。

l        
单值

表示子查询返回一个聚合函数的值或者只找到一个记录在某一列上的值，与一个数值或字符常量等价。

l        
多值

表示子查询找到多个记录在某一列上的取值，等价于一个集合。

   

 

 

 

 

 

 

 

1.       
由IN引入的子查询

子查询的一种形式是用一个表达式与由子查询返回的一组值作比较，判定表达式的值是否包含在子查询的返回值中。如果表达式的值包含在子查询返回的值中，那么该条件的结果为真。这可以通过与IN一起使用子查询来实现。通过 IN（或
NOT IN）引入的子查询结果是一列零值或更多值。子查询返回结果之后，外部查询将利用这些结果。

语句格式通常采用：

WHERE expression
[ NOT ] IN (subquery)

使用这种形式必须注意使表达式的值与子查询的返回值数据类型相兼容，具有可比性。

下面的示例显示了一个由IN引入的子查询

 

B 例：

       
SELECT CollectorID, CollectorType, CollectorStatus

FROM Collector

WHERE CollectorName IN

         
(SELECT CollectorName

          
FROM Tag

      
WHERE SourceAddress = 'Simudevice.i1Val.i1Val0')

    该查询是一个无关子查询，首先，内部查询返回数据源地址为Simudevice.i1Val.i1Val0的采集器名称。然后，这些值被代入外部查询中，在 Collector中查找与上述采集器名相配的采集器信息。

与IN子句一起使用的子查询既可以是无关的，也可以是相关的。

 

B 例：

SELECT TagName, TagId, Description

FROM Tag AS T

WHERE CollectorName IN

( SELECT CollectorName

FROM Collector AS C

WHERE T.CollectorType = C.CollectorType)

        该查询是一个相关子查询，从表Tag中查询那些变量所属采集器类型与现有采集器的类型相同的变量信息。

关键字IN前面还可以加上限定词NOT，表示限定不包含在子查询返回结果中的值。使用NOT IN可以非常容易的实现排除某些值的查询要求。通过 NOT IN 关键字引入的子查询也返回一列零值或更多值

下面的示例显示了一个使用NOT IN的例子

 

B 例：

SELECT CollectorType,
CollectorID, CollectorStatus

FROM Collector

WHERE CollectorName
NOT IN

         
(SELECT CollectorName

          
FROM Tag

       WHERE SourceAddress =
'Simudevice.i1Val.i1Val0')

2.       
由未修改的比较运算符引入的子查询

子查询可由一个比较运算符（=、<
>、>、> =、<、或 < =）引入。由未修改的比较运算符（后面不跟 ANY 或 ALL 的比较运算符）引入的子查询必须返回单个值而不是值列表。如果子查询的结果为空值，整个查询的结果也为空。

语句格式通常采用：

WHERE expression
comparison\_operator (subquery)

要使用由无修改的比较运算符引入的子查询，必须对数据和问题的本质非常熟悉，以了解该子查询实际是否只返回一个值。

下面的示例显示了通过比较运算符=引入的子查询

 

B 例：

        SELECT
CollectorType, CollectorID, CollectorStatus

FROM Collector

WHERE CollectorName =

(SELECT CollectorName

           FROM
Tag

      
WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0')

    变量名为OPC\_Simudevice.i1Val.i1Val0的变量只属于一个采集器，而要找到这个采集器的信息，就可以通过由简单的等号 (=) 比较运算符引入的子查询查找。但是如果变量名为OPC\_Simudevice.i1Val.i1Val0的变量属于多个采集器，则会导致查询失败。此时可以使用IN 表达式（= ANY 也可以）代替 = 比较运算符。

由未修改的比较运算符引入的子查询经常包括聚合函数，因为这些子查询要返回单个值。下面的示例显示了这样一个例子

 

B 例：

        SELECT
TagName, TagId, Description

FROM Tag

WHERE CollectorName >

(SELECT MIN(CollectorName)

 FROM Collector)

    因为由未修改的比较运算符引入的子查询必须返回单个值，所以除非知道 GROUP BY 或 HAVING 子句本身会返回单个值，否则不能包括 GROUP BY 或 HAVING 子句。下面的示例显示了这样一个例子

 

    B 例：

        SELECT
CollectorType, CollectorID, CollectorStatus

FROM Collector

WHERE CollectorName >

(SELECT  MIN(CollecotorName)

           FROM
Tag

           GROUP
BY SourceAddress

           HAVING
SourceAddress = 'Simudevice.i1Val.i1Val0')

    上面的示例都是无关子查询，下面的示例显示了一个可以使用返回单个值的相关子查询。

 

    B 例：

        SELECT
CollectorType, CollectorID, CollectorStatus

FROM Collector AS C

WHERE CollectorName =

          (SELECT
MAX(CollectorName)

           FROM
Tag AS T

           WHERE
C.CollectorType = T.CollectorType)

3.       
由修改的比较运算符引入的子查询

在KDB-SQL中可以用 ALL 或
ANY 、SOME关键字修改引入子查询的比较运算符。通过未修改的比较运算符引入的子查询必须只返回单个值，而通过修改的比较运算符引入的子查询则可以返回一列零值或更多值。

语句格式通常采用：

WHERE expression
comparison\_operator  [ ALL | ANY | SOME ] (subquery)

l        
使用ALL

        要使带有 ALL 的子查询中的某行满足外部查询中指定的条件，引入子查询的列中的值与由子查询返回的值的列表中的每个值都满足指定的条件。也就是说，表达式的值与子查询返回的全部值之间都满足关系运算符指定的比较关系。比如，对于x > ALL (SELECT y……)，表示的含义是“x大于子查询选定的每一个y值”。

        对于带有ALL的搜索条件的判断方法是，将表达式的计算值依次与子查询的返回结果作比较。在比较的过程中，如果有一次的比较结果为假，则搜索条件的结果也为假；如果全部比较完后，结果均为真，则搜索条件的结果也为真；如果子查询未返回结果，则搜索条件的结果还为真。

    下面的示例显示了这样一个示例

 

     B 例：

             
SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorType >ALL

(SELECT CollectorType

                
FROM Collector

            
WHERE CollectorID < 4)

l      
使用ANY或SOME

要使带有ANY 的子查询中的某行满足外部查询中指定的条件，引入子查询的列中的值必须至少与由子查询返回的值的列表中的一个值满足指定的条件。也就是说，表达式的值与子查询返回的部分值之间满足关系运算符指定的比较关系。比如，对于x > ANY (SELECT y……)，表示的含义是“x大于子查询选定的某些y值”。

对于带有ANY或SOME的搜索条件的判断方法是，将表达式的值依次与子查询的返回结果作比较，如果某次的比较结果为真，则搜索条件的结果也为真；如果全部比较完成之后，各次的比较结果均为假，则搜索条件的结果也为假；如果子查询未返回结果，则搜索条件的结果为假。

下面的示例显示了这样一个例子。

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorType >SOME

(SELECT CollectorType

      
FROM Collector

      
WHERE CollectorID < 4)

4.       
由EXISTS引入的子查询

        使用 EXISTS 关键字引入一个子查询时，就相当于进行一次存在测试。外部查询的
WHERE 子句测试子查询返回的行是否存在。子查询实际上不产生任何数据；它只返回 TRUE 或
FALSE 值，用于判定子查询是否能返回值。若子查询的返回结果不为空，则带EXISTS的搜索条件的结果为真；否则若子查询不返回行，则搜索条件的结果为假。

        与其他的运算符不同，带有EXISTS的搜索条件并不需要真正取得子查询的整个返回结果，而只需了解子查询是否为空。例如，对于EXISTS来说，在执行子查询的过程中，如果发现有一行数据能满足子查询的条件，就知道了EXISTS的结果为真，也就没有必要继续完成子查询了。

        语句格式通常采用：

    WHERE [ NOT ] EXISTS (subquery)

    使用EXISTS引入的子查询在以下几个方面与其他子查询略有不同：

l      
EXISTS 关键字前面没有列名、常量或其它表达式。

l      
由 EXISTS 引入的子查询的选择列表通常几乎都是由星号 (\*) 组成。由于只是测试是否存在符合子查询中指定条件的行，所以不必列出列名。

        下面的示例显示了查找所有出版商业书籍的出版商的名称：

 

B 例：

       
SELECT CollectorType, CollectorID, CollectorStatus

FROM Collector

WHERE EXISTS

          
(SELECT \*

           
FROM Tag

           
WHERE CollectorName = Collector.CollectorName

           
AND SourceAddress = 'Simudevice.i1Val.i1Val0')

        若要确定该查询的结果，请按照顺序考虑每个数据地址的名称。该值是否会使子查询至少返回一行？换句话说，该值是否会使存在测试的计算值为 TRUE？如果可以使存在测试为TRUE，则该值则是所选值之一。

    尽管一些使用 EXISTS 表示的查询不能以任何其它方法表示，但所有使用 IN 或由ANY
或 ALL 修改的比较运算符的查询都可以通过 EXISTS 表示。

以下是使用 EXISTS 和等效的备选方法的查询示例。

 

B 例：

    SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorName = ANY

       (SELECT CollectorName

        FROM Collector)

    -----------或

    SELECT TagName, TagId, Description

FROM Tag

WHERE exists

      (SELECT \*

       FROM Collector

       WHERE Tag.CollectorName =
Collector.CollectorName)

NOT EXISTS 与 EXISTS 的工作方式类似，只是如果子查询不返回行，那么使用 NOT EXISTS 的 WHERE 子句会得到令人满意的结果

下面的示例显示了使用NOT EXISTS引入子查询查找作者所居住的、但没有出版商居住的所有城市。

 

B 例：

SELECT TagName, TagId, Description

FROM Tag

WHERE NOT EXISTS

      (SELECT \*

       FROM Collector

       WHERE Tag.CollectorName =
Collector.CollectorName)

Copyright
2013-2016

---

第7章 SQL语法

#### 7.4.3.2 用于代替表达式的子查询

在 KDB-SQL 中，除了在 ORDER BY 列表中以外，在 SELECT中任何可以使用表达式的地方都可以使用子查询来替代。

下面的示例显示了在使用表达式的地方使用子查询来替代。

 

B 例：

    SELECT TagName, TagId, Description,

(SELECT Count(\*) FROM Collector) AS CollectorCount,

           
DataLength - (SELECT AVG(DataLength) FROM Tag)  AS  Difference

FROM Tag

WHERE SourceAddress = 'Simudevice.i1Val.i1Val0'

该查询查找数据源地址为Simudevice.i1Val.i1Val0的变量名、变量ID、变量描述，现有的采集器个数，以及数据长度与平均数据长度之间的差额。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.4.3.3 HAVING子句中的子查询

尽管子查询经常用在SELECT语句的WHERE子句中，但它也可以用在HAVING子句中。当子查询用在HAVING子句时，它作为分组条件的一部分限制某些分组的输出。在HAVING子句中引入的子查询的处理方式与在WHERE子句中的一样，也按子查询的特征分别处理。若为无关子查询，则单独执行子查询；若为相关子查询，则与外层语句交叉处理。

下面的示例显示了为外部查询中定义的每一个组各评估一次子查询：

 

B 例：

    SELECT CollectorName, Count(TagName) AS Total

FROM Tag

GROUP BY CollectorName

HAVING CollectorName =

      (SELECT MIN(CollectorName)

       FROM Collector)

下面的示例显示了一个引入相关子查询的HAVING子句：

 

B 例：

    SELECT CollectorName, Count(TagName) AS Total

FROM Tag AS T

GROUP BY CollectorName

HAVING CollectorName =

      (SELECT MIN(CollectorName)

       FROM Collector AS C

       WHERE T.CollectorType =
C.CollectorType)

Copyright
2013-2016

---

第7章 SQL语法

#### 7.4.3.4 在子查询中限定列名

许多其中的子查询和外部查询引用同一表的语句可被表述为自联接（将某个表与自身联接，请参见使用联接）。在这种情况下，必须使用表的别名（也称为相关名）明确指定要使用哪个表引用，以避免列的二义性。

1.       
在无关查询中，一般的规则是，语句中的列名通过同级 FROM 子句中引用的表来隐性限定。

在下面的示例中，显示了无关子查询中存在的隐性限定：

 

B 例：

    SELECT TagName, TagId, Description

FROM Tag

WHERE CollectorName NOT IN

      (SELECT CollectorName

       FROM Collector

       WHERE CollectorID > 4)

    在该查询中，外部查询的 WHERE 子句中的 CollectorName列是由外部查询的 FROM 子句中的表名（Tag）隐性限定的。对子查询的选择列表中 CollectorName的引用则通过子查询的 FROM 子句（即通过 Collector表）来限定。

2.       
相关子查询可以用于从外部查询引用的表中选择数据之类的操作中。子查询和外部查询引用同一表的语句可被表述为自联接（将某个表与自身联接）。在这种情况下，必须使用表的别名（也称为相关名）明确指定要使用哪个表引用。

下面的示例显示了这种类型的查询：

 

 B 例：

     SELECT T1.CollectorName

FROM Tag AS T1

WHERE T1.CollectorName IN

        (SELECT T2.CollectorName

         FROM Tag AS T2

         WHERE
T1.CollectorType <> T2.CollectorType)

      在该查询中，需要用别名来区分在其中出现
Tag表的两个不同角色。显式别名清楚地表明，对子查询中
Tag的引用并不等同于外部查询中的引用。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.4.3.5 嵌套子查询

正如子查询可以嵌套在标准查询中一样，它也可以嵌套在另一个子查询中。子查询可以包括一个或多个子查询。将子查询嵌套起来可以表达非常繁杂的逻辑关系。对于所提供的嵌套来说，唯一的限制就是性能。随着对子查询一层接一层的嵌套，查询的性能也会不断下降。

下面的示例显示了一个使用三层嵌套的查询语句：

 

B 例：

   
SELECT TagName, DataTime, DataValue

FROM Realtime

WHERE TagName IN

     
(SELECT TagName

      
FROM Tag

      
WHERE CollectorType IN

   
     (SELECT CollectorType FROM Collector

         
WHERE CollectorName = 'OPCCollector'))

    在该查询中，最内部的查询返回采集器名称为OPCCollector的采集器类型，再上一层的查询使用这些类型进行评估，并返回变量名。最后，外部查询使用变量名查找变量的实时数据。

下面的示例显示了将相关子查询和无关子查询混合在同一个语句中：

 

 B 例：

      SELECT TagName, DataTime, DataValue

FROM Realtime AS R

WHERE TagName IN

        (SELECT TagName

         FROM Tag AS T

         WHERE ColletorName
IN

           (SELECT
CollectorName

FROM Collector AS C

           
WHERE T.CollectorType = C.CollectorType))

      在该查询中，最内部的子查询使用了上一层的Tag表的CollectorType值。

Copyright
2013-2016

---

第7章 SQL语法

## 7.5     如何使用联接

Copyright
2013-2016

---

第7章 SQL语法

### 7.5.1  联接概述

在许多情况下，当查询数据库时，并不是所有需要的数据都存储在一个表中。即使所有需要的数据都保存在单独的一个表中，可能也需要将它和存储在其他表中的数据进行比较来检索正确的行。在许多情况下，数据通过很多表来分配，以删除数据中的冗余部分。

要想使用单个查询从许多表中返回数据，就需要使用联接。通过联接，可以根据各个表之间的逻辑关系从两个或多个表中检索数据。在SELECT语句中介绍的SELECT语句与使用了联接的SELECT语句的主要差别在于：联接语句的FROM子句中指定了多个表。典型的非联接SELECT语句如下所示：

SELECT \*

FROM Realtime

而使用了联接的典型SELECT语句如下所示：

SELECT \*

FROM Realtime, Tag

使用了联接的SELECT语句将返回这两个表的每一种可能的组合。如果Realtime表包含了8行，Tag表包含了10行，则查询将返回80行数据。

要想编写有意义的联接，需要注意两方面：

l        
每个表中的列都可以兼容联接

不同表中的列如果包含相同的意义，就可以认为它们是联接兼容的。只包含相同形式的数据是不充分的。仅因为这两个列包含整数或是VARCHAR（10）形式的数据，并不意味着在这两列中的数据有任何意义上的联系。

l        
联接的条件

要想编写一个使用了联接的SELECT语句，就需要联接条件。从根本上说，联接条件是WHERE子句中的搜索条件，表明了所要联接的表之间的关系。联接条件通过指定每个表中要用于联接的列和指定比较各列的值时要使用的逻辑运算符（=、<>
等）定义了两个表在查询中的关联方式。在联接多于两个表的情况下，就需要多个联接条件。在联接条件中，空值并不判定为“真”。如果联接条件中两列里的任何一列包含空值，那一行就将被删除，甚至当这两列都是空值时也是如此。空值在这种情况下被认为是包含了未知的值，因而不能将它视为等同于另外一个值（大于或小于另外一个数）。

当使用联接时，首先被联接的笛卡尔积生成了。笛卡尔积是每一个表中的所有行与另一个表中所有行的组合。如果每个表都只包含一行，笛卡尔积也将只包含一行。如果每一个表都包含8行，笛卡尔积将会有64行。笛卡尔积中行的数量是由联接中所有表中的行数相乘得到的。为了查询，被联接的表的笛卡尔积也可作为单独一个表使用。新的表保存了联接表中行的顺序。下一步就是将WHERE子句运用到表中的所有行中。WHERE子句为“真”的所有行都将保留。接下来，如果GROUP BY子句存在，表中余下的行将根据GROUP BY子句指定的列里的公共值来排序。如果在选择列表中出现了聚合函数，它们将被使用，表也将被包含聚合值的表所替换。如果在查询中有HAVING子句，它将应用到用GROUP BY子句创建的组中。与HAVING子句不一致的行将被删除。最后，选择列表将被应用到余下的表中，表达式被判定，不在选择列表中的列将被删除，因而只留下最终的查询结果。

Copyright
2013-2016

---

第7章 SQL语法

### 7.5.2  联接类型

在KDB-SQL中，联接语法格式如下：

FROM
first\_table join\_type second\_table [ ON (join\_condition) ]

l       
join\_type

指定所执行的联接类型：内联接、外联接或交叉联接。

l       
join\_condition

定义联接条件。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.5.2.1 内联接

内联接使用比较运算符根据每个表共有的列的值匹配两个表中的行。仅当至少有一个同属于两表的行符合联接条件时，内联接才返回行。内联接消除与另一个表中的任何行不匹配的行。当创建内联接时，包含 NULL 的列不与任何值匹配，因此不包括在结果集内。空值不与其它的空值匹配。

KDB-SQL所支持的内联接语法格式如下：

FROM
first\_table [ NATURAL ] [ INNER ] JOIN second\_table [ ON (join\_condition) ]

参数说明如下：

l        
NATURAL

指定自然联接。如果省略掉NATURAL关键字，则为非自然联接。自然联接是一种特殊的联接。一般来说，在自然联接（NATURAL JOIN）中，查询结果里只出现一列来代表用在联接条件中的两个联接兼容列。

l        
INNER

   指定返回所有相匹配的行对。废弃两个表中不匹配的行。如果未指定联接类型，则这是默认设置。

l        
ON

如果指定非自然联接，则必须指定ON联接条件。

l        
join\_condition

   指定联接条件。

下面是内联接的一个示例：

 

B 例：

SELECT \*

FROM Tag AS T inner JOIN Collector AS C

    ON T.CollectorName =
C.CollectorName

ORDER BY 1 DESC

该内联接为相等联接。它返回两个表中的所有列，但只返回在联接列中具有相等值的行。

也可以联接两个不相等的列中的值。用于内联接的运算符和谓词同样也可用于不相等联接。

下面的示例显示了一个大于（>）联接

 

B 例：

    SELECT T.CollectorName, T.CollectorType, C.CollectorName,
C.CollectorType

FROM Tag T INNER JOIN
Collector C

    ON T.CollectorType
> C.CollectorType

WHERE T.SourceAddress
= 'Simudevice.i1Val.i1Val0'

ORDER BY 1 ASC, 2 ASC

通常不等联接（<>）只有与自联接同时使用才有意义。下面的示例显示了使用不等联接和自联接的组合查找的例子

 

B 例：

    SELECT T1.TagName, T1.TagId

FROM Tag T1 JOIN Tag
T2

    ON T1.DataType
<> T2.DataType

WHERE T1.SourceAddress = T2.SourceAddress

由于INNER关键字可选，所以该联接没有显示指定INNER。

下面的示例显示了一个自然联接的例子：

 

B 例：

SELECT \*

FROM Tag AS T INNER JOIN Collector AS C

ON T.CollectorName = C.CollectorName

在该联接查询的结果集中，CollectorName列出现两次。由于重复相同的信息没有意义，因此可通过使用自然联接消除两个相同列中的一个

SELECT \*

FROM Tag AS T NATURAL JOIN Collector AS C

在自然联接中，不必再指定ON联接条件。在查询的结果集中，CollectorName列只出现了一次。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.5.2.2 外联接

在联接两张表时，左边表可能有某些行在右边表中找不到匹配的数据，右边表也可能有些行在左边表中找不到匹配数据，如果不做特殊处理，这些行是不会出现在输出结果中。如果希望在作表联接时能够列出某张表的全部数据，而不管它是否能与另一张表匹配，则需要用到外联接。两张表在作外联接时，一张表处于支配地位，称为支配表；另一张表处于被支配地位，称为被支配表。外联接的结果由两部分组成，一部分由来自两张表的满足联接条件的行匹配组成，另一部分由来自支配表中无法满足联接条件的行组成，结果中的第二部分数据在被支配表上的分量为NULL。

与内联接不同，内联接仅显示两个联接表中的匹配行的联接，外联接还包括在联接表中没有相关行的行的联接。外联接可以是左向外联接、右向外联接或完整外部联接。

1.        
左向外联接

    左向外联接的结果集包括 LEFT OUTER 子句中指定的左表的所有行，而不仅仅是联接列所匹配的行。如果左表的某行在右表中没有匹配行，则在相关联的结果集行中右表的所有选择列表列均为空值。

KDB-SQL所支持的内联接语法格式如下：

FROM left\_table [ NATURAL ] LEFT [ OUTER ] JOIN right\_table

[ ON ( join\_condition) ]

参数说明如下：

l       
left\_table

指定左表，在左外向联接中，左表为支配表。

l       
right\_table

指定右表，在左外向联接中，右表为被支配表。

l       
NATURAL

指定为自然联接。如果省略掉NATURAL关键字，联接为非自然联接。

l       
LEFT

指定为左外向联接。

l       
OUTER

可选关键字，显示指定为外联接。

l       
ON

   
如果为非自然联接，则必须指定ON关键字；否则不必指定。

l       
join\_condition

   
如果为非自然联接，则必须指定联接条件；否则不必指定。

下面是左外向联接的一个示例：

 

B 例：

       
SELECT T.TagName, T.TagId, C.CollectorName

FROM Tag T INNER JOIN
Collector C

    ON T.CollectorType = C.CollectorType

    该内联接在CollectorType列上联接 Tag表和 CollectorType表.

       
SELECT T.TagName, T.TagId, C.CollectorName

FROM Tag T LEFT OUTER
JOIN Collector C

    ON T.CollectorType = C.CollectorType

    在该左外向联接中，不管是否与 Collector 表中的 CollectorType列匹配，LEFT OUTER JOIN 均会在结果中包含 Tag表的所有行。但在结果集中，没有与Tag表的行相匹配的CollectorName均为空。

2.        
右向外联接

        右向外联接是左向外联接的反向联接。将返回右表的所有行。如果右表的某行在左表中没有匹配行，则将为左表返回空值。

KDB-SQL所支持的内联接语法格式如下：

FROM left\_table [ NATURAL ] RIGHT [ OUTER ] JOIN right\_table

[ ON (join\_condition) ]

    参数说明如下：

l        
left\_table

指定左表，在右外向联接中，左表为被支配表。

l        
right\_table

   
指定右表，在右外向联接中，右表为支配表。

l        
NATURAL

指定为自然联接。如果省略掉NATURAL关键字，联接为非自然联接。

l        
RIGHT

指定为右外向联接。

l        
OUTER

   
可选关键字，显示指定为外联接。

l        
ON

   
如果为非自然联接，则必须指定ON关键字；否则不必指定。

l        
join\_condition

如果为非自然联接，则必须指定联接条件；否则不必指定。

下面是右外向联接的一个示例：

 

B 例：

        SELECT T.TagName, T.TagId, C.CollectorName

FROM Tag AS T RIGHT
OUTER JOIN Collector AS C

    ON T.CollectorType = C.CollectorType

   使用谓词（如将联接与常量比较）可以进一步限制外联接。下面的示例显示了这样的一个右外向联接

 

B 例：

       
SELECT T.TagName, T.TagId, C.CollectorName

FROM Tag AS T RIGHT
OUTER JOIN Collector AS C

    ON T.CollectorType = C.CollectorType AND
T.DataType > 4

    该联接与上例联接相同，但消除了数据类型小于4的变量。

3.        
完整外部联接

       完整外部联接返回左表和右表中的所有行。当某行在另一个表中没有匹配行时，则另一个表的选择列表列包含空值。如果表之间有匹配行，则整个结果集行包含基表的数据值。

KDB-SQL所支持的内联接语法格式如下：

FROM left\_table [ NATURAL ] FULL [ OUTER ] JOIN right\_table

[ ON (join\_condition) ]

参数说明如下：

l       
left\_table

指定左表。

l       
right\_table

指定右表。

l       
NATURAL

指定为自然联接。如果省略掉NATURAL关键字，联接为非自然联接。

l       
FULL

指定为完全外部联接。

l       
OUTER

   
可选关键字，显示指定为外联接。

l       
ON

   
如果为非自然联接，则必须指定ON关键字；否则不必指定。

l       
join\_condition

如果为非自然联接，则必须指定联接条件；否则不必指定。

下面是完整外部联接的一个示例：

 

B 例：

    SELECT T.TagName, T.TagId, C.CollectorName

FROM Tag T FULL OUTER JOIN Collector C

    ON T.CollectorType =
C.CollectorType

Copyright
2013-2016

---

第7章 SQL语法

#### 7.5.2.3 交叉联接

交叉联接返回左表中的所有行，左表中的每一行与右表中的所有行组合。交叉联接也称作笛卡尔积。

KDB-SQL所支持的内联接语法格式如下：

FROM left\_table
CROSS JOIN right\_table [ WHERE (join\_condition) ]

参数说明如下：

l       
CROSS

   指定联接为交叉联接

l       
WHERE

   在交叉联接中，联接条件由WHERE关键字指定，不能使用关键字ON指定。

l       
join\_condition

   指定联接条件。

没有 WHERE 子句的交叉联接将产生联接所涉及的表的笛卡尔积。第一个表的行数乘以第二个表的行数等于笛卡尔积结果集的大小。下面的示例显示了一个简单的交叉联接：

 

B 例：

   
SELECT TagName, TagId, CollectorID

FROM Tag CROSS JOIN Collector

ORDER BY 1 DESC

如果添加一个 WHERE 子句，则交叉联接的作用将同内联接一样。

 

B 例：

   
SELECT TagName, TagId, CollectorID

FROM Tag CROSS JOIN Collector

WHERE Tag.CollectorName = Collector.CollectorName

ORDER BY 1 DESC

该交叉联接得到的结果集与内联接得到的结果集一样：

SELECT TagName, TagId, CollectorID

FROM Tag INNER JOIN Collector

ON Tag.CollectorName = Collector.CollectorName

ORDER BY 1 DESC

Copyright
2013-2016

---

第7章 SQL语法

#### 7.5.2.4 自联接

在作多表查询时，除了不同表的联接，有时还会遇到同一张表自身作联接，称为自联接。当需要将同一张表中不同行在同一列上的取值相互比较时，就需要用到表的自联接。一般说来，作一张表的自联接查询与其他的联接查询没有本质的差别，只是在FROM子句将同一个表名写上两遍。为了相互区分，在使用自联接时，必须至少为包括在FROM子句中的一个表指定别名。并且在SELECT子句和WHERE子句中使用别名引用需要的表。

下面的示例显示了一个自联接：

 

B 例：

SELECT T1.TagName, T1.TagId, T2.CollectorName

FROM Tag T1 INNER JOIN Tag T2

ON T1.CollectorType = T2.CollectorType

WHERE T1.DataType <> T2.DataType

Copyright
2013-2016

---

第7章 SQL语法

### 7.5.3  使用联接

1.       
在使用联接时，还可以不指定联接类型，而是直接在FROM子句中指定要联接的表，并用逗号分隔。这种联接方式可以看作是交叉联接的等价方式。

 

B 例：

       
SELECT TagName, TagId, CollectorID

FROM Tag, Collector

ORDER BY 1 DESC

2.       
在一个数据库中，往往会有几张表含有相同列名的情况。通常，在单表查询时这不会造成任何混乱，因为在FROM子句中指明表名，这样就可以确定在查询中引用的是哪个表的列名。但当查询中引用多个表时，这就会出现问题，因为系统无法知道你引用的到底是哪个表中的数据。这就是列名引用的二义性。为了清除这种二义性，当联接多表时，任何重复的列名都必须用表名限定：表名.列名。

 

B 例：

       
SELECT T.CollectorName, T.CollectorType,
C.CollectorName,C.CollectorType

FROM Tag T LEFT OUTER
JOIN Collector C

    ON T.CollectorType =
C.CollectorType   

如果某个列名在查询用到的两个或多个表中不重复，则对这一列的引用不必用表名限定。

 

B 例：

       
SELECT TagName, TagId, CollectorID, T.CollectorName,
C.CollectorName

FROM Tag T LEFT OUTER
JOIN Collector C

    ON T.CollectorType = C.CollectorType

   

   

   

       

   

   

   

   

   

3.        
在SELECT语句中，曾经介绍过\*表示一个表中全部的列。在多表查询中，可以在\*前加上表名限制，限定为某个表的全部列。

4.        
 

B 例：

    SELECT T.\*

    FROM Tag AS T LEFT JOIN Collector AS C

    ON T.CollectorName =
C.CollectorName

    在该查询中，T.\*表示表Tag的全部列，与Collector表无关。所以该查询只返回表Tag的所有行和列。

5.        
查询所选的行首先通过
FROM 子句联接条件进行筛选，其次由
WHERE 子句搜索条件筛选，然后由
HAVING 子句搜索条件筛选。

 

B 例：

    SELECT  Count(T.TagName) AS Total

FROM Tag T LEFT OUTER JOIN Collector C

    ON T.CollectorName = C.CollectorName

WHERE CollectorType > 1

GROUP BY DataType, SourceAddress

HAVING SourceAddress = 'Simudevice.i1Val.i1Val0'

对于该查询，首先根据联接条件T.CollectorName=C.CollectorName进行筛选得到联接结果集，然后将不满足WHERE子句搜索条件CollectorType>1的行从结果集中消除，并由HAVING子句条件SourceAddress=’Simudevice.i1Val.i1Val0'对分组进行筛选得到最后的结果集。

6.        
可以在FROM子句中联接三个或更多个表。

 

B 例：

    SELECT R.TagName, T.DataType, C.CollectorName,
C.CollectorType

FROM Tag T INNER JOIN Collector C

     ON T.CollectorName = C.CollectorName JOIN
Realtime R

      ON T.TagName = R.TagName

WHERE T.SourceAddress = 'Simudevice.i1Val.i1Val0'

该查询联接了三个表。

Copyright
2013-2016

---

第7章 SQL语法

### 7.5.4  联接与子查询

到目前为止，我们已经知道的从多张表中查找数据的方法有两种：一种是使用子查询；另一种就是使用表联接。大多数使用联接的查询可以用子查询（嵌套在其它查询中的查询）重写，并且大多数子查询可以重写为联接。

下面是一些联接与子查询可以互相替换的例子。

 

B 例：

       
SELECT TagName

FROM Tag

WHERE 2 IN

         
(SELECT CollectorType

          
FROM Collector

       WHERE Collector.CollectorName =
CollectorName)

    使用联接，同一查询可以用如下方式表示：

       
SELECT DISTINCT TagName

FROM Tag T INNER JOIN
Collector C

ON T.CollectorName = C.CollectorName AND C.CollectorType = 2

    对于上面的例子，使用联接还可以在结果中显示多个表中的列。例如，如果要在结果中包括变量的类型，就可以使用联接来查询。

 

    B 例：

       
SELECT TagName, DataType

FROM Tag T INNER JOIN
Collector C

ON T.CollectorName = C.CollectorName AND C.CollectorType = 2

Copyright
2013-2016

---

第7章 SQL语法

### 7.5.5  UNNION联合

UNION与本章中所介绍的联接不同。它不是在SELECT语句的FROM子句中添加多个表，而是允许在一个查询结果组中将多个查询捆绑在一起。UNION查询结果出现的形式就仿佛是从一个表中选择的，而实际上它们是从多个表中选择的。

使用UNION组合两个查询的结果集的两个基本规则是：

l        
所有查询中的列数和列的顺序必须相同。

l        
数据类型必须相兼容。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.5.5.1 UNION语法格式

query\_expression
UNION [ ALL ] query\_expression

Copyright
2013-2016

---

第7章 SQL语法

#### 7.5.5.2 UNION参数说明

l       
query\_expression

是查询表达式，用以返回与另一个查询表达式所返回的数据组合的数据。作为 UNION 运算一部分的列定义可以不相同，但它们必须通过隐性转换实现兼容。

l       
UNION

指定组合多个结果集并将其作为单个结果集返回，该结果集包含联合查询中的所有查询的全部行。

l       
ALL

在结果中包含所有的行，包括重复行。如果没有指定，则删除重复行。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.5.5.3 用法示例

1.       
使用UNION组合两个查询的结果集

 

B 例：

    SELECT CollectorName, CollectorType

FROM Tag

UNION

SELECT CollectorName, CollectorType

FROM Collector

将表Tag的查询结果集和Collector的查询结果集组合成单个结果集。

但下面的查询却不能完成

    SELECT CollectorName, CollectorType

FROM Tag

UNION

SELECT CollectorName, CollectorType, CollectorID

FROM Collector

    使用 UNION 组合的结果集都必须具有相同的结构。而且它们的列数必须相同，并且相应的结果集列的数据类型必须兼容。在该例中，两个查询的列数不一致。

2.       
默认情况下，UNION 运算符从结果集中删除重复的行。如果使用 ALL 关键字，那么结果中将包含所有行并且将不删除重复的行。

 

B 例：

    SELECT CollectorName, CollectorType

FROM Tag

UNION ALL

SELECT CollectorName, CollectorType

FROM Collector

该例与上例的查询语句基本相同，只是指定了ALL关键字。上例中的查询结果集中不包括重复的行；而本例中，ALL可以让UNION联接返回联合查询结果中的所有行，包括重复行。

3.       
默认情况下，KDB-SQL从左到右对包含 UNION 运算符的语句进行取值。

 

   
B 例：

       
SELECT CollectorName , CollectorType

FROM Collector

WHERE CollectorID > 2

UNION

SELECT CollecotrName, CollectorType

FROM Tag

WHERE DataType> 2

UNION

SELECT CollectorName, CollectorType

FROM Collector

WHERE CollectorStatus = 2

使用圆括号可以指定求值的顺序。下面的两个语句并不等价：

 

B 例：

    (SELECT CollectorName , CollectorType

FROM Collector

WHERE CollectorID > 2

UNION ALL

SELECT CollectorName, CollectorType

FROM Tag

WHERE DataType > 2)

UNION

SELECT CollectorName, CollectorType

FROM Collector

WHERE CollectorStatus = 2

-------------------------------------------------

SELECT CollectorName , CollectorType

FROM Collector

WHERE CollectorID > 2

UNION ALL

(SELECT CollectorName, CollectorType

FROM Tag

WHERE DataType > 2

UNION

SELECT CollectorName, CollectorType

FROM Collector

WHERE CollectorStatus = 2)

在第一个语句中，Collector和Tag之间的联合中包含重复行，但在随后与Collector的联合中将消除重复行。在第二个语句中，将消除Collector和Tag之间的联合中的重复行。而在该集与Collector之间的并集中，不消除重复行。

Copyright
2013-2016

---

第7章 SQL语法

## 7.6     如何处理具体的数据类型

 

 

 

 

 

 

 

 

 

在工业实时数据库中，每个列、局部变量、表达式和参数都有一个相关的数据类型，这是指定对象可持有的数据类型（整型、字符等等）的特性。数据类型决定了该对象可以存储的数据，以及在该对象上可以执行的操作。例如在KDB-SQL中，对于字符串str1和str2，就不能使用加号操作将两个字符串连接起来。这是因为字符串数据不支持加号操作。

Copyright
2013-2016

---

第7章 SQL语法

### 7.6.1  数字数据类型

尽管所有的数字字段都具有相同基本类型的信息——数字，但工业实时数据库还是提供了许多种数字数据类型。工业实时数据库所支持的数值型有：

|  |  |
| --- | --- |
| bigint | 从 -2^63 (-9223372036854775808) 到 2^63-1 (9223372036854775807) 的整型数据（所有数字） |
| int | 从 -2^31 (-2,147,483,648) 到 2^31 - 1 (2,147,483,647) 的整型数据（所有数字） |
| smallint | 从 -2^15 (-32,768) 到 2^15 - 1 (32,767) 的整数数据 |
| tinyint | 从 0 到 255 的整数数据 |
| bit | 1 或 0 的整数数据 |
| float | 从 -1.79E + 308 到 1.79E + 308 的浮点精度数字 |
| real | 从 -3.40E + 38 到 3.40E + 38 的浮点精度数字 |

Copyright
2013-2016

---

第7章 SQL语法

#### 7.6.1.1 空值和数字

空值代表未知的值。正因为如此，它们“破坏”了数学表达式。每当空值出现在数学表达式中时，这个表达式的结果都将是空值自身。有时可能希望在查询执行时对空值代表的值是什么做出假设，以便数学表达式返回真值。在KDB-SQL中，可以使用COALESCE（）函数替换空值。COALESCE()接受两种形式的自变量：包含空值的字段和用来替换空值的值。

1.        
COALESCE

返回其参数中第一个非空表达式。如果所有自变量均为 NULL，则
COALESCE 返回 NULL 值

语法格式：

COALESCE(expression
[, ……n ] )

参数说明：

n        
expression

   
任何类型的表达式。

n        
n

    表示可以指定多个表达式的占位符。所有表达式必须是相同类型，或者可以隐性转换为相同的类型。

用法示例：

为了简单起见，这里只举一个简单的例子：

 

B 例：

SELECT
COALESCE(2+NULL, 545)

此处指定用545来替换2+NULL。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.6.1.2 常用数学函数

1.       
ABS

返回给定数字表达式的绝对值。

l        
语法格式：

ABS(numeric\_expression)

l        
参数说明

n       
numeric\_expression

    精确数字或近似数字数据类型类别的表达式（bit 数据类型除外）。

l        
用法示例

下例显示了 ABS 函数对三个不同数字的效果：

 

B 例：

    SELECT ABS(-1.0), ABS(0.0), ABS(1.0)

ABS 函数可能产生溢出错误，如下例所示：

 

B 例：

    SELECT ABS(convert(int, -2147483648%156))

    由于数据值溢出了目标数据类型的取值范围，数据类型转换失败。

2.       
ACOS

返回以弧度表示的角度值，该角度值的余弦为给定的 float 表达式；本函数亦称反余弦。

l        
语法格式

ACOS(float\_expression)

l        
参数说明

n       
float\_expression

是 float或real 类型的表达式，其取值范围从 -1 到 1。对超过此范围的参数值，函数将返回 NULL。

l        
用法示例

 

B 例：

    SELECT ACOS(0.65)

    返回0.863212。

3.       
ASIN

返回以弧度表示的角度值，该角度值的正弦为给定的 float 表达式；亦称反正弦。

l        
语法格式

ASIN(float\_expression)

l        
参数说明

n       
float\_expression

是 float 类型的表达式，其取值范围从 -1 到 1。对超过此范围的参数值，函数将返回 NULL。

l        
用法示例

 

B 例：

SELECT ASIN(0.65)

返回0.707584。

4.       
ATAN

返回以弧度表示的角度值，该角度值的正切为给定的 float 表达式；亦称反正切。

l        
语法格式

ATAN(float\_expression)

l        
参数说明

n         
float\_expression

    是 float 类型的表达式。

l        
用法示例

 

B 例：

    SELECT ATAN(12.55)

    返回1.491283。

5.       
COS

       返回给定表达式中给定角度（以弧度为单位）的三角余弦值。

l        
语法格式

       COS(float\_expression)

l        
参数说明

n        
float\_expression

    是 float 类型的表达式。

l        
用法示例

 

B 例：

    SELECT COS(12.55)

    返回0.999866。

6.       
COT

返回给定 float 表达式中指定角度（以弧度为单位）的三角余切值。

l       
语法格式

COT(float\_expression)

l       
参数说明

n        
float\_expression

    是 float 类型的表达式。

l       
用法示例

 

B 例：

    SELECT COT(12.55)

    返回-61.079605。

7.       
SIN

以近似数字 (float) 表达式返回给定角度（以弧度为单位）的三角正弦值。

l        
语法格式

SIN(float\_expression)

l        
参数说明

n         
float\_expression

    是 float 类型的表达式。

l        
用法示例

 

B 例：

SELECT SIN(12.55)

返回-0.01637。

8.       
TAN

返回输入表达式的正切值。

l       
语法格式

TAN(float\_expression)

l       
参数说明

n        
float\_expression

    float 或
real 类型的表达式，解释为弧度数。

l       
用法示例

 

B 例：

SELECT TAN(12.55)

返回-0.016372。

9.       
EXP

返回所给的 float 表达式的指数值。

l       
语法格式

EXP(float\_expression)

l       
参数说明

n      
float\_expression

    是 float 类型的表达式。

l       
用法示例

B 例：

SELECT EXP(2)

返回7.389056。

10.    LOG

    LOG 函数返回所给
float 表达式的自然对数。自然对数是使用底数为 2 的体系计算的。

l        
语法格式

LOG(float\_expression)

l        
参数说明

n         
float\_expression

    是 float 类型的大于0的表达式。

l        
用法示例

 

B 例：

SELECT LOG(2)

返回0.693147。

11.    LOG10

返回给定 float 表达式的以
10 为底的对数。

l       
语法格式

LOG10(float\_expression)

l       
参数说明

n       
float\_expression

    是 float 类型的大于0的表达式。

l       
用法示例

 

B 例：

SELECT LOG10(12)

返回1.079181。

12.    RAND

返回 0 到1 之间的随机float 值。在单个查询中反复调用 RAND() 将产生相同的值。

l       
语法格式

RAND( [ seed ] )

l       
参数说明

n       
seed

    是给出种子值或起始值的整型表达式（tinyint、smallint 或 int）。

l       
用法示例

RAND函数是非确定性函数，仅当指定种子参数时，RAND 才具有确定性。

 

B 例：

SELECT RAND(2)

13.    SIGN

返回给定表达式的正 (+1)、零 (0) 或负 (-1) 号。

l        
语法格式

SIGN(numeric\_expression)

l        
参数说明

n        
numeric\_expression

    精确数字或近似数字数据类型类别的表达式。

l        
用法示例

 

B 例：

SELECT SIGN(-2), SIGN(0), SIGN(2)

       返回结果为-1、0、1。

14.    SQRT

返回给定表达式的平方根。

l        
语法格式

SQRT(float\_expression)

l        
参数说明

n         
float\_expression

    是 float 类型的不小于0的表达式。

l        
用法示例

 

B 例：

SELECT SQRT(16)

       返回结果为4。

15.    SQUARE

返回给定表达式的平方。

l        
语法格式

SQUARE(float\_expression)

l        
参数说明

n       
float\_expression

    是 float 类型的表达式。

l        
用法示例

 

B 例：

SELECT SQUARE(4)

返回结果为16。

16.    FLOOR

返回小于或等于所给数字表达式的最大整数。

l        
语法格式

FLOOR(numeric\_expression)

l        
参数说明

n         
numeric\_expression

    精确数字或近似数字数据类型类别的表达式。

l        
用法示例

 

B 例：

SELECT FLOOR(4.25)

返回结果为4。

17.    CEILING

返回大于或等于所给数字表达式的最小整数。

l       
语法格式

CEILING(numeric\_expression)

l       
参数说明

n       
numeric\_expression

    是精确数字或近似数字数据类型类别的表达式。

l       
用法示例

 

B 例：

SELECT CEILING(4.25)

返回结果为5。

18.    POWER

返回给定表达式乘指定次方的值。

l       
语法格式

POWER(numeric\_expression, y)

l       
参数说明

n        
numeric\_expression

    是精确数字或近似数字数据类型类别的表达式。

n        
y

    numeric\_expression 的次方。y 可以是精确数字或近似数字数据类型类别的表达式。

l       
用法示例

 

B 例：

SELECT POWER(4, 2)

返回结果为16。

Copyright
2013-2016

---

第7章 SQL语法

### 7.6.2  字符串数据类型

在KDB-SQL中有两种主要的字符串数据：VARCHAR和CHAR。

l        
VARCHAR[ (n) ]

    长度为 *n* 个字节的可变长度且非 Unicode 的字符数据。存储大小为输入数据的字节的实际长度，而不是
n 个字节。所输入的数据字符长度可以为零。在KDB-SQL中，VARCHAR的同义词为CHAR VARYING或CHARACTER VARYING。

l        
CHAR[ (n) ]

    长度为 *n* 个字节的固定长度且非 Unicode 的字符数据。存储大小为 n 个字节。在KDB-SQL中，CHAR的同义词为CHARACTER。

在KDB-SQL中还有专门的数据类型可以包含极大的字符串，例如CLOB和TEXT。

针对UNICODE字符集，KDB-SQL还支持UNICODE字符数据类型：

l        
NVARCHAR [ (n) ]

    包含 n 个字符的可变长度 Unicode 字符数据。n的值必须介于 1 与
4,000 之间。字节的存储大小是所输入字符个数的两倍。所输入的数据字符长度可以为零。NVARCHAR的同义词为NATIONAL CHAR VARYING或NATIONAL CHARACTER VARYING。

l        
NCHAR [ (n) ]

    包含 n 个字符的固定长度 Unicode 字符数据。n的值必须介于 1 与
4,000 之间。存储大小为 n 字节的两倍。NCHAR的同义词为NATIONAL CHAR或NATIONAL CHARACTER。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.6.2.1 并置字符串

所有的数据库都提供字符串并置运算符，它用来把多个字符串当作单独一个字符串处理。在T-SQL（SQL
Server使用）中使用+来并置字符串。而在KDB-SQL中则使用||作为字符串并置运算符。两种运算符的工作方式相同。

下面的示例显示了使用并置运算符来创建作者的姓名：

 

B 例：

        SELECT  au\_lname || ' ' || au\_fname AS name

FROM authors

在该查询中，au\_lname和au\_fname都与中间包含空格的字符串并置在一起。

下面的示例显示了使用并置的字符串来创建查询的WHERE子句中的比较：

 

B 例：

    SELECT au\_id, age, salary

    FROM authors

    WHERE
au\_lname || ' ' || au\_fname = 'White Johnson'

Copyright
2013-2016

---

第7章 SQL语法

#### 7.6.2.2 字符串函数

1.       
UPPER

返回将小写字符数据转换为大写的字符表达式。

l       
语法格式

UPPER(str\_expression)

l       
参数说明

n       
str\_expression

字符串表达式。必须为可隐性转换为
varchar 的数据类型。

l       
用法示例

 

B 例：

SELECT UPPER('white')

返回WHITE。

下面的示例显示了将列TagName转换为大写形式：

 

   B 例：

            
SELECT UPPER(TagName)

            
FROM Realtime

2.       
LOWER

将大写字符数据转换为小写字符数据后返回字符表达式。

l       
语法格式

LOWER(str\_expression)

l       
参数说明

n      
str\_expression

字符串表达式。必须为可隐性转换为
varchar 的数据类型。

l       
用法示例

 

B 例：

SELECT LOWER('WHITE')

    返回white。

下面的示例显示了用 LOWER 函数、UPPER 函数并把 UPPER 函数嵌套在 LOWER 函数内

 

B 例：

    SELECT LOWER(UPPER(TagName))

    FROM Realtime

3.       
TRIM

删除两边的空格后返回字符串。

l        
语法格式

TRIM(str\_expression)

l        
参数说明

n         
str\_expression

字符串表达式。必须为可隐性转换为
varchar 的数据类型。

l        
用法示例

 

B 例：

SELECT TRIM(' 
WHITE       ')

返回WHITE，而非 
WHITE       ，TRIM将WHITE两边的空格删除。

4.       
LTRIM

删除起始空格后返回字符表达式。

l        
语法格式

LTRIM(str\_expression)

l        
参数说明

n       
str\_expression

字符串表达式。必须为可隐性转换为
varchar 的数据类型。

l        
用法示例

 

B 例：

    SELECT LTRIM(' 
WHITE       ')

    与上例不同，返回WHITE    ，而非  WHITE       ，LTRIM将WHITE左边的空格删除。

5.       
RTRIM

截断所有尾随空格后返回一个字符串。

l        
语法格式

RTRIM(str\_expression)

l        
参数说明

n        
str\_expression

字符串表达式。必须为可隐性转换为
varchar 的数据类型。

l        
用法示例

 

B 例：

    SELECT RTRIM(' 
WHITE       ')

    返回   WHITE，而不是  WHITE       ，RTRIM将WHITE右边的空格删除。

6.       
CHAR\_LENGTH

返回字符串的长度。如果字符串包含空格，则将空格个数记入在内。与之相同的还有CHARACTER\_LENGTH函数。

l        
语法格式

CHAR\_LENGTH(str\_expression)

l        
参数说明

n        
str\_expression

字符串表达式。必须为可隐性转换为
varchar 的数据类型。

l        
用法示例

 

B 例：

SELECT CHAR\_LENGTH('  WHITE  ')

    返回字符串长度为9。包括空格个数。

7.       
REPLACE

用第三个表达式替换第一个字符串表达式中出现的所有第二个给定字符串表达式。

l        
语法格式

REPLACE(str\_expression1,
str\_expression2, str\_expression3)

l        
参数说明

n        
str\_expression1

   
待搜索的字符串表达式。必须为可隐性转换为 varchar 的数据类型。

n        
str\_expression2

   
待查找的字符串表达式。必须为可隐性转换为 varchar 的数据类型。

n        
str\_expression3

   
替换用的字符串表达式。必须为可隐性转换为 varchar 的数据类型。

l        
用法示例

 

B 例：

    SELECT REPLACE('white',
'wh', 'xx')

    返回替换后的字符串xxite。

8.       
REVERSE

返回字符表达式的反转。

l        
语法格式

REVERSE(str\_expression)

l        
参数说明

n        
str\_expression

   
字符串表达式。必须为可隐性转换为 varchar
的数据类型。

l        
用法示例

 

B 例：

    SELECT REVERSE('white')

    返回反转后的字符串etihw。

9.       
SUBSTRING

返回字符串、二进制字符串或文本串的一部分。

l        
语法格式

SUBSTRING(expression
FROM start\_position [ FOR string\_length ] )

l        
参数说明

n        
expression

    是字符串、二进制字符串、列或包含列的表达式。不要使用包含聚合函数的表达式。

n        
start\_position

   
是一个整数，指定子串的开始位置。

n        
FOR string\_length

    是一个整数，指定子串的长度（要返回的字符数或字节数）。如果不指定string\_length，则认为是从开始位置起，直到expression末尾的长度。

l        
用法示例

 

B 例：

    SELECT SUBSTRING('white'
FROM 0 FOR 3)

    返回字符串子串whi。

下面的示例显示了省略掉FOR关键字：

 

B 例：

    SELECT SUBSTRING('white'
FROM 0)

    返回字符串子串white。

Copyright
2013-2016

---

第7章 SQL语法

### 7.6.3  日期数据类型

KDB-SQL支持timestamp类型的日期数据。使用timestamp数据类型可以存储从1970 年 1
月 1 日至 2106 年 2 月 7 日的日期。KDB-SQL目前只支持一种日期格式：年-月-日 时:分:秒:毫秒。比如2006年8月22日9时1分15秒，可以写成2006-8-22
9:1:15。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.6.3.1 日期函数

1.        
YEAR

返回表示指定日期中的年份的整数。

l        
语法格式

YEAR(date\_expression)

l        
参数说明

n        
date\_expression

date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT YEAR('2005-1-14
20:08:14')

    返回日期的年份2005。

2.        
MONTH

返回表示指定日期月份的整数。

l        
语法格式

MONTH(date\_expression)

l        
参数说明

n        
date\_expression

date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT MONTH('2005-1-14
20:08:14')

    返回日期的月份1。

3.        
DAY

返回表示指定日期的天的日期部分的整数。

l        
语法格式

DAY(date\_expression)

l        
参数说明

n        
date\_expression

date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT DAY('2005-1-14
20:08:14')

    返回日期的天数14。

4.        
HOUR

返回表示指定日期的小时部分的整数。

l        
语法格式

HOUR(date\_expression)

l        
参数说明

n        
date\_expression

date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT HOUR('2005-1-14
20:08:14')

    返回日期的小时20。

5.        
MINUTE

返回表示指定日期的分部分的整数。

l        
语法格式

MINUTE(date\_expression)

l        
参数说明

n        
date\_expression

date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT MINUTE('2005-1-14
20:08:14')

    返回日期的分08。

6.        
SECOND

返回表示指定日期的秒部分的整数。

l        
语法格式

SECOND(date\_expression)

l        
参数说明

n        
date\_expression

date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT SECOND('2005-1-14
20:08:14')

    返回日期的秒14。

7.        
MILLISECOND

返回表示指定日期的毫秒部分的整数。

l        
语法格式

MILLSECOND(date\_expression)

l        
参数说明

n        
date\_expression

date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT
MILLISECOND('2005-1-14 20:08:14')

    返回日期的毫秒，此时返回0。

8.        
EXTRACT

从指定日期中返回指定部分的整数。

l        
语法格式

EXTRACT(datepart
from date\_expression)

l        
参数说明

n        
datepart

指定要提取的日期部分。可以指定为：YEAR、MONTH、DAY、HOUR、MINUTE、SECOND、MILLISECOND。

n        
from

指定要从已知日期中提取指定部分。

n        
date\_expression

   
date或timestamp类型的表达式。

l        
用法示例

 

B 例：

        SELECT
EXTRACT(MILLISECOND FROM '2005-1-14 20:08:14')

    从已知日期中提取毫秒部分，此处为0。

9.        
DATEADD

在向指定日期加上一段时间的基础上，返回新的date值。

l        
语法格式

DATEADD(datepart,
number, date\_expression)

l        
参数说明

n        
datepart

    是规定应向日期的哪一部分返回新值的参数。可以指定为：YEAR、MONTH、

DAY、HOUR、MINUTE、SECOND、MILLISECOND。

n        
number

    是用来增加 datepart 的值。如果指定一个不是整数的值，则将废弃此值的小数部分。可以是负值。

n        
date\_expression

   
date或timestamp类型的表达式。

l        
用法示例

下面的示例显示了在已知日期上加上21天：

 

B 例：

SELECT DATEADD(day, 21, '2005-1-14
20:08:14')

由于加上21天后，超过了本月的天数，所以返回结果为2005-2-4
20:08:14。

       
下例显示了一个指定number值为小数的例子：

 

        
B 例：

            
SELECT DATEADD(day, 2.1, '2005-1-14 20:08:14')

            
此时将废气值的小数部分，返回的结果是2005-1-16
20:08:14。

10.    
CURRENT\_DATE

返回当前日期值。

l        
用法示例

 

B 例：

SELECT CURRENT\_DATE

    返回系统的当前日期。

11.    
CURRENT\_TIME

返回当前时间。

l        
用法示例

 

B 例：

SELECT CURRENT\_TIME

    返回系统的当前时间。

12.    
CURRENT\_TIMESTAMP

返回当前时间。

l        
用法示例

 

B 例：

SELECT CURRENT\_TIMESTAMP

    返回系统的当前时间。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.6.3.2 日期算法

KDB-SQL允许用简单的算术运算将日子加到日期中。与函数DATEADD作用相同。

l        
语法格式

date\_expression
[ + |  INTERVAL number datepart

l        
参数说明

n        
date\_expression

   
date或timestamp类型的表达式。

n        
INTERVAL

允许用简单的算术运算对日期作加减法。

n        
number

    是用来增加 datepart 的值。如果指定一个不是整数的值，则将废弃此值的小数部分。可以是负值。

n        
datepart

    是规定应向日期的哪一部分返回新值的参数。可以指定为：YEAR、MONTH、

DAY、HOUR、MINUTE、SECOND、MILLISECOND。

l        
用法示例

下面的示例显示了在已知日期上加上21天：

 

B 例：

SELECT '2005-1-14 20:08:14' + INTERVAL 21 DAY

返回的结果为2005-2-4 20:08:14。与例40的结果相同。

        
还可以在已知日期上减去某一数值

 

        
B 例：

 SELECT '2005-1-14 20:08:14' – INTERVAL 10 SECOND

            
返回的结果为2005-1-14
20:08:04。

Copyright
2013-2016

---

第7章 SQL语法

### 7.6.4  二进制数据类型

KDB-SQL中支持如下几种二进制数据类型：

l        
BINARY [ (n) ]

    固定长度的 n 个字节二进制数据。n必须从 1 到
8,000。存储空间大小为 n+4 字节。

l        
VARBINARY

    n 个字节变长二进制数据。n必须从 1 到
8,000。存储空间大小为实际输入数据长度+4个字节，而不是 n 个字节。输入的数据长度可能为 0 字节。

l        
IMAGE

   
可变长度二进制数据介于 0 与 231-1
(2,147,483,647) 字节之间。

Copyright
2013-2016

---

第7章 SQL语法

### 7.6.5  转换数据类型

在工业实时数据库中，数据类型转换有两种：

l        
隐式转换

    隐性转换对于用户是不可见的，系统自动将数据从一种数据类型转换成另一种数据类型。例如，如果一个
smallint 变量和一个 int 变量相比较，这个 smallint 变量在比较前即被隐性转换成 int 变量

l        
显示转换

   
显式转换使用 CAST 或 CONVERT 函数。

1.        
CAST

将某种数据类型的表达式显式转换为另一种数据类型。

l        
语法格式

CAST(expression
AS data\_type)

l        
参数说明

n        
expression

是任何有效的表达式。

n        
date\_type

   
系统所提供的数据类型。

l        
用法示例

下面的示例显示了将INT型的数据显示转换为VARCHAR类型：

B 例：

SELECT CAST(CollectorType AS varchar(10))

FROM Collector

       
但如果要进行不可能的转换，则会导致转换失败：

 

       
B 例：

           
SELECT CAST(SourceAddress AS int)

           
FROM Tag

            在该转换中，试图将VARCHAR类型的数据转换为INT类型的数据，导致转换失败。

2.        
CONVERT

与CAST作用相同，CONVERT函数也是将某种数据类型的表达式显式转换为另一种数据类型。

l        
语法格式

CONVERT(data\_type,
expression)

l        
参数说明

n        
data\_type

   
系统所提供的数据类型。

n        
expression

是任何有效的表达式。

l        
用法示例

下面的示例显示了将INT型的数据显示转换为VARCHAR类型：

 

B 例：

SELECT CONVERT(varchar(10), CollectorType)

FROM Collector

该例与上述例子的结果集相同。

        
同样，如果要进行不可能的转换，也会导致CONVERT转换失败：

 

        
B 例：

            
SELECT CONVERT(int, SourceAddress)

            
FROM Type

    
在该转换中，试图将VARCHAR类型的数据转换为INT类型的数据导致转换失败。

Copyright
2013-2016

---

第7章 SQL语法

### 7.6.6  聚合函数

聚合函数对一组值执行计算并返回单一的值，通常与 SELECT 语句的 GROUP BY 子句一同使用。可选关键字 DISTINCT 可以与 SUM、AVG 和 COUNT 一同使用，以便在应用聚合函数之前消除重复值（默认为 ALL）。

仅在下列项中聚合函数允许作为表达式使用：

l        
SELECT 语句的选择列表（子查询或外部查询）。

如果某个选择列表中使用了聚合函数，则该选择列表只能包含聚合函数、通过GROUP BY子句分组的列以及为结果集中每一行返回同一值（例如一个常量）的表达式。可以在同一个选择列表中使用多个聚合函数。

l        
HAVING 子句。

WHERE 子句中不能使用聚合函数。但是，在其选择列表中包含聚合函数的
SELECT 语句经常包括 WHERE
子句，该子句限制要应用聚合函数的行。如果 SELECT 语句中包含 WHERE 子句（但不包含 GROUP BY 子句），那么聚合函数将为 WHERE 子句指定的行的子集产生一个值。不论聚合函数是应用于表中的所有行还是 WHERE 子句定义的行的子集，都会产生该值。

1.        
COUNT

返回组中项目的数量。

l        
语法格式

COUNT ( {[ ALL |
DISTINCT ] expression } | \* )

l        
参数说明

n        
ALL

   
对所有的非空值进行聚合函数运算。ALL
是默认设置。

n        
DISTINCT

   
指定 COUNT 返回唯一非空值的数量。用于消除重复值。

n        
expression

是任何有效的表达式，不允许使用聚合函数和子查询。

n        
\*

    指定应该计算所有行以返回表中行的总数。COUNT(**\***) 不需要任何参数，而且不能与 DISTINCT 一起使用。COUNT(\*) 不需要 expression 参数，因为根据定义，该函数不使用有关任何特定列的信息。COUNT(**\***) 返回指定表中行的数量而不消除副本。它对每行分别进行计数，包括含有空值的行

l        
用法示例

下面给出了一个使用COUNT和DISTINCT的示例：

 

B 例：

           
SELECT COUNT(DISTINCT CollectorType)

FROM Tag

该例查找来自于不同类型采集器的变量的数量。

       
下例显示指定了ALL关键字：

 

        
B 例：

            
SELECT COUNT(ALL CollectorType)

FROM Tag

与上例不同，该例包括来自所有采集器类型的数量，不管是否来自同一采集器。但不包括CollectorType为空值的行。

       
下面是一个使用COUNT（\*）的示例：

 

        
B 例：

     SELECT COUNT(\*)

     FROM Tag

            
返回Tag表的所有行数，包括含有空值的行。

         当与 GROUP BY 子句一起使用时，COUNT函数都为每一组生成一个值，而不是对整个表生成一个值。

 

        
B 例：

       
     SELECT COUNT(TagName)

            
FROM Tag

            
GROUP BY CollectorType

            
该查询返回来自于不同采集器的变量的数量。

2.        
AVG

返回组中值的平均值。AVG只能用于数字列。空值将被忽略。

l        
语法格式

AVG( [ ALL |
DISTINCT ] expression )

l        
参数说明

n        
ALL

   
对所有的值进行聚合函数运算。ALL 是默认设置。

n        
DISTINCT

   
指定 AVG 操作只使用每个值的唯一实例，而不管该值出现了多少次。

n        
expression

   
精确数字或近似数字数据类型类别的表达式。不允许使用聚合函数和子查询。

l        
用法示例

下面给出了一个使用AVG和DISTINCT的示例：

 

B 例：

SELECT AVG(DISTINCT Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

返回History表中变量名为OPC\_Simudevice.i1Val.i1Val0的平均值，不包括重复的值。

        
下例显示指定了ALL关键字，以在计算时包括重复值：

 

        
B 例：

            
SELECT AVG(ALL Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

          当与 GROUP BY 子句一起使用时，AVG函数都为每一组生成一个值，而不是对整个表生成一个值。

 

B 例：

    SELECT AVG(Cast(DataValue As int))

    FROM History

    GROUP BY TagName

    该例返回History表中不同变量名的平均值。

3.        
SUM

    返回表达式中所有值的和，或只返回 DISTINCT 值。SUM 只能用于数字列。空值将被忽略。

l        
语法格式

SUM( [ ALL |
DISTINCT ] expression )

l        
参数说明

n        
ALL

   
对所有的值进行聚合函数运算。ALL 是默认设置。

n        
DISTINCT

   
指定 SUM 返回唯一值的和。

n        
expression

是精确数字或近似数字数据类型的表达式。不允许使用聚合函数和子查询

l        
应用示例

下面给出了一个使用SUM和DISTINCT的示例：

 

B 例：

SELECT SUM(DISTINCT Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

返回History表中变量名为OPC\_Simudevice.i1Val.i1Val0的值的总和，不包括重复的值。

下例显示指定了ALL关键字，以在计算时包括重复值：

 

        
B 例：

            
SELECT SUM(ALL Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

当与 GROUP BY 子句一起使用时，SUM函数都为每一组生成一个值，而不是对整个表生成一个值。

 

B 例：

    SELECT SUM(Cast(DataValue As int))

    FROM History

    GROUP BY TagName

该例返回History表中按不同变量名分组的每组值的总和。

4.        
MAX

返回表达式的最大值。MAX 忽略任何空值。

l        
语法格式

MAX( [ ALL |
DISTINCT ] expression )

l        
参数说明

n        
ALL

   
对所有的值进行聚合函数运算。ALL 是默认设置。

n        
DISTINCT

   
指定每个唯一值都被考虑。

n        
expression

    常量、列名、函数以及算术运算符、按位运算符和字符串运算符的任意组合。不允许使用聚合函数和子查询

l        
应用示例

下面给出了一个使用MAX和DISTINCT的示例：

 

B 例：

SELECT MAX(DISTINCT Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

返回History表中变量名为OPC\_Simudevice.i1Val.i1Val0的最大值，不包括重复的值。

        
下例显示指定了ALL关键字，以在计算时包括重复值：

 

        
B 例：

            
SELECT MAX(ALL Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

当与 GROUP BY 子句一起使用时，MAX函数都为每一组生成一个值，而不是对整个表生成一个值。

 

B 例：

    SELECT MAX(Cast(DataValue As int))

    FROM History

    GROUP BY TagName

该例返回按不同变量分组的变量的最大值。

5.        
MIN

返回表达式的最小值。MIN 忽略任何空值。

l        
语法格式

MIN( [ ALL |
DISTINCT ] expression )

l        
参数说明

n        
ALL

   
对所有的值进行聚合函数运算。ALL 是默认设置。

n        
DISTINCT

   
指定每个唯一值都被考虑。

n        
expression

    常量、列名、函数以及算术运算符、按位运算符和字符串运算符的任意组合。不允许使用聚合函数和子查询

l        
应用示例

下面给出了一个使用MIN和DISTINCT的示例：

 

B 例：

SELECT MIN(Distinct Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_ Simudevice.i1Val.i1Val0'

返回History表中变量名为OPC\_ Simudevice.i1Val.i1Val0的最小值，不包括重复的值。

下例显示指定了ALL关键字，以在计算时包括重复值：

 

        
B 例：

            
SELECT MIN(ALL Cast(DataValue As int))

FROM History

WHERE TagName = 'OPC\_Simudevice.i1Val.i1Val0'

该例返回History表中变量名为OPC\_Simudevice.i1Val.i1Val0的最小值。

当与 GROUP BY 子句一起使用时，MIN函数都为每一组生成一个值，而不是对整个表生成一个值。

 

B 例：

    SELECT MIN(Cast(DataValue As int))

    FROM History

    GROUP BY TagName

该例返回不同变量名的最小值。

6.        
STDEV

返回给定表达式中所有值的统计标准偏差。STDEV 只能用于数字列。空值将被忽略。

l        
语法格式

STDEV(expression)

l        
参数说明

n        
expression

是精确数字或近似数字数据类型的表达式。不允许进行聚合函数和子查询。

l        
应用示例

下例返回History表中变量名为OPC\_
Simudevice.i1Val.i1Val0的标准偏差：

 

B 例：

SELECT STDEV(Cast(DataValue As int))

FROM History

Where TagName = ‘OPC\_ Simudevice.i1Val.i1Val0’

当与 GROUP BY 子句一起使用时，STDEV函数都为每一组生成一个值，而不是对整个表生成一个值。

 

B 例：

  SELECT STDEV(Cast(DataValue As int))

  FROM History

  GROUP BY TagName

该例返回不同变量名的标准偏差。

7.        
VAR

返回给定表达式中所有值的统计方差。VAR 可只用于数字列。空值将被忽略。

l        
语法格式

VAR(expression)

l        
参数说明

n        
expression

是精确数字或近似数字数据类型的表达式。不允许进行聚合函数和子查询。

l        
应用示例

下例返回History表中变量名为OPC\_
Simudevice.i1Val.i1Val0的方差：

 

B 例：

SELECT VAR(Cast(DataValue As int))

FROM History

Where TagName = ‘OPC\_ Simudevice.i1Val.i1Val0’

当与 GROUP BY 子句一起使用时，VAR函数都为每一组生成一个值，而不是对整个表生成一个值。

 

B 例：

  SELECT VAR(Cast(DataValue As int))

  FROM History

  GROUP BY TagName

  该例返回不同变量名的方差。

8.        
STDEVP

返回给定表达式中所有值的填充统计标准偏差。STDEVP 只能用于数字列。空值将被忽略。

l        
语法格式

STDEVP(expression)

l        
参数说明

n        
expression

    是精确数字或近似数字数据类型的表达式。不允许进行聚合函数和子查询。

l        
应用示例

下例返回History表中变量名为OPC\_
Simudevice.i1Val.i1Val0的填充标准偏差：

 

B 例：

SELECT STDEVP(Cast(DataValue As int))

FROM History

Where TagName = ‘OPC\_ Simudevice.i1Val.i1Val0’

当与 GROUP BY 子句一起使用时，STDEVP函数都为每一组生成一个值，而不是对整个表生成一个值。

 

B 例：

  SELECT STDEVP(Cast(DataValue As int))

  FROM History

  GROUP BY TagName

  该例返回不同变量名的变量的填充标准偏差。

9.        
VARP

返回给定表达式中所有值的填充的统计方差。VARP 可只用于数字列。空值将被忽略。

l        
语法格式

VARP(expression)

l        
参数说明

n        
expression

    是精确数字或近似数字数据类型的表达式。不允许进行聚合函数和子查询。

l        
应用示例

下例返回History表中变量名为OPC\_ Simudevice.i1Val.i1Val0的填充方差：

B 例：

SELECT VARP(Cast(DataValue As int))

FROM History

Where TagName = ‘OPC\_ Simudevice.i1Val.i1Val0’

当与 GROUP BY 子句一起使用时，VARP函数都为每一组生成一个值，而不是对整个表生成一个值

B 例：

  SELECT VARP(Cast(DataValue As int))

  FROM History

  GROUP BY TagName

    该例返回不同变量名的变量的填充方差。

Copyright
2013-2016

---

第7章 SQL语法

### 7.6.7  系统函数

1.        
CURRENT\_USER

返回当前用户。

l        
用法示例

 

B 例：

    SELECT CURRENT\_USER

    返回当前的用户。

Copyright
2013-2016

---

第7章 SQL语法

## 7.7     事务

 

 

 

 

 

 

从根本上讲，事务是SQL语句组，出于处理的目的，将其看作为单一的工作单元。如果某一事务成功，则在该事务中进行的所有数据更改均会提交，成为数据库中的永久组成部分。如果事务遇到错误且必须取消或回滚，则所有数据更改均被清除。

Copyright
2013-2016

---

第7章 SQL语法

### 7.7.1  事务概述

事务是作为单个逻辑工作单元执行的一系列操作。一个逻辑工作单元必须有四个属性，称为 ACID（原子性、一致性、隔离性和持久性）属性，只有这样才能成为一个事务：

l        
原子性

   
事务必须是原子工作单元；对于其数据修改，要么全都执行，要么全都不执行。

l        
一致性

    事务在完成时，必须使所有的数据都保持一致状态。在关系数据库中，所有规则都必须应用于事务的修改，以保持所有数据的完整性

l        
隔离性

    事务查看数据时数据所处的状态，要么是另一并发事务修改它之前的状态，要么是另一事务修改它之后的状态，事务不会查看中间状态的数据。

l        
持久性

事务完成之后，它对于系统的影响是永久性的。

事务的重要特征就是：除非执行事务内的所有语句，否则，对应用到数据库的事务不作任何更改。

Copyright
2013-2016

---

第7章 SQL语法

### 7.7.2  事务隔离级别

事务准备接受不一致数据的级别称为隔离级别。隔离级别是一个事务必须与其它事务进行隔离的程度。较低的隔离级别可以增加并发，但代价是降低数据的正确性。相反，较高的隔离级别可以确保数据的正确性，但可能对并发产生负面影响。

并发调度中存在的不一致现象有：

l        
丢失修改

    两个事务T1和T2读入同一数据并修改，T2首先提交，然后T1再提交，T1提交的结果破坏了T2提交的结果，从而导致T2的修改丢失，这称作丢失修改。又称作写-写错误。

l        
读脏数据

    事务T1修改某一数据，并将其写回磁盘，事务T2读取同一数据后，T1由于某种原因被撤销，这时T1已修改过的数据恢复原值，T2读到的数据与数据库中的数据不一致，则T2读到的数据就是脏数据，又称作写-读错误。

l        
不能重复读

    事务T2读取某一数据后，事务T1对其作了修改，当T2再次读取该数据时，得到与前次不同的值，又称作读-写错误。

l        
幻象

    事务T2按一定条件读取了某些数据后，事务T1插入（删除）了一些满足这些条件的数据，当T2再次按相同条件读取数据时，发现多（少）了一些记录。

工业实时数据库支持的隔离级别有：

l        
未提交读（事务隔离的最低级别，仅可保证不读取物理损坏的数据）。

l        
提交读。

l        
可重复读。

l        
可串行读（事务隔离的最高级别，事务之间完全隔离）。

Copyright
2013-2016

---

第7章 SQL语法

### 7.7.3  使用事务

基本上有三个动作与事务相关。首先是启动事务，启动事务后，把所有后继KDB-SQL语句看作事务的组成部分，直到终止事务为止。其次与事务相关的动作是提交动作。提交事务后，事务结束，事务期间对数据库所做的全部更改应用到数据库。如果事务遇到错误，则必须取消或回滚，放弃到这时所作的任何更改。

一般来说，在提交事务之前，使用数据库的其他人看不到事务所作的更改。只有提交那些更改后，其他用户才能看到更改。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.7.3.1 设置事务模式

设置下一条事务的属性。在同一个SET TRANSACTION语句中多余的和自相矛盾的设置是不合法的。因为SET TRANSACTION 只对下一步事务有影响，所以应用程序必须保证在每个事务开始之前执行SET
TRANSACTION语句。

1.        
语法格式

set\_transaction\_statement

   ::= SET TRANSACTION < transaction\_mode >

< transaction\_mode
> ::= < isolation\_level > | < transaction\_access\_mode >

<
isolation\_level > ::=

ISOLATION LEVEL [ READ COMMITTED | READ UNCOMMITTED

| REPEATABLE READ | SERIALIZABLE ]

   
< transaction\_access\_mode > ::=  READ ONLY | READ WRITE

2.        
参数说明

l        
transaction\_mode

指定事务模式。

l        
isolation\_level

指定隔离级别。如果<isolation\_level>没有被指定，那么<isolation\_level> 的缺省值是ISOLATION LEVEL
SERIALIZABLE。

l        
transaction\_access\_mode

指定访问模式。如果<transaction\_access\_mode>没有被指定并且<isolation\_level> 被指定为ISOLATION LEVEL READ
UNCOMMITTED,那么<transaction
access mode>的值缺省是READ ONLY，否则缺省是READ WRITE。

l        
ISOLATION LEVEL

指定设置隔离级别事务模式

l        
READ COMMITTED

指定在不更改或不更新数据的操作（只读操作）中，如执行SELECT语句时，任何其他并发事务都不能修改数据。但数据可在事务结束前更改，从而产生不可重复读或幻象数据

指定该模式可以避免脏读。脏读是指包含未提交数据的读。例如，事务1更改了某行。事务2在事务1提交更改之前读取已更改的行。如果事务1回滚更改，则事务2便读取了逻辑上从未存在过的行。

l        
READ UNCOMMITTED

当设置该选项时，可以对数据执行脏读。

l        
REPEATABLE READ

    锁定查询中使用的所有数据以防止其他用户更新数据。可重复读保证一个事务如果再次访问同一数据，与此前访问相比，数据不会发生改变。换句话说，在事务两次访问同一数据之间，其他事务不能修改该数据。但可重复读允许发生幻象。

l        
SERIALIZABLE

    防止其他用户在事务完成之前更新数据集或将行插入数据集内。可串行化隔离性级别在可重复读的基础上，进一步要求在一个事务执行两次查询之间，其他事务不能插入满足该查询条件的元组，也即不能发生幻象。

l        
READ ONLY

指定只读访问模式。

l        
READ WRITE

指定读写访问模式。如果READ WRITE被指定，那么<isolation\_level>不能是ISOLATION LEVEL READ
UNCOMMITTED。

3.        
用法示例

使用SET TRANSACTION设置的选项将一直保持有效，直到显式更改该选项为止。

 

B 例：

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ

BEGIN TRANSACTION

SELECT \* FROM Tag

SELECT \* FROM Realtime

COMMIT TRANSACTION

Copyright
2013-2016

---

第7章 SQL语法

#### 7.7.3.2 启动事务

可通过START TRANSACTION或BEGIN TRANSACTION语句显式启动事务。事务启动后必须显式地完成每一个事务，或者使用COMMIT或者使用ROLLBACK语句。没有终止事务就结束执行过程将导致COMMIT还是ROLLBACK由实现来决定。

1.        
语法格式

start\_transaction\_statement

    
::= { START | BEGIN } { TRANSACTION | WORK } [ <
transaction\_mode > ]

2.        
参数说明

l        
{ START | BEGIN }

指定启动一个新事务。目前工业实时数据库不支持嵌套事务。

l        
< transaction\_mode >

指定事务模式。参见设置事务模式。

3.        
用法示例

下面显示了一个启动事务的示例：

 

B 例：

BEGIN TRANSACTION ISOLATION LEVEL READ COMMITTED

SELECT \* FROM Realtime

COMMIT

启动了一个新事务，并设置事务模式为提交读，则在从表authors中读取数据时，不允许其他事务修改数据。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.7.3.3 提交事务

结束事务，把事务期间所做的更改应用到数据库中。

1.        
语法格式

commit\_statement

  ::= COMMIT [ TRANSACTION | WORK ]

2.        
参数说明

l        
COMMIT

标志一个事务的结束。

3.        
用法示例

 

B 例：

BEGIN TRANSACTION READ ONLY

SELECT \* FROM Realtime

COMMIT

    设置事务访问模式为只读，则在事务期间，不能执行插入、更新或删除操作。

Copyright
2013-2016

---

第7章 SQL语法

## 7.8     其他

 

 

 

 

 

 

 

本章主要讨论在实际使用中会用到的一些技术，包括声明自定义变量，显示KDB-SQL语句的执行计划，以及分支语句的应用。

Copyright
2013-2016

---

第7章 SQL语法

### 7.8.1  自定义变量

在使用工业实时数据库中，用户可以自己定义变量。在KDB-SQL中，可以使用DECLARE语句声明变量，并用SET语句给其指派值。

Copyright
2013-2016

---

第7章 SQL语法

#### 7.8.1.1 声明变量

所有变量在声明后均初始化为 NULL。

1.        
语法格式

declare\_variable\_statement

    
::= DECLARE  @
variable\_name  data\_type

[, ……n ]

2.        
参数说明

l        
@ variable\_name

指定变量的名称。变量名必须以at符（@）开头。variable\_name必须符合标识符规则。

l        
data\_type

指定声明的变量类型。

3.        
用法示例

下面声明一个varchar类型的变量，变量初始化为NULL：

 

B 例：

DECLARE @find varchar(30)；

SELECT @find

------------------------------------

NULL

下例声明了两个变量，一个varchar类型的变量，一个int类型的变量：

 

B 例：

DECLARE @find varchar(30), @temp int;

SELECT @find, @temp

----------------------------------------------------

NULL   NULL

Copyright
2013-2016

---

第7章 SQL语法

#### 7.8.1.2 设置变量

1.        
语法格式

set\_variable\_statement

  ::= SET @variable\_name expression

2.        
参数说明

n        
@variable\_name

在DECLARE语句中声明的变量。

n        
expreesion

任何有效的表达式。expression的数据类型必须与声明变量时的数据类型相互兼容。

3.        
用法示例

下面声明了一个varchar类型的变量，并赋值：

 

B 例：

    DECLARE @name varchar(20);

    SET @name = 'Jack';

    SELECT @name

    ----------------------------------------

   
Jack

下面声明了两个变量，并赋值：

 

B 例：

    DECLARE @name varchar(20), @temp int;

    SET @name = 'Jack', @temp = 10;

    SELECT @name, @temp

   
-------------------------------------------------------

    Jack    10

下面给出一个使用自定义变量的示例：

 

B 例：

    DECLARE @iCollectorId int;

    SET @iCollectorId = 3;

    SELECT CollectorName, CollectorID, CollectorType

    FROM Collector

    WHERE CollectorID > @iCollectorId

    声明了int型变量，并赋值为3。然后查找采集器ID大于3的采集名、采集器ID以及采集器类型。

Copyright
2013-2016

---

第7章 SQL语法

### 7.8.2  系统变量

工业实时数据库中包含历史数据表，从中可以查询历史数据信息，包括数据变量名、采集时间、数据版本、数据质量戳以及数据值。在使用KDB-SQL语句查询历史数据信息时，需要用到工业实时数据库定义的系统变量。与用户自定义变量不同，系统变量都是以@@开头。

1.       
@@SamplingMode

指定采样模式。工业实时数据库支持7种采样模式，如下表所示：

 

|  |  |
| --- | --- |
| 采样模式 | 含义 |
| ‘Unknown’ | 未知方式。 |
| ‘CurrentValue’ | 取当前值。 |
| ‘Interpolated’ | 线性插值方式取数据。 |
| ‘RawByTime’ | 根据采样间隔取原始数据，这也是默认设置。 |
| ‘RawByNumber’ | 根据采样点数取原始数据。 |
| ‘Calculated’ | 根据计算模式取数据。 |
| ‘Stepped’ | 步进插值方式取数据。 |
| ‘Trend’ | 趋势曲线方式取数据。 |

 

下面的语句显示了指定根据采样间隔取原始数据采样模式：

 

B 例：

SET
@@SamplingMode = 'RawByTime';

SELECT TagName, DataTime, DataValue

FROM History

WHERE datavalue
= 0 AND datatime BETWEEN '2006-9-8 13:40' AND '2006-9-8 13:42'

2.        
@@ SamplingNumber

指定采样点数。只有指定采样模式为’RawByNumber’时采样点数才有效。采样点数默认值为1000。

下面的语句显示了指定采样点数为2000：

 

B 例：

   
SET @@SamplingMode = 'RawByNumber';

SET @@SamplingNumber = 2000;

SELECT TagName, DataTime, DataValue

FROM History

WHERE datavalue
= 1 AND datatime BETWEEN '2006-9-8 13:40' AND  '2006-9-8 13:42' AND
tagname = 'test5\_kvbool1.value'

3.        
@@SamplingInterval

指定采样间隔，单位为毫秒。只有指定采样模式为’Interpolated’、’Calculated’、’Stepped’或’Trend’时采样间隔才有效。

l      
如果采样模式为’Interpolated’、’Calculated’或’Stepped’，采样间隔可以小于0。 

如果采样间隔小于0，则采样间隔为默认采样间隔60秒（=60\*1000ms）。如果采样间隔等于0，则采样间隔为整个检索时间范围。

l      
如果采样模式为’Trend’，采样间隔不可以小于0。如果采样间隔等于0，则采

样间隔为检索时间范围除以默认趋势间隔数目（=512）。如果采样间隔大于最

大采样间隔1000天（=1000\*24\*60\*60\*1000ms），则采样间隔是最大采样间隔。

        
下面的例子指定了采样间隔为5000ms：

 

        B 例：

           
SET @@SamplingMode = 'Interpolated';

SET
@@SamplingInterval = 5000;

SELECT TagName,
DataTime, DataValue

FROM History

WHERE datavalue
= 1 AND datatime BETWEEN '2006-9-8 13:40' AND  '2006-9-8 13:42' AND
tagname = 'test5\_kvbool1.value'

4.        
@@DataVersion

指定数据版本。数据版本的有效取值范围为0-32767。其中0是第一个数据版本（即原始版本），1是第一个修改版本，以此类推。数据的最后一个版本为数据的最新版本。只有指定采样模式为’RawByNumber’或’RawByTime’时数据版本才有效。其它采样模式下，只考虑数据的最新版本。

5.        
@@ CalculationMode

指定计算模式。工业实时数据库支持以下计算模式：

 

|  |  |
| --- | --- |
| 计算模式 | 含义 |
| ‘Unknown’ | 未知方式 |
| ‘Count’ | 原始值记数 |
| ‘Average’ | 时间加权原始平均值 |
| ‘Total’ | 时间加权原始数值求和 |
| ‘StandardDeviation’ | 时间加权标准方差 |
| ‘RawTotal’ | 原始值求和 |
| ‘RawAverage’ | 原始值的算术平均值 |
| ‘RawStandardDeviation’ | 原始值的标准方差 |
| ‘Minimum’ | 最小值（时间戳为采样区间起始时间） |
| ‘Maximum’ | 最大值（时间戳为采样区间起始时间） |
| ‘MinimumTime’ | 最小值对应时间 |
| ‘MaximumTime’ | 最大值对应时间 |
| ‘DurationGood’ | 好数据时间长度 |
| ‘DurationBad’ | 坏数据时间长度 |
| ‘MinimumActualTime’ | 最大值（时间戳为最小值自身时间戳） |
| ‘MaximumActualTime’ | 最小值（时间戳为最大值自身时间戳） |
| ‘Start’ | 第一个原始数据 |
| ‘End’ | 最后一个原始数据 |
| ‘Delta’ | 第一个好的原始数据和最后一个好的原始数据之差 |
| ‘Range’ | 最大最小值的绝对差值 |
| ‘PercentGood’ | 好数据的时间百分比 |
| ‘PercentBad’ | 坏数据的时间百分比 |

 

6.        
@@ FilterTag

指定过滤变量。

7.        
@@ FilterMode

指定过滤模式。工业实时数据库支持以下过滤模式：

 

|  |  |
| --- | --- |
| 过滤模式 | 含义 |
| ‘Unknown’ | 未知模式 |
| ‘ExactTime’ | 过滤条件为真的确切时间点 |
| ‘BeforeTime’ | 从过滤条件为假到过滤条件为真的时间段 |
| ‘AfterTime’ | 从过滤条件为真到下次过滤条件为假的时间段 |
| ‘BeforeAndAfterTime’ | 过滤条件为假到下次过滤条件为假的时间段 |

 

8.        
@@ FilterComparisonMode

指定过滤比较模式。工业实时数据库支持以下过滤比较模式：

|  |  |
| --- | --- |
| 过滤比较模式 | 含义 |
| ‘Unknown’ | 未知比较模式 |
| ‘Equal’ | 等于 |
| ‘NotEqual’ | 不等于 |
| ‘LessThan’ | 小于 |
| ‘GreaterThan’ | 大于 |
| ‘LessThanEqual’ | 小于等于 |
| ‘GreaterThanEqual’ | 大于等于 |

 

9.        
@@ FilterValue

指定过滤变量值。

Copyright
2013-2016

---

第7章 SQL语法

### 7.8.3  显示执行计划

KDB-SQL提供了EXPLAIN语句用于显示SQL的执行计划，帮助分析SQL语句。

1.        
语法格式

explain\_statement

   ::= EXPLAIN { select\_statement }

2.        
参数说明

l        
{ select\_statement }

指定执行何种语句，分析该语句的执行计划。

3.        
用法示例

下面给出一个显示SELECT语句的示例：

 

B 例：

EXPLAIN SELECT \* FROM Realtime

显示SELECT语句的执行计划。

Copyright
2013-2016

---

第7章 SQL语法

### 7.8.4  分支语句

Copyright
2013-2016

---

第7章 SQL语法

#### 7.8.4.1 NULLIF

如果两个指定的表达式相等，则返回空值。

l        
语法格式

NULLIF(expression,
expression)

l        
参数说明

n        
expression

    常量、列名、函数、子查询或算术运算符、按位运算符以及字符串运算符的任意组合。

l        
用法示例

    如果两个表达式不相等，NULLIF 返回第一个 expression 的值。如果相等，NULLIF 返回第一个 expression 类型的空值。

 

    B 例：

        SELECT
NULLIF(T.CollectorType, C.CollectorType)

        FROM Tag AS T, Collector
AS C

Copyright
2013-2016

---

第7章 SQL语法

#### 7.8.4.2 CASE

CASE语句计算条件列表并返回多个可能结果表达式之一。KDB-SQL支持两种CASE格式：简单CASE函数和CASE搜索函数。

1.        
简单CASE函数

简单 CASE 函数将某个表达式与一组简单表达式进行比较以确定结果。

l        
语法格式

CASE
input\_expression

  WHEN
when\_expression THEN result\_expression

  [, ……n ]

  [ ELSE
eles\_result\_expression ]

END

l        
参数说明

n        
input\_expression

   
是使用简单 CASE 格式时所计算的表达式，可以为任何有效的表达式。

n        
WHEN when\_expression

    使用简单 CASE 格式时 input\_expression 所比较的简单表达式。when\_expression是任意有效的表达式。input\_expression和每个when\_expression的数据类型必须相同，或者是隐性转换。

n        
THEN result\_expression

当input\_expression = when\_expression取值为TRUE时返回的表达式。result\_expression可以是任何有效的表达式。

n        
ELSE else\_result\_expression

当比较运算取值不为 TRUE 时返回的表达式。如果省略此参数并且比较运算取值不为 TRUE，CASE 将返回 NULL 值。else\_result\_expression是任何有效的表达式。Else\_result\_expression和所有 result\_expression
的数据类型必须相同，或者必须是隐性转换。

对于简单CASE函数：

n        
计算 input\_expression，然后按指定顺序对每个 WHEN 子句的 input\_expression = when\_expression进行计算。

n        
返回第一个取值为
TRUE 的 (input\_expression
= when\_expression) 的 result\_expression。

n        
如果没有取值为 TRUE
的 input\_expression = when\_expression，则当指定 ELSE 子句时将返回 else\_result\_expression；若没有指定 ELSE 子句，则返回 NULL 值。

l        
用法示例

 

   
B 例：

     SELECT  

       CASE CollectorType

            
WHEN 1 THEN '计算引擎'

            
WHEN 2 THEN 'OPC数据采集器'

            
WHEN 3 THEN '报警服务器'

            
WHEN 4 THEN '组态王数据采集器'

            
ELSE 'Not yet categorized'

          
END

AS 采集器类型 
FROM Tag

在 SELECT 语句中，简单 CASE 函数仅检查是否相等，而不进行其它比较。在本例中，根据不同的采集器类型，显示不同的变量分类。

2.        
CASE搜索函数

CASE 搜索函数计算一组布尔表达式以确定结果。

l        
语法格式

CASE

     
WHEN boolean\_expression THEN result\_expression

  [, ……n ]

  [ ELSE
else\_result\_expression ]

     
END

l        
参数说明

n        
WHEN boolean\_expression

    使用 CASE 搜索格式时所计算的布尔表达式。boolean\_expression 是任意有效的布尔表达式。

对于CASE搜索函数：

n        
按指定顺序为每个 WHEN
子句的 boolean\_expression求值。

n        
返回第一个取值为
TRUE 的 boolean\_expression
的result\_expression

n        
如果没有取值为 TRUE
的 boolean\_expression，则当指定 ELSE 子句时将返回else\_result\_expression；若没有指定 ELSE 子句，则返回 NULL 值。

l        
用法示例

 

   
B 例：

SELECT   

   CASE

      WHEN CollectorType = 1 THEN '计算引擎'

         
WHEN CollectorType = 2 THEN 'OPC数据采集器'

         
WHEN CollectorType = 3 THEN '报警服务器'

         
ELSE '未识别采集器类型'

         
END

AS 采集器类型
FROM Tag

在 SELECT 语句中，CASE 搜索函数允许根据比较值在结果集内对值进行替换。

Copyright
2013-2016