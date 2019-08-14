#!/bin/sh

cd /root/workspace/target/
cp /root/workspace/dockerfile .
pwd && ls
docker login --username=为之科技研发 -p wizhidev2019 registry.cn-beijing.aliyuncs.com
docker build -t registry.cn-beijing.aliyuncs.com/wizhi/quartz:1.0 .
docker push registry.cn-beijing.aliyuncs.com/wizhi/quartz:1.0