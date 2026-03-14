# 📡 Domain Events Engine (Reactive Core)

## 🇧🇷 Português | 🇺🇸 English

---

### 🧠 Visão Geral | Overview
O **Domain Events Engine** é o sistema circulatório da nossa arquitetura. Ele implementa o padrão de **Event-Driven Design**, permitindo que o sistema reaja a mudanças de estado sem criar dependências circulares. Aqui, o motor financeiro não "comanda" ações externas; ele apenas declara: *"Um fato de negócio ocorreu"*, e o ecossistema reage de forma inteligente e assíncrona.

*The **Domain Events Engine** is the circulatory system of our architecture. It implements the **Event-Driven Design** pattern, enabling a reactive and decoupled architecture where the core engine declares business facts, and the ecosystem reacts intelligently and asynchronously.*

---

### 🚀 Funcionalidades de Alta Engenharia | High-End Features

* **Integridade Transacional (Transactional Integrity):** Eventos são vinculados ao ciclo de vida da transação do banco de dados (PostgreSQL), garantindo que mensagens só sejam disparadas após o `COMMIT` bem-sucedido. 
* **Isolamento de Efeitos Colaterais (Side-Effect Isolation):** Falhas em disparos de periféricos (e-mail, notificações) não interrompem o fluxo de conversão principal.
* **Consistência Eventual (Eventual Consistency):** Garante a sincronia entre o motor de cálculo e os serviços de auditoria via RabbitMQ de forma resiliente.

---

### ☕ Conceitos de Programação & Stack Moderna | Programming Concepts & Modern Stack

* **Java 21 Records:** Uso de `records` para definição de eventos, garantindo **Imutabilidade Total** (Thread-Safety) e redução de boilerplate.
* **Patterns Observer & Mediator:** Desacoplamento total da lógica de domínio em relação à infraestrutura de mensageria externa.
* **Spring Boot 3.4+ (ApplicationEvents):** Utilização da infraestrutura nativa para publicação de eventos com overhead quase zero.
* **Static Factory Methods:** Encapsulamento da criação de eventos, garantindo que `UUIDs` e `Timestamps` sejam gerados no momento exato do fato.



---

### ⚙️ Fluxo de Execução | Execution Workflow

1.  **Fact Creation:** O `FinanceEngine` processa a conversão e gera um `CurrencyConvertedEvent`. / *The `FinanceEngine` processes the conversion and generates a `CurrencyConvertedEvent`.*
2.  **Internal Dispatch:** O `FinanceEventPublisher` dispara o evento dentro da JVM. / *The `FinanceEventPublisher` dispatches the event within the JVM.*
3.  **Transactional Gate:** O sistema aguarda o sucesso da persistência no PostgreSQL. / *The system waits for successful persistence in PostgreSQL.*
4.  **External Bridge:** O `EventBridgeMediator` captura o evento interno e o despacha para o **RabbitMQ**. / *The `EventBridgeMediator` captures the internal event and dispatches it to **RabbitMQ**.*



---

### 💻 Implementação Técnica | Technical Implementation

| Class | Responsibility | Pattern |
| :--- | :--- | :--- |
| **`DomainEvent.java`** | Representar o fato ocorrido / *Represent the occurred fact* | **Value Object (Record)** |
| **`FinanceEventPublisher.java`** | Anunciar o evento / *Announce the event* | **Inversion of Control (IoC)** |
| **`EventBridgeMediator.java`** | Ponte interna -> externa / *Internal to External Bridge* | **Mediator Pattern / AOP** |

---

### 🛡️ Por que esta abordagem? | Why this approach?

**1. Resiliência Financeira (Financial Resilience):** Ao utilizar `@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)`, evito disparar notificações de sucesso para transações que falharam no banco de dados.
*By using Transactional Event Listeners, I avoid firing success notifications for transactions that failed in the database.*

**2. Desacoplamento de Infraestrutura (Infrastructure Decoupling):** O motor de cálculo não sabe que o RabbitMQ existe. Se decidirmos trocar o Broker por Kafka ou AWS SNS, alteramos apenas o Mediator, mantendo o Core intacto.
*The calculation engine is unaware of RabbitMQ. Switching to Kafka or AWS SNS only requires changing the Mediator class, leaving the Core untouched.*

**3. Manutenibilidade (Maintainability):** Código limpo, testável e aderente aos princípios **SOLID**, facilitando o uso de Mocks em testes unitários.
*Clean, testable code adhering to **SOLID** principles, simplifying the use of Mocks in unit testing.*

---

### 🚀 Como Validar | How to Validate

1.  **Monitoramento de Logs:** Observe o log do `LogMasker` rastreando o `Correlation-ID` desde o disparo até o Broker.
2.  **Teste de Integridade:** Simule um erro de persistência e confirme que o Mediator **não** enviou a mensagem ao RabbitMQ.
3.  **Audit Trace:** Verifique se o hash gerado pelo Audit Service corresponde aos dados do `DomainEvent`.
