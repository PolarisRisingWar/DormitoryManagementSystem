# DormitoryManagementSystem
我的移动技术开发与创业课程大作业。

### 5.31  14:02

  1.主页面为MainActivity，通过RadioGroup切换至个人中心、寝室事务、我的生活三个fragment。  
2.个人中心MyFragment界面架构。  
3.MyFragment跳转到登录界面。  
TODO：  
1.（checked）
2.（postponed） 
3.（checked） 
4.（checked）。

### 6.2

  1.在多个activity界面增加了返回按钮，监听事务为停止该activity的生命周期（finish）。  
  2.登录传参选用sharedpreference方式，退出即直接清楚editor。  
  3.登录前后，myfragment界面有所差别；因为还没有连数据库，觉得把很多小的activity加进来没意义，就先不加了。退出按钮清空sharedpreference。登录前点击多按钮会弹出toast。登陆后头像和用户名会改变。  
  4.做了一些比较表层的UI优化。  
TODO：  
1.连接数据库，把账号密码用户名之类的改成连接服务器获取。  
2.连接数据库以后：完善个人中心fragment下多个activity（个人信息为表，校园卡、通知信息为list（分页），建议与反馈、修改密码为表单），出入管理下多个activity（日常出入记录为list（分页），离校登记、外部人员出入、大件出入跳转到XX历史为表、新建为表单），危险品登记跳转到登记历史为表、新建为表单。  
校园卡管理界面：  
（1）失物招领：首先是个分页信息（丢卡为红色条，招领为灰色条），最上面可以模糊搜索，模糊搜索下面可以登记丢卡或招领的卡。登记丢卡的同时弹窗提醒学生“建议补办该卡”。**此处需要添加数据库管理相关功能**：学生登记的信息实时显示，找到的信息（由发布学生自己取消）可以消失（隐藏；本学生自己可以从“发布历史”中看到该条），补办成功的卡的失物招领信息自动消失。  
（2）挂失、补办、转账都是表、表单格式。  
（3）充值因为对接不到学校肯定不能仿真了，如果我比较闲的话会放个自己的支付宝收款码上去。  
（4）交易记录为list（分页）。  
我的生活界面（如果没时间做就不做了）：  
（1）天气使用《第一行代码Android》上提供的从和风天气接口获得天气，先做只有成都天气版的，后期有时间添加自动定位、手动选择城市功能。要是还有时间，多从几个接口获得天气数据。  
（2）备忘录：用sqlite存在本地就行。  
（3）新闻：直接找个新闻网站爬数据。  
3.封装apk。

## 存图截图点：
本来我想发无提取码版的，但由于形势紧张，百度网盘非要我发带提取码版的：  
链接：https://pan.baidu.com/s/1HNFmTJ3LvWoCmKZlxLdqhg  
提取码：n96o  
复制这段内容后打开百度网盘手机App，操作更方便哦  

## Tips：
1.管理员、宿管、教师界面没有搞。要求他们都去登录PC版。
