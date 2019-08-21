FROM java:8
WORKDIR usr/src
ENV_MYSQL_DATABASE=noteBD
ENV_MYSQL_USER=produser2018
ENV_MYSQL_PASSWORD=produser2018

ADD ./target/ReminderService-0.0.1-SNAPSHOT.jar /usr/src/ReminderService-0.0.1-SNAPSHOT.jar

EXPOSE 8089

ENTRYPOINT ["java","-jar","/src/ReminderService-0.0.1-SNAPSHOT.jar"]