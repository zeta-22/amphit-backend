FROM maven:3.8-openjdk-17

WORKDIR /app
COPY . .
RUN mvn package -DskipTests
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "target/*.jar"]
