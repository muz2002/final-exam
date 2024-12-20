# Blogging Platform API

**A Spring Boot based REST API for a blogging platform** with the following features:

- **User Authentication with Roles**: `ADMIN`, `AUTHOR`, `READER`.
- **CRUD for Blog Posts and Categories**.
- **Comments and Likes on Posts**.
- **Popular Posts Endpoint** sorted by views & likes.
- **JWT-based Authentication** for secure access.

## Features

- **User Management**: Register and Login to get a JWT token.
- **Roles**:
    - **ADMIN** and **AUTHOR** can create, update, delete posts and categories.
    - **READER** can only read posts and categories, and also like/comment.
- **Comments and Likes**:
    - Any authenticated user can comment and like posts.
- **Popular Posts**: Returns a list of posts sorted by `(views + likes)`.

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/muz2002/final-exam.git

2. **Navigate to the project directory**
    ```bash
   cd final-exam

3. **Build the project**
    ```bash
   ./gradlew build
4. **Run the application**
   ```bash
   ./gradlew bootRun
**Once the application is running, it will be available on http://localhost:8000.**

## API ENDPOINTS

**Authentication**





