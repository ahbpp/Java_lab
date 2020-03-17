FROM openjdk:9-jdk
# copy application WAR (with libraries inside)
COPY ./target /target
# specify default command
CMD ["java", "-jar", "target/gs-spring-boot-0.1.0.jar"]