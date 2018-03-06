FROM java:8

COPY spring-boot-server/target/ssistoza.studio-home-0.0.1-SNAPSHOT.jar home.jar

EXPOSE 80
CMD [ "java", "-jar", "home.jar" ]