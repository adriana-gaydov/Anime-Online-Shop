### Final project for SoftUni Java Web module. The website is built using the framework Spring.
A custom implementation of an anime e-commerce website. Built using Spring framework and MySQL database.

## Index page preview:
![](https://i.imgur.com/wePxRXi.gif)

This application <b>cannot</b> be used for commercial purposes as it is intended for <b>education</b> only.

## Basic info and technologies used in the website:
- Spring framework
- Spring Data JPA for accessing the database
- MySQL database
- Security with Spring Security
- Rest services + Fetch (with JS)
- IP tracking interceptor
- Exception handling (Controller advice, Controller based, Rest exception handlers, Custom exceptions)
- Cron scheduler
- Unit and integration tests (*61% for now*)
- Validation (Custom annotations, User-friendly error messages)
- MapStruct
- Thymeleaf
- Bootstrap

## Functionalities of the application:
- Login/Register/Logout
- User details
  - Edit
  - View orders
- Admin panel
  - View all orders
  - Statistics for the visitations
  - Add new products
  - Edit existing products
  - Manage all users
    - Disable user
    - Make/Remove admin
- Shopping cart custom implementation *not the best, should add payment methods*
- Address management when ordering
  - Create new address
  - Use existing one
- Products separated by category
- Contact us form
- About page
- Home page with banners, customer review, product categories
- Search by name, min and max price
- Newsletter

## Admin page preview:
![](https://i.imgur.com/BTm6JwW.gif)

## Prerequisites
- IntelliJ IDEA
- Spring framework
- MySQL Database

## Testing user credentials (with admin rights) 
```
admin@admin.bg 123
```
