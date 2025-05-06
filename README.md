# DropBaba 🍔📦

**DropBaba** is a robust, scalable, distributed food and grocery delivery platform built using Java (Spring Boot) microservices and an Android mobile frontend.

## 🚀 Project Goal
To create a delivery system that connects users, vendors, and delivery riders in real time with event-driven architecture and strong observability.

## 🏗️ Tech Stack

### Backend (Java)
- Java 21 + Spring Boot
- Spring Cloud Gateway, Eureka (Service Discovery)
- RabbitMQ (Event Messaging)
- Redis (Caching)
- PostgreSQL (Primary DB)
- Docker & Kubernetes
- GitHub Actions for CI/CD
- Micrometer + Prometheus + Grafana (Monitoring)

### Frontend (Mobile)
- Android (Kotlin)
- MVVM Architecture
- Retrofit, Firebase, Room DB

## 📦 Microservices Overview

| Service | Purpose |
|--------|---------|
| `auth-service` | JWT-based auth, login/signup |
| `user-service` | Profiles, address book |
| `vendor-service` | Vendor onboarding & menus |
| `order-service` | Cart, checkout, payment |
| `delivery-service` | Rider assignment, tracking |
| `notification-service` | Push/SMS via RabbitMQ |
| `analytics-service` | Event tracking & CTR |
| `gateway-service` | API gateway for routing |
| `discovery-server` | Eureka service registry |

## 🔄 CI/CD

- GitHub Actions for build/test/lint
- Docker-based builds
- Optional deployment to staging via Kubernetes

## 🧪 Local Development

Use `docker-compose` to spin up:
- RabbitMQ
- Redis
- PostgreSQL
- Eureka & Gateway

```bash
docker-compose up --build
