FROM gradle:6.7 as builder

COPY . .

RUN gradle clean build --no-daemon


FROM openjdk:8-jre-alpine

COPY --from=builder /home/gradle/build/libs/jenjen.ktor-1.0.0.jar /app.jar

CMD ["java", "-jar", "/app.jar"]