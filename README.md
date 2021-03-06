# DormitoryManagementSystem
我的移动技术开发与创业课程暨管理信息系统课程大作业。

## 图标
![image](https://github.com/PolarisRisingWar/DormitoryManagementSystem/raw/master/app/src/main/res/mipmap-hdpi/ic_launcher.png)

## 功能介绍
寝室管理系统，可以实现常规寝室管理功能，有个人中心等附属功能。  
通过JDBC连接数据库，使用华为云的MySQL5.6云数据库，APP客户端与数据库实时远程连接。天气界面使用和风天气的Android天气插件。  
囿于硬件、财务、开发能力所限，很多功能只有示意性界面，没有实际应用。有条件的话会继续开发的。  
### 主要功能  
#### 1 出入管理界面
##### 1.1 日常出入记录
从数据库获取学生日常出入寝室的时间
##### 1.2 离校登记、外部人员出入登记、大件物品出入登记
线上填写表格，自动获得用户的学号、姓名、填写日期等信息，由用户手动填写其他部分。分别在学生需要长期离校，学生亲友来访学生寝室，学生需要携带电脑、行李箱等大件物品出入寝室时填写
#### 2 危险品登记
##### 2.1登记记录
查看本人以往的危险品登记情况和审核情况（用户新填写的登记表为待审核状态）
##### 2.2危险品登记
线上填写表格，自动获得用户的学号和填写日期，由用户自行填写危险品名称（如吹风机、电热毯等）和备注（危险品品牌等）
#### 3 校卡管理界面
##### 3.1失物招领
发布、查看失物招领信息
##### 3.2校园卡挂失、补办、充值、转账（指从校园卡转账到热水和电费）
（无实际功能）
##### 3.3交易记录
从数据库获取用户使用校园卡进行交易的记录
#### 4 寝室日常管理
##### 4.1预约维修、购买水票
（无实际功能）
##### 4.2寝室自习室
（未开发）实时显示当前该寝室楼下自习室的座位使用情况
##### 4.3寝室用水
（无实际功能）根据各寝室楼学生各时段、季节的用水情况，向准备用水的学生动态实时推荐错峰用水时段
### 个人中心界面
#### 1 登录、退出、修改密码
因为业务逻辑是直接接入校方的学生数据库，所以没有开发注册功能
#### 2 个人信息
学号、姓名、入学年份等用户基本信息
#### 3 校园卡
校卡管理界面的交易记录界面
#### 4 通知信息
从数据库获取下发给用户的通知信息
#### 5 建议与反馈
实名或匿名提建议，发送到数据库
### 其他功能
#### 1 天气
动态获得成都本地数据（因为地域锁死，所以不能使用点击该控件后弹出的更换地点的功能）
#### 2 （未开发）备忘录、新闻
 

## 应用文件（已签名的apk、简单的录屏演示和截图、数据库截图）：
链接：https://pan.baidu.com/s/1iCxapUnLSxPZdkMuXvZNfg  
提取码：2nor  
复制这段内容后打开百度网盘手机App，操作更方便哦  
注：华为手机录屏的过程中，输入密码的部分会直接黑屏 

## Tips：
1.理想状态的架构应该是云端服务器+数据库，web端+移动应用APP。    
2.Android studio平台为3.3版。数据库为华为云的MySQL5.6。测试用的真机是华为p10，安卓版本是9.0.0。用了MySQL的JDBC和和风天气的SDK两个包。  
3.华为云是30天免费试用，所以很快就要过期了。  
4.我每次都用的是rebase（因为在这边更新的README和licence），不知道有没有什么问题（我的平时作业那个项目用的是merge，JSP那个项目没用IDE，直接用Git bash在本地写了README文件）。有问题算我倒霉。  
5.计划在CSDN或者什么博客上写个技术总结；也可能会直接放在GitHub上。因为不足和可改进之处太多，写不完了，所以一块写博客好了。

## 技术实现
### 1.数据库连接
直接用APP远程连接华为云数据库，开了外网远程连接。APP里可见的上传和下发数据基本都由数据库获得动态数据。由于数据库区域在北京，所以每次获得数据时会有约1秒左右的延时。
具体实现主要是做了一个SqlHelper类（出于保密考虑，这个文件没有上传到Git），有初始化、查询、更新、关闭数据库的方法，在每次需要调用数据库时实例化之后就能调用。
本应用使用网络的场景主要都是远程连接数据库，都在子线程中实现。需要从子线程中获得参数返回主线程时使用handle。
### 2.天气
使用和风天气的Android天气插件，用户每次打开APP时获取一次天气数据。
具体实现是一个横条的控件，我把它撑满了整个fragment界面。在Java文件中可以直接实例化，因为高封装，所以基本布局和内容都是第三方库内置的，只能更改位置、字体大小、背景等基本信息。
因为没有设置强制用户开放GPS功能，所以无法实时获取用户位置消息，所以直接写死了天气区域为成都，所以点击这个控件以后会弹出的H5 Activity界面的更换城市功能无法使用。因为这个页面在开发者文档里没有写，所以我也不知道怎么关掉它。
### 3.表单输入
实现了限制inputType，锁死EditView，自动获取当前日期（主要用Date）和时间（主要用Calendar），用户使用DatePickerDialog选择日期等功能。
### 4.登录和传参
使用SharedPreference，登录时一次性从数据库获得主要参数（学号、姓名等），因为登录后重新刷新界面，所以可以使所有界面获得SharedPreference中的数据。
### 5.多功能
功能真的多，我写到天荒地老，半死不活，灵魂出窍，还没写完。
### 6.弹窗制作
有简单的AlertDialog弹窗，也有填充布局（放照片、输入框等）的弹窗，弹窗的按钮和布局里的控件可以实现监听器。
很多较复杂或者常用的弹窗封装成类，便于复用。
### 7.fragment处理
因为fragment强依附于activity，所以很难用，但还是实现了fragment填充布局、内嵌viewpager和tablayout、设置监听器等功能。
### 8.返回键
因为按回退键后会返回上一个activity，所以在主页面的fragment之间不能用回退键切换，所以MainActivity重写了onBackPressed功能跳转回本页面。
### 9.从数据库获得数据的展示主要使用listview。较短的提示信息主要使用toast。
