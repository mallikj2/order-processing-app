# Order Processing Application

This is a Spring Boot application designed to manage orders, exposing both RESTful and GraphQL APIs. It uses an in-memory H2 database for simplicity.

## Technologies Used

*   **Spring Boot:** Framework for building stand-alone, production-grade Spring applications.
*   **Spring Web:** For building RESTful APIs.
*   **Spring for GraphQL:** For implementing GraphQL APIs.
*   **Spring Data JPA:** For data persistence with Hibernate.
*   **H2 Database:** An in-memory relational database for development and testing.
*   **Lombok:** To reduce boilerplate code (e.g., getters, setters).
*   **Maven:** Dependency management and build automation tool.
*   **Java 17:** The programming language version used.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 17 or higher
*   Maven (already set up with `mvnw` wrapper in the project)

### Building the Application

Navigate to the `order-processing-app` directory in your terminal and run the following command:

```bash
./mvnw clean install
```

On Windows, you might need to use `mvnw.cmd`:

```bash
./mvnw.cmd clean install
```

### Running the Application

After building, you can run the application from the `order-processing-app` directory:

```bash
java -jar target/order-processing-app-0.0.1-SNAPSHOT.jar
```

The application will start on port `8080` by default.

## API Documentation

The application exposes both REST and GraphQL APIs for managing orders.

### REST API

**Base URL:** `http://localhost:8080/orders`

#### 1. Create a New Order

*   **Endpoint:** `POST /orders`
*   **Description:** Creates a new order in the system.
*   **Request Body (JSON):**
    ```json
    {
        "customerName": "John Doe",
        "product": "Laptop",
        "quantity": 1
    }
    ```
*   **Sample `curl` Command:**
    ```bash
    curl -X POST http://localhost:8080/orders \
    -H "Content-Type: application/json" \
    -d "{ \"customerName\": \"John Doe\", \"product\": \"Laptop\", \"quantity\": 1 }"
    ```
*   **Sample Response (JSON):**
    ```json
    {
        "id": 1,
        "customerName": "John Doe",
        "product": "Laptop",
        "quantity": 1,
        "status": "CREATED"
    }
    ```

#### 2. Get All Orders

*   **Endpoint:** `GET /orders`
*   **Description:** Retrieves a list of all orders.
*   **Sample `curl` Command:
    ```bash
    curl http://localhost:8080/orders
    ```
*   **Sample Response (JSON):**
    ```json
    [
        {
            "id": 1,
            "customerName": "John Doe",
            "product": "Laptop",
            "quantity": 1,
            "status": "CREATED"
        },
        {
            "id": 2,
            "customerName": "Jane Smith",
            "product": "Mouse",
            "quantity": 2,
            "status": "CREATED"
        }
    ]
    ```

#### 3. Get Order by ID

*   **Endpoint:** `GET /orders/{id}`
*   **Description:** Retrieves a single order by its ID.
*   **Sample `curl` Command (e.g., for ID 1):
    ```bash
    curl http://localhost:8080/orders/1
    ```
*   **Sample Response (JSON):**
    ```json
    {
        "id": 1,
        "customerName": "John Doe",
        "product": "Laptop",
        "quantity": 1,
        "status": "CREATED"
    }
    ```

### GraphQL API

**Endpoint:** `http://localhost:8080/graphql`

The GraphQL schema is defined in `src/main/resources/graphql/schema.graphqls`.

#### Schema Definition

```graphql
type Query {
    orders: [Order]
    order(id: ID!): Order
}

type Mutation {
    createOrder(customerName: String!, product: String!, quantity: Int!): Order
}

type Order {
    id: ID!
    customerName: String!
    product: String!
    quantity: Int!
    status: String!
}
```

#### 1. Get All Orders (Query)

*   **Description:** Fetches all orders.
*   **Sample `curl` Command:
    ```bash
    curl -X POST http://localhost:8080/graphql \
    -H "Content-Type: application/json" \
    -d "{ \"query\": \"query { orders { id customerName product quantity status } }\" }"
    ```
*   **Sample Response (JSON):**
    ```json
    {
        "data": {
            "orders": [
                {
                    "id": "1",
                    "customerName": "John Doe",
                    "product": "Laptop",
                    "quantity": 1,
                    "status": "CREATED"
                },
                {
                    "id": "2",
                    "customerName": "Jane Smith",
                    "product": "Mouse",
                    "quantity": 2,
                    "status": "CREATED"
                }
            ]
        }
    }
    ```

#### 2. Get Order by ID (Query)

*   **Description:** Fetches a single order by its ID.
*   **Sample `curl` Command (e.g., for ID 1):
    ```bash
    curl -X POST http://localhost:8080/graphql \
    -H "Content-Type: application/json" \
    -d "{ \"query\": \"query { order(id: 1) { id customerName product quantity status } }\" }"
    ```
*   **Sample Response (JSON):**
    ```json
    {
        "data": {
            "order": {
                "id": "1",
                "customerName": "John Doe",
                "product": "Laptop",
                "quantity": 1,
                "status": "CREATED"
            }
        }
    }
    ```

#### 3. Create a New Order (Mutation)

*   **Description:** Creates a new order.
*   **Sample `curl` Command:
    ```bash
    curl -X POST http://localhost:8080/graphql \
    -H "Content-Type: application/json" \
    -d "{ \"query\": \"mutation { createOrder(customerName: \"Alice Brown\", product: \"Keyboard\", quantity: 5) { id customerName product quantity status } }\" }"
    ```
*   **Sample Response (JSON):**
    ```json
    {
        "data": {
            "createOrder": {
                "id": "3",
                "customerName": "Alice Brown",
                "product": "Keyboard",
                "quantity": 5,
                "status": "CREATED"
            }
        }
    }
    ```

## H2 Database Console

You can access the H2 database console to inspect the data directly.

*   **URL:** `http://localhost:8080/h2-console`
*   **JDBC URL:** `jdbc:h2:mem:testdb` (default, as configured in `application.properties`)
*   **Username:** `sa` (default)
*   **Password:** (empty by default)

Click "Connect" to access the database.
