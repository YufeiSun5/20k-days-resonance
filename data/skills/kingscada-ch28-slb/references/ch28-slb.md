第二十八章  负载均衡工具KingSCADASLB

# 第二十八章 负载均衡工具KingSCADASLB

---

第二十八章  负载均衡工具KingSCADASLB

## 28.1     概述

KingSCADA连接客户端的能力比较弱，一般不超过60个。随着互联网的快速发展以及可提供的网络带宽越来越高，有越来越多的项目中需要更多客户端（C/S,B/S）同时访问服务端数据。这就对KingSCADA提出了新的要求，为了解决这个问题，在KingSCADA3.51SP1及以后的版本中增加负载均衡SLB工具，方便KingSCADA服务器连接更多的客户端。

**注：一个项目中具体能够连接的客户端数目，依赖于网络带宽。**

---

第二十八章  负载均衡工具KingSCADASLB

## 28.2     功能描述

KingSCADA负载均衡SLB工具简称KingSCADASLB。它的主要功能是提升KingSCADA带客户端的能力。

一个KingSCADA可以连接多个负载均衡SLB，负载均衡SLB的网络是自适应的，当多个KingSCADASLB连接多个客户端负载时，SLB会均分所连接的客户端负载。当某个负载均衡SLB掉线后，其它负载均衡依然可以接管掉线后的那部分客户端。网络恢复后，又可以动态的进行调整。

同时KingSCADASLB对于多核心CPU进行了特殊的优化，可发挥多核心CPU的性能。如果客户端较多尽量使用多核心CPU。

 

 

图1 负载均衡SLB结构示意图

依据上图所示，KingSCADASLB连接KingSCADA Server，以订阅方式获取数据。

Ø 
一个KingSCADAServer可连接多个KingSCADASLB，具体数量要根据客户现场的具体需求而定，建议不超过4个；

Ø 
每个KingSCADASLB默认可连接50个客户端。实际连接数量可配置。一个服务端可连接客户端总数量与项目中可提供的网络带宽有直接关系，具体参见3.2节。

Ø 
KingSCADASLB和KingSCADA Server只能放到一个局域网；

Ø 
KingSCADASLB只用于实时数据负载均衡，不代理报警数据，历史数据，用户安全数据等；

Ø 
KingSCADASLB只能负载均衡单个KingSCADA Server。

 

**注：KingSCADASLB****不建议运行在XP****机器上**

---

第二十八章  负载均衡工具KingSCADASLB

## 28.3     负载均衡工具配置及应用

若要使用负载均衡工具来平衡负载，则需要对KingSCADA与KingSCADASLB都进行配置，接下来介绍一下配置过程与具体的含义。

---

第二十八章  负载均衡工具KingSCADASLB

### 28.3.1 KingSCADA实时服务器中配置

若使用SLB负载均衡功能，首先在KingSCADA的安装目录下的bin文件夹下找到kxConfig.ini文件，将SLBMode的值设置为2。

 SLBMode配置项的具体含义如下：

Ø 
SLBMode=0  表示客户端可直连KingSCADA服务器也可通过负载均衡工具来分担负载。配置原则是 当有可使用的KingSCADASLB时，把客户端分配到负载均衡SLB上，如果没有就直连。

Ø 
SLBMode=1  表示客户端直连KingSCADA服务器，不通过负载均衡的方式来分担负载。

Ø  SLBMode=2 
表示强制客户端使用负载均衡SLB，当没有可用的SLB时，客户端连接不上KingSCADA实时服务器。

**注：如果web****发布时需要使用SLB****代理的方式，则需要在第三方文件的快速添加中勾选KxRDBNetworkCtrl.dll****。**

图2

---

第二十八章  负载均衡工具KingSCADASLB

### 28.3.2 KingSCADASLB中配置

需要在KingSCADASLB的安装目录下，打开kxSLBConfig.ini进行配置。

注：必须配置项为KingSCADASLBServerIP，KSMasterServerIP。

全部配置项含义如下：

[KingSCADASLB]

KingSCADASLBServerIP=172.16.2.25  
表示负载均衡工具所在电脑的IP地址，必填项。

KingSCADASLBServerPort=8805      
表示负载均衡工具启用的监听端口 默认值为8805

KingSCADASLBWebServerIP=        
表示公网中所对应的IP

KingSCADASLBWebServerPort=8805    表示公网中启用的监听端口，默认值为8805,若公网发布则需要将此端口做映射

MaxAllowClientNum=50             
表示代理实时库连接的最大客户端数量，默认50个

OnceMaxSendDataNum =
10000      表示向客户端一次最大发送的记录数量。默认10000个，如果当前的记录个数少于10000个也会立即发送。如果超过10000个会分多次发送出去。（每条记录的大小大概30个字节。10000个记录大概是300KB的容量在无压缩的情况下传输大概需要3Mb的网络带宽。代理服务器使用了压缩传输压缩率在一般在60%~40%之间。最多能压缩到20%这样计算占用的带宽大概在1.5Mb左右）

MaxBufferSizeForClient
= 50        表示每个客户端独立的缓存记录所占内存的大小。默认为50M。可根据现场环境调整这个数值。

MaxBufferSizeForDataSource
= 5000   表示KS服务器获取数据的最大缓存长度。这个长度默认是5000，可缓存的记录条数为5000\*256 ，共计128万个记录。估计占用内存空间为40MB。

MaxBufferSizeForRealDB
= 100000    表示KS服务器实时库向客户端发布的一个前端缓存长度。默认为100000。可缓存记录条数为2560万条记录。估计最大占用内存空间为800MB。

CallbackPort=19500           
回调端口。用于KingSCADA回调用。默认是19500

MaxConnectError=2           
表示调用失败后最大的重试次数。默认为2.

KSMasterServerIP=172.16.2.63   表示KingSCADA服务器的主机IP地址。必填项

KSMasterServerIP2=          
表示KingSCADA服务器的主机备用IP地址

KSSlaverServerIP=            
表示KingSCADA服务器的从机IP地址。

KSSlaverServerIP2=           
表示KingSCADA服务器的从机备用IP地址。

KSServerPort=8800           
表示实时库访问端口默认是8800，具体地址需要和KingSCADA服务器配置统一。

HeartDetectInterval=3000      
表示心跳检查周期

NetworkTimeout=30000       
表示网络超时周期

如下图所示：

---

第二十八章  负载均衡工具KingSCADASLB

### 28.3.3 KingSCADASLB界面

当前的KingSCADASLB没有配置界面，只有运行的信息输出界面。根据需要配置kxSLBConfig.ini完成后，双击KingSCADASLB.exe，启动工具后会启动该界面，并且开始根据配置信息连接KingSCADA服务器。然后等待客户端连接和订阅数据。

图3

---

第二十八章  负载均衡工具KingSCADASLB

## 28.4     大客户端的软硬件配置要求

---

第二十八章  负载均衡工具KingSCADASLB

### 28.4.1 软件配置要求

KingSCADASLB可单独部署，也可与KingSCADA服务器可在同一台机器上。

若KingSCADA服务器为多CPU核心，并且内存满足28.4.2节所要求的，可以将KingSCADASLB与KingSCADA服务器部署在同一机器上。

---

第二十八章  负载均衡工具KingSCADASLB

### 28.4.2 KingSCADASLB内存配置要求

KingSCADASLB对所在机器的内存占用，主要包括以下部分：

（1）数据源缓存对象：这个对象用户缓存从KingSCADA服务器订阅的临时数据。它的默认的缓存大小是5000\*256 ，共计可缓存128万个记录。最大可占用40MB左右的内存。但通常这个缓存的占用会很小不到10MB。

（2）实时库映射表：这个是存储从KingsCADA订阅上来的所以变量和域的内存空间。它安装KingSCADA服务端的变量总数分片内存，一个Tag大概需要内存4KB如果10万变量会占用400MB内存。

（3）发布缓存对象，这个是实时库数据到客户端的一个很大的缓存对象。他默认的缓存大小100000\*256，共计能缓存128万个记录。估计最大占用内存空间为800MB。

总体来看，客户端数越多，变量点数越大占用的内存越高。当客户端数量超过50个，建议使用64位版本，并加大内存。

---

第二十八章  负载均衡工具KingSCADASLB

### 28.4.3 网络带宽要求

KingSCADA服务器上的一个Tag变量值发生变化，即会形成一条数据记录。

KingSCADASLB向客户端发送数据，在无压缩的情况下每条记录大小是30字节，压缩率一般可达到70%~50%之间，最多可达到20%。依此计算，10000个变量同时变化便会产生 10000条记录数据，若只向一个客户端发送，则大约占用1Mb网络带宽；若同时向10个客户端发送数据，则大约占用10Mb的网络带宽，以此类推。

当客户端首次连接服务器时，客户端会订阅一次服务器端所有变量及域值，当客户端正常运行时，只订阅服务端变化的变量值或域值。