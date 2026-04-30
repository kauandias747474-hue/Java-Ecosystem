#  Business Rules Engine: International Tax (Case Study)

> **[PT-BR]** Proof of Concept (PoC) focada em desacoplamento de regras fiscais complexas através de Clean Code e Design Patterns.
>
> **[EN]** Proof of Concept (PoC) focused on decoupling complex tax rules using Clean Code and Design Patterns.

---

## 📄 Arquitetura e Clean Code / Architecture & Clean Code

Este projeto foi construído aplicando os princípios do **Uncle Bob (Robert C. Martin)** e **Joshua Bloch**, focando em:

### 1. S.O.L.I.D. Principles 
* **Single Responsibility (SRP):** O motor (`TaxEngine`) apenas gerencia o fluxo; cada estratégia (`TaxStrategy`) cuida de sua regra específica.
* **Open/Closed (OCP):** Novas legislações tributárias são adicionadas sem modificar o código fonte existente.
* **Dependency Inversion (DIP):** O sistema depende de abstrações (interfaces), não de implementações concretas.

### 2. Engenharia Defensiva & Fail-Fast 
* **Validação Precoce:** O sistema valida a integridade dos dados (nulos ou negativos) no momento da criação do objeto.
* **Imutabilidade:** Uso de `Java Records` para garantir que um cálculo fiscal, uma vez realizado, jamais seja alterado (**Thread-Safety**).

### 3. Legibilidade e Manutenibilidade 
* **Eliminação de Hardcoding:** Regras de negócio são injetadas, tornando o código principal limpo e focado no domínio.
* **Nomes Significativos:** Variáveis e métodos que expressam claramente sua intenção, eliminando a necessidade de comentários excessivos.

---

## Pilares Técnicos / Technical Pillars

* **Precisão Bancária:** Uso estrito de `BigDecimal` para evitar erros de arredondamento de ponto flutuante.
* **High Performance:** Processamento em massa utilizando `Parallel Streams` para otimização de CPU em grandes lotes de faturas.
* **Thread-Safe Registry:** Uso de `ConcurrentHashMap` para garantir a segurança em ambientes multithread de alta concorrência.

---

##  Tecnologias / Tech Stack

| Conceito / Concept | Implementação / Implementation |
| :--- | :--- |
| **Strategy Pattern** | Desacoplamento de algoritmos fiscais. |
| **Java 17+ Records** | DTOs imutáveis e código conciso. |
| **Functional Interfaces** | Uso de Lambdas para reduzir o boilerplate code. |
| **BigDecimal API** | Precisão absoluta para transações financeiras. |

---

**[PT-BR]** Este projeto demonstra a capacidade de transformar requisitos de negócio (leis fiscais) em uma arquitetura resiliente. Se a alíquota mudar ou um novo país for adicionado, o impacto na manutenção é próximo de zero.

**[EN]** This project demonstrates the ability to transform business requirements into a resilient architecture. If tax rates change or a new country is added, the maintenance impact is near zero.

---
