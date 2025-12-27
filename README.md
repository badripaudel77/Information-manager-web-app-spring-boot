# 💡 Information Keeping Web Application

**Technology Stack:** Spring Boot, Spring Data JPA, Thymeleaf, Spring Security, PostgreSQL

---

## 🏛️ Architectural Pattern: MVC

The project follows the **Model-View-Controller (MVC)** design pattern for clean separation of concerns.

**Model [M]:**
- Represents entities like **User** and **Contact**.
- Relationship: One user can have multiple contacts.

**View [V]:**
- HTML pages built using **Thymeleaf**.

**Controller [C]:**
- Handles HTTP requests and user interactions.
- Calls service layer for business logic.

---

## 📁 Project File Structure

- **java/** → All source code (controllers, services, repositories, models)
- **static/** → CSS, JS, images, and other static resources
- **templates/** → HTML files using Thymeleaf templates
- **application.properties** → Environment-specific configuration (local, production) with Spring profiles
- **pom.xml** → All required dependencies

---

## 🛠️ Dependencies Used

**Spring Web, Spring Security, Spring Data JPA, Thymeleaf, PostgreSQL, Apache POI, OpenCSV**

---

## 🚀 Project Overview

The application allows users to securely manage their personal information and contacts.

**Features include:**
1. User registration and authentication (normal users vs admin privileges)
2. Admin-only reserved endpoints for management tasks
3. Add and manage notes/contacts with text and image fields
4. Navigation and pagination for large datasets
5. Password reset and update functionality
6. Import contacts from CSV files into the database
7. Export contact lists to CSV or Excel files

---

## 🏗️ Application Architecture & CI/CD

- GitHub Actions triggers on code push:
    - Runs unit tests using Maven
    - Builds the Spring Boot project
    - Creates a Docker image and pushes it to Docker Hub

- **Elastic Beanstalk Deployment:**
    - Full-stack Spring Boot application can be deployed easily to AWS Elastic Beanstalk

---

## ⚙️ Spring Boot Actuator

- Access `/actuator` endpoint to view health and metrics
- By default, only `/actuator/health` is enabled
- To expose all endpoints, add in **application.properties**:
```properties
management.endpoints.web.exposure.include=*
```

### 🏃Run the Application 
#### 1️⃣ Run Locally
#### 2️⃣ Using Docker compose as described below:

Pull the application Docker image:

```docker pull badripaudel77/info-keeper-spring-boot-docker:my-info-app```

Run the container:

```docker run -p 8080:8080 badripaudel77/info-keeper-spring-boot-docker:my-info-app```


Open the application in your browser: http://localhost:8080

Note: To ensure the latest changes are included, rebuild using Docker Compose:

```docker-compose up --build```

Using Docker Compose : Start the app along with the PostgreSQL container:
```docker-compose up```

### Showcase of the application:

1️⃣ Home Page
![Home Page](https://raw.githubusercontent.com/badripaudel77/Information-manager-web-app-spring-boot/main/src/main/resources/static/demo_screenshots/home_page.png)
2️⃣ Login Page
![Login Page](https://raw.githubusercontent.com/badripaudel77/Information-manager-web-app-spring-boot/main/src/main/resources/static/demo_screenshots/loginpage.png)
3️⃣ Adding Info
![Adding Info](https://raw.githubusercontent.com/badripaudel77/Information-manager-web-app-spring-boot/main/src/main/resources/static/demo_screenshots/adding_note.png)
4️⃣ Info Details
![Info Details Page](https://raw.githubusercontent.com/badripaudel77/Information-manager-web-app-spring-boot/main/src/main/resources/static/demo_screenshots/note_details.png)
5️⃣ View All Information
![Home Page](https://raw.githubusercontent.com/badripaudel77/Information-manager-web-app-spring-boot/main/src/main/resources/static/demo_screenshots/view_notes.png)
6️⃣ Admin Message
![Admin Message](https://raw.githubusercontent.com/badripaudel77/Information-manager-web-app-spring-boot/main/src/main/resources/static/demo_screenshots/admin_message.png)

