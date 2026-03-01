# Campus Hub – University of Wolverhampton

A full-stack web application for centralising student services, campus information, and engagement at the University of Wolverhampton.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.2, Spring Security (JWT), Hibernate ORM, JPA |
| Database | MySQL 8 |
| Build | Maven |
| Frontend | HTML, CSS, Vanilla JavaScript |
| Server | Embedded Tomcat (port 8080) |

---

## Architecture

```
campus-hub/
├── backend/
│   └── src/main/java/com/wolverhampton/campushub/
│       ├── CampusHubApplication.java
│       ├── config/          (Security, Web, DataInitializer)
│       ├── controller/      (REST Controllers)
│       ├── dto/             (Data Transfer Objects)
│       ├── entity/          (JPA Entities)
│       ├── repository/      (Spring Data JPA Repositories)
│       ├── security/        (JWT, UserDetails)
│       └── service/         (Business Logic)
└── frontend/
    ├── css/style.css
    ├── js/app.js, layout.js
    ├── pages/  (HTML pages)
    └── index.html
```

---

## Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven 3.6+

### 1. Create Database
```sql
CREATE DATABASE campus_hub;
```

### 2. Configure Database
Edit `backend/src/main/resources/application.properties`:
```properties
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
```

### 3. Build & Run
```bash
cd backend
mvn spring-boot:run
```

### 4. Access the Application
Open http://localhost:8080

### Default Admin Credentials
- **Username:** `admin`
- **Password:** `admin123`

*(Change this immediately in production)*

---

## Database Entities

| Entity | Description |
|---|---|
| User | Students and admin accounts |
| Role | ROLE_STUDENT, ROLE_ADMIN |
| Facility | Campus buildings and services |
| Event | Campus events |
| Announcement | News feed and announcements |
| TransportSchedule | Inter-campus shuttle timetables |
| Club | Student clubs and societies |
| ClubMembership | Student-Club many-to-many join |
| CheckIn | Engagement check-in records |
| Reward | Redeemable reward catalogue |
| Counselor | Wellbeing counselor profiles |
| CounselingSession | Booked counseling sessions |

---

## API Endpoints Summary

### Public
- `POST /api/auth/register` – Student registration
- `POST /api/auth/login` – Login (returns JWT)

### Authenticated (Student + Admin)
- `GET/PUT /api/auth/profile` – View/update profile
- `GET /api/facilities` – View all facilities
- `GET /api/events` – View upcoming events
- `GET /api/announcements` – View announcements
- `GET /api/transport` – View transport schedules
- `GET /api/clubs` – View all clubs
- `POST /api/clubs/{id}/join` – Join a club
- `DELETE /api/clubs/{id}/leave` – Leave a club
- `POST /api/checkin` – Check in at a location
- `GET /api/checkin/my` – My check-in history
- `GET /api/rewards` – View reward catalogue
- `GET /api/counseling/counselors` – View counselors
- `POST /api/counseling/sessions` – Book a session
- `GET /api/counseling/sessions/my` – My sessions
- `PATCH /api/counseling/sessions/{id}/cancel` – Cancel session

### Admin Only
- `/api/admin/**` – Full CRUD for all content types
- `/api/admin/stats` – Dashboard statistics
- `/api/admin/users` – User management

---

## Features

1. **Authentication** – JWT-based, BCrypt password hashing, role-based access
2. **Facility Feed** – View campus facilities with status (Open/Closed/Busy)
3. **Events** – Upcoming events with admin CRUD
4. **Announcements** – Pinnable news posts by category
5. **Transport** – Inter-campus shuttle schedules with route filtering
6. **Club Hub** – Join/leave student clubs, admin management
7. **Check-In Rewards** – Earn engagement points at campus locations
8. **Mental Health Portal** – Book confidential counseling sessions

---

## Security Notes

- Passwords hashed with BCrypt
- JWT tokens expire after 24 hours
- Counseling session details only visible to the booked student and admin
- All admin endpoints protected with `ROLE_ADMIN` Spring Security authorization
- CORS configured for development (restrict in production)
