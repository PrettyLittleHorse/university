FROM openjdk:11
ADD build/libs/university-0.0.1-SNAPSHOT.jar university-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/university-0.0.1-SNAPSHOT.jar"]