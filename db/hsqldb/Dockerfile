FROM java:8
MAINTAINER Kunal Patil <kunalpatil@yahoo.com>

WORKDIR /opt/hsqldb
ADD . /opt/hsqldb
RUN chmod +x /opt/hsqldb/*.sh && rm Dockerfile && rm testcasedb.hsql.*

VOLUME ["/data"]

EXPOSE 9001

CMD ["/opt/hsqldb/dbserver.sh", "/data"]

