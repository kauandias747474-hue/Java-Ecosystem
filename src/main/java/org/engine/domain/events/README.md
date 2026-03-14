# 📡 Domain Events Engine

## 🇧🇷 Português | 🇺🇸 English

---

### 🧠 Visão Geral | Overview
Este módulo implementa o padrão de **Eventos de Domínio**, permitindo que o sistema opere de forma reativa e totalmente desacoplada. Em vez de uma lógica de negócio chamar diretamente outra (acoplamento forte), ela apenas anuncia ao ecossistema que algo importante aconteceu.
*This module implements the **Domain Events** pattern, enabling a reactive and decoupled architecture. Instead of business logic calling another service directly, it simply announces to the ecosystem that a significant event has occurred.*

---

### 🚀 Funcionalidades | Key Features

* **Desacoplamento (Decoupling):** O `FinanceEngine` dispara um `TransactionCompletedEvent` sem precisar conhecer os destinatários. / *The engine fires events without knowing who the consumers are.*
* **Extensibilidade (Extensibility):** Permite adicionar novas funções (ex: envio de e-mail, alertas, notificações) apenas "ouvindo" os eventos, sem alterar o código core. / *Add new features by simply listening to events, without touching the core code.*
* **Auditabilidade (Auditability):** Fornece um rastro histórico de fatos de negócio que ocorreram, facilitando o rastreio de fluxos complexos. / *Creates a historical trail of business facts for complex flow tracking.*

---

### ☕ Conceitos Java & Programação Aplicados

* **Observer Pattern / Pub-Sub:** Implementação do padrão observador onde múltiplos "Listeners" podem reagir ao mesmo evento de forma independente.
* **Spring ApplicationEventPublisher:** Uso da infraestrutura nativa do Spring para despacho de eventos síncronos ou assíncronos dentro da mesma JVM.
* **Transaction-Bound Events:** Uso de `@TransactionalEventListener`. Isso garante que o evento (como enviar um e-mail de confirmação) só seja disparado se a transação no banco de dados for concluída com sucesso.



---

### ⚙️ Fluxo de Exemplo | Execution Workflow

1.  **Trigger:** O `FinanceEngine` conclui um cálculo monetário.
2.  **Dispatch:** Um evento `PaymentProcessedEvent` é publicado no contexto da aplicação.
3.  **Action (Audit):** O `TaskLogger` captura o evento e registra no log de auditoria.
4.  **Action (Schedule):** O `TaskScheduler` captura o mesmo evento e agenda a próxima tarefa automática.

---
