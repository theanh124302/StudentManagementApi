FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target StudentManagementApi
EXPOSE 8080
CMD ["java", "-jar", "StudentManagementApi/StudentManagementApi-0.0.1-SNAPSHOT.jar"]