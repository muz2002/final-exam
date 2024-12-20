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

### Authentication

| Endpoint              | Method | Description                           | Auth Required | Example Payload                           |
|-----------------------|--------|---------------------------------------|---------------|--------------------------------------------|
| `/api/auth/register`  | POST   | Register a new user with a given role | No            | `{"username":"newuser","password":"pass","role":"AUTHOR"}` |
| `/api/auth/login`     | POST   | Authenticate and obtain a JWT token    | No            | `{"username":"admin","password":"adminpass"}` |

### Categories

| Endpoint               | Method | Description                        | Auth & Role                 | Example Payload                          |
|------------------------|--------|------------------------------------|-----------------------------|-------------------------------------------|
| `/api/categories`      | GET    | List all categories                | None                        | N/A                                       |
| `/api/categories`      | POST   | Create a new category              | `ADMIN` or `AUTHOR`         | `{"name":"Tech","description":"Tech posts"}` |
| `/api/categories/{id}` | PUT    | Update an existing category by ID  | `ADMIN` or `AUTHOR`         | `{"name":"Updated","description":"Desc"}` |
| `/api/categories/{id}` | DELETE | Delete a category by ID            | `ADMIN` or `AUTHOR`         | N/A                                       |

### Posts

| Endpoint            | Method | Description                                | Auth & Role           | Example Payload                                          |
|---------------------|--------|--------------------------------------------|-----------------------|---------------------------------------------------------|
| `/api/posts`        | GET    | List all posts (optionally by category)    | None                  | N/A                                                     |
| `/api/posts/{id}`   | GET    | Get a single post by ID (increments views) | None                  | N/A                                                     |
| `/api/posts`        | POST   | Create a new post                          | `ADMIN` or `AUTHOR`   | `{"title":"My Post","content":"Some text","categoryId":1}` |
| `/api/posts/{id}`   | PUT    | Update an existing post by ID             | `ADMIN` or `AUTHOR`   | `{"title":"Updated","content":"Updated content","categoryId":1}` |
| `/api/posts/{id}`   | DELETE | Delete a post by ID                       | `ADMIN` or `AUTHOR`   | N/A                                                     |
| `/api/posts/popular`| GET    | List popular posts by `(views+likesCount)`| None                  | N/A                                                     |


### Comments

| Endpoint                     | Method | Description                   | Auth Required | Example Payload          |
|------------------------------|--------|-------------------------------|---------------|--------------------------|
| `/api/comments/post/{postId}` | GET    | List comments on a given post | No            | N/A                      |
| `/api/comments/post/{postId}` | POST   | Add a comment to a post       | Yes (Any Role)| `{"content":"Great post!"}` |
 when the comment was created |

### Likes

| Endpoint               | Method | Description               | Auth Required | Example Payload |
|------------------------|--------|---------------------------|---------------|----------------|
| `/api/likes/post/{postId}` | POST | Like a post (once per user) | Yes (Any Role)| N/A            |

