关于PersistentVolume以及PersistentVolumeClaims的例子（使用nfs网络存储方案）<br/>
首先在一台linux服务器上搭建nfs服务，具体安装方法可以网上搜索。<br/>
注意，nfs的目录要注意给读写权限，另外要记得开放必要的端口。安装完毕后可以在本机进行mount测试。<br/>

k8s对象启动顺序<br/>
kubectl apply -f pv.yml<br/>
kubectl apply -f pvc.yml<br/>
kubectl apply -f deploy.yml<br/>
kubectl apply -f service.yml<br/>
