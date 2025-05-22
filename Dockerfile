FROM maven:3.8-openjdk-17

WORKDIR /app
COPY . .
RUN mvn package -DskipTests
# Find the actual JAR name and use it explicitly
RUN find target -name "*.jar" -not -name "*sources*" -not -name "*javadoc*" -exec cp {} app.jar \;
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
