Redis-Cluster采用无中心结构，每个节点保存数据和整个集群状态，每个节点都和其他所有节点连接。
一组Redis Cluster是由多个Redis实例组成，官方推荐使用6实例，其中3个为主节点，3个为从节点。一旦有主节点发生故障的时候，Redis Cluster可以选举出对应的从节点成为新的主节点，继续对外服务，从而保证服务的高可用性。
<ul>
<li>1.当集群内一个Master以及其对应的Slave同时宕机，集群将无法提供服务。</li>
<li>2.当存活的主节点数小于总节点数的一半时，整个集群就无法提供服务了。</li>
</ul>

<img alt="" height="691" src="https://img-blog.csdnimg.cn/20210812160019222.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoaW1vbW8=,size_16,color_FFFFFF,t_70" width="1200">

### 创建create-redis.sh
```
HOST=$1
PORT=$2
VERSION=$3

mkdir -p /data/redis-cluster
cd /data/redis-cluster
mkdir data$PORT

eval "cat <<EOF
bind 0.0.0.0
daemonize no
logfile "/data/redis-server.log"
dir "/data"

port ${PORT}
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip ${HOST}
cluster-announce-port ${PORT}
cluster-announce-bus-port 1${PORT}
appendonly yes
EOF" > redis$PORT.conf

docker run -dit --name redis$PORT --restart=always -v $PWD/redis$PORT.conf:/etc/redis.conf -v $PWD/data$PORT:/data/ -p $PORT:$PORT -p 1$PORT:1$PORT redis:$VERSION redis-server /etc/redis.conf
```
### 创建redis-start.sh
```
./create-redis.sh 192.168.126.136 7001 7.0.4
./create-redis.sh 192.168.126.136 7002 7.0.4
./create-redis.sh 192.168.126.136 7003 7.0.4
./create-redis.sh 192.168.126.136 7004 7.0.4
./create-redis.sh 192.168.126.136 7005 7.0.4
./create-redis.sh 192.168.126.136 7006 7.0.4
```
### 创建redis-cluster-start.sh
```
docker exec -it redis7001 redis-cli --cluster create 192.168.126.136:7001 192.168.126.136:7002 192.168.126.136:7003 192.168.126.136:7004 192.168.126.136:7005 192.168.126.136:7006 --cluster-replicas 1
```

### 参考博客：
<ul>
  <li><a href="https://blog.csdn.net/qq_43430759/article/details/126352446">docker搭建redis三主三从集群</a></li>
  <li><a href="https://www.pudn.com/news/632629932aaf6043c988feca.html">docker容器化搭建 redis7.0.4 三主三从集群 [详细说明，步骤简洁]</a></li>
  <li><a href="https://mafgwo.cn/2021/02/07/2018_Redis%E4%B8%89%E4%B8%BB%E4%B8%89%E4%BB%8E%E9%9B%86%E7%BE%A4Docker%E6%96%B9%E5%BC%8F%E6%90%AD%E5%BB%BA/">Redis三主三从集群Docker方式搭建</a></li>
</ul>

