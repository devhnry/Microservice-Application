# Banking System Microservices

## Overview

This project demonstrates how to separate a banking system into three distinct microservices: Loan, Account, and Card. The primary focus is on dockerizing the Account service for learning and understanding property files in a microservices architecture. Followed by a Course I was taking.

## Microservices

1. **Loan Service**
2. **Account Service** (Dockerized)
3. **Card Service**

## Prerequisites

- Java Development Kit (JDK) 21.
- Docker
- Maven or Gradle (for building the project) **Maven was used.**

## Project Structure Summary

```
banking-system/
├── loans/
│   └── src/
├── accounts/
│   ├── src/
│   └── Dockerfile
│   └── application.properties
├── cards/
│   └── src/
├── README.md
```

## Account Service: Dockerization

### Dockerfile

The Dockerfile for the Account service is located in the `accounts` directory.

```dockerfile
# Starting with a base image containing java runtime
FROM openjdk:21-jdk-slim

#The person maintaing the dockker file
MAINTAINER devhnry.com

#Add the appication's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
```

### Building and Running the Docker Container

1. **Build the project**:

    ```bash
    cd accounts
    mvn clean install
    ```

2. **Build the Docker image**:

    ```bash
    docker build -t accounts:latest .
    ```

3. **Run the Docker container**:

    ```bash
    docker run -p 8080:8080 accounts:latest
    ```

## Property Files

The `application.yml` files for the Account service is located in the `accounts` directory. It contains configuration settings for the service.


## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any changes.

---
