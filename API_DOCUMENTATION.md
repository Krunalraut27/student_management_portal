# Student Management API - Complete Documentation

## Base URL
```
http://localhost:8080/api/v1
```

---

## Authentication Endpoints

### 1. User Login
**Endpoint:** `POST /auth/login`

**Description:** Authenticate user and receive JWT token

**Request Body:**
```json
{
  "username": "user@example.com",
  "password": "password123"
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Login successful",
  "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

**Error Response (401 Unauthorized):**
```json
{
  "success": false,
  "message": "Invalid username or password",
  "errors": null,
  "statusCode": 401,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

### 2. User Registration
**Endpoint:** `POST /auth/register`

**Description:** Register a new user account

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "active": true
}
```

**Success Response (201 Created):**
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "active": true,
    "createdAt": "2026-04-24T10:30:00"
  },
  "statusCode": 201,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

## Course Endpoints

### 1. Create Course
**Endpoint:** `POST /courses/addCourse`

**Description:** Create a new course (PERMIT ALL)

**Request Body:**
```json
{
  "courseCode": "CS101",
  "courseName": "Introduction to Computer Science",
  "description": "Comprehensive introduction to computer science fundamentals and programming basics",
  "duration": "12 weeks",
  "fees": 5000.00,
  "maxStudents": 50
}
```

**Success Response (201 Created):**
```json
{
  "success": true,
  "message": "Course created successfully",
  "data": {
    "id": 1,
    "courseCode": "CS101",
    "courseName": "Introduction to Computer Science",
    "description": "Comprehensive introduction to computer science fundamentals and programming basics",
    "duration": "12 weeks",
    "fees": 5000.00,
    "maxStudents": 50,
    "isActive": true,
    "createdAt": "2026-04-24T10:30:00",
    "createdBy": 1,
    "modifiedAt": "2026-04-24T10:30:00",
    "modifiedBy": 1
  },
  "statusCode": 201,
  "timestamp": "2026-04-24T10:30:00"
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "fieldErrors": {
      "courseCode": "Course code is required",
      "courseName": "Course name is required",
      "fees": "Fees must be greater than 0"
    }
  },
  "statusCode": 400,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

### 2. Get All Courses
**Endpoint:** `GET /courses/getAllCourse`

**Description:** Retrieve all active courses (PERMIT ALL)

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Courses retrieved successfully",
  "data": [
    {
      "id": 1,
      "courseCode": "CS101",
      "courseName": "Introduction to Computer Science",
      "description": "Comprehensive introduction to computer science fundamentals and programming basics",
      "duration": "12 weeks",
      "fees": 5000.00,
      "maxStudents": 50,
      "isActive": true,
      "createdAt": "2026-04-24T10:30:00",
      "createdBy": 1,
      "modifiedAt": "2026-04-24T10:30:00",
      "modifiedBy": 1
    },
    {
      "id": 2,
      "courseCode": "JAVA101",
      "courseName": "Java Programming",
      "description": "Master the Java programming language",
      "duration": "8 weeks",
      "fees": 4000.00,
      "maxStudents": 40,
      "isActive": true,
      "createdAt": "2026-04-24T10:35:00",
      "createdBy": 1,
      "modifiedAt": "2026-04-24T10:35:00",
      "modifiedBy": 1
    }
  ],
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

### 3. Get Course by ID
**Endpoint:** `GET /courses/getCourse/{id}`

**Description:** Retrieve a specific course by ID (PERMIT ALL)

**Path Parameters:**
- `id` (Long): The course ID

**Example:** `GET /courses/getCourse/1`

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Course retrieved successfully",
  "data": {
    "id": 1,
    "courseCode": "CS101",
    "courseName": "Introduction to Computer Science",
    "description": "Comprehensive introduction to computer science fundamentals and programming basics",
    "duration": "12 weeks",
    "fees": 5000.00,
    "maxStudents": 50,
    "isActive": true,
    "createdAt": "2026-04-24T10:30:00",
    "createdBy": 1,
    "modifiedAt": "2026-04-24T10:30:00",
    "modifiedBy": 1
  },
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

**Error Response (404 Not Found):**
```json
{
  "success": false,
  "message": "Resource not found",
  "errors": {
    "error": "Course not found with ID: 999",
    "timestamp": "2026-04-24T10:30:00",
    "path": "/api/v1/courses/getCourse/999"
  },
  "statusCode": 404,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

### 4. Update Course
**Endpoint:** `PUT /courses/updateCourse/{id}`

**Description:** Update an existing course

**Path Parameters:**
- `id` (Long): The course ID

**Request Body:**
```json
{
  "courseCode": "CS101",
  "courseName": "Advanced Computer Science",
  "description": "Advanced topics in computer science",
  "duration": "16 weeks",
  "fees": 6000.00,
  "maxStudents": 60
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Course updated successfully",
  "data": {
    "id": 1,
    "courseCode": "CS101",
    "courseName": "Advanced Computer Science",
    "description": "Advanced topics in computer science",
    "duration": "16 weeks",
    "fees": 6000.00,
    "maxStudents": 60,
    "isActive": true,
    "createdAt": "2026-04-24T10:30:00",
    "createdBy": 1,
    "modifiedAt": "2026-04-24T10:45:00",
    "modifiedBy": 1
  },
  "statusCode": 200,
  "timestamp": "2026-04-24T10:45:00"
}
```

---

### 5. Delete Course (Soft Delete)
**Endpoint:** `DELETE /courses/deleteCourse/{id}`

**Description:** Soft delete a course (marks as inactive)

**Path Parameters:**
- `id` (Long): The course ID

**Example:** `DELETE /courses/deleteCourse/1`

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Course deleted successfully (Soft Delete)",
  "data": "Course ID: 1 has been marked as inactive",
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

## Student Endpoints

### 1. Register Student
**Endpoint:** `POST /students/registerStudent`

**Description:** Register a new student (PERMIT ALL)

**Request Body:**
```json
{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "phone": "9876543210",
  "age": 22,
  "gender": "Female",
  "courseId": 1,
  "addressLine1": "123 Main Street",
  "addressLine2": "Apartment 4B",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "USA"
}
```

**Success Response (201 Created):**
```json
{
  "success": true,
  "message": "Student registered successfully",
  "data": {
    "id": 1,
    "studentCode": "STUD0001",
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane@example.com",
    "phone": "9876543210",
    "age": 22,
    "gender": "Female",
    "course": {
      "id": 1,
      "courseCode": "CS101",
      "courseName": "Introduction to Computer Science"
    },
    "addressLine1": "123 Main Street",
    "addressLine2": "Apartment 4B",
    "city": "New York",
    "state": "NY",
    "postalCode": "10001",
    "country": "USA",
    "isActive": true,
    "createdAt": "2026-04-24T10:30:00",
    "createdBy": 1,
    "modifiedAt": "2026-04-24T10:30:00",
    "modifiedBy": 1
  },
  "statusCode": 201,
  "timestamp": "2026-04-24T10:30:00"
}
```

**Note:** A password will be auto-generated and sent to the student's email.

---

### 2. Get All Students
**Endpoint:** `GET /students/getAllStudents`

**Description:** Retrieve all active students (PERMIT ALL)

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Students retrieved successfully",
  "data": [
    {
      "id": 1,
      "studentCode": "STUD0001",
      "firstName": "Jane",
      "lastName": "Smith",
      "email": "jane@example.com",
      "phone": "9876543210",
      "age": 22,
      "gender": "Female",
      "course": {
        "id": 1,
        "courseCode": "CS101"
      },
      "isActive": true,
      "createdAt": "2026-04-24T10:30:00"
    }
  ],
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

### 3. Get Student by ID
**Endpoint:** `GET /students/getStudentById/{id}`

**Description:** Retrieve a specific student by ID (PERMIT ALL)

**Path Parameters:**
- `id` (Long): The student ID

**Example:** `GET /students/getStudentById/1`

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Student retrieved successfully",
  "data": {
    "id": 1,
    "studentCode": "STUD0001",
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane@example.com",
    "phone": "9876543210",
    "age": 22,
    "gender": "Female",
    "course": {
      "id": 1,
      "courseCode": "CS101",
      "courseName": "Introduction to Computer Science"
    },
    "addressLine1": "123 Main Street",
    "addressLine2": "Apartment 4B",
    "city": "New York",
    "state": "NY",
    "postalCode": "10001",
    "country": "USA",
    "isActive": true,
    "createdAt": "2026-04-24T10:30:00"
  },
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

### 4. Update Student
**Endpoint:** `PUT /students/updateStudent/{id}`

**Description:** Update an existing student

**Path Parameters:**
- `id` (Long): The student ID

**Request Body:**
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phone": "9876543210",
  "age": 23,
  "gender": "Female",
  "addressLine1": "456 Oak Avenue",
  "addressLine2": "Suite 100",
  "city": "Boston",
  "state": "MA",
  "postalCode": "02101",
  "country": "USA"
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Student updated successfully",
  "data": {
    "id": 1,
    "studentCode": "STUD0001",
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "phone": "9876543210",
    "age": 23,
    "gender": "Female",
    "isActive": true,
    "modifiedAt": "2026-04-24T10:45:00"
  },
  "statusCode": 200,
  "timestamp": "2026-04-24T10:45:00"
}
```

---

### 5. Delete Student (Soft Delete)
**Endpoint:** `DELETE /students/deleteStudent/{id}`

**Description:** Soft delete a student (marks as inactive)

**Path Parameters:**
- `id` (Long): The student ID

**Example:** `DELETE /students/deleteStudent/1`

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Student deleted successfully (Soft Delete)",
  "data": "Student ID: 1 has been marked as inactive",
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

## Common Response Structure

All API responses follow this structure:

```json
{
  "success": boolean,          // true or false
  "message": "string",         // Human-readable message
  "data": {},                  // Response data (null on error)
  "errors": {},                // Error details (null on success)
  "statusCode": number,        // HTTP status code
  "timestamp": "string"        // ISO 8601 timestamp
}
```

---

## HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created successfully |
| 400 | Bad Request - Invalid input or validation failed |
| 401 | Unauthorized - Authentication failed |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error - Server error |

---

## Error Handling

### Validation Error Example
```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "fieldErrors": {
      "email": "Please provide a valid email address",
      "phone": "Phone number must be exactly 10 digits"
    }
  },
  "statusCode": 400,
  "timestamp": "2026-04-24T10:30:00"
}
```

### Resource Not Found Example
```json
{
  "success": false,
  "message": "Resource not found",
  "errors": {
    "error": "Course not found with ID: 999",
    "path": "/api/v1/courses/getCourse/999",
    "timestamp": "2026-04-24T10:30:00"
  },
  "statusCode": 404,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

## Security

- **CORS Enabled:** All origins allowed (*)
- **All endpoints are PERMIT ALL:** No JWT required (for development)
- **Passwords:** Auto-generated for students and BCrypt-encrypted for users
- **Soft Delete:** All deletes are soft deletes (records marked as inactive)

---

## Notes for React Frontend

1. **Base URL:** Store as environment variable `REACT_APP_API_URL=http://localhost:8080/api/v1`
2. **Error Handling:** Always check `response.success` before accessing `response.data`
3. **Course Creation:** Send only non-null fields
4. **Student Registration:** Course ID is required and must exist
5. **Timestamps:** All timestamps are in ISO 8601 format
6. **Pagination:** Not implemented yet - all results returned

---

## Example React Fetch Calls

### Get All Courses
```javascript
const fetchCourses = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/v1/courses/getAllCourse');
    const data = await response.json();
    if (data.success) {
      console.log('Courses:', data.data);
    } else {
      console.error('Error:', data.message);
    }
  } catch (error) {
    console.error('Fetch error:', error);
  }
};
```

### Create Course
```javascript
const createCourse = async (courseData) => {
  try {
    const response = await fetch('http://localhost:8080/api/v1/courses/addCourse', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(courseData)
    });
    const data = await response.json();
    if (data.success) {
      console.log('Course created:', data.data);
    } else {
      console.error('Errors:', data.errors);
    }
  } catch (error) {
    console.error('Fetch error:', error);
  }
};
```

### Register Student
```javascript
const registerStudent = async (studentData) => {
  try {
    const response = await fetch('http://localhost:8080/api/v1/students/registerStudent', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(studentData)
    });
    const data = await response.json();
    if (data.success) {
      console.log('Student registered:', data.data);
    } else {
      console.error('Errors:', data.errors);
    }
  } catch (error) {
    console.error('Fetch error:', error);
  }
};
```

---

Generated: April 24, 2026
API Version: 1.0.0

