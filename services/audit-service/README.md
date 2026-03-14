# ⛓️ Audit Service (Immutable Chain)

## 🇧🇷 Português | 🇺🇸 English

---

### 🛡️ Pilar: Integridade Criptográfica
**Pillar: Cryptographic Integrity**

Este serviço é responsável por garantir a imutabilidade e a prova de não-repúdio de todas as operações sensíveis do sistema. Ele não apenas armazena logs, mas os encadeia matematicamente.
*This service ensures the immutability and non-repudiation of all sensitive system operations. It doesn't just store logs; it mathematically chains them.*

---

### 🔒 Mecanismos de Integridade | Integrity Mechanisms

* **SHA-256 Chaining:** Cada novo registro de auditoria incorpora o Hash do registro anterior. Se um bit mudar no passado, toda a cadeia futura é invalidada.
    *Each log entry incorporates the previous entry's Hash. If a past bit changes, the entire future chain is invalidated.*
* **Anti-Tampering (IntegrityChecker):** Um processo em background verifica periodicamente a integridade da corrente. Se `Hash(n-1) + Data(n) != Hash(n)`, um alerta de segurança é disparado.
    *A background process periodically verifies the chain. If the math doesn't match, a security alert is triggered.*
* **Async Logging (RabbitMQ):** A auditoria é disparada via eventos assíncronos, garantindo que a segurança não se torne um gargalo de performance para os motores de cálculo.
    *Audit logs are triggered via asynchronous events, ensuring security doesn't become a performance bottleneck.*



---

#### ☕ Conceitos Java & Segurança | Java & Security Concepts
* **`MessageDigest` (Java Security):** Utilização da API nativa de alta performance para geração de hashes SHA-256.
* **Idempotência:** Consumidores RabbitMQ projetados para evitar duplicidade de registros na cadeia, mesmo em casos de retry.
* **Imutabilidade de Objetos:** Uso de *Java Records* para garantir que o registro de auditoria não seja alterado após ser criado na memória.

---

### 🔄 Fluxo de Auditoria | Audit Workflow

1.  **Trigger:** Um evento ocorre no motor de cálculo. / *An event occurs in the calculation engine.*
2.  **Queue:** O evento é enviado para o RabbitMQ com o `Correlation-ID`. / *Event sent to RabbitMQ with Correlation-ID.*
3.  **Hashing:** O serviço de Auditoria busca o Hash do último registro no banco e calcula o novo Hash. / *Audit service fetches the last hash and calculates the new one.*
4.  **Persistence:** O novo bloco é salvo. A corrente está protegida. / *New block is saved. The chain is secured.*



---

### 🛠️ Roadmap de Implementação | Development Roadmap

#### 1. Integrity Chain Engine (`ChainLinker.java`)
O coração do serviço. Responsável por garantir que a operação de `Hash(n) = SHA256(Hash(n-1) + Payload)` seja atômica e sequencial.
*The core engine ensuring that the hashing operation remains atomic and sequential.*

#### 2. Async Consumer (`AuditEventListener.java`)
Listener de alta performance que processa as mensagens da fila e as encaminha para o motor de encadeamento.
*High-performance listener processing queue messages for the chaining engine.*

#### 3. Integrity Checker (`VaultSentinel.java`)
Um serviço agendado (Cron) que revalida a corrente inteira para detectar tentativas de alteração direta no banco de dados (SQL Injection ou acesso manual).
*A scheduled service that revalidates the entire chain to detect direct database tampering.*

---

## 🚀 Como Validar | How to Validate

1.  Insira 10 registros de teste.
2.  Tente alterar manualmente o texto de um registro no Banco de Dados.
3.  Execute o `VaultSentinel` e observe o serviço detectando a quebra de integridade e bloqueando novas operações.
