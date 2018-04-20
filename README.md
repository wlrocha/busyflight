The application runs in port 8080

Requires Java 1.8 and maven

CrazyAir API and ToughJet API are mocked with the following internal endpoints:
(random flight data is generated)
- crazyAir:
   endpoint: http://127.0.0.1:8080/crazyAir
- toughJet:
   endpoint: http://localhost:8080/toughJet

## Run Application:

to run the application use the following command:

`mvn spring-boot:run`

- To test in browser go to http://localhost:8080/test

- To consume the flight service use the following endpoint:

```
GET http://localhost:8080/flight/search?origin=LHR&destination=AMS&departureDate=2011-12-03&returnDate=2018-11-01&numberOfPassengers=2
```

## Run Integration Tests:
`mvn test`


