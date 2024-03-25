
# Event Management System

Welcome to the Event Management System developed using Spring Boot! This system provides a RESTful service for managing and querying event data based on geographical location and date.

# Tech Stack
- Framework: Spring Boot
- Web Dependency: Spring Web
- Data Access: Spring Data JPA
- Database: MySQL
- HTTP Client: Included in Spring Boot Starter Web

## Setup and Running

1. **Clone the Repository**: `git clone <repository-url>`
   
2. **Database Setup**:
   - Install MySQL and create a database.
   - Update the database configuration in `application.properties`.
   
3. **Run the Application**: 
   - Using Maven: `mvn spring-boot:run`
   - Using IDE: Run the `EventManagementApplication.java` class
   
4. **Access the API**: The API will be accessible at [http://localhost:8081/](http://localhost:8081/).
   
5. **Swagger Documentation**: Explore the API using [Swagger UI here](http://localhost:8081/swagger-ui/index.html#/).


# Application properties

```
spring.application.name=Eventmanagementsystem
spring.datasource.url=jdbc:mysql://localhost:3306/csvfile
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update

```
This configuration sets up your Spring Boot application with the specified properties, including the application name, server port, and MySQL database connection details. Adjust the database URL, username, and password as per your MySQL setup.

## API Documentation

### Event Finder API

- **Endpoint**: `/api/events/find`
- **Method**: GET
- **Query Parameters**:
    - `latitude`: User's source latitude
    - `longitude`: User's source longitude
    - `date`: Search date in YYYY-MM-DD format
    - `page`: Page number (optional)
- **Response Format**: JSON
    ```json
    {
        "events": [
            {
                "eventName": "Event Name",
                "cityName": "City Name",
                "date": "YYYY-MM-DD",
                "weather": "Weather Description",
                "distanceKm": Distance in Kilometers
            },
            ...
        ],
        "page": Page Number,
        "pageSize": Page Size,
        "totalEvents": Total Number of Events,
        "totalPages": Total Number of Pages
    }
    ```
- **Error Codes**:
    - 400 Bad Request: Invalid input parameters
    - 404 Not Found: Resource not found
    - 500 Internal Server Error: Server-side error



## Screenshots

![Sending Request using users latitude ,longitude and page no ](https://github.com/shashank77t/EventManagementSystem/blob/main/images/eventpic1.png)


![Response in JSON format ](https://github.com/shashank77t/EventManagementSystem/blob/main/images/eventpic2.png)


![](https://github.com/shashank77t/EventManagementSystem/blob/main/images/eventpic3.png)



![](https://github.com/shashank77t/EventManagementSystem/blob/main/images/eventpic4.png)
