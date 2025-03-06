# Currency Exchange and Discount Calculation Application

## Overview

This Spring Boot application calculates the total payable amount for a bill in a specified currency after applying discounts. It integrates with a third-party currency exchange API to retrieve real-time exchange rates and provides functionality to calculate applicable discounts. The application exposes a single API endpoint: `/api/calculate` to calculate the payable amount based on user inputs.
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