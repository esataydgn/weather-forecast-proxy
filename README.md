# weather-forecast-proxy app

# Task

Using Java (1.8 or newer), create an API that will retrieve weather metrics of a specific city.
Please use https://openweathermap.org/ to create a free account and retrieve the data for this
case study.

#   The requirements:
1. The API should expose a “/data” endpoint to retrieve the averages

2. The “/data” endpoint should return a JSON object that gives the averages of the following metrics:
    
    ✓   Average temperature (in Celsius) of the next 3 days from today’s date for Day time (06:00 – 18:00) and Night time (18:00 – 06:00)
    
    ✓   Average of pressure for the next 3 days from today’s date.
3. The “/data” endpoint must include a CITY parameter containing the city’s name as the
input for the correct response.
4. You must use full REST API conventions. Correct error codes must be returned when
necessary

 
# Environments

Java 8.

Rest Template

junit-jupiter

Lombook

Spring Boot 2.

Swagger.

##  Running the App

After cloning from the existing repository via git. Import to your favorite IDE as existing maven project and then maven clean install. 

`mvn clean install pom.xml -f -U`

`mvn spring-boot:run`

##  Running the Test Cases

After deployin the application to the IDE, select the project then run test cases by maven. I did not have time to create integration test via Mockito.

# Documentation

Swagger documentation can be reached [here Swagger-UI](http://localhost:8080/swagger-ui.html) while the project is running 

# Example of usage 

[http://localhost:8080/data/istanbul](http://localhost:8080/data/istanbul)

Response should be like this.

`{
     "dailyAverageTemperature": 10.42,
     "nightlyAverageTemperature": 9.65,
     "pressureAverage": 1032.69
 }`

# Example of wrong parameter

[http://localhost:8080/data/istanbul123](http://localhost:8080/data/istanbul123)
Response will be like this when the city is not exist or the city parameter has not been sent.

`{
     "status": "NOT_FOUND",
     "message": "istanbul1 is invalid!!!",
     "developerMessage": "The city provided was not found"
 }`
