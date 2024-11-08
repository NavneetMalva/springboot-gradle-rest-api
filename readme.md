# springboot-gradle-rest-api
### Spring Boot Gradle REST API for Employee Management

This project is a Spring Boot REST API for managing employees, built with Gradle. It includes CRUD operations such as creating, reading, updating, and deleting employees, as well as exception handling, validation, and integration with a MySQL database.

## Features

- **CRUD Operations**: Create, read, update, and delete employee data.
- **Exception Handling**: Custom exception handling for employee-related errors (e.g., employee not found).
- **Validation**: Input validation using annotations like `@NotBlank` to ensure required fields are provided.
- **Gradle Build**: The project uses Gradle for dependency management and building the application.
- **MySQL Integration**: The application is connected to a MySQL database for data persistence.


## Prerequisites

- **Java 17 or later**: This project uses Spring Boot 3+, which requires Java 17 or higher.
- **Gradle**: Gradle should be installed, or you can use the included Gradle wrapper.
- **MySQL**: You need a MySQL instance running, and the application will connect to the database using the configuration provided in `application.properties`.

## Installation

### 1. Clone the repository

Clone the repository to your local machine:

```bash
git clone https://github.com/NavneetMalva/springboot-gradle-rest-api.git
```

### 2. Set up MySQL Database
Make sure MySQL is installed and running. You need to create a database named employee_db or change the database configuration in `src/main/resources/application.properties`.

Example of `application.properties` configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gradle_api
spring.datasource.username=root
spring.datasource.password=Mysql@123

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto = update
```

### 3.Build the application
To build the project, use the following Gradle command:

```bash
./gradlew build
```
If you're on Windows, use `gradlew.bat`:
```bash
gradlew.bat build
```
### 4.Run the application
After building the project, run it with the following command:

```bash
./gradlew bootRun
```
Alternatively, you can run the `SpringbootGradleRestApiApplication` class directly from your IDE.


### Create Employee

POST /api/v1
```
{
"name": "John Doe",
"department": "Engineering"
}
```

### Example Error Response
```
{
    "fieldErrorsMap": {
        "name": "employee name can not be empty.",
        "department": "employee department can not be empty."
    },
    "error": "Validation Failed",
    "status": 400
}
```

### Update Employee

- It will only update the details provided for the employee based on the id.

```declarative
{
  "id": 10,
  "name": "Jeremy"
}
```
### Updated Employee Response
```declarative
{
"id": 10,
"name": "Jeremy",
"department": "Computer-Science"
}
```
