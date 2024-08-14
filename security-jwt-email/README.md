# **Spring Security JWT Email**

This is a Spring Boot application project with Spring Security, JWT authentication. Below is a guide for installation and configuration.

## **Prerequisites**

Ensure you have the following installed:

- Java 17 or higher
- Maven 3.6 or higher
- An email account for sending verification emails (if email functionality is used)

## **Clone the Repository**

First, clone the repository from GitHub:

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### **Environment Configuration**
1. Configure the application.properties File ->
Create or edit the src/main/resources/application.properties file with the necessary configuration. Here is a sample configuration:
```bash
#DATABASE CONFIGURATION
SPRING_DATASOURCE_URL=your_database_connection
SPRING_DATASOURCE_USERNAME=your_database_username
SPRING_DATASOURCE_PASSWORD=your_database_password

#JWT SECRET KEY
JWT_SECRET_KEY=your_generated_jwt_secret_key

#MAIL CONFIG
MAIL_HOST=smtp.your-email-provider.com
MAIL_PORT=your_mail_provider_port
MAIL_USERNAME=your_mail_provider_username
MAIL_PASSWORD=your_mail_provider_password
```
2. Configure Email Credentials ->
If you are using an email provider to send verification emails, ensure you configure the correct credentials in application.properties.

- Gmail: If using Gmail, enable access for less secure apps or use an app-specific password.
- Mailtrap: If using Mailtrap, make sure to enter the correct SMTP server details and credentials.

### **Dependencies and Build**
1. Install Project Dependencies ->
Use Maven to install dependencies and build the project:
```bash
mvn clean install
```
2. Run the Application -> 
Once built, run the application with:
```bash
mvn spring-boot:run
```
**The application will start on port 8080 by default, unless otherwise configured in application.properties.

### **API Usage**
1. Public Endpoints -> The following endpoints are available without authentication:
- POST /auth/register - Register a new user
- POST /auth/login - User login
2. Protected Endpoints -> The following endpoints require a valid JWT token in the Authorization header:
- GET /example - Retrieves a list of example technologies. Requires a valid JWT token.
- GET / - Retrieves a list of registered users. Requires a valid JWT token.

Example request for the protected endpoint:
```bash
GET /example
Authorization: Bearer <your_jwt_token>
```
Successful response (200 OK):
```bash
["HTML", "CSS", "JavaScript"]
```

### Additional Configuration
1. CORS Configuration: Ensure the CORS configuration in SecurityConfiguration allows the necessary origins, methods, and headers for your frontend application. By default at SecurityConfiguration it accepts "https://app-backend.com" and "http://localhost:8080"
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
3. Email: For local testing, you may use services like Mailtrap to avoid sending real emails.
