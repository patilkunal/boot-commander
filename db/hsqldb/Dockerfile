FROM java:8
MAINTAINER Kunal Patil <kunalpatil@yahoo.com>

RUN "mkdir /opt/hsqldb"
WORKDIR /opt/hsqldb
ADD . /opt/hsqldb
RUN "chmod +x /opt/hsqldb/*.sh"

EXPOSE 9001:9001

CMD ["./dbserver.sh"]
