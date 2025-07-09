# 🏢 Stock Inventory - Complete API Collection Guide

## 📋 Overview

This is the **complete API collection** for the Stock Inventory Management System. It includes all endpoints for Authentication, Products, Purchases, and Invoices in one comprehensive Postman collection.

## 🚀 Quick Setup

### 1. Import Collection & Environment

1. **Import Collection:**
   - Open Postman
   - Click "Import" → "Upload Files"
   - Select `Stock_Inventory_All_APIs_Collection.json`

2. **Import Environment:**
   - Click "Import" → "Upload Files" 
   - Select `Stock_Inventory_All_APIs_Environment.postman_environment.json`
   - Select the environment from the dropdown (top-right)

### 2. Configure Base URL

The environment is pre-configured for:
- **Local Development:** `http://localhost:8081`
- **Production:** Update `baseUrl` variable as needed

## 📚 Collection Structure

### 🔐 **Authentication**
| Endpoint | Description | Token Saved |
|----------|-------------|-------------|
| `📝 Register User` | Register regular user | - |
| `📝 Register Admin` | Register admin user | - |
| `🔑 Login User` | Login regular user | `{{jwt_token}}` |
| `🔑 Login Admin` | Login admin user | `{{admin_jwt_token}}` |

### 📦 **Products**
| Endpoint | Description | Required Role |
|----------|-------------|---------------|
| `📋 Get All Products` | List all products | USER+ |
| `➕ Create Product` | Create new product | ADMIN |

### 🛒 **Purchases**
| Endpoint | Description | Required Role |
|----------|-------------|---------------|
| `📋 Get All Purchases` | List all purchases | USER+ |
| `➕ Create Purchase` | Create new purchase | ADMIN |
| `🔄 Update Purchase Status` | Update payment status | ADMIN |

### 🧾 **Invoices**
| Endpoint | Description | Required Role |
|----------|-------------|---------------|
| `📋 Get All Invoices` | List all invoices | USER+ |
| `➕ Create Invoice` | Create new invoice | ADMIN |
| `🔄 Update Invoice Status` | Update invoice status | ADMIN |

## 🔄 Complete Usage Workflow

### Step 1: Authentication Setup
1. **Register Users:**
   - Run `📝 Register User` (creates: `john_doe`)
   - Run `📝 Register Admin` (creates: `admin_user`)

2. **Login & Get Tokens:**
   - Run `🔑 Login User` → saves `{{jwt_token}}`
   - Run `🔑 Login Admin` → saves `{{admin_jwt_token}}`

### Step 2: Product Management
1. **Create Products:**
   - Run `➕ Create Product` (creates Gaming Laptop)
   - Note: Auto-saves `{{created_product_id}}`

2. **View Products:**
   - Run `📋 Get All Products` (lists all products)

### Step 3: Purchase Management
1. **Create Purchases:**
   - Run `➕ Create Purchase` (creates purchase order)
   - Note: Auto-saves `{{created_purchase_id}}`

2. **Update Purchase Status:**
   - Run `🔄 Update Purchase Status` (marks as PAID)

3. **View Purchases:**
   - Run `📋 Get All Purchases` (lists all purchases)

### Step 4: Invoice Management
1. **Create Invoices:**
   - Run `➕ Create Invoice` (creates customer invoice)
   - Note: Auto-saves `{{created_invoice_id}}`

2. **Update Invoice Status:**
   - Run `🔄 Update Invoice Status` (marks as PAID)

3. **View Invoices:**
   - Run `📋 Get All Invoices` (lists all invoices)

## 🔧 Environment Variables

| Variable | Description | Auto-Set | Usage |
|----------|-------------|----------|-------|
| `baseUrl` | API base URL | Manual | All requests |
| `jwt_token` | User JWT token | ✅ Auto | General access |
| `admin_jwt_token` | Admin JWT token | ✅ Auto | Admin operations |
| `user_id` | Current user ID | ✅ Auto | Reference |
| `username` | Current username | ✅ Auto | Reference |
| `product_id` | First product ID | ✅ Auto | Product operations |
| `created_product_id` | Created product ID | ✅ Auto | Product updates |
| `purchase_id` | First purchase ID | ✅ Auto | Purchase operations |
| `created_purchase_id` | Created purchase ID | ✅ Auto | Purchase updates |
| `invoice_id` | First invoice ID | ✅ Auto | Invoice operations |
| `created_invoice_id` | Created invoice ID | ✅ Auto | Invoice updates |

## 📝 Sample Data Examples

### Product Creation:
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

### Purchase Creation:
```json
{
    "vendorName": "ABC Suppliers",
    "poId": "PO-2024-001",
    "productName": "Gaming Laptop",
    "quantity": 5,
    "unitPrice": 1299.99,
    "purchaseDate": "2024-01-15",
    "deliveryDate": "2024-01-25",
    "paymentStatus": "PENDING",
    "productId": 1
}
```

### Invoice Creation:
```json
{
    "invoiceNumber": "INV-2024-001",
    "customerName": "John Smith",
    "customerEmail": "john.smith@example.com",
    "customerPhone": "+1-555-0123",
    "customerAddress": "123 Main St, City, State 12345",
    "invoiceDate": "2024-01-15",
    "dueDate": "2024-02-15",
    "status": "PENDING",
    "items": [
        {
            "productId": 1,
            "productName": "Gaming Laptop",
            "quantity": 2,
            "unitPrice": 1299.99,
            "totalPrice": 2599.98
        }
    ],
    "subtotal": 2599.98,
    "taxAmount": 259.99,
    "totalAmount": 2859.97,
    "notes": "Thank you for your business!"
}
```

## 🛡️ Role-Based Access Control

### 👤 USER Role (`{{jwt_token}}`)
- ✅ View Products, Purchases, Invoices
- ❌ Create/Update/Delete operations

### 👑 ADMIN Role (`{{admin_jwt_token}}`)
- ✅ Full access to all operations
- ✅ Create/Update/Delete Products, Purchases, Invoices
- ✅ Manage user accounts

## ⚠️ Important Notes

1. **Authentication Required:** All endpoints require valid JWT tokens
2. **Role Validation:** Different endpoints require different role levels
3. **Data Dependencies:** Products must exist before creating purchases/invoices
4. **Environment Selection:** Ensure correct environment is selected
5. **Server Status:** Make sure Spring Boot app is running on port 8081

## 🔍 Troubleshooting

### Common Issues:

1. **401 Unauthorized**
   - Check if JWT token is valid and not expired
   - Ensure correct Authorization header format
   - Verify user has required role

2. **400 Bad Request**
   - Check for duplicate codes/IDs
   - Verify all required fields are present
   - Validate data types and formats

3. **404 Not Found**
   - Verify resource ID exists
   - Check if resource was already deleted

4. **403 Forbidden**
   - User doesn't have required role for the operation
   - Use appropriate token (admin_jwt_token for admin operations)

## 🎯 Testing Strategy

### Recommended Testing Order:
1. **Authentication** → Register users and login
2. **Products** → Create products first (needed for purchases/invoices)
3. **Purchases** → Create purchase orders
4. **Invoices** → Create customer invoices

### Automated Testing:
- Each request includes built-in tests
- Automatic variable saving for chained requests
- Response validation and error checking

---

**🔗 Files Included:**
- `Stock_Inventory_All_APIs_Collection.json` - Complete API collection
- `Stock_Inventory_All_APIs_Environment.postman_environment.json` - Environment variables
- `ALL_APIS_POSTMAN_GUIDE.md` - This comprehensive guide

**🎉 Ready to Use:** Import both files into Postman and start testing your complete Stock Inventory Management System!
