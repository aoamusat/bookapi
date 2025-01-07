# Book API

This is a Spring Boot application that provides a RESTful API for managing books.

## Technologies Used

- Java 21
- Spring Boot 3.4.1
- Spring Data JPA
- H2 Database
- Maven
- Lombok

## Project Structure

```
.
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── io
    │           └── hexcore
    │               └── bookapi
    │                   ├── BookapiApplication.java
    │                   ├── model
    │                   │   └── Book.java
    │                   ├── repository
    │                   │   └── BookRepository.java
    │                   └── service
    │                       └── BookService.java
    └── test
        └── java
            └── io
                └── hexcore
                    └── bookapi
                        ├── BookapiApplicationTests.java
                        └── BookApiTest.java
```

## Getting Started

To run this application locally, follow these steps:

1. Ensure you have Java 21 and Maven installed on your system.
2. Clone this repository.
3. Navigate to the project directory.
4. Run the following command to build and start the application:

   ```
   mvn spring-boot:run
   ```

The application will start, and you can access the API at `http://localhost:8080`.

## API Endpoints

The following API endpoints are available:

- `GET /books`: Retrieve all books
- `GET /books/{id}`: Retrieve a book by ID
- `POST /books`: Create a new book
- `DELETE /books/{id}`: Delete a book by ID

## Testing

The project includes unit tests for the `BookService` class. To run the tests, use the following command:

```
mvn test
```

## Dependencies

The main dependencies for this project are:

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- H2 Database (runtime scope)
- Spring Boot Starter Test (test scope)
- Lombok (annotation processor scope)

For the complete list of dependencies and their versions, please refer to the `pom.xml` file.