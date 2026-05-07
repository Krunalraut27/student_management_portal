# JWT Token with Role Implementation Guide

## Summary of Changes

Your JWT token now includes the **user's role**. Here's what was updated:

### 1. **JwtUtil.java** - Updated to include role in token
- Added method: `generateToken(String username, String role)` - Creates JWT with role claim
- Added method: `extractRole(String token)` - Extracts role from JWT token
- Maintains backward compatibility with `generateToken(String username)` - defaults to "USER"

### 2. **AuthController.java** - Passes role when generating token
- Modified `/login` endpoint to fetch user's role from database
- Now generates JWT with user's role included

### 3. **CustomUserDetailsService.java** - Uses actual role from UserTable
- Updated to use user's role from database instead of hardcoding "USER"
- Default role is "USER" if not set in database

### 4. **UserService.java** - Added new method
- Added `getUserByEmail(String email)` method to fetch user details

### 5. **JwtFilter.java** - Extracts role from token
- Updated to extract role from token during request processing

---

## JWT Token Structure

Your JWT token now contains:

```json
{
  "sub": "user@example.com",
  "role": "ADMIN",  // ← NEW: User's role is now included
  "iat": 1672531200,
  "exp": 1672534800
}
```

---

## How to Set Roles in Database

You need to set the `role` field in the `users` table for each user:

### SQL Example:
```sql
UPDATE users SET role = 'ADMIN' WHERE email = 'admin@example.com';
UPDATE users SET role = 'STUDENT' WHERE email = 'student@example.com';
UPDATE users SET role = 'TEACHER' WHERE email = 'teacher@example.com';
```

---

## Frontend Usage

### 1. **Login and Get Token**
```javascript
// Send login request
const response = await fetch('http://localhost:8080/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    username: 'user@example.com',
    password: 'password123'
  })
});

const token = await response.text();
// Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNjcyNTMxMjAwLCJleHAiOjE2NzI1MzQ4MDB9.xyz
```

### 2. **Decode JWT to Get Role (No Library Needed)**
```javascript
function decodeJWT(token) {
  const parts = token.split('.');
  const payload = JSON.parse(atob(parts[1]));
  return payload;
}

const decoded = decodeJWT(token);
console.log('User:', decoded.sub);      // user@example.com
console.log('Role:', decoded.role);     // ADMIN
console.log('Expires:', new Date(decoded.exp * 1000)); // Expiry date
```

### 3. **Use Token in API Requests**
```javascript
const headers = {
  'Authorization': `Bearer ${token}`,
  'Content-Type': 'application/json'
};

// Example API call
const response = await fetch('http://localhost:8080/getStudentById/1', {
  method: 'GET',
  headers: headers
});
```

### 4. **Use Role for UI Control (React Example)**
```javascript
import { useEffect, useState } from 'react';

function AdminDashboard() {
  const [userRole, setUserRole] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
      const decoded = decodeJWT(token);
      setUserRole(decoded.role);
    }
  }, []);

  return (
    <div>
      {userRole === 'ADMIN' && (
        <button>Delete User</button>
      )}
      {userRole === 'STUDENT' && (
        <p>Welcome Student</p>
      )}
    </div>
  );
}
```

---

## Available Roles

You can use any role in the `role` field:
- `ADMIN` - Administrator access
- `STUDENT` - Student access
- `TEACHER` - Teacher access
- `USER` - General user access
- Any other custom role as needed

---

## Testing with Postman

### 1. **Login Request**
```
POST http://localhost:8080/login
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password123"
}
```

### 2. **Response** (Copy this token)
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNjcyNTMxMjAwLCJleHAiOjE2NzI1MzQ4MDB9.xyz
```

### 3. **Use Token in Protected API**
```
GET http://localhost:8080/getAllCourse
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## Important Notes

✅ Tokens expire after **1 hour**  
✅ Roles are fetched from the `users` table → `role` column  
✅ Default role is "USER" if not set  
✅ Token includes both username and role for frontend identification  
✅ Role can be used for route protection and UI control

---

## Next Steps to Complete Backend for React

1. **Set up CORS properly** for React frontend
2. **Add @PreAuthorize annotations** on controller methods to enforce role-based access
3. **Create error response DTO** for consistent error messages
4. **Add logging** for debugging token issues
5. **Implement refresh token** for better security

