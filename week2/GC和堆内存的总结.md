**4.（必做）**根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

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

