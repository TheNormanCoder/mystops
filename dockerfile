FROM openjdk:11-jdk

WORKDIR /app

COPY target/atm-nearme-1.0-SNAPSHOT-full.jar /app/atm-nearme.jar

EXPOSE 4567

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/atm-nearme.jar"]
