# Java Ecosystem ☕️
<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring--Boot-3.3+-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Architecture-Hexagonal%20|%20Clean-blue?style=for-the-badge" alt="Architecture">
  <img src="https://img.shields.io/badge/Infrastructure-Docker%20|%20K8s%20|%20Terraform-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Infra">
</p>

---

### 🌐 Global Engineering | Engenharia Global

**EN:** Mission-critical technical ecosystem developed for high-frequency transactions and distributed systems. This repository demonstrates the integration between **Low-Level Software Engineering** and **Enterprise Scalability**, utilizing Java 21 Virtual Threads and a full-stack resilience layer.

**PT:** Ecossistema técnico de missão crítica desenvolvido para transações de alta frequência e sistemas distribuídos. Este repositório demonstra a integração entre **Engenharia de Software de Baixo Nível** e **Escalabilidade Enterprise**, utilizando Java 21 Virtual Threads e uma camada completa de resiliência.

---

## 🏗️ Technical Pillars | Pilares Técnicos

### 🧠 Computer Science & Low-Level | Ciência da Computação e Baixo Nível
- **EN:** **Big O Optimization:** Algorithms validated for $O(1)$ or $O(log N)$. **Bitwise Engineering:** Manual flag management and memory optimization using Bitwise Operators to bypass GC overhead in critical paths.
- **PT:** **Otimização Big O:** Algoritmos validados para $O(1)$ ou $O(log N)$. **Engenharia Bitwise:** Gerenciamento manual de flags e otimização de memória usando operadores Bitwise para evitar overhead de GC em caminhos críticos.

### 🛡️ DevSecOps & Cloud Native | Segurança e Nuvem
- **EN:** **IaC (Terraform):** AWS/Azure provisioning logic. **LocalStack:** Full cloud emulation (S3/SQS). **Security:** Automated SAST/DAST scanning via Snyk & SonarCloud.
- **PT:** **IaC (Terraform):** Lógica de provisionamento AWS/Azure. **LocalStack:** Emulação completa de nuvem (S3/SQS). **Segurança:** Scan automatizado SAST/DAST via Snyk e SonarCloud.

### 🛡️ Resilience & Fault Tolerance | Resiliência
- **EN:** Implementation of **Circuit Breaker, Retry, and Rate Limiter** via **Resilience4j**. **Self-Healing** capabilities through health checks and automated container recovery.
- **PT:** Implementação de **Circuit Breaker, Retry e Rate Limiter** via **Resilience4j**. Capacidade de **Auto-cura** através de health checks e recuperação automatizada de containers.

### 📊 Data Integrity & Persistence | Dados e Persistência
- **EN:** **ACID Transactions:** Financial rigor with **PostgreSQL/MySQL**. **Flyway/Liquibase:** Database versioning (Zero manual DB changes). **Hibernate Envers:** Full audit trail for data changes.
- **PT:** **Transações ACID:** Rigor financeiro com **PostgreSQL/MySQL**. **Flyway/Liquibase:** Versionamento de banco de dados (Zero mudanças manuais). **Hibernate Envers:** Trilha de auditoria completa para alterações de dados.

---

## 📂 Project Architecture | Estrutura do Ecossistema

### **I. Infrastructure & Security (`/infrastructure`)**
* **`/terraform`**: Cloud provisioning manifests (AWS/Azure).
* **`/kubernetes`**: Orchestration via K8s (Deployments/Services).
* **`/observability`**: Advanced monitoring with `HealthCheckProvider.java` and `LogMasker.java`.
* **`/persistence`**: Intelligent storage management with `SchemaVersionChecker.java`.
* **`/security`**: 
    * **`/audit`**: Immutable chain using `AuditChainManager.java` (SHA-256).
    * **`/hashing`**: `TransactionHasher.java` for financial integrity.
    * **`/validation`**: Active integrity checks via `IntegrityChecker.java`.

### **II. Distributed Services (`/services`)**
* **`/api-gateway`**: Centralized entry point with `TraceabilityFilter.java` and `SecuritySanitizationFilter.java`.
* **`/audit-service`**: High-security logging via `VaultSentinel.java` and `ChainLinker.java`.
* **`/discovery-server`**: Service registry using **Netflix Eureka** configuration.
* **`/message-broker`**: Asynchronous orchestration with `RabbitHoleConfig.java` (**RabbitMQ**).
* **`/conversion-service`**: Specialized financial math engine (`MonetaryEngine.java`).

### **III. Core Engine (`src/main/java/org/engine`)**
* **`/audit`**: Real-time logging engine with `AuditLogger.java` and `AuditRepository.java`.
* **`/core`**:
    * **`/context`**: Global traceability via `CorrelationIdContext.java`.
    * **`/idempotency`**: Protection against duplicate transactions (`IdempotencyEngine.java`).
    * **`/resilience`**: Fault tolerance via `CircuitBreaker.java` and `RetryManager.java`.
    * **`/throttling`**: Active traffic control with `FlowController.java`.
    * **`/validation`**: High-performance schema and constraint validation.
    * **`/research`**: Bitwise manipulation and memory optimization laboratory.
    * **`FinanceEngine.java`**: Main execution engine for heavy financial processing.

### **IV. Domain & Domain Events (`/domain`)**
* **`/events`**: Decoupled architecture using `DomainEvent.java` and `EventBridgeMediator.java`.
* **`/records`**: Immutable data structures via `TransactionRecord.java` and `MonetaryValue.java`.
* **`/rules`**: Advanced business logic like `InternationalTax.java`.
* **`/strategy`**: Dynamic algorithm selection for complex scenarios.

---

## 🛠️ Full Tech Stack | Ferramentas de Elite

| Category / Categoria | Technology / Tecnologia |
| :--- | :--- |
| **Runtime** | Java 21 (Virtual Threads), Spring Boot 3.3+, Quarkus (GraalVM Native) |
| **Data / Persistence** | PostgreSQL, MySQL, Redis, Flyway / Liquibase |
| **Messaging / Stream** | Apache Kafka, RabbitMQ |
| **Testing** | JUnit 5, Mockito, Testcontainers, Jacoco |
| **Infrastructure** | Docker, K8s, Terraform, LocalStack (AWS) |
| **Observability** | Prometheus, Grafana, Micrometer, ELK Stack |
| **Security / CI/CD** | Snyk, SonarCloud, GitHub Actions, Vault |

---

## 👨‍💻 Author | Autoria
**Kauan Oliveira** - *Systems & Security Engineer*

**EN:** Professional specialized in distributed systems, low-level performance, and high-availability backend architecture.  
**PT:** Profissional especializado em sistemas distribuídos, performance de baixo nível e arquitetura backend de alta disponibilidade.

### 📩 Contact / Contato
- **LinkedIn:** [Kauan Oliveira](https://www.linkedin.com/in/kauan-oliveira-324264378/)
- **GitHub:** [kauandias747474-hue](https://github.com/kauandias747474-hue)

---

## 🚀 How to Run | Como Executar

```bash
# 1. Clone the repository | Clone o repositório
git clone [https://github.com/kauandias747474-hue/java-backend-architecture](https://github.com/kauandias747474-hue/java-backend-architecture)

# 2. Deploy the entire ecosystem (App, DB, Redis, Kafka, LocalStack)
# 2. Suba todo o ecossistema (App, DB, Redis, Kafka, LocalStack)
docker-compose up -d

# 3. Access API Documentation | Acesse a documentação da API
# http://localhost:8080/swagger-ui.html
