关于PersistentVolume以及PersistentVolumeClaims的例子（使用nfs网络存储方案）<br/>
首先在一台linux服务器上搭建nfs服务:<br/>
yum -y install nfs-utils rpcbind<br/>
mkdir -p /data/nfs<br/>
nfs的目录要注意给读写权限: <br/>
chmod 666 /data/nfs<br/>
vim /etc/exports <br/>
/data/nfs *(rw,no_root_squash,no_all_squash,sync)<br/>
*表示任何主机可以访问，也可以设置网段如192.168.2.0/24<br/>
exportfs -r<br/>
systemctl start rpcbind<br/>
systemctl start nfs<br/>
查看rpc服务注册情况 rpcinfo -p localhost<br/>
server 端先自我测试一下是否可以联机 showmount -e localhost<br/>
Export list for localhost:<br/>
/data/nfs *<br/>

客户端:<br/>
yum -y install nfs-utils<br/>
客户端测试一下是否可以联机 showmount -e 服务端ip<br/>
挂载 mount -t nfs 服务端ip:/data/nfs /mnt -o proto=tcp -o nolock<br/>
查看硬盘 df -h<br/>
卸载 umount /lys/<br/>

注意，另外要记得开放必要的端口：nfsd 端口 2049 udp/tcp<br/>

*************************************<br/>

k8s对象启动顺序<br/>
kubectl apply -f pv.yml<br/>
kubectl apply -f pvc.yml<br/>
kubectl apply -f deploy.yml<br/>
kubectl apply -f service.yml<br/>
