FROM eclipse-temurin:19-jre

RUN mkdir -p /opt/app
COPY build/libs/huts.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]
