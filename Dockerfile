FROM java:8
VOLUME /www

COPY marvel-web/target/root-spring-boot.jar /www/
COPY docker/entrypoint.sh /www/

#分配容器最大内存（MB）以及 CPU最大核数
ENV CONTAINER_LIMIT_MEMORY = 2048
ENV CONTAINER_LIMIT_CPU = 1

RUN  mkdir -p /www/logs \
     && mkdir -p /www/logs/backups \
     && chmod 777  /www/logs

ENTRYPOINT ["/bin/bash","/www/entrypoint.sh"]

EXPOSE 80