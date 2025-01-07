# Spring Boot Book API

This project is a RESTful API for managing books, built with Spring Boot and Java 21.

The Book API provides a robust and scalable solution for book management operations.
It offers endpoints for creating, retrieving, and deleting book records, with data persistence handled through JPA.
The API is designed with best practices in mind, including global exception handling, request logging, and comprehensive unit testing.

## Repository Structure

```
.
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── io
    │   │       └── hexcore
    │   │           └── bookapi
    │   │               ├── BookapiApplication.java
    │   │               ├── controller
    │   │               │   └── BookController.java
    │   │               ├── exception
    │   │               │   └── GlobalExceptionHandler.java
    │   │               ├── model
    │   │               │   └── Book.java
    │   │               ├── repository
    │   │               │   └── BookRepository.java
    │   │               ├── service
    │   │               │   └── BookService.java
    │   │               └── util
    │   │                   └── RequestLogger.java
    │   └── resources
    │       └── log4j2.xml
    └── test
        └── java
            └── io
                └── hexcore
                    └── bookapi
                        ├── BookapiApplicationTests.java
                        └── BookApiTest.java
```

Key Files:
- `pom.xml`: Maven project configuration file
- `src/main/java/io/hexcore/bookapi/BookapiApplication.java`: Main application entry point
- `src/main/java/io/hexcore/bookapi/controller/BookController.java`: REST controller for book operations
- `src/main/java/io/hexcore/bookapi/model/Book.java`: Book entity definition
- `src/main/java/io/hexcore/bookapi/util/RequestLogger.java`: Aspect for logging API requests
- `src/test/java/io/hexcore/bookapi/BookApiTest.java`: Unit tests for the Book API

## Usage Instructions

### Installation

Prerequisites:
- Java Development Kit (JDK) 21
- Maven 3.6+

To install and run the application:

1. Clone the repository:
   ```
   git clone <repository-url>
   cd bookapi
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start and be available at `http://localhost:8080`.

### API Endpoints

1. Get all books:
   ```
   GET /api/v1/books
   ```

2. Get a single book by ID:
   ```
   GET /api/v1/books/{id}
   ```

3. Create a new book:
   ```
   POST /api/v1/books
   Content-Type: application/json

   {
     "title": "Sample Book",
     "author": "John Doe",
     "isbn": "1234567890"
   }
   ```

4. Delete a book:
   ```
   DELETE /api/v1/books/{id}
   ```

### Configuration

The application uses an H2 in-memory database by default. No additional configuration is required for local development.

### Testing

To run the tests:

```
mvn test
```

### Troubleshooting

1. Application fails to start
   - Ensure Java 21 is installed and set as the active JDK
   - Verify that port 8080 is not in use by another application

2. Database connection issues
   - Check that the H2 database dependency is correctly specified in the `pom.xml`
   - Verify that no conflicting database configurations are present in `application.properties`

3. Logging issues
   - Ensure that the `log4j2.xml` file is present in the `src/main/resources` directory
   - Verify that the Log4j2 dependency is correctly specified in the `pom.xml`

To enable debug logging, add the following line to `src/main/resources/application.properties`:

```
logging.level.io.hexcore.bookapi=DEBUG
```

### Performance Optimization

- Monitor API response times using tools like Apache JMeter or Postman
- Use Spring Boot Actuator for application metrics and health checks
- Consider implementing caching for frequently accessed data
- Optimize database queries and indexes for large datasets

## Data Flow

The Book API follows a typical Spring Boot MVC architecture for handling requests:

1. Client sends an HTTP request to the API endpoint
2. The `BookController` receives the request
3. The controller delegates business logic to the `BookService`
4. The `BookService` interacts with the `BookRepository` for data persistence
5. The repository performs CRUD operations on the H2 database
6. Results are passed back through the service to the controller
7. The controller returns an HTTP response to the client

```
Client <-> BookController <-> BookService <-> BookRepository <-> H2 Database
```

The `RequestLogger` aspect intercepts all controller method calls to log request details and execution times.