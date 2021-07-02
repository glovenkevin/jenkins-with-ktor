FROM gradle:6.7 as builder
COPY . .
RUN gradle clean installDist


FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /home/gradle/build/install/jenjen.ktor .
CMD ["./bin/jenjen.ktor"]