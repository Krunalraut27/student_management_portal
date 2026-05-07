# Quick Start Guide - API Endpoints

## 🚀 Base URL
```
http://localhost:8080/api/v1
```

---

## 📚 Course Management APIs

### ✅ Get All Courses
```
GET /api/v1/courses/getAllCourse
```

### ✅ Get Course by ID
```
GET /api/v1/courses/getCourse/1
```

### ✅ Create Course
```
POST /api/v1/courses/addCourse
```

**Payload:**
```json
{
  "courseCode": "JAVA101",
  "courseName": "Java Programming",
  "description": "Learn Java from basics to advanced",
  "duration": "12 weeks",
  "fees": 5000.00,
  "maxStudents": 50
}
```

### ✅ Update Course
```
PUT /api/v1/courses/updateCourse/1
```

**Payload:** Same as Create Course

### ✅ Delete Course
```
DELETE /api/v1/courses/deleteCourse/1
```

---

## 👥 Student Management APIs

### ✅ Get All Students
```
GET /api/v1/students/getAllStudents
```

### ✅ Get Student by ID
```
GET /api/v1/students/getStudentById/1
```

### ✅ Register Student
```
POST /api/v1/students/registerStudent
```

**Payload:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "9876543210",
  "age": 22,
  "gender": "Male",
  "courseId": 1,
  "addressLine1": "123 Main Street",
  "addressLine2": "Apt 4B",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "USA"
}
```

### ✅ Update Student
```
PUT /api/v1/students/updateStudent/1
```

**Payload:** Same as Register Student (courseId optional)

### ✅ Delete Student
```
DELETE /api/v1/students/deleteStudent/1
```

---

## 🔐 Authentication APIs

### ✅ User Login
```
POST /api/v1/auth/login
```

**Payload:**
```json
{
  "username": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

### ✅ User Registration
```
POST /api/v1/auth/register
```

**Payload:**
```json
{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "password": "securePass123"
}
```

---

## 📊 Success Response Format

```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    "id": 1,
    "name": "Example"
  },
  "errors": null,
  "statusCode": 200,
  "timestamp": "2026-04-24T10:30:00"
}
```

---

## ❌ Error Response Format

```json
{
  "success": false,
  "message": "Validation failed",
  "data": null,
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

---

## 🧪 Testing with cURL

### Get All Courses
```bash
curl -X GET "http://localhost:8080/api/v1/courses/getAllCourse"
```

### Create Course
```bash
curl -X POST "http://localhost:8080/api/v1/courses/addCourse" \
  -H "Content-Type: application/json" \
  -d '{
    "courseCode": "JAVA101",
    "courseName": "Java Programming",
    "description": "Learn Java",
    "duration": "12 weeks",
    "fees": 5000.00,
    "maxStudents": 50
  }'
```

### Register Student
```bash
curl -X POST "http://localhost:8080/api/v1/students/registerStudent" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "9876543210",
    "age": 22,
    "gender": "Male",
    "courseId": 1,
    "addressLine1": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10001",
    "country": "USA"
  }'
```

---

## ⚙️ HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | ✅ OK - Request successful |
| 201 | ✅ Created - Resource created |
| 400 | ❌ Bad Request - Invalid input |
| 401 | ❌ Unauthorized - Auth failed |
| 404 | ❌ Not Found - Resource missing |
| 500 | ❌ Server Error |

---

## 🔍 Common Issues

### Issue: 404 Error on old endpoints
**Old:** `GET /getAllCourse` ❌
**New:** `GET /api/v1/courses/getAllCourse` ✅

### Issue: Validation errors
Check that all required fields are provided with correct data types and formats.

### Issue: Course not found
Ensure the course is active (not deleted) and the ID exists.

### Issue: Student registration failed
Ensure the courseId exists and the email is unique.

---

## 📝 Notes for Frontend Integration

1. **Base URL:** Use environment variable `REACT_APP_API_URL=http://localhost:8080/api/v1`
2. **Always use new API paths** with `/api/v1/` prefix
3. **Check `response.success`** before accessing data
4. **Handle errors** in the `errors` object
5. **Timestamps** are in ISO 8601 format
6. **CORS is enabled** - no special headers needed
7. **All endpoints are PERMIT ALL** - no JWT required

---

**Version:** 1.0.0  
**Last Updated:** April 24, 2026

