#!/bin/bash

# 进入项目目录
cd /path/to/your/project

# 启动 Spring Boot 项目
nohup java -jar target/wx-dev-0.0.1-SNAPSHOT.jar > logs/application.log 2>&1 &

echo "Spring Boot application started. To view logs, use 'tail -f logs/application.log'"
