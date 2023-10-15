# 项目：wx-pusher

该项目基于 SpringBoot+Thymeleaf开发，可以在没有个人公众号或服务号的情况下向用户推送信息，该项目演示课表推送功能。

## 使用前准备

- 本项目需要一台云服务器

- 由于项目无需公众号，所以需要第三方平台协助完成。本项目使用WxPusher平台。

- 参考地址：https://wxpusher.zjiecode.com

- 注册器完毕后注意保存appToken。

## 项目结构

- 详见项目本体

## 使用的技术

- **Spring Boot**：用于构建基于 Java 的强大应用程序的框架。
- **Maven**：用于管理依赖关系和构建项目的构建自动化工具。
- **Thymeleaf**：现代服务器端 Java 模板引擎，适用于 Web 和独立环境。
- **MySQL**：开源关系数据库管理系统。
- **wxpusher-java-sdk**：用于与 wxpusher API 集成的 Java SDK。
- **HttpClient**：用于发出 HTTP 请求和接收 HTTP 响应的库。
- **Jackson**：用于 Java 的高性能 JSON 处理器。
- **Spring Boot Actuator**：为您的应用程序添加了生产就绪功能，帮助您监视和管理应用程序。

## 快速开始

### 先决条件

- Java 17
- Maven
- SpringBoot 2.7

## 配置

### 配置文件

该项目使用 Maven 管理依赖并构建应用程序。主要配置文件如下：

- `pom.xml`：Maven 项目配置。
- `application.properties`：通用应用程序属性。
- `application-dev.properties`：开发环境属性。
- `application-prod.properties`：生产环境属性。

### `application-prod.properties`

```properties
# 服务器需要安装mysql
spring.datasource.url=jdbc:mysql://localhost:3306/class_one
spring.datasource.username=root
spring.datasource.password=root

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# wxpusher的appToken
wxpusher.app-token=xxx
wxpusher.send-message-url=https://wxpusher.zjiecode.com/api/send/message
# server-url的结尾不要写"/"如 (错误：https://www.baidu.com/ 应为 https://www.baidu.com)
# 若没有域名，ip地址应为 (例)http://10.0.0.127
wxpusher.server-url=https://www.example.com

spring.thymeleaf.enabled=true

# 开启一次性任务执行开关（详情见com.mobanc.wxdev.wxpusher.task.MyOnceTask）
# 开启后executeTask()会在启动时执行且仅执行一次
my-once-task.enabled=false

# 必须为80端口
server.port=80
```

`class_one.sql`

建表后自行输入自己的所有课程的课表（所有的必修和选修）

`course_schedule`表：

示例：

```
1	1,3,5,12,	1	1-2	软件质量保证与测试	2A101	张三	
```



从左到右分别是id（主键）、第几周有该课程、第几节课、课程名、教室名、教师名、备注可不填。

`base_course`表(必修课)：

示例：

```
1   软件质量保证与测试,软件工程,Linux系统应用及服务器配置,
```

从左到右分别是cid（主键）、必修的课程（注意逗号结尾）

```sql
/*
 Source Server         : TC_mysql
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Schema         : class_one

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 15/10/2023 21:42:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_course
-- ----------------------------
DROP TABLE IF EXISTS `base_course`;
CREATE TABLE `base_course`  (
  `cid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `base` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for course_schedule
-- ----------------------------
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE `course_schedule`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `week_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `day_of_week` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `classroom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for course_time
-- ----------------------------
DROP TABLE IF EXISTS `course_time`;
CREATE TABLE `course_time`  (
  `class_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`class_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `uid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `selected_course` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

```

### WxPuSher后台设置

后台地址：https://wxpusher.zjiecode.com/admin/

配置地址：https://wxpusher.zjiecode.com/admin/main/app/appInfo

将事件回调地址和设置地址都设置为你的服务器地址或域名（一定要和`application-prod.properties`里`wxpusher.server-url`的值一样），后面的`/wechat`和`/main`照抄：

![example-one](https://github.com/Mobanc/Springboot2-wechat-pusher/blob/main/src/main/resources/prtsc/example-one.png)

### 构建和运行

1. 克隆该仓库：

   ```bash
   git clone https://github.com/Mobanc/Springboot2-wechat-pusher.git
   cd Springboot2-wechat-pusher
   ```

2. 自定义配置（项目文件位置）：

   - 自定义事件回调地址:

     `com.mobanc.wxdev.controller.RegisterController`

   - 自定义设置地址：

     `com.mobanc.wxdev.wxpusher.controller.SubController`

   - 自定义一次性发布任务：

     `com.mobanc.wxdev.wxpusher.task.MyOnceTask`

   - 自定义发布信息时间、内容模板：

     `com.mobanc.wxdev.wxpusher.task.Publish`

   - 自定义网页注册内容(选修课程，必须更改)：

     `resource/static/register.html`

3. 构建项目（idea -> maven -> Lifecycle -> clean+package）或：

   ```bash
   mvn clean package
   ```

4. 运行应用程序（无需求不用运行）：

   ```bash
   java -jar target/wx-dev-0.0.1-SNAPSHOT.jar
   ```

   应用程序必须在端口 80 上启动（可在 `application-prod.properties` 中配置）。

## 服务器部署

- 给予脚本权限

```bash
chmod +x start.sh stop.sh restart.sh view_logs.sh
```

### 启动脚本 `start.sh`

```bash
./start.sh
```

### 停止脚本 `stop.sh`

```bash
./stop.sh
```

### 重启脚本 `restart.sh`

```bash
./restart.sh
```

### 查看日志脚本 `view_logs.sh`

```bash
./view_logs.sh
```



## 推送效果

- 用户通过你的关注方式注册之后会在你指定的时间时被推送信息。

- 关注方式：https://wxpusher.zjiecode.com/admin/main/app/appFollow

- 效果演示：

  ![example-two](https://github.com/Mobanc/Springboot2-wechat-pusher/blob/main/src/main/resources/prtsc/example-two.png)

  ![example-three](https://github.com/Mobanc/Springboot2-wechat-pusher/blob/main/src/main/resources/prtsc/example-three.png)

## 联系方式

本项目仅仅为个人学习及测试使用，不可用于任何形式盈利，如有侵权，请联系作者删除。

如有任何疑问或问题，请随时联系项目贡献者：

- Mobanc：[ls6012@hotmail.com](mailto:ls6012@hotmial.com)

