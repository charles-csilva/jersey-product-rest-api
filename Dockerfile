FROM maven:3.6.3-openjdk-8-slim
ADD ./ /source
WORKDIR /source
ENTRYPOINT [ "mvn", "jetty:run-war" ]
EXPOSE 8080