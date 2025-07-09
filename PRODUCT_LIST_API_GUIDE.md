# 📋 Product List APIs - Postman Collection Guide

## 📋 Overview

This is a comprehensive **Product List API collection** for the Stock Inventory Management System. It includes various ways to retrieve and filter product data with detailed logging and validation.

## 🚀 Quick Setup

### 1. Import Collection & Environment

1. **Import Collection:**
   - Open Postman
   - Click "Import" → "Upload Files"
   - Select `Product_List_APIs_Postman_Collection.json`

2. **Import Environment:**
   - Click "Import" → "Upload Files" 
   - Select `Product_List_Environment.postman_environment.json`
   - Select the environment from the dropdown (top-right)

### 3. Prerequisites

**⚠️ Important:** You need JWT tokens before using Product List APIs!

1. **First import and use the Authentication collection**
2. **Login to get JWT tokens:**
   - User token: `{{jwt_token}}`
   - Admin token: `{{admin_jwt_token}}`

## 📚 Collection Structure

### 📋 **Basic Listing**

| Endpoint | Description | Output |
|----------|-------------|--------|
| `📋 Get All Products` | Retrieve all products | Complete product list |
| `🔍 Get Product by ID` | Get specific product | Detailed product info |

### 🔍 **Filtering & Search**

| Endpoint | Description | Filter Type |
|----------|-------------|-------------|
| `🏷️ Get Products by Category` | Filter by category | `?category=cosmetics` |
| `🔍 Search Products by Brand` | Filter by brand | `?brand=ponds` |
| `💰 Get Products by Price Range` | Filter by price | `?minPrice=50&maxPrice=200` |

### 📄 **Pagination & Sorting**

| Endpoint | Description | Parameters |
|----------|-------------|------------|
| `📄 Get Products with Pagination` | Paginated results | `?page=0&size=5` |
| `🔢 Get Products Sorted by Price` | Sort by price | `?sort=price&order=asc` |

## 🔄 Usage Workflow

### Step 1: Authentication
1. **Import Authentication collection** and get JWT tokens
2. **Login as user** → saves to `{{jwt_token}}`
3. **Login as admin** → saves to `{{admin_jwt_token}}`

### Step 2: Basic Product Listing
1. **Run `📋 Get All Products`** - See all products in system
2. **Check console logs** - View detailed product information
3. **Run `🔍 Get Product by ID`** - Get detailed view of specific product

### Step 3: Filtering & Search
1. **Run `🏷️ Get Products by Category`** - Filter cosmetics products
2. **Run `🔍 Search Products by Brand`** - Find Ponds products
3. **Run `💰 Get Products by Price Range`** - Find products $50-$200

### Step 4: Advanced Features
1. **Run `📄 Get Products with Pagination`** - Test pagination
2. **Run `🔢 Get Products Sorted by Price`** - Test sorting

## 🔧 Environment Variables

| Variable | Description | Auto-Set | Usage |
|----------|-------------|----------|-------|
| `baseUrl` | API base URL | Manual | All requests |
| `jwt_token` | User JWT token | ✅ Auto | All product requests |
| `admin_jwt_token` | Admin JWT token | ✅ Auto | Admin operations |
| `product_id` | First product ID | ✅ Auto | Get by ID requests |
| `product_code` | First product code | ✅ Auto | Reference |

## 📝 API Endpoints & Parameters

### Basic Endpoints:
```
GET /api/products                    - Get all products
GET /api/products/{id}               - Get product by ID
```

### Filtering Endpoints:
```
GET /api/products?category=cosmetics     - Filter by category
GET /api/products?brand=ponds            - Filter by brand
GET /api/products?minPrice=50&maxPrice=200 - Price range filter
```

### Pagination & Sorting:
```
GET /api/products?page=0&size=5          - Pagination
GET /api/products?sort=price&order=asc   - Sort by price
```

## 📊 Expected Response Format

### Product List Response:
```json
[
  {
    "id": 1,
    "code": "21244",
    "product": "powder",
    "brand": "ponds",
    "category": "cosmetics",
    "price": 123,
    "color": "Yellow",
    "thumbnailImage": "string",
    "subImages": "string",
    "description": "string"
  }
]
```

### Single Product Response:
```json
{
  "id": 1,
  "code": "21244",
  "product": "powder",
  "brand": "ponds",
  "category": "cosmetics",
  "price": 123,
  "color": "Yellow",
  "thumbnailImage": "string",
  "subImages": "string",
  "description": "string"
}
```

## 🧪 Built-in Testing Features

### ✅ **Automatic Validation:**
- Status code verification (200 OK)
- Response structure validation
- Data type checking
- Array/object validation

### ✅ **Console Logging:**
- Total product counts
- Detailed product information
- Search/filter results
- Pagination details

### ✅ **Variable Management:**
- Auto-saves product IDs
- Auto-saves product codes
- Ready for chained requests

## 🔍 Troubleshooting

### Common Issues:

1. **401 Unauthorized**
   - Check if JWT token is valid and not expired
   - Ensure you've logged in first

2. **Empty Results []**
   - No products in database yet
   - Create some products first using Product Creation APIs

3. **404 Not Found**
   - Product ID doesn't exist
   - Check available product IDs from "Get All Products"

4. **Connection Error**
   - Verify Spring Boot app is running on port 8081
   - Check `baseUrl` environment variable

## 📋 Console Output Examples

### Get All Products:
```
Total products found: 3
Sample product: {
  "id": 1,
  "code": "21244",
  "product": "powder",
  "brand": "ponds",
  "category": "cosmetics",
  "price": 123,
  "color": "Yellow"
}
```

### Category Filter:
```
Cosmetics products found: 2
Product 1: {
  code: "21244",
  product: "powder",
  brand: "ponds",
  category: "cosmetics",
  price: 123,
  color: "Yellow"
}
```

### Price Range Filter:
```
Products in price range 50-200: 2
Product 1: {
  code: "21244",
  product: "powder",
  brand: "ponds",
  price: 123,
  category: "cosmetics"
}
```

## ⚠️ Important Notes

1. **Authentication Required:** All endpoints require valid JWT tokens
2. **Data Dependencies:** Products must exist in database to see results
3. **Console Logs:** Check Postman console for detailed output
4. **Environment Selection:** Ensure correct environment is selected
5. **Server Status:** Spring Boot app must be running on port 8081

## 🎯 Next Steps

After testing Product List APIs:
1. **Create products** using Product Creation APIs
2. **Test filtering** with real data
3. **Explore pagination** with larger datasets
4. **Integrate with Purchase/Invoice APIs**

---

**🔗 Related Files:**
- `Product_List_APIs_Postman_Collection.json` - Main collection
- `Product_List_Environment.postman_environment.json` - Environment variables
- `Login_Register_Postman_Collection.json` - Required for JWT tokens

**🎉 Ready to Use:** Import the collection and start exploring your product data!
