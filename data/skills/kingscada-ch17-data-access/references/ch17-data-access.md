第十七章 其他程序访问SCADA数据的方式

# 第十七章 其他程序访问SCADA数据的方式

---

第十七章 其他程序访问SCADA数据的方式

## 17.1 概述

其他程序能够通过多种方式访问KingSCADA的数据充分体现了该软件的开放性，一个软件的开放性是指该软件能与多种通讯协议互联，是衡量一个软件好坏的重要指标。

KingSCADA支持动态数据交换（DDE），能够方便地和其他支持动态数据交换的应用程序交换数据。通过DDE接口，KingSCADA可以与EXCEL、VB等服务程序进行动态数据交换。

KingSCADA还提供了标准的OPC接口，可以方便地与其他具有OPC标准的工业应用程序或外部控制设备进行数据交换。

除此之外，系统还提供了100多个API接口，可以实现与KingSCADA系统的数据交互。

---

第十七章 其他程序访问SCADA数据的方式

## 17.2 KingSCADA的DDEServer

DDE是WINDOWS平台上的一个完整的通信协议，它使支持动态数据交换的两个或多个应用程序能彼此交换数据和发送指令，KingSCADA提供了DDE Server的功能，任何一个DDE Client都可以访问KingSCADA的数据，数据交换是通过三个标识名来实现的：

应用程序名（Application）：KSddeserver

主题（Topic）：KStopic

项目（Item）：变量名称

下面以EXCEL应用程序访问KingSCADA数据为例介绍数据交换的过程。

**第一步：**在工程设计器的树形目录中选中建点－数据词典，单击“新建”图标按钮，建立变量，如图所示：

图17- 1 在数据词典中建立变量

***注：“允许其他应用访问”复选项必须选中。***

**第二步：**运行KingSCADA程序

**第三步：**打开系统开始菜单，在程序“选项”中找到KingSCADA3.7目录启动KingSCADADDE Server，启动方法如下：

图17- 2 启动DDE服务

启动界面如下图所示：

图17- 3 DDE服务界面

在图17-3中单击“开始”按钮，启动DDE数据交换的功能。

**第四步：**新建一个EXCEL文件，在相应的单元格中（如第一行、第一列）输入如下代码：

=KSDdeserver|KSTopic!tag1，其中“KSDdeserver|KSTopic!”项是固定形式，如图所示：

图17- 4 在单元格中输入代码

输入完毕后回车，KingSCADA中的tag1变量的实时值会显示在单元格中。

---

第十七章 其他程序访问SCADA数据的方式

## 17.3 KingSCADA的OPCDAServer

KingSCADA提供了OPCServer的功能，任何一个OPCClient都可以访问KingSCADA OPCServer。访问方式有两种：

**方式一（推荐）：**KingOPCServer与**OPC****客户端**部署在同一台机器上，通过OPCServerTool配置的KingSCADA数据源向OPC客户端发布数据。这样，无需配置复杂的DCOM即可访问网内任意一台机器上的KingSCADA数据，而且可使用一个OPCServer同时采集多个KingSCADA的数据。

**方式二：**即传统方式，KingOPCServer与**KingSCADA**部署在同一台机器上，通过DCOM向远程OPC客户端发布数据。

---

第十七章 其他程序访问SCADA数据的方式

### 17.3.1    方式一

方式一要求KingSCADA
OPCServerTool程序运行在OPC客户端所在的机器上，其作用是：该程序通过内部接口访问远程的KingSCADA数据源，这样OPC客户端通过访问本地的KingSCADA
OPCServerTool即可获取远程的KingSCADA数据，达到避免DCOM配置的目的，网络拓扑图如下所示：

图17- 5 KingSCADA OPCServer网络拓扑图

所以要使用KingSCADA OPCServer功能必须在客户端安装如下图矩形框里所示的程序。

图17- 6 安装KingSCADA OPC服务器

该程序安装完毕后在系统“开始”—“程序”中自动创建KingOPCServerTool快捷方式。如图所示：

图17- 7 King OPCServerTool快捷方式

该程序是KingSCADA OPCServer的配置工具，用来连接一台或多台远程KingSCADA数据源，从而实现了OPC客户端通过OPC方式访问KingSCADA数据的功能。下面具体介绍该工具的使用过程。

---

第十七章 其他程序访问SCADA数据的方式

#### 17.3.1.1   KingSCADA OPCServer配置工具

在图17-7中单击“KingOPCServerTool”选项弹出对话框，如图所示：

图17- 8
 KingOPCServerTool对话框

在该对话框中连接远程KingSCADA数据源，单击“点击此处增加数据源”按钮弹出对话框，如图所示：

图17- 9 添加数据源对话框

在该对话框中指定远程KingSCADA数据源所在的节点的IP地址和别名，别名为该节点在OPC Client端看到的节点名字，如不指定，将用IP地址替代，其中字符“.”以“\_”替代

例如：IP =172.16.1.1 不指定别名情况下，自动生成别名172\_16\_1\_1。

别名的指定弱化了节点名称和IP地址的直接联系，有利于工程的移植，建议用户使用自定义别名。

别名长度不能超过32位，不能使用下列字符  ,:;+-\*/%&!~|^<>={}[]().`'\"\\?

别名不能使用"Local"。

单击“高级…”按钮弹出对话框如图所示：

图17- 10 高级设置对话框

在高级设置对话框中可以配置KingSCADA节点的冗余网络，该配置需要和实际连接的KingSCADA节点的冗余配置一致。

设置完毕后，单击“确定”按钮完成KingSCADA数据源的连接。

***注：如果******KingSCADA OPCServer******和******OPCClient******在同一节点上，就不需要使用******KingOPCServerTool******工具连接******KingSCADA*** ***数据源了。配置过程如下所示。***

---

第十七章 其他程序访问SCADA数据的方式

#### 17.3.1.2   配置实例

**1、** **KingSCADA OPCServer****与****OPCClient****在同一节点上的配置过程如下：**

OPC客户端作为一个独立的应用程序，可能由硬件制造商、软件开发商或其他第三方提供，因此数据项定义的方法和界面都可能有所差异。下面以某一厂家提供的OPC客户端为例讲解KingSCADA OPCServer的使用方法。

**第一步：**启动KingSCADA
运行系统以保证OPCClient端能够正确读取数据

**第二步：**运行OPCClient，弹出画面，如图所示：

图17- 11 OPC客户端界面

在图17-11中执行“OPC”菜单中的“CONNECT”命令，弹出连接服务器选项对话框，如图所示：

图17- 12 连接OPC服务器对话框

KingSCADA的OPC服务器名称是KingSCADA.OPCServer.2（KingSCADA
OPCServer），用户选择此选项并单击“确定”按钮完成客户端与服务器的连接，返回到主界面。

**第三步：**在客户端界面中执行“OPC”菜单下的“ADD ITEM”命令，弹出添加项目对话框（需要用户事先启动KingSCADA运行系统），如图所示：

图17- 13 添加数据项对话框

在该对话框右侧变量列表中列举了KingSCADA当前运行工程中建立的部分变量，即OPC服务器中的数据项，用户可选择所需的数据项，该数据项会在客户端上显示，并按照采集频率实时刷新，如图所示：

图17- 14 客户端数据显示

***注：除了系统变量外，只有设置了“******允许其他应用访问******”选项的工程变量才会在图******17-13******中显示，如图所示：***

图17- 15 设置变量的允许其他应用访问属性

在图17-14中执行“OPC”菜单中的“Write Value to Item”命令，可以对可读写变量或域进行修改。

**2、** **KingSCADA OPCServer****与****OPCClient****不在同一节点上的配置过程如下：**

**第一步：**首先保证在OPC
Client所在计算机安装KingSCADA OPC服务器程序，如图17-6。

**第二步：**启动KingSCADA
运行系统以保证OPC Client能够正确读取数据。

**第三步：**打开KingOPCServerTool程序，连接远程KingSCADA数据源，设置如下图所示：（以连接一台KingSCADA 数据源为例）

图17- 16 连接远程KingSCADA数据源

单击“确定”按钮，该数据源被添加到列表中，如果连接多个远程KingSCADA数据源的话，依次添加即可。

**第四步：**运行OPCClient程序，弹出对话框，如图所示：

图17- 17
OPC客户端界面

在图17-17中执行“OPC”菜单中的“CONNECT”命令，弹出连接服务器选项对话框，如图所示：

图17- 18 连接OPC服务器对话框

KingSCADA的OPC服务器名称是 KingSCADA.OPCServer.2（KingSCADA.
OPCServer），用户选择此选项并点击“确定”按钮完成客户端与服务器的连接，返回到主界面。

**第五步：**在客户端界面中执行“OPC”菜单下的“ADD ITEM”命令，弹出添加数据项对话框，如图所示：

图17- 19 添加数据项对话框

其中“DataSource1”是远程KingSCADA数据源所在的节点别名。右侧变量列表中列举了该数据源中的部分变量，用户可选择所需的数据项，该数据项会在客户端上显示，并按照采集频率实时刷新，如图所示：

图17- 20 客户端数据显示

在图17-20中执行“OPC”菜单中的“Write Value to Item”命令，可以对可读写变量或域进行修改。

---

第十七章 其他程序访问SCADA数据的方式

### 17.3.2    方式二

方式二要求KingSCADA OPCServer与KingSCADA部署在同一台机器上并在该机器上配置DCOM，DCOM配置方法和步骤如下：

基于Windows XP/Windows
Vista/Windows 7操作系统DCOM配置

**第一步：**配置防火墙

WINDOWS防火墙是基于“例外”的，也就是默认情况下，防火墙将阻止外部“未被请求”的连接通过网络，而管理员可以在规则之外设置特定的应用程序或端口来响应外部“未被请求”的连接。

防火墙的例外可被归入两种层次的情况，一是应用程序层次，二是端口与协议层次。前者可设置特定的程序来对“未被请求”的连接进行响应，后者可设置特定的TCP或UDP端口来允许相应的通信。为了使OPC程序可以通过DCOM正常工作，必须在这两个层次上都进行设置。

防火墙的配置过程如下：

1）为了给系统提供必须的保护，WINDOWS防火墙是默认启用的如图17-21所示。（个人）不推荐关闭WINDOWS防火墙，若通信连接失败，在调试过程中可以暂时关闭防火墙以确定问题是否是由防火墙所引起。如若确定永久关闭防火墙，下面所述关于防火墙的设置均可忽略。

图17- 21
WINDOWS防火墙

2）进入WINDOWS控制面板，双击“WINDOWS防火墙”图标，打开“WINDOWS防火墙”设置对话框，选中“例外”选项卡，把相应OPC
Client和Server程序添加到例外列表。同时添加Microsoft
Management Console (mmc.exe 在Windows\System32目录下)和OPC 应用程序OPCEnum (opcEnum.exe 在Windows\System32 目录下)到例外列表中，如图17-22所示。最后确保“文件和打印机共享”也被选中在例外列表中。

***注：只有EXE******程序可以被添加到例外列表中，对于DLL******和OCX******等类型的OPC Server*** ***和OPC Client*** ***，必须添加调用它们的EXE******程序；本步设置可能要用到“添加程序”和“浏览”按钮。***

图17- 22 添加例外列表

3）添加TCP
135端口。建立DCOM通信和对外来请求进行响应需要用到TCP 135端口。在“WINDOWS防火墙”的“例外”选项卡中，点击“添加端口”按钮。在“添加端口”对话框中进行设置，如图所示：

图17- 23 添加端口

**第二步：**DCOM配置

在Windows XP/Windows
Vista/Windows 7中若要通过网络使用OPC，应该注意到以下两个方面的问题：一是用户可以通过“激活和访问权限”对话框对使用DCOM的应用程序的“限制权限”进行配置；二是在“激活和访问权限”中定义的每个用户，其本地和远程访问权限可以进行分别配置。

关于“激活和访问权限”，启动权限定义了谁可以本地或远程激活（或启动）基于COM的应用程序（比如OPC Server程序）；访问权限定义了谁可以对已经启动起来的程序进行访问。

默认情况下，Windows XP/Windows
Vista/Windows 7不允许经由网络的OPC通信，为了使基于DCOM的OPC应用程序可以通过网络工作，用户应该被给予OPC Server和OPC Client的远程激活和访问权限。

Windows XP/Windows Vista/Windows 7下DCOM的配置过程如下：

1）点击“开始”-->“运行”，输入dcomcnfg命令，如图所示：

图17- 24 启动运行窗口

2）单击“确定”按钮后打开“组件服务”对话框，双击“控制台根目录”下的“组件服务”展开“组件服务”文件夹，同样方式，展开“计算机”文件夹，右键点击右侧窗口的“我的电脑”图标，在弹出的快捷菜单中选择“属性”选项，打开属性对话框。

选中“默认属性”选项卡，勾选“在此计算机上启用分布式COM”，“默认身份验证级别”选“无”，如图所示：

图17- 25 默认属性选项卡设置

3）选中“COM
安全”选项卡，注意这里有4个按钮可供点击进入配置，如图所示：

图17- 26  COM安全选项卡

4）配置访问和启动激活权限。

**“访问权限”-->****“编辑限制(L)…****”**

设置ANONYMOUS LOGON的本地访问及远程访问权限为允许。（此设置与OPCEnum.exe发挥作用有关，对于某些设置了“验证等级”为“无”以允许匿名连接的OPC
Servert和OPC Client，此设置也是必须的。）

设置Everyone的的本地访问及远程访问权限为允许，如图所示：

图17- 27 访问权限设置

**“启动和激活权限”-->****“编辑限制(I)…****”**

设置ANONYMOUS LOGON的本地启动、本地激活、远程启动和远程激活权限为允许。

设置Everyone的本地启动、本地激活、远程启动和远程激活权限为允许。（注：为了安全，可建立专用于OPC通信的用户组，进行权限赋予。）如图所示：

图17- 28 启动权限设置

设置默认访问和启动激活权限。对于每个参与OPC通信的用户或组，均应赋予其本地和远程访问以及启动激活权限。若相应用户或组没有出现，则应手动添加，然后赋予权限。

**“访问权限”-->“****编辑默认值(E)…****”**

添加并设置ANONYMOUS LOGON的本地访问及远程访问权限为允许。

添加并设置Everyone的本地访问及远程访问权限为允许，如图所示：

图17- 29 访问权限设置

**“启动和激活权限”-->“****编辑默认值(D)…****”。**

添加并设置ANONYMOUS LOGON的本地启动、本地激活、远程启动和远程激活权限为允许。

添加并设置Everyone的本地启动、本地激活、远程启动和远程激活权限为允许，如图所示：

图17- 30 启动权限设置

5）配置OPCEnum与KingSCADA OPCServer

双击“控制台根目录”下的“组件服务”展开“组件服务”文件夹，同样方式，展开“计算机”－“我的电脑”－“DCOM配置”－“OPCEnum”，单击鼠标右键选择“属性”选项，打开OPC属性对话框。

选择“常规”标签页，“身份验证级别”选“默认”，如图所示：

图17- 31 常规选项卡设置

选择“位置”标签页，勾选“在此计算机上运行应用程序”，如图所示：

图17- 32 位置选项卡设置

选择“安全”标签页，设置“启动和激活权限”和“访问权限”为“使用默认值”如图所示：

图17- 33
安全选项卡设置

“配置权限”选择“自定义”，点击“编辑”，添加并设置ANONYMOUS LOGON和Everyone的完全控制和读取为允许，如图所示：

图17- 34 更改配置权限设置

KingSCADA OPCServer设置与OPCEnum相似，区别在于KingSCADA OPCServer需要配置“交互式用户”。

选择KingSCADA OPCServer属性的“标识”标签页，设置为“交互式用户”，如图所示：

图17- 35  KingSCADA OPCServer属性设置

6）完成以上设置后，重新启动计算机。

以上配置是在OPCServer端进行的，在OPC客户端需要安装OPC Core Components 2.00
SDK 2.20.msi程序（版本号会有区别），安装完该程序后再按17.3.1.2配置实例1中的说明进行配置即可。

---

第十七章 其他程序访问SCADA数据的方式

## 17.4     KingSCADA的OPCUAServer

UA 服务功能：主要支持OPC UA 的基本功能（数据发布订阅、读写等）、支持KingSCADA(以下简称KS)数据源、支持枚举KS数据源变量分组以及数据模型变量，支持运维接口、保证安全性要求和日志输出。OPC UA作为一个独立服务，可以与SCADA服务端部署在一起，也可以分布式部署，支持SCADA冗余架构。可以在KS安装路径下使用，也可以放在一个路径下独立使用。

配置工具可以配置OPC UA服务连接的数据源，允许的最大连接数，启停控制；通过RESTful监视接口可以浏览到OPC UA Server内部数据，UA客户端信息，SCADA 连接状态等。OPC UA本体服务，支持客户端加密链接，支持读写订阅操作。支持RESTful接口功能启停配置，支持RESTful密码登录。

17-36 UA功能概貌

首先，需要如下图所示的OPC服务：在使用安装包安装OPC服务时，同时安装OPCUA以及OPCDA服务，安装在同一路径下，可以独立运行，支持分布式部署，可以与KingSCADA部署在同一台机器，也可以部署在不同的机器上。

该程序安装完毕后在系统“开始”—“程序”中自动创建OPCUAServerTool快捷方式。如图所示，该程序是KingSCADA OPCUAServer的配置工具，用来创建OPCUA服务。

---

第十七章 其他程序访问SCADA数据的方式

### 17.4.1    UAServer服务添加

配置方面，首先需要以管理员身份启动KingOPCUAServerTool.exe，否则会提示拒绝访问而无法正常使用，打开KingOPCUAServerTool.exe配置工具后，如图17-37所示

17-37 工具配置界面

配置工具的主要用途是创建一个OPCUA服务，与KS数据源进行连接，打开可以看到三个区域，分别是UA 服务端点列表区，功能操作区、日志输出列表。

图中1区域是UA 服务端点列表区，展示内容为工具历史所有添加的端点。点击右侧刷新按钮，可以重新刷新列表，获取最新的端点列表。

图中2区域是日志输出界面。日志列表分三列，日志时间，数据源和日志事件。主要记录UA服务状态，包括启动停止服务，与KS数据源的连接状态，以及是否正常监听，默认记录1000条记录，超出后，最新的日志将覆盖第一条记录。

图中3区域是功能菜单，启动、停止按钮控制某一个端点服务启停，编辑、添加、删除按钮对某一个端点UA服务配置操作，具体见下面操作流程。其中，启动、停止、编辑、删除需要选中端点列表中一个端点后方可操作，否则置灰不可操作。

---

第十七章 其他程序访问SCADA数据的方式

#### 17.4.1.1   数据源配置

UA 服务端点，指创建 UA 客户端可与之通信的 UA 接口。UA
服务器端点定义为通用资源定位器
(URL)，用于标识服务器的特定实例、传输类型以及通信安全性。服务器端点由一个 URL 和一个安全策略类型组成。

UA Server服务可以多实例运行，每个实例服务对应一个KS数据源，**注：当配置****KS****数据源为冗余状态时，UA Server****服务只连接激活状态下的KS****，连接备份状态下的KS****会失败。**

点击工具右侧添加按钮，弹出KS数据源配置界面，如图17-38所示

图17-38
KS数据源配置界面

实例名称：运行服务实例名称，会生成对应实例配置文件等。不允许特殊字符（? “ ”/ \ < > \* | :），长度不超过16个字符，不区分大小写，每次新建会自动生成名字OPCUA01，后两位数字累加递增，可以手动更新。不允许重复，点击应用若检测到重复，自动在后面补增\_1,如OPCUA01\_1。

主站点：IP 、端口默认本机IP（127.0.0.1） 、端口（默认8800，默认不能修改），多网卡场景，各网卡均可调用。

冗余站点功能默认不启用，启用后，可以填入冗余KS数据源的IP 可以手动设置修改，端口与主站点一致，不可修改。**注：端点****KS****数据源端口修改后，需要从KS****安装目录下bin****文件夹，获取kcCongfig.ini****配置文件，放置在KingOPCUAServerTool.exe****同级目录下。**

点击测试按钮，进行数据源链接测试，当KS运行时，测试连接成功，当KS未运行时，测试连接失败，如下图所示，

 

图17-39 测试连接成功与失败

---

第十七章 其他程序访问SCADA数据的方式

#### 17.4.1.2   UAServer配置

点击测试连接成功后，切换端点配置选项卡进行UA Server设置。若没有配置端点配置选项卡，默认使用本地IP
127.0.0.1 默认端口14840，安全策略，无，进行配置。

允许匿名：选择是否允许登录匿名。默认选项是，允许客户端匿名登录UA
Server，客户端无论是否使用账号密码，都允许客户端登录，反之则客户端需要输入账号密码验证，账号密码同**连接数据源****KS****用户密码**。

端口号：整数，取值范围是:0-65535，默认14840，当输入的内容不符合要求，给出提示信息。此对话框定义的所有端点
URL 的格式均为
opc.tcp://<hostname>:<port>。

**注意：默认IP****使用127.0.0.1****，多网卡场景，允许所有网卡调用服务。**

最大连接：整数，取值范围是:0-128，默认8。指定所支持的最大连接数。 单个UA服务建议连接数不要超过16个，可以启动多个服务连接KS数据源。

会话超时：整数，单位：秒，默认60，指定 UA 客户端建立会话的最大超时限制,

安全策略：这些安全策略和消息模式参数指定 UA 服务器支持的安全算法。可以选择一个或多个，加密策略方式不要与None同用，默认情况下选择 Basic256Sha256。选项如下：

    Basic256Sha256

    Basic256 (Deprecated)

    Basic128Rsa15 (Deprecated)

    None (不安全)

**注意：安全策略 Basic128Rsa15** **和 Basic256** **从 OPC UA** **规范版本 1.04** **开始已被 OPC Foundation** **弃用。勾选None****以外的加密策略时，需要使用证书生成工具生成UA****证书放入对应服务的文件路径下，否则会停止OPCUA****服务监听**。具体配置见下文。

证书信任控制区域：可以导入UA客户端的证书，并通过信任和拒绝的方式来限制UA客户端的连接，详细配置请见17.4.5

---

第十七章 其他程序访问SCADA数据的方式

#### 17.4.1.3   运维控制

支持RESTful API接口对UA 服务进行运维（内部接口）通过RESTful监视接口可以浏览到OPC UA Server内部数据，UA客户端信息，SCADA 连接状态。运维设置界面如图17-40

图17-40 运维设置界面

RESTful启用：该OPC UA服务是否启用RESTful接口运维

端口号：整数，取值范围是:0-65535，默认14850，当输入的内容不符合要求，给出提示信息，如图17-41所示。

 

图17-41 端口输入错误提示

总共提供了五个运维接口，分别是获取UA服务状态接口UAConfiguration，获取KS数据源状态接口dataSourceStatus，获取UA 客户端连接状态接口UAClientsStatus，获取UA 服务端KS数据源所有变量接口dataSourceAllRTData以及4.1.3.5       获取UA 服务端KS数据源指定变量值接口dataSourceRTData，详细使用规则如下：

**1.****获取UA** **服务状态接口**

请求方式：GET（HTTP）

content\_type：application/json

请求地址：   

http://<IP>+<Port> + /api/v1/UAConfiguration

默认端口：14850

请求地址举例：

http://192.168.1.10:4567/api/v1/ UAConfiguration

权限说明（请求头参数）：

参数说明       描述

Authorization  <Token>,Token为“username:password”格式的Base64加密字符串

 

返回结果示例：

可点击key和value值进行编辑

{

    "ErrorCode":0,

    "Message":"success",

    "UAServer":{

       
"EnableAnonymous":true,

        "Port":14841,

        "TimeOut":60,

       
"MaxConnCount":8,

       
"EnableNone":true,

       
"EnableBasic256Sha256":false,

        "EnableBasic256":false,

       
"EnableBasic128Rsa15":false

    }

}

其中：

EnableAnonymous：表示允许客户端匿名访问。

Port：表示UA服务监听端口号。

TimeOut：表示客户端连接最大超时时间，如果客户端设置的超时大于此值，服务端在建立连接时强制设置为此值。

MaxConnCount：表示支持的最大客户端连接数。

EnableNone：表示允许None通信安全策略。

EnableBasic256Sha256：表示允许Basic256Sha256通信安全策略。

EnableBasic256：表示允许Basic256通信安全策略。

EnableBasic128Rsa15：表示允许Basic128Rsa15通信安全策略。

其中，EnableBasic256和EnableBasic128Rsa15为过时的安全策略。

 

**2.****获取KS****数据源状态接口**

请求方式：GET（HTTP）

content\_type：application/json

请求地址：   

http://<IP>+<Port> + /api/v1/dataSourceStatus

默认端口：14850

请求地址举例：

http://192.1868.1.10:4567/api/v1/dataSourceStatus

权限说明（请求头参数）：

参数说明       描述

Authorization  <Token>,Token为“username:password”格式的Base64加密字符串

返回结果：

{

    "ErrorCode":0,

    "Message":"success",

    "DataSource":{

       
"IsConnected":true,

        "MasterIP":"127.0.0.1",

       
"MasterPort":8800,

       
"SlaveEnable":false,

       
"SlaveIP":"",

       
"SlavePort":8800

    }

}

其中

IsConnected：表示数据源与UA服务连接状态，连接、未连接。

MasterIP：表示数据源主站点IP。

MasterPort：表示数据源主站点端口号。

SlaveEnable：表示是否配置主站点。

SlaveIP：表示数据源从站点IP。

SlavePort：表示数据源从站点端口号。

注：目前MasterPort和SlavePort从配置文件kxConfig.ini文件中读取实时库访问端口的值，如果KingOPCUAServer与SCADAView不在同一目录，需要将配置文件kxConfig.ini拷贝到KingOPCUAServer目录下。

 

**3.****获取UA** **客户端连接状态接口**

请求方式：GET（HTTP）

content\_type：application/json

       请求地址：   

http://<IP>+<Port> + /api/v1/UAClientsStatus

默认端口：14850

请求地址举例：

http://192.168.10.112:4567/api/v1/UAClientsStatus

权限说明（请求头参数）：

参数说明       描述

Authorization  <Token>,Token为“username:password”格式的Base64加密字符串

返回结果：

 

可点击key和value值进行编辑

{

    "ErrorCode":0,

    "Message":"success",

    "Number":1,

    "DataPro":[

        "ClientID",

       
"ConnectionTime",

        "Reserve"

    ],

    "Data":[

        [

           
"ns=1;g=42b04b7d-c250-afbd-e97e-b74b63c68c98",

           
"2021-08-19 17:07:31.736",

           
""

        ]

    ]

}

其中：

Number：当前客户端连接个数。

ClientID：客户端ID，可能值数字也可能是随机字符串或GUID等。

ConnectionTime：指记录的客户端最后一次连接UA服务开始时间，时间类型为字符串。

Reserve：预留字段，可不写。

 

**4.****获取UA** **服务端KS****数据源所有变量接口**

请求方式：GET（HTTP）

content\_type：application/json

       请求地址：  

默认端口：14850

http://<IP>+<Port> + /api/v1/dataSourceAllRTData

请求地址举例：

http://192.168.10.112:4567/api/v1/dataSourceAllRTData

权限说明（请求头参数）：

参数说明       描述

Authorization  <Token>,Token为“username:password”格式的Base64加密字符串

 

返回结果：

{

    "ErrorCode":0,

    "Message":"success",

    "Number":1,

    "DataPro":[

        "TagName",

        "Value",

        "DataType",

        "TimeStamp",

        "QualityStamp"

    ],

    "Data":[

        [

           
"Tag1",

           
"1",

           
"2021-08-19 09:10:24.237",

           
"192"

        ]

    ]

}

 

**5.****获取UA** **服务端KS****数据源指定变量值接口**

请求方式：POST（HTTP）

content\_type：application/json

       请求地址：

默认端口：14850  

http://<IP>+<Port> + /api/v1/dataSourceRTData

请求地址举例：

http://192.168.10.112:4567/api/v1/dataSourceRTData

权限说明（请求头参数）：

参数说明       描述

Authorization  <Token>,Token为“username:password”格式的Base64加密字符串

请求Body(JSON对象类型)参数说明：

参数说明       描述

TagNames       查询的变量合集，Array类型

 

请求Body示例：

{

    "TagNames":[

        "Tag1",

        "Tag2",

        "$Day",

        "变量13"

    ]

}

 

返回结果格式同dataSourceAllRTData返回结果。

---

第十七章 其他程序访问SCADA数据的方式

### 17.4.2    UAServer服务启停

添加UA服务端点后，UA Server服务启动和停止，按钮功能需要选中列表中一个端点，并根据服务启停状态，服务停止状态，启动按钮生效，服务启动状态，停止按钮生效。创建服务端点后会在服务中添加以创建的UA服务端点为名称的服务，启动后会在工具所在目录生成以创建的UA服务端点为名称的文件夹，在文件夹内可以放入证书和密钥，具体用处和使用方法请见下文。

图17-42 服务端点启动停止

创建服务端点OPCUA01，选中端点，右侧启动、停止、编辑及删除按钮由置灰不可操作变为可操作状态，点击启动按钮，弹出弹窗提示正在启动服务，同时下方日志输出界面打印日志包括服务状态，与KS数据源连接状态以及是否正常监听，图17-42为KS运行态启动时，OPCUA01可以正常连接到KS数据源，监听成功后，UA客户端可以连接到UAServer，拿到变量数据，图17-43为KS运行态未启动时，OPCUA01无法成功连接到KS数据源，监听失败，UA客户端无法连接到UAServer，拿不到变量数据。

图17-42 KS运行态启动时

图17-43 KS运行态未启动时

---

第十七章 其他程序访问SCADA数据的方式

### 17.4.3    UAServer服务删除

选中想要删除的端点，点击右侧删除按钮，弹窗提示，点击“是”UA服务端点移除，对应的发布数据服务停止。

---

第十七章 其他程序访问SCADA数据的方式

### 17.4.4    UAServer服务编辑

选中想要编辑的服务端点，点击右侧编辑按钮，可以对已经创建的服务端点进行编辑，除名称以外均可修改，修改配置方法与17.4.1中介绍一致，以下不再赘述。

当服务端点在运行时进行编辑，修改配置完毕后点击确定，弹出弹窗与下图所示，点击是时自动重启服务应用配置，点击否时保存配置但不生效，下次启动服务时应用配置。当服务端点在停止时进行编辑，修改配置完毕后点击确定，正常保存配置并在下次启动服务时应用配置。

---

第十七章 其他程序访问SCADA数据的方式

### 17.4.5    证书使用

OPCUA证书主要是为了可以正常使用Basic256Sha256、Basic256 (Deprecated)、Basic128Rsa15 (Deprecated)这三种加密策略以及作为一种UA客户端连接UAServer的限制条件，加密策略指定 UA 服务器支持的加密算法，当选择了加密策略但是没有提供响应证书时，OPCUA服务无法正常启动监听，客户端无法连接UAServer。

此外证书也可以用做一种登录形式，与None和用户名密码方式一样，限制UA客户端连接UAServer，下面说一下证书的使用

---

第十七章 其他程序访问SCADA数据的方式

#### 17.4.5.1   生成证书

我们提供了第三方工具用来生成证书，随OPC服务安装时一同安装在~\OPCServerTool\OPCUAServer路径下，名为GenerateCertificate，打开文件夹，双击opc.ua.configurationtool.exe后打开应用程序，可以选择为本机或其他机器生成证书，首先是为本机生成证书，如图17-44

图17-44

其中HostName是计算机名称，默认为本机计算机名称。点击“CreateCer”，显示证书正在生成，如下图所示

 

为其他机器生成证书打开证书生成工具，首先需要将GenerateCertificate拷贝到其他路径下，（避免本机生成的证书被替换掉），勾选Modify，输入要生成证书的计算机的名称，点击“CreateCer”，生成相应的证书，在GenerateCertificate的同级目录中将生成两个文件夹OpcUaClient和OpcUaServer；

注：当双击opc.ua.configurationtool.exe后弹出以下提示时，是由于证书生成工具位于中文路径下，请复制到**不包含中文路径**的其他目录中，即可正常使用

---

第十七章 其他程序访问SCADA数据的方式

#### 17.4.5.2   服务端证书的使用

目前服务端证书的用途主要是在建立服务端点时勾选了None之外的加密策略时，需要将生成的OpcUaServer文件，如图17-44所示中的证书和密钥分别复制到对应的UA服务的文件路径下如图17-45所示，才可以正常使用None之外的加密策略，UA服务才能正常监听。

图17-44 证书生成路径

图17-45 UAserver服务证书放置位置

---

第十七章 其他程序访问SCADA数据的方式

#### 17.4.5.3   UA客户端证书的使用

当想要使用证书方式来限制客户端登录的时候，需要将UA客户端的证书导入到UA服务证书信任控制区域，通过信任或者拒绝UA客户端证书的方式来达到目的，**此方式同样需要在服务端添加证书**，方法同17.4.5.2，下面以UaExpert为UA客户端为例，讲解一下证书控制UA客户端登录的方式：

第一步：生成UaExpert客户端证书，点击 “Settings”-“Manage Certificates”打开证书管理界面（图17-47）。

图17-47 证书管理界面

点击“Create new Application
Certificates”在图17-48中Organization输入名字，点击ok，会生成一个新证书，

图17-48 生成证书

之后选中生成的证书，点击“Copy Application Certificates
To…”，打开证书所在位置，可以看到UA客户端自己的证书和所信任的UAserver的证书如图17-49，将uaexpert.der证书导入到UA服务配置工具证书信任控制区域中，即可通过修改信任或拒绝的方式控制UA客户端的连接

图17-49 证书位置

---

第十七章 其他程序访问SCADA数据的方式

### 17.4.6    配置实例

OPCUA服务端作为一个独立的应用程序，可能要对接硬件制造商、软件开发商或其他第三方提供的UA客户端，因此数据项定义的方法和界面都可能有所差异。下面以UaExpert为例讲解KingSCADA OPCUAServer的使用方法

**第一步：**启动带有管理用户的KingSCADA 运行系统以保证OPCUA端能够正确连接

**第二步：**运行KingOPCUAServerTool服务配置工具，弹出画面，配置“OPCUA冗余”服务端点，使用IP172.30.10.20端口8800，勾选启用冗余，使用172.30.10.153 端口8800 UAServer服务界面，是否匿名选择“否”默认端口14840，安全策略勾选Basic256Sha256，启用Restful，默认端口14850，进行配置，点击启动（**需要用户事先启动****KingSCADA****运行系统并提前将证书生成工具生成的证书放入****对应的UA****服务的文件路径下，见17.4.5**）如图所示：

图17-45 建立服务

**第三步**：运行UaExpert，弹出如下画面，如图所示：

图17-46 UaExpert主界面

点击“Project”-“Servers”节点右键选择“ADD”，弹出的ADD Server界面。在“Configuration Name”输入框中输入任意名称。

“Discovery”界面配置：“Endpoint Filter”后的选择框中选择“opc.tcp”；可以在“Authentication Settings”中选择UA客户端身份认证方式，默认为匿名连接。可以选择用户名密码连接，使用KS数据源用户名密码登录，也可以选择证书控制连接方式，在对应位置添加UA客户端证书和密钥(添加导入在opc服务中受信任的证书和秘钥)并在UA服务中导入UA客户端证书即可（具体方法详见17.4.5.2及17.4.5.3）。

“Advanced”界面配置：在“Server
Information”-“Endpoint Url”的输入框中输入要连接的OPCUA服务对应的URL，如下图17-46
KingOPCUAServerTool.exe实例界面；在“Security Settings”中选择需要配置的安全策略和安全模式默认为非安全模式连接；在“Authentication Settings”中选择UA客户端身份认证方式，默认为匿名连接。配置界面如下图所示：

图17-46
Discovery配置界面

图17-46
KingOPCUAServerTool.exe实例界面

图17-47
Advanced配置界面

配置ADD Server后点击“OK”，选择“Project”-“Server”下连接的UA服务器，右键选择“Connect”，弹出“Certificate Validation”界面，点击界面中“Trust Server Certificate”后点击“Continue”。在弹出的“Connect Error”提示框中点击“Ignore”。

图17-47 UaExpert信任UA服务证书

图17-48 Connect Error界面

**第三步：**在“Address Space”窗口中选择“Root”-“Objects”-“RootNode”节点下列举了KingSCADA当前运行工程中建立的部分变量，将变量拖动到右侧“Data Access View”界面中，**在“Data Access View”界面中，双击变量的Value，即可对变量进行写操作**，界面如下图所示：

图17-49添加UA变量成功

***注：除了系统变量外，只有设置了“******允许其他应用访问******”选项的工程变量才会在图******17-49******中显示，如图所示：***

---

第十七章 其他程序访问SCADA数据的方式

### 17.4.7     OPCUA授权

OPCUA服务的使用需要有授权，其授权是在加密锁上进行烧制。

服务器端锁授权：*KSRS-0-60000-10-15-5-5-1 A40-L1-N1-D1-V1*，其中web客户端数后一位作为OPCUA授权使用，无实例数限制，非0表示拥有OPCUA授权，为0表示没有OPCUA授权。如果没有OPCUA授权，创建的OPCUA服务无法连接到KS数据源，无法启动监听。如下图所示：