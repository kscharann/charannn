# Add Product API - Postman Collection Guide

## Overview

Clean and simple Add Product API collection for Stock Inventory Management System without any symbols or emojis.

## Quick Setup

### 1. Import Collection and Environment

1. **Import Collection:**
   - Open Postman
   - Click "Import" then "Upload Files"
   - Select `Add_Product_API_Collection.json`

2. **Import Environment:**
   - Click "Import" then "Upload Files" 
   - Select `Add_Product_Environment.postman_environment.json`
   - Select the environment from the dropdown

### 2. Prerequisites

**Important:** You need admin JWT token before using Add Product APIs

1. **First login as admin** to get JWT token
2. **Save token to** `admin_jwt_token` environment variable

## Collection Structure

### Add Product Endpoints

| Endpoint | Description | Product Type |
|----------|-------------|--------------|
| `Add New Product` | Add powder product | Powder |
| `Add Lipstick Product` | Add lipstick product | Lipstick |
| `Add Foundation Product` | Add foundation product | Foundation |

## Usage Workflow

### Step 1: Authentication
1. **Login as admin** using authentication API
2. **Copy JWT token** to `admin_jwt_token` variable

### Step 2: Add Products
1. **Run Add New Product** - Creates powder product
2. **Run Add Lipstick Product** - Creates lipstick product
3. **Run Add Foundation Product** - Creates foundation product

## Request Format

### Product Fields Required

All add product requests use this exact format:

```json
{
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

### Field Descriptions

- **code** - Unique product code (string)
- **product** - Product name (string)
- **brand** - Brand name (string)
- **category** - Product category (string)
- **price** - Product price (number)
- **color** - Product color (string)
- **thumbnailImage** - Main product image URL (string)
- **subImages** - Additional images comma-separated (string)
- **description** - Product description (string)

## Sample Products

### Product 1 - Powder
```json
{
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

### Product 2 - Lipstick
```json
{
    "code": "21245",
    "product": "lipstick",
    "brand": "maybelline",
    "category": "cosmetics",
    "price": 89,
    "color": "Red",
    "thumbnailImage": "lipstick-thumb.jpg",
    "subImages": "lipstick1.jpg,lipstick2.jpg",
    "description": "Long-lasting matte lipstick"
}
```

### Product 3 - Foundation
```json
{
    "code": "21246",
    "product": "foundation",
    "brand": "loreal",
    "category": "cosmetics",
    "price": 156,
    "color": "Beige",
    "thumbnailImage": "foundation-thumb.jpg",
    "subImages": "foundation1.jpg,foundation2.jpg",
    "description": "Full coverage liquid foundation"
}
```

## Environment Variables

| Variable | Description | Auto-Set |
|----------|-------------|----------|
| `baseUrl` | API base URL | Manual |
| `admin_jwt_token` | Admin JWT token | Manual |
| `created_product_id` | Created product ID | Auto |
| `created_product_code` | Created product code | Auto |

## Expected Response

### Success Response (201 Created)
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

## Built-in Testing

Each request includes automatic tests that verify:
- Status code is 201 (Created)
- Response contains all required fields
- Product data matches input
- Auto-saves created product ID and code

## Troubleshooting

### Common Issues

1. **401 Unauthorized**
   - Check admin JWT token is valid
   - Ensure token is saved in `admin_jwt_token` variable

2. **400 Bad Request**
   - Check all required fields are present
   - Verify data types are correct
   - Ensure product code is unique

3. **500 Internal Server Error**
   - Check database connection
   - Verify field names match database schema

4. **Connection Error**
   - Ensure Spring Boot app is running on port 8081
   - Check `baseUrl` is correct

## Important Notes

1. **Authentication Required** - All endpoints require admin JWT token
2. **Unique Product Codes** - Each product must have unique code
3. **Data Validation** - All fields are required
4. **Environment Selection** - Ensure correct environment is selected
5. **Server Status** - Spring Boot app must be running

## Files Included

- `Add_Product_API_Collection.json` - Main collection
- `Add_Product_Environment.postman_environment.json` - Environment variables
- `ADD_PRODUCT_API_GUIDE.md` - This guide

## Ready to Use

Import both files into Postman and start adding products to your Stock Inventory System.
