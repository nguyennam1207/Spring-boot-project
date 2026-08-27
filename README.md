Spring Boot User Management & Authentication APIDự án Java Spring Boot RESTful API cung cấp các chức năng quản lý người dùng (User Management) và xác thực người dùng (Authentication) sử dụng JWT (JSON Web Token), Spring Security, và Spring Data JPA.  🛠 Tech StackLanguage: Java 17+Framework: Spring Boot 3.xSecurity & Auth: Spring Security, Nimbus JWT / JJWT  Database Access: Spring Data JPA / Hibernate  Database: MySQL / PostgreSQLMapping & Utilities: MapStruct, Lombok  Build Tool: Maven  📁 Cấu Trúc Dự ÁnPlaintextsrc/main/java/com/example/spring_boot_project
├── configuration/       # Cấu hình Spring Security & JWT Filter
│   └── SecurityConfig.java
├── controller/          # Tiếp nhận & xử lý HTTP Requests
│   ├── AuthenticationController.java
│   └── UserController.java
├── dto/                 # Data Transfer Objects (Request & Response)
│   ├── request/
│   │   ├── ApiResponse.java
│   │   ├── AuthenticationRequest.java
│   │   ├── IntrospectRequest.java
│   │   ├── UpdateUserRequest.java
│   │   └── UsersRequest.java
│   └── response/
│       ├── AuthenticationResponse.java
│       ├── IntrospectResponse.java
│       └── UsersResponse.java
├── entity/              # JPA Entities (Mapping Database Table)
│   └── Users.java
├── exception/           # Xử lý ngoại lệ tập trung (Global Exception Handling)
│   ├── AppException.java
│   ├── ErrorCode.java
│   └── GlobalExceptionHandler.java
├── mapper/              # MapStruct mappers (Entity <-> DTO)
│   └── UserMapper.java
├── repository/          # Spring Data JPA Repositories
│   └── UsersRepository.java
├── service/             # Xử lý Business Logic
│   ├── AuthenticationService.java
│   └── UsersService.java
└── SpringBootProjectApplication.java
🚀 Hướng Dẫn Cài Đặt & Chạy Ứng Dụng1. Tiền đề (Prerequisites)Java Development Kit (JDK): Phiên bản 17 hoặc cao hơn.Maven: 3.8+ (hoặc dùng mvnw đi kèm project).  Database: MySQL / PostgreSQL.2. Cấu hình DatabaseMở file src/main/resources/application.properties và chỉnh sửa các thông tin kết nối cơ sở dữ liệu phù hợp với máy của bạn:  Propertiesspring.datasource.url=jdbc:mysql://localhost:3306/spring_boot_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
3. Build và Chạy ứng dụngTrên Terminal / CMD:Bash# Tải dependencies và build project
./mvnw clean package

# Chạy ứng dụng
./mvnw spring-boot:run
(Nếu dùng Windows, bạn có thể thay ./mvnw bằng mvnw.cmd)  Ứng dụng sẽ khởi chạy tại port mặc định: http://localhost:8080
