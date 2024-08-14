# **Spring Security JWT Register Login**

This is a Spring Boot application project with Spring Security, JWT authentication. See installation and configuration below.

## **Prerequisites**

Ensure you have the following installed:

- Java 17 or higher
- Maven 3.6 or higher

## **Dependencies and Build**

1. Clone the repository from GitHub and navegate to the project folder:
```bash
git clone https://github.com/sheidiz/Java-Spring-Tutorials.git
cd Java-Spring-Tutorials
cd spring-security
```
2. Install Project Dependencies ->
Use Maven to install dependencies and build the project:
```bash
mvn clean install
```
3. Configure the application.properties File ->
Create a .env file with your own database information and jwt secret key. Make sure you don't upload it to your repository, but do use it locally. Here is a sample configuration:
```bash
#DATABASE CONFIGURATION
SPRING_DATASOURCE_URL=your_database_connection
SPRING_DATASOURCE_USERNAME=your_database_username
SPRING_DATASOURCE_PASSWORD=your_database_password

#JWT SECRET KEY
JWT_SECRET_KEY=your_generated_jwt_secret_key
```
4. Run the Application -> 
Once built, *run the application with:
```bash
mvn spring-boot:run
```
_*The application will start on port 8080 by default, unless otherwise configured in application.properties._

### **API Usage**
1. Public Endpoints -> The following endpoints are available without authentication:
- POST /auth/register - Register a new user
- POST /auth/login - User login
2. Protected Endpoints -> The following endpoints require a valid JWT token in the Authorization header:
- GET /users - Retrieves a list of users. Requires a valid JWT token.
- GET /users/me - Retrieves logged user information. Requires a valid JWT token.

Example request for the protected endpoint:
```bash
GET /users/me
Authorization: Bearer <your_jwt_token>
```
Successful response (200 OK):
```bash
{
  "id":1,
  "email":"example.com",
  "fullName":"Example"
}
```

### Additional Configuration
1. CORS Configuration: Ensure the CORS configuration in SecurityConfiguration allows the necessary origins, methods, and headers for your frontend application. By default at config/SecurityConfiguration it accepts "https://app-backend.com" and "http://localhost:8080"
```bash
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("https://app-backend.com", "http://localhost:8080"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
```
2. Security: Ensure that the JWT secret key (jwt.secret) is secure and not exposed in source code or repositories.
