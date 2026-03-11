# ♟️ Tax Strategy Engine (Technical Showcase)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Clean Code](https://img.shields.io/badge/Clean_Code-AABBCC?style=for-the-badge&logo=clean-code&logoColor=black)

[Português](#-tax-strategy-engine-showcase-técnico) | [English](#-tax-strategy-engine-technical-showcase-1)

---

## 🇧🇷 Tax Strategy Engine (Showcase Técnico)

Este repositório é um **Showcase de Engenharia de Software** focado em demonstrar o domínio de padrões de projeto, performance e governança de dados no ecossistema Java moderno. 

> **Nota de Portfólio:** Não se trata de uma aplicação completa, mas de uma **Prova de Conceito (PoC)** sobre como construir o "Core" financeiro de um sistema escalável, resiliente e agnóstico a leis fiscais.

### 🚀 Pilares do Projeto

A lógica do sistema está dividida em quatro pilares fundamentais:

1.  **O Contrato (Strategy Pattern) ♟️**
    Utilizamos o padrão **Strategy** para isolar as regras fiscais de diferentes jurisdições. O motor de cálculo é totalmente agnóstico: ele apenas executa um contrato definido por uma interface.
    * **Benefício:** Permite adicionar novos impostos (Brasil, EUA, Europa) sem alterar uma única linha de código do motor principal (**Princípio Aberto/Fechado**).

2.  **O Motor (Parallel Processing) ⚙️**
    Para lidar com grandes volumes de dados, o processamento é feito via **Parallel Streams**. O sistema distribui as tarefas entre os múltiplos núcleos da CPU, garantindo alta performance.
    * **Simulação de Latência:** Implementação de `TimeUnit` para demonstrar o comportamento multithread e o ganho de performance em tempo real.

3.  **O Porteiro (Fail-Fast & Imutabilidade) 🛡️**
    Utilizamos **Java Records** para garantir que os dados sejam imutáveis e seguros entre threads (**Thread-safety**).
    * **Fail-Fast:** Validação rigorosa no construtor. O sistema impede a criação de objetos com valores negativos ou nulos, travando o erro na entrada e evitando a propagação de dados corrompidos.

4.  **A Blindagem (Precisão Bancária) 💰**
    Cálculos financeiros exigem precisão absoluta. O uso exaustivo de **BigDecimal** em toda a camada de cálculo elimina erros de arredondamento inerentes aos tipos primitivos (`double`/`float`), garantindo integridade centavo a centavo.

### 🛠️ Tecnologias e Conceitos Aplicados

| Categoria | Tecnologias / Conceitos |
| :--- | :--- |
| **Java Moderno** | Records (17+), Functional Interfaces, Lambdas, Streams API. |
| **Arquitetura** | Princípios SOLID, Strategy Design Pattern, Engenharia Defensiva. |
| **Performance** | Parallelism, Multithreading, Concurrent API. |
| **I/O & I18n** | Java NIO (Files/Paths), Internacionalização (Locale/NumberFormat). |
| **Segurança** | Privacy by Design (Anonimização de dados/Masking). |

---

## 🇺🇸 Tax Strategy Engine (Technical Showcase)

This repository is a **Software Engineering Showcase** focused on demonstrating mastery of design patterns, performance, and data governance within the modern Java ecosystem.

> **Portfolio Note:** This is not a full-scale application, but a **Proof of Concept (PoC)** on how to build the financial "Core" of a scalable, resilient system that remains agnostic to tax laws.

### 🚀 Core Pillars

The system logic is built upon four fundamental pillars to ensure maintainability regardless of business rule complexity:

1.  **The Contract (Strategy Pattern) ♟️**
    We use the **Strategy pattern** to decouple tax rules from different jurisdictions. The calculation engine is fully agnostic: it simply executes a contract defined by an interface.
    * **Benefit:** Allows adding new taxes (BR, USA, EU) without changing a single line of the main engine code (**Open/Closed Principle**).

2.  **The Engine (Parallel Processing) ⚙️**
    To handle high data volumes, processing is powered by **Parallel Streams**. The system distributes tasks across multiple CPU cores, ensuring high-throughput performance.
    * **Latency Simulation:** Implementation of `TimeUnit` (TimeSleep) to demonstrate multi-threaded behavior and real-time performance gains.

3.  **The Sentry (Fail-Fast & Immutability) 🛡️**
    We utilize **Java Records** to ensure data is immutable and safe across threads (**Thread-safety**).
    * **Fail-Fast:** Rigorous validation within the constructor. The system prevents the creation of objects with negative or null values, catching errors at the entry point and preventing data corruption.

4.  **The Shield (Banking Precision) 💰**
    Financial calculations demand absolute precision. The exhaustive use of **BigDecimal** throughout the calculation layer eliminates rounding errors inherent in primitive types (`double`/`float`), guaranteeing "cent-by-cent" integrity.

### 🛠️ Technologies & Applied Concepts

| Category | Technologies / Concepts |
| :--- | :--- |
| **Modern Java** | Records (17+), Functional Interfaces, Lambdas, Streams API. |
| **Architecture** | SOLID Principles, Strategy Design Pattern, Defensive Engineering. |
| **Performance** | Parallelism, Multithreading, Concurrent API. |
| **I/O & I18n** | Java NIO (Files/Paths), Internationalization (Locale/NumberFormat). |
| **Security** | Privacy by Design (Data Anonymization/Masking). |

### 🧠 Development Mindset

This project reflects a **"Privacy by Design"** mindset through sensitive data anonymization methods and the application of **Clean Code** to ensure the codebase is human-readable and machine-executable.
