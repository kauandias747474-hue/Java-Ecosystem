# 🏛️ Business Rules Engine: International Tax (Case Study)

> **[PT-BR]** Este repositório apresenta o `InternationalTax.java`, uma **Proof of Concept (PoC)** que demonstra como desacoplar regras fiscais complexas do núcleo do sistema.
>
> **[EN]** This repository features `InternationalTax.java`, a **Proof of Concept (PoC)** demonstrating how to decouple complex tax rules from the system's core.

---

## 📄 O que é o International Tax? / What is International Tax?

**[PT-BR]** É uma implementação do **Strategy Pattern** focada em transações *cross-border*. Ele atua como um motor de decisão que aplica alíquotas e regras de conformidade baseadas na localização do cliente e do provedor. 

**[EN]** It is a **Strategy Pattern** implementation focused on *cross-border* transactions. It acts as a decision engine that applies tax rates and compliance rules based on the customer's and provider's locations.



---

## 🎯 Por que, Para que e Como? / Why, What for, and How?

### 🧩 O que ele faz? / What does it do?
* **[PT]** Traduz legislações fiscais (como VAT, ICMS, Sales Tax) em código executável e isolado.
* **[EN]** Translates tax legislations (such as VAT, ICMS, Sales Tax) into isolated, executable code.

### 💡 Por que ele faz? / Why does it do it?
* **[PT]** Para evitar o "Hardcode". Em vez de `if/else` infinitos no código principal, as regras são injetadas. Isso protege o **Core Domain** de mudanças constantes na lei.
* **[EN]** To avoid "Hardcoding". Instead of infinite `if/else` statements in the main code, rules are injected. This protects the **Core Domain** from constant changes in tax law.

### 🚀 Pra que ele faz? / What is it for?
* **[PT]** Para garantir **Escalabilidade de Negócio**. Permite que uma empresa entre em um novo país apenas adicionando uma nova "Policy", sem precisar reescrever o motor financeiro.
* **[EN]** To ensure **Business Scalability**. It allows a company to enter a new country just by adding a new "Policy" without rewriting the financial engine.

---

## 🏗️ Pilares Técnicos / Technical Pillars

* **Imutabilidade (Records):** Garante que o cálculo não seja alterado durante o fluxo. / *Ensures the calculation isn't altered during the flow.*
* **Precisão (BigDecimal):** Integridade centavo a centavo em moedas estrangeiras. / *Cent-by-cent integrity in foreign currencies.*
* **Processamento Paralelo:** Capacidade de processar milhares de taxas simultaneamente via Threads. / *Ability to process thousands of rates simultaneously via Threads.*

---

## 🛠️ Tecnologias / Tech Stack

| Categoria / Category | Tecnologias / Concepts | Descrição / Description |
| :--- | :--- | :--- |
| **Design Pattern** | **Strategy Pattern** | Desacoplamento de regras fiscais. / Decoupling tax rules. |
| **Java Moderno** | **Records, Sealed Interfaces, Parallel Streams** | Uso de Java 17+ para performance e legibilidade. / Java 17+ for performance. |
| **Engenharia Defensiva** | **Fail-Fast Validation** | Validação rigorosa na entrada de dados. / Strict entry-point validation. |

---

## 🧠 Mindset Sênior / Senior Mindset

**[PT-BR]** Ter o `InternationalTax.java` organizado demonstra visão global. Se uma lei mudar amanhã, você altera apenas um arquivo, garantindo manutenibilidade e transparência para auditorias. 

**[EN]** Having `InternationalTax.java` organized demonstrates a global vision. If a law changes tomorrow, you only update one file, ensuring maintainability and audit transparency.

---

_Desenvolvido como um Showcase Técnico de Engenharia de Software._
