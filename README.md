# 🔐 SurakshaWalt — Consent & Notification Backend

> **Project for Canara Bank Hackathon** — a Spring Boot backend to handle user information, partner (company) consent flows, notifications (WebSocket chat), and authentication. Built with Java 21, Spring Boot, Spring Data JPA and PostgreSQL. 🚀

---

## ✨ Overview
SurakshaWalt is a modular backend service that helps partners (companies) request consent from users, lets users manage their consent, and supports real-time notifications (chat/rooms). It includes authentication (user/partner/admin), user profile management, company information, consent management, and WebSocket support for live messaging.

---

## 🔧 Key Features
- User / Partner / Admin registration and JWT-based authentication. 🛂  
- Partner (company) can request user consent for data sharing. 🧾  
- Users can give, update, revoke, and block consent; consent history is recorded. 🔁  
- Company information management (CRUD for company profiles). 🏢  
- User profile management (get/update/delete). 👤  
- User data retrieval endpoint for partners (secured). 🔍  
- WebSocket (STOMP) endpoint for real-time messaging (`/chat`) and a `/rooms` controller that manages rooms/messages. 💬  
- PostgreSQL persistence via Spring Data JPA. 🗄️

---

## 🗂️ Project Structure (important folders)
```
SurakshaWalt/
├─ pom.xml
├─ src/
│  ├─ main/
│  │  ├─ java/com/kucp1127/SurakshaWalt/
│  │  │  ├─ SecurityConfiguration/   # Auth, JWT, user/partner/admin services
│  │  │  ├─ UserInformation/         # User profile management
│  │  │  ├─ UserConsent/             # Consent flow & history
│  │  │  ├─ CompanyInformation/      # Company info CRUD
│  │  │  ├─ CompanyAskingConsent/    # Company consent requests
│  │  │  ├─ UserSendingData/         # Partner requests for user data
│  │  │  ├─ NotificationHandeling/   # Rooms & messaging models + services
│  │  │  └─ WebSocket/               # STOMP config, /chat endpoint
│  │  └─ resources/
│  │     └─ application.properties   # DB + JWT + other config (redacted values!)
└─ HELP.md
```

---

## ⚙️ Tech Stack
- Java 21  
- Spring Boot 3.x (Maven)  
- Spring Security (JWT)  
- Spring Web / Spring WebSocket (STOMP + SockJS)  
- Spring Data JPA (PostgreSQL)  
- Maven wrapper included (`mvnw` / `mvnw.cmd`)  

---

## 🧩 Important Endpoints (summary)
> Base controller mappings and common endpoints found in the project — use these as a starting point.

### Authentication & Registration (`/auth`)
- `POST /auth/userRegistration` — register a user  
- `POST /auth/PartnerRegistration` — register a partner/company  
- `POST /auth/AdminRegistration` — register an admin (protected by passkey)  
- `POST /auth/login` — user/partner/admin login → returns JWT  
- `GET /auth/getAllCompanies` — list verified companies  
- `PUT /auth/Partner/{username}` — verify/unverify partner entries  

### User Profile (`/userinfo`)
- `PUT /userinfo/{username}` — update user profile  
- `GET /userinfo/{username}` — fetch user profile  
- `DELETE /userinfo/{username}` — delete user profile  

### Company Info (`/company`)
- `GET /company` — list companies  
- `GET /company/{companyName}` — company details  
- `PUT /company` — create/update company info  

### Company Asking Consent (`/consents`)
- `POST /consents` — partner asks for consent (create request)  
- `GET /consents` — list consent requests  
- `GET /consents/{companyName}` — consent details for a company  
- `DELETE /consents/{companyName}` — delete consent info  

### User Consent (`/user-consent`)
- `POST /user-consent` — give consent  
- `GET /user-consent/getStatus` — check consent status (query params used)  
- `GET /user-consent/user/{username}` — all consents of a user  
- `GET /user-consent/company/{companyName}` — consents related to a company  
- `PUT /user-consent` — update consent  
- `POST /user-consent/revoke` — revoke consent  
- `POST /user-consent/block` — block a company's access  
- `GET /user-consent/history` — consent history  

### User Data Access
- `POST /getData` — partner requests user data (expects DTO)  

### Notification / WebSocket
- STOMP endpoint: `ws://<host>:<port>/chat` (SockJS enabled)  
- Topic broker prefix: `/topic`, application prefix: `/app`  
- Rooms controller: `/rooms` — message and room-related REST endpoints

---

## 🛠️ Setup & Run (local)
1. Install Java 21 and Maven (or use the included `mvnw`).  
2. Create a PostgreSQL database (recommended name: `SurakshaWalt`) and a user.  
3. Copy `src/main/resources/application.properties` → replace sensitive values:
   - `spring.datasource.url=jdbc:postgresql://localhost:5432/<DB_NAME>`  
   - `spring.datasource.username=<DB_USER>`  
   - `spring.datasource.password=<DB_PASSWORD>`  
   - **Important:** `jwt.secret` is present in `application.properties` — rotate this secret for production. Never commit real secrets to source control. 🔒
4. From the project root (`SurakshaWalt/SurakshaWalt`) run:
```bash
# Using included wrapper
./mvnw spring-boot:run

# Or with local maven
mvn spring-boot:run
```
5. The app will start on the default Spring Boot port (8080) unless overridden in `application.properties`.

---

## ✅ Example: Login (curl)
```bash
curl -X POST 'http://localhost:8080/auth/login' \
 -H 'Content-Type: application/json' \
 -d '{"username":"partner1","password":"<your_password>"}'
```
Response normally contains a JWT token; use that in `Authorization: Bearer <token>` for protected endpoints.

---

## 🔐 Notes on Security & Secrets
- **DO NOT** publish `application.properties` with real credentials or API keys.. Please **rotate** any exposed credentials immediately and move secrets to environment variables or a secrets manager.
- Example: `jwt.secret`, database password, API keys should be set via environment variables and not hard-coded.
---

## 🤝 Contributing
1. Fork the repository.  
2. Create a feature branch: `git checkout -b feat/my-feature`  
3. Commit & push: `git push origin feat/my-feature`  
4. Open a PR describing the change.

Please follow standard Java code style & include Javadoc for major services/controllers.

---

## 📜 License & Contact 
- Contact : adarshiiitkota@gmail.com
---


