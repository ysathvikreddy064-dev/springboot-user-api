**Spring Boot User API — Production‑Style Backend (By Sathvik)**

This project demonstrates how I design real-world, production-ready backend services using Java \& Spring Boot.

It focuses on clean architecture, validation, exception handling, logging, and observability — exactly the areas MTI emphasized during the interview.


🚀 1. Overview

This service exposes CRUD operations for a User resource and is intentionally built to reflect production engineering standards, not a toy CRUD project.

Key features:

DTO validation using Jakarta Validation
Global exception handling with structured error responses
Correlation ID–based logging for traceability
Layered architecture (Controller → Service → Model)
Consistent API response wrapper
Readable, maintainable code with clear separation of concerns

This project demonstrates how I (Sathvik) approach backend development in a real engineering environment.


🧱 2. Architecture

Code

Client
&#x20;  ↓
\[Controller Layer]  →  Input validation, request mapping
&#x20;  ↓
\[Service Layer]     →  Business logic, logging, data operations
&#x20;  ↓
\[Model Layer]       →  Domain objects
&#x20;  ↓
\[Exception Handler] →  Centralized error mapping
&#x20;  ↓
\[Correlation Filter]→  Adds correlationId to every log line

✔ Why this architecture?

Controller stays thin → only handles HTTP concerns
Service contains logic → easier to test \& maintain
Exception handler ensures consistent error responses
Correlation filter makes logs traceable across services
This mirrors how production microservices are structured.


🧩 3. Endpoints

Method	Endpoint	Description

GET	/api/users	Fetch all users
GET	/api/users/{id}	Fetch user by ID
POST	/api/users	Create a new user
PUT	/api/users/{id}	Update an existing user
DELETE	/api/users/{id}	Delete a user

Example request:
json
POST /api/users
{
&#x20; "name": "John Doe",
&#x20; "email": "john.doe@example.com"
}


🛡️ 4. Validation (DTO Layer)

The UserRequest DTO uses annotations:

java
@NotBlank(message = "Name is required")
@Email(message = "Email must be valid")

✔ Why this matters
Prevents invalid data from entering the system
Reduces bugs downstream
Produces clean, user-friendly error messages


⚠️ 5. Exception Handling (Global)

Centralized in GlobalExceptionHandler:
Handles validation errors
Handles not found errors
Handles unexpected errors
Returns consistent JSON responses

Example error response:

json
{
&#x20; "success": false,
&#x20; "message": "email: Email must be valid"
}

✔ Why this matters
Predictable API behavior
Cleaner controllers
Easier debugging


📊 6. Logging \& Observability

Every request gets a correlationId:
Extracted from X-Correlation-Id header
Or auto-generated
Added to MDC
Included in every log line

Example log:

Code
{"ts":"2026-04-16T15:30:12.123Z","level":"INFO","correlationId":"c12f...","logger":"UserController","msg":"POST /api/users name=John"}

✔ Why this matters
This is how real distributed systems are debugged — especially in integration-heavy environments like MTI.


🧪 7. In-Memory Store (For Demo Purposes)

The service uses a simple Map<UUID, User> to store users.

✔ Why this is acceptable
Keeps the project lightweight
Focuses on API design, validation, logging, and structure
Easy to replace with a database later

🛠️ 8. How to Run

bash
mvn clean spring-boot:run

Service runs at:
Code
http://localhost:8081


🩺 9. Troubleshooting Guide

❗ 400 Bad Request
Cause: Validation failure
Fix: Check request body fields

❗ 404 Not Found
Cause: User ID does not exist
Fix: Verify the UUID

❗ 500 Internal Server Error
Cause: Unexpected exception
Fix: Check logs filtered by correlationId

❗ Logs not showing correlationId
Cause: Missing header
Fix: Add X-Correlation-Id header or let the filter generate one

10. Summary

This project is intentionally small but built with real production engineering principles.
It shows how I (Sathvik) think about:

API design
Error handling
Logging
Observability
Maintainability
Documentation



