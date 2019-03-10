FROM java:8
MAINTAINER Kunal Patil <kunalpatil@yahoo.com>

ADD . /opt/hsqldb
RUN chmod +x /opt/hsqldb/*.sh

VOLUME ["/data"]

EXPOSE 9001

WORKDIR /opt/hsqldb
CMD ./dbserver.sh /data
