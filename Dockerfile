FROM tomcat:11.0-jre17-temurin

COPY build/libs/equipment-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/equipment.war

EXPOSE 8080

CMD ["catalina.sh", "run"]