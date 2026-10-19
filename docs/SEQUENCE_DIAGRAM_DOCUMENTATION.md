# Master Data REST API - Sequence Diagram

## Overview
This document describes the sequence diagram for the Master Data REST API request flow. The diagram illustrates how a GET request flows through the system from the REST controller to the service layer.

## Sequence Flow

### 1. **Client Request**
- A client sends a GET HTTP request to the endpoint: `/api/{source}/{type}/{id}`
- Path parameters include:
  - `source`: Data source identifier
  - `type`: Data type
  - `id`: Unique identifier for the master data

### 2. **Controller Layer** (`MasterDataRestController`)
- The REST controller receives the incoming request
- Extracts path variables: `source`, `type`, and `id`
- Creates a `DataUpdateValidatorUUID` instance passing these parameters

### 3. **Validator** (`DataUpdateValidatorUUID`)
- Implements `Supplier<MasterDataUUID>` functional interface
- Performs validation on the input parameters:
  - Validates `source` parameter
  - Validates `type` parameter
  - Validates `id` parameter
- Stores the validated parameters for later use

### 4. **Service Layer** (`MasterDataService`)
- Controller calls `update(validator)` method on the service
- This method accepts a `Supplier<MasterDataUUID>` instance
- Delegates to the default implementation: `DefaultMasterDataService`

### 5. **Service Implementation** (`DefaultMasterDataService`)
- Executes the supplier's `get()` method
- This triggers the validator to create a `MasterDataUUID` instance with validated data
- Receives the created model object containing:
  - `id`: The master data UUID
  - `sourceId`: Source identifier
  - `type`: Data type
  - `clientId`: Client identifier
  - `title`: Data title

### 6. **Client Data Service** (`ClientDataService`)
- The service implementation calls `retrieve(masterDataUUID)`
- Retrieves the actual master data from the backend data source
- Returns a `ResponseEntity<MasterDataUUID>` with the data

### 7. **Response**
- The response propagates back through the service layers
- Controller returns the `ResponseEntity<MasterDataUUID>` to the client
- HTTP response with status 200 OK and JSON payload containing the master data

## Key Components

| Component | Role |
|-----------|------|
| `MasterDataRestController` | Entry point for HTTP requests |
| `DataUpdateValidatorUUID` | Validates input and provides data model via Supplier pattern |
| `MasterDataService` | Interface defining service contract |
| `DefaultMasterDataService` | Implements service logic |
| `ClientDataService` | Retrieves actual data from backend |
| `MasterDataUUID` | Model representing master data |

## Design Patterns Used

1. **Dependency Injection**: Services are injected via constructor (Lombok's `@RequiredArgsConstructor`)
2. **Supplier Pattern**: Validator implements `Supplier<MasterDataUUID>` for lazy evaluation and validation
3. **ResponseEntity Pattern**: Spring's `ResponseEntity` for flexible HTTP responses
4. **Separation of Concerns**: Clear separation between controller, service, and data layers

## How to View the Diagram

The sequence diagram is provided in PlantUML format (`MasterDataFlow_SequenceDiagram.puml`). You can view it using:
- Online PlantUML editors (plant.jdi.io)
- IDE plugins (VS Code, IntelliJ, Eclipse)
- Jenkins/CI/CD documentation tools
- Convert to PNG/SVG using PlantUML CLI

## Example Request Flow

```
GET http://localhost:8080/api/SALESFORCE/ACCOUNT/550e8400-e29b-41d4-a716-446655440000
```

The system validates all three parameters, creates the MasterDataUUID model, retrieves the data from ClientDataService, and returns:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "sourceId": "SALESFORCE",
  "type": "ACCOUNT",
  "clientId": "client123",
  "title": "Account Name"
}
```
