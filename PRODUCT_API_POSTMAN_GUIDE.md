# 📦 Product API - Postman Collection Guide

## 📋 Overview

This guide explains how to use the **Product API Postman Collection** for the Stock Inventory Management System. The collection includes complete CRUD operations for product management with role-based access control.

## 🚀 Quick Setup

### 1. Import Collection & Environment

1. **Import Collection:**
   - Open Postman
   - Click "Import" → "Upload Files"
   - Select `Product_API_Postman_Collection.json`

2. **Import Environment:**
   - Click "Import" → "Upload Files" 
   - Select `Product_Environment.postman_environment.json`
   - Select the environment from the dropdown (top-right)

### 2. Prerequisites

**⚠️ Important:** You need JWT tokens before using Product APIs!

1. **First import and use the Authentication collection**
2. **Login to get JWT tokens:**
   - User token: `{{jwt_token}}`
   - Moderator token: `{{mod_jwt_token}}`
   - Admin token: `{{admin_jwt_token}}`

## 📚 Collection Structure

### 📋 **Read Operations** (USER+ Access)

| Endpoint | Description | Required Role |
|----------|-------------|---------------|
| `📋 Get All Products` | Retrieve all products | USER+ |
| `🔍 Get Product by ID` | Get specific product | USER+ |

### ✏️ **Write Operations** (MODERATOR+ Access)

| Endpoint | Description | Required Role |
|----------|-------------|---------------|
| `➕ Create New Product` | Create new product | MODERATOR+ |
| `✏️ Update Product` | Update existing product | MODERATOR+ |

### 🗑️ **Delete Operations** (ADMIN Access)

| Endpoint | Description | Required Role |
|----------|-------------|---------------|
| `🗑️ Delete Product` | Delete product | ADMIN |

### ❌ **Error Testing**

| Endpoint | Description | Expected Result |
|----------|-------------|-----------------|
| `❌ Create Product - Duplicate Code Error` | Test duplicate code | 400 Bad Request |
| `❌ Get Product - Not Found` | Test invalid ID | 404 Not Found |

## 🔄 Usage Workflow

### Step 1: Authentication Setup
1. Import and run Authentication collection first
2. Login as different users to get tokens:
   - `🔑 User Login` → saves to `{{jwt_token}}`
   - `🔑 Moderator Login` → saves to `{{mod_jwt_token}}`
   - `🔑 Admin Login` → saves to `{{admin_jwt_token}}`

### Step 2: Read Products (Any User)
1. Run `📋 Get All Products` - lists all products
2. Run `🔍 Get Product by ID` - gets specific product

### Step 3: Create Products (Moderator+)
1. Run `➕ Create New Product` - creates laptop example
2. Check the response and note the created product ID

### Step 4: Update Products (Moderator+)
1. Run `✏️ Update Product` - updates the created product
2. Verify the changes in the response

### Step 5: Delete Products (Admin Only)
1. Run `🗑️ Delete Product` - deletes the created product
2. Confirm deletion with success message

### Step 6: Test Error Cases
1. Run `❌ Create Product - Duplicate Code Error`
2. Run `❌ Get Product - Not Found`

## 🔧 Environment Variables

| Variable | Description | Auto-Set | Usage |
|----------|-------------|----------|-------|
| `baseUrl` | API base URL | Manual | All requests |
| `jwt_token` | User JWT token | ✅ Auto | Read operations |
| `mod_jwt_token` | Moderator JWT token | ✅ Auto | Create/Update |
| `admin_jwt_token` | Admin JWT token | ✅ Auto | Delete operations |
| `product_id` | First product ID | ✅ Auto | Get by ID tests |
| `created_product_id` | Created product ID | ✅ Auto | Update/Delete tests |

## 📝 Request Examples

### Create Product Request:
```json
{
    "code": "LAPTOP001",
    "product": "Gaming Laptop",
    "brand": "Dell",
    "category": "Electronics",
    "price": 1299.99,
    "color": "Black",
    "thumbnailImage": "https://example.com/laptop-thumb.jpg",
    "subImages": "https://example.com/laptop1.jpg,https://example.com/laptop2.jpg",
    "description": "High-performance gaming laptop with RTX graphics card and 16GB RAM"
}
```

### Product Response:
```json
{
    "id": 1,
    "code": "LAPTOP001",
    "product": "Gaming Laptop",
    "brand": "Dell",
    "category": "Electronics",
    "price": 1299.99,
    "color": "Black",
    "thumbnailImage": "https://example.com/laptop-thumb.jpg",
    "subImages": "https://example.com/laptop1.jpg,https://example.com/laptop2.jpg",
    "description": "High-performance gaming laptop with RTX graphics card and 16GB RAM"
}
```

## 🛡️ Role-Based Access Control

### 👤 USER Role
- ✅ Get All Products
- ✅ Get Product by ID
- ❌ Create Product
- ❌ Update Product
- ❌ Delete Product

### 👨‍💼 MODERATOR Role
- ✅ Get All Products
- ✅ Get Product by ID
- ✅ Create Product
- ✅ Update Product
- ❌ Delete Product

### 👑 ADMIN Role
- ✅ Get All Products
- ✅ Get Product by ID
- ✅ Create Product
- ✅ Update Product
- ✅ Delete Product

## ⚠️ Important Notes

1. **Authentication Required:** All endpoints require valid JWT tokens
2. **Role Validation:** Different endpoints require different role levels
3. **Unique Product Codes:** Product codes must be unique across the system
4. **Data Validation:** All required fields must be provided
5. **Environment Selection:** Ensure correct environment is selected

## 🔍 Troubleshooting

### Common Issues:

1. **401 Unauthorized**
   - Check if JWT token is valid and not expired
   - Ensure correct Authorization header format
   - Verify user has required role

2. **400 Bad Request**
   - Check for duplicate product codes
   - Verify all required fields are present
   - Validate data types (price should be number)

3. **404 Not Found**
   - Verify product ID exists
   - Check if product was already deleted

4. **403 Forbidden**
   - User doesn't have required role for the operation
   - Use appropriate token (mod_jwt_token for create/update, admin_jwt_token for delete)

## 🎯 Next Steps

After Product API testing:
1. Test Purchase APIs (requires products to exist)
2. Test Invoice APIs (requires products for invoice items)
3. Explore advanced filtering and search features

---

**🔗 Related Files:**
- `Product_API_Postman_Collection.json` - Main collection
- `Product_Environment.postman_environment.json` - Environment variables
- `Authentication_API_Postman_Collection.json` - Required for JWT tokens
