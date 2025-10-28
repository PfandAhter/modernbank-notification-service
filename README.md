# ModernBank Notification Service

ModernBank Notification Service is a Spring Boot microservice that delivers real-time notifications for ModernBank channels. It exposes REST APIs for managing user notifications, integrates with Kafka for asynchronous delivery, persists notifications in MySQL, and pushes updates over WebSocket so web and mobile clients can react immediately.

## Features

- **Notification lifecycle management** – create, list, mark as read, and soft-delete notifications via REST endpoints backed by JPA repositories.
- **Chat message alerts** – dedicated API and Kafka producer/consumer pair for streaming chat notifications to subscribed users.
- **WebSocket delivery** – STOMP endpoints broadcast notifications and chat events to `/user/...` destinations with SockJS fallbacks.
- **Kafka integration** – configurable producer/consumer factories with retry handling for notification and chat topics.
- **Error-code cache** – scheduled cache warms error codes from the database for consistent API responses.
- **Header propagation for Feign clients** – outgoing calls reuse authentication and user headers from the inbound request.

## Architecture

```
Client -> REST API (Spring MVC) -> Service Layer -> Repository (Spring Data JPA / MySQL)
                                   |-> Kafka Producer (notification-send, chat-notification-send)
Kafka Consumer -> SimpMessagingTemplate -> WebSocket /user/{userId}/notifications
```

Key components:

- **Controllers** expose REST endpoints (`/api/v1/notification` and `/api/v1/notification/chat`).
- **Services** encapsulate business logic and mapping via ModelMapper.
- **Repositories** use Spring Data JPA to persist `Notification` and `ErrorCodes` entities.
- **Kafka infrastructure** is defined in `KafkaConfiguration` with separate factories and templates per topic.
- **WebSocket configuration** registers STOMP endpoints at `/notification-websocket` and `/chat-websocket` with SockJS support.

## Technology Stack

- Java 17
- Spring Boot 3.2 (Web, WebSocket, Data JPA)
- Spring Kafka
- Spring Cloud OpenFeign
- MySQL (runtime)
- Apache Kafka
- ModelMapper & Lombok

See [`pom.xml`](pom.xml) for the complete dependency list.

## Prerequisites

- JDK 17+
- Maven 3.9+ (or use the bundled `mvnw` wrapper)
- Running MySQL instance with a database named `modern_bank_notification_service`
- Running Kafka broker on `localhost:9092`

Update [`src/main/resources/application.yml`](src/main/resources/application.yml) if your database or Kafka connection details differ.

## Getting Started

1. **Clone and install dependencies**
   ```bash
   ./mvnw clean install
   ```

2. **Configure the database**
   - Create the `modern_bank_notification_service` schema in MySQL.
   - Update `spring.datasource.*` credentials in `application.yml` if required.

3. **Run Kafka locally**
   - Start Zookeeper and Kafka.
   - Create topics `notification-send` and `chat-notification-send` (Kafka auto-creation can also be enabled).

4. **Start the service**
   ```bash
   ./mvnw spring-boot:run
   ```
   The application listens on `http://localhost:8082` by default.

5. **Access WebSocket endpoints**
   - STOMP endpoint: `ws://localhost:8082/notification-websocket` for general notifications.
   - STOMP endpoint: `ws://localhost:8082/chat-websocket` for chat updates.
   - Subscribe clients to `/user/{userId}/notifications` or `/user/chat/model/{userId}/notifications`.

## API Overview

| Endpoint | Method | Description |
| --- | --- | --- |
| `/api/v1/notification/send` | POST | Publishes a `NotificationMessage` to Kafka for asynchronous delivery. |
| `/api/v1/notification/get` | POST | Retrieves unread, non-deleted notifications for the given `userId`. |
| `/api/v1/notification/read` | POST | Marks a notification as read. |
| `/api/v1/notification/delete` | POST | Soft-deletes a notification. |
| `/api/v1/notification/chat/send` | POST | Sends a chat notification through Kafka and WebSocket. |

All responses extend a common `BaseResponse` structure containing status, process code, and message fields.

## Database Schema

Spring Data JPA auto-creates the `notifications` table with the following columns:

| Column | Type | Notes |
| --- | --- | --- |
| `id` | BIGINT (auto-generated) | Primary key. |
| `user_id` | VARCHAR | Recipient identifier. |
| `title` | VARCHAR | Notification title. |
| `message` | VARCHAR | Notification body. |
| `type` | VARCHAR | Free-form notification type (e.g., `TRANSACTION`). |
| `timestamp` | DATETIME | Creation timestamp. |
| `is_read` | BOOLEAN | Read flag. |
| `is_deleted` | BOOLEAN | Soft-delete flag. |

Entity definition: [`Notification.java`](src/main/java/com/modernbank/notification_service/entity/Notification.java).

## Testing

Run the unit test suite:

```bash
./mvnw test
```

## Project Structure

```
modernbank-notification-service/
├── pom.xml
├── src
│   ├── main
│   │   ├── java/com/modernbank/notification_service
│   │   │   ├── api               # REST contract interfaces & DTOs
│   │   │   ├── aspect            # Cross-cutting concerns
│   │   │   ├── configuration     # Kafka, WebSocket, Feign, ModelMapper configs
│   │   │   ├── controller        # REST entry points
│   │   │   ├── entity            # JPA entities
│   │   │   ├── exceptions        # Custom exception types
│   │   │   ├── repository        # Spring Data repositories
│   │   │   ├── rest/service      # Service interfaces and implementations
│   │   │   └── websocket         # STOMP message controllers
│   │   └── resources
│   │       └── application.yml   # Service configuration
└── src/test/java                 # Test sources
```
