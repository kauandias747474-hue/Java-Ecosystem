# 🚪 API Gateway Service

## 🇧🇷 Português

### 🛡️ Pilar: Segurança de Borda & Roteamento Centralizado
O **API Gateway** atua como o *Single Point of Entry* (Ponto Único de Entrada) do ecossistema. Ele funciona como um orquestrador de tráfego que blinda os motores de cálculo, garantindo que apenas requisições legítimas, autenticadas e "limpas" cheguem à camada de negócio.



#### ⚙️ Responsabilidades Core
* **Roteamento Dinâmico:** Integração nativa com *Service Discovery* para localizar instâncias sem IPs estáticos.
* **Terminação TLS/SSL:** Centralização de certificados para comunicação criptografada (HTTPS).
* **Autenticação Centralizada:** Validação de tokens (JWT/OAuth2) na borda.
* **Observabilidade:** Injeção de headers para rastreio distribuído.

#### 🛡️ Hardening & Security Features
| Feature | Mecanismo | Benefício |
| :--- | :--- | :--- |
| **Rate Limiting** | Algoritmo Token Bucket | Proteção anti-DoS e controle de cota por IP. |
| **Request Sanitization** | Filtro de Mutação | Bloqueio de SQL Injection e XSS na borda. |
| **Correlation ID** | Injeção de UUID | Rastreabilidade total via `ContextInterceptor`. |

---

## 🇺🇸 English

### 🛡️ Pillar: Edge Security & Centralized Routing
The **API Gateway** acts as the *Single Point of Entry* for the ecosystem. It functions as a traffic orchestrator that shields the calculation engines, ensuring only legitimate, authenticated, and "sanitized" requests reach the business layer.

#### ⚙️ Core Responsibilities
* **Dynamic Routing:** Native integration with *Service Discovery* to locate instances without static IPs.
* **TLS/SSL Termination:** Centralized certificate management for encrypted communication (HTTPS).
* **Centralized Authentication:** Edge-level token validation (JWT/OAuth2).
* **Observability:** Header injection for distributed tracing.

#### 🛡️ Hardening & Security Features
| Feature | Mechanism | Benefit |
| :--- | :--- | :--- |
| **Rate Limiting** | Token Bucket Algorithm | anti-DoS protection and per-IP quota control. |
| **Request Sanitization** | Mutation Filter | Block SQL Injection and XSS at the edge. |
| **Correlation ID** | UUID Injection | Full traceability via `ContextInterceptor`. |

---
## 📘 Fundamentação Teórica & Design Patterns

A implementação deste API Gateway não é apenas uma escolha tecnológica, mas uma aplicação de princípios consolidados na engenharia de software moderna.

### 📚 Influências Literárias e Arquiteturais

1.  **"Building Microservices" (Sam Newman):** O Gateway aplica o padrão **BFF (Backend for Frontends)** e o conceito de **Edge Enclosure**. Newman defende que preocupações transversais (*cross-cutting concerns*) como autenticação e logging não devem poluir a lógica de negócio dos microserviços.
    
2.  **"Clean Architecture" (Uncle Bob):** Ao isolar a sanitização e o roteamento na borda, mantemos o *Core* do sistema (motores de cálculo) independente de detalhes de entrega (HTTP, JSON, Headers). Isso garante que o motor de cálculo "não saiba" que está na web, facilitando testes e portabilidade.

3.  **"Release It!" (Michael Nygard):** A implementação de **Rate Limiting** e **Circuit Breakers** no Gateway atua como um "Anteparo" (*Bulkhead*), garantindo que uma falha ou sobrecarga em um serviço não derrube todo o ecossistema.



---

### ☕ Conceitos Java Aplicados

* **Programação Reativa (Project Reactor):** Utilizamos `Mono<Void>` e `Flux`, permitindo que o Gateway processe milhares de requisições simultâneas com um overhead de memória mínimo (Non-blocking I/O).
* **Ordered Filters:** Implementamos a interface `Ordered` do Spring para garantir que a **Segurança (Sanitização)** ocorra antes de qualquer tentativa de **Roteamento**, seguindo o princípio de *Fail-Fast*.
* **Mutabilidade Controlada:** O uso de `exchange.mutate()` segue as boas práticas de imutabilidade do Java moderno, criando uma nova instância da requisição com os headers injetados (como o `Correlation ID`) sem alterar o estado original de forma insegura.

---

### 🔄 Fluxo de Execução (Workflow)

1.  **Ingress:** A requisição chega via TLS.
2.  **Pre-Filter (Identification):** O `CorrelationIdFilter` gera o ID único.
3.  **Security Filter (Guard):** O `SanitizationFilter` valida o payload contra ataques.
4.  **Routing:** O Gateway consulta o *Service Discovery* e encaminha a requisição "limpa" para o motor de cálculo.
5.  **Post-Filter:** O tempo de resposta é logado e os headers sensíveis de infraestrutura são removidos antes de retornar ao cliente.

---

## 🇺🇸 Theoretical Foundation & Design Patterns

### 📚 Literary & Architectural Influences

1.  **"Building Microservices" (Sam Newman):** The Gateway applies the **Edge Enclosure** concept. Newman argues that cross-cutting concerns (Auth, Logging) should not pollute microservices' business logic.
    
2.  **"Clean Architecture" (Uncle Bob):** By isolating sanitization at the edge, we keep the system's *Core* independent of delivery details. This ensures the calculation engine remains "unaware" of web-specific overhead.

### ☕ Java Concepts in Action

* **Reactive Programming:** Using `Mono` and `Flux` for non-blocking I/O, allowing high throughput with low resource footprint.
* **Fail-Fast Filters:** Execution order ensures **Security Filters** run before **Routing**, protecting internal resources immediately.
