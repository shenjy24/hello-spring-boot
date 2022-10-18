### 1. 拉取镜像

```
docker pull mongo
```

### 2.启动容器

```
docker run -d --name mongodb -p 27017:27017 -v E:\workspace\docker\mongo\data:/data/db -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=123456 --privileged=true --restart always mongo
```

