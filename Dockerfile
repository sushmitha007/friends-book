FROM openjdk:10-jdk
ADD /target/friends-book-0.0.1-SNAPSHOT.jar /dockerfs/app/friends-book-0.0.1-SNAPSHOT.jar
WORKDIR /dockerfs/app
ENTRYPOINT ["java","-jar","friends-book-0.0.1-SNAPSHOT.jar"]