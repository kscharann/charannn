# 🔐 Login & Register - Postman Collection Guide

## 📋 Overview

Simple and focused **Login & Register Postman Collection** for the Stock Inventory Management System. This collection provides essential authentication functionality with user registration and login capabilities.

## 🚀 Quick Setup

### 1. Import Collection & Environment

1. **Import Collection:**
   - Open Postman
   - Click "Import" → "Upload Files"
   - Select `Login_Register_Postman_Collection.json`

2. **Import Environment:**
   - Click "Import" → "Upload Files" 
   - Select `Login_Register_Environment.postman_environment.json`
   - Select the environment from the dropdown (top-right)

### 2. Configure Base URL

The environment is pre-configured for:
- **Local Development:** `http://localhost:8081`
- **Production:** Update `baseUrl` variable as needed

## 📚 Collection Structure

### 📝 **Registration Endpoints**

| Endpoint | Description | Role Created |
|----------|-------------|--------------|
| `📝 Register User` | Register regular user | USER |
| `📝 Register Admin` | Register admin user | ADMIN |
| `📝 Register Moderator` | Register moderator | MODERATOR |

### 🔑 **Login Endpoints**

| Endpoint | Description | Token Saved To |
|----------|-------------|----------------|
| `🔑 Login User` | Login regular user | `{{jwt_token}}` |
| `🔑 Login Admin` | Login admin user | `{{admin_jwt_token}}` |
| `🔑 Login Moderator` | Login moderator | `{{mod_jwt_token}}` |

## 🔄 Usage Workflow

### Step 1: Register Users
1. **Run `📝 Register User`** - Creates: `john_doe` with USER role
2. **Run `📝 Register Admin`** - Creates: `admin_user` with ADMIN role  
3. **Run `📝 Register Moderator`** - Creates: `moderator_user` with MODERATOR role

### Step 2: Login & Get Tokens
1. **Run `🔑 Login User`** - Saves token to `{{jwt_token}}`
2. **Run `🔑 Login Admin`** - Saves token to `{{admin_jwt_token}}`
3. **Run `🔑 Login Moderator`** - Saves token to `{{mod_jwt_token}}`

### Step 3: Use Tokens
- Use `{{jwt_token}}` for general API access
- Use `{{admin_jwt_token}}` for admin operations
- Use `{{mod_jwt_token}}` for moderator operations

## 📝 Request Examples

### Registration Request:
```json
{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123",
    "role": ["user"]
}
```

### Login Request:
```json
{
    "username": "john_doe",
    "password": "password123"
}
```

### Login Response:
```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "type": "Bearer",
    "id": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "roles": ["ROLE_USER"]
}
```

## 🔧 Environment Variables

| Variable | Description | Auto-Set | Usage |
|----------|-------------|----------|-------|
| `baseUrl` | API base URL | Manual | All requests |
| `jwt_token` | User JWT token | ✅ Auto | General API access |
| `admin_jwt_token` | Admin JWT token | ✅ Auto | Admin operations |
| `mod_jwt_token` | Moderator JWT token | ✅ Auto | Moderator operations |
| `user_id` | User ID | ✅ Auto | Reference |
| `username` | Username | ✅ Auto | Reference |
| `user_email` | User email | ✅ Auto | Reference |

## 👥 Default User Accounts

After running the registration requests, you'll have:

### 👤 Regular User
- **Username:** `john_doe`
- **Email:** `john@example.com`
- **Password:** `password123`
- **Role:** USER

### 👑 Admin User
- **Username:** `admin_user`
- **Email:** `admin@company.com`
- **Password:** `admin123`
- **Role:** ADMIN

### 👨‍💼 Moderator User
- **Username:** `moderator_user`
- **Email:** `moderator@company.com`
- **Password:** `mod123`
- **Role:** MODERATOR

## 🛡️ Using JWT Tokens

After login, tokens are automatically saved. Use them in other API calls:

**Authorization Header:**
```
Authorization: Bearer {{jwt_token}}
```

**For Admin Operations:**
```
Authorization: Bearer {{admin_jwt_token}}
```

**For Moderator Operations:**
```
Authorization: Bearer {{mod_jwt_token}}
```

## ⚠️ Important Notes

1. **Server Must Be Running:** Ensure your Spring Boot app is running on port 8081
2. **Environment Selection:** Make sure the correct environment is selected
3. **Token Expiration:** JWT tokens expire based on server configuration
4. **Role Validation:** Different API endpoints require different roles

## 🔍 Troubleshooting

### Common Issues:

1. **400 Bad Request - Username/Email Exists**
   - User already registered with that username or email
   - Try different username/email or login instead

2. **401 Unauthorized - Login Failed**
   - Check username and password are correct
   - Ensure user was registered successfully

3. **Connection Error**
   - Verify Spring Boot app is running
   - Check `baseUrl` is correct (`http://localhost:8081`)

4. **Token Not Saved**
   - Check if login was successful (200 status)
   - Verify environment is selected
   - Look at test results tab

## 🎯 Next Steps

After successful login:
1. **Import Product API collection** to manage products
2. **Import Purchase API collection** to manage purchases  
3. **Import Invoice API collection** to manage invoices
4. **Use the JWT tokens** for authenticated API calls

## 📋 Test Results

Each request includes automatic tests that verify:
- ✅ Correct HTTP status codes
- ✅ Response structure validation
- ✅ JWT token presence and format
- ✅ User role verification
- ✅ Automatic token saving

---

**🔗 Related Files:**
- `Login_Register_Postman_Collection.json` - Main collection
- `Login_Register_Environment.postman_environment.json` - Environment variables

**🔗 Next Collections:**
- `Product_API_Postman_Collection.json` - Product management
- `Authentication_API_Postman_Collection.json` - Extended auth features
