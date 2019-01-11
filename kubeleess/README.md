kubeless是k8s对serverless的支持，FaaS啥的，轻量级函数服务<br/>
安装可以参考 https://kubeless.io/docs/quick-start/ <br/>
CLI在这里获得 https://github.com/kubeless/kubeless/releases <br/>


kubectl create ns kubeless <br/>
kubectl create -f https://github.com/kubeless/kubeless/releases/download/v1.0.0-alpha.8/kubeless-v1.0.0-alpha.8.yaml<br/>
kubectl get pods -n kubeless<br/>

以上是初始化kubeless<br/>

下面是函数部署<br/>

kubeless function deploy qrytype --runtime java1.8 --handler Query.getData --from-file src/main/java/io/kubeless/Query.java --dependencies pom4kubeless.xml<br/>

关于工程调试：pom.xml是供调试用的，调试的时候要注释掉 Query.java中的 <br/>

import io.kubeless.Event;<br/>
import io.kubeless.Context;<br/>

以及<br/>
public String getData(io.kubeless.Event event, io.kubeless.Context context) {<br/>
    System.out.println(event.Data);<br/>
    List result = new ArrayList();<br/>
    Query query = new Query();<br/>
    if("qryAllTypes".equals(event.Data)){<br/>
        result = query.qryAllTypes();<br/>
    }<br/>
    return JSONObject.toJSON(result).toString();<br/>
}<br/>

否则调试会报错你懂的。
部署之前把这些注释去掉，否则会报错

call 函数用以下命令 <br/>
kubeless function call qrytype --data 'qryAllTypes'<br/>

测了一下获取到数据没问题，但是返回数据有中文貌似会报错。。。java.io.IOException: too many bytes to write to stream<br/>

还可以暴露成service，具体看文档即可
