#启动zookeeper
start  zkServer

#启动kafka，端口520
E:
cd \kafka\kafka_2.12-2.5.0
start .\bin\windows\kafka-server-start.bat .\config\server.properties

#启动kafka-manager管理工具，端口521
E:
cd \kafka\kafka-manager-1.3.3.17
del RUNNING_PID
start  .\bin\kafka-manager