FROM alpine:3.11.3
RUN apk add --update --no-cache openjdk11-jre=11.0.5_p10-r0
COPY target/insecureapp*.war /insecureapp.war
ENTRYPOINT ["java", "-jar", "/insecureapp.war"]
