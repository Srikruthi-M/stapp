# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build   
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:copy-dependencies
RUN mvn clean package -DskipTests   

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine   
WORKDIR /app

# Copy generated JAR from Stage 1
COPY --from=build /app/target/*.jar stapp.jar   
# change "sms.jar" if your JAR output needs a different name

EXPOSE 8100  
ENTRYPOINT ["java", "-jar", "stapp.jar"]   
#update only if JAR file name above changes
