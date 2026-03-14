# 🚪 API Gateway Service

## 🇧🇷 Português | 🇺🇸 English

---

### 🛡️ Pilar: Segurança de Borda & Roteamento Centralizado
**Pillar: Edge Security & Centralized Routing**

O **API Gateway** atua como o *Single Point of Entry* do ecossistema, funcionando como um orquestrador de tráfego que blinda os motores de cálculo.
*The **API Gateway** acts as the Single Point of Entry for the ecosystem, functioning as a traffic orchestrator that shields the calculation engines.*

---

### ⚙️ Responsabilidades Core | Core Responsibilities

* **Roteamento Dinâmico (Dynamic Routing):** Integração nativa com *Service Discovery* (Eureka/Consul) para localizar instâncias sem IPs estáticos.
* **Terminação TLS/SSL (TLS Termination):** Centralização de certificados para comunicação criptografada (HTTPS).
* **Autenticação Centralizada (Auth Edge):** Validação de tokens (JWT/OAuth2) na borda.
* **Observabilidade (Observability):** Injeção de headers para rastreio distribuído.

---

### 🛡️ Hardening & Security Features

| Feature | Mecanismo / Mechanism | Benefício / Benefit |
| :--- | :--- | :--- |
| **Rate Limiting** | Token Bucket Algorithm | Proteção anti-DoS e controle de cota por IP. / Anti-DoS protection. |
| **Request Sanitization** | Mutation Filter | Bloqueio de SQL Injection e XSS na borda. / Block attacks at the edge. |
| **Correlation ID** | UUID Injection | Rastreabilidade total via `ContextInterceptor`. / Full traceability. |

---

### 📘 Fundamentação Teórica | Theoretical Foundation

#### 📚 Influências Literárias | Literary Influences
1.  **"Building Microservices" (Sam Newman):** Aplicação do padrão **Edge Enclosure**. Preocupações transversais não poluem a lógica de negócio. / *Cross-cutting concerns do not pollute business logic.*
2.  **"Clean Architecture" (Uncle Bob):** Mantém o **Core** (motores de cálculo) independente de detalhes de entrega (HTTP/JSON). / *Keeps the Core independent of delivery details.*
3.  **"Release It!" (Michael Nygard):** Implementação de **Bulkheads** (anteparos) para evitar o efeito cascata. / *Implementation of bulkheads to prevent cascading failures.*

#### ☕ Conceitos Java Aplicados | Java Concepts Applied
* **Project Reactor:** Uso de `Mono<Void>` e `Flux` para processamento não-bloqueante (Non-blocking I/O).
* **Ordered Filters:** Interface `Ordered` garantindo que a **Segurança** preceda o **Roteamento** (Fail-Fast).
* **Imutabilidade:** Uso de `exchange.mutate()` para manipulação segura de estados da requisição.

---

### 🔄 Fluxo de Execução | Execution Workflow



1.  **Ingress:** Requisição chega via TLS. / *Request arrives via TLS.*
2.  **Pre-Filter (Identification):** `TraceabilityFilter` gera o ID único. / *Generates unique ID.*
3.  **Security Filter (Guard):** `SecuritySanitizationFilter` valida o payload. / *Validates payload.*
4.  **Routing:** Gateway consulta o Service Discovery e encaminha a requisição. / *Routes to microservice.*
5.  **Post-Filter:** Remoção de headers sensíveis e log de performance. / *Sensitive header stripping.*

---

### 🛠️ Roadmap de Desenvolvimento | Development Roadmap

#### 1. Global Traceability Filter (`TraceabilityFilter.java`)
Implementação de um filtro de alta precedência que injeta um `X-Correlation-ID`. Permite agrupamento de logs distribuídos em toda a malha de serviços.
*Injects an X-Correlation-ID for log aggregation across the entire service mesh.*

#### 2. Intelligent Guard (`SecuritySanitizationFilter.java`)
Filtro customizado (WAF minimalista) que analisa o `POST Body` em busca de assinaturas de ataques como SQLi e XSS.
*Minimalist WAF scanning POST bodies for SQLi and XSS signatures.*

#### 3. Service Discovery Routing (`GatewayRoutesConfig.java`)
Configuração de rotas dinâmicas utilizando `lb://` (Load Balancer), garantindo transparência de localização e escalonamento horizontal.
*Dynamic routing using load balancer protocols for location transparency.*

---

## 🚀 Como Executar | How to Run

1.  Certifique-se de ter o **Discovery Server** (Eureka) rodando.
2.  Clone o repositório e execute:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
3.  O Gateway estará disponível em `http://localhost:8080`.
