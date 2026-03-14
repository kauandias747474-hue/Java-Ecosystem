# 🔍 Observability Module | Módulo de Observabilidade

## 🇧🇷 Português | 🇺🇸 English

---

### 🧠 Visão Geral | Overview
Este módulo atua como o **"sistema nervoso"** da infraestrutura. Ele garante que cada operação seja rastreável, que dados sensíveis nunca vazem nos logs e que o orquestrador (Kubernetes/Docker) saiba exatamente quando o serviço está pronto para receber tráfego.
*This module acts as the **"nervous system"** of the infrastructure. It ensures every operation is traceable, sensitive data never leaks into logs, and the orchestrator knows exactly when the service is ready for traffic.*

---

### 🛠️ Componentes Principais | Key Components

* **OpenTelemetryConfig.java:** Configura o rastreamento distribuído (Distributed Tracing). Ele gera e propaga `Trace IDs` e `Span IDs` entre microsserviços. / *Configures distributed tracing, generating and propagating IDs across the ecosystem.*
* **LogMasker.java:** Um interceptor de alta performance que utiliza Expressões Regulares (Regex) para identificar e mascarar PII (*Personally Identifiable Information*), como CPFs ou Cartões de Crédito. / *A high-performance interceptor that masks sensitive data (PII) in logs.*
* **HealthCheckProvider.java:** Implementa sondas de **Liveness** (o processo está vivo?) e **Readiness** (as dependências como Redis e RabbitMQ estão prontas?). / *Implements Liveness and Readiness probes for operational resilience.*

---

### ☕ Conceitos Java & Programação Aplicados

* **Logback/Log4j2 Custom Layouts:** O `LogMasker` não é apenas um `System.out`. Ele estende as classes de layout do Logback para garantir que o mascaramento ocorra **antes** do log ser gravado no disco ou enviado para o ELK Stack.
* **Context Propagation:** Uso da API do OpenTelemetry para garantir que o contexto de execução seja passado entre threads, mesmo em operações assíncronas (CompletableFuture).
* **Spring Boot Actuator:** Customização de `HealthIndicators` para criar métricas de saúde que vão além do simples "UP", verificando latência de banco e conexão com brokers.



---

### ⚙️ Funcionamento do Código | How it Works

1.  **Trace Flow:** Quando uma requisição entra no Gateway, o OpenTelemetry inicia um `Root Span`. Se o serviço de Conversão é chamado, o ID é passado via header. Isso permite ver a "árvore" da requisição.
2.  **Privacy Shield (LogMasking):** Antes de qualquer linha de log ser escrita, o `LogMasker` aplica um filtro: `\d{3}\.\d{3}\.\d{3}-\d{2}` vira `***.***.***-**`.
3.  **Self-Healing:** O Kubernetes consulta o `/health/readiness`. Se o Redis cair, o `HealthCheckProvider` retorna `503`, e o orquestrador para de enviar tráfego para essa instância até que ela se recupere.



---
