# Billing Application

## Overview

This Spring Boot application calculates the total payable amount for a bill in a specified currency after applying discounts. It integrates with a third-party currency exchange API to retrieve real-time exchange rates and provides functionality to calculate applicable discounts. The application exposes a single API endpoint: `/api/calculate` to calculate the payable amount based on user inputs.

### Getting Started
Prerequisites
 * Java 21 or higher
 * Gradle (for build)
 * Postman or any REST client for testing the APIs
 * jacoco Plugin

### Setting up the Project
1. Clone the Repository:
    *  git clone git@github.com:jasminfrancis/Ecommerce-project.git
    *  After cloning you will get project folder named as Ecommerce-project(If asking the password give:GitPassword@123) or download the zip file


### API Documentation
 * Default port :8085( actual deployment you can use any. 8085 port used only for development)
 * Login Endpoint (POST /api/login)
 * Local endpoint (POST http://localhost:8085/api/login)
 * This endpoint authenticates a user and returns a JWT token.
### Request Format:
```json
{
  "username": "jasmin",
  "password": "Jasmin@123"
}
```
### Response Format:
```json
{
   "message": "Login success",
   "status": "Login success",
   "httpCode": 200,
   "response": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXNtaW4iLCJzMTk0MjcsImV4cCI6MTc0MTMyMzAyN30.FxkHP0S_aMYe5pJaz-REyQAKtvH1UOe0KnDzQIWQraA"
}
```

## BillAmount Calculation

This endpoint allows users to submit a bill in one currency and get the payable amount in another currency after applying discounts. The request body should contain the following information:

* Billing endpoint :(POST Request to /api/calculate)
* Local endpoint: http://localhost:8085/api/calculate

Headers:

* Authorization: Bearer <your_jwt_token_here>
* Content-Type:application/json

### Request Format:
```json
{
  "user": {
    "employee": true,
    "affiliate": false,
    "customerForOverTwoYears": false
  },
  "items": [
    {"name": "watch", "price": 200.25, "grocery": false},
    {"name": "banana", "price": 50.32, "grocery": true},
    {"name": "salwar", "price": 150, "grocery": false}
  ],
  "originalCurrency": "USD",
  "targetCurrency": "AED"
}
```

### Response Format:
```json
{
    "message": "Data retrieved success",
    "status": "Data retrieved success",
    "httpCode": 200,
    "response": 1011.7553875
}
```
### Description of Parameters:

* user- 3 types of users. Employee,Affiliate,customerForOverTwoYears. Based on the user provide the discount
* items-Purchasing items and their category.(category =grocery) for identifying the grocery item give proper  discounts
* originalCurrency- Base courrency (bill amount currency)
* targetCurrency-Conversion currency(payable amount currency )

### Authentication:

For secure access, the application uses JWT authentication. To obtain a token, use the /api/login endpoint (as described in the documentation).
Include the token in the Authorization header when calling the /api/calculate endpoint:

Authorization: Bearer <your_jwt_token>
Content-Type:application/json


### Running the Application
1. Build the project:
   ./gradlew build
2. Run the application:
   ./gradlew bootRun


### API Key for Currency Exchange
The application uses the Open Exchange Rates API for fetching real-time currency exchange rates. You will need an API key from the Open Exchange Rates platform to make the currency exchange API work.
Replace the placeholder in application.properties:
* currency.api.key=jasminfrancis (But realtime need more specific keys should be added)



## Test Coverage Report (JaCoCo)

### **1. Running Tests and Generating Coverage Report**
To run tests and generate the JaCoCo test coverage report, use the following Gradle command:

```sh
./gradlew test jacocoTestReport
```
### **1. Clean and Build**
```sh
./gradlew clean build jacocoTestReport
```
### **2. Viewing the Coverage Report**
build/jacocoHtml/index.html

### Dependencies
* Spring Boot - For building the backend API
* JWT (JSON Web Token) for authentication
* Open Exchange Rates API - For real-time currency exchange rates
* Lombok for simplifying Java code
* Gradle for project build and dependency management
* JUnit 5 for unit testing
* JaCoCo Test Coverage Report 