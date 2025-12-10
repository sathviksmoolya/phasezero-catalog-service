How to run the project
----------------------

Make sure the JAVA 17 is installed

Clone the Repository

git clone https://github.com/sathviksmoolya/phasezero-catalog-service.git
cd phasezero-catalog-service

Run the Application in windows 
bash -  mvnw.cmd spring-boot:run

Once started, the app will run on:
http://localhost:8080

Test endpoints, for example:

All products → GET http://localhost:8080/product/allproducts

Add product → POST http://localhost:8080/product/add



 Architecture & Design
============================

The application has:

1. Controller Layer**
   - `ProductController` (`com.jsp.phasezero_catalog_service.controller`)
   - Exposes REST endpoints under the base path: `/product`
   - Responsible only for:
     - Mapping HTTP requests
     - Validating input (`@Valid`)
     - Returning appropriate HTTP status & response bodies

2. Service Layer
   - `ProductService` (`com.jsp.phasezero_catalog_service.service`)
   - Contains business logic, such as:
     - Converting between DTOs and entity      
	- Filtering products by name and category
     - Sorting products by price
     - Calculating total inventory value (`price * stock`)

3. Repository Layer
   - `ProductRepository` (`com.jsp.phasezero_catalog_service.repository`)
   - Extends `JpaRepository<Product, Integer>`
   - Provides:
     - Standard CRUD operations
     - Derived queries:
       - `findByPartNameContainingIgnoreCase(String partName)`
       - `findByCategoryIgnoreCase(String category)`
       - `findAllByOrderByPriceAsc()`

4. Entity Layer
   - `Product` (`com.jsp.phasezero_catalog_service.entity`)
   - Mapped to table **`products`**
   - Fields:
     - `id` (primary key, auto-generated)
     - `partNumber` (unique)
     - `partName`
     - `category`
     - `price`
     - `stock`
   - Uses `@PrePersist` / `@PreUpdate` to normalize `partName` to lowercase**.

5. DTO Layer
   - `ProductRequest`
     - Input DTO for creating products
     - Validation:
       - `@NotBlank` for `partNumber`, `partName`, `category`
       - `@Min(0)` for `price`, `stock`
   - `ProductResponse`
     - Output DTO returned by API
     - Contains: `id`, `partNumber`, `partName`, `category`, `price`, `stock`




## 🔗 API Endpoints

All endpoints are prefixed with the base path: `/product`

| HTTP Method | Path | Description |
| :--- | :--- | :--- |
| `POST` | `/product/add` | Create a new product. |
| `GET` | `/product/allproducts` | Get all products. |
| `GET` | `/product/search?name={partName}` | Search products by partial name (case-insensitive). |
| `GET` | `/product/category/{category}` | Filter products by exact category (case-insensitive). |
| `GET` | `/product/sort/price` | Get all products sorted by price (ascending). |
| `GET` | `/product/inventory/value` | Get total inventory value (sum of `price * stock`). |



## 📝 Example Requests & Responses

### 1. Create a Product – `POST /product/add`

#### Request Body (`ProductRequest`)
```json
{
  "partNumber": "BRK-101",
  "partName": "Brake Pad",
  "category": "BRAKES",
  "price": 1500.0,
  "stock": 10
}
```
Successful Response – 201 Created (ProductResponse)
```json
{
  "id": 1,
  "partNumber": "BRK-101",
  "partName": "Brake Pad",
  "category": "BRAKES",
  "price": 1500.0,
  "stock": 10
}
```
2. Get All Products – GET /product/allproducts
Response – 200 OK 
```json
[
  {
    "id": 1,
    "partNumber": "BRK-1001",
    "partName": "brake pad",
    "category": "BRAKES",
    "price": 1500.0,
    "stock": 10
  },
  {
    "id": 2,
    "partNumber": "ENG-2001",
    "partName": "engine oil",
    "category": "ENGINE",
    "price": 800.0,
    "stock": 25
  }
]
```


Why I Choose H2
---------------

I chose H2 because it gives me a real database environment with SQL, schema validation,
and JPA support, but without requiring any installation. Lists and Maps lose data on restart,
no constraints, and cannot use sql.

