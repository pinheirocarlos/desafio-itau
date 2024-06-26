FROM maven:3.8.6-amazoncorretto-17 AS build
WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn package

FROM amazoncorretto:17.0.5
WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
ENV APP_NAME api-transferencia
ENV SERVICE_NAMESPACE api-transferencia
ENV TZ="America/Fortaleza"
ENV JAVA_OPTS "$JAVA_OPTS \
    -XX:+UseParallelGC \
    -XX:ActiveProcessorCount=2 \
    -XX:MaxRAMPercentage=75 \
    --add-opens java.base/java.lang=ALL-UNNAMED \
    -Duser.timezone=America/Fortaleza"
EXPOSE 8080 9090
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

ENTRYPOINT ["java", "-jar", "app.jar"]