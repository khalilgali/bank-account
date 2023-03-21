FROM openjdk:8
EXPOSE 8080
ADD target/bank-account-kata.jar bank-account-kata.jar
ENTRYPOINT ["java","-jar","/bank-account-kata.jar"]