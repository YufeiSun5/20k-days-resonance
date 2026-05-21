第三十章 
KingSCADA-Portal瘦客户端系统

# 第三十章  KingSCADA-Portal瘦客户端系统

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.1.       概述

为了满足系统集成以及远程监控的发展需求，同时为了适应和满足越来越多的谷歌浏览器用户，开发了一款能直接将KS客户端工程进行转换并通过浏览器浏览的瘦客户端系统，满足了兼容性、拓展性、易融合的需求。

KingSCADA-Portal是在KingPortal产品的基础上做的改造和适配，只提供运行服务、数据源服务、用户登录服务，没有开发态。工程由通过KS客户端工程转换而来，经过简单的部署和服务配置、启动即可浏览，无需单独开发工程。使用KingSCADA3.7SP1及以上版本的用户可以选择性安装运行服务，工程转换功能跟随KS安装，入口在客户端功能树。

在KingSCADA3.8版本中，对3.7SP1中KSP遇到的故障进行了修复和合并，对部分之前未实现的方法和功能进行了补充和完善，其中主要部分在于KSP支持KingSCADA系统的冗余、报警及事件以及变量域的相关功能。有关KSP支持KS冗余，主要是指发生冗余切换后，自动连接处于激活状态的服务器，支持实时数据、历史数据、报警数据的冗余，浏览器KSP前端自动刷新保证用户的操作及展示的数据不会间断。

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.2.       工程转换

***注：只有客户端工程可以进行工程转换。***

在KS客户端工程下找到KSP客户端->发布配置，打开Web ThinClientPack发布界面。

图 功能入口

图 Web ThinClientPack发布界面

**1****、选择发布画面：**

在“工程画面”列表框中选择要发布的画面，然后单击按钮将选中的画面移动到“选中画面”列表框中，同样可以单击按钮将已选中的画面取消发布，也可以通过双击画面的方法添加或取消画面发布。

对于没有修改的画面，在第一次发布成功之后，后续可以不再进行发布，只发布有改动的画面，从而节省发布所需要的时间。

***注：按住Ctrl******可以点选多个画面，按住SHIFT******可以连续选择多个画面。***

**2****、选择浏览时的初始画面：**

在“选中画面”列表框中选择一个或多个画面作为客户端浏览时的初始画面，即在KS-Portal登录之后首先显示的画面，如图所示，“页眉、左侧菜单、工艺01”三个画面被设置为初始画面。

**3****、KSP****属性配置：**

Ø 
增加5项转出的对象，分别是画面、变量、站点、系统脚本和资源，可以对需要发布的项进行选择，单独发布某一项或者多项，对于没有改动的项可以取消勾选，本次不进行发布，节约时间；**注：第一次发布时需要全部勾选进行发布**

Ø 
访问工程名称：默认为客户端工程名称，可自定义修改，在浏览器输入网址时需要带有该工程名称信息；

Ø 
登录页：KS-Portal运行系统自带一个登陆首页，当登录页设置为默认时，浏览时会打开自带登录页，在该页面输入账号密码之后可以跳转到步骤2设置的初始画面。当登录页选择为其他画面时，画面内需要包含LogOnNoInput函数，在KS-Portal中LogOnNoInput函数执行成功之后会自动跳转至步骤2设置的首页，不需按照KS的方式ShowPicture打开页面。

**4****、KSP****手机配置：**

Ø 
分辨率：默认为分辨率宽1920、高1080，可以根据实际需求进行修改；开发的手机画面分辨率（可以是多页面拼接之后的分辨率）应和此分辨率保持一致；

Ø 
手机登录页：KS-Portal运行系统自带一个手机登陆首页，当登录页设置为默认时，浏览时会打开自带登录页，在该页面输入账号密码之后可以跳转到下方选择的初始画面（手机端的初始页面和PC端的分开设置，点击图中红框里右侧的三个点，弹出初始画面选择界面，选择一个或多个画面作为客户端浏览时的初始画面，即在手机端登录之后首先显示的画面，如图中选择画面“冗余测试”作为初始画面）。当登录页选择为其他画面时，画面内需要包含LogOnNoInput函数，在KS-Portal中LogOnNoInput函数执行成功之后会自动跳转至下方选择的的初始页面。

Ø 
如没有手机端浏览的需求，此处可不进行任何修改，转出默认参数即可。也可仅修改此处配置仅作为手机端运行，PC端的参数默认即可。

**5****、KSP****工程路径：**

默认为安装文件下的KingClient文文件夹，可以自定义选择文件路径放在任意位置。转出之后将在该路径下生成以工程名称命名的工程文件夹。

l 
***注①：工程转换之前，需要先在“站点管理”中加载需要读取数据的全部服务端工程，在“本应用设置”里选择好登录服务器。***

l 
***注②：PC******端的设置参数和手机端参数相互独立，互不干扰。***

l 
***转换报错：对于KS-Portal******暂未支持的KS******函数、控件方法等，会在转换时在KS******错误列表给出提示，包括函数、方法名称已经所在详细位置，精确到脚本所在编辑器的行列，并且可通过双击错误内容跳转至错误位置。不支持的脚本转出之后会影响工程运行，需要修改至无错误才能保证BS******正常运行。***

图 错误列表

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.3.       工程部署和服务启停配置

在KS客户端工程下找到KSP客户端->启停控制，打开KS-Portal服务工具界面。

图 KS-Portal服务工具界面

**1、****工程加载和取消**

允许同时加载多个工程。

点击“加载工程”按钮选择转出文件夹下的.kspapp工程文件进行工程加载，加载之后的文件会显示在工程列表中，新加载的工程状态是未部署。工程名称不允许重复（避免浏览器读取错误），相同工程名称会提示“工程已存在”；点击“取消加载”按钮会从列表中删除工程。

已部署的工程不可取消加载，需先取消部署再取消加载。

图 同名功能加载提示

**2、****工程部署和取消**

允许同时部署多个工程，每个已经部署的工程均可通过浏览器访问。

选中加载的工程，点击“部署”按钮，该工程状态变为已部署，同时会将工程信息写入运行配置文件；点击“取消部署”，工程状态变更为未部署，同时运行配置文件内的工程信息被删除，工程无法再进行访问，此时的工程可以在列表取消加载。

**3、****服务启动、停止和重启**

选中对应的服务，可以控制服务的启动、停止；

点击重启会重新启动全部服务。

**4、****端口配置**

后台服务默认端口运行服务11015、用户服务端口11013、数据源服务端口11016。点击“配置”按钮可以对运行端口进行修改，**点击“确认”按钮修改配置文件，重启服务运行端口生效**。

图 端口配置

***注：1.******部署之后的同一个工程，可以通过工程名称同时在PC******端和手机端进行浏览，开发工程时在同一客户端开发，但是要分别开发PC******端和手机端运行画面的处理逻辑。***

***2.******与3.7SP1******不同，取消了KingSCADAPortalde******安装目录的配置，必须要安装才能读取到正确的路径，否则可能会有报错的情况。***

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.4.       KS-Portal数据源说明

Ø 
**KS-Portal****支持的数据源：**

ü 
KSServer数据源：实时数据、历史数据、报警事件数据，并支持多KSServer；

ü 
KH：支持ODBC驱动，用select语句查询、KS系统函数以及KS服务端将KH设置为历史库的情况；

ü 
关系库：支持通过ODBC驱动连接关系库，暂不支持OLEDB驱动方式连接；

Ø 
**关于ODBC****数据源的说明：**

1、KS连接ODBC数据源使用的均为32位驱动（64位版本除外），通过KS-Portal连接需要使用64位数据源。关系库的32/64位驱动在各官网均可下载，KH的SDK文件夹也提供了两个版本的驱动。

2、KS客户端使用ODBC数据源，需要在每个运行客户端的计算机都进行ODBC数据源配置，KS-Portal只需要在运行KS-Portal服务的计算机进行数据源配置即可。

3、如果一台计算机既运行KS（客户端/服务端），又同时运行KS-Portal服务，需要分别安装32位和64位数据源驱动（可正常安装，不冲突）、分别建立两个版本的DataSourceName（DSN），名称相同即可（可建立两个相同名称不同位的数据源，不冲突）

图 数据源管理器

*64**位ODBC**数据源管理器位置：C:\Windows\System32\odbcad32.exe*

*32**位ODBC**数据源管理器位置：C:\Windows\SysWOW64\odbcad32.exe*

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.5.       KS-Portal用户系统说明

KS-Portal和KS服务端共用一套用户登录系统，后台通过RESTful请求KS用户数据并执行用户登录、校验权限。只允许一个KS服务端作为用户登录服务器存在，即当多个KS服务器作为数据源服务时，只能选择其中一台作为登录服务器。

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.6.       KS-Portal授权

Ø  **授权方式**

KingSCADA-Portal只支持新加密锁授权（红色或者绿色的硬锁以及软授权），和原WEB发布共用一个授权参数，即授权参数中的BS客户端数目，在3.8版本中是WEB发布数量和KS-Portal数量的总和，且不分别规定两种BS的数量。

Ø  **授权机器**

KS-Portal服务和KS服务端在同一机器时，直接读取本机授权；KS-Portal服务和KS服务端分开部署时，KS-Portal运行服务需要远程读取KS服务端授权信息。

**并且登录服务器和授权服务器必须是同一台机器，授权数烧在用于用户登录的KS****服务端机器。**

Ø  **授权参数**

此版上锁序列号说明如下：

**运行锁号：****KSRS-0-60000-0-10-0-2-1 
A1-L1-N1-D1-V1**

**以下解释了锁号参数的含义，顺序由左向右：**

**KSRS****：**运行锁，KSM代表开发锁

**0****：**代表OEM号，通用版本为0

**60000****：**代表点数

**0****：**c/s客户端数目

**10****：**B/s客户端数目，即web数目（web发布和KS-Portal总数量）

**0****：**移动客户端数目

**2****：**OPCUA授权，不为0表示有OPCUA授权

**1:**  代表GIS授权，不支持则显示为0

**A1****：**代表一个实例数，如果是网络锁，A后面的数字就是实例的个数，即多少个程序可用这把锁

**L1****：**代表本地访问是否允许，若不允许则为L0

**N1****：**代表是不是网络锁，若不是则为N0

**D1****：**代表是不是支持远程桌面，若不支持则显示为D0

**V1****：**代表是不是支持虚拟机，若不支持，则显示为V0

Ø  **没有授权的情况**

原WEB发布，没有BS客户端授权的情况下不允许连接服务端，不提供数据服务。

当前KS-Portal在找不到BS客户端授权的情况下，每个IP允许登录浏览10分钟（每个IP下全部客户端只有10分钟），到期自动退出登录。同一IP再次登录提示无授权，其他未登录过的IP不受影响。

重启KS-Portal运行服务，所有IP下的客户端重新开始计算时间。

***注：KS-Portal******采用“******登录访问数据”******模式，不登录用户的情况下不提供数据服务***

Ø 
**客户端的概念：**

² 
按浏览器记录客户端数量

1）同一台电脑的谷歌浏览器，打开多个标签，先后登录多个不同的用户，只计算一个客户端；

2）同一台电脑，开一个Edge浏览器和一个谷歌内核的浏览器，登录相同/不同用户，计算为两个客户端。

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.7.       KS-Portal运行参数

n  **KS-Portal****服务端运行参数**

Ø  **网络要求**

ü 
TCP/IP协议（局域网环境或外网环境）；

ü 
带宽：100Mbps及以上；

Ø  **硬件要求**

ü 
硬盘：10GB及以上剩余空间

ü 
内存：32GB及以上

ü 
CPU：16核心32线程，主频2.5GHz以上（并发100客户端）

Ø  **软件要求**

ü 
微软视窗系统环境（Windows
Server2012及以上，Win7旗舰版专业版、Win10专业版）；

n  **KS-Portal****客户端浏览参数**

Ø  **网络要求**

ü 
TCP/IP协议（与服务器相同局域网环境或外网环境）；

ü 
带宽：100Mbps及以上；

Ø  **硬件要求**

ü 
硬盘：1G及以上剩余空间

ü 
内存：4G及以上

ü 
CPU：主频2GHz及以上；

Ø  **软件要求**

ü 
微软视窗系统环境（Win7、Win10）；

ü 
Chrome浏览器（67.0.3396.99正式版本（64位）或以上版本）、火狐（版本）兼容的浏览器

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.8.       工程访问及公网发布

工程浏览采用浏览器浏览方式，指定浏览器为谷歌浏览器（其他浏览器可以打开，但是可能会导致某些功能无法使用，不建议使用。*其中**Edge**、火狐浏览器适配度较高，**IE**无法使用*）。

工程访问网址格式：<http://IP:Port/?Pro=ProjectName>

l 
IP：运行服务器IP

l 
Port：运行服务运行端口，默认11015

l 
ProjectName：工程名称

例如：*http://127.0.0.1:11015/?Pro=**工程demo*

***注：如果输入的ProjectName******对应的工程未加载部署，默认会直接打开******ProjectManager.json******文件中的第一个工程。***

Ø 
公网发布需要开放的端口（默认端口可手动修改）：

l 
运行服务端口（默认11015）

l 
数据源服务（默认11016）

***注：如果工程中涉及到ODBC******数据源，可能会在脚本参数中暴露数据库（关系库、KH******）的账号密码，在浏览器开发者环境可以获取，如存在相关功能，请慎重考虑公网发布是否安全。***

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.9.       单页面访问实现方式

Ø 
**目的**

l 
实现KS通过瘦客户端发布客户端工程之后，将页面嵌入到第三方进行访问

l 
单页面访问时，在第三方系统中免登陆，且能通过url传参的方式访问不同的单页面

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.9.1.                    方式一：工程方式

Ø 
**实现思路**

实现的前提是要发布一个带自定义登录页面的工程。后台根据用户输入的URL请求进行判断，若打开的登录页没有登录函数，按照正常的登录流程进行；若登录页打开时带有LogOnNoInput，则进行URL判断：URL不带页面名称，则打开自定义初始页面；URL中带有“#页面名称”，则打开URL中定义的页面。

图 登录流程

Ø 
**部署条件**

用于单画面访问的发布工程登录页必须是自定义页面，不能是默认KP登录页，并且自定义的登录页打开时脚本内要写LogOnNoInput函数并传入参数登录一个用户（仅此一个页面需要）

图 登录页设置

图 打开时脚本

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.9.2.                    方式二：URL密文传参

Ø  **前提条件**

需要先通过接口获取密码的密文格式，在根据或得的密文格式拼接URL进行访问。

Ø  **密码加密接口**

KSP密码加密接口，用于随机获取一个加密之后的字符串，

Ø  **调用说明**

***注：获得到的密码只允许使用一次，执行一次登录之后密码立即失效！***

接口格式：

[http://IP:Port/getencryptPass](http://127.0.0.1:11005/getencryptPass) (IP/Port分别为KSP运行态IP和端口)

方式：get

Ø  **请求参数**

| 名称 | 类型 | 是否必选 | 示例值 | 描述 |
| --- | --- | --- | --- | --- |
| pass | String | 是 | Admin001 | 用户密码 |

Ø  **返回数据**

| 名称 | 类型 | 示例值 | 描述 |
| --- | --- | --- | --- |
| status | Integer | 0 | 状态码：  0 ：正常；-1：参数名称错误；  其他：未知，可能是接口、端口等问题 |
| pass | String | KZZMwfyF5dnPBGRIjp4OQp5jsWkKHSV/IoMFl++wCTuY3A0TrVrOfq0JzzSoApVdAew0ljqXxNNes4vL/rgLBQ==?a5IsQoXeitJOQGIOYE1rxg78LC7sL84MKJYaR7LOtY8=" | 加密后的字符串 |

Ø  **示例**

**请求示例****：**

<http://127.0.0.1:11015/getencryptPass>

**参数：**

{

  "pass":"admin001"

}

**正常返回示例****：**

{

"status":0,

"pass":"KZZMwfyF5dnPBGRIjp4OQp5jsWkKHSV/IoMFl++wCTuY3A0TrVrOfq0JzzSoApVdAew0ljqXxNNes4vL/rgLBQ==?a5IsQoXeitJOQGIOYE1rxg78LC7sL84MKJYaR7LOtY8="

}

Ø  **访问格式**

http://IP:Port/?Pro=ProjectName&u=UserName&p=PassWord#PageName

 

l  IP :                KSP运行服务IP

l  Port :             KSP运行服务端口

l  ProjectName：工程名称

l  UserName：   用户名（KS用户）

l  PassWord：    加密后的密码

l  PageName：   页面名称（可选：不加#PageName默认打开初始页面）

Ø  **访问示例**

<http://127.0.0.1:11015/?Pro=KSPClient&u=KVUser1&p=KZZMwfyF5dnPBGRIjp4OQp5jsWkKHSV/IoMFl++wCTuY3A0TrVrOfq0JzzSoApVdAew0ljqXxNNes4vL/rgLBQ==?a5IsQoXeitJOQGIOYE1rxg78LC7sL84MKJYaR7LOtY8=#Page1>

**注：也可以直接使用不加密的密码进行单画面访问（看客户保密要求）**

---

第三十章 
KingSCADA-Portal瘦客户端系统

## 30.10. 支持的KS功能清单

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.1.               画面图素类

|  |  |  |  |
| --- | --- | --- | --- |
| 模块 | 分类 | 名称 | 支持情况 |
| 画面 | 图素类 | 直线 | 支持 |
| 画面 | 图素类 | 折线 | 支持 |
| 画面 | 图素类 | 弧线 | 支持 |
| 画面 | 图素类 | 曲线 | 支持 |
| 画面 | 图素类 | 贝塞尔曲线 | 支持 |
| 画面 | 图素类 | 矩形 | 支持 |
| 画面 | 图素类 | 圆角矩形 | 支持 |
| 画面 | 图素类 | 椭圆 | 支持 |
| 画面 | 图素类 | 扇形 | 支持 |
| 画面 | 图素类 | 弓形 | 支持 |
| 画面 | 图素类 | 多边形 | 支持 |
| 画面 | 图素类 | 封闭曲线 | 支持 |
| 画面 | 图素类 | 封闭贝塞尔曲线 | 支持 |
| 画面 | 图素类 | 文本 | 支持 |
| 画面 | 图素类 | 管道 | 支持 |
| 画面 | 图素类 | 组合图素-基本图素和UI控件跨层级组合 | 不支持 |
| 画面 | 图素类 | 组合图素-单独的基本图素 | 支持 |
| 画面 | 图素类 | 图形模型 | 支持 |
| 画面 | 扩展类 | 图像 | 支持 |
| 画面 | 扩展类 | 趋势曲线/XY曲线 | 支持 |
| 画面 | 扩展类 | 报警窗 | 支持 |
| 画面 | 扩展类 | 报表 | 支持 |
| 画面 | 扩展类 | 事件窗 | 支持 |
| 画面 | 扩展类 | 棒图/饼图 | 支持 |
| 画面 | 扩展类 | 水平标尺 | 不支持 |
| 画面 | 扩展类 | 垂直标尺 | 不支持 |
| 画面 | 扩展类 | 用户登录窗 | 不支持 |
| 画面 | UI控件 | 按钮 | 支持 |
| 画面 | UI控件 | 复选框 | 支持 |
| 画面 | UI控件 | 文本框 | 支持 |
| 画面 | UI控件 | 复杂文本框 | 支持 |
| 画面 | UI控件 | 组合框 | 支持 |
| 画面 | UI控件 | 单选框 | 支持 |
| 画面 | UI控件 | 树视图 | 支持 |
| 画面 | UI控件 | 多功能树 | 支持 |
| 画面 | UI控件 | 日历 | 支持 |
| 画面 | UI控件 | 日期时间 | 支持 |
| 画面 | UI控件 | 列表框 | 不支持 |
| 画面 | UI控件 | 相对时间 | 不支持 |
| 画面 | 闭合图素 | 画刷-纯色 | 支持 |
| 画面 | 闭合图素 | 画刷-线型 | 支持 |
| 画面 | 闭合图素 | 画刷-影线 | 不支持 |
| 画面 | 闭合图素 | 画刷-纹理 | 不支持 |
| 画面 | 闭合图素 | 画刷-路径 | 支持 |
| 画面 | 闭合图素 | 画笔-边框 | 支持 |
| 画面 | 非闭合图素 | 画笔 | 支持 |
| 画面 | 页面背景 | 页面背景 | 支持 |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.2.               动画链接类

|  |  |  |  |
| --- | --- | --- | --- |
| 模块 | 分类 | 名称 | 支持情况 |
| 动画链接 | 动作 | 鼠标触敏 | 不支持 |
| 动画链接 | 动作 | 飞出特效 | 不支持 |
| 动画链接 | 动作 | ToolTip | 支持 |
| 动画链接 | 基本 | 可见性 | 支持 |
| 动画链接 | 基本 | 使能 | 支持 |
| 动画链接 | 基本 | 组合流动 | 支持 |
| 动画链接 | 基本 | 管道流动 | 支持 |
| 动画链接 | 填充 | 水平填充 | 支持 |
| 动画链接 | 填充 | 垂直填充 | 支持 |
| 动画链接 | 属性 | 画刷 | 支持 |
| 动画链接 | 属性 | 画笔 | 支持 |
| 动画链接 | 旋转 | 自旋转 | 支持 |
| 动画链接 | 旋转 | 中心旋转 | 支持 |
| 动画链接 | 焦点 | 获得焦点 | 支持 |
| 动画链接 | 焦点 | 失去焦点 | 支持 |
| 动画链接 | 移动 | 水平 | 支持 |
| 动画链接 | 移动 | 垂直 | 支持 |
| 动画链接 | 缩放 | 水平 | 支持 |
| 动画链接 | 缩放 | 垂直 | 支持 |
| 动画链接 | 闪烁 | 画刷 | 支持 |
| 动画链接 | 闪烁 | 画笔 | 支持 |
| 动画链接 | 闪烁 | 可见性 | 支持 |
| 动画链接 | 闪烁 | 文本闪烁 | 支持 |
| 动画链接 | 值输入 | 离散值 | 支持 |
| 动画链接 | 值输入 | 模拟值 | 支持 |
| 动画链接 | 值输入 | 字符串 | 支持 |
| 动画链接 | 值输入 | 水平滑块输入 | 不支持 |
| 动画链接 | 值输入 | 垂直滑块输入 | 不支持 |
| 动画链接 | 值输出 | 离散值 | 支持 |
| 动画链接 | 值输出 | 模拟值 | 支持 |
| 动画链接 | 值输出 | 字符串 | 支持 |
| 动画链接 | 鼠标 | 左键按下 | 支持 |
| 动画链接 | 鼠标 | 左键弹起 | 支持 |
| 动画链接 | 鼠标 | 左键按住 | 支持 |
| 动画链接 | 鼠标 | 左键双击 | 支持 |
| 动画链接 | 鼠标 | 右键按下 | 支持 |
| 动画链接 | 鼠标 | 右键弹起 | 支持 |
| 动画链接 | 鼠标 | 右键按住 | 支持 |
| 动画链接 | 鼠标 | 右键双击 | 不支持 |
| 动画链接 | 鼠标 | 鼠标进入 | 支持 |
| 动画链接 | 鼠标 | 鼠标离开 | 支持 |
| 动画链接 | 鼠标 | 鼠标悬停 | 不支持 |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.3.               脚本函数类

|  |  |  |  |
| --- | --- | --- | --- |
| 模块 | 分类 | 名称 | 支持情况 |
| 系统脚本 | 应用程序脚本 | 启动时 | 支持 |
| 系统脚本 | 应用程序脚本 | 运行时 | 支持 |
| 系统脚本 | 应用程序脚本 | 关闭时 | 支持 |
| 系统脚本 | 定时器脚本 | 定时器脚本 | 支持 |
| 系统脚本 | 数据改变 | 数据改变 | 支持 |
| 系统脚本 | 条件事件脚本 | 条件事件脚本 | 支持 |
| 系统脚本 | 热键脚本 | 热键脚本 | 支持 |
| 系统脚本 | 自定义函数 | 自定义函数 | 支持 |
| 画面脚本 | 打开时 | 打开时 | 支持 |
| 画面脚本 | 存在时 | 存在时 | 支持 |
| 画面脚本 | 关闭时 | 关闭时 | 支持 |
| 画面脚本 | 定时器脚本 | 定时器脚本 | 支持 |
| 画面脚本 | 变量改变时脚本 | 变量改变时脚本 | 支持 |
| 函数脚本 | 画面操作函数 | ShowPicture | 支持 |
| 函数脚本 | 画面操作函数 | ShowPictureAt | 支持 |
| 函数脚本 | 画面操作函数 | ClosePicture | 支持 |
| 函数脚本 | 画面操作函数 | HidePicture | 支持 |
| 函数脚本 | 画面操作函数 | EnableNavigate | 不支持 |
| 函数脚本 | 画面操作函数 | ShowNavigateWindow | 不支持 |
| 函数脚本 | 画面操作函数 | GetPictureScrollXPos | 不支持 |
| 函数脚本 | 画面操作函数 | GetPictureScrollYPos | 不支持 |
| 函数脚本 | 画面操作函数 | GetPictureStatus | 不支持 |
| 函数脚本 | 画面操作函数 | MovePicture | 支持 |
| 函数脚本 | 画面操作函数 | PreViewWholePicture | 不支持 |
| 函数脚本 | 画面操作函数 | PreviewWindow | 不支持 |
| 函数脚本 | 画面操作函数 | PrintWholePicture | 不支持 |
| 函数脚本 | 画面操作函数 | PrintWindow | 不支持 |
| 函数脚本 | 画面操作函数 | PrintScreenSet | 不支持 |
| 函数脚本 | 画面操作函数 | PrintPictureSet | 不支持 |
| 函数脚本 | 画面操作函数 | SaveCurWindowToFile | 不支持 |
| 函数脚本 | 画面操作函数 | SaveCurWindowToFile1 | 不支持 |
| 函数脚本 | 画面操作函数 | SavePictureToFile | 不支持 |
| 函数脚本 | 画面操作函数 | SavePictureToFile1 | 不支持 |
| 函数脚本 | 画面操作函数 | SaveWholePictureToFile | 不支持 |
| 函数脚本 | 画面操作函数 | SaveWholePictureToFile1 | 不支持 |
| 函数脚本 | 画面操作函数 | SaveCurScreenToFile | 不支持 |
| 函数脚本 | 画面操作函数 | SaveCurScreenToFile1 | 不支持 |
| 函数脚本 | 画面操作函数 | ScrollPicture | 不支持 |
| 函数脚本 | 画面操作函数 | GetPictureWindowViewScale | 不支持 |
| 函数脚本 | 画面操作函数 | SetPictureWindowViewScale | 不支持 |
| 函数脚本 | 画面操作函数 | SetPictureWindowViewScale1 | 不支持 |
| 函数脚本 | 画面操作函数 | SetPicVarValue | 不支持 |
| 函数脚本 | 画面操作函数 | WindowSize | 不支持 |
| 函数脚本 | 画面操作函数 | SetPicToScreenCenter | 不支持 |
| 函数脚本 | 画面操作函数 | PrintScreen | 不支持 |
| 函数脚本 | 画面操作函数 | SavePicture | 不支持 |
| 函数脚本 | 画面操作函数 | PauseSecond | 支持 |
| 函数脚本 | 用户操作函数 | AddUser | 不支持 |
| 函数脚本 | 用户操作函数 | ChangePassword | 不支持 |
| 函数脚本 | 用户操作函数 | ChangeUserPassword | 不支持 |
| 函数脚本 | 用户操作函数 | CheckUser | 不支持 |
| 函数脚本 | 用户操作函数 | CheckUserEx | 不支持 |
| 函数脚本 | 用户操作函数 | DeleteUser | 不支持 |
| 函数脚本 | 用户操作函数 | EditUser | 不支持 |
| 函数脚本 | 用户操作函数 | EditUserRole | 不支持 |
| 函数脚本 | 用户操作函数 | EditUsers | 不支持 |
| 函数脚本 | 用户操作函数 | GetCurrentUser | 支持 |
| 函数脚本 | 用户操作函数 | GetCurrentSecurityPriority | 不支持 |
| 函数脚本 | 用户操作函数 | GetCurrentSecuritySection | 不支持 |
| 函数脚本 | 用户操作函数 | GetUserInfoContent | 不支持 |
| 函数脚本 | 用户操作函数 | GetLogonUsers | 不支持 |
| 函数脚本 | 用户操作函数 | LogOff | 支持 |
| 函数脚本 | 用户操作函数 | LogOn | 支持 |
| 函数脚本 | 用户操作函数 | LogOnNoInput | 支持 |
| 函数脚本 | 用户操作函数 | GetRoleArray | 不支持 |
| 函数脚本 | 用户操作函数 | GetUserArray | 不支持 |
| 函数脚本 | 用户操作函数 | GetUserArrayByRole | 不支持 |
| 函数脚本 | 用户操作函数 | GetUserBindType | 不支持 |
| 函数脚本 | 用户操作函数 | GetUserMaxlogonNum | 不支持 |
| 函数脚本 | 用户操作函数 | GetUserBindAddr | 不支持 |
| 函数脚本 | 用户操作函数 | GetUserIsForceLogon | 不支持 |
| 函数脚本 | 用户操作函数 | GetUsersAll | 不支持 |
| 函数脚本 | 用户操作函数 | SetUserBindType | 不支持 |
| 函数脚本 | 用户操作函数 | SetUserMaxlogonNum | 不支持 |
| 函数脚本 | 用户操作函数 | SetUserBindAddr | 不支持 |
| 函数脚本 | 用户操作函数 | SetUserIsForceLogon | 不支持 |
| 函数脚本 | 用户操作函数 | SetUserInfoContent | 不支持 |
| 函数脚本 | 字符串函数 | StrASCII | 支持 |
| 函数脚本 | 字符串函数 | StrChar | 支持 |
| 函数脚本 | 字符串函数 | StrFromInt | 支持 |
| 函数脚本 | 字符串函数 | StrFromReal | 支持 |
| 函数脚本 | 字符串函数 | StrFromTime | 支持 |
| 函数脚本 | 字符串函数 | StrInStr | 支持 |
| 函数脚本 | 字符串函数 | StrLeft | 支持 |
| 函数脚本 | 字符串函数 | StrLen | 支持 |
| 函数脚本 | 字符串函数 | StrLower | 支持 |
| 函数脚本 | 字符串函数 | StrMid | 支持 |
| 函数脚本 | 字符串函数 | StrReplace | 支持 |
| 函数脚本 | 字符串函数 | StrRight | 支持 |
| 函数脚本 | 字符串函数 | StrSpace | 支持 |
| 函数脚本 | 字符串函数 | StrToInt | 支持 |
| 函数脚本 | 字符串函数 | StrToReal | 支持 |
| 函数脚本 | 字符串函数 | StrToTime | 支持 |
| 函数脚本 | 字符串函数 | StrTrim | 支持 |
| 函数脚本 | 字符串函数 | StrType | 支持 |
| 函数脚本 | 字符串函数 | StrUpper | 支持 |
| 函数脚本 | 字符串函数 | Text | 支持 |
| 函数脚本 | 日期时间函数 | ConvertTimeToSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetCustomDateString | 支持 |
| 函数脚本 | 日期时间函数 | GetCustomTimeString | 支持 |
| 函数脚本 | 日期时间函数 | GetLocalDateTimeFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetLocalYearFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetLocalMonthFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetLocalDayFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetLocalHourFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetLocalMinuteFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetLocalSecondFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetSystemtimeDateString | 支持 |
| 函数脚本 | 日期时间函数 | GetSystemtimeTimeString | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeYear | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeMonth | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeDay | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeHour | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeMinute | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeMSec | 支持 |
| 函数脚本 | 日期时间函数 | GetSystimeWeek | 支持 |
| 函数脚本 | 日期时间函数 | GetUTCDateTimeFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetUTCYearFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetUTCMonthFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetUTCDayFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetUTCHourFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetUTCMinuteFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | GetUTCSecondFromSecond | 支持 |
| 函数脚本 | 日期时间函数 | TimeSynchNetNode | 不支持 |
| 函数脚本 | 日期时间函数 | TransDateTimeByTimeZone | 支持 |
| 函数脚本 | 日期时间函数 | GetDateTime | 支持 |
| 函数脚本 | 日期时间函数 | DateAdd | 支持 |
| 函数脚本 | 日期时间函数 | DateDiff | 支持 |
| 函数脚本 | 日期时间函数 | DatePart | 支持 |
| 函数脚本 | 日期时间函数 | SetLoaclSystemTime | 不支持 |
| 函数脚本 | 数学函数 | Abs | 支持 |
| 函数脚本 | 数学函数 | ArcCos | 支持 |
| 函数脚本 | 数学函数 | ArcSin | 支持 |
| 函数脚本 | 数学函数 | ArcTan | 支持 |
| 函数脚本 | 数学函数 | Average | 支持 |
| 函数脚本 | 数学函数 | Cos | 支持 |
| 函数脚本 | 数学函数 | Exp | 支持 |
| 函数脚本 | 数学函数 | Int | 支持 |
| 函数脚本 | 数学函数 | LogE | 支持 |
| 函数脚本 | 数学函数 | LogN | 支持 |
| 函数脚本 | 数学函数 | Max | 支持 |
| 函数脚本 | 数学函数 | Min | 支持 |
| 函数脚本 | 数学函数 | PI | 支持 |
| 函数脚本 | 数学函数 | Pow | 支持 |
| 函数脚本 | 数学函数 | Sgn | 支持 |
| 函数脚本 | 数学函数 | Sin | 支持 |
| 函数脚本 | 数学函数 | Sqrt | 支持 |
| 函数脚本 | 数学函数 | Sum | 支持 |
| 函数脚本 | 数学函数 | Tan | 支持 |
| 函数脚本 | 数学函数 | Trunc | 支持 |
| 函数脚本 | 系统函数 | ActivateApp | 不支持 |
| 函数脚本 | 系统函数 | displayMCI | 不支持 |
| 函数脚本 | 系统函数 | Exit | 支持 |
| 函数脚本 | 系统函数 | InfoAppActive | 不支持 |
| 函数脚本 | 系统函数 | InfoAppDir | 不支持 |
| 函数脚本 | 系统函数 | InfoAppTitle | 不支持 |
| 函数脚本 | 系统函数 | InfoDisk | 不支持 |
| 函数脚本 | 系统函数 | InfoFile | 不支持 |
| 函数脚本 | 系统函数 | InfoResource | 不支持 |
| 函数脚本 | 系统函数 | PlaySound | 支持 |
| 函数脚本 | 系统函数 | SendKeys | 不支持 |
| 函数脚本 | 系统函数 | StartApp | 不支持 |
| 函数脚本 | 系统函数 | StopApp | 不支持 |
| 函数脚本 | 系统函数 | Trace | 支持 |
| 函数脚本 | 系统函数 | ClearArray | 支持 |
| 函数脚本 | 系统函数 | CreatArray | 支持 |
| 函数脚本 | 系统函数 | GetArrayElement | 支持 |
| 函数脚本 | 系统函数 | GetArrayElementType | 支持 |
| 函数脚本 | 系统函数 | GetArrayLength | 支持 |
| 函数脚本 | 系统函数 | SetArrayElement | 支持 |
| 函数脚本 | 系统函数 | GetKey | 不支持 |
| 函数脚本 | 系统函数 | ShowMessageBox | 支持 |
| 函数脚本 | 系统函数 | AsynShowMessageBox | 支持 |
| 函数脚本 | 系统函数 | GetCursorPosX | 支持 |
| 函数脚本 | 系统函数 | GetCursorPosY | 支持 |
| 函数脚本 | 系统函数 | GetCurrPictureFocusGraphyName | 不支持 |
| 函数脚本 | 系统函数 | Ping | 不支持 |
| 函数脚本 | 系统函数 | Is64BitWindows | 不支持 |
| 函数脚本 | 系统函数 | RegODBCDataSource | 不支持 |
| 函数脚本 | 系统函数 | GetComputerName | 不支持 |
| 函数脚本 | 系统函数 | GetWindowsUserName | 不支持 |
| 函数脚本 | 系统函数 | GetProjectClientName | 不支持 |
| 函数脚本 | 系统函数 | GetProjectClientUnit | 不支持 |
| 函数脚本 | 系统函数 | CreatArray2 | 支持 |
| 函数脚本 | 系统函数 | SetArrayElementInt | 支持 |
| 函数脚本 | 系统函数 | GetArrayElementInt | 支持 |
| 函数脚本 | 系统函数 | SetArrayElementFloat | 支持 |
| 函数脚本 | 系统函数 | GetArrayElementFloat | 支持 |
| 函数脚本 | 系统函数 | SetArrayElementBool | 支持 |
| 函数脚本 | 系统函数 | GetArrayElementBool | 支持 |
| 函数脚本 | 系统函数 | SetArrayElementStr | 支持 |
| 函数脚本 | 系统函数 | GetArrayElementStr | 支持 |
| 函数脚本 | 文件操作函数 | CreateCsvFile1 | 不支持 |
| 函数脚本 | 文件操作函数 | FileCopy | 不支持 |
| 函数脚本 | 文件操作函数 | FileDelete | 不支持 |
| 函数脚本 | 文件操作函数 | FileMove | 不支持 |
| 函数脚本 | 文件操作函数 | FileReadFields | 不支持 |
| 函数脚本 | 文件操作函数 | FileReadStr | 不支持 |
| 函数脚本 | 文件操作函数 | FileWriteFields | 不支持 |
| 函数脚本 | 文件操作函数 | FileWriteStr | 不支持 |
| 函数脚本 | 文件操作函数 | ReadStringFromFile | 不支持 |
| 函数脚本 | 文件操作函数 | ShowFileDialog | 不支持 |
| 函数脚本 | 文件操作函数 | ShowFolderDialog | 不支持 |
| 函数脚本 | 数据集函数 | KDBGetDataset | 支持 |
| 函数脚本 | 数据集函数 | KDBClearDataset | 支持 |
| 函数脚本 | 数据集函数 | KDBDatasetMoveFirst | 不支持 |
| 函数脚本 | 数据集函数 | KDBDatasetMoveNext | 不支持 |
| 函数脚本 | 数据集函数 | KDBDatasetMovePrev | 不支持 |
| 函数脚本 | 数据集函数 | KDBDatasetMoveLast | 不支持 |
| 函数脚本 | 数据集函数 | KDBEditDataset1 | 支持 |
| 函数脚本 | 数据集函数 | KDBGetCurRowValueByFieldId | 不支持 |
| 函数脚本 | 数据集函数 | KDBGetCurRowValueByFieldName | 不支持 |
| 函数脚本 | 数据集函数 | KDBGetCellValueByFieldId | 支持 |
| 函数脚本 | 数据集函数 | KDBGetCellValueByFieldName | 支持 |
| 函数脚本 | 数据集函数 | KDBGetDatasetCols | 支持 |
| 函数脚本 | 数据集函数 | KDBGetDatasetRows | 支持 |
| 函数脚本 | 数据集函数 | KDBExecuteStatement | 支持 |
| 函数脚本 | 数据集函数 | KDBGetConnectID | 支持 |
| 函数脚本 | 数据集函数 | KDBStatusTest | 支持 |
| 函数脚本 | 数据集函数 | KDBDisConnect | 支持 |
| 函数脚本 | 数据集函数 | KDBGetDataset1 | 支持 |
| 函数脚本 | 数据集函数 | KDBExecuteStatement1 | 支持 |
| 函数脚本 | 数据集函数 | KDBSQLExecuteFromFile | 不支持 |
| 函数脚本 | 数据集函数 | KDBKSRawData | 支持 |
| 函数脚本 | 数据集函数 | KDBKSSampleData1 | 支持 |
| 函数脚本 | 数据集函数 | KDBKSSampleData2 | 支持 |
| 函数脚本 | 数据集函数 | KDBKHRawData | 支持 |
| 函数脚本 | 数据集函数 | KDBKHSampleData1 | 支持 |
| 函数脚本 | 数据集函数 | KDBKHSampleData2 | 支持 |
| 函数脚本 | 数据集函数 | KDBSetCMDTimeOut | 不支持 |
| 函数脚本 | 报警函数 | AckByGroupName | 支持 |
| 函数脚本 | 报警函数 | AckByTagName | 支持 |
| 函数脚本 | 报警函数 | AlarmConfirmDlgEnableFilter | 不支持 |
| 函数脚本 | 报警函数 | AlarmConfirmDlgAddUsers | 不支持 |
| 函数脚本 | 报警函数 | AlarmConfirmDlgRemoveUsers | 不支持 |
| 函数脚本 | 报警函数 | AlarmConfirmDlgClearUsers | 不支持 |
| 函数脚本 | 报警函数 | GetAlarmNumByFilterString | 支持 |
| 函数脚本 | 报警函数 | GetNoAckNumByFilterString | 支持 |
| 函数脚本 | 报警函数 | GetAlarmNumByFilterStringFromFile | 不支持 |
| 函数脚本 | 报警函数 | GetNoAckNumByFilterStringFromFile | 不支持 |
| 函数脚本 | 报警函数 | GetAlarmNum | 支持 |
| 函数脚本 | 报警函数 | GetNoAckNum | 支持 |
| 函数脚本 | 报警函数 | GetAlarmNumByGroup | 支持 |
| 函数脚本 | 报警函数 | GetNoAckNumByGroup | 支持 |
| 函数脚本 | 报警函数 | GetGroupName | 不支持 |
| 函数脚本 | 报警函数 | ResetByGroupName | 不支持 |
| 函数脚本 | 报警函数 | ResetByTagName | 不支持 |
| 函数脚本 | 其他函数 | CreateCsvFile2 | 不支持 |
| 函数脚本 | 其他函数 | DText | 支持 |
| 函数脚本 | 其他函数 | VarRefAddress | 支持 |
| 函数脚本 | 其他函数 | VarRefAddressRelease | 支持 |
| 函数脚本 | 其他函数 | CreateExcelFile | 不支持 |
| 函数脚本 | 其他函数 | GetTagField | 支持 |
| 函数脚本 | 其他函数 | GetTagFieldFloat | 支持 |
| 函数脚本 | 其他函数 | GetTagFieldInt | 支持 |
| 函数脚本 | 其他函数 | SetTagField | 支持 |
| 函数脚本 | 其他函数 | SaveTagFiledValueToAEDB1 | 不支持 |
| 函数脚本 | 其他函数 | IOGetServerRedunStatus | 不支持 |
| 函数脚本 | 其他函数 | ShowColourDialog | 不支持 |
| 函数脚本 | 其他函数 | ThreeDESFileEncrypt | 不支持 |
| 函数脚本 | 其他函数 | ThreeDESFileDecrypt | 不支持 |
| 函数脚本 | 其他函数 | Bit | 支持 |
| 函数脚本 | 其他函数 | BitSet | 支持 |
| 函数脚本 | 其他函数 | EmailAlarm | 不支持 |
| 函数脚本 | 其他函数 | EditAlarmNotify | 不支持 |
| 函数脚本 | 其他函数 | GetLocalIP | 不支持 |
| 函数脚本 | 其他函数 | WindowSize | 不支持 |
| 函数脚本 | 其他函数 | SubscribeTagFunc | 不支持 |
| 函数脚本 | 其他函数 | UnSubscribeTagFunc | 不支持 |
| 函数脚本 | 其他函数 | GetRGBValue | 不支持 |
| 函数脚本 | 其他函数 | BitI | 支持 |
| 函数脚本 | 其他函数 | GetExcelSheetsNum | 不支持 |
| 函数脚本 | 其他函数 | GetExcelSheetNameBySheedID | 不支持 |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.4.               基本UI事件类

|  |  |  |  |
| --- | --- | --- | --- |
| 模块 | 分类 | 名称 | 支持情况 |
| 按钮 | 事件 | 单击事件 | 支持 |
| 按钮 | 属性 | Caption | 支持 |
| 按钮 | 属性 | Visible | 支持 |
| 按钮 | 属性 | Enable | 支持 |
| 按钮 | 动画链接 | 使能 | 支持 |
| 按钮 | 动画链接 | 可见性 | 支持 |
| 组合框 | 事件 | 选项变化时 | 支持 |
| 组合框 | 属性 | SelectIndex | 支持 |
| 组合框 | 属性 | Visible | 支持 |
| 组合框 | 动画链接 | 使能 | 支持 |
| 组合框 | 动画链接 | 可见性 | 支持 |
| 组合框 | 方法 | GetCurrentText | 支持 |
| 组合框 | 方法 | ExportToFile | 不支持 |
| 组合框 | 方法 | ImportFromFile | 不支持 |
| 组合框 | 方法 | GetItemCount | 支持 |
| 组合框 | 方法 | AddItem | 支持 |
| 组合框 | 方法 | InsertItem | 支持 |
| 组合框 | 方法 | DeleteItem | 支持 |
| 组合框 | 方法 | ClearAll | 支持 |
| 组合框 | 方法 | GetFirstIndex | 支持 |
| 组合框 | 方法 | GetItemText | 支持 |
| 组合框 | 方法 | SetItemData | 支持 |
| 组合框 | 方法 | GetItemData | 支持 |
| 组合框 | 方法 | GetCurrentText | 支持 |
| 复选框 | 事件 | 选择状态变化事件 | 支持 |
| 复选框 | 属性 | Text | 支持 |
| 复选框 | 属性 | Checked | 支持 |
| 复选框 | 属性 | Layout | 支持 |
| 复选框 | 属性 | BackgroundColor | 不支持 |
| 复选框 | 属性 | Font | 不支持 |
| 复选框 | 属性 | Visible | 支持 |
| 复选框 | 动画链接 | 使能 | 支持 |
| 复选框 | 动画链接 | 可见性 | 支持 |
| 复选框 | 方法 | GetCheckState | 支持 |
| 复选框 | 方法 | SetCheckState | 支持 |
| 单选框 | 事件 | 单选项变化事件 | 支持 |
| 单选框 | 属性 | Visible | 支持 |
| 单选框 | 属性 | SelectedIndex | 支持 |
| 单选框 | 属性 | SelectedText | 支持 |
| 单选框 | 属性 | LayOut | 支持 |
| 单选框 | 属性 | RightToLeft | 支持 |
| 单选框 | 动画链接 | 使能 | 支持 |
| 单选框 | 动画链接 | 可见性 | 支持 |
| 单选框 | 方法 | GetButtonText | 支持 |
| 单选框 | 方法 | GetButtonIndex | 支持 |
| 单选框 | 方法 | SetButtonEnable | 支持 |
| 单选框 | 方法 | GetButtonEnable | 支持 |
| 单选框 | 方法 | SetButtonVisible | 支持 |
| 单选框 | 方法 | GetButtonVisible | 支持 |
| 单选框 | 方法 | SetButtonText | 支持 |
| 文本框 | 事件 | 失去焦点事件 | 不支持 |
| 文本框 | 事件 | 文本变化事件 | 支持 |
| 文本框 | 属性 | Text | 支持 |
| 文本框 | 属性 | Enable | 支持 |
| 文本框 | 属性 | Visible | 支持 |
| 文本框 | 属性 | 使能 | 支持 |
| 文本框 | 属性 | 可见性 | 支持 |
| 复杂文本框 | 属性 | Text | 支持 |
| 复杂文本框 | 属性 | Enable | 支持 |
| 复杂文本框 | 属性 | Visible | 支持 |
| 复杂文本框 | 属性 | 使能 | 支持 |
| 复杂文本框 | 属性 | 可见性 | 支持 |
| 复杂文本框 | 方法 | LoadFromFile | 不支持 |
| 复杂文本框 | 方法 | SaveToFile | 不支持 |
| 列表框 | 事件 | 选项变化事件 | 不支持 |
| 列表框 | 事件 | 单击选项事件 | 不支持 |
| 树视图 | 属性 | Visible | 支持 |
| 树视图 | 属性 | 使能 | 支持 |
| 树视图 | 属性 | 可见性 | 支持 |
| 树视图 | 事件 | 单击节点事件 | 支持 |
| 树视图 | 事件 | 双击节点事件 | 支持 |
| 树视图 | 事件 | 节点选择事件 | 支持 |
| 树视图 | 事件 | 展开节点事件 | 支持 |
| 树视图 | 事件 | 折叠节点事件 | 支持 |
| 树视图 | 事件 | 状态变化事件 | 支持 |
| 树视图 | 方法 | GetNodeCheckState | 支持 |
| 树视图 | 方法 | GetNodeParent | 支持 |
| 树视图 | 方法 | GetNodeText | 支持 |
| 树视图 | 方法 | GetNodeName | 支持 |
| 树视图 | 方法 | GetSelectedNodeName | 支持 |
| 树视图 | 方法 | GetSelectedNodeText | 支持 |
| 树视图 | 方法 | GetTreeNodeData | 支持 |
| 树视图 | 方法 | SetTreeNodeData | 支持 |
| 树视图 | 方法 | InsertNewNode | 支持 |
| 树视图 | 方法 | DeleteNode | 支持 |
| 树视图 | 方法 | ClearAllNodes | 支持 |
| 树视图 | 方法 | AddNewNode | 支持 |
| 树视图 | 方法 | SetNodeCheckState | 支持 |
| 树视图 | 方法 | SetNodeSelected | 支持 |
| 树视图 | 方法 | SetNodeText | 支持 |
| 树视图 | 方法 | GetNodeDisabledState | 不支持 |
| 树视图 | 方法 | SetNodeDisabledState | 不支持 |
| 多功能树视图 | 事件 | 单击节点事件 | 支持 |
| 多功能树视图 | 事件 | 双击节点事件 | 支持 |
| 多功能树视图 | 事件 | 节点选择事件 | 支持 |
| 多功能树视图 | 事件 | 展开节点事件 | 支持 |
| 多功能树视图 | 事件 | 折叠节点事件 | 支持 |
| 多功能树视图 | 事件 | 状态变化事件 | 支持 |
| 多功能树视图 | 方法 | GetCheckedNodes | 支持 |
| 多功能树视图 | 方法 | GetCheckedNodesNum | 支持 |
| 多功能树视图 | 方法 | GetNodeCheckState | 支持 |
| 多功能树视图 | 方法 | GetNodeParent | 支持 |
| 多功能树视图 | 方法 | GetNodeText | 支持 |
| 多功能树视图 | 方法 | GetSelectedNodeName | 支持 |
| 多功能树视图 | 方法 | GetSelectedNodeText | 支持 |
| 多功能树视图 | 方法 | GetTreeNodeData | 支持 |
| 多功能树视图 | 方法 | SetTreeNodeData | 支持 |
| 多功能树视图 | 方法 | AddRootNode | 支持 |
| 多功能树视图 | 方法 | InsertNewNode | 支持 |
| 多功能树视图 | 方法 | DeleteNode | 支持 |
| 多功能树视图 | 方法 | ClearAllNodes | 支持 |
| 多功能树视图 | 方法 | AddNewNode | 支持 |
| 多功能树视图 | 方法 | SetNodeCheckState | 支持 |
| 多功能树视图 | 方法 | SetNodeSelected | 支持 |
| 多功能树视图 | 方法 | SetNodeText | 支持 |
| 日历 | 事件 | 日期变化事件 | 不支持 |
| 日历 | 事件 | 日变化事件 | 不支持 |
| 日历 | 事件 | 月变化事件 | 不支持 |
| 日历 | 事件 | 年变化事件 | 不支持 |
| 日期时间控件 | 属性 | Value | 支持 |
| 日期时间控件 | 属性 | Visible | 支持 |
| 日期时间控件 | 属性 | Enable | 支持 |
| 日期时间控件 | 动画链接 | 使能 | 支持 |
| 日期时间控件 | 动画链接 | 可见性 | 支持 |
| 日期时间控件 | 事件 | 日期事件变化事件 | 支持 |
| 日期时间控件 | 事件 | 日变化事件 | 支持 |
| 日期时间控件 | 事件 | 月变化事件 | 支持 |
| 日期时间控件 | 事件 | 年变化事件 | 支持 |
| 日期时间控件 | 事件 | 时变化事件 | 支持 |
| 日期时间控件 | 事件 | 分变化事件 | 支持 |
| 日期时间控件 | 事件 | 秒变化事件 | 支持 |
| 日期时间控件 | 事件 | Value | 支持 |
| 图片 | 事件 | 位图播放 | 不支持 |
| 水平标尺 | 事件 | 无 | 不支持 |
| 垂直标尺 | 事件 | 无 | 不支持 |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.5.               报表

|  |  |  |  |
| --- | --- | --- | --- |
| 模块 | 分类 | 名称 | 支持情况 |
| 报表模式 | 实时报表 | 实时报表 | 支持 |
| 报表模式 | 历史报表 | 历史报表 | 支持 |
| 报表操作 | 支持单元格编辑 | 支持单元格编辑 | 支持 |
| 报表操作 | 支持计算公式 | 支持计算公式 | 支持 |
| 报表操作 | 支持合并单元格 | 支持合并单元格 | 支持 |
| 报表操作 | 支持样式配置 | 支持样式配置 | 支持 |
| 报表操作 | 支持显示链接变量 | 支持显示链接变量 | 支持 |
| 报表操作 | 文本自动换行 | 文本自动换行 | 支持 |
| 报表操作 | 支持图片插入 | 支持图片插入 | 支持 |
| 报表操作 | 支持复选框插入 | 支持复选框插入 | 支持 |
| 报表操作 | 支持Excel导入导出 | 支持Excel导入导出 | 支持 |
| 报表操作 | 支持报表打印 | 支持报表打印 | 支持 |
| 报表操作 | 运行态右键菜单 | 运行态右键菜单 | 支持 |
| 报表操作 | 排序功能 | 排序功能 | 不支持 |
| 报表事件 | 点击单元格事件 | OnClickCell | 支持 |
| 报表事件 | 右击单元格事件 | OnRClickCell | 支持 |
| 报表事件 | 查询事件 | OnBeginQuery | 不支持 |
| 报表事件 | 查询事件 | OnEndQuery | 不支持 |
| 报表事件 | 复选框事件 | OnReportCheckStatusChange | 支持 |
| 报表属性 | 行列操作 | RowCount | 支持 |
| 报表属性 | 行列操作 | ColumnCount | 支持 |
| 报表属性 | 行列操作 | HeadRowCount | 支持 |
| 报表属性 | 行列操作 | HeadColumnCount | 支持 |
| 报表属性 | 行列操作 | TailRowCount | 支持 |
| 报表属性 | 行列操作 | TailColumnCount | 支持 |
| 报表属性 | 行列操作 | ShowRowTitle | 支持 |
| 报表属性 | 行列操作 | ShowColumnTitle | 支持 |
| 报表属性 | 行列操作 | ShowInvalidDate | 支持 |
| 报表属性 | 行列操作 | ShowTagTitle | 支持 |
| 报表属性 | 行列操作 | ShowTimeTitle | 支持 |
| 报表属性 | 行列操作 | TimeStampFormat | 支持 |
| 报表属性 | 行列操作 | PrintToOnePage | 支持 |
| 报表属性 | 行列操作 | BackColor | 支持 |
| 报表属性 | 行列操作 | RowHeightLock | 支持 |
| 报表属性 | 行列操作 | ColWidthLock | 支持 |
| 报表属性 | 历史数据 | TimeParams | 支持 |
| 报表属性 | 历史数据 | HistoryTags | 支持 |
| 报表方法 | 清空类 | Clear | 支持 |
| 报表方法 | 填充类 | SetCellString | 支持 |
| 报表方法 | 填充类 | SetCellStrings | 支持 |
| 报表方法 | 填充类 | SetCellValue | 支持 |
| 报表方法 | 填充类 | SetCellValues | 支持 |
| 报表方法 | 填充类 | SetTime | 支持 |
| 报表方法 | 填充类 | SetTimes | 支持 |
| 报表方法 | 填充类 | SetHistData | 支持 |
| 报表方法 | 填充类 | SetHistData1 | 不支持 |
| 报表方法 | 填充类 | Query | 支持 |
| 报表方法 | 填充类 | Query1 | 不支持 |
| 报表方法 | 填充类 | SetDataset1 | 支持 |
| 报表方法 | 填充类 | SetDataset2 | 支持 |
| 报表方法 | 填充类 | SetDataset3 | 支持 |
| 报表方法 | 填充类 | InsertDataset | 不支持 |
| 报表方法 | 获取类 | GetCellString | 支持 |
| 报表方法 | 获取类 | GetCellValue | 支持 |
| 报表方法 | 获取类 | GetSelectedRow | 支持 |
| 报表方法 | 设置格式 | SetRowHeight | 支持 |
| 报表方法 | 设置格式 | SetColWidth | 支持 |
| 报表方法 | 设置格式 | SetRowAlignType | 支持 |
| 报表方法 | 设置格式 | SetColAlignType | 支持 |
| 报表方法 | 设置格式 | SetCellAlignType | 支持 |
| 报表方法 | 设置格式 | SetCellBackColor | 支持 |
| 报表方法 | 设置格式 | SetCellFont | 支持 |
| 报表方法 | 设置格式 | SetFreezePanes | 支持 |
| 报表方法 | 设置格式 | CombineCell | 支持 |
| 报表方法 | 设置格式 | SplitCell | 支持 |
| 报表方法 | 设置格式 | SplitCells | 支持 |
| 报表方法 | 设置格式 | SetCellReadOnly | 支持 |
| 报表方法 | 设置格式 | SetCellsReadOnly | 支持 |
| 报表方法 | 设置格式 | SetCellLinkEnable | 支持 |
| 报表方法 | 设置链接 | SetLinkEnable | 支持 |
| 报表方法 | 设置链接 | SetCellLink | 支持 |
| 报表方法 | 行列操作 | InsertRows | 支持 |
| 报表方法 | 行列操作 | InsertCols | 支持 |
| 报表方法 | 行列操作 | DelRow | 支持 |
| 报表方法 | 行列操作 | DelCol | 支持 |
| 报表方法 | 输入输出 | ReportLoad | 支持 |
| 报表方法 | 输入输出 | ReportLoadFromExcel | 支持 |
| 报表方法 | 输入输出 | ReportSaveAs | 支持 |
| 报表方法 | 输入输出 | ReportSaveAsExcelFile | 支持 |
| 报表方法 | 输入输出 | ReportSaveAsExcelFile2 | 支持 |
| 报表方法 | 输入输出 | ReportSaveAsExcelFile3 | 支持 |
| 报表方法 | 输入输出 | ReportSaveAsExcelFileByTemp | 支持 |
| 报表方法 | 输入输出 | Preview | 支持 |
| 报表方法 | 输入输出 | Print | 支持 |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.6.               报警事件

|  |  |  |  |
| --- | --- | --- | --- |
| 模块 | 分类 | 名称 | 支持情况 |
| 报警窗 | 模式 | 实时模式 | 支持 |
| 报警窗 | 模式 | 历史模式 | 支持 |
| 报警窗 | 模式 | 查询模式 | 支持 |
| 报警窗 | 实时报警操作 | 报警确认 | 支持 |
| 报警窗 | 实时报警操作 | 报警隐藏 | 不支持 |
| 报警窗 | 实时报警操作 | 报警删除 | 不支持 |
| 报警窗 | 事件 | 单击报警事件 | 支持 |
| 报警窗 | 事件 | 双击报警事件 | 支持 |
| 报警窗 | 事件 | 报警确认事件 | 支持 |
| 报警窗 | 开放属性 | WindowType | 支持 |
| 报警窗 | 开放属性 | ConfirmRemark | 支持 |
| 报警窗 | 开放属性 | ConfirmCertification | 支持 |
| 报警窗 | 方法 | Previe | 支持 |
| 报警窗 | 方法 | Print | 支持 |
| 报警窗 | 方法 | Query | 支持 |
| 报警窗 | 方法 | SetColumWidth | 支持 |
| 报警窗 | 方法 | SetFieldWidth | 支持 |
| 报警窗 | 方法 | SetFilter | 支持 |
| 报警窗 | 方法 | SetFilterString | 支持 |
| 报警窗 | 方法 | SetFilterStringFromFile | 支持 |
| 报警窗 | 方法 | SortBy | 支持 |
| 报警窗 | 方法 | FirstPage | 支持 |
| 报警窗 | 方法 | PreviousPage | 支持 |
| 报警窗 | 方法 | NextPage | 支持 |
| 报警窗 | 方法 | LastPage | 支持 |
| 报警窗 | 方法 | GetCurUnAckAlarmNum | 支持 |
| 事件窗 | 模式 | 实时模式 | 支持 |
| 事件窗 | 模式 | 查询模式 | 支持 |
| 事件窗 | 事件 | 单击事件 | 支持 |
| 事件窗 | 事件 | 双击事件 | 支持 |
| 事件窗 | 开放属性 | WindowType | 支持 |
| 事件窗 | 方法 | 同报警窗 | 支持 |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.7.               曲线图表

|  |  |  |  |  |
| --- | --- | --- | --- | --- |
| 模块 | 分类1 | 分类2 | 名称 | 支持情况 |
| 趋势曲线 | 模式 |  | 实时趋势曲线 | 支持 |
| 趋势曲线 | 模式 |  | 历史趋势曲线 | 支持 |
| 趋势曲线 | 开放属性 |  | ChartAreaCount | 支持 |
| 趋势曲线 | 开放属性 |  | RefreshFrequance | 支持 |
| 趋势曲线 | 开放属性 |  | RefreshMode | 支持 |
| 趋势曲线 | 开放属性 |  | TrendsMode | 支持 |
| 趋势曲线 | 开放属性 |  | Visible | 支持 |
| 趋势曲线 | 开放属性 | 标题区 | Title1.Text | 支持 |
| 趋势曲线 | 开放属性 | 标题区 | Title1.Visible | 支持 |
| 趋势曲线 | 开放属性 | 图例区 | Legend1.Visible | 支持 |
| 趋势曲线 | 事件 | 游标事件 | OnFocusOnCursor | 不支持 |
| 趋势曲线 | 事件 | 游标事件 | OnCursorMoving | 不支持 |
| 趋势曲线 | 事件 | 游标事件 | OnReleaseCursor | 不支持 |
| 趋势曲线 | 事件 | 曲线更换事件 | OnChangeCurve | 不支持 |
| 趋势曲线 | 事件 | 时间轴变化事件 | OnChangeStartTime | 不支持 |
| 趋势曲线 | 事件 | 时间轴变化事件 | OnChangeTimeScope | 不支持 |
| 趋势曲线 | 事件 | 数据轴变化事件 | OnChangeYmaxValue | 不支持 |
| 趋势曲线 | 事件 | 数据轴变化事件 | OnChangeYminValue | 不支持 |
| 趋势曲线 | 事件 | 数据轴变化事件 | OnChangeYDisplay | 不支持 |
| 趋势曲线 | 事件 | 模式切换事件 | OnRealToHist | 支持 |
| 趋势曲线 | 事件 | 模式切换事件 | OnHistToReal | 支持 |
| 趋势曲线 | 方法 | 增删变更 | AddArea | 支持 |
| 趋势曲线 | 方法 | 增删变更 | DeleteArea | 不支持 |
| 趋势曲线 | 方法 | 增删变更 | AddCurve1 | 支持 |
| 趋势曲线 | 方法 | 增删变更 | AddCurve2 | 支持 |
| 趋势曲线 | 方法 | 增删变更 | ChangeCurveDataSource | 支持 |
| 趋势曲线 | 方法 | 增删变更 | ChangeCurveDataSet | 不支持 |
| 趋势曲线 | 方法 | 增删变更 | DeleteCurve | 支持 |
| 趋势曲线 | 方法 | 增删变更 | ClearCurrentCurves | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetTrendsMode | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetRealModeRefreshParam | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetCurveColorStyle | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetCurveVisible | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetStartTimeAxis | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetEndTimeAsCurrent | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetStringStartTimeAxis | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetStringEndTimeAxis | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetTimeAxisParam1 | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetTimeAxisParam2 | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetTimeAxisDialog | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetDataAxisType | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetDataAxisRange | 支持 |
| 趋势曲线 | 方法 | 设置类 | SetDataAxisDecimalNum | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetAreaBackground | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetMajorGridColums | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetMajorGridRows | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetCurveProperty | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetCurveLineStyle | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetCurveLineStyle1 | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetLegendAreaVisible | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetLegendAreaProperty | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetLegendItemCustomText | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetCursorShowMemo | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetTimeAxisScaleMarkCount | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetTimeAxisTextAngle | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SetMouseKeyMode | 不支持 |
| 趋势曲线 | 方法 | 设置类 | SaveTrendChartParam | 不支持 |
| 趋势曲线 | 方法 | 设置类 | RestoreTrendChartParam | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetCurveLeftValue | 支持 |
| 趋势曲线 | 方法 | 获取类 | GetCurveRightValue | 支持 |
| 趋势曲线 | 方法 | 获取类 | GetCurveStatisticInTimeAxis | 支持 |
| 趋势曲线 | 方法 | 获取类 | GetCurveValueAtCursor | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetCurveStatisticInCursors | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetTimeAxisParam1 | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetTimeAxisParam2 | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetDataSourceByCurveName | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetLegendItemDefaultTextByCurveName | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetLegendItemCustomTextByCurveName | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetCurveNameByDataSource | 不支持 |
| 趋势曲线 | 方法 | 获取类 | GetTimeAtCursor | 不支持 |
| 趋势曲线 | 方法 | 滚动类 | TimeAxisScrollPast | 支持 |
| 趋势曲线 | 方法 | 滚动类 | TimeAxisScrollLater | 支持 |
| 趋势曲线 | 方法 | 滚动类 | DataAxisScrollDecrease | 支持 |
| 趋势曲线 | 方法 | 滚动类 | DataAxisScrollIncrease | 支持 |
| 趋势曲线 | 方法 | 缩放类 | ZoomOutTimeAxis | 不支持 |
| 趋势曲线 | 方法 | 缩放类 | ZoomInTimeAxis | 不支持 |
| 趋势曲线 | 方法 | 缩放类 | ZoomOutDataAxis | 不支持 |
| 趋势曲线 | 方法 | 缩放类 | ZoomInDataAxis | 不支持 |
| 趋势曲线 | 方法 | 缩放类 | AreaZoom | 不支持 |
| 趋势曲线 | 方法 | 缩放类 | AreaZoomReturn | 不支持 |
| 趋势曲线 | 方法 | 输出类 | PrintPreview | 支持 |
| 趋势曲线 | 方法 | 输出类 | PrintChart | 支持 |
| 趋势曲线 | 方法 | 新合并功能 | AddCurve3 | 不支持 |
| 趋势曲线 | 方法 | 新合并功能 | DeleteCurves | 支持 |
| 趋势曲线 | 方法 | 新合并功能 | SetCurveWidth | 支持 |
| 趋势曲线 | 方法 | 新合并功能 | SetCurveColorStylePre | 不支持 |
| XY曲线 | 事件 | 游标移动事件 | OnFocusOnCursor | 不支持 |
| XY曲线 | 事件 | 游标移动事件 | OnCursorMoving | 不支持 |
| XY曲线 | 事件 | 游标移动事件 | OnReleaseCursor | 不支持 |
| XY曲线 | 事件 | X轴移动事件 | OnXScrollLeft | 不支持 |
| XY曲线 | 事件 | X轴移动事件 | OnXScrollRight | 不支持 |
| XY曲线 | 事件 | Y轴移动事件 | OnYScrollDown | 不支持 |
| XY曲线 | 事件 | Y轴移动事件 | OnYScrollUp | 不支持 |
| XY曲线 | 开放属性 |  | CurveCount | 支持 |
| XY曲线 | 开放属性 |  | Visible | 支持 |
| XY曲线 | 开放属性 |  | Title1.Text | 支持 |
| XY曲线 | 开放属性 |  | Title1.Visible | 支持 |
| XY曲线 | 开放属性 |  | Curve1.Visible | 支持 |
| XY曲线 | 开放属性 |  | Legend1.Visible | 支持 |
| XY曲线 | 方法 |  | AddNewPoint | 支持 |
| XY曲线 | 方法 |  | AddNewPoint2 | 支持 |
| XY曲线 | 方法 |  | Clear | 支持 |
| XY曲线 | 方法 |  | ClearAll | 支持 |
| XY曲线 | 方法 |  | GetValueAt | 支持 |
| XY曲线 | 方法 |  | GetValueX | 支持 |
| XY曲线 | 方法 |  | GetValueY | 支持 |
| XY曲线 | 方法 |  | LoadFromFile | 不支持 |
| XY曲线 | 方法 |  | SaveToFile | 不支持 |
| XY曲线 | 方法 |  | PrintPreview | 支持 |
| XY曲线 | 方法 |  | Print | 支持 |
| XY曲线 | 方法 |  | SetIndexYAxisRange | 支持 |
| XY曲线 | 方法 |  | SetXAxisRange | 支持 |
| XY曲线 | 方法 |  | AddNewDataSet | 支持 |
| XY曲线 | 方法 |  | GetDataCount | 不支持 |
| XY曲线 | 方法 |  | ScrollLeft | 不支持 |
| XY曲线 | 方法 |  | ScrollRight | 不支持 |
| XY曲线 | 方法 |  | ScrollUp | 不支持 |
| XY曲线 | 方法 |  | ScrollDown | 不支持 |
| XY曲线 | 方法 |  | SetCurvesColorStyle | 不支持 |
| XY曲线 | 方法 |  | SetCurvesMapToYAxisIndex | 不支持 |
| XY曲线 | 方法 |  | SetXAxisShowTimeFormat | 不支持 |
| XY曲线 | 方法 |  | ZoomIn | 不支持 |
| XY曲线 | 方法 |  | ZoomOut | 不支持 |
| XY曲线 | 方法 |  | ZoomPart | 不支持 |
| XY曲线 | 方法 |  | ZoomResume | 不支持 |
| 柱状图 | 开放属性 |  | SeriesItemCount | 支持 |
| 柱状图 | 开放属性 |  | SeriesCount | 支持 |
| 柱状图 | 开放属性 |  | AutofixYAxis | 支持 |
| 柱状图 | 开放属性 |  | Visible | 支持 |
| 柱状图 | 方法 |  | AddSeries | 支持 |
| 柱状图 | 方法 |  | DeleteSeries | 支持 |
| 柱状图 | 方法 |  | SetDataValueForCylinder | 支持 |
| 柱状图 | 方法 |  | AddDataSet | 支持 |
| 柱状图 | 方法 |  | SetSeriesColorStyle | 支持 |
| 柱状图 | 方法 |  | SetMaxValue | 支持 |
| 柱状图 | 方法 |  | GetMaxValue | 支持 |
| 柱状图 | 方法 |  | SetMinValue | 支持 |
| 柱状图 | 方法 |  | GetMinValue | 支持 |
| 柱状图 | 方法 |  | SetCylinderVisible | 支持 |
| 柱状图 | 方法 |  | PrintChart | 不支持 |
| 柱状图 | 方法 |  | PrintView | 不支持 |
| 柱状图 | 方法 |  | SetLabelItemText | 支持 |
| 柱状图 | 方法 |  | SetAllDrawType | 不支持 |
| 柱状图 | 方法 |  | SetSeriesDrawType | 不支持 |
| 柱状图 | 方法 |  | SetLegendItemCustomText | 不支持 |
| 饼图 | 开放属性 |  | PieItemCount | 支持 |
| 饼图 | 开放属性 |  | Visible | 支持 |
| 饼图 | 方法 |  | SetItemColor | 支持 |
| 饼图 | 方法 |  | SetDataValueForPie | 支持 |
| 饼图 | 方法 |  | ChangePieToCylinder | 不支持 |
| 饼图 | 方法 |  | ChangeCylinderToPie | 不支持 |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.8.               变量域

|  |  |  |
| --- | --- | --- |
| 模拟类型变量支持的域 | 离散类型变量支持的域 | 字符串类型变量支持的域 |
| Name | Name | Name |
| Comment | Comment | Comment |
| Quality | Quality | Quality |
| QualityString | QualityString | QualityString |
| TimeYear | TimeYear | TimeYear |
| TimeMonth | TimeMonth | TimeMonth |
| TimeDay | TimeDay | TimeDay |
| TimeHour | TimeHour | TimeHour |
| TimeMinute | TimeMinute | TimeMinute |
| TimeSecond | TimeSecond | TimeSecond |
| TimeMsec | TimeMsec | TimeMsec |
| TimeZone | TimeZone | TimeZone |
| TimeDateString | TimeDateString | TimeDateString |
| TimeTimeString | TimeTimeString | TimeTimeString |
| LogEnable | LogEnable | LogEnable |
| EventEnable | EventEnable | EventEnable |
| ExtendFieldString1 | ExtendFieldString1 | ExtendFieldString1 |
| ExtendFieldString2 | ExtendFieldString2 | ExtendFieldString2 |
| ExtendFieldString3 | ExtendFieldString3 | ExtendFieldString3 |
| ExtendFieldString4 | ExtendFieldString4 | ExtendFieldString4 |
| ExtendFieldString5 | ExtendFieldString5 | ExtendFieldString5 |
| ExtendFieldString6 | ExtendFieldString6 | ExtendFieldString6 |
| ExtendFieldString7 | ExtendFieldString7 | ExtendFieldString7 |
| ExtendFieldString8 | ExtendFieldString8 | ExtendFieldString8 |
| Value | Value | Value |
| IOEnable | IOEnable | IOEnable |
| IORead | IORead | IORead |
| IOWrite | IOWrite | IOWrite |
| Ack | Ack |  |
| UnAck | UnAck |  |
| Alarm | Alarm |  |
| Group | Group |  |
| AlarmEnable | AlarmEnable |  |
| MaxEU | DiscAlarm |  |
| MinEU | DiscAck |  |
| MaxRAW | DiscUnAck |  |
| MinRAW | DiscEnable |  |
| EngUnits | DiscOffString |  |
| AlmUnackCount | DiscOnString |  |
| AlmTotalCount | DiscAlarmDelay |  |
| AlmLimitUnackCount | DiscPriority |  |
| AlmLimitCount | DiscAlarmGenType |  |
| AlmDevUnackCount | DiscAlarmInhibited |  |
| AlmDevCount | AlmDiscCount |  |
| AlmRocUnackCount | AlmDiscUnackCount |  |
| AlmRocCount | DiscOnToOffString |  |
| LimitAlarm | DiscOffToOnString |  |
| LimitAck |  |  |
| LimitUnAck |  |  |
| LimitAlarmDead |  |  |
| LimitAlarmDelay |  |  |
| HiHiLimit |  |  |
| HiHiStatus |  |  |
| HiHiEnable |  |  |
| HiHiString |  |  |
| HiHiPriority |  |  |
| HiLimit |  |  |
| HiStatus |  |  |
| HiEnable |  |  |
| HiString |  |  |
| HiPriority |  |  |
| LoLimit |  |  |
| LoStatus |  |  |
| LoEnable |  |  |
| LoString |  |  |
| LoPriority |  |  |
| LoLoLimit |  |  |
| LoLoStatus |  |  |
| LoLoEnable |  |  |
| LoLoString |  |  |
| LoLoPriority |  |  |
| RocAlarm |  |  |
| RocAck |  |  |
| RocUnAck |  |  |
| RocEnable |  |  |
| RocPct |  |  |
| RocString |  |  |
| RocAlarmDelay |  |  |
| RocPriority |  |  |
| DevAlarm |  |  |
| DevAck |  |  |
| DevUnAck |  |  |
| DevAlarmDead |  |  |
| DevTarget |  |  |
| DevAlarmDelay |  |  |
| MajorDevPct |  |  |
| MajordevStatus |  |  |
| MajorEnable |  |  |
| MajorString |  |  |
| MajorDevPriority |  |  |
| MinorDevPct |  |  |
| MinorDevStatus |  |  |
| MinorEnable |  |  |
| MinorString |  |  |
| MinorDevPriority |  |  |
| StatusAlarm |  |  |
| StatusAlarmAck |  |  |
| StatusAlarmUnAck |  |  |
| StatusAlarmEnable |  |  |
| StatusAlarmString |  |  |
| StatusAlarmPriority |  |  |
| HiHiAlarmInhibited |  |  |
| HiAlarmInhibited |  |  |
| LoAlarmInhibited |  |  |
| LoLoAlarmInhibited |  |  |
| RocAlarmInhibited |  |  |
| MaDevAlarmInhibited |  |  |
| MiDevAlarmInhibited |  |  |
| StateAlarmInhibited |  |  |
| StateEnumTable |  |  |

---

第三十章 
KingSCADA-Portal瘦客户端系统

### 30.10.9.               其他

|  |  |  |  |
| --- | --- | --- | --- |
| 模块 | 分类 | 名称 | 支持情况 |
| 浏览模式 | 手机端 | APP-Android | 支持 |
| 浏览模式 | 手机端 | APP-IOS | 不支持 |
| 浏览模式 | 手机端 | 手机浏览器（Android） | 支持 |
| 浏览模式 | 手机端 | 手机浏览器（IOS） | 支持 |
| 浏览模式 | PC端 | Google | 支持 |
| 浏览模式 | PC端 | IE | 不支持 |
| 数据源 | KS数据源 | 实时数据 | 支持 |
| 数据源 | KS数据源 | 历史数据 | 支持 |
| 数据源 | KS数据源 | 报警事件 | 支持 |
| 数据源 | 外部数据源 | ODBC(关系库、KH等) | 支持 |
| 数据源 | 外部数据源 | OLEDB | 不支持 |
| 附属功能 | 控件 | OCX | 不支持 |
| 附属功能 | 3D | 3D控件 | 不支持 |
| 附属功能 | GIS | GIS控件 | 不支持 |
| 附属功能 | 监控视频 | OCX视频插件 | 不支持 |
| 附属功能 | 监控视频 | KingVideo | 支持 |
| 附属功能 | 图表 | echarts | 支持 |
| 附属功能 | 第三方融合 | 单页面访问 | 支持 |
| 性能 | 冗余 | KS数据源冗余 | 支持 |
| 性能 | 负载均衡 | 负载均衡 | 不支持 |
| 变量 | 服务端变量 | 服务端内存变量 | 支持 |
| 变量 | 服务端变量 | 服务端IO变量 | 支持 |
| 变量 | 服务端变量 | 数据模型变量 | 支持 |
| 变量 | 客户端变量 | 画面变量 | 支持 |
| 变量 | 客户端变量 | 全局变量 | 支持 |
| 变量 | 客户端变量 | 数据模型变量 | 支持 |
| 变量 | 客户端变量 | 引用变量 | 支持 |
| 变量 | 客户端变量 | 引用数据模型 | 支持 |
| 安全 | 权限校验 | 安全区和角色 | 支持 |
| 安全 | 授权 | BS授权 | 支持 |