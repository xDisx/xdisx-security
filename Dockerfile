FROM amazoncorretto:17-alpine-jdk
COPY /build/libs/xdisx-security-0.1.0.jar xdisx-security-0.1.0.jar
ENTRYPOINT ["java", "-jar", "/xdisx-security-0.1.0.jar"]
