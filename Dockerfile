FROM openjdk:11
ADD /target/pizza-cooker.jar pizza-cooker.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar","pizza-cooker.jar"]