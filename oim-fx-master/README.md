### 置顶信息

QQ交流群255158245(已满)（主要是对JavaFX界面开发感兴趣）

QQ交流群186526119（主要是对即时通讯技术，服务端开发感兴趣）入群说明一下，发广告的会踢出群的

-----------------------------------------------------------------------------------------------------------
### OIM说明

  OIM是一套即时通讯的聊天系统，在这里献给大家，一方面希望能够帮助对即时通讯有兴趣研究的朋友，希望我们能够共同进步，另一个就是希望能够帮助到需要即时通讯系统的朋友或者企业，可以直接使用或者二次开发。可以用于公司内网、外网通讯、客服系统等，聊天系统。OIM项目可用于任何商业、个人作品中并且希望能够保留作者信息。如果OIM能够帮助到您，请点赞好评，加个星。

### 本项说明
  本项目是OIM时通讯系统的桌面版客户端。主要采用JavaFX开发，支持跨平台Windows、Mac、Linux等主流系统使用。
目前oim-fx 进行了重构，早期版本已经挪到old-0.0.1分支了。
oim-fx 重构主要代码初步完成，
主要完成了以下工作
1、oim-fx 打通了oim-e、oim-m、oim-web等其他客户端
2、架构方式调整，之前收到服务端消息推送后，直接掉用ui对象刷新改为由ui对象监听服务端变动，这样将业务代码和ui代码彻底解耦
 为后面安卓客户端打下基础
3、调整了些小的优化，群聊支持@功能
 
### 其他项说明
　关于服务端：
　服务端目前支持Socket、WebSocket，可以方便接入其他支持这个２个协议的语言平台。

  OIM时通讯系统是包含服务端和客户端整个完整体系，所以本项目以及其他客户端需要OIM的服务端支持。

  OIM服务端源码地址：https://gitee.com/oimchat/oim-server

　Web版客户端源码地址：https://gitee.com/oimchat/oim-web

　Web版演示地址：http://web.oimchat.com





### 项目截图演示


![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142219_7d44e784_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142219_6a70d826_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142219_acff6e48_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142219_357d7d22_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142220_04ac2d23_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142220_ba815af8_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142220_690d4618_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142220_95eaf7f4_7154.png "在这里输入图片标题")

![输入图片说明](https://git.oschina.net/uploads/images/2017/0609/165639_4e48e9a5_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142220_2a42fb9d_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142220_9d52c926_7154.png "在这里输入图片标题")

![输入图片说明](https://images.gitee.com/uploads/images/2019/0307/142220_050f1a99_7154.png "在这里输入图片标题")


### 以下是其他客户端

#### OIM-E是基于
地址：https://gitee.com/oimchat/oim-e

  ## 截图
<img src="./screenshot/client/e/1.png">
<img src="./screenshot/client/e/2.png">
<img src="./screenshot/client/e/3.png">
<img src="./screenshot/client/e/4.png">
<img src="./screenshot/client/e/5.png">
<img src="./screenshot/client/e/6.png">


