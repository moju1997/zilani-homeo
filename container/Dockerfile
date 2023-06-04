FROM openjdk:16

### installing PostgreSQL Client ###
#RUN yum -y install https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm && yum -y install postgresql12
RUN microdnf install postgresql

ENV CFEA_HOME /cfea

COPY . /cfea

### Execute Permission to shell scripts ###

RUN ["/bin/bash", "-c", "chmod 755 /cfea/bin/*"]
RUN ["/bin/bash", "-c", "chmod -R 755 /cfea/conf/"]

EXPOSE 8016

ENTRYPOINT ["/cfea/bin/run.sh"]