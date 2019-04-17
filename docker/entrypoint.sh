#!/bin/bash
source /etc/profile

## JVM opt
if [ ! -n "$HEAP_DIRECT_RATE" ];then
    HEAP_DIRECT_RATE=0.7
fi

if [ ! -n "$HEAP_YOUNG_RATE" ];then
    HEAP_YOUNG_RATE=0.4
fi

if [ ! -n "$HEAP_SURVIVOR_RATIO" ]; then
    HEAP_SURVIVOR_RATIO=8
fi

MAXMEM=$(echo "$CONTAINER_LIMIT_MEMORY" | awk -v rate="${HEAP_DIRECT_RATE}" '{printf "%.0f", $0*rate}')
MINMEM=$(echo "$MAXMEM" | awk '{printf "%.0f", $0}')
NEWSIZE=$(echo "$MAXMEM" | awk -v youngRate="${HEAP_YOUNG_RATE}" '{printf "%.0f", $0*youngRate}')
CPU_CORE=$(echo "$CONTAINER_LIMIT_CPU" | awk '{printf "%.0f", $0}')

# 内存设置
export JAVA_OPTS="$JAVA_OPTS -Xms${MINMEM}m -Xmx${MAXMEM}m -Xmn${NEWSIZE}m -Xss256k -XX:MetaspaceSize=512m -XX:PretenureSizeThreshold=3145728";

# GC 配置
export JAVA_OPTS="$JAVA_OPTS -XX:MaxTenuringThreshold=4 -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=${HEAP_SURVIVOR_RATIO} -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSScavengeBeforeRemark -XX:+ParallelRefProcEnabled -XX:ParallelGCThreads=6 -XX:ConcGCThreads=2 -XX:-UseBiasedLocking";

# GC日志打印配置
export JAVA_OPTS="$JAVA_OPTS -verbose:gc -Xloggc:/www/gc.log.`date +%Y%m%d_%H%M%S` -XX:+PrintFlagsFinal -XX:+PrintCommandLineFlags -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=1 -XX:PrintSafepointStatisticsTimeout=500 -XX:+SafepointTimeout -XX:SafepointTimeoutDelay=500";

# 内存溢出时Dump Heap 设置heap-dump时内容输出文件 指定致命错误日志位置
export JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/www/java_pid1.hprof -XX:ErrorFile=/www/hs_err_pid1.log";

# tomcat 不忽略重复异常的栈，这是JDK的优化，大量重复的JDK异常不再打印其StackTrace
export JAVA_OPTS="$JAVA_OPTS -XX:-OmitStackTraceInFastThrow";

# 禁止JIT调用计数器衰减
export JAVA_OPTS="$JAVA_OPTS -XX:-UseCounterDecay";

# 加大Integer Cache
export JAVA_OPTS="$JAVA_OPTS -XX:AutoBoxCacheMax=20000";

# elastic search client 的 netty 配置
export JAVA_OPTS="$JAVA_OPTS -Des.set.netty.runtime.available.processors=false";

# tomcat log4j2 config
export JAVA_OPTS="$JAVA_OPTS -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager";

#encoding
export JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8";

# 额外的jvm参数配置
if [ -n "$DEFINE_JVM_OPTS" ];then
    export JAVA_OPTS="$JAVA_OPTS $DEFINE_JVM_OPTS";
fi

echo $JAVA_OPTS

exec java $JAVA_OPTS -jar /www/root-spring-boot.jar;