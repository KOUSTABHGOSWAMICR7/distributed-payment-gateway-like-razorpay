# Payment Gateway (Learning Project)

A Java/Spring Boot backend inspired by payment platforms such as Razorpay and Stripe. This project is being built to explore how payment systems handle correctness, security, and reliability when requests are duplicated or components fail.

> **Learning project only.** This software is not PCI DSS certified and is not suitable for processing real payments or production cardholder data. The card vault is a PCI-conscious design exercise, not a compliance claim. Payment adapters are not integrations with real banks or payment processors.

## Project status

The project is an in-progress modular monolith. “Implemented” below means that code exists for a prototype flow; it does not imply production readiness or complete end-to-end testing.

| Area | Current status |
|---|---|
| Merchant signup/login with JWT | Implemented |
| Merchant API key issuance, BCrypt-hashed secrets, Redis-backed key cache, rotation | Implemented |
| Rate limiting for API-key-authenticated routes | Implemented; fixed-window is configured by default, with other limiter implementations present |
| Orders, payment initiation/capture, and Redis-backed idempotency response replay | Implemented as a prototype; idempotency applies when a request supplies `X-Idempotency-Key` |
| Payment state machine and transition audit records | Implemented |
| Card, UPI, and net-banking routing | Adapter/processor structure exists; integrations are local implementations, not real processor connections |
| Card tokenization and encrypted PAN storage | Implemented as a learning exercise; **not PCI-certified** |
| Bank callback simulator | Partially implemented but currently inactive: its scheduled invocation is commented out, and its configuration prefix does not match the YAML nesting |
| Automated tests for idempotency, state transitions, and rate limiting | Planned; the current test suite contains only a Spring application context-load test |
| Transactional outbox and SAGA | Planned; payment/order services still contain event-publishing TODOs |
| HMAC-SHA256 webhooks with retry/backoff | Planned; webhook configuration/event entities exist, but delivery and retry behavior is not implemented |
| Merchant settlements and payout calculation | Planned; settlement entities exist, but calculation and payout flows are not implemented |
| Dead-letter handling | Planned; a DLQ entity exists, but processing and recovery flows are not implemented |
| Split into services | Planned; the current architecture is a modular monolith |
| Load testing against a 10,000 TPS design target | Planned; this target has not been measured |

### Known implementation gaps

- The callback simulator is not scheduled to run, and its `payment.simulator` properties are currently nested beneath `spring` in `application.yaml`, so they do not bind to the simulator's configured prefix.
- Idempotency response replay is a filter-level prototype, not a complete guarantee spanning database transactions, processor calls, and event publication.
- Webhook, settlement, and dead-letter support is currently represented by entities rather than working workflows.
- The API surface is incomplete: some order service operations and payment/refund operations do not yet have corresponding HTTP endpoints.
- The API-key revoke mapping currently does not declare the `keyId` path variable expected by its handler.
- There are no migration scripts or focused tests for the payment-critical behavior yet.

## What this project is exploring

- Idempotency and duplicate-request handling
- Payment state machines and transition auditing
- Failure recovery and partial failures
- Transaction consistency and eventual consistency patterns
- Safer handling and tokenization of card data
- Load testing and validating performance claims with measurements

## Technology stack

- Java 25
- Spring Boot 4.1.1, Spring MVC, Spring Security, Spring Data JPA
- PostgreSQL and Redis
- JWT and BCrypt
- MapStruct and Lombok
- Maven

## Project layout

```text
src/main/java/com/personalproject/razorpay/
├── common/       Shared configuration, exceptions, idempotency, rate limiting
├── merchant/     Merchant authentication, API keys, and security filters
├── payment/      Orders, payments, adapters, processors, and state machine
├── vault/        Card tokenization and encrypted card storage
└── operations/   Webhook, settlement, and DLQ entity scaffolding
```

## Getting started

### Prerequisites

- JDK 25
- PostgreSQL
- Redis

Create a PostgreSQL database named `razorpayDB` (or configure a different URL), and make PostgreSQL and Redis available before starting the application.

### Configure

The application reads database and Redis settings from these environment variables:

| Variable | Default |
|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/razorpayDB` |
| `DB_USER` | `postgres` |
| `DB_PASS` | Local development default in `application.yaml` |
| `REDIS_HOST` | `localhost` |
| `REDIS_PORT` | `6379` |
| `REDIS_PASSWORD` | empty |

Set `JWT_SECRET_KEY` and `VAULT_MASTER_KEY` to strong, private values before running. `VAULT_MASTER_KEY` must be Base64-encoded key material of a valid AES length (16, 24, or 32 bytes); `JWT_SECRET_KEY` must be at least 32 bytes for the configured HMAC algorithm. Spring Boot's environment-variable binding lets these override `jwt.secret-key` and `vault.master-key`.

The checked-in configuration contains local development defaults, including secrets and a database password. **Do not rely on those defaults outside a disposable local environment, and never commit real credentials or keys.** Store secrets in an appropriate secret manager for any shared or deployed environment.

The application currently uses Hibernate schema auto-update and SQL logging in its checked-in configuration. Treat these as development settings, not a production database migration strategy.

### Run

From the repository root:

```bash
# macOS/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

The service uses the `/v1` API prefix. For example, merchant signup and login are available at `POST /v1/auth/signup` and `POST /v1/auth/login`. Merchant API-key management is under `/v1/merchants/api-keys`; order, payment, and vault routes are under `/v1/orders`, `/v1/payments`, and `/v1/vault` and require API-key authentication.

API-key-authenticated requests use HTTP Basic credentials where the username is the API key ID and the password is its secret. Send idempotent write requests with an `X-Idempotency-Key` header. The API key secret is returned only when the key is created or rotated; store it securely.

## Tests

Run the current Maven test suite:

```bash
# macOS/Linux
./mvnw test

# Windows
mvnw.cmd test
```

At present, this suite only checks that the Spring application context loads. Idempotency, state transitions, rate limiting, and failure scenarios still need dedicated automated tests.

## Non-goals and safety

This repository is for learning and experimentation. Do not send real card data, connect real payment credentials, or use it to move money. Encryption and tokenization code alone do not provide PCI DSS compliance; compliance requires broader technical, operational, and organizational controls.
