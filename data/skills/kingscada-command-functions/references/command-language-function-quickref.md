命令语言函数速查手册

# 命令语言函数速查手册

---

命令语言函数速查手册

## 声明

1、以下所有函数的参数如果包含字符串参数，则参数使用的字符串长度最大为255，超过此长度时会被程序自动截取255个字符;

2、客户端工程不支持的函数：

配方相关函数、SQL访问相关函数

---

命令语言函数速查手册

## 一、画面操作函数

声明：画面操作函数都不能在程序启动脚本里执行。

---

命令语言函数速查手册

### 1、ShowPicture(string strPicName)

**功能：**

此函数用于显示画面。

**参数：**

strPicName ：要显示的画面名称，字符串类型。

**返回值：**

true：显示画面成功

false：显示画面失败

**调用格式：**

ShowPicture("反应车间");

---

命令语言函数速查手册

### 2、ShowPictureAt( string strPicName, int Left, int Top )

**功能：**

此函数用于将画面显示到指定的位置，如果画面没有打开，则打开画面并显示到指定位置，如果画面已经打开了，则将画面移动到指定位置

**参数：**

strPicName：画面的名称，字符串类型

Left：指定位置的左坐标值，以像素为单位

Top：指定位置的上坐标值，以像素为单位

**返回值：**

true：显示画面成功

false：显示画面失败

**调用格式：**

ShowPictureAt("反应车间",200,200);

---

命令语言函数速查手册

### 3、ClosePicture(string strPicName)

**功能：**

此函数用于将已经调入内存的画面关闭，并从内存中删除，但是当画面的AlwaysOpen属性设置为true时，画面不会从内存中删除，这种类型的画面从运行系统启动后就一直常驻内存，调用ClosePicture函数只会将此画面设置成隐藏模式。

**参数：**

strPicName：关闭的画面名称，字符串类型。

**返回值：**

true：关闭画面成功

false：关闭画面失败

**调用格式：**

ClosePicture("反应车间");

---

命令语言函数速查手册

### 4、HidePicture(string strPicName)

**功能：**

此函数用于隐藏正在显示的画面，但并不将其从内存中删除。

**参数：**

strPicName：表示要隐藏的画面名称，字符串类型。

**返回值：**

true ：表示隐藏画面成功

false：表示隐藏画面失败

**调用格式:**

HidePicture("反应车间");

---

命令语言函数速查手册

### 5、EnableNavigate(bool Cmdshow)

**功能：**

此函数用于设置是否允许打开画面导航窗口。

**参数：**

Cmdshow：1：允许打开，0：禁止打开

**返回值：**

无返回值。

---

命令语言函数速查手册

### 6、ShowNavigateWindow(bool Cmdshow)

**功能：**

打开画面导航窗口。

**参数：**

Cmdshow：1：打开画面导航窗口，0：关闭画面导航窗口

**返回值：**

无返回值。

**调用格式：**

EnableNavigate(1);

ShowNavigateWindow(1);

---

命令语言函数速查手册

### 7、GetPictureScrollXPos(string strPicName)

**功能：**

此函数用于获取窗口内指定画面水平方向的滚动位置，即左上角的X坐标值。

**参数：**

strPicName：表示要获取坐标值的画面名称，字符串类型。

**返回值：**

为当前窗口显示画面左上角的X坐标值

**调用格式：**

xx=GetPictureScrollXPos("反应车间");   
//xx的值即为当前窗口显示画面左上角的X坐标值。

---

命令语言函数速查手册

### 8、GetPictureScrollYPos(string strPicName)

**功能：**

此函数用于获取窗口内指定画面左上角的Y坐标值。

**参数：**

strPicName：表示要获取坐标值的画面名称，字符串类型。

**返回值：**

为当前窗口显示画面左上角的Y坐标值

**调用格式：**

yy=GetPictureScrollYPos("反应车间");   
//yy的值即为当前窗口显示画面左上角的Y坐标值。

---

命令语言函数速查手册

### 9、GetPictureStatus(string strPictureName)

**功能：**

此函数用于获取指定画面的状态

**参数：**

strPicName：画面名称，字符串类型

**返回值：**

返回画面状态标识：

0=无此画面

1=画面关闭

2=画面正在打开

3=画面正在关闭

4=画面已打开

5=画面隐藏

**调用格式：**

GetPictureStatus("反应车间");

---

命令语言函数速查手册

### 10、MovePicture(string strPicName, int Left, int Top)

**功能：**

此函数用于在系统运行时通过命令语言脚本来移动画面到所在的位置。

**参数：**

strPicName：要移动的画面名称，字符串型

Left：画面左边界所要移动的目标位置的坐标值，整型

Top：画面上边界所要移动的目标位置的坐标值，整型

**返回值：**

无返回值。

**调用格式：**

MovePicture("信息提示",400,300);    //将名字为"信息提示"的画面左边界移动到距坐标原点400，上边界距坐标原点300个像素点的位置。

---

命令语言函数速查手册

### 11、PreViewWholePicture(string strPicName, int ScaleX, int ScaleY, int Option, int StartX, int StartY)

**功能：**

此函数用于预览指定的画面(以画面的大小为基础，画面大小在画面属性对话框可以设置)

**参数：**

strPicName：画面的名称，字符串类型

ScaleX：预览输出的画面宽度占纸张总宽度的百分比

ScaleY：预览输出的画面高度占纸张总高度的百分比

StartX：要预览的画面左边横向空白长度占纸张总宽度的百分比

StartY：要预览的画面上边纵向空白长度占纸张总高度的百分比

Option：预览布局选项，仅在ScaleX和ScaleY同时为0的情况下有效

Option=0时，保持画面的纵横比不变，以适合页面的最大比例显示(画面不失真)，如果画面中有位图，建议使用这种模式，以避免位图被拉伸。

Option=1时，按页面的大小对画面进行缩放(画面有可能失真)

**返回值：**

true：表示预览成功

false：表示预览失败

**调用格式：**

PreViewWholePicture("反应车间"，80，80，0，10，10);

---

命令语言函数速查手册

### 12、PreviewWindow(string strPicName, int ScaleX, int ScaleY, int Option, int StartX, int StartY)

**功能：**

此函数用于预览指定的窗口(以窗口的大小为基础，窗口大小在画面属性对话框可以设置)

**参数：**

strPicName：画面的名称，字符串类型

ScaleX：预览输出的窗口宽度占纸张总宽度的百分比，

ScaleY：预览输出的窗口高度占纸张总高度的百分比

StartX：要预览的窗口左边横向空白长度占纸张总宽度的百分比

StartY：要预览的窗口上边纵向空白长度占纸张总高度的百分比

Option：预览布局选项，仅在ScaleX和ScaleY同时为0的情况下有效

Option=0时，保持窗口的纵横比不变，以适合页面的最大比例显示(画面不失真)，如果窗口中有位图，建议使用这种模式，以避免位图被拉伸。

Option=1时，按页面的大小对窗口进行缩放(画面有可能失真)

**返回值：**

true：预览成功

false：预览失败

**调用格式：**

PreviewWindow("反应车间"，80，80，0，10，10);

---

命令语言函数速查手册

### 13、PrintWholePicture(string strPicName, int ScaleX, int ScaleY, int Option, int StartX, int StartY)

**功能：**

此函数用于打印指定画面。

参数与调用格式与PreViewWholePicture函数相同，在此不再赘述。

---

命令语言函数速查手册

### 14、PrintWindow(string strPicName, int ScaleX, int ScaleY, int Option, int StartX, int StartY)

**功能：**

此函数用于打印指定窗口。

参数与调用格式与PreviewWindow函数相同，在此不再赘述。

---

命令语言函数速查手册

### 15、PrintScreenSet()

**功能：**

此函数用于打印当前的运行系统屏幕区域。

**返回值：**

true：打印成功

false：打印失败

**调用格式：**

PrintScreenSet();

与PrintPictureSet函数的区别在与打印的区域不同：

当前KingSCADA运行系统界面如下如所示。

执行PrintScreenSet()函数，则打印界面如下图。

执行PrintPictureSet()函数，则打印界面如下图。

---

命令语言函数速查手册

### 16、PrintPictureSet()

**功能：**

运行模式，调用该打印设置函数，可执行windows打印功能，实现打印KingScada当前激活画面的功能。

**返回值：**

true：打印成功

false：打印失败

**调用格式：**

PrintPictureSet();

---

命令语言函数速查手册

### 17、SaveCurWindowToFile(string strFileName)

**功能：**

保存当前画面窗口到指定的文件中，支持的图片格式是bmp。

**参数：**

strFileName：文件名称及文件所在的路径

**返回值：**

true：保存成功

false：保存失败

**调用格式：**

SaveCurWindowToFile("E:\反应车间.bmp");

保存的图片内容同PrintPictureSet()函数。

---

命令语言函数速查手册

### 18、SaveCurWindowToFile1()

**功能：**

保存当前画面窗口到用户指定的文件中，弹出另存为，让用户选择保存图片文件路径的对话框。

**返回值：**

true：保存成功

false：保存失败

保存的图片内容同PrintPictureSet()函数。

---

命令语言函数速查手册

### 19、SavePictureToFile(string strPicName, int Left, int Top, int Width, int Height, string strFileName)

**功能：**

保存整个画面或画面中的某个区域到指定的文件中，支持的图片格式是bmp。

**参数：**

strPicName：画面名称，字符串类型

Left：要保存的区域的起始位置的左坐标值

Top：要保存的区域的起始位置的上坐标值

Width：要保存的区域的宽度，以像素为单位

Height：要保存的区域德高度，以像素为单位

strFileName：保存的文件名称及其路径，字符串类型

**返回值：**

true：保存成功

false：保存失败

 

**调用格式：**

SavePictureToFile ("反应车间",
0, 0, 400, 300, "E:\反应车间\反应车间画面.bmp");

***注：当画面的宽和高过大时，******创建内存位图会失败，函数直接返回失败。画面的大小要限制在******1024 \* 1280 \*8******，即宽和高的乘积要小于******1024 \* 1280 \*8******。***

---

命令语言函数速查手册

### 20、SavePictureToFile1(string strPicName, int Left, int Top, int Width, int Height)

**功能：**

保存整个画面或画面中的某个区域到用户指定的文件中，弹出另存为，让用户选择保存图片文件路径的对话框。

**参数：**

strPicName：画面名称，字符串类型

Left：要保存的区域的起始位置的左坐标值

Top：要保存的区域的起始位置的上坐标值

Width：要保存的区域的宽度，以像素为单位

Height：要保存的区域德高度，以像素为单位

**返回值：**

true：保存成功

false：保存失败

***注：当画面的宽和高过大时，******创建内存位图会失败，函数直接返回失败。画面的大小要限制在******1024 \* 1280 \*8******，即宽和高的乘积要小于******1024 \* 1280 \*8******。***

---

命令语言函数速查手册

### 21、SaveWholePictureToFile(string strPicName, string strFileName)

**功能：**

保存整个画面到指定的文件中，支持的图片格式是bmp。

**参数：**

strPicName：画面名称，字符串类型

strFileName：保存的文件名及其路径，字符串类型

**返回值：**

true：保存成功

false：保存失败

**调用格式：**

SaveWholePictureToFile("反应车间",
"E:\反应车间\反应车间画面.bmp");

---

命令语言函数速查手册

### 22、SaveWholePictureToFile1(string strPicName)

**功能：**

保存整个画面，弹出另存为，让用户选择保存图片文件路径的对话框，支持的图片格式是bmp。

**参数：**

strPicName：画面名称，字符串类型

**返回值：**

true：保存成功

false：保存失败

---

命令语言函数速查手册

### 23、SaveCurScreenToFile()

**功能：**

保存当前屏幕，弹出另存为，让用户选择保存图片文件路径的对话框，支持的图片格式是bmp。

**返回值：**

true：保存成功

false：保存失败

**调用格式：**

SaveCurScreenToFile()；保存图片的内容与PrintScreenSet类似。

---

命令语言函数速查手册

### 24、SaveCurScreenToFile1(string strFileName)

**功能：**

保存当前屏幕到指定的文件中，支持的图片格式是bmp。

**参数：**

string strFileName：目标路径和文件名称，扩展名为.bmp。

**返回值：**

true：保存成功

false：保存失败

**调用格式：**

SaveCurScreenToFile1 ("E:\反应车间.bmp");   
//把当前打开的屏幕保存到E盘根目录下，文件名称是反映车间.bmp

当函数中的参数名称包含windows关键字时或当参数strFileName中不包含文件的名字时，不执行保存，不生成保存文件。

保存图片的内容与PrintScreenSet类似。

---

命令语言函数速查手册

### 25、ScrollPicture(string strPicName, int Left, int Top)

**功能：**

设置画面的滚动条到指定的位置

**参数：**

strPicName：表示要滚动的画面名称，字符串类型。

Left：表示画面的滚动条到距左边为left的位置，整型。

Top：表示画面的滚动条到距上边为top的位置，整型。

**返回值：**

无返回值。

**调用格式：**

ScrollPicture( "反应车间",
400,300);    //将"反应车间"画面的滚动条移动到位置(400,300)处

---

命令语言函数速查手册

### 26、GetPictureWindowViewScale(string strPicName)

**功能：**

获得画面的显示比例

**参数：**

strPicName：画面名称，字符串类型

**返回值：**

1表示100%，0.5表示50%，2表示200%

**调用格式：**

GetPictureWindowViewScale("反应车间");

---

命令语言函数速查手册

### 27、SetPictureWindowViewScale(string strPicName, float ViewScale)

**功能：**

设置画面的显示比例

**参数：**

strPicName：要缩放的画面名称，字符串类型

ViewScale：显示比例，范围0-1000，1表示100%，0.5表示50%

**返回值：**

true：缩放成功

false：缩放失败

**调用格式：**

SetPictureWindowViewScale("反应车间",0.5);

---

命令语言函数速查手册

### 28、SetPictureWindowViewScale1(string strPicName, float WidthViewScale, float HeightViewScale)

**功能：**

设置画面的显示比例

**参数：**

strPicName：要缩放的画面名称，字符串类型

WidthViewScale：画面宽显示比例，范围0-1000，1表示100%，0.5表示50%

HeightViewScale：画面高显示比例，范围0-1000，1表示100%，0.5表示50%

**返回值：**

true：缩放成功

false：缩放失败

---

命令语言函数速查手册

### 29、SetPicVarValue(string strPicVarName, string strTagName)

**功能：**

将某个SCADA工程变量的值赋给某个画面变量(画面变量是指在图形编辑器的"内容窗口"中定义的变量)。

**参数：**

strPicVarName：画面变量名，格式："画面名.变量名"，字符串变量

strTagName：SCADA工程变量名称，使用全称"\\站点名\变量名"

**返回值：**

遇到以下情况返回值为0：

(1)指定的画面没有打开，或者画面没有指定名称的变量

(2)SCADA工程变量找不到或不存在

(3)画面变量类型和SCADA工程变量类型不匹配

赋值成功，函数返回1

**调用格式：**

SetPicVarValue("反应车间.变量1", "\\local\液位");

---

命令语言函数速查手册

### 30、WindowSize(int nFlag)

**功能：**

此函数用于对运行画面最大化、最小化的操作。避免了由于要使用最大化、最小化按钮在运行画面存在的蓝色目录条，与画面颜色不搭的问题，使画面显示更美观。应用此函数时，配合选中运行系统设置中的"最小化按钮"和"最大化按钮"按钮，可以实现窗口最大化和最小化的切换。

**参数：**

nFlag----窗口最大化、最小化控制，整型。

当nFlag=0时，表示最大化，当nFlag=1时，表示最小化

**返回值：**

无返回值。

**调用格式****:**

WindowSize(nFlag);

**例如：**

WindowSize(1);    //运行窗口最小化操作

---

命令语言函数速查手册

### 31、SetPicToScreenCenter(string strPicName)

**功能：**

设置画面显示在屏幕中央。

**参数：**

strPicName：画面名称

**返回值：**

True：成功

False：失败

---

命令语言函数速查手册

### 32、PrintScreen(int XAxis, int YAxis, int Width, int Length, int ScaleX, int ScaleY, int Option, int StartX, int StartY);

**功能：**

打印屏幕区域。

**参数：**

XAxis：区域的起始X坐标

YAxis：区域的起始Y坐标

Width：区域的宽度

Length: 区域的高度

ScaleX：打印输出的宽度占此页总宽度的百分比

ScaleY：打印输出的高度占此页总高度的百分比

Option：打印选项，仅在ScaleX和ScaleY同时为0的情况下有效。

² 
Option=0时，保持窗口的纵横比不变，以适合页面的最大比例显示(画面不失真)，如果窗口中有位图，建议使用这种模式，以避免位图被拉伸。

² 
Option=1时，按页面的大小对窗口进行缩放(画面有可能失真)

StartX：要打印的窗口的横向空白长度的百分比

StartY：要打印的窗口的纵向空白长度的百分比

**返回值：**

True：成功

False：失败

**调用格式：**

PrintScreen(0，0，800，700，0，0，0，2，2);

---

命令语言函数速查手册

### 33、SavePicture(LPCTSTR strPicName)

功能：保存运行态下修改的画面中图素的属性

**参数：**

**strPicName****：画面名称**

**返回值：**

True：成功

False：失败

**调用格式：**

SavePicture("画面A");

---

命令语言函数速查手册

### 34、PauseSecond(int nSecond)

**功能：**

延时函数

**参数说明：**

nSecond：延时的豪秒数

**返回值：**

整形

1：表示执行成功

0：执行失败

**调用格式：**

PauseSecond(1000);  //延时1S

**注：**

**1）**该脚本函数目前只能用于画面脚本中，如按钮按下等。不能用在系统脚本中

**2）** 暂停后，主程序窗口，不响应鼠标键盘事件

---

命令语言函数速查手册

### 35、ReadTagImmediately(string TagName);

**功能：**

实现立即读取设备变量点的值

**参数说明：**

TagName：变量名

**调用格式：**

ReadTagImmediately("Tag1");//立即读取变量Tag1的值

---

命令语言函数速查手册

### 36、SaveCurScreenToFile2(int Left, int Top, int Width, int Height, string strFileName);

**功能：**

保存当前屏幕到指定的文件中，支持的图片格式是bmp。

**参数：**

Left：要保存的区域的起始位置的左坐标值

Top：要保存的区域的起始位置的上坐标值

Width：要保存的区域的宽度，以像素为单位

Height：要保存的区域德高度，以像素为单位

strFileName：目标路径和文件名称，扩展名为.bmp。

**返回值：**

true：保存成功

false：保存失败

**调用格式：**

SavePictureToFile2(0, 0, 400, 300, "E:\反应车间\反应车间画面.bmp");//保存当前选定的画面大小到E:\反应车间\反应车间画面.bmp位置。

**注意：**此函数可以保存ocx控件内容

---

命令语言函数速查手册

## 二、用户操作函数

---

命令语言函数速查手册

### 1、AddUser(bool bLogonServer, string strUserName, string strGroupName, string strUserComment, string strPassword, int nLogonTimeOut, string strUserTimeOut)

**功能：**

在运行过程中动态增加本地或远程用户。只有拥有用户管理权限的用户才能使用该函数增加用户。

**参数说明：**

bLogonServer：布尔型，true表示增加登录服务器的用户，false表示增加本地用户。

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\
?

strGroupName：用户组名称，字符串类型，16个字符，如果组名为空字符串，则不加入任何组；

strUserComment：描述，字符串类型，64个字符

strPassword: 密码，字符串类型，6—16位字符，密码中包含且仅包含数字、大写字母、小写字母、字符中的两种及以上，不满足要求的密码视为无效密码

nLogonTimeOut：登录超时时间，整形，单位为分钟，范围为0到2的31方减1，即0-2147483647。如果为0，表示无超时

strUserTimeOut：用户超时时间，字符串类型，如果为空字符串，无超时。格式为：YYYY-MM-DD
hh:mm:ss

**返回值：**整型

0:成功; -1:没有配置权限; -2:用户名称错误（空、含非法字符，重名）; -3:用户组名称错误（不存在）; -4:用户描述超长; -5:密码无效; -6:用户登录超时时间小于0; -7:用户超时时间格式错误; -8:其它错误

**调用格式：**

AddUser( 0, "KVUser1",
"Group1", " KVUser1", "Admin1", 0,
"2011-10-9 09:30:00");  //增加本地用户。

AddUser( 1, "KVUser1",
"Group1", " KVUser1", "Admin1", 0,
"2011-10-9 09:30:00");  //增加登录服务器的用户。

---

命令语言函数速查手册

### 2、ChangePassword()

**功能：**

在运行过程中动态修改用户密码，无参数。

**返回值：**

true：更改成功

false：更改失败

**调用格式：**

ChangePassword();

---

命令语言函数速查手册

### 3、ChangeUserPassword(bool bLogonServer, String strUserName, string strOldPassword, string strNewPassword)

**功能：**

在运行过程中动态修改用户的密码。只有拥有用户管理权限的用户才能使用该函数编辑用户。

**参数说明：**

bLogonServer：布尔型，true表示修改登录服务器中指定用户的密码，false表示修改本地用户的密码。

strUserName：用户名，字符串类型，该参数不能为空字符串

strOldPassword: 密码，字符串类型，6—16位字符，密码中包含且仅包含数字、大写字母、小写字母、字符中的两种及以上

strNewPassword: 密码，字符串类型，6—16位字符，密码中包含且仅包含数字、大写字母、小写字母、字符中的两种及以上

**返回值：**整型

0:成功;-1:没有配置权限;  -2:用户名称不存在; 
-3:原用户密码验证失败; -4:新用户密码无效;-5:其它错误

**调用格式：**

ChangeUserPassword
(0, "KVUser1","Admin1","Kingview1"); 
//修改本地用户KVUser1的密码

ChangeUserPassword
(1, "KVUser1","Admin1","Kingvew1"); 
//修改登录服务器的用户KVUser1的密码

---

命令语言函数速查手册

### 4、CheckUser()

**功能：**

校验用户的合法性，该函数适用于下面的场合：

如：当画面中的某个按钮有较高的权限，并需要两个用户同时验证通过后才允许操作该按钮。

**返回值：**

true：校验正确

false：校验不正确

**调用格式：**

CheckUser();

执行该函数弹出对话框，如下图所示：

在该对话框中输入需要验证的用户名和密码，如果验证通过后，该函数返回true。

如果您使用的用户是网络用户的话，要选中"网络"选项；如果是本机用户的话，就不用选中该项。网络用户是指网络中登录服务器的用户。

---

命令语言函数速查手册

### 5、CheckUserEx(StringTag OperatorName, StringTag MonitorName)

**功能：**

该函数的作用与CheckUser类似。不同的是：CheckUserEx除了可以检验当前用户的密码外，还能返回操作者姓名和监管者姓名

**参数：**

StringTag OperatorName：引用类型，返回操作者姓名

StringTag MonitorName：引用类型，返回监管者姓名

**返回值：**

密码正确，返回True，同时返回操作者姓名和监管者姓名

密码错误，返回False，不返回操作者和监管者姓名

**调用格式：**

CheckUserEx(工程变量，工程变量);

执行该函数弹出对话框，如下图所示：

在该对话框中输入需要验证的用户名和密码，如果验证通过后，该函数返回true和操作者姓名、监管者姓名，用户可以通过设置函数中的两个工程变量的"字符串输出"动画连接来查看操作者姓名和监管者姓名。

如果您使用的用户是网络用户的话，要选中"网络"选项；如果是本机用户的话，就不用选中该项。网络用户是指网络中登录服务器的用户。

---

命令语言函数速查手册

### 6、DeleteUser(bool bLogonServer, string strUserName)

**功能：**

在运行过程中动态删除用户。只有拥有用户管理权限的用户才能使用该函数删除用户。

**参数：**

bLogonServer：布尔型，true表示删除登录服务器的用户，false表示删除本地用户。

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串

**返回值：**整型

0:成功;  -1:没有配置权限;  -2:用户名称不存在;  -3:其它错误

**调用格式：**

DeleteUser(0, "KVUser1"); 
//删除本地用户KVUser1;

DeleteUser(1, "KVUser1"); 
//删除登录服务器的用户KVUser1;

---

命令语言函数速查手册

### 7、EditUser(bool bLogonServer, String strOldUserName, String strNewUserName, string strGroupName, string strUserComment, int nLogonTimeOut, string strUserTimeOut)

**功能：**

在运行过程中动态编辑用户。只有拥有用户管理权限的用户才能使用该函数编辑用户。只有这里列出的用户属性会改变，其它的属性保持不变。

**参数：**

bLogonServer：布尔型，true表示编辑登录服务器的用户，false表示编辑本地用户。

strOldUserName：原用户名称，根据该名称找到用户并进行编辑，字符串类型，该参数不能为空字符串

strNewUserName：新用户名称，将原用户名称改为该名称，32个字符，字符串类型，该参数不能为空字符串

strGroupName：用户组名称，字符串类型，16个字符，如果组名为空字符串，则用户将不属于任何组

strUserComment：描述，64个字符，字符串类型

nLogonTimeOut：登录超时时间，整形，单位为分钟，范围为0到2的31方减1，即0-2147483647。如果为0，无超时。

strUserTimeOut：用户超时时间，字符串类型，如果为空字符串，表示无超时。
格式为：YYYY-MM-DD hh:mm:ss

**返回值：**整型

0:成功; -1:没有配置权限; 
-2:原用户名称不存在;  -3:新用户名称错误（空、含非法字符，重名）; -4:用户组名称错误（不存在）; -5:用户描述超长; -6:用户登录超时时间小于0; -7:用户超时时间格式错误; -8:其它错误

**调用格式：**

EditUser(0, "KVUser1","KVUser2",
"Group2", " KVUser2", "admin", 0, "2011-10-9
09:30:00"); //编辑本地用户KVUser1

EditUser(1, "KVUser1","KVUser2",
"Group2", " KVUser2", "admin", 0, "2011-10-9
09:30:00"); //编辑登录服务器的用户KVUser1

---

命令语言函数速查手册

### 8、EditUserRole(string strUserName, string strRoleName, bool bRoleConfig)

**功能：**

在运行过程中动态关联和解除用户的角色。只有拥有用户管理权限的用户才能使用该函数关联和解除用户的角色。

**参数：**

bLogonServer：布尔型，true表示操作登录服务器用户，false表示操作本地用户。

strUserName：用户名称，字符串类型，该参数不能为空字符串

strRoleName: 角色名称，字符串类型，该参数不能为空字符串

bRoleConfig：是否关联角色，布尔型。1：关联角色；0：解除关联

**返回值：**整型

0:成功; -1:没有配置权限;  -2:用户名称为空或不存在;
-3:角色名称为空，不存在或用户没有此角色; -4:其它错误

**调用格式：**

关联角色 EditUserRole(0, "KVUser1","KVAdmin", 1);

EditUserRole(1, "KVUser1","KVAdmin", 1);

解除关联 EditUserRole(0, "KVUser1","KVAdmin", 0);

EditUserRole(1, "KVUser1","KVAdmin", 0);

---

命令语言函数速查手册

### 9、EditUsers()

**功能：**

在运行环境中动态修改用户和密码。只有拥有变更管理权限的用户才能使用该函数修改用户和密码。

**返回值：**

无返回值。

**调用格式：**

EditUsers();

执行该函数弹出对话框，如下图所示：

在弹出的对话框中添加、删除、修改用户和角色，操作过程和开发态下一致。不同的是：新建用户时没有"密码"选项，默认密码为空。如果要修改密码，则可以右键单击新建的用户，在弹出的右键菜单中选择"设置密码"选项来完成用户密码的修改，密码需满足6—16位字符，密码中包含且仅包含数字、大写字母、小写字母、字符中的两种及以上，否则视为无效密码。

---

命令语言函数速查手册

### 10、GetCurrentUser()

**功能：**

获取当前登录用户的名称

**返回值：**

字符串类型，返回当前登录用户的名称

**调用格式：**

GetCurrentUser();

---

命令语言函数速查手册

### 11、GetCurrentSecurityPriority()

**功能：**

获取当前登录用户的优先级

**返回值：**

无用户登录时返回0，正常返回1~999

**调用格式：**

GetCurrentSecurityPriority();

---

命令语言函数速查手册

### 12、GetCurrentSecuritySection()

**功能：**

获取当前登录用户的安全区

**返回值：**

字符串类型，无用户登录时返回"None"，有多个安全区时用分号";"隔开

**调用格式：**

GetCurrentSecuritySection();

---

命令语言函数速查手册

### 13、GetUserInfoContent（string strUserName，int iUserInfoID）

**功能：**

获得服务器端用户的辅助信息内容。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

iUserInfoID：整型，n表示第n条信息记录，范围0-7，其具体对应关系：

0表示单位，1表示部门，2表示职务，3表示级别，4表示电话，5表示Email，6表示扩展1,7表示扩展2

**返回值类型**：字符串 String

**返回值说明**：返回用户指定信息内容，Error表示输入用户名不存在

---

命令语言函数速查手册

### 14、GetLogonUsers( string DatasetName, string FieldNames, string FieldValues )

**功能：**

获取已登录的用户信息，生成到数据集中。

**参数：**

**DatasetName**：用于存储结果的数据集名称，该数据集包括用户名、用户的运行态域值，

其中用户运行态域包括：

ü  LogonDate：登录日期（年月日）

ü  LogonTime：登录时间（时分秒）

ü  LogonDuration：登录时长

ü  LogonCount：同一用户的登录数

ü  ClientType：客户端类型SCADAVIEW.EXE、IEXPLORE【版本号】、Unknow

ü  ClientOS：客户端操作系统

**FieldNames**：用于检索的各个域名称，用逗号分隔开，大小写不限（为空时，表示所有用户）

**FieldValues**： 用于给检索条件的各个域赋值的字符串，用逗号分隔开

**返回值：**

 0：运行正常

-1：输入域信息错误

-2：输入值信息错误

-3：输入域个数与值个数不匹配

---

命令语言函数速查手册

### 15、LogOff()

**功能：**

用户注销

**返回值：**

true：注销成功

false：注销失败

**调用格式：**

LogOff();

---

命令语言函数速查手册

### 16、LogOffNoTips()

**功能：**

执行注销操作，不弹出对话框，直接进行用户注销。

**返回值：**

true：用户登录成功

false：用户登录失败

**调用格式：**

LogOffNoTips();

---

命令语言函数速查手册

### 17、LogOn()

**功能：**

用户登录

**返回值：**

true：用户登录成功

false：用户登录失败

**调用格式：**

LogOn()

执行此函数弹出登录对话框，输入用户名和密码执行用户登录操作。

---

命令语言函数速查手册

### 18、LogOnNoInput(string strUserName, string strPassword, bool bNetLogMode)

**功能：**

执行登录操作，不弹出对话框，直接进行用户登录。

**参数：**

strUserName：登录用户名称，字符串类型

strPassword：登录用户密码，字符串类型

bNetLogMode：布尔类型，是网络用户还是本地用户，1：网络用户，0：本地用户

**返回值：**

true：用户登录成功

false：用户登录失败

**调用格式：**

LogOnNoInput("user1","
user1",0);

---

命令语言函数速查手册

### 19、GetRoleArray(bool bLogonServer, string strSecuritySection, int nSecurityPriority, string strRoleArrayName)

**功能：**

根据安全区和优先级获取角色。

**参数：**

bLogonServer：本地是否为登录服务器，离散类型，1：本地为登录服务器，0：本地不是登录服务器。

strSecuritySection：安全区，字符串类型

nSecurityPriority：优先级，整型，范围为1~999

strRoleArrayName: 角色数组名称。数组内储存所有符合条件的角色名称，该数组要事先存在，可以用CreatArray（）创建。

**返回值：**

成功：返回符合条件的所有角色个数。

失败：返回错误代码。-1 ：错误；-2
：安全区错误；-3 ：优先级越界；-4 ：角色数组类型不匹配。

**调用格式：**

CreatArray("Role",
3, 5);//先创建数组，字符串类型。

GetRoleArray(1,
"A", 900, "Role");//将安全区为A，优先级为900的角色获取后添加到"Role"数组里，可以用GetArrayElement（）函数得到数组内的各个角色名。

---

命令语言函数速查手册

### 20、GetUserArray(bool bLogonServer, string strSecuritySection, int nSecurityPriority, string strUserArrayName)

**功能：**

根据安全区和优先级获取用户。

**参数：**

bLogonServer：本地是否为登录服务器，离散类型，1：本地为登录服务器，0：本地不是登录服务器。

strSecuritySection：安全区，字符串类型

nSecurityPriority：优先级，整型，范围为1~999

strUserArrayName: 用户数组名称。数组内储存所有符合条件的用户名称，该数组要事先存在，可以用CreatArray（）创建。

**返回值：**

成功：返回符合条件的所有用户个数。

失败：返回错误代码。-1 ：错误；-2
：安全区错误；-3 ：优先级越界；-4 ：用户数组类型不匹配。

**调用格式：**

CreatArray("KSUser",
3, 5);//先创建数组，字符串类型。

GetUserArray(1,
"A", 900, " KSUser ");//将安全区为A，优先级为900的用户获取后添加到"KSUser"数组里，可以用GetArrayElement（）函数得到数组内的各个用户名。

---

命令语言函数速查手册

### 21、GetUserArrayByRole(bool bLogonServer, string strRoleName, string strUserArrayName)

**功能：**

根据角色获取用户。

**参数：**

bLogonServer：本地是否为登录服务器，离散类型，1：本地为登录服务器，0：本地不是登录服务器。

strRoleName：角色名称，字符串类型

strUserArrayName: 用户数组名称。数组内储存所有符合条件的用户名称，该数组要事先存在，可以用CreatArray（）创建。

**返回值：**

成功：返回符合条件的所有用户个数。

失败：返回错误代码。-1 ：错误；-2
：未找到角色；-3 ：用户数组类型不匹配。

**调用格式：**

CreatArray("KSUser",
3, 5);//先创建数组，字符串类型。

GetUserArrayByRole(1,
"KVAdmin", " KSUser ");//将属于KVAdmin角色的用户获取后添加到"KSUser"数组里，可以用GetArrayElement（）函数得到数组内的各个用户名。

---

命令语言函数速查手册

### 22、GetUserBindType（string strUserName）

**功能：**

获得用户的绑定类型。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

**返回值**：整型Int

0：表示没有进行绑定设置

1：表示该绑定了机器的IP地址

2：表示绑定了机器的MAC地址

-1：其它错误，如用户名不存在

---

命令语言函数速查手册

### 23、GetUserMaxlogonNum（string strUserName）

**功能：**

获得用户的最大在线数。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

**返回值**：整型Int

0：未做限制，同一用户能登录多次

n：最大在线用户数（范围0~1000）

-1：其他，例如用户名不存在

---

命令语言函数速查手册

### 24、GetUserBindAddr（string strUserName）

**功能：**

获得用户绑定地址。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

**返回值**：字符串String

返回值为Ip地址格式，即用户绑定地址是IP地址；

返回值为MAC地址格式，即用户绑定地址是MAC地址；

返回值为空，即用户未设定绑定地址；

---

命令语言函数速查手册

### 25、GetUserIsForceLogon（string strUserName）

**功能：**

获得用户是否允许强制登录。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

**返回值**：整型Int

0：表示未配置强制登录，即不允许强制登录

1：表示配置了强制登录，即允许此用户强制登录

-2：用户名称错误（空、含非法字符）

---

命令语言函数速查手册

### 26、GetUsersAll(string Dataset, string FieldNames, string FieldValues)

**功能：**

获取系统中所有用户信息，生成到数据集中。

**参数：**

**DatasetName**：生成的数据集名称（自定义的）

**FieldNames**：用于检索的各个域名称，用逗号分隔开，大小写不限（为空时，表示所有用户）

**FieldValues**： 用于给检索条件的各个域赋值的字符串，用逗号分隔开

**返回值：**

  0：运行正常

-1：输入域信息错误

-2：输入值信息错误

-3：输入域个数与值个数不匹配

**注：**

在使用此函数时，FieldNames中涉及到的域字段名含义：

       UserName;                   用户名

       UserComment;              用户描述

       UserID;       
          用户ID，只读属性

       bForceLogon;        
强制登录

       nLimitedLogonNum;  
最大在线数

       sBindType;                   绑定类型（无绑定\ IP绑定\ MAC绑定）

       wstrBindAddress;          绑定的地址 (IP、MAC) ，字符串

       nHasLogonNum;           已登录该账户的客户端数目

       UserTimeOut;  
          用户超时，默认为，无超时

       LogonTimeOut;             登录超时单位: 分钟，默认为，无超时

       UserAddTime;               用户创建时间，不可修改

       UserModifyTime;          用户最后修改时间

       GroupName;                 用户组

UserInfomation1;          单位

       UserInfomation2;          部门

       UserInfomation3;          职务

       UserInfomation4;          级别

       UserInfomation5;          电话

       UserInfomation6;          电子邮箱地址

       UserInfomation7;          扩展1

UserInfomation8;          扩展2

FieldValues与FieldNames一一对应，即FieldNames中有几个域，FieldValues就需要有几个值，并且顺序要一致。部分值有格式要求：

举例：获取单位为"一厂"，职务为"经理"的用户信息：

GetUsersAll( "Dateset2", "UserInfomation1,UserInfomation3", "一厂,经理"
);

---

命令语言函数速查手册

### 27、SetUserBindType（string strUserName，int iUserBindType）

**功能：**

设置用户的绑定类型。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

iUserBindType：绑定类型，整型，0表示"无"，1表示"IP地址绑定"，2表示"MAC地址绑定"

**返回值**：整型Int

0：成功

-1：没有配置权限

-2：用户名称错误（空、含非法字符，重名）

-3：绑定类型错误（不存在）

-4：其它错误

---

命令语言函数速查手册

### 28、SetUserMaxlogonNum（string strUserName, int iUserLimitCount）

**功能：**

设置用户的最大在线数。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

iUserLimitCount：一个用户同时登录的最大数量，整型，该参数输入范围0-1000。

**返回值**：整型Int

0：成功

-1：没有配置权限

-2：用户名称错误（空、含非法字符，重名）

-3：绑定类型错误（不存在）

-4：其它错误

---

命令语言函数速查手册

### 29、SetUserBindAddr（string strUserName, string strUserBindAddr）

**功能：**

设置用户绑定地址，绑定IP地址或是MAC地址。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

strUserBindAddr：绑定地址，字符串类型，输入值为"IP地址绑定"或"MAC地址绑定"。

**返回值**：字符串String

0：成功

-1：没有配置权限

-2：用户名称错误（空、含非法字符）

-3：绑定地址错误（例如格式错误）

-4：其它错误

---

命令语言函数速查手册

### 30、SetUserIsForceLogon（string strUserName,int iUserIsForceLogon）

**功能：**

设置用户是否允许强制登录。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

iUserIsForceLogon：设置项是否允许强制登录，整型，0表示不设置"强制登录"，1表示设置"强制登录"

**返回值**：整型Int

0：未配置"强制登录"

1： 已配置强制登录

-1: 没有配置权限；

-2：用户名称错误（空、含非法字符）；

-3: 其它错误；

---

命令语言函数速查手册

### 31、SetUserInfoContent（string strUserName，int iUserInfoID，string strUserInfoContent）

**功能：**

设置用户信息内容。

**参数**：

strUserName：用户名，字符串类型，32个字符，该参数不能为空字符串，也不能含非法字符：,:;+-\*/%&!~|^<>={}[]().`’ "\ ?

iUserInfoID：整型，n表示第n条信息记录，范围0-7

strUserInfoContent：设置信息内容

**返回值类型**：整型Int

**返回值说明**：

0：设置成功;

-1：没有配置权限;

-2：用户名称错误（空、含非法字符，重名）;

-3：记录不存在

-4：其它错误

---

命令语言函数速查手册

## 三、配方操作函数

---

命令语言函数速查手册

### 1、RecipeDelete(string strRecipeTemplateName, string strRecipeName)

**功能：**

动态删除配方模板中指定的配方。

**参数：**

strRecipeTemplateName：工程中定义的配方模板名称，字符串类型

strRecipeName：配方模板中配方的名称，字符串类型

**返回值：**

true：删除配方成功

false：删除配方失败

**调用格式：**

RecipeDelete("bread","recipe3");

---

命令语言函数速查手册

### 2、RecipeInsertRecipe(string strRecipeTemplateName, string strRecipeUnitName, string strInsertRecipeName)

**功能：**

在指定配方中选定的位置插入一个新的配方

**参数：**

strRecipeTemplateName：工程中定义的配方模板名称，字符串类型

strRecipeUnitName：配方模板中的单元名称，字符串类型

strInsertRecipeName：要插入的新配方的名称，字符串类型

**返回值：**

true：插入配方成功

false：插入配方失败

**调用格式：**

RecipeInsertRecipe("bread","unit1","recipe4");

执行此函数后，弹出对话框如下所示：

在该对话框中选择要插入的位置，确定后新的配方将被插入到指定配方的前面。

---

命令语言函数速查手册

### 3、RecipeInsertRecipe2(string strRecipeTemplateName, string strPostionRecipeName, string strRecipeUnitName, string strInsertRecipeName)

**功能：**

将一个新配方插入到配方模板的指定位置

**参数：**

strRecipeTemplateName：工程中定义的配方模板名称，字符串类型。

strPostionRecipeName：要插入配方的位置。即指定在哪个配方前插入，缺省情况下可为空，则插入在最前面。

strRecipeUnitName：配方模板中单元的名称，字符串类型。

strInsertRecipeName：要插入的新配方的名称，字符串类型。不能为空，不能和原有配方名字重复。

**返回值：**

true：插入配方成功

false：插入配方失败

**调用格式：**

RecipeInsertRecipe2
("bread","recipe3","unit1","recipe4");

---

命令语言函数速查手册

### 4、RecipeLoad(string strRecipeTemplateName, string strRecipeUnitName, string strRecipeName)

**功能：**

此函数将配方模板中某一配方中的数据下载到某一单元的变量中。

**参数：**

strRecipeTemplateName：工程中定义的配方模板名称，字符串类型。

strRecipeUnitName：指定配方值下载到哪个单元中，字符串类型。

strRecipeName：指配方模板中配方的名字，字符串类型。

**返回值：**

true：插入配方成功

false：插入配方失败

**调用格式：**

RecipeLoad("bread"
,"unit1","recipe1");      //将名为"recipe1"的配方下载到unit1对应的变量中。

---

命令语言函数速查手册

### 5、RecipeSave(string strRecipeTemplateName, string strRecipeUnitName, string strRecipeName)

**功能：**

动态实现将新建配方或对修改过的配方存入已有的配方模板中。

**参数：**

strRecipeTemplateName：工程中定义的配方模板名称，字符串类型。

strRecipeUnitName：配方模板中单元的名称，字符串类型。

strRecipeName：指配方模板中配方的名称，字符串类型。

**返回值：**

true：保存指定配方成功

false：保存指定配方失败

**调用格式：**

RecipeSave("bread","unit1","recipe3");   
//保存名为"recipe3"的配方到配方模板中。

***注：操作此函数的用户必须具有******"******修改配置******"******的权限，如图所示：***

---

命令语言函数速查手册

### 6、RecipeSelectNextRecipe(string strRecipeTemplateName, string strRecipeName)

**功能：**

在配方模板中选择当前配方的下一个配方。

**参数：**

strRecipeTemplateName：工程中定义的配方模板名称，字符串类型。

strRecipeName：当前配方名称，字符串类型。

**返回值：**

字符串类型，返回指定配方的下一个配方的名称，如果strRecipeName的值为空或是无效的名称，则返回配方模板中的最后一个配方;如果strRecipeName的值为配方模板中的最后一个配方，则直接返回此配方。

**调用格式：**

RecipeSelectNextRecipe("bread", "recipe3");

---

命令语言函数速查手册

### 7、RecipeSelectPreviousRecipe(string strRecipeTemplateName, string strRecipeName)

**功能：**

在配方模板中选择当前配方的前一个配方。

**参数：**

strRecipeTemplateName：指工程中定义的配方模板名，字符串类型。

strRecipeName：当前配方的名称，字符串类型。

**返回值：**

字符串类型，返回指定配方的上一个配方的名称，如果strRecipeName的值为空或是无效的名称，则返回配方模板中的第一个配方;如果strRecipeName的值为配方模板中的第一个配方，则直接返回此配方。

**调用格式：**

RecipeSelectPreviousRecipe("bread", "recipe3");

---

命令语言函数速查手册

### 8、RecipeSelectRecipe(string strRecipeTemplateName, string strMess)

**功能：**

在指定的配方模板中选取指定的配方。

**参数：**

strRecipeTemplateName：指工程中定义的配方模板名称，字符串类型。

strMess：字符串提示信息，由工程人员自己设定，字符串类型。

**返回值：**

字符串类型，返回选中的配方名称。

**调用格式：**

RecipeSelectRecipe("bread"，"please
input recipe name!");

---

命令语言函数速查手册

## 四、SQL访问函数

---

命令语言函数速查手册

### 1、SQLSelectTop(int nConnectID, string strTableName, string strBindListName, string strCondition, string strOder, string strNum)

**功能：**

该函数用于查询SQL数据库中符合查询条件的前N条记录。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：数据库表格名称，字符串型。

strBindListName：记录体名称，字符串型。

strCondition：条件子句，字符串型。

strOder：排序子句，字符串型。

strNum ----符合查询条件和排序的前N条，如TopExpr＝3，则表示符合查询条件和排序的前3条记录，字符串型。

**返回值：**

整型

**语法格式：**

SQLSelectTop( DeviceID, "TableName", "BindList",
"WhereExpr", "OrderByExpr", "TopExpr" );

**例如：**

SQLSelectTop(DeviceID, "table1", "bind1",
"select \* from table1", "DESC", "10" );

查询SQL数据库中表"table1"中符合查询条件的前10条记录，并按降序排列。

***注：******SQLSelectTop******函数******\_******对于******MySQL******数据库不适用，因为******MySQL******数据库不支持******Top******语句，******MySQL******采用******Limit******语法来实现指定记录条数的查询。***

***举例：******select \* from mytable limit 1,10***

***以上语句在******MySQL******中使用，返回第******1******条至第******10******条记录。***

---

命令语言函数速查手册

### 2、SQLAppendStatement(int nConnectID, string strSqlSatement)

**功能：**

在SQLSetStatement()后，追加语句。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strSqlSatement：附加的SQL语句，字符串型。

**返回值：**

整型

**调用格式：**

ResultCode=SQLAppendStatement(DeviceID,"SqlStatement");

**例如：**

SQLAppendStatement(DeviceID, "where 年龄=24");   
// 在SQLConnct()产生的连接号的后面追加条件：年龄=24

***注：本函数自动在语句两侧添加空格，所以同一个变量或连接符不能分开到两条语句中。***

---

命令语言函数速查手册

### 3、SQLClearStatement(int nConnectID, int SQLHandle)

**功能：**

清除指定连接号的SQL操作语言描述。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

SQLHandle：SQLPrepareStatement()产生的内存整数，整型。

**返回值：**

整型

**调用格式：**

ResultCode=SQLClearStatement(DeviceID,SQLHandle);

**例如：**

请详见SQLInsertPrepare

---

命令语言函数速查手册

### 4、SQLClearTable(int nConnectID, string strTableName)

**功能：**

删除表格中的所有记录，但保留表格。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：需访问的数据库表名，字符串型。

**返回值：**

整型

**调用格式：**

SQLClearTable(DeviceID, "TableName");

**例如：**

SQLClearTable(DeviceID, "kingview");

删除表格kingview中的所有记录

---

命令语言函数速查手册

### 5、SQLCommit(int nConnectID)

**功能：**

提交SQL语句，定义在一组访问语句的结尾。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLTransact()后的指令暂不执行，直到执行了SQLCommit()。语法：

SQLCommit( DeviceID );

**例如：连续实现三次插入**

SQLTransact(DeviceID);

SQLInsertPrepare(DeviceID,TableName,BindList,SQLHandle);

SQLInsertExecute(DeviceID,BindList,SQLHandle);

SQLInsertExecute(DeviceID,BindList,SQLHandle);

SQLInsertExecute(DeviceID,BindList,SQLHandle);

SQLCommit(DeviceID);

***注******1******：由于同时执行多个指令，******SQLCommit()******执行速度会变慢。***

***注******2******：在******SQLTransact()******指令和******SQLCommit()******指令之间的一组指令称为一个指令集。一个指令集的管理如同一个单一指令。***

***注******3******：尽量避免在画面脚本和全局脚本中同时使用事务处理***

***注******4******：合理调度脚本，避免在执行事务时有其他脚本对相同的表格做修改******(******比如插入、删除、更新等******)******，特别是定时脚本***

***注******5******：尽量少用事务处理，可使用其他******SQL******函数配合实现这项功能***

---

命令语言函数速查手册

### 6、SQLConnect(IntTag nConnectID, string strDsn)

**功能：**

连接KingSCADA和数据库(用连接句柄的方式)。用户可通过SQLConnect()方法建立一个数据库连接，利用这个连接，向数据库发送SQL命令请求，返回操作结果，并不关闭连接，该连接句柄可以一直使用，直到调用SQLDisconnect()函数将其断开。通过获取句柄方法，使得一个数据库连接方法可以得到高效、安全的复用。

因为网络、数据库本身的稳定性原因，即使没有执行释放连接句柄的脚本，时间长了，连接句柄也可能会自动失效，在网络好、数据库访问量不太大的情况下，1个连接句柄大概能坚持几天的时间，建议在执行SQL命令的脚本里，增加获取返回值的语句，来判断是否需要重新获取连接句柄。例句：

 

int tmpint;

tmpint=SQLSelect(DeviceID,"TableName","BindList",
" strCondition "," strOder ");

if (tmpint!=0)

{

    SQLDisconnect(DeviceID);

    SQLConnect(DeviceID, "
dsn=;database=;uid=;pwd=");

   
SQLSelect(DeviceID,"TableName","BindList", "
strCondition "," strOder ");

｝

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strDsn：连接语句，字符串型。

**返回值：**

整型

**调用格式：**

SQLConnect(DeviceID, " dsn=;database=;uid=;pwd=");

"dsn=;uid=;pwd="格式如下：

"DSN=data source name [;attribute= value[;attribute = value]…"

**例如：**

KingSCADA以sa身份登录(无密码)和名为wang的SQL Server中的pubs数据库连接

SQLConnect(DeviceID,"DSN=wang;DATABASE=pubs;UID=sa; PWD=");

属性描述：

|  |  |
| --- | --- |
| 属性 | 值 |
| DSN | ODBC中定义的数据源名 |
| UID | 登录ID号 |
| PWD | 密码，区分大小写 |
| DATABASE | 所要访问的数据库名 |

***注：不同的数据库的连接字符串区别较大，用户需要根据具体的数据库类型生成连接字符串，相关信息可查询该类型数据库德使用说明。***

---

命令语言函数速查手册

### 7、SQLCreateTable(int nConnectID, string strTableName, string strTemplateName)

**功能：**

以表格模板中定义的表格类型，在数据库中创建新表。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：要创建的数据库名，字符串型。

strTemplateName：表格模板名称，字符串型。

**返回值：**

整型

**调用格式：**

SQLCreateTable(DeviceID,"TableName","TemplateName");

**例如：**

SQLCreateTable(DeviceID, "kingview", "table1");

***注：对于Oracle******数据库，不能使用离散变量类型，需要用整型代替(******原因是Oracle******数据库不支持bool******数据类型)******。***

---

命令语言函数速查手册

### 8、SQLDelete(int nConnectID,string strTableName,string strCondition)

**功能：**

删除一条或多条记录。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型

strTableName：数据库表格名称，字符串类型

strCondition：删除记录的条件，字符串型

**返回值：**

整型

**调用格式：**

SQLDelete(DeviceID, "TableName", "WhereExpr");

**例****1****：**

SQLDelete(DeviceID, "KingTable","LogNo=11");

删除KingTable表格中所有LogNo列等于11的记录

**例****2****：**

SQLDelete(DeviceID, "KingTable", "年龄>=20 and 年龄<30");

删除"年龄"字段中在20~30之间的记录。

**例****3****：**

SQLDelete(DeviceID, "KingTable", "姓名=’Asia’");

删除"姓名"字段中为Asia的记录，其中"姓名"字段类型是字符串类型。

---

命令语言函数速查手册

### 9、SQLDisconnect(int nConnectID)

**功能：**

断开与数据库的连接。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLDisconnect(DeviceID);

---

命令语言函数速查手册

### 10、SQLDropTable(int nConnectID, string strTableName)

**功能：**

删除一个表格(包括结构)。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：表格名称，字符串型。

**返回值：**

整型

**调用格式：**

SQLDropTable(nConnectID,strTableName);

**例如：**

SQLDropTable(DeviceID,"KingTable");

---

命令语言函数速查手册

### 11、SQLEndSelect(int nConnectID)

**功能：**

在使用SQLSelect()之后使用此函数释放用来存储结果表格的资源。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLEndSelect(DeviceID);

---

命令语言函数速查手册

### 12、SQLErrorMsg(int nResultCode, StringTag strErrorMsg)

**功能：**

SQL系列函数执行后都有返回值，返回值类型为整型，不同的返回值代码代表不同的执行结果。因为返回值代码很不直观，本函数将nResultCode参数设定的返回值代码转成返回结果描述字符串，并返给strErrorMsg参数设定的字符串变量。返回值代码和返回结果描述的对应关系参见后面的"SQL函数返回值列表"帮助说明。

**参数：**

nResultCode：返回值代码，整型，必须是下列数之一：0、-2、-3、-5、-6、-7、-8、-9、-10、-11、-12、-13、-14、-15

strErrorMsg：保存转换后的返回结果描述信息的字符串变量

**返回值：**

整型

**调用格式：**

SQLErrorMsg(ResultCode,
strErrorMsg);

**例如：**

SQLErrorMsg(-7,StringTag1);

//其中StringTag1是KingSCADA中定义的字符串变量，存放错误信息。

---

命令语言函数速查手册

### 13、SQLExecute(int nConnectID, string strBindName, int nSqlHandle)

**功能：**

执行SQL语句。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strBindName：记录体名称，字符串型。

nSqlHandle：如果调用前执行了SQLPrepareStatement(), 此参数为返回的一个整数，如果没有准备的句柄，此值为零。

**返回值：**

整型

**调用格式：**

SQLExecute(DeviceID, "BindList" , SQLHandle);

---

命令语言函数速查手册

### 14、 SQLSelect(int nConnecdtID, string strTableName, string strBindName, string strCondition, string strOder)

**功能：**

访问数据库，得到一个特定的选择集。选择集中的记录可以由SQLFirst(), SQLNext(),SQLPrev(),SQLLast()等函数访问。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：数据库表格名称，字符串型。

strBindName：记录体名称，字符串型。

strCondition：SQL条件语句，字符串型。

strOder：定义排序的列和方向。只有列名可以用来排序，格式：列名[ASC|DESC]。如，按"温度"进行升序排序："温度 ASC"。另外，排序中也可使用多重表达式。如，"温度 ASC，时间 DESC"，字符串型。

**返回值：**

整型

**调用格式：**

SQLSelect(DeviceID,"TableName","BindList", " strCondition "," strOder ");

**例****1****：**

strCondition举例：

字符串例子："Ser\_No=’abcd’"

字符串中使用like语句："Ser\_No like ab%"

使用%代表广义字符，多个条件之间使用and进行连接，如：Ser\_No=’abcd’ and
‘Number=150’

当使用SQLSelect产生选择集后要使用SQLEndSelect释放资源。

**例****2****：**

选择表格kingview中列名"Ser\_No"为abcd的行，以温度列的升序排序。变量对应关系在bind1中指定。

SQLSelect(DeviceID, "kingview", "bind1",
"Ser\_No=’abcd’, "温度 ACS");

**例****3****：**

字符串变量：FindDate表示条件;(这是相似查询)

string WhereExpr="日期
like+'%"+FindDate+"%'";

SQLSelect( DeviceID, "数据记录表",
"日报表", WhereExpr, "" );

**例****4****：**

String  str1="炉号="+"'"+\\本站点\test+"'";

SQLSelect( DeviceID, "表2",
"Bind2", str1, "" );  //SQL变量条件查询, [\\本站点\test](file:///\\本站点\test)：字符串变量

**例****5****：**

String strtime=StrFromInt( inttime, 10 );

//inttime:为整数类型。先转换为字符串类型

string str1="Times="+"'"+strtime+"'";

SQLSelect( DeviceID, "数据查询",
"BIND", str1, "" );

**例****6****：**

SQLSelect(DeviceID, "KingTable", "Bind1", "",
"");

SQLFirst(DeviceID);/\*得到选择集中的第一条记录\*/

SQLNext(DeviceID);/\*得到选择集中当前记录的下一条记录\*/

SQLPrev(DeviceID);/\*得到选择集中当前记录的上一条记录\*/

SQLLast(DeviceID);/\*得到选择集中的最后一条记录\*/

执行SQLSelect函数后得到KingTable表中全部记录，并通过SQLFirst、SQLNext、
SQLPrev和SQLLast函数查询得到的所有记录

---

命令语言函数速查手册

### 15、SQLNumRows(int nConnectID)

**功能：**

SQLSelect()函数得到的选择集中记录的个数。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLNumRows(DeviceID);

**例如：**

NumRows=SQLNumRows(DeviceID);

---

命令语言函数速查手册

### 16、SQLFirst(int nConnectID)

**功能：**

从SQLSelect()函数产生的数据集中选取第一条记录。此句执行之前，必须执行SQLSelect()。

**参数：**

nConnectID---- SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLFirst (DeviceID);

---

命令语言函数速查手册

### 17、SQLLast(int nConnectID)

**功能：**

从SQLSelect()函数产生的数据集中选取最后一条记录。此句执行之前，必须执行SQLSelect()。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLLast(DeviceID);

---

命令语言函数速查手册

### 18、SQLPrev(int nConnectID)

**功能：**

选取选择集中当前记录的上一条记录。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLPrev(DeviceID);

---

命令语言函数速查手册

### 19、SQLNext(int nConnectID)

**功能：**

选取选择集中当前记录的下一条记录。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLNext(DeviceID);

---

命令语言函数速查手册

### 20、SQLGetRecord(int nConnectID, int nRecordNum)

**功能：**

返回当前选择集中的指定序号的记录。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

nRecordNum：序号，整型。

**返回值：**

整型

**调用格式：**

SQLGetRecord(DeviceID, RecordNumber);

例如：

SQLGetRecord(DeviceID, 3);

返回选择集中的第三条记录

---

命令语言函数速查手册

### 21、SQLInsert(int nConnectID, string strTableName, string strBindName)

**功能：**

使用记录体中定义的连接在表格中插入一个新的记录。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：表格名称，字符串型。

strBindName：记录体名称，字符串型。

**返回值：**

整型

**调用格式：**

SQLInsert(DeviceID, "TableName", "BindList");

**例如：**

SQLInsert(DeviceID, "kingview", "bind1");

在表格kingview中插入一条记录，记录体bind1

 

***注：***

***以下三个函数配合使用可以取代标准的******SQLInsert()******实现快速插入：******SQLInsertPrepare(),SQLInsertExecute(),SQLInsertEnd()******。***

***SQLInsert()******是一个一步完成程序，包括插入和结束语句，因此，当多次执行时，整个过程重复执行，资源也在被反复分配和释放。***

***而******SQLInsertPrepare()******分配句柄******SQLHandle******后，可以使用该句柄连续执行多个******SQLInsertExecute(),*** ***最后执行******SQLInsertEnd()******释放句柄。这样，同样的资源反复使用以达到提高效率的目的。***

---

命令语言函数速查手册

### 22、SQLInsertPrepare(int nConnectID, string strTableName, string strBindName, IntTag nSQLHandle)

**功能：**

SQLInsertPrepare()为SQLInsertExecute()函数做好前期准备，包括生成插入语句、分配资源、分配句柄，并通过句柄参数nSQLHandle传递给SQLInsertExecute()函数。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：数据库表格名称，字符串型。

strBindName：记录体名称，字符串型。

nSQLHandle：产生的句柄，整型。

**返回值：**

整型

**调用格式：**

SQLInsertPrepare(DeviceID,"TableName", "BindList",
SQLHandle);

---

命令语言函数速查手册

### 23、SQLInsertExecute(int nConnectID, string strBindName, int nSQLHandle)

**功能：**

执行句柄参数nSQLHandle指定的sql插入，可以执行多次，每次执行插入都取记录体变量的最新值。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strBindName：记录体名称，字符串型，函数执行时会把strBindName参数设定的记录体里的变量的最新值代入sql插入语句。

***注：******strBindName******参数值必须和******nSQLHandle******参数所对应的******SQLInsertPrepare()******语句里的******strBindName******参数值相同，即必须是同一个记录体名称。***

nSQLHandle：SQLInsertPrepare产生的句柄，整型。

**返回值：**

整型

**调用格式：**

SQLInsertExecute(DeviceID,"BindList", SQLHandle);

---

命令语言函数速查手册

### 24、SQLInsertEnd(int nConnectID, int nSQLHandle)

**功能：**

释放nSQLHandle参数指定的句柄资源。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

nSQLHandle：SQLInsertPrepare()产生的句柄，整型。

**返回值：**

整型

**调用格式：**

SQLInsertEnd(DeviceID, SQLHandle);

**例如：**

SQLInsertPrepare(DeviceID,"KingTable", "Bind1",
SQLHandle);   //准备句柄

SQLInsertExecute(DeviceID,"Bind1", SQLHandle);

SQLInsertExecute(DeviceID,"Bind1", SQLHandle);

SQLInsertExecute(DeviceID,"Bind1", SQLHandle);  //连续执行三次插入操作

SQLInsertEnd(DeviceID, SQLHandle);   //释放句柄

---

命令语言函数速查手册

### 25、SQLLoadStatement(int nConnectID, string strOutputFile)

**功能：**

读取包含在文件中的语句。类似于SQLSetStatement()函数。此函数也可以用SQLAppendStatement()附加语句。每个文件只能包含一个指令。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strOutputFile：文件名及其路径，字符串型。

**返回值：**

整型

**调用格式：**

SQLLoadStatement(DeviceID, "OutputFile");

**例如：**

SQLLoadStatement(DeviceID, "D:\KingSCADA\SQL.txt");

在文件SQL.txt中，包含以下信息：

Select ColumnName from TableName where ColumnName>100;

---

命令语言函数速查手册

### 26、SQLPrepareStatement(int nConnectID, IntTag nSQLHandle)

**功能：**

为SQLSetStatement()
或SQLLoadStatement()和SQLAppendStatement()指定的语句，返回句柄。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

nSQLHandle：产生的句柄，整型。

**返回值：**

整型

**调用格式：**

调用格式详见SQLSetParamTime函数。

---

命令语言函数速查手册

### 27、SQLRollback(int nConnectID)

**功能：**

撤消最近的位于SQLTransact()后的尚未"提交"的指令。

**参数：**

nConnectID---- SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLRollback(DeviceID);

**例如：**

SQLTransact(DeviceID);

SQLInsertPrepare(DeviceID, "kingview", "bind1",
handle);

SQLInsertExecute(DeviceID, "bind1", handle);

SQLInsertEnd(DeviceID, handle);

/\*如果这时执行SQLCommit(DeviceID)，以上语句将执行。\*/

SQLRollback(DeviceID);

/\*撤消SQLTransact()后的语句。\*/

***注：***

***1.******尽量避免在画面脚本和全局脚本中同时使用事务处理******;***

***2.*** ***合理调度脚本，避免在执行事务时有其他脚本对相同的表格做修改******(******比如插入、删除、更新等******)******，特别是定时脚本******;***

***3.*** ***尽量少用事务处理，可使用其他******SQL******函数配合实现这项功能******;***

---

命令语言函数速查手册

### 28、SQLSetParamChar(int nSQLHandle, int nParaNumber, string strParaValue, int nMaxLen)

**功能：**

以指定的参数为指定的字符串赋值。

**参数：**

NSQLHandle：由SQLPrepareStatement()函数准备的句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

strParaValue：赋予的值，字符串型。

nMaxLen：参数相关列的最大长度，整型。

**返回值：**

整型

**调用格式：**

SQLSetStatement(ConnectionID, "select \*
from kingview where Name=?");

SQLPrepareStatement(ConnectionID, handle);

SQLSetParamChar
(handle, 1, "var1"，5);

将SQLSetStatement函数的第二个参数中的？赋予var1值

---

命令语言函数速查手册

### 29、SQLSetParamDate(int nSQLHandle, int nParaNumber, string strParaValue)

**功能：**

为指定的字符型参数赋日期值。

**参数：**

nSQLHandle：由SQLPrepareStatement()函数准备的句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

strParaValue：赋给的值，字符串型。

**返回值：**

整型

**调用格式：**

SQLSetStatement(ConnectionID, "select \*
from kingview where Date=?");

SQLPrepareStatement(ConnectionID, handle);

SQLSetParamDate (handle, 1, "2009-12-01");//日期格式规定：%d-%d-%d

将SQLSetStatement函数的第二个参数中的？赋予2009-12-01值

---

命令语言函数速查手册

### 30、SQLSetParamTime(int nSQLHandle, int nParaNumber, string strParaValue)

**功能：**

为指定的字符型参数赋时间值。

**参数：**

nSQLHandle：由SQLPrepareStatement()函数准备的句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

strParaValue：赋给的值(可以是KingSCADA量)，字符串型。

**返回值：**

整型

**调用格式：**

SQLSetStatement(ConnectionID,
"select \* from kingview where Time=?");

SQLPrepareStatement(ConnectionID,
handle);

SQLSetParamTime (handle, 1,
"14:59:00");//时间格式规定：%d:%d:d

将SQLSetStatement函数的第二个参数中的？赋予14:59:00值

---

命令语言函数速查手册

### 31、SQLSetParamDatetime(int nSQLHandle, int nParaNumber, string strParaValue, int nPrecision)

**功能：**

为指定的字符型参数赋日期和时间值。

**参数：**

nSQLHandle：由SQLPrepareStatement()函数准备的句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

strParaValue：赋给的值，字符串型。

nPrecision：为要赋值的字符型参数设置一定的长度，设定的值可以大于或等于其字符串的长度。

**调用格式：**

SQLSetStatement(ConnectionID, "select \*
from kingview where DateTime=?");

SQLPrepareStatement(ConnectionID, handle);

SQLSetParamDatetime (handle, 1, "2009-12-01 11:30:00",20);//日期时间格式规定：%d-%d-%d %d: %d: %d

将SQLSetStatement函数的第二个参数中的？赋予2009-12-01 11:30:00值

---

命令语言函数速查手册

### 32、SQLSetParamDecimal(int nSQLHandle, int nParaNumber, string strParaValue, int nPrecision, int nScale)

**功能：**

为指定的整型参数赋十进制值。

**参数：**

nSQLHandle：由SQLPrepareStatement()函数准备的句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

strParaValue：赋给的值(可以是KingSACDA3.0变量)，双精度。

nPrecision：为要赋值的参数设置一定的长度，设定的值可以大于或等于其字符串的长度。如果是浮点型数值，则此长度包括整数部分的长度与小数点之后的长度，整型。

nScale：指的是浮点型数值小数点之后的长度，整型。

**返回值：**

整型

**调用格式：**

SQLSetStatement(ConnectionID, "select \* from kingview where
Value=?");

SQLPrepareStatement(ConnectionID, handle);

SQLSetParamDecimal (handle, 1, "2.14",3,2);

为SQLSetStatement函数的第二个参数中的？赋值2.14

---

命令语言函数速查手册

### 33、SQLSetParamFloat(int nSQLHandle, int nParaNumber, double fParaValue)

**功能：**

为指定的浮点型参数赋值。

**参数：**

nSQLHandle：由SQLPrepareStatement()函数准备的句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

fParaValue：赋予的值，双精度。

**返回值：**

整型

**调用格式：**

SQLSetStatement(ConnectionID, "select \* from kingview where
highth=?");

SQLPrepareStatement(ConnectionID, handle);

SqlSetParamFloat(handle, 1, var1);

将SQLSetStatement函数的第二个参数中的？赋予var1变量的值

---

命令语言函数速查手册

### 34、SQLSetParamInt(int nSQLHandle, int nParaNumber, int  nParaValue)

**功能：**

为指定的整数型参数赋值。

**参数：**

nSQLHandle：由SQLPrepareStatement()函数准备的句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

nParaValue：赋给的值，整型。

**返回值：**

整型

**调用格式：**

SQLSetParamInt(SQLHandle,ParameterNumber, ParameterValue);

**例如：**

SQLSetStatement(ConnectionID, "select \* from kingview where
agg=?");

SQLPrepareStatement(ConnectionID, handle);

SqlSetParamInt(handle, 1, var2);

将SQLSetStatement函数的第二个参数中的？赋予var2变量的值

---

命令语言函数速查手册

### 35、SQLSetParamNull((int nSQLHandle, int nParaNumber, int nParaType, int nPrecision, int nScale)

**功能：**

把指定的参数赋成空值。

**参数：**

NSQLHandle：由SQLPrepareStatement()函数准备的句柄句柄，整型。

nParaNumber：语句中参数出现的序号，整型。

nParaType：可使用下列值：

0：字符串

1：日期时间

2：整型

3：长整型

4：单精度

5：双精度

6：十进制小数

nPrecision：默认为0

nScale：默认为0

**返回值：**

整型

***注：使用此函数时需要被查询数据库支持空值******Null******的比较，具体配置方式可参阅相关数据库。***

---

命令语言函数速查手册

### 36、SQLSetStatement(int nConnectID, string strSqlSatement)

**功能：**

启动一个SQL语句缓存区。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strSqlSatement：SQL语句，字符串型。

**返回值：**

整型

**例****1****：**

SQLSetStatement(DeviceID, "Select LotNo, LotName from
LotInfo");

**例****2****：**

SQLSetStatement(DeviceID, "select Speed from kingview");

SQLExecute(DeviceID, "BIND", 0);

上例中，没有SQLPrepareStatement()准备的语句调用，句柄设为0。

**例****3****：**

SQLSetStatement(DeviceID, "select Speed from kingview");

SQLPrepareStatement(DeviceID, handle);

SQLExecute(DeviceID, "BIND"，handle);

SQLClearStatement(DeviceID, handle);

---

命令语言函数速查手册

### 37、SQLTransact(int nConnectID)

**功能：**

定义了一组访问指令。这组指令暂不执行，直到用SQLCommit()函数提交执行。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

**返回值：**

整型

**调用格式：**

SQLTransact(DeviceID);

***注：***

***1******、尽量避免在画面脚本和全局脚本中同时使用事务处理***

***2******、合理调度脚本，避免在执行事务时有其他脚本对相同的表格做修改******(******比如插入、删除、更新等******)******，特别是定时脚本***

***3******、尽量少用事务处理，可使用其他******SQL******函数配合实现这项功能***

---

命令语言函数速查手册

### 38、SQLUpdate(int nConnecdtID, string strTableName, string strBindName, string strCondition)

**功能：**

使用KingSCADA的变量值修改数据库中的记录。

**参数：**

NconnectID：SQLConnct()产生的连接号，整型。

strTableName：数据库表格名称，字符串型。

strBindName：记录体名称，字符串型。

strCondition：SQL语句，字符串型。

**返回值：**

整型

**调用格式:**

SQLUpdate(DeviceID, "kingview", "bind1",
"agg=20");

用KingSCADA的当前变量值更新kingview表格中所有agg=20的行

---

命令语言函数速查手册

### 39、SQLUpdateCurrent(int nConnectID, string strTableName)

**功能：**

使用KingSCADA中的变量更新数据库中当前行的记录。

**参数：**

nConnectID：SQLConnct()产生的连接号，整型。

strTableName：数据库的表格名称，字符串型。

**返回值：**

整型

**调用格式:**

SQLUpdateCurrent(DeviceID, "kingview");

***注：该函数对支持可更改游标的关系型数据库有效，例如：******SQL Server******、******Oracle******，对不支持可更改游标的关系型数据库无效，例如：******Access******，******MySQL******。***

---

命令语言函数速查手册

### 40、ManageDsn()

**功能：**

弹出数据源配置对话框

**参数：**

无

**返回值：**

整型

**调用格式：**

ManageDsn ();

---

命令语言函数速查手册

### SQL函数返回值列表：

|  |  |  |
| --- | --- | --- |
| 返回值代码 | 返回结果字符串 | 描述 |
| 0 | Success! | 函数执行成功 |
| -2 | Memory Out! | 内存溢出 |
| -3 | Time Out! | 超时 |
| -5 | BindParam Failed! | 绑定参数失败 |
| -6 | StringParam is NULL! | 字符串参数为空 |
| -7 | Database Connect Failed! | 数据库连接失败 |
| -8 | Execute failed! | 执行失败，未知原因的错误 |
| -9 | Invalid Device! | 无效的DeviceID |
| -10 | Invalid Selector! | 无效的查询语句 |
| -11 | Invalid Tag! | 无效的变量 |
| -12 | Invalid BindList! | 无效的记录体 |
| -13 | Invalid TableTemplate! | 无效的表格模板 |
| -14 | Invalid Statement! | 无效的SQL语句，无效的句柄 |
| -15 | Invalid Table Name! | 无效的表名 |

---

命令语言函数速查手册

## 五、字符串函数

---

命令语言函数速查手册

### 1、StrASCII(string Str)

**功能：**

此函数返回某一指定的字符串变量首字符的ASCII值。

**参数：**

Str：字母表顺序的字符或KingSCADA字符串变量，字符串型。

**返回值：**

返回值为Int型，即为首字符的ASCII码

**例****1****：**

StrASCII("A");   //返回 65

**例****2****：**

StrASCII("A Mixer is
Running");   //返回 65

**例****3****：**

StrASCII("a mixer is
running");    //返回 97

---

命令语言函数速查手册

### 2、StrChar(int nCode)

**功能：**

此函数返回某一指定ASCII码所对应的字符。

**参数：**

nCode：ASCII码或KingSCADA字符串变量，字符串型。

**返回值：**

返回值为ASCII码对应的字符

---

命令语言函数速查手册

### 3、StrFromInt(int nInteger, int nbase)

**功能：**

此函数将一整数值转换为另一进制下的字符串表示。

**参数：**

nInteger：要转换的数

nbase：用来转换的进制，取值范围：2-63，常用的是2、8、10、16

**返回值：**

字符串型

**调用格式：**

MessageResult=StrFromInt(Integer,Base);

**例****1****：**

StrFromInt(26, 2);   //返回"11010"

**例****2****：**

StrFromInt(26, 8);   //返回 "32"

**例****3****：**

StrFromInt(26, 16);  //返回"1A"。

---

命令语言函数速查手册

### 4、StrFromReal(double real, int Precision, string strType)

**功能：**

此函数将一实数值转换成字符串形式，该字符串以浮点数计数制表示或以指数计数制表示。

**参数：**

real：要转化为字符串型的实数值，浮点型。

Precision：指定要显示多少个小数位，整型。

strType：确定显示方式，可为以下字符之一：

"f"：按浮点数显示

"e"：按小写"e"的指数制显示。

"E"：按大写"E"的指数制显示。

**返回值：**

字符串型

**例****1****：**

StrFromReal(263.355,
2,"f");   //返回
"263.36"

**例****2****：**

StrFromReal(263.355,
2,"e");   //返回
"2.63e2"

**例****3****：**

StrFromReal(263.55,
3,"E");    //返回
"2.636E2"

---

命令语言函数速查手册

### 5、StrFromTime(int Time, int nStrType)

**功能：**

将时间秒数转换为时间字符串。

**参数：**

Time：从1970年到指定时间的整数秒值。

nStrType：返回时间字符串的格式：

1-----返回"2010-05-06
07:06:03"格式的日期时间字符串。

2-----返回"2010-5-6 7:6:3"格式的日期时间字符串。

3---返回Windows 控制面板相同格式的日期字符串。

4----返回Windows 控制面板相同格式的时间字符串。

5----返回Windows 控制面板相同格式的日期时间字符串。

**返回值：**

字符串型。

---

命令语言函数速查手册

### 6、StrInStr(string strText, string strSearch, int nStartPos, int nCaseSens)

**功能：**

此函数返回strSearch在strText中第一次出现的位置。

**参数：**

strText：源文本内容。

strSearch：要查找的文本内容

nStartPos：查找位置，即从strText中的哪个位置开始查找，整型

nCaseSens：查找的对象是否区分大小写。0：不区分，1：区分

**返回值：**

整型

**调用格式：**

StrInStr("The mixer is running",
"mix", 1, 0);     //返回 5

StrInStr("Today is Thursday",
"day", 1, 0);       //返回 3

StrInStr("Today is Thursday",
"day", 10, 0);      //返回 15

StrInStr("Today is Veteran's
Day", "Day", 1, 1);   //返回 20

StrInStr("Today is Veteran's
Day", "Night", 1, 1);  //返回 0。

---

命令语言函数速查手册

### 7、StrLeft(string strText, int nCount)

**功能：**

此函数返回指定字符串前若干个字符。

**参数：**

strText：要截取的文本，字符串类型

nCount：要截取的字符个数。若nCount置为0，则返回全部字符串，整型

**返回值：**

字符串型

**调用格式：**

StrLeft("The Control Pump is On",
3);   //返回 "The"

StrLeft("Pump 01 is On",
4);           //返回 "Pump"

StrLeft("Pump 01 is On",
96);          //返回 "Pump 01 is On"

StrLeft("The Control Pump is On",
0);   //返回 "The Control Pump is On"。

---

命令语言函数速查手册

### 8、StrLen(string strText)

**功能：**

此函数返回指定字符串的长度。

**参数：**

strText：指定返回长度的文本，字符串类型

**返回值：**

整型

**调用格式：**

StrLen("Twelve
percent");    //返回 14

StrLen("12%");            
//返回 3

StrLen("The end. [CR]");     //返回10，[CR] 是回车符- ASCII 13。

---

命令语言函数速查手册

### 9、StrLower(string strText)

**功能：**

此函数将指定文字中的所有大写字母转换为小写字母。小写字母、标号、数字和其它特殊字符将不受影响。

**参数：**

strText：实际文本字符串或字符串变量名，字符串型。

**返回值：**

字符串型

**调用格式：**

StrLower("TURBINE");        
//返回 "turbine"

StrLower("22.2 Is The
Value");   //返回 "22.2 is the
value."

---

命令语言函数速查手册

### 10、StrMid(string strText, int nPos, int nCount)

**功能：**

此函数可以获得指定字符串从指定位置开始的若干个字符

**参数：**

strText：要截取的文本，字符串型。

nPos：截取的开始位置，从1开始，整型。

nCount：截取的个数，整型。

**返回值：**

字符串型

**调用格式：**

StrMid("The Furnace is
Overheating",5,7,);     //返回 "Furnace"

StrMid("The Furnace is
Overheating",13,3);     //返回 "is "

StrMid("The Furnace is
Overheating",16,50);    //返回
"Overheating"

---

命令语言函数速查手册

### 11、StrReplace(string strText, string strSearch, string strReplace, int nCaseSens, int nNumToReplace, int nMatch)

**功能：**

此函数替换或改变所提供字符串的指定部分。使用此函数能获取字符串变量并替换字符、单词或短语。

**参数：**

strText：要修改的原始文本，字符串类型

strSearch：替换的源文本，字符串类型

strReplace：替换的目的文本，字符串类型

nCaseSens：是否区分大小写(0=不区分，1=区分)

nNumToReplace：确定要替换的次数(0=全部)

nMatch：是否要全字匹配。(0=不全字匹配，1=全字匹配)

**返回值：**

字符串型

**调用格式：**

StrReplace("In From
Within","In","Out",0,1,0); //返回 "Out From Within"  (只替换第一个)

StrReplace("In From
Within","In","Out",0,0,0) ; // 返回 "Out From WithOut"  (全部替换)

StrReplace("In From
Within","In","Out",1,0,0) ; // 返回 "Out  From Within"  (大小写匹配的全部替换)

StrReplace("In From
Within","In","Out",0,0,1) ; // 返回 "Out From Within"  (全字全部替换)

对于一些特殊字符如：@#$%&\*()，函数将它们视为分隔符，分隔符也可以作为要替换的字符

**例如：**

StrReplace("abc#","abc","1234",0,1,1); //执行替换操作

StrReplace("abc#","ab","1234",0,1,1); //不执行替换操作

StrReplace("abc#","abc#","1234",0,1,1); //执行替换操作

StrReplace("#","#","1234",0,1,1); //执行替换操作

---

命令语言函数速查手册

### 12、StrRight(string strText, int nCount)

**功能：**

此函数返回指定字符串变量的最末端(或最右)若干个字符。

**参数：**

strText:指定字符串文本或字符串变量名。

nCount:要返回最右端字符的个数。若nCount为0，则返回全部字符串。

**返回值：**

字符串型

**调用格式：**

StrRight("The Pump is On",
2);   //返回 "On"

StrRight("The Pump is On",
5);   //返回 "is On"

StrRight("The Pump is On",
87);  //返回 "The Pump is On"

StrRight("The Pump is On",
0);   //返回 "The Pump is On"。

---

命令语言函数速查手册

### 13、StrSpace(int nNumSpaces)

**功能：**

此函数在字符串变量中或表达式中产生一个空格串。

**参数：**

nNumSpaces：数字或整型变量，指定产生的空格数。

**返回值：**

字符串型

**调用格式：**

"Pump" + StrSpace(1) +
"Station" 的结果是："Pump Station"。

---

命令语言函数速查手册

### 14、StrToInt(string strText)

**功能：**

此函数将一个由数字组成的字符串转换成一个能用作数学计算的整数值。

**参数：**

strText：要转换的文本，字符串类型。

**返回值：**

整型。

---

命令语言函数速查手册

### 15、StrToReal(string strText)

**功能：**

此函数将一个由数字组成的字符串转换成一个能用于数字计算的实数值。

**参数：**

strText：函数将处理的字符串

**返回值：**

双精度。

---

命令语言函数速查手册

### 16、StrToTime(string strDateTime)

**功能：**

此函数将一个时间字符串转换成一个秒数。

**参数：**

strDateTime：时间字符串

**返回值：**

长整型。从1970年到指定时间的整数秒值。

**调用格式：**

\\local\int1=/\*int\*/ StrToTime("2012-02-03
12:02:03");

返回值int1为1328241723

---

命令语言函数速查手册

### 17、StrTrim(string strText, int nTrimType)

**功能：**

此函数删除字符串中无用的空格。

**参数：**

strText：要处理的文本。

nTrimType：删除方式，可为下列类型之一：

1----删除首部空格(第一个非空格字符的左边)。

2----删除尾部空格(最后一个非空格字符的右边)。

3----删除单词间单个空格外的多余空格。

**返回值：**

字符串型。

---

命令语言函数速查手册

### 18、StrType(string strText, int nTestType)

**功能：**

此函数检测字符串的首字符以确定其是否为某一类型。

**参数：**

strText：函数将处理的文本，字符串类型

nTestType：确定为下列类型之一：

1----字母数字符 
('A'-'Z', 'a-z' 和 '0-9')

2----数字符 
('0'- 9')

3----字母  ('A-Z'
和 'a-z')

4----大写字母 ('A'-'Z')

5----小写字母 ('a'-'z')

6----标点字符
(0x21-0x2F)

7----ASCII 字符 (0x00
- 0x7F)

8----十六进制字符
('A'-'F' 或 'a'-'f' 或
'0'-'9')

9----可打印字符 
(0x20-0x7E)

10----控制字符 
(0x00-0x1F 或 0x7F)

11----空白符(0x09-0x0D
or 0x20)

**返回值：**

整型,0或1。若Text中首字符是TestType指定的类型，则StrType()函数将返回1，否则返回0，只检测变量的首字符。

**调用格式：**

StrType("ACB123",1);   //返回 1

StrType("ABC123",5);   //返回 0。

***注：若******Text******中首字符是由******TestType******指定的类型，则******StrType()*** ***函数将返回给******DiscreteResult******一个正值。正如在其它函数中，单个字符被检测或影响一样，若******StrType()*** ***函数的字符串变量含有一个以上字符时，只有变量的首字符将被检测。***

---

命令语言函数速查手册

### 19、StrUpper(string strText)

**功能：**

此函数将指定字符串中所有的小写字符转换成大写字符。大写字符、符号、数字以及其它特殊字符将不受影响。

**参数：**

strText：函数将处理的文本，字符串类型

**返回值：**

字符串型

---

命令语言函数速查手册

### 20、Text(double dValue, string strFormat)

**功能：**

此函数将变量(整型或实型)的值显示成一个基于指定strFormat格式的模拟值。

**参数：**

dValue：要转换的变量或数值。

strFormat：转换所使用的格式

**返回值：**

字符串型

**调用格式：**

\\local\string1=Text(\\local\int1,
"#.00");  \local\string1是一个字符串型变量名，\\local\int1为一个整型或者实型的变量名。
"#0.00"代表显示格式：

若\\local\int1=66, 则 \\local\string1=66.00。

若\\local\int1=22.269, 则 \\local\string1=22.27。

若\\local\int1=9.999, 则 \\local\string1=10.00。

---

命令语言函数速查手册

### 21、StrConcat (string param1,...)

**功能：**

此函数将多个字符串合成一个字符串。

**参数：**

param1：指定的参数，字符串型

**返回值：**

字符串型

---

命令语言函数速查手册

## 六、日期时间函数

---

命令语言函数速查手册

### 1、ConvertTimeToSecond(int nYear, int nMonth, int nDay, int nHour, int nMinute, int nSecond, bool bMode)

**功能：**

此函数将指定的时间格式(年、月、日、时、分、秒)转换为以秒为单位的长整型数，转换的时间基准是UTC(格林威治)1970年1月1日00:00:00。例：北京为东八区，那么转换的时间基准为1970年1月1日8:00:00。

**参数**：

nYear：年，整型，此值必须介于1970至2038之间

nMonth：月，整型，此值必须介入1至12之间

nDay：日，整型，此值必须介入1至31之间

nHour：小时，整型，此值必须介入0至23之间

nMinute：分钟，整型，此值必须介入0至59之间

nSecond：秒，整型，此值必须介入0-59之间

bMode：0：按本地时区输出，1：按格林威治时区输出

**返回值：**

整型

***注：***

***1******、该函数返回值是整数，在定义返回值变量的时候需要定义为整型，不要定义为浮点型，否则会导致数据不准确;***

***2******、建议用户定义返回值变量的时候，将最大值设为：2147483647(C******语言允许的整型变量最大值)******，这样可以保证在2038-01-19 11:14:07******以前的转换秒数都能返回给该变量。***

---

命令语言函数速查手册

### 2、GetCustomDateString(int nYear, int nMonth, int nDay, int bMode)

**功能：**

根据用户给出的年、月、日整型数值，返回日期字符串

**参数：**

nYear：年份，整型。

nMonth：月份，整型。

nDay：日份，整型。

nType：显示格式，整型。可以取如下数值之一：

0----表示返回的日期以YY/mm/dd格式显示

1----表示返回的日期以mm/dd/yy格式显示

2----表示返回的日期以YYYY/mm/dd格式显示

3----表示返回的日期以mm/dd格式显示

**返回值：**

字符串型

**调用格式：**

日期= GetCustomDateString($Year,$Month,$Day,1)   //返回12/03/09

---

命令语言函数速查手册

### 3、GetCustomTimeString(int nHour, int nMinute, int nSecond, int nType)

**功能：**

根据用户给出的时、分、秒整型数值，返回时间字符串

**参数：**

nHour：小时，整型

nMinute----分钟，整型

nSecond----秒，整型

nType----显示格式，整型。取值0或1

0----24小时制显示时间，比如
13：01：02。

1----12小时制显示时间并带标志，如下午某一时间显示为 01：01：02PM

**返回值：**

字符串型。

**调用格式：**

时间= GetCustomTimeString($Hour,$Minute,$Second,1);  //返回11:49:30AM

---

命令语言函数速查手册

### 4、GetLocalDateTimeFromSecond(int nDateTime, IntTag Year, IntTag Month, IntTag Day, IntTag Hour, IntTag Minute, IntTag Second)

**功能：**

根据用户给出的秒数值获得本地时间的时间值。

**参数：**

nDateTime：从1970年到现在时间的秒数值

Year：存放返回年的工程变量，整型

Month：存放返回月的工程变量，整型

Day：存放返回日的工程变量，整型

Hour：存放返回时的工程变量，整型

Minute：存放返回分钟的工程变量，整型

Second：存放返回秒数的工程变量，整型

**返回值：**

无返回值。

**调用格式：**

GetLocalDateTimeFromSecond(100,year,month,day,hour,minute,second);

其中，year、month、day、hour、minute、second是在工程中定义的整型变量。

执行该函数后返回：

Year：1970

Month：01

Day：01

Hour：08

Minute：01

Second：40

---

命令语言函数速查手册

### 5、GetLocalYearFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得本地时间的年值

**参数：**

nDateTime：从1970年到现在时间的整数秒值，整型

**返回值：**

整型

**调用格式：**

GetLocalYearFromSecond (100);    //返回1970

---

命令语言函数速查手册

### 6、GetLocalMonthFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得本地时间的月值

**参数：**

nDateTime：从1970年到现在时间的整数秒值，整型

**返回值：**

整型

**调用格式：**

GetLocalMonthFromSecond (100);    //返回1

---

命令语言函数速查手册

### 7、GetLocalDayFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得本地时间的日值

**参数：**

nDateTime：从1970年到现在时间的整数秒值，整型

**返回值：**

整型

**调用格式：**

GetLocalDayFromSecond(100);   
//返回1

---

命令语言函数速查手册

### 8、GetLocalHourFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得本地时间的小时值

**参数：**

nDateTime：从1970年到现在时间的整数秒值，整型

**返回值：**

整型

**调用格式：**

GetLocalHourFromSecond (100);    //返回8

---

命令语言函数速查手册

### 9、GetLocalMinuteFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得本地时间的分钟值

**参数：**

nDateTime：从1970年到现在时间的整数秒值，整型

**返回值：**

整型

**调用格式：**

GetLocalMinuteFromSecond (100);    //返回1

---

命令语言函数速查手册

### 10、GetLocalSecondFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得本地时间的秒值

**参数：**

nDateTime：从1970年到现在时间的整数秒值，整型

**返回值：**

整型

**调用格式：**

GetLocalSecondFromSecond (100);    //返回40

---

命令语言函数速查手册

### 11、GetSystemtimeDateString()

**功能：**

根据系统当前的年、月、日整型数值，返回日期字符串

**参数：**

无

**返回值：**

字符串型

**调用格式：**

GetSystemtimeDateString();   
//返回2009-12-3

---

命令语言函数速查手册

### 12、GetSystemtimeTimeString(int bMsec)

**功能：**

根据系统当前的时、分、秒整型数值，返回时间字符串

**参数：**

bMsec：返回的字符串中是否包含毫秒值，0：不包含毫秒，1：包含毫秒

**返回值：**

字符串型

**调用格式：**

GetSystemtimeTimeString(1);   
//返回16:21:43 841

---

命令语言函数速查手册

### 13、GetSystimeYear()

**功能：**

获取系统当前时间的年值

**参数：**

无

**返回值：**

整型

---

命令语言函数速查手册

### 14、GetSystimeMonth()

**功能：**

获取系统当前时间的月值

**参数：**

无

**返回值：**

整型

---

命令语言函数速查手册

### 15、GetSystimeDay()

**功能：**

获取系统当前时间的日值

**参数：**

无

**返回值：**

整型

---

命令语言函数速查手册

### 16、GetSystimeHour()

**功能：**

获取系统当前时间的小时值

**参数：**

无

**返回值：**

整型

---

命令语言函数速查手册

### 17、GetSystimeMinute()

**功能：**

获取系统当前时间的分钟值

**参数：**

无

**返回值：**

整型

---

命令语言函数速查手册

### 18、GetSystimeSecond()

**功能：**

获取系统当前时间的秒值

**参数：**

无

**返回值：**

整型

---

命令语言函数速查手册

### 19、GetSystimeMSec()

**功能：**

获取系统当前时间的毫秒值

**参数：**

无

**返回值：**

整型

---

命令语言函数速查手册

### 20、GetSystimeWeek()

**功能：**

获取系统当前时间的周值

**参数：**

无

**返回值：**

整型

0：星期天

1：星期一

2：星期二

3：星期三

4：星期四

5：星期五

6：星期六

---

命令语言函数速查手册

### 21、GetUTCDateTimeFromSecond(int nDateTime, IntTag Year, IntTag Month, IntTag Day, IntTag Hour, IntTag Minute, IntTag Second)

**功能：**

根据用户给出的秒数获得格林尼治标准时间值。

**参数：**

nDateTime：从1970年到现在时间的整数秒值

Year：存放返回年的工程变量，整型

Month：存放返回月的工程变量，整型

Day：存放返回日的工程变量，整型

Hour：存放返回时的工程变量，整型

Minute：存放返回分钟的工程变量，整型

Second：存放返回秒数的工程变量，整型

**返回值：**

无返回值。

**调用格式：**

GetUTCDateTimeFromSecond (100,year,month,day,hour,minute,second);

其中，year、month、day、hour、minute、second是在工程中定义的整型变量。

执行该函数后返回：

Year：1970

Month：01

Day：01

Hour：00

Minute：01

Second：40

---

命令语言函数速查手册

### 22、GetUTCYearFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得格林尼治时间的年值

**参数：**

nDateTime：需要获得格林尼治标准时间的秒数值，整型。

**返回值：**

整型

**调用格式：**

GetUTCYearFromSecond(100);   //返回1970

---

命令语言函数速查手册

### 23、GetUTCMonthFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得格林尼治时间的月值

**参数：**

nDateTime：需要获得格林尼治标准时间的秒数值，整型。

**返回值：**

整型

**调用格式：**

GetUTCMonthFromSecond (100);   //返回1

---

命令语言函数速查手册

### 24、GetUTCDayFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得格林尼治时间的日值

**参数：**

nDateTime：需要获得格林尼治标准时间的秒数值，整型。

**返回值：**

整型

**调用格式：**

GetUTCDayFromSecond (100);   //返回1

---

命令语言函数速查手册

### 25、GetUTCHourFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得格林尼治时间的时值

**参数：**

nDateTime：需要获得格林尼治标准时间的秒数值，整型。

**返回值：**

整型

**调用格式：**

GetUTCHourFromSecond (100);   //返回0

---

命令语言函数速查手册

### 26、GetUTCMinuteFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得格林尼治时间的分钟值

**参数：**

nDateTime：需要获得格林尼治标准时间的秒数值，整型。

**返回值：**

整型

**调用格式：**

GetUTCMinuteFromSecond (100);   //返回1

---

命令语言函数速查手册

### 27、GetUTCSecondFromSecond(int nDateTime)

**功能：**

根据给出的秒数值获得格林尼治时间的秒值

**参数：**

nDateTime：需要获得格林尼治标准时间的秒数值，整型。

**返回值：**

整型

**调用格式：**

GetUTCSecondFromSecond (100);   //返回40

---

命令语言函数速查手册

### 28、TimeSynchNetNode (string strNetNodeIP, int nNetNodePort)

**功能：**

给定一个IP地址用来获取该计算机的系统时间ITime，并将本地计算机时间改为ITime。

**参数：**

strNetNodeIP：被校时的网络节点IP地址(用于校准时间，参考时间)

nNetNodePort：被校时KS的实时库端口

**返回值：**

整型

0, 校时成功

1, 校时失败   

2, 无效IP地址：strNetNodeIP为空或不为IP地址

3, 获取本地服务失败

4, 创建通讯服务器失败

5, 网络连接失败

6, 获取指定IP计算机系统时间失败

7, 预留

***注意：指定IP******地址的计算机中需先启动SCADA******运行系统(******只需启动运行系统，不需要打开画面)******。***

---

命令语言函数速查手册

### 29、TransDateTimeByTimeZone（string strDateTime，string strTimeZone1，string strTimeZone2）

**功能：**

时区间时间的转换。

**参数：**

strDateTime：时间字符串，格式必须是sql的标准时间格式 YYYY-MM-DD HH:MM:SS。

strTimeZone1：strDateTime对应的时区。格式必须是"local"、"-12" ~
"12"。"local"：获取本机时区，"-12" ~
"12"：时区编号，UTC时区的编号是："0"。

strTimeZone2：要转换后的时区。格式必须是"local"、"-12" ~
"12"。"local"：获取本机时区，"-12" ~
"12"：时区编号，UTC时区的编号是："0"。

**返回值：**

字符型。将StrDateTime按iTimeZone1所设置的时区，转换成iTimeZone2时区的时间字符串，格式YYYY-MM-DD HH:MM:SS。参数非法，则返回空字符串。

---

命令语言函数速查手册

### 30、GetDateTime（）

**功能：**

获取返回系统目前的日期与时间。

**参数：**

无

**返回值：**

字符型：返回日期时间字符串

**调用格式：**

String GetDateTime（）；

返回：2013-5-29 12:01:45

---

命令语言函数速查手册

### 31、DateAdd（String strInterval，int nNumber，String strDate）

**功能：**

给参数strDate以strInterval指定方式上加Number指定的值，返回字符串型的日期时间值。

**参数：**

strInterval：指定方式的字符串。

"Y"－年

"M"－月

"D"－天

Number：整形数值。

strDate：日期字符串。

**返回值：**

字符型：表示日期的字符串。

**调用格式：**

String DateAdd("D",2,
"2004-10-15")；

向2004-10-15字符串指定的日值上加2，返回：2004-10-17

String DateAdd("M",3
, "2004-10-15")；

向2004-10-15字符串指定的月值上加3，返回：2005-01-15

String DateAdd("Y",1
, "2004-10-15")；

向2004-10-15字符串指定的年值上加1，返回：2005-10-15

---

命令语言函数速查手册

### 32、DateDiff (String strInterval, String strDate1, String strDate2)

**功能：**

以strInterval指定的方式，返回String strDate2与String strDate1两个日期之间的差值（
String strDate2-String strDate1）。

**参数：**

strInterval：指定方式的字符串。

"Y"－年

"M"－月

"D"－天

String strDate1：日期1字符串，格式：YYYY－MM－DD。

String strDate2：日期2字符串，格式：YYYY－MM－DD。

**返回值：**

两个日期值之差的整数，如果String
strDate2的日期早于String
strDate1的日期，则返回负数。

**调用格式：**

DateDiff ("M","2006-10-11","2006-11-01")；

返回1

---

命令语言函数速查手册

### 33、DatePart (String strInterval, String strDate)

**功能：**

返回日期strDate 中，strInterval 指定方式所对应的整数值。

**参数：**

strInterval：指定方式的字符串。

"Y"－年

"M"－月

"D"－天

String strDate：日期字符串，格式：YYYY－MM－DD。

**返回值：**

指定方式的整数值。

**调用格式：**

DatePart ("M","2013-5-29")
；

返回5

---

命令语言函数速查手册

### 34、SetLoaclSystemTime(int nYear, int nMonth, int nDay, int nHour, int nMinute, int nSecond);

**功能：**

设置本机的系统时间。

**参数：**

nYear：年

nMonth：月

nDay：日

nHour：时

nMinute：分

nSecond：秒

**返回值：**

整型

**调用格式：**

result =SetLoaclSystemTime(2015, 4, 19, 12, 53,
53);//设置本机系统时间为2015-4-19 12:53:53

---

命令语言函数速查手册

## 七、数学函数

---

命令语言函数速查手册

### 1、Abs(double dbNumber)

**功能：**

此函数用于获得指定参数的绝对值。

**参数：**

dbNumber：要绝对值的参数，双精度型。

**返回值：**

双精度

**例****1****：**

Abs(14);     //返回值为14

**例****2****：**

Abs(-7.5);    //返回值为7.5

**例****3****：**

Abs(距离);  
//返回内存模拟变量"距离"的绝对值。

---

命令语言函数速查手册

### 2、ArcCos(double dbNumber)

**功能：**

此函数用于获得指定参数的反余弦值

**参数：**

dbNumber：要获得反余弦值的参数，双精度型，取值范围在[-1，1]之间

**返回值：**

双精度型，0~180。

**例****1****：**

ArcCos(1);      //此函数返回值为0

**例****2****：**

ArcCos(temp);   //此函数返回变量"temp"的反余弦值。

---

命令语言函数速查手册

### 3、ArcSin(double dbNumber)

**功能：**

此函数用于获得指定参数的反正弦值

**参数：**

dbNumber：要获得反正弦值的参数，双精度型，取值范围在[-1，1]之间

**返回值：**

双精度型，-90~90。

**例****1****：**

ArcSin(1);      //此函数返回值为90

**例****2****：**

ArcSin(temp);   //此函数返回变量"temp"的反正弦值。

---

命令语言函数速查手册

### 4、ArcTan(double dbNumber)

**功能：**

此函数用于获得指定参数的反正切值

**参数：**

dbNumber：要获得反正切值的参数，双精度型

**返回值：**

双精度型，-90~90。

**例****1****：**

ArcTan(1);      
//此函数返回值为45

**例****2****：**

ArcTan (temp);   //此函数返回变量"temp"的反正切值

---

命令语言函数速查手册

### 5、Average(double Param1,….)

**功能：**

此函数用于获得指定参数的平均值，可以是KingSCADA报表表格的多个单元格的平均值，或是多个变量的平均值，参数个数范围：1-1000

**参数：**

Param1：要获得平均值的参数

**返回值：**

双精度型

**例1****：**

=Averge(‘a1’,
‘b2’, ‘r10’);    //对报表控件中的任意单元格求平均值，结果显示在当前单元格中

**例2****：**

=Averge(‘b1:b10’);   
//对报表控件中连续的单元格求平均值，结果显示在当前单元格中

**例3****：**

Averge(tag1，tag2);    //求多个变量的平均值

---

命令语言函数速查手册

### 6、Cos(double dbNumber)

**功能：**

此函数用于获得指定参数的余弦值.

**参数：**

dbNumber----所要计算余弦值的数值，双精度型。

**返回值：**

双精度型。

**例****1****：**

Cos(90);      //返回值为0

**例****2****：**

Cos(temp);    //返回变量"temp"的余弦值。

---

命令语言函数速查手册

### 7、Exp(double dbNumber)

**功能：**

此函数获得以e为底的指定参数的指数值

**参数：**

dbNumber：表示多少次方，双精度型。

**返回值：**

双精度型。

**例****1****：**

Exp(1);       
//返回e1的计算值2.718

**例****2****：**

Exp(temp);     //计算e常量的temp次幂并返回计算结果。

---

命令语言函数速查手册

### 8、Int(double dbNumber)

**功能：**

此函数返回小于等于指定参数的最大整数

**参数：**

dbNumber：要获得最大整数的参数

**返回值：**

整型

**例****1****：**

Int(4.7);     //将返回 4

**例****2****：**

Int(-4.7);    //将返回 -5

---

命令语言函数速查手册

### 9、LogE(double dbNumber)

**功能：**

此函数用于获得指定参数的以e为底的对数值

**参数：**

dbNumber：要获得以e为底的对数的参数，双精度型。

**返回值：**

双精度型。

**例****1****：**

LogE(100);    //返回loge100计算值4.605

**例****2****：**

LogE(1);      //返回loge1计算值0

---

命令语言函数速查手册

### 10、LogN(double dbNumber, int nBase)

**功能：**

此函数获得指定参数以nBase为底的对数值

**参数：**

dbNumber：要获得以nBase为底对数值的参数，双精度型。

nBase：做底的整数，整型。

**返回值：**

双精度型。

**例****1****：**

LogN(8, 3);    //将返回 1.89279...

**例****2****：**

LogN(3, 7);    //将返回 0.564...

---

命令语言函数速查手册

### 11、Max(double param1,….)

**功能：**

此函数用于获得指定参数中的最大的一个数，其参数个数范围：1~1000

**参数：**

param1：指定的参数，双精度型

**返回值：**

双精度型

**例1****：**

Max(tag1，tag2);

**例2****：**

Max(Max(tag1,tag2),tag3)  
//返回tag1、tag2、tag3的最大数

---

命令语言函数速查手册

### 12、Min(double param1)

**功能：**

此函数用于获得指定参数中的最小的一个数，其参数个数范围：1~1000

**参数：**

param1：指定的参数，双精度型

**返回值：**

双精度型

**例1****：**

Min(tag1，tag2);

**例2****：**

Min(Min(tag1,tag2),tag3)  
//返回tag1、tag2、tag3的最小数

---

命令语言函数速查手册

### 13、PI()

**功能：**

此函数返回圆周率的值。

**参数：**

无

**返回值：**

双精度

**调用格式：**

PI( );   将返回 3.1415926...

---

命令语言函数速查手册

### 14、Pow(double x, double y)

**功能：**

此函数求得一模拟值或模拟变量的任意次幂。

**参数：**

x：底数，双精度型。

y：指数，双精度型。

**返回值：**

为x的y次幂，双精度。

**调用格式：**

Result=Pow(2,
3);      //函数调用后Result=8.0

---

命令语言函数速查手册

### 15、Sgn(double dbNumber)

**功能：**

此函数判别一个数值的符号(正、零或负)。

**参数：**

dbNumber：所需判别符号的数值，双精度型。

**返回值：**

整型。

1：正数

0：零

-1：负数

**例****1****：**

Sgn(425);    //将返回 1

**例****2****：**

Sgn(0);      //将返回 0

**例****3****：**

Sgn(-37.3);   //将返回 -1

---

命令语言函数速查手册

### 16、Sin(double dbNumber)

**功能：**

此函数获得指定参数的正弦值。

**参数：**

dbNumber：要获得正弦值的参数，双精度型。

**返回值：**

双精度型。

**例****1****：**

Sin(90);    此函数返回值为1

**例****2****：**

Sin(0);     此函数返回值为0

---

命令语言函数速查手册

### 17、Sqrt(double dbNumber)

**功能：**

此函数用于获得指定参数的平方根

**参数：**

dbNumber：要获得平方根的参数，双精度型。

**返回值：**

双精度型

**例****1****：**

Sqrt(0);        //返回值为0;

**例****2****：**

Sqrt(-1);       //返回值为-1;

**例****3****：**

Sqrt(10000);    //返回值为100;

---

命令语言函数速查手册

### 18、Sum(double param1,….)

**功能：**

此函数用于获得指定参数的和，可以是KingSCADA报表表格的多个单元格的和，或是多个变量的和，参数个数范围：1-1000

**参数：**

param1：要获得和的参数

**返回值：**

双精度型

**例1****：**

=Sum(‘a1’, ‘b2’,
‘r10’);    //对报表控件中的任意单元格求和，结果显示在当前单元格中

**例2****：**

= Sum
(‘b1:b10’);    //对报表控件中连续的单元格求和，结果显示在当前单元格中

**例3****：**

Sum (tag1，tag2);    //求多个变量的和

---

命令语言函数速查手册

### 19、Tan(double dbNumber)

**功能：**

此函数用于获得指定参数的正切值

**参数：**

dbNumber：要获得正弦值的参数，双精度型。

**返回值：**

双精度型。

**例****1****：**

Tan(45);    //返回值为1

**例****2****：**

Tan(0);     //返回值为0。

---

命令语言函数速查手册

### 20、Trunc(double dbNumber)

**功能：**

通过删去小数点右边部分的方式截取一个实数

**参数：**

dbNumber：要截取的参数，双精度型

**返回值：**

整型。

**例****1****：**

Trunc(4.3);     //返回 4

**例****2****：**

Trunc(-4.3);    //返回 –4

---

命令语言函数速查手册

## 八、系统函数

---

命令语言函数速查手册

### 1、ActivateApp(string strWindowsTitleName)

**功能：**

此函数用于激活正在运行的窗口应用程序，使之变为当前窗口。获得输入焦点。该函数也可配合函数SendKeys的使用，具体说明请详见SendKeys函数。

**参数：**

strWindowsTitleName：应用程序窗口标题，字符串型

**返回值：**

无返回值。

**调用格式：**

ActivateApp("Micrsoft
Excel-Book1");   //激活Microsoft Excel应用程序窗口

---

命令语言函数速查手册

### 2、displayMCI(string strCmdString, string strOption)

**功能：**

此函数提供了一个对多媒体设备的通用接口，用于进行多媒体播放的控制。

**参数：**

strCmdString：用于对多媒体进行控制的命令语言，字符串型。

strOption：要进行多媒体控制的对象，字符串型。

**返回值：**

无返回值

**例****1****：**

displayMCI("PLAYCD",
"3");

播放CD光盘中的第3支歌曲。

**例****2****：**

displayMCI("StopCD",
"");

停止播放CD，第二个参数不起作用

**例****3****：**

displayMCI("PlayMIDI",
"c:\midi.mid");

播放MIDI格式的背景音乐"c:\midi.mid"

**例****4****：**

displayMCI("PauseMIDI",
"");

暂停当前的背景音乐

**例****5****：**

displayMCI("ResumeMIDI",
"");

继续播放当前的背景音乐

***注：第一个参数取值为******ResumeMIDI******的时候，在某些情况下会出现问题，不建议用户使用。***

**例****6****：**

displayMCI("CloseMIDI", "");

停止播放当前的背景音乐

**例****7****：**

displayMCI("EjectCD",
"1");

打开光驱舱门

displayMCI("EjectCD",
"0");

关闭光驱舱门

***注：***

***1******、函数中的两个参数在使用时不区分大小写，即******"******PlayMidi******"******和******"******PLAYMIDI******"******执行结果是相同的******;***

***2******、只有当函数的第一个参数为******"PlayMidi"******时，第二个参数才是文件路径，文件路径长度不能超过******127******个字符，且路径中不能含有空格，其余各情况下均未使用第二个参数，只需要提供一个任意字符串******;***

---

命令语言函数速查手册

### 3、Exit(int nOption)

**功能：**

此函数退出KingSCADA运行系统

**参数：**

nOption：运行系统退出控制数，可以取下列数值之一：

0----退出KingSCADA运行系统

1----关机

2----重新启动Windows

**返回值：**

无返回值。

**调用格式：**

Exit(0);

---

命令语言函数速查手册

### 4、InfoAppActive(string strProgramName)

**功能：**

此函数测试一个应用程序是否为活动的。

**参数：**

strProgramName：要测试的应用程序的程序名，字符串型。

**返回值：**

true：应用程序正在运行

false：应用程序未运行

**调用格式：**

InfoAppActive("Excel.exe");

---

命令语言函数速查手册

### 5、InfoAppDir()

**功能：**

此函数用于获得当前正在运行的KingSCADA工程的路径

**参数：**

无

**返回值：**

字符串型

**调用格式：**

DemoPath=InfoAppDir();

---

命令语言函数速查手册

### 6、InfoAppTitle(string strProgramName)

**功能：**

此函数用于获得应用程序的标题名

**参数：**

strProgramName----需要获得标题的应用程序序名，字符串型。

**返回值：**

字符串型。

**例****1****：**

InfoAppTitle("calc.exe");   
//将返回 "Calculator"

**例****2****：**

InfoAppTitle("excel.exe");  
//将返回 "Microsoft Excel"

***注：要获得标题的应用程序必须运行着***

---

命令语言函数速查手册

### 7、InfoDisk(string strDriverName, int nInfoType)

**功能：**

此函数用于获得本地(或网络)磁盘驱动器信息

**参数：**

strDriveName：驱动器名称，字符串型，若该名称使用字符串变量，且该字符串变量包含多个字符，则只使用此变量的首字符。

nInfoType：信息类型，可为以下两个值之一：

1----返回磁盘驱动器的总空间数(以字节计)。

2----返回磁盘驱动器上可用的空闲空间数(以字节计)。

**返回值：**

整型。

**例****1****：**

InfoDisk("C",
1);    //将返回C盘总空间数，以字节计算

**例****2****：**

InfoDisk("C",
2);    //将返回C盘空闲空间数，以字节计算

---

命令语言函数速查手册

### 8、InfoFile(string strFileName, int nInfoType)

**功能：**

此函数用于获得指定文件或文件夹的有关信息

**参数：**

strFilename：要获得信息的文件或文件夹的名称，字符串类型

nInfoType：信息类型，可为以下值之一：

1----查找文件是否存在，若存在返回1，若不存在则返回0

2----文件大小(字节数)

3----文件日期/时间(自1970年1月1日起的相对秒数)

4----与文件名描述相匹配的文件数。仅当使用通配符查找并找到多个匹配的文件时，返回值大于1。

**返回值：**

整型。

**例****1****：**

InfoFile("D:\KingSCADA\KingSCADAView.exe",1);

**例****2****：**

InfoFile("D:\KingSCADA\\*.exe",
4);

---

命令语言函数速查手册

### 9、InfoResource(int nResourceType)

**功能：**

此函数返回各种系统资源值。

**参数：**

nResourceType：代表要监视的资源类型的整数，可为以下值之一：

1----返回GDI资源可用空闲空间的百分比。

2----返回USER资源可用空闲空间的百分比。

3----返回当前内存中空闲空间字节数。

4----返回当前正在运行的任务数。

**返回值：**

整型。

**例****1****：**

InfoResource(1);    //将返回GDI资源可用空闲空间百分比

**例****2****：**

InfoResource(2);    //将返回USER资源可用空闲空间百分比

**例****3****：**

InfoResource(3);    //将返回内存中空闲空间字节数

**例****4****：**

InfoResource(4);    //将返回任务数

***注：在******WIN NT*** ***下返回******GDI******和******USER******的资源可用空闲空间的百分比是一样的，与******WIN NT******系统有关。可用内存，以******k******字节计算***

---

命令语言函数速查手册

### 10、PlaySound(string strWaveFileName, int nMode)

**功能：**

此函数通过安装了Windows
wave音频设备驱动器的机器，播放\*.wav格式的声音文件。

**参数：**

strWaveFileName：声音文件名，或表示声音文件的字符串变量。声音文件需要在安装了wave形式音频设备驱动器的机器上播放。声音文件名前可加声音文件所在的目录，也可以不加。

声音文件目录的查找按以下顺序：strWaveFileName参数中列出的目录，当前KingSCADA工程目录，系统目录。若列出的目录和缺省目录中都找不到该声音文件，则不播放声音。

nMode：播放模式，可为下述之一：

0----停止播放声音，用于停止异步播放和重复播放

1----同步播放声音(按照调用PlaySound函数的顺序,依次完整的播放所有语音)

2----异步播放声音(如果当前正在异步播放一语音文件,再调用PlaySound会中止当前语音文件的播放)

3----重复播放声音直到下次调用PlaySound()函数为止

4----蜂鸣器报警

5----停止同步播放声音。当前声音完整播放，停止的是当前声音之后的声音。

**备注**：在ksp上，{ PlaySound函数的音频文件 需要放在exedb\kingclient\x86\linux\1.0.0.1\views\wavs这个路径下。“文件路径中的文件间隔符号只能用斜杠或者两个反斜杠”；

下面两个为正确的格式：

PlaySound("F:/desktop/ring/机场提示音.wav", 3);

PlaySound("F:\\desktop\\ring\\机场提示音.wav", 3); }

**返回值：**

无返回值。

**例****1****：同步播放声音**

PlaySound("a.wav",
1);     //文件路径为KingSCADA工程目录或系统目录

PlaySound("D:\a.wav",
1);    //指定文件路径。

**例2****：停止播放同步文件**

PlaySound("", 5);

**例3****：异步播放声音文件**

PlaySound("a.wav", 2);

PlaySound("D:\a.wav", 2);

**例4****：重复播放声音文件**

PlaySound("a.wav", 3);

PlaySound("D:\a.wav", 3);

**例5****：停止异步播放**

PlaySound("", 0);

---

命令语言函数速查手册

### 11、SendKeys(string strCmd)

**功能：**

此函数与StartApp、ActivateApp配合使用，使KingSCADA具备了远程控制其它应用程序的能力，这是软件的重要功能之一。它可以启动另一应用程序，如Excel，然后又可以命令该应用程序执行一组功能，如产生报表，趋势图或记录数据。所需的过程可以用某一应用程序(比如Excel)的"宏"语言来写。这就是说，只要按一次键调用该宏命令就能启动很复杂的过程。这种用其他功能很强的应用程序作为从属程序的能力大大增强了KingSCADA的功能。

该函数用于将击键信息发送至当前获得输入焦点的应用程序。对于此应用程序来说，键似乎已由键盘输入。在调用此函数时，必须使接受键信息的应用程序获得输入焦点，因此需要先调用ActivateApp。

**参数：**

StrCmd：命令语言表述，字符串型。参数strCmd为特定键的代码，代码意义和用法与Microsoft的Excel的函数Send Keys中参数keyT相同，可参照下面的表：

 

|  |  |
| --- | --- |
| 键码 | 意义 |
| {BACKSPACE}or{BS} | Backspace |
| {BREAK} | BreakCaps |
| {CAPSLOCK} | Caps Lock |
| {CLEAR} | Clear |
| {DELETE}or{DEL} | Delete or Del |
| {DOWN} | Down direction key |
| {END} | End |
| {ENTER}or~ | Enter |
| {ESCAPE}or{ESC} | Esc(Escape) |
| {HOME} | Home |
| {INSERT} | Insert |
| {LEFT} | Left direction key |
| {NUMLOCK} | Num Lock |
| {PGDN} | Page Down |
| {PGUP} | Page up |
| {PRTSC} | Print Screen |
| {RIGHT} | Right direction key |
| {SCROLLLOCK} | Scroll Lock |
| {TAB} | Tab |
| {UP} | Up direction key |
| {F1}through{F12} | Function keys F1 through F12 |

 

可以用大写或小写的字符定义一个键命令，还可以同时与下面的键配合使用：

 

|  |  |
| --- | --- |
| 键码 | 意义 |
| + | Shift |
| ^ | Ctrl |
| % | Alt |

 

**返回值：**

无

**例****1****：**

ActivateApp("Excel.exe");

SendKeys("^(X)");

将Control X键信息发送至Excel。对于Excel来说，这可能为报告生成宏命令的命令码。

**例****2****：**

为了发送一键序列来拷贝已选定的区，调用函数

SendKeys("^{insert}");   
//为了表示在另一键按下时按下SHIFT，CONTROL或ALT，可以将其它键放入括号内。如：

SendKeys("%(TFR)~");  
//这表示先发出击键信号：Alt-t、Alt-f和Alt-r，然后是Enter回车键。%指代Alt键，因为跟在Alt键码后面的字母都在括号中，所以当每一键按下时Alt键好象同时也被按下。

SendKeys("secret~");    
//表示先发出字符串secret，然后按回车键。

***注：由于字符******+******、******^******和******%******都有特殊含义，为了输入这些字符本身而不取其特殊含义，应给字符加花括号，如：******SendKeys("A{+}B")******，表示发出字符串******A+B******。***

---

命令语言函数速查手册

### 12、StartApp(string strProgramName)

**功能：**

此函数用于启动指定的应用程序。为确保能启动应用程序，请在应用程序名前使用全路径。路径使用DOS名称，即在DOS下显示的路径名。

**参数：**

strProgramName：需要启动的应用程序名，字符串型。

**返回值：**

无

**例****1****：**

StartApp("c:\Program Files\Microsoft
Office\Office\Excel.exe");

打开excel应用程序

**例****2****：**

StartApp("c:\Program Files\Microsoft
Office\Office\Excel.exe d:\Report.xls");

打开excel应用程序，同时打开Report.xls文件

---

命令语言函数速查手册

### 13、StopApp(string strProgramName, int nType)

**功能：**

根据设置的类型来停止指定的应用程序

**参数：**

strProgramName：根据nType参数，该参数的设置有所不同，有如下对应关系：

nType参数     
strProgramName参数

1                                   
应用程序的标题名

2                                   
应用程序的类名

3                                   
应用程序的名称

4                                   
应用程序的ID号

该参数为字符串类型，不区分大小写

nType：整型，取下列值之一：

1----caption，根据标题名来停止指定的应用程序

2----class，根据类名来停止指定的应用程序，可以通过VC中的Spy工具获得

3----exeFile，根据exe文件来停止指定的应用程序，即可执行文件的名称，不包含文件的路径

4----ID，根据进程ID号来停止指定的应用程序，可以通过Windows任务管理器获得，如下图矩形中的PID号：

**调用格式：**

StopApp ("信息浏览器",
1);      //根据标题名来停止信息浏览器

---

命令语言函数速查手册

### 14、Trace(string strFormatString)

**功能：**

此函数为调试函数，即系统运行时，利用该函数将获得的数值按照指定的形式显示在信息窗口中。

**参数：**

strFormatString：输出字符串内容，字符串型。

**调用格式：**

Trace("Expression="+tagname);

//即把表达式Expression的值输出到信息窗口中

**例如：**

Trace("Pressure="+pressure1);    
//信息窗口将显示"Pressure=50.6"。

Trace("\\local\step4timeout=
"+\\local\speedofBlender1+"pa");   //输出为"Pressure=50.6pa"

---

命令语言函数速查手册

### 15、ClearArray(string strArrayName)

**功能：**

此函数用于清空数组，删除数组对象，释放资源函数。

**参数：**

strArrayName：数组名称，字符串型。

**返回值：**

无

**调用格式：**

ClearArray("arrInt10"); //删除一个名称为"arrInt10"的数组

---

命令语言函数速查手册

### 16、CreatArray(string strArrayName，int nElementType，int nElementNum)

**功能：**

创建全局数组函数。

**参数：**

strArrayName：数组名称。

nElementType：数组里元素的类型，1：int;2：float;3：string;4：bool。

nElementNum：数组里元素的个数，>0。

**返回值：**

0：成功;-1：失败;-2：数组重复创建;-4：错误的类型。

**调用格式：**

CreatArray("arrInt10",
"1", 10);//创建一个名称是"arrInt10"，类型为整型int，长度为10的数组

---

命令语言函数速查手册

### 17、GetArrayElement(string strArrayName, int nIndex)

**功能：**

此函数用于获取数组元素值。

**参数：**

strArrayName：数组名称。

nIndex：元素索引号，从0开始。

**返回值：**

返回元素值，Bool值false返回字符串"false" ，Bool值true返回字符串"true"。

**调用格式：**

GetArrayElement("arrInt10",2);//从"arrInt10"数组中获得元素索引号为2的元素值

---

命令语言函数速查手册

### 18、GetArrayElementType(string strArrayName)

**功能：**

此函数用于获取数组元素类型。

**参数：**

strArrayName：数组名称。

**返回值：**

返回数组元素类型，1：int，2：float，3：string，4：bool。

**调用格式：**

GetArrayElementType(
"arrInt10" );//获取数组"arrInt10"的元素类型

---

命令语言函数速查手册

### 19、GetArrayLength(string strArrayName)

**功能：**

此函数用于获取数组元素个数。

**参数：**

strArrayName：数组名称。

**返回值：**

返回数组元素个数。

**调用格式：**

GetArrayLength(
"arrInt10" );//获取数组"arrInt10"的元素个数

---

命令语言函数速查手册

### 20、SetArrayElement(string strArrayName, int nIndex, string strElementValue)

**功能：**

此函数用于设置数组元素值。

**参数：**

strArrayName：数组名称。

nIndex：元素索引号，从0开始;

strElementValue：元素值，都用字符串形式传递，函数内部自动按数组的元素类型进行转换。Bool值false用字符串"false"或"0"传递，Bool值true用字符串"true"或" [非0的数值]"传递。(注意false和true要小写)

**返回值：**

0：成功;-1：失败;-3：找不到数组;-5：元素值与类型不匹配，转换失败;-6：数组元素索引越界。

**调用格式：**

SetArrayElement(
"arrInt10", i,value);//用于设置数组"arrInt10"中第i个元素的值

---

命令语言函数速查手册

### 21、GetKey(integer iLicenseType)

**功能：**

获得SCADA当前使用的加密锁的序列号。

**参数：**

iLicenseType：授权类型，1=获取KS运行授权锁序列号，2=获取KS开发授权锁序列号（暂不实现，预留）。

**返回值：**

字符串型，返回当前使用锁指定授权类型锁序列号。

**调用格式：**

GetKey(1);

---

命令语言函数速查手册

### 22、ShowMessageBox(String lpszText, String lpszCaption, UINT nType, UINT nIcon)；

**功能：**

消息提示框，该函数为阻塞线程脚本函数

**参数**

lpszText：提示框的提示文本内容，字符型；

lpszCaption：提示框的标题，字符型；

nType：消息提示框的按钮组合类型 整形；

0：1个"确定"按钮：

1：一个"确定"按钮和"取消"按钮；

2：一个"终止"按钮，一个"重试"按钮，一个"忽略"按钮；

3：一个"是"按钮，一个"否"按钮，一个"取消"按钮；

4：一个"是"按钮，一个"否"按钮；

5：一个"重试"按钮，一个"取消"按钮；

6：一个"取消"按钮，一个"重试"按钮，一个"继续"按钮。

nIcon：消息提示框图标类型

0： 空格，没有图标。

1：

2：

3：

4：

**返回值：**

整形。

1：单击"确定"返回值

2：单击"取消"返回值

3：单击"终止"返回值

4：单击"重试"返回值

5：单击"忽略"返回值

6：单击"是"返回值

7：单击"否"返回值

8：单击"关闭"返回值

9：单击"帮助"返回值

10：单击"重试"返回值：该返回值只在按钮组合类型为6时出现。

11：单击"继续"返回值

---

命令语言函数速查手册

### 23、AsynShowMessageBox(String lpszText, String lpszCaption, UINT nIcon)；

**功能**

消息提示框，该函数为非阻塞消息提示框。

**参数**

lpszText：消息框文本内容 字符串类型String

lpszCaption：消息框标题  字符串类型String

nIcon：图标类型 

0：空格，没有图标 

1：

2：

3：

4：

 

**返回值：**

无返回值

---

命令语言函数速查手册

### 24、GetCursorPosX()

**功能：**

此函数返回当前鼠标的X坐标。

**参数：**无

**返回值：**

整型，返回鼠标当前的X坐标。

**调用格式：**

result=GetCursorPosX();  //result的值即为当前鼠标点击的X坐标值。

---

命令语言函数速查手册

### 25、GetCursorPosY()

**功能：**

此函数返回当前鼠标的Y坐标。

**参数：无**

**返回值：**

整型，返回鼠标当前的Y坐标。

**调用格式：**

result=GetCursorPosY();  //result的值即为当前鼠标点击的Y坐标值。

---

命令语言函数速查手册

### 26、GetCurPictureFocusGraphyName()

**功能：**

此函数返回当前获取焦点的图素的名称，失败返回空字符串

**参数：无**

**返回值：**

字符串型

**调用格式：**

result=
GetCurrPictureFocusGraphyName();  //result的值即为当前获取焦点的图素名称。

注：图素指包括默认MemberAccess为true的图素成员

---

命令语言函数速查手册

### 27、Ping(string strIP, int nRetryTimes)

**功能：**

此函数是测试本机到某一个IP之间的网络是否通畅

**参数：**

strIP：需测试是否通畅电脑的IP

nRetryTimes：重连次数

**返回值：**

整型

0：网络不通

1：网络通

2：输入有误

3：源地址无效

4：目的地址无效

**调用格式：**

result= Ping("127.0.0.1"，3);

---

命令语言函数速查手册

### 28、Is64BitWindows()

**功能：**

此函数检查是否为64位操作系统的接口

**参数：无**

**返回值：**

布尔型

true：是

false：否

**调用格式：**

result= Is64BitWindows();

---

命令语言函数速查手册

### 29、RegODBCDataSource(bool b32Bit, string strDSNName, string strComment, string strIP, int nPort, string strUser, string strPassword, string strDB, string strDriverName);

**功能：**

此函数是注册ODBC数据源

**参数：**

b32Bit：是否是32位系统

strDSNName：数据源名称

strComment：数据源描述

strIP：服务器地址

nPort：服务器端口

strUser：登录用户名

strPassword：登录密码

strDB：超时

strDriverName：驱动程序名称

**返回值：**

整型

0：成功

-1：失败

-2：输入参数错误

-3：指定的数据源驱动未安装

**调用格式：**

result= RegODBCDataSource(1, "DSN45", "测试ODBC",
"172.16.14.55", 5678, "sa", "sa", "0",
"KingHistorian ODBC Driver(Ver 3.1)"); //新建名称为DSN45、描述为测试ODBC的数据源

---

命令语言函数速查手册

### 30、GetComputerName()

**功能：**

此函数是获取本地计算机名称

**参数：无**

**返回值：**

字符型

**调用格式：**

result= GetComputerName();//result为本地计算机名称

---

命令语言函数速查手册

### 31、GetWindowsUserName()

**功能：**

此函数是本地登录Windows的用户名称

**参数：无**

**返回值：**

字符型

**调用格式：**

result= GetWindowsUserName();//result为本地登录Windows的用户名称

---

命令语言函数速查手册

### 32、GetProjectClientName(string strProjectName)

**功能：**

此函数是获取工程客户端名称

**参数：**

strProjectName：注册表中人为命名的客户端工程名称，该名称通过 reg 文件手动写到注册表中

**返回值：**

字符型

**调用格式：**

注册"工程客户端名称"：编一个后缀为 reg 的文本文件，写如下文本：

Windows Registry Editor Version 5.00

 

[HKEY\_CURRENT\_USER\Software\WellinTech\Run-Time
System\ Client]

"ClientName"="靖三联"

result= GetProjectClientName("Client");//result为注册表中Client 的ClientName

---

命令语言函数速查手册

### 33、GetProjectClientUnit(string strProjectName)

**功能：**

此函数是获取工程客户端单位名称

**参数：**

strProjectName：注册表中人为命名的客户端单位名称，该名称通过 reg 文件手动写到注册表中

**返回值：**

字符型

**调用格式：**

注册"工程客户端单位名称"：编一个后缀为 reg 的文本文件，写如下文本：

Windows Registry Editor Version 5.00

 

[HKEY\_CURRENT\_USER\Software\WellinTech\Run-Time
System\ Client]

"UnitName"="调度室"

result= GetProjectClientUnit ("Client");//result为注册表中Client 的UnitName

**注：**以上两个函数GetProjectClientName
和GetProjectClientUnit 的reg文件可合并为：

Windows Registry
Editor Version 5.00

 

[HKEY\_CURRENT\_USER\Software\
WellinTech\Run-Time System\ Client]

"ClientName"="靖三联"

"UnitName"="调度室"

---

命令语言函数速查手册

### 34、CreatArray2(string strArrayName, int nElementType, int nElementNum)

**功能：**

创建数组，该函数的返回值为数组对象的内存指针(int)

**参数：**

strArrayName：数组名称

nElementType：代表数组元素的类型1=int，2=
float，3= string，4= bool

nElementNum：数组元素的个数，该参数取值范围0-300000

**返回值：**

int类型的内存指针

**调用格式：**

同SetArrayElementInt与GetArrayElementInt一起使用。

int a;

a = CreatArray2("A",1,1000);

int l;

for(l = 0;l< 1000; l++)

{ SetArrayElementInt(a, l, 11);

 GetArrayElementInt(a, l);}

---

命令语言函数速查手册

### 35、SetArrayElementInt(int ArrayHandle, int nIndex, int intElementValue)

**功能：**

设置int类型数组元素值

**参数：**

ArrayHandle：int数组对象的内存指针(int)

nIndex：元素索引

intElementValue：int型元素值

**返回值：**

整形，0：执行成功

**调用格式：**

同CreatArray2一起用，调用格式见CreatArray2

---

命令语言函数速查手册

### 36、GetArrayElementInt(int ArrayHandle, int nIndex)

**功能：**

获取int类型数组元素值

**参数：**

ArrayHandle：int数组对象的内存指针(int)

nIndex：元素索引

**返回值：**

Int元素值

**调用格式：**

同CreatArray2一起用，调用格式见CreatArray2

---

命令语言函数速查手册

### 37、SetArrayElementFloat(int ArrayHandle, int nIndex, float floatElementValue)

**功能：**

设置Float类型数组元素值

**参数：**

ArrayHandle：float数组对象的内存指针(int)

nIndex：元素索引

floatElementValue：float型元素值

**返回值：**

整形，0：执行成功

**调用格式：**

同CreatArray2一起用，与调用SetArrayElementInt格式一致

---

命令语言函数速查手册

### 38、GetArrayElementFloat(int ArrayHandle, int nIndex)

**功能：**

获取float类型数组元素值

**参数：**

ArrayHandle：int数组对象的内存指针(int)

nIndex：元素索引

**返回值：**

float元素值

**调用格式：**

同CreatArray2一起用，调用格式见CreatArray2

---

命令语言函数速查手册

### 39、SetArrayElementBool(int ArrayHandle, int nIndex, bool booElementValue)

**功能：**

设置bool类型数组元素值

**参数：**

ArrayHandle：int数组对象的内存指针(int)

nIndex：元素索引

booElementValue：bool类型元素值

**返回值：**

整形，0：执行成功

**调用格式：**

同CreatArray2一起用，调用格式见CreatArray2

---

命令语言函数速查手册

### 40、GetArrayElementBool(int ArrayHandle, int nIndex)

**功能：**

获取bool类型数组元素值

**参数：**

ArrayHandle：int数组对象的内存指针(int)

nIndex：元素索引

**返回值：**

bool元素值

**调用格式：**

同CreatArray2一起用，调用格式见CreatArray2

---

命令语言函数速查手册

### 41、SetArrayElementStr(int ArrayHandle, int nIndex, string strElementValue)

**功能：**

设置字符串类型数组元素值

**参数：**

ArrayHandle：int数组对象的内存指针(int)

nIndex：元素索引

strElementValue：字符串型元素值

**返回值：**

整形，0：执行成功

**调用格式：**

同CreatArray2一起用，调用格式见CreatArray2

---

命令语言函数速查手册

### 42、GetArrayElementStr(int ArrayHandle, int nIndex)

**功能：**

获取字符串类型数组元素值

**参数：**

ArrayHandle：int数组对象的内存指针(int)

nIndex：元素索引

**返回值：**

Int元素值

**调用格式：**

同CreatArray2一起用，调用格式见CreatArray2

---

命令语言函数速查手册

### 43、ShowMessageBoxAt(string strText, string strCaption, double x, double y)

**功能：**

在屏幕指定位置弹出消息提示框

**参数：**

strText：消息框文本内容 字符串类型String

strCaption：消息框标题  字符串类型String

x,y: 位置坐标

返回值：

Bool类型

True---成功

False---失败

---

命令语言函数速查手册

### 44、ShowMessageBoxAtWithAlign(string strText, int nAlign, string strCaption, double x, double y)

**功能：**

在屏幕指定位置弹出消息提示框，并可设置消息框文本内容对齐方式

**参数：**

strText：消息框文本内容 字符串类型String

nAlign：0---靠左，1---居中，2---靠右，整型int

strCaption：消息框标题  字符串类型String

x,y: 位置坐标

返回值：

Bool类型

True---成功

False---失败

---

命令语言函数速查手册

### 45、GetClientNum( int Type )

**功能：**

获取当前系统BS、CS客户端授权占用连接数。

**参数：**

Type ：获取的客户端类型，0：BS客户端，1：CS客户端，7：KSP客户端

返回值：

整型

失败，返回值为-1

---

命令语言函数速查手册

### 46、SendKeysEx(int VirtualKeys, int isCTRL, int isSHIFT, int isALT)

**功能：**

此函数与ActivateApp配合使用实现向激活窗口发送键盘指令的功能。该函数只能发送虚拟键盘指令，不能用于字符串输入。

**参数：**

int VirtualKeys：整型，虚拟键值，十进制或者十六进制均可（十六进制需为0x00格式）；

int isCTRL：：整型，是否按Ctrl键，非零代表按下；

int isSHIFT：：整型，是否按Shift键，非零代表按下；

int isALT：：整型，是否按Alt键，非零代表按下；

**返回值：**

无

**举例1****：**

ActivateApp("工作表.xlsx - EXCEL");

SendKeysEx(82,1,0,0);

//将Excel表格“工作表.xlsx”激活，并向Excel窗口发送Ctrl+R指令（82为R的十进制ASCII码）；

**举例****2****：**

ActivateApp("工作表.xlsx - EXCEL");

SendKeysEx(0x56,1,0,0);

//将Excel表格“工作表.xlsx”激活，并向Excel窗口发送发送Ctrl+V指令（0x56为R的十六进制ASCII码）；

**注意：**发送组合键命令时，字母按键需要使用大写字母的ASCII码

---

命令语言函数速查手册

## 九、文件操作函数

---

命令语言函数速查手册

### 1、CreateCsvFile1(string strCsvFilePath, string strFoldPath, string strFileExtension)

**功能：**

将某个扩展名的文件名strFileExtension读取到指定的CSV文件strCsvFilePath中，且能被组合框方法ImportFromFile去调用的格式。

说明： 组合框导入导出的CSV文件格式：

|  |  |
| --- | --- |
| Item String | Iteam Data (long) |
|  |  |

**参数：**

strCsvFilePath：生成的csv文件路径，含文件名称，不能为空

strFoldPath：指定查找目录路径，不能为空

strFileExtension：要查找的文件名包含的字符串和扩展名，支持通配符。例如：日报\*.xls，\*.rtl….；目前只支持一种扩展名格式。

**返回值：**

布尔型。生成的csv文件的存储格式为文件名保存为Item String，与其对应的Item
Data值默认为0 （提供默认值的目的是为能使该CSV文件能被组合框调用）。

***注：***

***1、******当******strCsvFilePath******参数指定的******CSV******文件存在时，清除其中的内容，再将结果保存到该******CSV******文件中；***

***2、******当******strCsvFilePath******指定的保存文件扩展名不为******csv******时，则自动添加******csv******格式，如：***

***strCsvFilePath = "D:\My Documents\PIC.exe"******，则保存文件为：*** ***D:\My Documents\PIC.exe.csv******；***

***3、******如果参数******strFileExtension******为空，则默认为******\*.\*******，******查找******strFoldPath******文件夹下所有文件******(******不对子文件夹中的文件进行查找******)******，******且目前只支持一种扩展名格式；***

***4、******保存的结果为文件的全名******(******文件名******+******扩展名******)******；***

***5、******strFoldPath******文件夹下的所查找的文件名，不应还有逗号，否则保存至******CSV******文件时，分成******2******个单元格******(CSV******文件是以逗号为分隔符的******)******；***

***6、******参数的长度不得超过******260******字节，第二个参数与第三个参数之和不得超过******256******。***

---

命令语言函数速查手册

### 2、FileCopy(string strSrcFile, string strDstFile)

**功能：**

此函数复制一个源文件到目的文件，它与DOS的Copy命令或者Windows文件管理器中的Copy功能相似，该函数为异步执行，就是验证处参数合法后立即返回，至于文件何时复制完毕要看文件的大小。

**参数：**

strSrcFile：源文件名(包含完整的路径)

strDstFile：目的文件(包含完整的路径)或目录名

**返回值：**

整型。

1：成功返回;

0：不能启动返回;

-1：出错返回;

**例****1****：**

FileCopy("c:\\*.txt",
"c:\backup");

拷贝多个文件到backup目录下，若strSrcFile包含任何通配符的话，strDstFile必须是一个目录

**例****2****：**

FileCopy("c:\data.txt",
"c:\backup");

拷贝data.txt文件到backup目录下，文件名称不变

**例****3****：**

FileCopy("c:\data.txt",
"c:\backup\data1.txt");

拷贝data.txt文件到backup目录下，文件名更改为data1.txt

---

命令语言函数速查手册

### 3、FileDelete(string strFileName)

**功能：**

此函数删除文件和文件夹

**参数：**

strFileName：要删除的文件名或文件夹名，字符串型。

**返回值：**

整型，

1----删除成功

0----删除失败

**调用格式：**

删除文件：FileDelete("c:\data.txt");

删除文件夹：FileDelete("D:\文档");

---

命令语言函数速查手册

### 4、FileMove(string strSrcFile, string strDstFile)

**功能：**

此函数与 FileCopy ()函数相似，但只是将文件从一个位置转移到另一个位置，而不是复制，该函数为异步执行，就是验证出参数合法后立即返回，至于文件何时移动完毕要看文件的大小。

**参数：**

strSrcFile：源文件名(包含完整的路径)

strDstFile：目的文件名(包含完整的路径)

**返回值：**

整型

1----成功返回

0----不能启动返回

-1----出错返回

**调用格式：**

FileMove("c:\data.txt","c:\backup");

若源文件和目的文件位于同一驱动器上，此函数可以简单地更改此文件的目录参照表(计算机在此表中保存磁盘上的文件名和存储位置)，而不用实际转移任何数据。在这种情况下，不管此文件的大小，转移操作将会很快。若源文件和目的文件位于不同的驱动器上，转移操作所费的时间将随文件的大小不同而不同。这是因为数据必须由一个物理磁盘传送到另一物理磁盘上，如：

FileMove("c:\data.txt","d:\data.txt");

将把c驱动器上根目录下的名为data.txt的文件转移到名为backup的目录下。

此函数也可用于文件更名，只要源文件和目的文件指定了相同的目录，但不同的文件名，如：

FileMove
("c:\data.txt","c:\data.bak");

将把c盘根目录下文件data.txt更名为data.bak

---

命令语言函数速查手册

### 5、FileReadFields(string strFileName, int nFileOffset, string strStartTagName, int nNumberOfFields)

**功能：**

使用此函数可以从一个文本文件中读出指定位置的字段，并将读出的字段保存到变量中，字段之间以逗号隔开。

**参数：**

strFileName：指定要读的文件，字符串类型

nFileOffset：指定读此文件的起始位置，大于等于1，若为1表示从头开始

strStartTagName：指定将读出的字段保存到KingSCADA的哪个工程变量中，此处是第一个变量的名称，该变量必须以一个数字结尾(如MyTag1)

nNumberOfFields：指定要读的字段数目(此文件的每个字段以逗号隔开)

**返回值：**

整型

**调用格式：**

FileReadFields(Filename,FileOffset,"StartTag",NumberOfFields);   

//若StartTag 为"MyTag1"而NumberOfField为3，则有3个字段从文件中读出并保存在MyTag1、MyTag2和MyTag3中。这些具有连续名字的变量必须先在工程中创建，并可以属于不同的类型(整型，字符串类型等等)。

**例：**

C：\DATA\FILE.CSV的第一行内容为："This is text, 3.1416, 5"，调用函数

BytePosition=FileReadFields("C:\DATA\FILE.CSV",1,
"MyTag1", 3);

将读出此行，并把"This is text"保存在MyTag1中，3.1416保存在 MyTag2中，5保存在 MyTag3中。

此函数在读出之后返回新的字节位置。你可以在下次读时使用此返回值作为FileOffset的值，如：BytePosition=FileReadFields("c:\DATA\FILE.CSV",FileOffset,
"MyTag1",3);

***注：******strStartTagName******参数******必须加引号***

---

命令语言函数速查手册

### 6、FileReadStr(string strFileName, int nFileOffset, StringTag strTag, int nCharsToRead)

**功能：**

此函数从指定的文本文件中读出一指定数目的字节(或一整行)，并将读出的数据保存到变量中。

**参数：**

strFileName：指定要读的文件，字符串类型

nFileOffset：指定读此文件的起始位置。大于等于1，若为1则表明从头开始

strTag：指定将读出的数据保存到KingSCADA的哪个工程变量中。

nCharsToRead：指定要读出的字节数。为处理文本文件，可将nCharsToRead置为0，函数从文件的指定位置一直读到下一个 LF(换行符)

**返回值：**

整型。

**调用格式：**

FileReadStr(Filename,FileOffset,Str\_Tag,CharsToRead);       
//此函数在读出之后返回新的字节位置。可以在下次读时使用此返回值作为 FileOffset 值。

**例：**

FileReadStr ("C:\DATA\FILE.TXT", 1, Str\_Tag,
0);            //文件"C：\DATA\FILE.TXT"的第一行将被读出并保存到 Str\_Tag中。

---

命令语言函数速查手册

### 7、FileWriteFields(string strFileName, int nFileOffset, string strStartTagName, int nNumberOfFields)

**功能：**

此函数往指定的文本文件中写入字段

**参数：**

strFileName：指定要写的文件，若文件不存在，则创建它

nFileOffset：指定写此文件的起始位置，若FileOffset为 0，此函数将写到文件末尾。若为1，则写到开头

strStartTagName：指定第一个数据项的工程变量名称。此变量名必须以一个数字结尾(如MyTag1)。此参数必须是一个表明变量名的字符串(而非实际的变量本身)。比如，变量名为MyTag1，就需要给出"MyTag1"(注意引号)或MyTag1.name，而不仅仅是MyTag1

nNumberOfFields：指定要写的字段数目(此文件的每条记录中以逗号隔开的字段的字段数目)

**返回值：**

整型。

**调用格式：**

FileWriteFields(Filename,FileOffset,"StartTag",NumberOfFields);    
//此函数在写入之后返回新的字节位置。可以在下次调用函数时使用此返回值作为FileOffset值。

若 StartTag为"MyTag1"，而NumberOfFields为3，则有3个字段被写入文件中(写入的是 MyTag1、MyTag2 和 MyTag3)。这些具有连续名字的变量必须先在KingSCADA工程中创建，并可以属于不同的类型(整型，字符串等等)。

**例如：**

将一行"This is text,3.1416,5"写到文件C：\DATA\FILE.CSV的第一行中。"This is text"是 MyTag1的当前值，3.1416是 MyTag2的当前值，5是 MyTag3的当前值。调用函数

FileWriteFields
("C:\DATA\FILE.CSV", 1, "MyTag1", 3);

若将文本串MyTag1写到C：\DATA\FILE.CSV的末尾，调用函数

FileWriteFields
("C:\DATA\FILE.CSV", 0, "MyTag1", 3);

StartTag 两侧必须加引号。

---

命令语言函数速查手册

### 8、FileWriteStr(string strFileName, int nFileOffset, string Str, int nLineFeed)

**功能：**

此函数往指定的文本文件中写入指定数目的字节(或一整行)。

**参数：**

strFileName：指定写入的文件。若文件不存在，则创建它

nFileOffset：指定此文件的起始位置。若 FileOffset 为 0，此函数将写到文件末尾。若为1，则写到开头

Str：指定要写入的内容，字符串类型。注：因为%是格式符号，所以如果写入内容里含有%，需要写成"%%"。例如，如果要写入"fff%"，则Str要赋值为"fff%%"。

nLineFeed：指定在内容写入之后是否添加换行符，当该参数为1时表示换行，为其他数值时表示不换行

**返回值：**

整型。

**调用格式：**

FileWriteStr(Filename,FileOffset,String,LineFeed);

//此函数在写入后返回新的字节位置。你可以在下次写时将此返回值当作FileOffset() 函数的返回值来使用。

**例如：**

将名为MsgTag的字符串变量内容写入文件C:\DATA\FILE.TXT的末尾。调用函数：

FileWriteStr ("C:\DATA\FILE.TXT",
0, MsgTag, 1);

将字符串常量abc写入文件C:\DATA\FILE.TXT的末尾，写入后换行

FileWriteStr ("C:\DATA\FILE.TXT",
0, "abc", 1);

---

命令语言函数速查手册

### 9、ReadStringFromFile (string strFileName, int nFileOffset, int nCharsToRead)

**功能：**

从指定文本文件中读出一指定数目的字节(或一整行)，并将读出的数据以字符串形式返回。

**参数：**

strFileName：指定要读的文件，字符串类型。

nFileOffset：指定读此文件的起始位置。大于等于1，若为1则表明从头开始。

nCharsToRead：指定要读出的字节数。为处理文本文件，可将nCharsToRead置为0，函数从文件的指定位置一直读到下一个 LF(换行符)。

**返回值：**

将读出的数据以字符串形式返回。

---

命令语言函数速查手册

### 10、ShowFileDialog(string strDefaultPath，string strExtendTypes)

**功能：**

弹出让用户选择文件的对话框。该函数帮助用户在指定路径下选择已存在的文件，或者在指定路径下创建新文件。用户可以在弹出界面里选择文件，也可以直接在文件名框里输入文件名。无论文件是否存在，文件全路径字符串都可以返回。

运行时，该函数弹出如下图所示的界面：

**参数：**

strDefaultPath：默认打开的路径，可以为空，空代表默认打开路径为"我的电脑"。

strExtendTypes：扩展名过滤字符串，可以为空，空代表所有文件，多个扩展名用","分隔，如：exe、txt、csv、xls。注：扩展名里不能有点、逗号这2个字符。

**返回值：**

字符串类型，返回用户选择的文件全路径。

---

命令语言函数速查手册

### 11、ShowFolderDialog()

**功能：**

弹出让用户选择文件夹的对话框。该函数在运行时会弹出如下图所示的界面：

 

**返回值：**

字符串类型，返回用户选择的文件夹全路径。

---

命令语言函数速查手册

## 十、资源函数

---

命令语言函数速查手册

### 1、ChangeLanguage(string strLanguageName)

**功能：**

此函数用于在不同语言间进行切换

**参数：**

strLanguageName：要切换的语言名称，字符串类型，即下图矩形中的名字

**返回值：**

切换成功返回true，否则返回false

**调用格式:**

ChangeLanguage("英文");

---

命令语言函数速查手册

### 2、GetImageFilePath(string strImageName)

**功能：**

此函数用于获得指定的图形资源路径。

**参数：**

strImageName：图形资源中用户定义的资源名，字符串类型

**返回值：**

字符串类型

**调用格式:**

GetImageFilePath("ImageResource");

---

命令语言函数速查手册

### 3、GetResourceFilePath(string strResourceName)

**功能：**

此函数用于获得指定的文件资源路径

**参数：**

strResourceName：文件资源中用户定义的资源名，字符串类型

**返回值：**

字符串类型

**调用格式:**

GetResourceFilePath("FileResource");

---

命令语言函数速查手册

### 4、GetTranslateText(string strOriginalText)

**功能：**

通过当前的语言环境获得指定原始文本的翻译文本

**参数：**

strOriginalText：原始文本，字符串类型

**返回值：**

字符串类型

**调用格式:**

GetTranslateText("abc");

---

命令语言函数速查手册

## 十一、数据集函数

声明：

1、在数据集函数中，数据集名称可以为空，空字符串也可以是一个数据集的名称，但是建议用户不要用空字符串作为数据集的名称，也不要忘了给数据集名称字符串赋值。

2、关系表如果有bool字段，数据集的该字段的值是0、-1，0---false，-1---true。

---

命令语言函数速查手册

### 1、KDBGetDataset(string DatasetName, string ConnectStr, string SqlStr)

**功能：**

此函数用来连接数据库，执行SQL查询命令，获得一个符合条件的数据集。如果不是频繁访问数据库，建议使用KDBGetDataset()函数，该函数的特点是：连接一次数据库，执行一次SQL命令，执行完毕便断开与数据库的连接;如果是频繁访问数据库，建议使用KDBGetconnectID()、KDBGetDataset1()、KDBDisConnect()系列函数，这三个函数是配套使用的，它通过连接句柄一直保持与数据库连接，直到执行KDBDisConnect()函数。

**参数：**

DatasetName：数据集的名称，字符串类型，不能为空字符串

ConnectStr：数据库连接字符串，字符串类型

SqlStr：SQL查询语句，字符串类型

**返回值**

true：获得数据集成功

false：获得数据集失败

**举例：**

一、数据库连接字符串ConnectStr，支持ODBC和OLEDB连接字符串，数据库连接字符串例子：

1、ODBC数据驱动接口

● Access数据库

ConnectStr="DSN=sany;DATABASE=;uid=sa;pwd=sa";

● SqlServer数据库

ConnectStr="DSN=DB\_SQL2005;DATABASE=TestDB;UID=sa;PWD=;";

● MySql数据库

ConnectStr="DSN=DB\_MySQL5;SERVER=172.16.2.203;UID=root;PWD=root;DATABASE=TestDB;PORT=3306";

● Oracle数据库

ConnectStr="DSN=DB\_Oracle9;UID=SYSTEM;PWD=MANAGER;DBQ=SQLDB\_ESTDB;DBA=W;";

● 工业库

ConnectStr="DSN=SQL\_KH;ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0;";

 

2、OLEDB数据驱动接口

   OLEDB方式连接字符串添加超时信息TimeOut，用户可以根据自己的需要适当选择超时的时间，单位为S，例如TimeOut=5，设置的超时时间为5秒；

● Access数据库

ConnectStr="Provider=Microsoft.Jet.OLEDB.4.0;Data
Source=d:\sany.mdb;Persist Security Info=False;TimeOut=5";

● SqlServer数据库

ConnectStr="Provider=SQLOLEDB.1;Integrated
Security=SSPI;Persist Security Info=False;Initial Catalog=SQLAccess\_TestDB;Data
Source= TestDB;TimeOut=5 ";

● MySql数据库

ConnectStr="Provider=MSDAORA.1;User
ID=system;Data Source=sqldb;Persist Security Info=False; TimeOut=5";

● 工业库

ConnectStr =
"Provider=KRTDBProvider.King.3;Data Source=172.16.2.111,5678;Persist
Security Info=True; User ID=sa;Password=sa; TimeOut=5";

 

二、sql查询字符串例子：

string strtagname,
strstarttime, strendtime;

strsql1="select
datatime, datavalue, dataquality from history where
tagname='"+strtagname+"' and datatime>'"+strstarttime+"'
and datatime<'"+strendtime+"'";

strsql2="select
\* from realtime join tag on tag.tagname = realtime.tagname where
tag.collectorname like 'king%'";

---

命令语言函数速查手册

### 2、KDBClearDataset(string DatasetName)

**功能：**

清空数据集

**参数：**

DatasetName：记录集名称，字符串类型

**返回值：**

true：删除成功

false：删除失败

**调用格式:**

KDBClearDataset("MyDataset")

删除MyDataset数据集中的数据记录

---

命令语言函数速查手册

### 3、KDBDatasetMoveFirst(string DatasetName)

**功能：**

移动到数据集的第一行

**参数：**

DatasetName：数据集的名称，字符串类型

**返回值：**

成功返回true，失败返回false

---

命令语言函数速查手册

### 4、KDBDatasetMoveNext(string DatasetName)

**功能：**

移动到数据集的下一行

**参数：**

DatasetName：数据集的名称，字符串类型

**返回值：**

成功返回true，失败返回false

**调用格式:**

KDBDatasetMoveNext("MyDataset");

---

命令语言函数速查手册

### 5、KDBDatasetMovePrev(string DatasetName)

**功能：**

移动到数据集的上一行

**参数：**

DatasetName：数据集的名称，字符串类型

**返回值：**

成功返回true，失败返回false

**调用格式:**

KDBDatasetMovePrev
("MyDataset");

---

命令语言函数速查手册

### 6、KDBDatasetMoveLast(string DatasetName)

**功能：**

移动到数据集的最后一行

**参数：**

DatasetName：数据集的名称，字符串类型

**返回值：**

成功返回true，失败返回false

---

命令语言函数速查手册

### 7、KDBEditDataset1(string DatasetName, int FieldId, string iTimeZone1, string iTimeZone2)

**功能：**

将数据集指定列的数据进行时区转换。注：指定列必须是时间字符串，格式是标准的YYYY-MM-DD HH:MM:SS格式，否则返回参数错误。

**参数：**

DatasetName：数据集名称，字符串类型。

FieldId：指定列号，整型。

iTimeZone1：当前数据对应的时区。有效范围是-12~12，0（local）：获取本机时区，-12~12：时区编号，UTC时区的编号是：0。

iTimeZone2：要转换后的时区。有效范围是-12~12，0（local）：获取本机时区，-12~12：时区编号，UTC时区的编号是：0。

**返回值：**

成功返回0，失败返回-1、 -2、 -3、 -4、 -5、 非负数。

-1表示数据集不存在；

-2表示列不存在；

-3表示列数据类型不正确；

-4表示获取数据集数据有误；

-5时区参数有误；

非负数表示第一个修改失败的行数。

---

命令语言函数速查手册

### 8、KDBGetCurRowValueByFieldId(string DatasetName, int FieldId)

**功能：**

此函数用于获得当前行中指定列号的单元格的值，得到一数据集后，当前行默认为第一行，可以配合KDBDatasetMoveFirst、KDBDatasetMoveNext、KDBDatasetMovePrev和KDBDatasetMoveLast函数使用。

**参数：**

DatasetName：数据集的名称，字符串类型

FieldId：要获取的单元格的列号，列号从0开始

**返回值：**

字符串类型，失败时返回NULL

**调用格式:**

KDBGetCurRowValueByFieldId("MyDataset",1);

在使用此函数之前需使用KDBGetDataset函数得到数据集

---

命令语言函数速查手册

### 9、KDBGetCurRowValueByFieldName(string DatasetName, string FieldName)

**功能：**

此函数用于获得当前行中指定列名称的单元格的值，得到一数据集后，当前行默认为第一行，可以配合KDBDatasetMoveFirst、KDBDatasetMoveNext、KDBDatasetMovePrev和KDBDatasetMoveLast函数使用。

**参数：**

DatasetName：数据集的名称，字符串类型

FieldName：要获取的单元格的列名称(即字段名)，字符串类型

**返回值：**

字符串类型，失败时返回NULL

**调用格式:**

KDBGetCurRowValueByFieldName
("MyDataset", "姓名");

在使用此函数之前需使用KDBGetDataset函数得到数据集

---

命令语言函数速查手册

### 10、KDBGetCellValueByFieldId(string DatasetName, int RowID, int FieldId)

**功能：**

此函数用于获得数据集中指定单元格的值

**参数：**

DatasetName：数据集的名称，字符串类型

RowID：要获取的单元格的行号，行号从0开始

FieldId：要获取的单元格的列号，列号从0开始

**返回值：**

字符串类型，失败时返回NULL

**调用格式:**

KDBGetCellValueByFieldId("MyDataset",1,1);

在使用此函数之前需使用KDBGetDataset函数得到数据集

---

命令语言函数速查手册

### 11、KDBGetCellValueByFieldName(string DatasetName, int RowID, string FieldName)

**功能：**

此函数用于获得数据集中指定单元格的值

**参数：**

DatasetName：数据集的名称，字符串类型

RowID：要获取的单元格的行号，行号从0开始

FieldName：要获取的单元格的列名称(即字段名)，字符串类型

**返回值：**

字符串类型，失败时返回NULL

**调用格式:**

KDBGetCellValueByFieldName("MyDataset",1, "姓名");

在使用此函数之前需使用KDBGetDataset函数得到数据集

---

命令语言函数速查手册

### 12、KDBGetDatasetCols(string DatasetName)

**功能：**

此函数用于获得指定数据集中字段数目(即：列数目)

**参数：**

DatasetName：数据集的名称，字符串类型

**返回值：**

整型

成功返回字段数目，失败则返回-1

**调用格式:**

KDBGetDatasetCols("MyDataset");

---

命令语言函数速查手册

### 13、KDBGetDatasetRows(string DatasetName)

**功能：**

此函数用于获得指定数据集中记录的数目

**参数：**

DatasetName：数据集名称，字符串类型

**返回值：**

整型

如果成功返回行数据，失败则返回-1

**调用格式:**

KDBGetDatasetRows("MyDataset")

返回MyDataset数据集的记录数

---

命令语言函数速查手册

### 14、KDBExecuteStatement(string ConnectStr, string SqlCommand)

**功能：**

执行SQL语句

**参数：**

ConnectStr：数据库连接字符串，支持ODBC和OLEDB连接字符串

SqlCommand：SQL语句，字符串类型

**返回值：**

成功返回true，失败返回false

**调用格式:**

KDBExecuteStatement("DSN=KingSCADADB",
"delete \* from Table\_LotCols");

或

KDBExecuteStatement("Provider=Microsoft.Jet.OLEDB.4.0;Data
Source=D:\KingDB\TestDB.mdb;Persist Security
Info=false; TimeOut=5", "delete \* from
Table\_LotCols");

---

命令语言函数速查手册

### 15、KDBGetConnectID(string strConnectIDName, string ConnectStr)

**功能：**

连接数据库，获得一个连接句柄，并保持与数据库的连接和打开状态。数据库作为服务器，提供数据服务时，需要和客户端建立服务连接，因为数据库一般都是要为多用户、多程序服务的，服务连接是一种关键的、有限的资源，如果不需要服务了，就要把服务连接给断掉，释放资源，如KDBGetDataset()函数，每执行1次，就创建1次连接，执行完SQL命令后，就关闭连接，下一次执行，再重新连接。

 

但是，如果客户端对数据库的访问是连续而密集的，那么频繁的建立、关闭连接，会极大地降低数据库性能，甚至造成数据库服务器堵塞，为了避免频繁建立、关闭数据库连接带来的的开销，我们提供获取句柄方式，实现对数据库的连接复用。

 

用户可通过KDBGetConnectID()方法建立一个数据库连接，利用这个连接，用KDBGetDataset1方法向数据库发送SQL命令，数据库执行完SQL命令，返回操作结果，并不关闭连接，该连接句柄可以一直使用，直到调用KDBDisConnect()函数将其断开。通过获取句柄方法，使得一个数据库连接方法可以得到高效、安全的复用。

 

长期占用数据库连接而不释放，可能会影响到数据库对其它用户的服务，建议只在需要这种方式的情况下使用，不要滥用。

 因为网络、数据库本身的稳定性原因，即使没有执行释放连接句柄的脚本，时间长了，连接句柄也可能会自动失效，在网络好、数据库访问量不太大的情况下，1个连接句柄大概能坚持几天的时间，建议在执行SQL命令的脚本里，增加获取返回值的语句，来判断是否需要重新获取连接句柄。例句：

 

bool tmpbool;

tmpbool=KDBGetDataset1("MyDataset",
"Handle1", "select \* from Table\_LotCols");

if (tmpbool==0)

{

   
KDBDisConnect("Handle1");

   
KDBGetConnectID("Handle1","DSN=wang;DATABASE=pubs;UID=sa;
PWD=");

   
KDBGetDataset1("MyDataset", "Handle1", "select \* from
Table\_LotCols");

｝

**参数：**

strConnectIDName：与数据库连接的句柄名称，字符串类型

ConnectStr：数据库连接字符串，字符串类型

**返回值：**

true：连接成功

false：通讯失败

**调用格式:**

KingSCADA以sa身份登录(无密码)和名为wang的SQL Server中的pubs数据库连接，并获得名为Handle1的句柄。

KDBGetConnectID ("Handle1", "DSN=wang;
DATABASE=pubs; UID=sa; PWD=");

---

命令语言函数速查手册

### 16、KDBStatusTest(string ConnectStr)

**功能：**

检测数据连接状态

**参数：**

ConnectStr：数据库连接字符串，支持ODBC和OLEDB连接字符串

**返回值：**

true：连接成功

false：通讯失败

**调用格式:**

bool
a=KDBStatusTest("DSN=KingDB");

---

命令语言函数速查手册

### 17、KDBDisConnect(string strConnectIDName)

**功能：**

断开与数据库的连接

**参数：**

strConnectIDName：与数据连接的句柄名称，字符串类型

**返回值：**

true：断开连接成功

false：断开连接失败

**调用格式:**

KDBDisConnect("Handle1");

---

命令语言函数速查手册

### 18、KDBGetDataset1(string DatasetName, string strConnectIDName, string SqlStr)

**功能：**

通过连接句柄，从数据库中获取数据集，获取完毕后不断开和数据库的连接。

**参数：**

DatasetName：数据集名称，字符串类型

strConnectIDName：与数据连接的句柄名称，字符串类型

SqlStr：SQL查询语句，字符串类型

**返回值：**

true：获得数据集成功

false：获得数据集失败

**调用格式:**

KDBGetDataset1("MyDataset", "Handle1",
"select \* from Table\_LotCols");

执行此函数得到一个名为"MyDataset"的数据集，该数据集中的记录符合查询条件

在使用此函数之前需使用KDBGetConnectID函数得到名为Hand1的句柄

---

命令语言函数速查手册

### 19、KDBExecuteStatement1(string strConnectIDName，string SqlCommand)

**功能：**

执行SQL语句

**参数：**

strConnectIDName：与数据库连接的句柄名称，字符串类型

SqlCommand：SQL语句，字符串类型

**返回值：**

成功返回true，失败返回false

**调用格式:**

KDBExecuteStatement1("Handle",
"delete \* from Table\_LotCols");

---

命令语言函数速查手册

### 20、KDBSQLExecuteFromFile(string DatasetName，string ConnectStr，string strSqlFilePath)

**功能：**

从文本文件中逐条顺序执行Sql语句，如果是查询语句的话，将查询结果赋予指定的数据集。如果指定文本文件中有多条查询语句的话，将最后一条查询语句的结果赋给数据集。

**参数：**

DatasetName：数据集名称，如果文本文件里有多条查询语句，返回最后那个结果集给该数据集。

ConnectStr：数据库连接字符串，支持ODBC和OLEDB连接字符串。

strSqlFilePath：Sql命令文本文件(.txt)路径。文本文件里的Sql命令用分号隔开，前面的Sql命令对后面的Sql语句有效。

**返回值：**

True：执行成功

False：执行失败

**调用格式:**

KDBSQLExecuteFromFile ("MyDataset",
"DSN=KingSCADADB", "E:\database\query.txt");

或

KDBSQLExecuteFromFile("MyDataset", "Provider=Microsoft.Jet.OLEDB.4.0;Data
Source=D:\KingDB\TestDB.mdb;Persist Security Info=false; TimeOut=5",
"E:\database\query.txt");

***注：在保存文本文件时，编码选择ANSI******类型，如图中矩形框所示：***

---

命令语言函数速查手册

### 21、KDBKSRawData(string DatasetName, string strTagName, string strStartTime, string strEndTime, int iTimeStampFormat, string strRowStatistic);

**功能：**

有些变量变化很少或非常缓慢，很长时间里，也只能得到很少的有效数据，对历史库进行采样查询时，如果采样间隔太小，会得到很多重复的数据，如果采样间隔太大，会遗漏掉有效数据，所以，需要提供得到原始数据数据集的函数。

当变量的历史保存设为变化时保存，如果在查询时间段内，该变量值没有变化过，那么历史库这段时间里没有原始值，查出来的记录为0。

该函数可以得到1个第1列为时间，第2列为某KS变量原始历史值，第3列为某KS变量质量戳的数据集。

**参数：**

DatasetName：数据集名称，不能为空，可以是空格字符串。

strTagName：KS变量名称，格式：\\站点名\变量名。

strStartTime：起始时间字符串。规定格式：YYYY-MM-DD HH:mm:ss。

strEndTime：终止时间字符串。规定格式：YYYY-MM-DD HH:mm:ss。

iTimeStampFormat：设置数据集第1列的时间戳格式类型。

0：YYYY-MM-DD
hh:mm:ss.xxx

1：YYYY-MM-DD
hh:mm:ss

2：YYYY-MM-DD
hh:mm

3：YYYY-MM-DD
hh

4：YYYY-MM-DD

5：YYYY-MM

6：MM-DD

7：hh:mm:ss.xxx

8：hh:mm:ss

9：hh:mm

10：mm:ss

11：ss.xxx

12：YYYY

13：MM

14：DD

15：hh

16：mm

17：ss

18：xxx

strRowStatistic：列统计设置，min,max，averg的顺序可以任意排列，也可以空，例如："min,max,averg"， "max,min"，"max,averg,min"。

**返回值：**

True：执行成功

False：执行失败

---

命令语言函数速查手册

### 22、KDBKSSampleData1(string DatasetName, int iTagNamesType, string strTagNames, string strHistTime, int iTimeStampFormat, string strColStatistic, string strTagStatisticRange);

**功能：**

得到1组KS变量某一历史时刻数据的数据集(只有1行，第1列为时刻，其它列为各KS变量历史时刻值)。

**参数：**

DatasetName：数据集名称。

iTagNamesType：strTagNames的格式类型，1=变量名称组，多个变量名称，变量名称之间用","分隔，2=csv文件路径，因为有的用户要批量查询的变量个数很多，现场有用户要查多达40个变量，所以，变量名称组的长度很可能超过字符串变量128个字符的长度限制，所以提供一个从csv文件里导入变量名称组的功能。

strTagNames：变量名称组和或者csv文件路径。

strHistTime：历史时刻时间字符串。规定格式：YYYY-MM-DD HH:mm:ss.xxx。

iTimeStampFormat：设置数据集第1列的时间戳格式类型。与KDBKSRawData函数的取值范围一致。

strColStatistic：行统计设置，可以为空，min, max, averg的顺序可以任意排列。

strTagStatisticRange：变量统计范围，可以为空，空代表所有的变量都统计。格式为变量序号用逗号分隔，或用-指定序号范围，例如："1,3,5-9,13"。逗号之间必须是数字，或者是数字1-数字2。其它形式都算参数非法，如果该参数非法，则返回数据集，并统计全部变量。如果统计变量中有布尔变量，按0和1的值进行统计，如果统计变量中有字符串变量，都按0进行统计。

**返回值：**

True：执行成功

False：执行失败

---

命令语言函数速查手册

### 23、KDBKSSampleData2(string DatasetName, int iTagNamesType, string strTagNames, string strStartTime, string strEndTime, int iSamplingInterval, int iTimeStampFormat, string strRowStatistic, string strColStatistic, string strTagStatisticRange);

**功能：**

得到一个第1列为时间，其它列为KS变量采样值的宽表数据集。

**参数：**

DatasetName：数据集名称。

iTagNamesType：strTagNames格式类型，1=变量名称组，2=csv文件路径。

strTagNames：变量名称组和或者csv文件路径。

strStartTime：起始时间字符串。规定格式：YYYY-MM-DD
HH:mm:ss。

strEndTime：终止时间字符串，小于等于当前时间。规定格式：YYYY-MM-DD
HH:mm:ss。

iSamplingInterval：采样间隔，单位：毫秒。

iTimeStampFormat：设置数据集第1列的时间戳格式类型，与KDBKSRawData函数的取值范围一致。

strRowStatistic：列统计设置，可以为空，也可以min, max, averg的顺序任意排列。

strColStatistic：行统计设置，可以为空，也可以min, max, averg的顺序任意排列。

strTagStatisticRange：变量统计范围，可以为空，空代表所有的变量都统计。格式为变量序号用逗号分隔，或用-指定序号范围，例如："1,3,5-9,13"。

**返回值：**

True：执行成功

False：执行失败

---

命令语言函数速查手册

### 24、KDBKHRawData(string DatasetName, string ConnectStr, string strTagName, string strStartTime, string strEndTime, int iTimeStampFormat, string strRowStatistic);

**功能：**

得到1个第1列为时间，第2列为某KH变量原始历史值，第3列为某KH变量质量戳的数据集。

**参数：**

DatasetName：数据集名称。

ConnectStr：工业库连接字符串，调用工业库的关系库接口，格式为："DSN=SQL\_KH; ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0";

strTagName：工业库变量名称。

strStartTime：起始时间字符串。规定格式：YYYY-MM-DD HH:mm:ss。

strEndTime：终止时间字符串。规定格式：YYYY-MM-DD
HH:mm:ss。

iTimeStampFormat：设置数据集第1列的时间戳格式类型。与KDBKSRawData函数的取值范围一致。

strRowStatistic：列统计设置，可以为空，min, max, averg的顺序可以任意排列。

**返回值：**

True：执行成功

False：执行失败

---

命令语言函数速查手册

### 25、KDBKHSampleData1(string DatasetName, string ConnectStr, int iTagNamesType, string strTagNames, string strHistTime, int iSamplingMode, int iTimeStampFormat, string strColStatistic, string strTagStatisticRange);

**功能：**

得到一组KH变量某一历史时刻数据的数据集 (只有1行，第1列为列为时刻，其它列为各工业库变量历史时刻值)。

**参数：**

DatasetName：数据集名称。

ConnectStr：工业库连接字符串，调用工业库的关系库接口，格式为："DSN=SQL\_KH; ServerAddress=127.0.0.1; ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0";

siTagNamesType：strTagNames格式类型，1=变量名称组，2=csv文件路径。

strTagNames：工业库变量名称组和或者csv文件路径。

strHistTime：历史时刻时间字符串。规定格式：YYYY-MM-DD HH:mm:ss.xxx。

iSamplingMode：采样模式。0=最近点采样;1线性插值采样。

iTimeStampFormat：设置数据集第1列的时间戳格式类型。与KDBKSRawData函数的取值范围一致。

strColStatistic：行统计设置，可以为空，min, max, averg的顺序可以任意排列。

strTagStatisticRange：变量统计范围，为空代表所有的变量都统计。

**返回值：**

True：执行成功

False：执行失败

例：

1)通过变量名称组方式查询工业库中变量某时刻的数据集

String ConnectStr="DSN=SQL\_KH;
ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0";

KDBKHSampleData1("DatasetName",
ConnectStr, 1, "test\_Tag1,test\_Tag2", "2016-3-17
15:21:00.356", 1, 1, "", "");

2）通过从csv文件中获取变量名称查询工业库变量某时刻的数据集

String ConnectStr="DSN=SQL\_KH;
ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0";

KDBKHSampleData1("DatasetName", ConnectStr, 2, "D:\KH\Kh.csv",

"2016-3-17 15:21:00.356", 1, 1, "", "");

注：CSV文件新建成功后，以记事本方式打开，将工业库变量名称放进去，逗号隔开即可。

---

命令语言函数速查手册

### 26、KDBKHSampleData2

**函数原型：**

bool KDBKHSampleData2(string
DatasetName, string ConnectStr, int iTagNamesType, string strTagNames, string
strStartTime, string strEndTime, int iSamplingMode, int iSamplingInterval, int
iTimeStampFormat, string strRowStatic, string strColStatistic, string
strTagStatisticRange);

**功能：**

得到一个第1列为时间，其它列为各工业库变量采样值的宽表数据集。

**参数：**

DatasetName：数据集名称。

ConnectStr：工业库连接字符串，调用关系库接口，连接字符串ConnectStr ="DSN=SQL\_KH;ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0";

iTagNamesType：strTagNames格式类型，1=变量名称组，2=csv文件路径。

strTagNames：工业库变量名称组和或者csv文件路径。

strStartTime：起始时间字符串。规定格式：YYYY-MM-DD HH:mm:ss。

strEndTime：终止时间字符串。规定格式：YYYY-MM-DD HH:mm:ss。

iSamplingMode：采样模式。0=最近点采样;1线性插值采样。

iSamplingInterval：采样间隔，单位：毫秒。

iTimeStampFormat：设置数据集第1列的时间戳格式类型。与KDBKSRawData函数的取值范围一致。

strRowStatistic：列统计设置，可以为空，min, max, averg的顺序可以任意排列。

strColStatistic：行统计设置，可以为空，min, max, averg的顺序可以任意排列。

strTagStatisticRange：变量统计范围，可以为空，空代表所有的变量都统计。格式为变量序号用逗号分隔，或用-指定序号范围，例如："1,3,5-9,13"。

**返回值：**

True：执行成功

False：执行失败

例：

1)通过变量名称组方式查询工业库中变量某时刻的数据集

String ConnectStr="DSN=SQL\_KH;
ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0";

KDBKHSampleData1("DatasetName",
ConnectStr, 1, "test\_Tag1,test\_Tag2", "2016-3-17 15:21:00",
"2016-3-18 15:21:00", 0, 1000, 0,"", "","");

2）通过从csv文件中获取变量名称查询工业库变量某时刻的数据集

String ConnectStr="DSN=SQL\_KH;
ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0";

KDBKHSampleData1("DatasetName",
ConnectStr, 2, "D:\KH\kh.csv", "2016-3-17 15:21:00", "2016-3-18
15:21:00", 0, 1000, 0,"", "","");

注：CSV文件新建成功后，以记事本方式打开，将工业库变量名称放进去，逗号隔开即可。

---

命令语言函数速查手册

### 27、KDBKHSampleData3

**函数原型：**

bool
KDBKHSampleData3(string DatasetName, string ConnectStr, int iTagNamesType,
string strTagNames, string strStartTime,string strEndTime, int
iSamplingMode,int iSamplingInterval,int iTimeStampFormat, string
strRowStatistic, string strColStatistic, string strTagStatisticRange);

**功能：**

获取数据集时对变量质量戳进行筛选，只获取质量戳为GOOD（192）的变量

**参数：**

各参数定义与 KDBKHSampleData2函数一样，详述如下：

DatasetName:数据集名称。

ConnectStr:工业库连接字符串，调用关系库接口,连接字符串ConnectStr ="DSN=SQL\_
KH;ServerAddress=127.0.0.1;ServerPort=S678:UID=sa:pwd=sa:NetworkTimeout=0":

iTagNamesType:
strTagNames格式类型，1=变量名称组, 2=csv文件路径。

strTagNames:变量名称组和或者csv文件路径。

strStartTime:起始时间字符串。规定格式: YYYY-MM-DD HH:mm:ss。

strEndTime:终止时间字符串。规定格式: YYYY-MM-DD HH:mm:sso

iSamplingMode:采样模式。0=最近点采样;1线性插值采样。

iSamplingInterval:采样间隔，单位:毫秒。

iTimneStampFormat:设置数据集第1列的时间戳格式类型。与KDBKSRawData函数的取值范围-致。

strRowStatistic:列统计设置,可以为空。

strColStatistic:行统计设置，可以为空。

strTagStatisticRange:变量统计范围，可以为空，空代表所有的变量都统计。格式为变量序号用逗号分隔，或用-指定序号范围，例如: "1,3,5-9.13"。

**返回值:**

True:执行成功

False:执行失败

---

命令语言函数速查手册

### 28、KDBKHSampleDataByAPI

**函数原型：**

KDBKHSampleDataByAPI(string
DatasetName, string strConnectStr, int iTagNamesType, string strTagNames,
string strStartTime, string strEndTime, int iSamplingMode, int
iSamplingInterval, int iTimeStampFormat, string strRowStatistic, string
strColStatistic, string strTagStatisticRange, int iThreadCount);

**功能：**

使用API接口查询，提高查询速度

**参数：**

DatasetName：查询结果数据集。

strConnectStr：工业库连接字符串，

"ServerAddress=127.0.0.1;ServerPort=5678;UID=sa;pwd=sa;NetworkTimeout=0"，其中超时单位为毫秒，0表示不超时退出。

iTagNamesType：strTagNames格式类型，1=变量名称组，2=csv文件路径。

strTagNames：工业库变量名称组和或者csv文件路径。

strStartTime：起始时间字符串。规定格式：YYYY-MM-DD
HH:mm:ss。

strEndTime：终止时间字符串。规定格式：YYYY-MM-DD
HH:mm:ss。

iSamplingMode：采样模式。0 原始值采样（宽表不支持原始值采样）;1 线性插值采样;2 步进插值采样。

iSamplingInterval：采样间隔，单位：毫秒。

iTimeStampFormat：设置数据集第1列的时间戳格式类型。与KDBKSRawData函数的取值范围一致。

strRowStatistic：行统计设置，可以为空。

strColStatistic：列统计设置，可以为空。

strTagStatisticRange：变量统计范围，可以为空，空代表所有的变量都统计。格式为变量序号用逗号分隔，或用-指定序号范围，例如："1,3,5-9,13"。

iThreadCount：线程个数，1~8。

**返回值:**

True:执行成功

False:执行失败

---

命令语言函数速查手册

### 29、KDBSetCMDTimeOut (int nCmdTimeOut)

**功能：**

设置查询数据库时的查询超时时间

**参数：**

nCmdTimeOut：设置的查询超时时间，单位是s

**返回值：**

True：执行成功

False：执行失败

string
ConnectStr="DSN=100SQL;DATABASE=BRHTEST;UID=sa;PWD=kingview;"; //连接字符串

string SqlStr="select \* from
Alarm"; //查询字符串

KDBGetConnectID("strConnectID",
ConnectStr);  //获取长连接字符串

KDBSetCMDTimeOut(60);  //设置查询超时为60S

KDBGetDataset1("DatasetName",
"strConnectID", SqlStr);  //查询数据集

**注：若不设置查询超时时间，默认的查询超时时间为30S**

---

命令语言函数速查手册

## 十二、报警函数

---

命令语言函数速查手册

### 1、AckByGroupName(string groupName)

**功能****：**

此函数用户确认指定报警组中的报警记录

**参数：**

groupName：报警组名称，如果是远程站点的话必须指明站点名，字符串类型

**返回值：**

无返回值。

**调用格式:**

AckByGroupName("Alarm\_group1");  
//确认本站点的报警组

AckByGroupName("\\aa\Alarm\_group1");   
//确认远程站点的报警组

---

命令语言函数速查手册

### 2、AckByTagName(string tagName)

**功能****：**

此函数用于确认指定名称的报警记录

**参数：**

tagName：变量名称，如果是远程站点的话必须指明站点名，字符串类型

**返回值：**

无返回值。

**调用格式:**

AckByTagName("tag1");  
//确认本地报警点

AckByTagName("\\aa\tag1");  
//确认远程报警点

---

命令语言函数速查手册

### 3、AlarmConfirmDlgEnableFilter(bool bEnabled)

**功能****：**

此函数用于使能报警确认对话框用户过滤功能

**参数：**

bEnabled：布尔型，是否使能，1表示使用过滤功能，0表示不使用过滤功能

**返回值：**

Int类型，修改成功返回0，失败返回1

**调用格式:**

AlarmConfirmDlgEnableFilter(1);  
//表示在报警确认对话框中使用用户过滤功能

---

命令语言函数速查手册

### 4、AlarmConfirmDlgAddUsers（string UserNames, bool bCheck）

**功能****：**

此函数用于使用用户过滤功能之后，设置在报警确认对话框中用户名的下拉框中显示的用户

**参数：**

UserNames：字符串类型，若添加多个用户名，各个用户名之间用逗号隔开

bCheck：布尔型，是否进行同名检查，值为false时，不做同名检查，多次添加相同用户名，可添加成功；值为
true 时，程序检查是否用户同名，相同用户名只会添加一次，但会稍微耗时

**返回值：**

Int类型，添加成功的用户数

**调用格式:**

AlarmConfirmDlgAddUsers（"KVUser1, KVUser2,
KVUser3",1）;   //表示在报警确认对话框中用户名的下拉框中显示KVUser1，KVUser2，KVUser3用户

---

命令语言函数速查手册

### 5、AlarmConfirmDlgRemoveUser（string UserNames）

**功能****：**

此函数用于使用用户过滤功能之后，删除报警确认对话框中用户名下拉框中用户

**参数：**

UserNames：字符串类型，若添加多个用户名，各个用户名之间用逗号隔开

**返回值：**

Int类型，成功返回移除的用户数，若程序出现异常则返回值为负数

**调用格式:**

AlarmConfirmDlgRemoveUser（"KVUser1, KVUser2,
KVUser3"）;  //表示在报警确认对话框中的用户名的下拉框中去掉这三个用户

---

命令语言函数速查手册

### 6、AlarmConfirmDlgClearUsers()

**功能****：**

此函数用于使用用户过滤功能之后，此函数用于清空列表

**参数****：**

无

**返回值：**

Int类型，成功返回0，文件操作失败返回负数

**调用格式:**

AlarmConfirmDlgClearUsers();   //清空报警确认对话框的用户名的下拉框中用户

---

命令语言函数速查手册

### 7、GetAlarmNumByFilterString (string strStationName, string strFilter)

**功能：**

此函数用于获得当前指定过滤条件的实时报警数目。

**参数：**

strStationName，站点名，如：local

strFilter，过滤条件，字符串类型；

**返回值：**

>0 -- 报警数目，整型

-1 -- 获取失败

-2 -- 参数错误

-3 -- 站点不存在

-4 -- 读取文件失败

**调用格式：**

int GetAlarmNumByFilterString ("local", "AlarmType ==
"+StrChar(34)+"HIHI"+StrChar(34)+"&& TagName
INCLUDE"+StrChar(34)+"Tag1"+StrChar(34));

注：其中StrChar(34)代表一个双引号。

如果参数是字符串变量，运行时可以直接给字符串变量赋值为：

AlarmType == "高高" && TagName INCLUDE "Tag1"

---

命令语言函数速查手册

### 8、GetNoAckNumByFilterString (string strStationName, string strFilter)

**功能：**

此函数用于获得当前指定过滤条件的未确认的实时报警数目。

**参数：**

strStationName，站点名

strFilter，过滤条件，字符串类型；

**返回值：**

>0 -- 报警数目，整型

-1 -- 获取失败

-2 -- 参数错误

-3 -- 站点不存在

-4 -- 读取文件失败

**调用格式：**

int GetNoAckNumByFilterString ("local", "AlarmType ==
"+StrChar(34)+"HIHI"+StrChar(34)+"&& TagName
INCLUDE"+StrChar(34)+"Tag1"+StrChar(34));

注：其中StrChar(34)代表一个双引号。

如果参数是字符串变量，运行时可以直接给字符串变量赋值为：

AlarmType == "高高" && TagName INCLUDE "Tag1"

---

命令语言函数速查手册

### 11、GetAlarmNumByFilterStringFromFile(string strStationName, string strFilterFileName)

**功能：**

此函数通过读取文件，从文件中取得符合过滤条件的语法，获取实时报警数目。

**参数：**

strStationName，站点名，如：local

strFilterStrFileName：过滤条件文件，该文件必须是txt格式

**返回值：**

>0 -- 报警数目，整型

-1 -- 获取失败

-2 -- 参数错误

-3 -- 站点不存在

-4 -- 读取文件失败

**调用格式：**

int GetAlarmNumByFilterStringFromFile ("local","e:\q.txt");

txt文件里面的内容：AlarmType == "高高" && TagName INCLUDE "Tag1"

---

命令语言函数速查手册

### 9、GetNoAckNumByFilterStringFromFile(string strStationName, string strFilterFileName)

**功能：**

此函数通过读取文件，从文件中取得符合过滤条件的语法，获取未确认的实时报警数目。

**参数：**

strStationName，站点名，如：local

strFilterStrFileName：过滤条件文件，该文件必须是txt格式

**返回值：**

>0 -- 报警数目，整型

-1 -- 获取失败

-2 -- 参数错误

-3 -- 站点不存在

-4 -- 读取文件失败

**调用格式：**

int GetNoAckNumByFilterStringFromFile ("local","e:\q.txt");

txt文件里面的内容：AlarmType == "高高" && TagName INCLUDE "Tag1"

---

命令语言函数速查手册

### 10、GetAlarmNum（string GroupName，int AlarmType，string PriorityRange）

**功能：**

获得指定报警组、报警类型且大于等于一定优先级的当前报警数目。此函数为报警记录分类统计函数，仅对实时报警进行统计。

**参数：**

GroupName：报警组名称，如果是远程站点的话必须指明站点名称，字符串类型。可指定为RootNode，统计RootNode组下的变量。

AlarmType：报警类型，整型，可设置0至10。

0：全部统计

1：低低报警

2：低报警

3：高报警

4：高高报警

5：大偏差报警

6：小偏差报警

7：变化率报警

8：离散开报警

9：离散关报警

10：离散变化报警

PriorityRange：优先级范围，字符串型，可指定某一优先级或优先级范围，中间用逗号分隔，或用-指定优先级范围，也可为空，为空代表统计所有优先级，如果参数设置错误，函数执行不成功。

**返回值：**

整型

成功返回报警记录数，失败返回-1。

**调用格式：**

GetAlarmNum（"group1"，1，"30,50-60"）；//本地报警组

说明：统计"group1"报警组中，优先级为30，和优先级在50－60范围内的低低报警的个数。

GetAlarmNum（"\\aa\group1"，1，"30,50-60"）；//远程报警组

说明：统计站点名为aa的"group1"报警组中，优先级为30，和优先级在50－60范围内的低低报警的个数。

---

命令语言函数速查手册

### 11、GetNoAckNum (string GroupName，int AlarmType，string PriorityRange)

**功能：**

获得指定报警组、报警类型且大于等于一定优先级的当前未确认的报警数目。此函数为报警记录分类统计函数，仅对实时报警进行统计。

**参数：**

GroupName：报警组名称，如果是远程站点的话必须指明站点名称，字符串类型。可指定为RootNode，统计RootNode组下的待确定的变量报警个数。

AlarmType：报警类型，整型，可设置0至10；

0：全部统计

1：低低报警

2：低报警

3：高报警

4：高高报警

5：大偏差报警

6：小偏差报警

7：变化率报警

8：离散开报警

9：离散关报警

10：离散变化报警

PriorityRange：优先级范围，字符串型，可指定某一优先级或优先级范围，中间用逗号分隔，或用-指定优先级范围，也可为空，为空代表统计所有优先级，如果参数设置错误，函数执行不成功。

**返回值：**

整型

成功返回为确认报警记录数，失败返回-1。

**调用格式：**

GetNoAckNum（"group1"，1，"30,50-60"）；//本地报警组

说明：统计"group1"报警组中，优先级为30，和优先级在50－60范围内的待确定低低报警的个数。

GetNoAckNum（"\\aa\group1"，1，"30,50-60"）；//远程报警组

说明：统计站点名为aa的"group1"报警组中，优先级为30，和优先级在50－60范围内的待确定的低低报警的个数。

---

命令语言函数速查手册

### 12、GetAlarmNumByGroup(string groupName)

**功能：**

此函数用户获得指定报警组当前的报警数目

**参数：**

groupName：报警组名称，如果是远程站点的话必须指明站点名称，字符串类型

**返回值：**

整型

**例如：**

GetAlarmNumByGroup("Alarm\_group1");     //本地报警组

GetAlarmNumByGroup("\\aa\Alarm\_group1");    //远程报警组

---

命令语言函数速查手册

### 13、GetNoAckNumByGroup(string groupName)

**功能****：**

此函数用于获得指定报警组当前没有确认的报警数

**参数：**

groupName：报警组名称，如果是远程站点的话必须指明站点名称，字符串类型

**返回值：**

整型

**调用格式:**

GetNoAckNumByGroup ("Alarm\_group1");    
//本地报警组

GetNoAckNumByGroup
("\\aa\Alarm\_group1");    //远程报警组

---

命令语言函数速查手册

### 14、GetGroupName(string stationName, int groupID)

**功能****：**

此函数为通过报警组的ID号获得报警组名称。在KingSCADA中，每个报警组除了名称外，还有ID号，ID号可以通过报警域
.Group获得，如果要获得相应的报警组，就需要使用该函数

**参数：**

stationName：报警点所在的站点名称，如果是本地站点的话，该项可以省略，字符串类型

groupID：报警点所在的报警组的ID号，整型

**返回值：**

字符串类型

**调用格式:**

GetGroupName("",Tag1.group);    
//本地站点的报警点

GetGroupName("aa",\\aa\Tag1.group);    
//远程站点的报警点

---

命令语言函数速查手册

### 15、ResetByGroupName(string groupName)

**功能：**

从实时报警窗中删除指定报警组中以被确认过的多条报警记录，使用该函数的前提是报警删除模式中选择矩形中的选项：

**参数：**

groupName：报警组名称，如果是远程站点的话必须指明站点名称，字符串类型

**返回值：**

无返回值。

**调用格式:**

ResetByGroupName
("Alarm\_group1");     //本地报警组

ResetByGroupName
("\\aa\Alarm\_group1");    //远程报警组

---

命令语言函数速查手册

### 16、ResetByTagName(string tagName)

**功能****：**

从实时报警窗中删除以被确认过的报警记录，使用该函数的前提是报警删除模式中选择矩形中的选项，见ResetByGroupName函数中的图

**参数：**

tagName：变量名称，如果是远程站点的话必须指明站点名称，字符串类型

**返回值：**

无返回值。

**调用格式:**

ResetByTagName("Tag1");   
//本地变量

ResetByTagName("\\aa\Tag1");   
//远程变量

---

命令语言函数速查手册

## 十三、其它函数

---

命令语言函数速查手册

### 1、CreateCsvFile2(string strCsvFilePath, int iFilter)

**功能：**

输出连接服务器的客户端信息，并保存到csv文件。注：Csv文件格式为NetNodeName,
MachineIP, ApplicationName,UserName。

**参数：**

strCsvFilePath：生成的csv文件路径（含文件名称），不能为空。

iFilter：=0：输出所有的客户端用户信息，=1：仅输出CS客户端用户信息，=2：仅输出BS客户端用户信息（web客户端）。

**返回值：**

布尔型。保存成功，返回True；保存失败，返回Flase。

***注：***

***1******、******strCsvFilePath******为空，或者******iFilter******为******0******，******1******，******2******以外的数，函数都会返回失败***

***2******、******strCsvFilePath******可以有后缀******.csv******也可以没有，没有的话，函数中会自动加上。***

---

命令语言函数速查手册

### 2、DText(bool Value, string strOnMsg, string strOffMsg)

**功能：**

此函数用于按离散变量的值动态地改变文本

**参数：**

Value：离散型变量名

strOnMsg：当Value=1时显示的文本，字符串类型

strOffMsg：当Value=0时显示的文本，字符串类型

**返回值：**

字符串类型

**调用格式:**

Str =Dtext(Switch, "LightOn", "LightOff");

当Switch＝1时，Str的值为LightOn，当Switch＝0时，Str的值为LightOff

---

命令语言函数速查手册

### 3、VarRefAddress(string RefTagName, string RefedTagName)

**功能****：**

此函数用于在变量之间建立引用关系，可以通过VarRefAddressRelease函数解除引用关系

**参数****：**

RefTagName：引用变量名称，字符串类型

RefedTagName：被引用变量名称，字符串类型

**返回值****：**

整型

0代表成功，其余值都代表失败

**举例：**

第一步：在数据词典中建立一个引用变量：

变量名：P\_CIETD

变量类型：引用变量

数据类型：内存整型

第二步：在数据词典中建立三个IO变量：

变量名：P\_CIETD1、P\_CIETD2、P\_CIETD3

变量类型：基本变量

数据类型：IO整型

其他属性根据需要定义

第三步：在画面上添加一个文本框和三个按钮：

文本框设置"模拟值输出"动画链接：\\local\P\_CIETD

三个按钮的鼠标左键弹起脚本程序分别为：

VarRefAddress("\\local\P\_CIETD","\\local\P\_CIETD1"
);

VarRefAddress("\\local\P\_CIETD","\\local\P\_CIETD2");

VarRefAddress("\\local\P\_CIETD",
"\\local\P\_CIETD3");

第四步：进入运行环境，单击第一个按钮后文本框中显示P\_CIETD1变量的值;单击第二个按钮后，文本框中显示P\_CIETD2变量的值;单击第三个按钮后显示P\_CIETD3变量的值。

***注：******1******、当该引用变量已经指向一个变量时，这时再引用其他变量，那么该引用变量与原来所指向变量的引用关系随即解除******,***

***2******、引用变量和被引用变量的数据类型必须相同，否则不能建立引用***

---

命令语言函数速查手册

### 4、VarRefAddressRelease(string RefTagName)

**功能****：**

此函数用于解除引用变量的引用关系

**参数：**

RefTagName：引用变量的名称，字符串类型

**返回值：**

整型

0代表成功，其余值都代表失败

**调用格式:**

VarRefAddressRelease("\\local\P\_CIETD");

---

命令语言函数速查手册

### 5、CreateExcelFile(string strExcelFilePath，string strTemplateFilePath，string strTemplateFileInitFunc)

**功能：**

本函数有调用excel，生成excel文件后结束excel的功能，所以，excel模板文件自带函数里不能有类似Application.exit的关闭excel的语句，如果有，会导致本函数调用的excel进程无法结束，在任务管理器的进程列表里会留下1个没退出的excel进程需要手动关闭，每次执行完本函数都会多出一个excel进程，如果不关闭，退出KingSCADA运行系统时，还会在任务管理器的进程列表里留下KingSCADA运行系统进程需要手动关闭。

本函数会按strExcelFilePath参数的指定，自动生成1个excel文件，如果用户要在excel模板文件自带函数里实现自动生成excel文件的功能，不用本函数创建excel文件，那么就将该参数设成一个格式正确但无效的路径，即可，该参数不可为空。

**参数：**

strExcelFilePath：生成的Excel文件路径，含文件名称，不能为空。

strTemplateFilePath：Excel模板文件路径，含文件名称，不能为空。

strTemplateFileInitFunc：模板文件初始化函数名和参数，函数名和参数用","分隔，第1个为初始化函数名，只能有1个函数名，最多允许30个参数。

参数由参数值和参数类型组成，参数值和参数类型之间用"!"分隔，参数值!参数类型。

参数类型：1=int，2= float，3=
string，4= bool(书写格式：0或1，0代表false，1代表true)

strTemplateFileInitFun可以为空，参数也可以为空。

**返回值：**

1：生成报表成功

-1：Excel模板文件无效(路径未找到或者文件类型错误)

-2：无效宏文件扩展

-3：宏文件路径无效

-4：Excel输出文件路径无效

-41：未知输出文件类型

-42：输出文件已经存在

-5：Excel启动失败

-6：报表初始化失败

-7：装载报表模板失败

-8：装载报表宏启动失败

-9：生成报表失败,自动化调用错误

-10：未知报表类型

-11：报表名不能为空

-12：无效行号

-13：无效列号

-14：内存不足

-15：报表参数设置失败

-16：宏参数无效

**举例：**

1、在excel中打开Visual Basic编辑器，添加函数，并保存该文件为：templet.xls，如图所示：

2、在KingSCADA画面中添加一按钮，在按钮按下脚本编辑器中编写如下命令：

CreateExcelFile("E:\ExcelFile\file1.xls","E:\ExcelFile\templet.xls","con,5!1, 5.5!2,abc!3,1!4");

3、  进入KingSCADA运行系统，单击此按钮，系统将参数3中定义的数字或字符写入指定文件的指定位置中，如图所示：

---

命令语言函数速查手册

### 6、GetTagField(string TagName, string FieldName)

**功能：**通过变量域名称获取变量域值函数,该函数主要用于非周期性获取少量变量域值，不适合长期周期性的获取。

**参数：**

TagName：变量名称

FieldName：变量域名称

**返回值：**字符串，把变量域值转换成字符串返回。

---

命令语言函数速查手册

### 7、GetTagFieldFloat(string TagName, string FieldName)

**功能：**通过变量域名称获取浮点型变量域值函数。

**参数：**

TagName：变量名称

FieldName：变量域名称

**返回值：**双精度型，返回浮点型变量域值。

---

命令语言函数速查手册

### 8、GetTagFieldInt(string TagName, string FieldName)

**功能：**通过变量域名称获取整型变量域值函数。

**参数：**

TagName：变量名称

FieldName：变量域名称

**返回值：**整型，返回整型变量域值。

---

命令语言函数速查手册

### 9、SetTagField(string TagName, string FieldName, string Value)

**功能：**设置变量域的值。

**参数：**

TagName：变量名称

FieldName：变量域名称

Value：变量域的值

**返回值：**布尔型。True：设置成功；False：设置失败。

---

命令语言函数速查手册

### 10、SaveTagFiledValueToAEDB1(string strTagName，string strTagFieldName，string strOperateText)

**功能****：**

保存变量的任何域值到AEDB的Operate表里。

**参数****：**

strTagName：变量名

strTagFieldName：变量域名，为空时，其值默认为"Value"。

strOperateText：描述信息。

**返回值****：**

整型

0 成功，-1
未知错误，-2 变量名参数错误，-3
域名参数错误，-4 向数据库添加记录失败

***注：******保存变量任何域值到报警事件库的Operate******表，其中EventType******字段的数据为"******自定义"******。***

---

命令语言函数速查手册

### 11、IOGetServerRedunStatus(string strIOServerName)

**功能****：**

获取2个主从ioserver的冗余状态。

**参数****：**

strIOServerName：IOServer的名称

**返回值****：**

整型

-1 错误，0 无冗余状态，1 主IOServer激活，从IOServer监视，2 从IOServer激活，主IOServer监视，3 错误状态，主从IOServer都激活，4 错误状态，主从IOServer都监视。

---

命令语言函数速查手册

### 12、ShowColourDialog()

**功能：**

弹出颜色选择对话框，如果用户选择颜色，单击"ok"返回的值是选择的颜色对应的值，如果用户单击"cancel"返回的值是0。

**返回值：**

整形，4个字节，最高位无含义，次高位红色值，其次是蓝色值、最后是绿色值，即R、G、B值，分别是0-255。

---

命令语言函数速查手册

### 13、ThreeDESFileEncrypt(string key1, string key2, string srcfilepath, string desfilepath);

**功能：**

只能对纯文本形式txt或dat格式的文件加密，不能加密图表，加密生产的文件可以是txt或dat格式，可对加密的文件进行重复加密，加密后不会对源文件（准备加密的文件）做任何处理。

**参数：**

key1：一次加密密码，字符串类型，8位长度可输入数字、空格、字母，也可为空，如果大于8位则只取前8位；

key2：二次加密密码，字符串类型，8位长度，可输入数字、空格、字母，也可为空，如果大于8位则只取前8位；

srcfilepath：源文件，准备加密的文件的完整路径、文件名、扩展名，字符串类型；

desfilepath：目的文件，加密后生成文件的完整路径、文件名、扩展名，字符串类型，加密后将自动生产以此名称命名的文件；如果路径下已存在该名称命名的同格式文件，则加密后的文件内容会自动追加到原来文件内容的后面；

**返回值**：

整型

0--成功；

1--源文件不存在；

2--目的文件创建失败；

**调用格式：**

ThreeDESFileEncrypt("12","34","D:\新建 文本文档.txt","D:\新建
12345.dat");

---

命令语言函数速查手册

### 14、ThreeDESFileDecrypt(string key1, string key2, string srcfilepath, string desfilepath);

**功能：**

可对已加密txt或dat格式的纯文本文件进行解密，解密生产的文件可以是txt或dat格式，如果对未加密的文件进行解密将生成乱码文件，如果文件进行了重复加密则需要重复解密才能看到原来的内容；解密后不会对准备解密的文件做任何处理。

**参数：**

key1：加密时设置的一次加密密码，8位长度，可输入数字、空格、字母，也可为空，如果大于8位则只取前8位，如果输入与原来加密密码key1不一致则解密生成的文件将是乱码；

key2：加密时设置的二次加密密码，8位长度，可输入数字、空格、字母，也可为空，如果大于8位则只取前8位，如果输入与原来加密密码key2不一致则解密生成的文件将是乱码；

srcfilepath：源文件，准备解密的文件的完整路径、文件名、扩展名；

desfilepath：目的文件，解密后生成文件的完整路径、文件名、扩展名；解密后将自动生产以此名称命名的文件；如果路径下已存在该名称命名的同格式文件，则解密后的文件会自动追加到原来文件内容的后面；

**返回值：**

整型

0--成功；

1--源文件不存在；

2--目的文件创建失败；

**调用格式:**

ThreeDESFileDecrypt("12","34","D:\新建 12345.dat","D:\新建 5678.txt");

---

命令语言函数速查手册

### 15、Bit( string var, int bitNo )

**功能：**

此函数用以取得一个整型或实型变量某一位的值(0或1)。

**参数说明：**

Var：变量名称，字符串类型

bitNo：要取此变量的位的序列号，取值范围：
1,2,3,……32。

**返回值：**

整形

1： 若变量Var的第bitNo位为1，返回值为1。

0：若变量Var的第bitNo位为0，返回值为0。

-1：操作失败返回-1。

**调用格式：**

Bit("Tag1", 1 );

函数的功能是取变量Tag1的第一位的值。

---

命令语言函数速查手册

### 16、BitSet( string Var, int bitNo, bool bitvalue)

**功能：**

此函数将一个整型或实型变量的任一位置为指定值(0或1)。

**参数：**

Var：要操作的变量,字符串类型；

bitNo：要操作的变量的位的序列号，取值范围： 1,2,3,……32。

bitvalue：要写入变量的某一位的值，取值范围：0或1

**返回值：**

true：位操作成功

false：位操作失败

**调用格式：**

BitSet( "Tag1",
5, 0);

函数的功能是：给变量Tag1的第5位写入0。

---

命令语言函数速查手册

### 17、EmailAlarm(bool bEmailAlarmEnable)

**功能：**

设置报警转发(报警发送email）功能是否使能。

**参数：**

bEmailAlarmEnable：是否使能。TRUE:使能；FALSE:不使能。

**返回值：**

True：设置成功

False：设置失败

**调用格式：**

EmailAlarm（true）；

---

命令语言函数速查手册

### 18、EditAlarmNotify()

**功能：**

此函数可弹出报警转发配置对话框。在此对话框中可以在线配置Email发送端、短信发送端、RTX发送端、过滤器、接收器等。点击"确定"则这些配置信息将传给服务器并保存到kxSysCfg.db文件中，如果存在冗余服务器，则这些配置信息也会同步到冗余服务器中。点击"取消"，则取消修改。

**返回值：**

True：设置成功

False：设置失败

**调用格式：**

EditAlarmNotify()；

---

命令语言函数速查手册

### 19、GetLocalIP()

**功能：**

获取本机IP

**返回值：**

返回本机IP，字符串类型。

---

命令语言函数速查手册

### 20、WindowSize(int nFlag)

**功能：**

控制窗口最大化，最小化。

**参数：**

nFlag：0：最大化，1：最小化

**返回值：**

无返回值。

**调用格式：**

WindowSize（1）；

---

命令语言函数速查手册

### 21、SubscribeTagFunc(string TagNamesArrayName)

**功能：**

订阅数组内的变量名

**参数：**

TagNamesArrayName：变量名组成的Array名称，用CreatArray脚本函数创建

**返回值：**

整型

1 成功；小于1 失败；

**调用格式：**

CreatArray("Array",
3, 2);

SetArrayElement("Array",
0, "\\local\vvv1");

SubscribeTagFunc("Array");

---

命令语言函数速查手册

### 22、UnSubscribeTagFunc(string TagNamesArrayName)

**功能：**

反订阅数组内的变量名

**参数：**

TagNamesArrayName：变量名组成的Array名称，用CreatArray脚本函数创建

**返回值：**

整型

1 成功；小于1 失败；

**调用格式：**

UnSubscribeTagFunc("Array");

---

命令语言函数速查手册

### 23、GetRGBValue(int Red, int Green, int Blue)

**功能：**

获取颜色数值，返回值直接可以作为CallSetAttrVal的参数适用

**参数：**

Red：红色素值（范围：0--255）

Green：绿色素值（范围：0--255）

Blue：蓝色素值（范围：0--255）

**返回值：**

整型

**调用格式：**

result=GetRGBValue(255,
255,7854);

---

命令语言函数速查手册

### 24、BitI( iTagVal, bitNo )

**功能：**

此函数用以取得一个整型或实型变量某一位的值(0或1)。

**参数说明：**

iTagVal：整型或实型变量值

bitNo：要取此变量的位的序列号，取值范围：
1,2,3,……32。

**返回值：**

整形

1： 若变量值的第bitNo位为1，返回值为1。

0：若变量值的第bitNo位为0，返回值为0。

-1：操作失败返回-1。

**调用格式：**

BitI(Tag1, 1 )

函数的功能是取变量Tag1的第一位的值。

***注：****BitI()**函数可以直接动画连接输出和脚本，动画输出结果会根据变量值变化；Bit()**函数不能用于动画输出，建议仅用于脚本。*

---

命令语言函数速查手册

### 25、GetExcelSheetsNum(string ExcelPath)

**功能：**

获取指定excel的sheet数目；

**参数说明：**

ExcelPath：Excel路径及名称

**返回值：**

整形

返回的是获取到的excel中sheet数目

**调用格式：**

GetExcelSheetsNum("c:/1.xlsx")

---

命令语言函数速查手册

### 26、GetExcelSheetNameBySheedID  (string ExcelPath, int SheetID)

**功能：**

获取指定Excel指定Sheet的名字

**参数说明：**

ExcelPath：Excel路径及名称

SheetID:Sheet页ID，从0开始

**返回值：**

字符串

返回的是Sheet页的名称

**调用格式：**

GetExcelSheetNameBySheedID ("c:/1.xlsx", 1);

---

命令语言函数速查手册

### 27、NCreatTimer(string nName, int nTime);

**功能：**

异步延时函数，创建一个定时器，初始返回值为False，并在设置的时间后置为True

**参数说明：**

nName：创建内部定时器的名称

nTime：延时时间，单位秒

**返回值**

布尔类型

初始返回值为False，并在设置的时间后置为True

**调用格式：**

bool a = NCreatTimer("cc",10);

注：内部定时器名称不要重复

---

命令语言函数速查手册

### 28、StrToDecimal(string strText, int nbase)

**功能：**

将16进制字符串，8进制字符串，2进制字符串转换为整数

**参数：**

strText：字符串类型，表示输入的16/8/2进制字符串，可包含+/-符号。

nbas：整型参数，表示输入字符串表示的进制。如16表示16进制，8表示8进制，2表示2进制。

**返回值：**

整型，表示输入字符串转换后的结果

注：若输入字符串非法，返回2147483647。

若如数字符串转换后超过32位整型最大值，会截断显示。

---

命令语言函数速查手册

### 29、TimingSaveRunTimeParam();

**功能**：

运行态保存值及参数

**参数**：

无

**返回值**：

0表示成功

其他表示失败

注：需要勾选保存值和保存参数，且当参数改变时，保存值功能才会生效。

---

命令语言函数速查手册

### 30、RequestJsonInfo(string lpUrl, int type, string strHeader, string strPostData, string strDataSetName)

**功能：**

向指定url发送restful请求，请求返回值保存到数据集中

**参数：**

lpUrl：restful接口的url；

type：请求类型，0表示get，1表示post

strHeader：请求头信息，如果使用默认请求头可以将其设定为空字符串””

strPostData：请求包体数据，为一个json字符串，如果type为get则此参数不会起作用。

strDataSetName：保存restful请求返回值的数据集名称。该数据集只能通过下面的ParseJsonInfo系列的函数进行解析。

**返回值：**

True：执行成功

False：执行失败

**例子：**

RequestJsonInfo("http://127.0.0.1:9008/var/getTagTable", 0,
"", "", "dataset1");

该函数会发送“http://127.0.0.1:9008/var/getTagTable”请求，请求类型为get，请求头为默认值，请求包体为空，返回的结果将放到一个叫dataset1的数据集中。

---

命令语言函数速查手册

### 31、RequestJsonInfo2(string lpUrl, int type, string strHeader, string strPostData)

**功能：**

向指定url发送restful请求，函数返回请求返回的字符串

**参数：**

lpUrl：restful接口的url；

type：请求类型，0表示get，1表示post

strHeader：请求头信息，如果使用默认请求头可以将其设定为空字符串””

strPostData：请求包体数据，为一个json字符串，如果type为get则此参数不会起作用。

**返回值：**

restful接口返回的字符串

**例子：**

RequestJsonInfo2("http://127.0.0.1:9008/var/getTagTable", 0,
"", "");

该函数会发送“http://127.0.0.1:9008/var/getTagTable”请求，请求类型为get，请求头为默认值，请求包体为空，函数将返回请求的返回值。

---

命令语言函数速查手册

### 32、ParseJsonInfoBool(string strDataSetName, string strFiledsNameSort, string strArraySort)

**功能：**

解析json字符串中某个数据类型为bool的属性的值

**参数：**

strDataSetName：RequestJsonInfo函数返回的数据集名称，如果该数据集不存在则会直接解析strDataSetName；

strFiledsNameSort：字段名称，如果是json是多层的话，每个字段之间用“\”隔开；

strArraySort：数组序号，若json不是数组则写0，若是数组的话写要获取数组的序号，从1开始。如果是json是多层的话，每个字段之间用“\”隔开

**返回值：**

要解析属性的值

**例子：**

假设一个json字符串为”{Success:true}”，该json字符串保存在数据集“DataSet1”中，则调用函数ParseJsonInfoBool(“DataSet1”,
“Success”, “0”)则可以得到json中Success属性的值；

假设一个json字符串为”{Object:{Success:true}}”，该json字符串保存在数据集“DataSet1”中，则调用函数ParseJsonInfoBool(“DataSet1”, “Object\Success”, “0\0”)则可以得到json中Object对象的Success属性的值；

假设一个json字符串为”{Array:[{Success:true},{Success:false}]}”，该json字符串保存在数据集“DataSet1”中，则调用函数ParseJsonInfoBool(“DataSet1”, “Array\Success”, “0\1”)则可以得到json中Array数组的第1个对象的Success属性的值；

---

命令语言函数速查手册

### 33、ParseJsonInfoDouble(string strDataSetName, string strFiledsNameSort, string strArraySort)

**功能：**

解析json字符串中某个数据类型为double的属性的值

**参数：**

strDataSetName：RequestJsonInfo函数返回的数据集名称，如果该数据集不存在则会直接解析strDataSetName；

strFiledsNameSort：字段名称，如果是json是多层的话，每个字段之间用“\”隔开；

strArraySort：数组序号，若json不是数组则写0，若是数组的话写要获取数组的序号，从1开始。如果是json是多层的话，每个字段之间用“\”隔开

**返回值：**

要解析属性的值

**例子：**使用方法与ParseJsonInfoBool相同

---

命令语言函数速查手册

### 34、ParseJsonInfoInt(string strDataSetName, string strFiledsNameSort, string strArraySort)

**功能：**

解析json字符串中某个数据类型为int的属性的值

**参数：**

strDataSetName：RequestJsonInfo函数返回的数据集名称，如果该数据集不存在则会直接解析strDataSetName；

strFiledsNameSort：字段名称，如果是json是多层的话，每个字段之间用“\”隔开；

strArraySort：数组序号，若json不是数组则写0，若是数组的话写要获取数组的序号，从1开始。如果是json是多层的话，每个字段之间用“\”隔开

**返回值：**

要解析属性的值

**例子****：**使用方法与ParseJsonInfoBool相同

---

命令语言函数速查手册

### 35、ParseJsonInfoString(string strDataSetName, string strFiledsNameSort, string strArraySort)

**功能：**

解析json字符串中某个数据类型为string的属性的值

**参数：**

strDataSetName：RequestJsonInfo函数返回的数据集名称，如果该数据集不存在则会直接解析strDataSetName；

strFiledsNameSort：字段名称，如果是json是多层的话，每个字段之间用“\”隔开；

strArraySort：数组序号，若json不是数组则写0，若是数组的话写要获取数组的序号，从1开始。如果是json是多层的话，每个字段之间用“\”隔开

**返回值：**

要解析属性的值

**例子：**使用方法与ParseJsonInfoBool相同