#!/bin/sh

APP=$(cd $(dirname $0); pwd | awk -F '/' '{print $(NF)}')

echo "开始打包 $APP"

mvn -U clean package -Dmaven.test.skip=true


echo "package $APP.zip success !"
