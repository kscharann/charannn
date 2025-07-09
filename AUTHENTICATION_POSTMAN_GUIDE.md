# 🔐 Authentication API - Postman Collection Guide

## 📋 Overview

This guide explains how to use the **Authentication API Postman Collection** for the Stock Inventory Management System. The collection includes complete JWT-based authentication with user registration, login, and token management.

## 🚀 Quick Setup

### 1. Import Collection & Environment

1. **Import Collection:**
   - Open Postman
   - Click "Import" → "Upload Files"
   - Select `Authentication_API_Postman_Collection.json`

2. **Import Environment:**
   - Click "Import" → "Upload Files" 
   - Select `Authentication_Environment.postman_environment.json`
   - Select the environment from the dropdown (top-right)

### 2. Configure Base URL

The environment is pre-configured for:
- **Local Development:** `http://localhost:8081`
- **Production:** Update `baseUrl` variable as needed

## 📚 Collection Structure

### 👤 **User Registration Endpoints**

| Endpoint | Description | Role |
|----------|-------------|------|
| `👤 User Registration` | Register regular user | `user` |
| `👤 Admin Registration` | Register admin user | `admin` |
| `👤 Moderator Registration` | Register moderator | `mod` |

### 🔑 **Login Endpoints**

| Endpoint | Description | Token Variable |
|----------|-------------|----------------|
| `🔑 User Login` | Login regular user | `jwt_token` |
| `🔑 Admin Login` | Login admin user | `admin_jwt_token` |
| `🔑 Moderator Login` | Login moderator | `mod_jwt_token` |

### 🧪 **Test Endpoints**

| Endpoint | Description | Required Auth |
|----------|-------------|---------------|
| `🧪 Test JWT Token` | Validate token | Any valid JWT |

## 🔄 Usage Workflow

### Step 1: Register Users
1. Run `👤 User Registration` to create a regular user
2. Run `👤 Admin Registration` to create an admin
3. Run `👤 Moderator Registration` to create a moderator

### Step 2: Login & Get Tokens
1. Run `🔑 User Login` - saves token to `{{jwt_token}}`
2. Run `🔑 Admin Login` - saves token to `{{admin_jwt_token}}`
3. Run `🔑 Moderator Login` - saves token to `{{mod_jwt_token}}`

### Step 3: Test Authentication
1. Run `🧪 Test JWT Token` to verify token validity

## 🔧 Environment Variables

| Variable | Description | Auto-Set |
|----------|-------------|----------|
| `baseUrl` | API base URL | Manual |
| `jwt_token` | User JWT token | ✅ Auto |
| `admin_jwt_token` | Admin JWT token | ✅ Auto |
| `mod_jwt_token` | Moderator JWT token | ✅ Auto |
| `user_id` | Current user ID | ✅ Auto |
| `username` | Current username | ✅ Auto |

## 📝 Request Examples

### Registration Request Body:
```json
{
    "username": "testuser",
    "email": "test@example.com", 
    "password": "password123",
    "role": ["user"]
}
```

### Login Request Body:
```json
{
    "username": "testuser",
    "password": "password123"
}
```

### Login Response:
```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "type": "Bearer",
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "roles": ["ROLE_USER"]
}
```

## 🛡️ Using JWT Tokens

After login, tokens are automatically saved to environment variables. Use them in other API calls:

**Header:**
```
Authorization: Bearer {{jwt_token}}
```

**For Admin Operations:**
```
Authorization: Bearer {{admin_jwt_token}}
```

## ⚠️ Important Notes

1. **Token Expiration:** JWT tokens expire based on server configuration
2. **Role-Based Access:** Different endpoints require different roles
3. **Environment Selection:** Ensure correct environment is selected
4. **Server Status:** Make sure your Spring Boot app is running on port 8081

## 🔍 Troubleshooting

### Common Issues:

1. **401 Unauthorized**
   - Check if token is valid and not expired
   - Ensure correct Authorization header format

2. **400 Bad Request**
   - Verify request body format
   - Check required fields are present

3. **Connection Error**
   - Confirm server is running on correct port
   - Check `baseUrl` environment variable

## 🎯 Next Steps

After authentication setup:
1. Import other API collections (Products, Purchases, Invoices)
2. Use the JWT tokens for protected endpoints
3. Test role-based access control

---

**🔗 Related Files:**
- `Authentication_API_Postman_Collection.json` - Main collection
- `Authentication_Environment.postman_environment.json` - Environment variables
