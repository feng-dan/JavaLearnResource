```java
java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.demo.log  GCLogAnalysis

-Xmx1g  最大堆内存：1g

-Xms1g 初始堆内存：1g

-XX:+PrintGCDetails:控制台输出详细GC日志信息

-XX:+PrintGCDateStamps：输出每次GC时间

-Xloggc:gc.demo.log：GC日志输出到 gc.demo.log 文件中

java8 jvm 自适应参数默认是启用，jvm 算法去动态根据需要调整各种容量大小以及调整一些其他相关参数，例如：晋升到老年代阀值默认是15，经过15次Young GC 之后，如果对象还未被回收掉，进入老年代



日志示例：

```
2021-05-16T17:49:35.003+0800: 0.194: [GC (Allocation Failure) [PSYoungGen: 262144K->43516K(305664K)] 262144K->80401K(1005056K), 0.0113328 secs] [Times: user=0.02 sys=0.05, real=0.01 secs] 
```

2021-05-16T17:49:35.003+0800：时间戳

[GC (Allocation Failure) [PSYoungGen: 262144K->43516K(305664K)] 262144K->80401K(1005056K), 0.0113328 secs]：GC堆内存变化情况

GC (Allocation Failure)：这次内存分配失败导致GC

0.0113328 secs：这次GC 并行执行使用的时间、GC暂停时间，秒（11.3328毫秒），STW（stop-the-world）暂停业务线程，GC线程执行(GC暂停时间)

[PSYoungGen: 262144K->43516K(305664K)]：PSYoungGen 表示 Young 区, 262144K->43516K：从大概262M压缩到43M，262144K减掉43516K等于218632K(219M)，(305664K)：Young区最大容量为 305M。

> [PSYoungGen: 262144K->43516K(305664K)] ，当前Young区使用了262144K(262M)，被压到剩余 43516K（43M), 总大小305664K(305M),压缩之后，当前使用了43M，压缩掉219M，当前可使用219M
>
> Young区：262144K->43516K(305664K)  GC之前Young区使用了 262144K，GC之后使用 43516K，Young区最大容量305664K
>
> jvm堆内存：262144K->80401K(1005056K)  GC之前堆内存使用了 262144K，GC之后使用 80401K，jvm堆内存最大容量1005056K



262144K->80401K(1005056K)：整个堆内存情况，262M左右压缩到79M左右

> 1005056K（1g）的堆内存使用量也是262144K（262M左右）

[Times: user=0.02 sys=0.05, real=0.01 secs]：CPU 使用情况



- 串行GC：

> java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps  -XX:+UseSerialGC  GCLogAnalysis

> 堆内存配置越大，GC 频率越小。堆内存配置越小，GC 频率越大, 堆内存配置越小容易造成内存溢出，Full频繁。
>
> 单线程处理GC（执行GC效率比较低），Minor GC(小GC)或者Young GC、DefNew 区，Tenured（老年代GC）



- 并行GC:  

> java  -Xmx1g  -Xms1g  -XX:+PrintGCDetails  -XX:+PrintGCDateStamps  -XX:+UseParallelGC  GCLogAnalysis

> 堆内存配置越大，GC 频率越小。堆内存配置越小，GC 频率越大, 堆内存配置越小容易造成内存溢出，Full频繁。堆内存中实际存放对象，回收不了的哪些对象，整个占用堆内存数大于启动配置堆内存造成内存溢出，产生 OutOfMemoryError 异常 ，Full GC 时，YoungGC区清空（PSYoungGen:），OldGenGC 区清空一半（ParOldGen），Metaspace不变（Metaspace）。
>
> 堆内存配置很大（4g），容易造成GC暂停时间很长(是堆内存1g 大概2-3倍)。
>
> java8 默认启用自适应参数 导致GC时间不可控，相关参数根据当前状况动态调整。



- CMSGC：

> java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseConcMarkSweepGC GCLogAnalysis

>堆内存配置越大，GC 频率越小。堆内存配置越小，GC 频率越大, 堆内存配置越小容易造成内存溢出，Full频繁。
>
>CMSGC针对Old区（老年代）
>
>1、Young GC: ParNew
>
>2、CMS GC：initial mark(初始化标记) STW，最开始只标记根对象和可达对象，包括跨区的直接引用对象（用时比较快）
>
>3、CMS GC：concurrent mark（并发标记）
>
>4、CMS GC：concurrent preclean（并发预清理）
>
>​	a、concurrent abortable preclean（子阶段可取消的并发预清理）
>
>5、Young GC: ParNew
>
>6、CMSGC：final remark(最终标记) STW
>
>7、CMSGC：concurrent-sweep(并发清理)
>
>8、CMSGC：concurrent-reset(并发重置)



- G1GC:

> 堆内存配置越大，GC 频率越小。堆内存配置越小，GC 频率越大, 堆内存配置越小容易造成内存溢出，Full频繁。G1GC整个步骤跟CMS比较类似
>
> G1GC: evacuation pause: young (纯年轻代模式转移暂停)
>
> G1GC: concurrent marking(并发标记)
>
> G1GC: initial mark（初始化标记大对象分配失败）
>
> G1GC: concurrent region scan (并发扫描region)
>
> G1GC: concurrent mark(并发标记)
>
> G1GC: remak (重新标记)
>
> G1GC: cleanup(清理)
>
> G1GC: concurrent cleanup (并行清理)
>
> G1GC: evacuation pause (mixed) (转移暂停：混合模式)
>
> G1GC: full GC

