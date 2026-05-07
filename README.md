# Student Management System - Spring Boot API

A production-ready REST API for managing students and courses built with Spring Boot 4.0.3, Spring Security, JWT, and MySQL.

## Features

✅ **RESTful API Endpoints** - Well-organized and documented endpoints
✅ **JWT Authentication** - Secure authentication with JWT tokens
✅ **CORS Support** - Cross-origin requests enabled
✅ **Global Exception Handling** - Consistent error responses
✅ **Input Validation** - Request body validation with detailed error messages
✅ **Logging** - Comprehensive logging to console and file
✅ **Soft Delete** - Records marked as inactive instead of being deleted
✅ **DTO Pattern** - Request/Response DTOs for clean API contracts
✅ **Active Records Filtering** - Automatic filtering of inactive records
✅ **Email Integration** - Auto-generated passwords sent via email

## Tech Stack

- **Framework:** Spring Boot 4.0.3
- **Security:** Spring Security 7.0.3 + JWT (jjwt 0.11.5)
- **Database:** MySQL 8.0+
- **ORM:** JPA/Hibernate
- **Java Version:** 21
- **Build Tool:** Maven

## Prerequisites

- Java 21 or higher
- MySQL 8.0 or higher
- Maven 3.6.0 or higher
- Git

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd Spring\ boot
```

### 2. Configure Database
Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/managementdb?createDatabaseIfNotExists=true
spring.datasource.username=root
spring.datasource.password=admin
```

**Create Database (Optional - Auto-creates):**
```sql
CREATE DATABASE IF NOT EXISTS managementdb;
```

### 3. Configure Email (Optional)
For sending auto-generated passwords to students:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

**For Gmail:**
1. Enable 2-Factor Authentication
2. Generate App-specific password
3. Use the app password in the configuration

### 4. Build the Application
```bash
mvn clean install
```

### 5. Run the Application
```bash
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

## Project Structure

```
src/main/java/com/student/management/
├── Application.java                 # Main Spring Boot Application
├── Controller/                      # REST Controllers
│   ├── AuthController.java
│   ├── StudentController.java
│   ├── CourseController.java
│   └── NotesController.java
├── Service/                         # Business Logic
│   ├── StudentService.java
│   ├── CourseService.java
│   ├── UserService.java
│   ├── EmailService.java
│   ├── CustomUserDetailsService.java
│   └── NotesService.java
├── Repository/                      # Data Access Layer
│   ├── StudentRepository.java
│   ├── CourseRepository.java
│   ├── UserRepo.java
│   └── NotesRepository.java
├── Entity/                          # JPA Entities
│   ├── Students.java
│   ├── Course.java
│   ├── UserTable.java
│   └── Notes.java
├── Dto/                             # Data Transfer Objects
│   ├── ApiResponse.java             # Standard response wrapper
│   ├── StudentRequest.java
│   ├── CourseRequest.java
│   └── UserLoginRequest.java
├── Exception/                       # Custom Exceptions
│   ├── GlobalExceptionHandler.java
│   ├── StudentNotFoundException.java
│   ├── ResourceNotFoundException.java
│   └── ResourceNotFoundException1.java
├── JwtToken/                        # JWT Utilities
│   ├── JwtUtil.java
│   └── JwtFilter.java
├── SecurityConfiguration/           # Security Config
│   └── SecurityConfig.java
└── Utility/                         # Utility Classes
    └── PasswordGenerator.java
```

## API Endpoints Overview

### Authentication
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/register` - User registration

### Courses
- `GET /api/v1/courses/getAllCourse` - Get all active courses
- `GET /api/v1/courses/getCourse/{id}` - Get course by ID
- `POST /api/v1/courses/addCourse` - Create new course
- `PUT /api/v1/courses/updateCourse/{id}` - Update course
- `DELETE /api/v1/courses/deleteCourse/{id}` - Delete course (soft delete)

### Students
- `GET /api/v1/students/getAllStudents` - Get all active students
- `GET /api/v1/students/getStudentById/{id}` - Get student by ID
- `POST /api/v1/students/registerStudent` - Register new student
- `PUT /api/v1/students/updateStudent/{id}` - Update student
- `DELETE /api/v1/students/deleteStudent/{id}` - Delete student (soft delete)

**See `API_DOCUMENTATION.md` for detailed endpoint documentation**

## Standard Response Format

All API responses follow this structure:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": {},
  "errors": null,
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

## Error Handling

Errors are returned in a consistent format:

```json
{
  "success": false,
  "message": "Error description",
  "data": null,
  "errors": {
    "error": "Detailed error message",
    "fieldErrors": {
      "field1": "validation error"
    }
  },
  "statusCode": 400,
  "timestamp": "2026-04-24T10:30:00"
}
```

## Logging

Logs are written to:
- **Console:** Real-time output with color formatting
- **File:** `logs/application.log` (rotated daily, max 30 days)

**Log Configuration:**
```properties
logging.level.com.student.management=INFO
logging.level.org.springframework.web=INFO
logging.level.org.springframework.security=INFO
```

## Database Schema

### Students Table
- id (Primary Key)
- student_code (Unique)
- first_name, last_name
- email (Unique), phone (Unique)
- age, gender
- course_id (Foreign Key)
- address_line1, address_line2, city, state, postal_code, country
- is_active (Boolean)
- created_at, created_by, modified_at, modified_by
- password

### Course Table
- id (Primary Key)
- course_code (Unique)
- course_name, description
- duration, fees
- max_students
- is_active (Boolean)
- created_at, created_by, modified_at, modified_by

### UserTable
- id (Primary Key)
- first_name, last_name, email (Unique)
- password
- active (Boolean)
- created_at, created_by, modified_at, modified_by
- student_id (Foreign Key, optional)

## Testing with Postman

1. **Import Collection:** Use the provided Postman collection
2. **Set Environment:** Configure base URL and tokens
3. **Test Endpoints:** Execute each endpoint with sample data

### Sample Course Data
```json
{
  "courseCode": "JAVA101",
  "courseName": "Java Programming Basics",
  "description": "Learn Java programming from scratch",
  "duration": "8 weeks",
  "fees": 4500.00,
  "maxStudents": 30
}
```

### Sample Student Data
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "9876543210",
  "age": 22,
  "gender": "Male",
  "courseId": 1,
  "addressLine1": "123 Main St",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "USA"
}
```

## Performance Optimization

- ✅ Index on frequently queried columns (course_id, is_active)
- ✅ Lazy loading for relationships
- ✅ Query optimization with custom repository methods
- ✅ Connection pooling configured
- ✅ Logging set to WARN for reduced I/O

## Security Considerations

1. **CORS Policy:** Currently allows all origins (*)
   - For production, specify allowed origins
   
2. **JWT Token:**
   - Tokens set to expire after configured duration
   - Stored in Authorization header
   
3. **Password Security:**
   - Passwords BCrypt-encrypted
   - Auto-generated passwords sent via email only
   
4. **HTTPS:**
   - Use HTTPS in production
   - Set secure=true in cookies

5. **SQL Injection:**
   - Protected via JPA parameterized queries

## Development Guidelines

### Adding a New Endpoint

1. **Create DTO** in `Dto/` package
2. **Create Repository Method** in `Repository/` if needed
3. **Create Service Method** in `Service/` package
4. **Create Controller Endpoint** in `Controller/` package
5. **Add Logging** using SLF4J Logger
6. **Document** in API_DOCUMENTATION.md
7. **Handle Exceptions** in GlobalExceptionHandler

### Example:
```java
@GetMapping("/example")
public ResponseEntity<ApiResponse<String>> example() {
    logger.info("Fetching example");
    try {
        String result = "example";
        ApiResponse<String> response = new ApiResponse<>(true, "Success", result, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
        logger.error("Error: {}", e.getMessage(), e);
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## Troubleshooting

### Issue: Database Connection Failed
**Solution:** Ensure MySQL is running and credentials are correct in application.properties

### Issue: Port 8080 Already in Use
**Solution:** Change port in application.properties:
```properties
server.port=8081
```

### Issue: 404 - Endpoint Not Found
**Solution:** Ensure endpoint URL matches the controller mapping:
- Check `@RequestMapping` path
- Check `@PostMapping`, `@GetMapping` path
- Verify the base API path `/api/v1/`

### Issue: JWT Token Expired
**Solution:** Generate a new token via `/api/v1/auth/login`

### Issue: CORS Error
**Solution:** Already configured for all origins. For production, update SecurityConfig.java

## Future Enhancements

- [ ] Pagination and Sorting
- [ ] Advanced Search Filters
- [ ] Role-based Access Control (RBAC)
- [ ] Refresh Token Implementation
- [ ] API Rate Limiting
- [ ] Caching with Redis
- [ ] API Versioning Strategy
- [ ] Swagger/OpenAPI Documentation
- [ ] Unit and Integration Tests
- [ ] Docker Containerization

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/NewFeature`)
3. Commit changes (`git commit -m 'Add NewFeature'`)
4. Push to branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

## Support

For issues and questions:
1. Check the API_DOCUMENTATION.md
2. Review logs in `logs/application.log`
3. Check console output for error messages
4. Verify database connectivity

## License

This project is licensed under the MIT License - see LICENSE file for details

## Version History

**v1.0.0** (April 24, 2026)
- Initial production-ready release
- RESTful API with proper structure
- JWT Authentication
- Global Exception Handling
- Comprehensive Logging
- Standard API Response Format

---

**Last Updated:** April 24, 2026
**Status:** Production Ready ✅

