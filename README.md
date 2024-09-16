# Java-E-Commerce

A E-commerce prototype made with Java Springboot, mysql, thymeleaf and bootstrap. This project includes CRUD operations, Login using Spring Security and Role Based authentication. Since this project was made for studying, payments were outside the scope and were not included.

## Video Demonstration

[Gravação de tela de 15-09-2024 23:01:21.webm](https://github.com/user-attachments/assets/fbb62fd7-115b-44f9-a789-12f567995e50)

## Installing

### Pre-Requisites

* Java 17 or higher
* SpringBoot 3.x
* MySql database

### Running

1. Download or clone the repository
2. Create a Mysql database
3. On teststore/src/main/resources/ create application.properties file
4. Set the following properties on application.properties:

  ```
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/"your database"
    spring.datasource.username="your username"
    spring.datasource.password="your password"
    
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
  ```
  **OBS: Change the database path, username and password**
  
5. Run `teststore/src/main/java/com/marteldelfer/teststore/TeststoreApplication.java`
6. Open `http://localhost:8080/`
7. You're done!

## Using the Api

You can navigate normally through the site, but there are some things to know beforehand

### Role Based Authentication

This application uses role based authentication to restrict the access to admin related privileges. If you create your account normally, it will get the **ROLE_USER** role. To get access to admin privileges, use the default user firstAdmin:

* email: firstAdmin@gmail.com
* password: firstAdmin

### Products

By default, the products database is empty. To create and manage products, go to the URL `http://localhost:8080/crud` while logged as a admin. The product images will be saved on a folder at the root of the project.
