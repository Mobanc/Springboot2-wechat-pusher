#!/bin/bash

# 查找并杀死 Spring Boot 进程
PID=$(ps -ef | grep "wx-dev-0.0.1-SNAPSHOT.jar" | grep -v grep | awk '{print $2}')
if [ -z "$PID" ]
then
  echo "No running Spring Boot application found."
else
  echo "Stopping Spring Boot application..."
  kill -9 $PID
  echo "Spring Boot application stopped."
fi
