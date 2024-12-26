@import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator
@import io.github.dumijdev.restdb4j.server.application.core.domain.select.JoinType
@import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator.Database


@param Operator[] operators
@param JoinType[] joinTypes
@param Database[] databases
# RestDB4J

RestDB4J is a lightweight and scalable solution for interacting with relational databases using a RESTful API. It abstracts the complexities of direct database connections, allowing developers to perform operations like querying, inserting, updating, and deleting through HTTP/REST calls.

## Table of Contents
- [Introduction](#introduction)
- [Motivation](#motivation)
- [Installation](#installation)
  - [On JVM](#on-jvm)
  - [On Docker](#on-docker)
- [Operations](#operations)
- [Constants](#constants)
- [Configuration](#configuration)
- [Contribute](#contribute)

## Introduction
In the modern world, integration between applications is a fundamental aspect of creating agile and efficient solutions. Relational databases play a critical role as repositories of structured information. However, direct access can be challenging, especially in scenarios requiring decentralized and secure communication.

RestDB4J simplifies interactions with relational databases by providing a RESTful interface. It supports common database operations like querying, inserting, updating, and deleting. The solution is ideal for teams seeking to access or manipulate data without managing drivers, SQL languages, or complex configurations.

### Key Benefits
- **Interoperability**: Support for various relational databases (MySQL, PostgreSQL, SQL Server, etc.).
- **Ease of Use**: Perform database operations through REST endpoints.
- **Standardization**: Unified interface for consistent communication.
- **Security**: Configurable access control to protect data.
- **Scalability**: Perfect for distributed applications and microservices.

## Motivation
Modern applications face challenges such as:
- Diverse drivers and configurations for different databases.
- The need to learn SQL for direct queries.
- Security risks when exposing direct database connections.
- Integration complexity in microservice architectures.

RestDB4J addresses these issues by offering:
- A RESTful API for decentralized and flexible database access.
- A simplified HTTP interface eliminating the need for SQL.
- Process standardization through REST conventions.
- Improved security via controlled endpoints.
- Interoperability with any HTTP-compatible language or tool.

## Installation

### Requirements
- **Java**: 21+
- **Maven**: 3.9.9+
- **Docker**: Required for running in a container.

### On JVM
1. Clone the repository:
   ```bash
   git clone https://github.com/DumiJDev/restdb4j.git
   ```
2. Navigate to the project directory:
   ```bash
   cd restdb4j
   ```
3. Build the project:
   ```bash
   mvn clean install package
   ```
4. Navigate to the target directory:
   ```bash
   cd target
   ```
5. Run the application:
   ```bash
   java -jar restdb4j.jar
   ```

### On Docker
Run the following command:
```bash
docker run --name restdb4j --rm -p 8082:8080 dumijdev/restdb4j:latest
```

## Operations
RestDB4J currently supports:
- **Select**: ALIAS fields and table, WHERE, ORDER BY, JOIN, PAGINATION.
- **Insert**.
- **Update**.
- **Delete**.

## Constants
### Operators
@for(var operator : operators)@if(!operator.value().isEmpty())- SQL: `${operator.value()}`, API: `${operator.symbol()}`
@endif@endfor

### JoinType
@for(var joinType : joinTypes)- SQL: ${joinType.name()}, API: `${joinType.value()}`
@endfor

### Supported Databases
@for(var database : databases)- ${database.name()}, API: `${database.value()}`
@endfor

## Configuration
Configuration can be done using:
- **application.yml**: For local setup.
- **Environment variables**: Works seamlessly with Docker.

### Docker Environment

| Name               | Default     | Description                                                |
|--------------------|-------------|------------------------------------------------------------|
| RESTDB_NAME        | RestDB4J    | Name for application instance                              |
| RESTDB_DB_PLATFORM | h2          | Database platform (API value, see [Constants](#constants)) |
| RESTDB_DB_PORT     |             | Database port                                              |
| RESTDB_DB_USER     | sa          | Database user                                              |
| RESTDB_DB_PWD      | password    | Database password                                          |
| RESTDB_DB_NAME     | restdb.db   | Database name                                              |
| RESTDB_DRIVER_CLASS|             | Java Driver Class for Database                             |
| RESTDB_DB_URL      |             | Database custom URL string connection                     |
| RESTDB_SERVER_NAME | restdb      | Name for instance in URL                                   |
| RESTDB_DB_HOST     | localhost   | Database host                                              |

## Contribute
Your contributions are vital! Follow the steps below to get started:

1. **Fork the repository**: Create your own copy to work on.
2. **Create a branch**: Name your branch descriptively, e.g., `feature/new-feature` or `fix/bug-fix`.
3. **Open an issue**: Describe your idea or the bug you plan to address.
4. **Implement your changes**: Follow coding standards and include tests.
5. **Submit a Pull Request**: Target the `staging` branch with a detailed description of your changes.

### Contribution Guidelines
- Ensure code quality and proper documentation.
- Add tests for any new features or bug fixes.
- Follow the repository's coding standards.

## License
This project is licensed under the MIT License.

---

&copy; 2024 RestDB4J - Powered by [DumiJDev](https://github.com/DumiJDev)