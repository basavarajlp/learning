# 🔗 URL Shortener Service

A simple, lightweight URL Shortener service built with **Java Spring Boot**. It allows users to shorten long URLs into short, base62-encoded URLs and redirect to the original URLs via encoded links.

---

## ✨ Features

- ✅ Public API for shortening long URLs
- 🔁 Redirect to original URLs using short codes
- 🔒 Duplicate URL detection and reuse of short codes
- 📦 MySQL/PostgreSQL support using Spring Data JPA
- 🔤 Base62 encoding of auto-generated database IDs
- 📄 Clean REST API design
- 📅 Timestamp support for URL creation (optional)

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- H2 / MySQL / PostgreSQL
- Lombok
- Maven

---

## 🚀 Getting Started

### 🧱 Prerequisites

- Java 17+
- Maven 3.8+
- (Optional) MySQL/PostgreSQL

### 📦 Build and Run

```bash
# Clone the repository
git clone https://github.com/your-username/url-shortener.git
cd url-shortener

# Run the app
./mvnw spring-boot:run
