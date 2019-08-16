#!/bin/sh
#远程执行部署命令脚本
docker login --username=为之科技研发 -p wizhidev2019 registry.cn-beijing.aliyuncs.com
docker pull registry.cn-beijing.aliyuncs.com/wizhi/quartz:1.0
docker stop quartz
docker rm quartz
docker run -d -e TZ="Asia/Shanghai" -p 8081:8081 --name quartz  registry.cn-beijing.aliyuncs.com/wizhi/quartz:1.0

echo "服务器 docker部署成功"
