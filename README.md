# IOREST

> 基于Spring4 mvc rest+JPA2实现的物联网REST服务后台，测试的网页客户端界面设计采用了Bootstrap3设计

## 项目说明
设计REST API实现物联网服务平台传感数据的传输及查询，REST的内容协商可以实现对文本数据（采用`application/json;charset=UTF-8`或者`application/x-www-form-urlencoded`）
和文本图像混合传感数据（采用`multipart/form-data`)进行数据采集，相关API如下：
* `POST http://{server:port}/rest/nodes`  创建监控节点
* `GET http://{server:port}/rest/nodes` 和 `GET http://{server:port}/rest/nodes/{nodeId}` 获取监控节点列表和某一监控节点
* `POST http://{server:port}/rest/nodes/{nodeId}/sensors` 创建某一监控节点传感器
* `GET http://{server:port}/rest/nodes/sensors` 和 `GET http://{server:port}/rest/sensors/{sensorId}` 获取某一监控节点的传感器列表和某一监控节点下的某一个传感器
* `POST http://{server:port}/rest/nodes/sensors/{sensorId}/temperatures` 发送一个采集得到的温度
* `GET http://{server:port}/rest/nodes/{nodeId}/images/newest`查询某一节点下最新的图像数据
*  通过对相关的URL进行`PUT`和`DELETE`,对节点和传感器资源进行改删操作

* 以下是通过网页端进行模拟测试（网页端的图像采集模块在有带摄像头的火狐44.0.2和IE EdGE，手机版火狐能够很好的运行，新版的chrome 由于安全的原因，已经禁止在非https的域进行摄像头调用。)
*  [阿里云演示地址](http://cjtblog.cn/IOTRest/rest/index.htm)
