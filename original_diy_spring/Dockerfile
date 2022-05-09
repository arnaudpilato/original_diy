FROM maven:3.8.2-jdk-11
WORKDIR /original_diy_spring
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run