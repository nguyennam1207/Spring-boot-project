<h1><b>Spring Boot User Management & Authentication API</b></h1>    


Dự án Java Spring Boot RESTful API cung cấp các chức năng quản lý người dùng (User Management) và xác thực người dùng (Authentication) sử dụng JWT (JSON Web Token), Spring Security, và Spring Data JPA.  

<h2>🛠 Tech Stack</h2>    

- Language: Java 17+

- Framework: Spring Boot 3.x

- Security & Auth: Spring Security, Nimbus JWT 

- Database Access: Spring Data JPA / Hibernate  

- Database: MySQL 

- Mapping & Utilities: MapStruct, Lombok  

- Build Tool: Maven  

<h2>🚀 Hướng Dẫn Cài Đặt & Chạy Ứng Dụng</h2>    

><h3>1. Tiền đề (Prerequisites)</h3>    
   
- Java Development Kit (JDK): Phiên bản 17 hoặc cao hơn.
   
- Maven: 3.8+ (hoặc dùng mvnw đi kèm project).
   
- Database: MySQL / PostgreSQL.
   
><h3>2. Cấu hình Database</h3>
   
   Mở file src/main/resources/application.properties và chỉnh sửa các thông tin kết nối cơ sở dữ liệu phù hợp:
   
        # Cấu hình kết nối MySQL Database
        spring.datasource.url=jdbc:mysql://localhost:3306/spring_boot_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
        spring.datasource.username=root
        spring.datasource.password=your_password
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        
        # Cấu hình Hibernate / JPA
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
      
><h3>3. Build và Chạy ứng dụng</h3>
   
  Trên Terminal / CMD:
  
    # Bước 1: Tải dependencies và build project
    ./mvnw clean package

    # Bước 2: Chạy ứng dụng Spring Boot
    ./mvnw spring-boot:run

