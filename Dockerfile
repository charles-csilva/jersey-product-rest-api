FROM maven:3.6.3-openjdk-11-slim AS build-step
ADD ./ /source
WORKDIR /source
RUN [ "mvn", "package" ]

FROM jetty:9.4.35-jre11
COPY --from=build-step /source/target/product-service-0.0.1-SNAPSHOT.war /var/lib/jetty/webapps/ROOT.war
EXPOSE 8080