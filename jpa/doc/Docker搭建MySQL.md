#### 1. 拉取镜像

```
docker pull mysql:5.7
```

#### 2.启动容器

```
docker run -d --restart=always --name my-mysql -v E:\workspace\docker\mysql\data:/var/lib/mysql -v E:\workspace\docker\mysql\conf:/etc/mysql/conf -v E:\workspace\docker\mysql\log:/var/log/mysql -p 13306:3306 -e TZ=Asia/Shanghai -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci
```

