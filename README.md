学习k8s的时候自己写的一些例子,均是在谷歌的GCE环境中运行，只要支持LoadBalancer类型Service的都可以

secret:
从文件生成password的secret对象，在container中引用该对象并放入容器环境变量，通过在initContainer中读取环境变量并将其写入nginx的index页面中。
pod启动后访问nginx主页就会打印出password的值。