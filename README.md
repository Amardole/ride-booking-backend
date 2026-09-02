# 🚕 Ride Booking Application Backend

A **Ride Booking REST API** built using **Java 21, Spring Boot, Spring Data JPA, Hibernate and PostgreSQL**.

This backend provides APIs for managing users, drivers, vehicles, rides and transactions. It also includes automatic driver assignment, fare calculation based on vehicle type and distance calculation using OpenRouteService.

---

## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Maven**
* **OpenRouteService API**
* **Swagger / OpenAPI**
* **Jakarta Bean Validation**

---

# 🏗️ Project Architecture

The project follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
DAO
    ↓
Repository
    ↓
PostgreSQL
```

### Project Structure

```text
com.rideApp.RideBookingApp
│
├── controller
│   ├── DriverController
│   ├── RideController
│   ├── TransactionController
│   ├── UserController
│   └── VahicleController
│
├── service
│   ├── DriverService
│   ├── RideService
│   ├── TransactionService
│   ├── UserService
│   └── VahicleService
│
├── dao
│   ├── DriverDao
│   ├── FareCalculator
│   ├── RideDao
│   ├── TransactionDao
│   ├── UserDao
│   └── VahicleDao
│
├── repo
│   ├── DriverRepo
│   ├── RideRepo
│   ├── TransactionRepo
│   ├── UserRepo
│   └── VahicleRepo
│
├── entity
│   ├── User
│   ├── Driver
│   ├── Vahicle
│   ├── Ride
│   └── Transaction
│
├── dto
│   ├── ResponceStructure
│   └── DriverNameEmailDto
│
├── enums
│   ├── DriverStatus
│   ├── RideStatus
│   ├── TransactionType
│   └── VahicleType
│
├── exception
│   ├── GlobalExceptionHandler
│   ├── UserNotFoundException
│   ├── DriverNotFoundException
│   ├── VahicleNotFoundException
│   ├── RideNotFoundException
│   ├── TransactionNotFoundException
│   └── DriverNotAvaliableException
│
└── location
    └── DistanceService
```

---

# 📦 Entities

## 👤 User

Represents a customer who can book rides.

```json
{
  "id": 1,
  "name": "Amar",
  "email": "amar@example.com",
  "password": "Password@123"
}
```

### User Features

* Create user
* Update user
* Find user by ID
* Find user by email
* Find all users
* Delete user
* Batch user creation

---

## 🚗 Driver

Represents a driver who can be assigned to rides.

```json
{
  "id": 1,
  "name": "Rahul",
  "email": "rahul@example.com",
  "contact_no": 9876543210,
  "driverStatus": "AVAILABLE"
}
```

### Driver Status

```text
AVAILABLE
BUSY
OFFLINE
```

---

## 🚘 Vehicle

Represents a vehicle associated with a driver.

```json
{
  "id": 1,
  "vahicle_no": "MH12AB1234",
  "vahicletype": "SEDAN"
}
```

### Vehicle Types

```text
BIKE
AUTO
MINI
SEDAN
XUV
```

---

## 🚕 Ride

Represents a ride booked by a user.

```json
{
  "id": 1,
  "source": "Pune",
  "destination": "Mumbai",
  "fare": 1250.0,
  "ridestatus": "ONGOING"
}
```

### Ride Status

```text
ONGOING
COMPLETED
REJECTED
INCOMPLETED
```

---

## 💳 Transaction

Represents the payment transaction for a completed ride.

### Transaction Types

```text
CASH
UPI
CARD
```

Example:

```json
{
  "id": 1,
  "transactionType": "UPI"
}
```

---

# 📤 Common Response Structure

The application uses a generic response wrapper:

```java
ResponceStructure<T>
```

Example response:

```json
{
  "message": "User Created Successfully...",
  "statusCode": 201,
  "data": {
    "id": 1,
    "name": "Amar",
    "email": "amar@example.com"
  }
}
```

### Response Fields

| Field        | Description                    |
| ------------ | ------------------------------ |
| `message`    | Operation result message       |
| `statusCode` | HTTP status code               |
| `data`       | Actual response object or list |

The generic `<T>` allows the same response structure to return different types of data.

---

# 🔗 REST API Endpoints

Base URL:

```text
http://localhost:8080
```

---

# 👤 User APIs

## 1. Create User

```http
POST /user/add
```

### Request

```json
{
  "name": "Amar",
  "email": "amar@example.com",
  "password": "Password@123"
}
```

### Response

```json
{
  "message": "User Created Successfully...",
  "statusCode": 201,
  "data": {
    "id": 1,
    "name": "Amar",
    "email": "amar@example.com"
  }
}
```

**Status:** `201 CREATED`

---

## 2. Create Multiple Users

```http
POST /user/addUserBatch
```

### Request

```json
[
  {
    "name": "Amar",
    "email": "amar@example.com",
    "password": "Password@123"
  },
  {
    "name": "Rahul",
    "email": "rahul@example.com",
    "password": "Rahul@123"
  }
]
```

**Status:** `200 OK`

---

## 3. Get All Users

```http
GET /user/findAllUsers
```

Returns all registered users.

**Status:** `200 OK`

---

## 4. Get User By ID

```http
GET /user/findUserById/{id}
```

Example:

```http
GET /user/findUserById/1
```

**Status:** `302 FOUND`

---

## 5. Get User By Email

```http
GET /user/findUserByEmail/{email}
```

Example:

```http
GET /user/findUserByEmail/amar@example.com
```

**Status:** `302 FOUND`

---

## 6. Update User

```http
PUT /user/update/{id}/{name}/{email}
```

Example:

```http
PUT /user/update/1/Amar/amar@example.com
```

**Status:** `200 OK`

---

## 7. Delete User By ID

```http
DELETE /user/deleteById/{id}
```

Example:

```http
DELETE /user/deleteById/1
```

**Status:** `200 OK`

---

## 8. Delete User By Email

```http
DELETE /user/deleteByEmail/{email}
```

Example:

```http
DELETE /user/deleteByEmail/amar@example.com
```

**Status:** `200 OK`

---

# 🚗 Driver APIs

## 1. Add Driver

```http
POST /driver/add
```

### Request

```json
{
  "name": "Rahul",
  "email": "rahul@example.com",
  "contact_no": 9876543210,
  "driverStatus": "AVAILABLE"
}
```

**Status:** `201 CREATED`

---

## 2. Get All Drivers

```http
GET /driver/getAllDriver
```

**Status:** `200 OK`

---

## 3. Get Driver By ID

```http
GET /driver/getDriver/{id}
```

Example:

```http
GET /driver/getDriver/1
```

**Status:** `302 FOUND`

---

## 4. Get Driver By Status

```http
GET /driver/getDriverByStatus?status=AVAILABLE
```

Supported statuses:

```text
AVAILABLE
BUSY
OFFLINE
```

**Status:** `200 OK`

---

## 5. Get Driver By Email

```http
GET /driver/getDriverByEmail/{email}
```

Example:

```http
GET /driver/getDriverByEmail/rahul@example.com
```

**Status:** `302 FOUND`

---

## 6. Update Driver

```http
PUT /driver/UpdateById/{id}
```

### Request

```json
{
  "id": 1,
  "name": "Rahul Updated",
  "email": "rahul@example.com",
  "contact_no": 9999999999,
  "driverStatus": "AVAILABLE"
}
```

**Status:** `200 OK`

---

## 7. Delete Driver By ID

```http
DELETE /driver/deleteById/{id}
```

Example:

```http
DELETE /driver/deleteById/1
```

**Status:** `200 OK`

---

## 8. Delete Driver By Email

```http
DELETE /driver/deleteByEmail/{email}
```

Example:

```http
DELETE /driver/deleteByEmail/rahul@example.com
```

**Status:** `200 OK`

---

## 9. Get Driver Name And Email

```http
GET /driver/getNameAndEmail
```

### Response

```json
[
  {
    "name": "Rahul",
    "email": "rahul@example.com"
  }
]
```

This endpoint uses `DriverNameEmailDto` instead of returning the complete Driver entity.

**Status:** `200 OK`

---

## 10. Delete Drivers By Status

```http
DELETE /driver/deleteByStatus?status=OFFLINE
```

Example:

```http
DELETE /driver/deleteByStatus?status=OFFLINE
```

**Status:** `200 OK`

---

# 🚘 Vehicle APIs

> The existing project uses the spelling `Vahicle` in class and endpoint names.

## 1. Add Vehicle For Driver

```http
POST /vahicle/saveVahicle/DriverId/{id}
```

Example:

```http
POST /vahicle/saveVahicle/DriverId/1
```

### Request

```json
{
  "vahicle_no": "MH12AB1234",
  "vahicletype": "SEDAN"
}
```

The Driver ID associates the vehicle with a driver.

**Status:** `201 CREATED`

---

## 2. Get All Vehicles

```http
GET /vahicle/getAll
```

**Status:** `200 OK`

---

## 3. Get Vehicle By ID

```http
GET /vahicle/getById/{id}
```

Example:

```http
GET /vahicle/getById/1
```

**Status:** `200 OK`

---

## 4. Get Vehicle By Type

```http
GET /vahicle/getVahicleByType?vahicleType=SEDAN
```

Supported types:

```text
BIKE
AUTO
MINI
SEDAN
XUV
```

**Status:** `200 OK`

---

## 5. Get Vehicle By Number

```http
GET /vahicle/getVahicleByNo/{no}
```

Example:

```http
GET /vahicle/getVahicleByNo/MH12AB1234
```

**Status:** `302 FOUND`

---

## 6. Update Vehicle Number

```http
PUT /vahicle/updateNo/{id}?no=MH12CD5678
```

Example:

```http
PUT /vahicle/updateNo/1?no=MH12CD5678
```

**Status:** `200 OK`

---

## 7. Update Vehicle Type

```http
PUT /vahicle/updateType/{id}?type=MINI
```

Example:

```http
PUT /vahicle/updateType/1?type=MINI
```

**Status:** `200 OK`

---

## 8. Update Vehicle Driver

```http
PUT /vahicle/updateDriver/{id}?driverId=2
```

Example:

```http
PUT /vahicle/updateDriver/1?driverId=2
```

**Status:** `200 OK`

---

## 9. Delete Vehicle

```http
DELETE /vahicle/delete/{id}
```

Example:

```http
DELETE /vahicle/delete/1
```

**Status:** `200 OK`

---

## 10. Delete Vehicle By Driver

```http
DELETE /vahicle/deleteVahicleByDriver/{id}
```

Example:

```http
DELETE /vahicle/deleteVahicleByDriver/1
```

**Status:** `200 OK`

---

# 🚕 Ride APIs

## 1. Book Ride

```http
POST /Ride/addRide/userId/{userid}
```

Example:

```http
POST /Ride/addRide/userId/1?source=Pune&destination=Mumbai&vahicleType=SEDAN
```

### Parameters

| Parameter     | Example  |
| ------------- | -------- |
| `userid`      | `1`      |
| `source`      | `Pune`   |
| `destination` | `Mumbai` |
| `vahicleType` | `SEDAN`  |

### Ride Booking Flow

```text
User
 ↓
Select Source
 ↓
Select Destination
 ↓
Select Vehicle Type
 ↓
Find Available Vehicle
 ↓
Find Available Driver
 ↓
Assign Driver
 ↓
Driver Status → BUSY
 ↓
Calculate Distance
 ↓
Calculate Fare
 ↓
Create Ride
```

**Status:** `201 CREATED`

---

## 2. Complete Ride

```http
POST /Ride/completeRide/rideid/{rideid}?transactionType=UPI
```

Example:

```http
POST /Ride/completeRide/rideid/1?transactionType=UPI
```

Supported payment methods:

```text
CASH
UPI
CARD
```

### Ride Completion Flow

```text
ONGOING Ride
      ↓
Create Transaction
      ↓
Driver Status → AVAILABLE
      ↓
Ride Status → COMPLETED
      ↓
Save Transaction
      ↓
Save Ride
```

**Status:** `200 OK`

---

## 3. Get All Rides

```http
GET /Ride/getAllRides
```

**Status:** `200 OK`

---

## 4. Get Rides By Status

```http
GET /Ride/getByStatus/{rideStatus}
```

Example:

```http
GET /Ride/getByStatus/COMPLETED
```

Supported statuses:

```text
ONGOING
COMPLETED
REJECTED
INCOMPLETED
```

**Status:** `200 OK`

---

## 5. Get Rides By Driver

```http
GET /Ride/getByDriver/{driverid}
```

Example:

```http
GET /Ride/getByDriver/1
```

**Status:** `200 OK`

---

# 💳 Transaction APIs

## 1. Get Transaction By ID

```http
GET /Transaction/findById/{id}
```

Example:

```http
GET /Transaction/findById/1
```

**Status:** `302 FOUND`

---

## 2. Get All Transactions

```http
GET /Transaction/AllTransactions
```

**Status:** `200 OK`

---

## 3. Get Transactions By Type

```http
GET /Transaction/getByType?transactionType=UPI
```

Supported types:

```text
CASH
UPI
CARD
```

**Status:** `200 OK`

---

## 4. Get Transactions By User

```http
GET /Transaction/getByUserId/{userid}
```

Example:

```http
GET /Transaction/getByUserId/1
```

**Status:** `200 OK`

---

# 💰 Fare Calculation

The application calculates the ride fare according to the selected vehicle type and route distance.

### Formula

```text
Fare = Base Fare + (Distance × Per KM Rate)
```

### Fare Configuration

| Vehicle Type | Base Fare | Per KM |
| ------------ | --------: | -----: |
| BIKE         |       ₹15 |     ₹6 |
| AUTO         |       ₹25 |    ₹10 |
| MINI         |       ₹30 |    ₹13 |
| SEDAN        |       ₹40 |    ₹18 |
| XUV          |       ₹60 |    ₹22 |

### Example

For a Sedan travelling 10 km:

```text
Base Fare = ₹40
Distance = 10 km
Rate = ₹18/km

Fare = 40 + (10 × 18)

Total Fare = ₹220
```

---

# 📍 Distance Calculation

The application contains a `DistanceService` which integrates with **OpenRouteService**.

### Process

```text
Source
   ↓
Geocoding API
   ↓
Source Coordinates
   ↓
Routing API
   ↓
Destination Coordinates
   ↓
Route Distance
   ↓
Fare Calculation
```

Configuration:

```properties
openroute.api.key=YOUR_API_KEY
```

---

# 🔗 Entity Relationships

```text
                 ┌──────────────┐
                 │     User     │
                 └──────┬───────┘
                        │
                  1     │     N
                        ↓
                 ┌──────────────┐
                 │     Ride     │
                 └──────┬───────┘
                        │
                  N     │     1
                        ↓
                 ┌──────────────┐
                 │    Driver    │
                 └──────┬───────┘
                        │
                  1     │     1
                        ↓
                 ┌──────────────┐
                 │   Vehicle    │
                 └──────────────┘


User
 │
 │ 1 : N
 ↓
Transaction
```

The project demonstrates JPA relationships such as:

```java
@OneToOne
@OneToMany
@ManyToOne
```

`@JsonIgnore` is used on relationship fields where necessary to avoid recursive JSON serialization.

---

# ⚠️ Exception Handling

The application uses custom exceptions:

```text
UserNotFoundException
DriverNotFoundException
VahicleNotFoundException
RideNotFoundException
TransactionNotFoundException
DriverNotAvaliableException
```

A centralized:

```text
GlobalExceptionHandler
```

handles these exceptions and returns meaningful error responses.

### Example

If a user does not exist:

```json
{
  "message": "User Not Found",
  "statusCode": 404,
  "data": null
}
```

---

# 📖 Swagger / OpenAPI

Swagger is included for API documentation and testing.

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger allows you to:

* View all APIs
* View HTTP methods
* Enter parameters
* Send API requests
* Test endpoints
* View response structures

---

# 🗄️ Database Configuration

The application uses **PostgreSQL**.

Example configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rideBooking
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Create the database:

```sql
CREATE DATABASE rideBooking;
```

⚠️ **Never commit real database passwords or API keys to GitHub.**

Use environment variables or local configuration for sensitive values.

---

# 🚀 How To Run

## Prerequisites

Install:

* Java 21
* Maven
* PostgreSQL
* Git

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

---

## 1. Clone Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

```bash
cd RideBookingApp
```

---

## 2. Create PostgreSQL Database

```sql
CREATE DATABASE rideBooking;
```

---

## 3. Configure Database

Update:

```text
src/main/resources/application.properties
```

with your PostgreSQL username and password.

---

## 4. Configure OpenRouteService

Add your API key:

```properties
openroute.api.key=YOUR_OPENROUTESERVICE_API_KEY
```

---

## 5. Run Application

Using Maven:

```bash
mvn spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Application will start at:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🧪 Complete API Testing Flow

To test the complete ride booking workflow:

```text
1. Create User
       ↓
2. Create Driver
       ↓
3. Create Vehicle
       ↓
4. Book Ride
       ↓
5. Find Available Driver
       ↓
6. Driver becomes BUSY
       ↓
7. Calculate Distance
       ↓
8. Calculate Fare
       ↓
9. Complete Ride
       ↓
10. Create Transaction
       ↓
11. Driver becomes AVAILABLE
       ↓
12. Ride becomes COMPLETED
```

---

# 📊 API Summary

| Module             | Features                                                                                    |
| ------------------ | ------------------------------------------------------------------------------------------- |
| User               | Create, Batch Create, Find All, Find By ID, Find By Email, Update, Delete                   |
| Driver             | Create, Find All, Find By ID, Find By Status, Find By Email, Update, Delete, DTO Projection |
| Vehicle            | Create, Find All, Find By ID, Find By Type, Find By Number, Update, Delete                  |
| Ride               | Book Ride, Complete Ride, Find All, Find By Status, Find By Driver                          |
| Transaction        | Find By ID, Find All, Find By Type, Find By User                                            |
| Fare               | Vehicle-based fare calculation                                                              |
| Distance           | OpenRouteService integration                                                                |
| Exception Handling | Centralized global exception handling                                                       |
| Documentation      | Swagger / OpenAPI                                                                           |

---

# 🔐 Validation

The application uses Jakarta Bean Validation.

Examples:

```java
@Email
@Pattern
@PositiveOrZero
```

Validation is applied to appropriate entity fields.

Examples:

* Invalid email → validation error
* Invalid password → validation error
* Invalid vehicle number → validation error
* Invalid numeric values → validation error

---

# 🎯 Key Features

### ✅ Layered Architecture

```text
Controller → Service → DAO → Repository → Database
```

Separates API handling, business logic and database operations.

### ✅ Generic Response

```java
ResponceStructure<T>
```

Provides a consistent API response format.

### ✅ Custom Exceptions

Provides meaningful business-level error messages.

### ✅ Driver Availability Management

Drivers are automatically assigned based on availability.

```text
AVAILABLE → BUSY → AVAILABLE
```

### ✅ Dynamic Fare Calculation

Fare depends on:

```text
Vehicle Type + Distance
```

### ✅ External API Integration

OpenRouteService is used for route and distance calculation.

### ✅ JPA Entity Relationships

Demonstrates:

```text
@OneToOne
@OneToMany
@ManyToOne
```

### ✅ Transaction Management

Ride completion updates the ride, driver and transaction as part of the business operation.

### ✅ Swagger Documentation

All REST APIs can be tested through Swagger UI.

---

# 📌 Project Notes

* The existing project uses `Vahicle` instead of the standard spelling `Vehicle`.
* The existing project uses `ResponceStructure` instead of the standard spelling `ResponseStructure`.
* Never commit passwords, API keys or other secrets to GitHub.
* Use environment variables for production credentials.
* For a production REST API, `200 OK` is generally more conventional for successful GET operations than `302 FOUND`.

---

# 👨‍💻 Author

**Amar Dole**

Java | Spring Boot | Backend Developer

---

## ⭐ Support

If you find this project useful, consider giving the repository a ⭐ on GitHub.
