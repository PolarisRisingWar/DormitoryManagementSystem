# DormitoryManagementSystem
我的移动技术开发与创业课程大作业。

### 5.31  14:02

  1.主页面为MainActivity，通过RadioGroup切换至个人中心、寝室事务、我的生活三个fragment。  
2.个人中心MyFragment界面架构。  
3.MyFragment跳转到登录界面。  
TODO：  
1.以“41711042”，“41711042”用户名-密码对为例，进行传参尝试。登录前后对比效果大致为：登录前点击个人中心下多个按钮显示“请登录”弹窗/Toast，
登陆后为跳转至某Activity界面或Fragment界面；登陆后，登录按钮上的文字变成“退出”，点击转至退出界面。  
2.计划是用Android studio连接MySQL数据库。据说周峰下节课要讲，等等再做。  
3.activity和fragment的选择：个人中心下所有按钮跳转到activity；寝室事务跳转到activity，继续跳转到activity，继续跳转到activity；我的生活用viewpager
管理三个fragment天气、备忘录、新闻。  
4.像知乎一样按返回键跳转回第一页（寝室事务页）。
