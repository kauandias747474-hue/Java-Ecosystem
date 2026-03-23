# 🗄️ Domain Records & Persistence

## 🇧🇷 Português

O módulo **records** é responsável pela representação imutável dos dados e pelo histórico de estado do sistema.

### 📁 Componentes Principais
* **TransactionRecord.java**: Representa uma "foto" imutável de uma transação financeira ou de uma tarefa processada. Uma vez criado, um Record não pode ser alterado, garantindo a integridade dos dados.

### 🛠️ Por que usar Records?
* **Segurança de Thread**: Como os dados são imutáveis, eles podem ser lidos por vários motores ao mesmo tempo (Finance, Scheduler, Processor) sem risco de corrupção.
* **Integridade Financeira**: Garante que o valor calculado pelo `FinanceEngine` seja exatamente o mesmo que será salvo pelo `TaskProcessor`.
* **Compliance**: Ideal para sistemas que exigem auditoria, onde cada mudança de estado deve ser documentada e preservada.

---

## 🇺🇸 English

The **records** module is responsible for the immutable representation of data and the system's state history.

### 📁 Core Components
* **TransactionRecord.java**: Represents an immutable "snapshot" of a financial transaction or a processed task. Once created, a Record cannot be modified, ensuring data integrity.

### 🛠️ Why use Records?
* **Thread Safety**: Since the data is immutable, it can be read by multiple engines simultaneously (Finance, Scheduler, Processor) without the risk of corruption.
* **Financial Integrity**: Ensures that the value calculated by the `FinanceEngine` is exactly the same as what will be saved by the `TaskProcessor`.
* **Compliance**: Ideal for systems requiring auditing, where every state change must be documented and preserved.
