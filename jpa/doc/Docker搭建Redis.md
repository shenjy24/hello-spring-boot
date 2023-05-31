#### 1. 拉取镜像
```
docker pull redis
```

#### 启动容器
```
docker run --restart=always --log-opt max-size=100m --log-opt max-file=2 -p 16379:6379 --name my-redis 
-v /home/redis/redis.config:/etc/redis/redis.conf -v /home/redis/data:/data -d redis redis-server 
/etc/redis/redis.conf --appendonly yes --requirepass 123456
```