FROM openjdk:11.0.2-jre-slim
RUN apt-get update
RUN apt-get install procps -y
COPY target/microservice-kafka-invoicing-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -XX:+PreserveFramePointer -XX:TieredStopAtLevel=1 -noverify -jar microservice-kafka-invoicing-0.0.1-SNAPSHOT.jar
EXPOSE 8080
