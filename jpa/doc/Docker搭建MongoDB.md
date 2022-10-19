### 1. 拉取镜像

```
docker pull mongo
```

### 2.启动容器

```
docker run -d --name mongodb -p 37017:27017 -v E:\workspace\docker\mongo\data:/data/db -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=123456 --privileged=true --restart always mongo
```
- 注意需要使用`-u mongodb`参数指定运行用户为`mongodb`，如果不加则使用`root`用户，会导致一直重启的问题。
- 宿主机使用27017端口时，`MongoDB Compass`一直连不上，暂不知原因。

### 3.创建账号

原因：`MongoDB`中每个数据库之间是相互独立的，都有独立的权限，正确的做法是使用`root`账号在【将要操作的数据库】中创建一个【子账号】，在用这个子账号连接。

具体步骤如下：

1. 进入`admin`数据库并授权

   ```
   > use admin
   > db.auth("root","123456")
   ```

2. 创建数据库

   ```
   > use mongo
   ```

   注：不存在就会创建。

3. 创建子账户

   ```
   > db.createUser({user:"jia",pwd:"123456",roles:[{role:"dbOwner",db:"mongo"}]})
   ```

   