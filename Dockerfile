FROM openjdk:17-jdk

WORKDIR /gen

COPY build/libs/gen-0.0.1-SNAPSHOT.jar gen-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "gen-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=dev"]