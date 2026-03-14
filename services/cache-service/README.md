# ⚡ Redis Cache Infrastructure

## 🇧🇷 Português | 🇺🇸 English

---

### 🚀 Pilar: Performance de Baixa Latência & Consistência Distribuída
**Pillar: Low-Latency Performance & Distributed Consistency**

Configuração do ecossistema **Redis 7** para otimização de leitura e orquestração de concorrência. O Redis atua como a memória de curto prazo do sistema, impedindo sobrecarga no PostgreSQL e garantindo a atomicidade de transações paralelas.
*Redis 7 configuration for read optimization and concurrency orchestration. Redis acts as the system's short-term memory, preventing PostgreSQL overload and ensuring atomicity for parallel transactions.*

---

### 🛡️ Estratégias de Cache | Cache Strategies

* **Look-aside Cache:** Redução de carga no PostgreSQL 15, servindo dados frequentes diretamente da memória. / *Reducing PostgreSQL load by serving hot data from memory.*
* **Distributed Lock (Atomic Shield):** Prevenção de *Race Conditions* em transações financeiras concorrentes utilizando o protocolo Redlock. / *Preventing race conditions in concurrent financial transactions using Redlock.*
* **TTL (Time-To-Live) Policy:** Configuração rigorosa de tempo de vida para evitar dados obsoletos (*stale data*) e garantir a renovação de cotações. / *Strict TTL settings to prevent stale data and ensure quote updates.*

---

### ☕ Conceitos Java & Programação Aplicados

* **Lettuce Driver:** Utilização de um driver reativo e thread-safe que permite multiplexação de conexões, otimizando o uso de recursos.
* **Redisson:** Implementação do objeto `RLock`. Diferente do `synchronized` do Java (que se limita a uma JVM), o Redisson gerencia o lock entre múltiplos microserviços.
* **Serialization Management:** Uso de `Jackson2JsonRedisSerializer` para garantir que os objetos Java sejam armazenados em formato JSON legível e portável.



---

### ⚙️ Funcionamento do Código | How it Works

O serviço utiliza o padrão **Distributed Lock** para garantir que, durante uma conversão de moeda, o saldo do usuário não seja alterado por outro processo simultâneo (Double Spending Protection).

1.  **Adição de Lock:** Antes de processar, o serviço "trava" o ID do recurso no Redis.
2.  **Verificação de Cache:** O sistema busca o saldo no Redis (Look-aside).
3.  **Processamento:** A lógica de negócio é executada com exclusividade.
4.  **Liberação:** O lock é liberado e o cache é invalidado para garantir consistência.



---
