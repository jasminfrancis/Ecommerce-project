# Currency Exchange and Discount Calculation Application

## Overview

This Spring Boot application calculates the total payable amount for a bill in a specified currency after applying discounts. It integrates with a third-party currency exchange API to retrieve real-time exchange rates and provides functionality to calculate applicable discounts. The application exposes a single API endpoint: `/api/calculate` to calculate the payable amount based on user inputs.

## Endpoints

### `/api/calculate`

This endpoint allows users to submit a bill in one currency and get the payable amount in another currency after applying discounts. The request body should contain the following information:

### Request Format:
```json
{
  "user": {
    "employee": true,
    "affiliate": false,
    "customerForOverTwoYears": false
  },
  "items": [
    {"name": "Shirt", "price": 200.25, "grocery": false},
    {"name": "Apple", "price": 50.32, "grocery": true},
    {"name": "Shoes", "price": 150, "grocery": false}
  ],
  "originalCurrency": "USD",
  "targetCurrency": "AED"
}


 
Response Format:

{
    "message": "Data retrieved success",
    "status": "Data retrieved success",
    "httpCode": 200,
    "response": 1011.7553875
}

Description of Parameters:



Authentication:

For secure access, the application uses JWT authentication. To obtain a token, use the /api/login endpoint (as described in the documentation).
Include the token in the Authorization header when calling the /api/calculate endpoint:

Authorization: Bearer <your_jwt_token>
Content-Type:application/json

### API Key for Currency Exchange
The application uses the Open Exchange Rates API for fetching real-time currency exchange rates. You will need an API key from the Open Exchange Rates platform to make the currency exchange API work.
Replace the placeholder in application.properties:
currency.api.key=jasminfrancis