#  Context Management Layer | Camada de Gerenciamento de Contexto

## 🇧🇷 Português

###  Visão Geral e Motivação
A **Camada de Gerenciamento de Contexto** é a "Espinha Dorsal" de observabilidade deste ecossistema. Desenvolver este módulo em Java foi uma jornada de aprendizado sobre **rigor arquitetural**. O Java não aceita atalhos: a estrutura de pastas deve espelhar o código, e cada componente tem um ciclo de vida definido. 

O resultado é um sistema ultra-confiável onde nenhuma requisição é "anônima". Isso garante que, em um cenário de falha em produção, saibamos exatamente quem, quando e por que algo aconteceu, permitindo uma depuração cirúrgica e rápida.

### 🏗️ Organização e Estrutura do Projeto

Abaixo, detalho a função de cada peça nesta engrenagem e como elas se organizam:

#### 📂 `api/` & `poc.management.api/` (A Fronteira)
* **`ContextInterceptor.java`**: O "segurança" da API. Ele intercepta toda requisição que chega e verifica se há um `correlation-id`. Se não houver, ele gera um novo UUID. É aqui que o rastreio nasce.
* **`GlobalExceptionHandler.java`**: O "redutor de danos". Garante que, se o código quebrar, o usuário receba uma resposta elegante e o erro seja logado com o contexto correto, sem expor detalhes sensíveis da infraestrutura.

#### 📂 `domain/` (O Coração)
* **`RequestContext.java`**: Onde os dados "moram" durante a execução. Utiliza o conceito de **ThreadLocal** para garantir o isolamento: as informações do Usuário A nunca se misturam com as do Usuário B, mesmo em processamento paralelo massivo.

#### 📂 `infrastructure/` (A Casa de Máquinas)
* **`CorrelationIdContext.java`**: Gerencia o estado do ID de correlação de forma estática e segura, permitindo que qualquer classe do sistema consulte o "RG" da transação atual.
* **`ChaosConfiguration.java`**: Nossa ferramenta de resiliência. Utiliza `@PostConstruct` para configurar falhas controladas (latência, erros randômicos), testando se o sistema sobrevive ao "caos" do mundo real.
* **`FeignClientInterceptor.java`**: Garante que o contexto "võe". Quando este serviço chama outro via HTTP, este componente injeta automaticamente o ID no cabeçalho da próxima chamada.
* **`OpenApiConfig.java`**: O cérebro por trás do **Swagger**. Automatiza a documentação e expõe visualmente como a API e seus headers obrigatórios devem ser usados.

#### 📂 `messaging/` (A Ponte Assíncrona)
* **`KafkaContextWrapper.java`**: Essencial para arquiteturas de eventos. Ele "embrulha" o contexto e garante que, ao enviar uma mensagem para o **Kafka**, o rastreio persista no outro lado da fila.

#### 📂 `resources/` (O Painel de Controle)
* **`application.yml`**: Onde residem as configurações de porta, nomes de serviços e conexões externas.
* **`logback-spring.yml`**: Configuração avançada de logs, preparada para exibir o `correlation-id` em cada linha de console.
* **`prometheus.yml`**: Define as métricas que serão coletadas para monitoramento de performance em tempo real (CPU, Memória, Latência).

#### 📂 `Raiz do Projeto / Docker` (A Armadura)
* **`Main.java`**: Localizado em `src/finance.context`, é o ponto de ignição que sobe todo o ecossistema Spring Boot.
* **`Dockerfile` & `docker-compose.yml`**: Permitem que este sistema rode em qualquer lugar (nuvem ou local) de forma idêntica, isolando as dependências em containers.

---

## 🇺🇸 English

### 📌 Overview & Motivation
The **Context Management Layer** is the "Backbone" of observability in this ecosystem. Developing this module in Java was a deep dive into **architectural rigor**. Java demands precision: the directory structure must mirror the code, and every component follows a strict lifecycle.

The result is an ultra-reliable system where no request is "anonymous." This ensures that in a production failure scenario, we know exactly who, when, and why something happened, allowing for surgical and rapid debugging.

### 🏗️ Project Organization & Structure

#### 📂 `api/` & `poc.management.api/` (The Frontier)
* **`ContextInterceptor.java`**: The API "security guard." It intercepts incoming requests to validate the `correlation-id`. If missing, it generates a new UUID. This is where tracing is born.
* **`GlobalExceptionHandler.java`**: The "damage reducer." It ensures that if the code breaks, the user receives an elegant response and the error is logged with the correct context.

#### 📂 `domain/` (The Heart)
* **`RequestContext.java`**: Where the data "lives" during execution. It uses **ThreadLocal** concepts to ensure isolation: User A's data never leaks into User B's process.

#### 📂 `infrastructure/` (The Engine Room)
* **`CorrelationIdContext.java`**: Manages the state of the correlation ID, allowing any part of the system to query the "ID" of the current transaction.
* **`ChaosConfiguration.java`**: Our resilience tool. Uses `@PostConstruct` to inject controlled failures (latency, random errors) to test real-world stability.
* **`FeignClientInterceptor.java`**: Ensures the context "flies." When this service calls another via HTTP, it automatically injects the ID into the next call's header.
* **`OpenApiConfig.java`**: The brain behind **Swagger**. It automates documentation and visually exposes how the API and its mandatory headers should be used.

#### 📂 `messaging/` (The Asynchronous Bridge)
* **`KafkaContextWrapper.java`**: Essential for event-driven architectures. It "wraps" the context, ensuring that when sending a message to **Kafka**, the trace persists on the other side.

#### 📂 `resources/` (The Control Panel)
* **`application.yml`**: External configurations like ports, service names, and connections.
* **`logback-spring.yml`**: Advanced log configuration, prepared to display the `correlation-id` in every console line.
* **`prometheus.yml`**: Defines metrics for real-time performance monitoring.

#### 📂 `Root / Docker` (The Armor)
* **`Main.java`**: Located in `src/finance.context`, the ignition point for the Spring Boot ecosystem.
* **`Dockerfile` & `docker-compose.yml`**: Ensure the system runs identically anywhere by isolating dependencies in containers.

---


### 💭 Reflexão sobre a Experiência de Produção | Reflection on the Production Experience
 **🇧🇷 Português**

*Produzir este código foi um exercício profundo de paciência, precisão e maturidade técnica. No início da jornada, os erros de package decorrentes da estrutura rígida do Java e as configurações minuciosas do Maven pareciam obstáculos burocráticos. No entanto, ao longo do processo, percebi que essa rigidez não é um defeito, mas a maior aliada de um engenheiro de software: ela impõe uma organização que elimina a ambiguidade e evita o acúmulo de dívidas técnicas.*

*Enfrentar o desafio de configurar interceptadores, gerenciar o ciclo de vida de beans com @PostConstruct e garantir o isolamento de threads com ThreadLocal me deu uma nova perspectiva sobre como sistemas de alta escala operam. A maior satisfação não foi apenas ver o código compilar, mas ver o ecossistema ganhar vida: o Swagger renderizando documentação viva, o Spring Boot subindo com "Build Success" e os logs exibindo rastreabilidade total. Este projeto marca a transição de apenas escrever lógica para projetar infraestruturas robustas com padrão de mercado.*

**🇺🇸 English**
*Producing this code was a profound exercise in patience, precision, and technical maturity. At the start of the journey, package errors resulting from Java's rigid structure and detailed Maven configurations felt like bureaucratic hurdles. However, throughout the process, I realized that this strictness is not a flaw, but a software engineer's greatest ally: it enforces an organization that eliminates ambiguity and prevents the accumulation of technical debt.*

*Tackling the challenge of configuring interceptors, managing bean lifecycles with @PostConstruct, and ensuring thread isolation with ThreadLocal gave me a new perspective on how high-scale systems operate. The greatest satisfaction wasn't just seeing the code compile, but seeing the ecosystem come to life: Swagger rendering live documentation, Spring Boot starting with "Build Success," and logs displaying full traceability. This project marks a transition from simply writing logic to designing robust, industry-standard infrastructures.*


---
###  Applied Concepts & Tools
* **AOP (Aspect-Oriented Programming):** Used via interceptors to decouple business logic from tracing infrastructure.
* **Thread Isolation (ThreadLocal):** Ensures data safety in multi-threaded environments, preventing context leaking between requests.
* **Distributed Tracing:** Implementation of Correlation IDs for end-to-end traceability in distributed systems.
* **Spring Boot 3.2.0:** Core framework for Inversion of Control (IoC) and dependency injection.
* **SpringDoc OpenAPI (Swagger):** Interactive and live API documentation engine.
* **Apache Kafka Integration:** Context propagation within asynchronous messaging systems 
* **Docker & Docker Compose:** Containerization to ensure parity between dev and production environments.
  
  
###  Conceitos Aplicados & Ferramentas
* **AOP (Aspect-Oriented Programming):** Utilizado via interceptadores para separar a lógica de negócio da infraestrutura de rastreio.
* **Thread Isolation (ThreadLocal):** Garante a segurança de dados em ambientes multi-thread, impedindo o vazamento de contexto entre requisições.
* **Distributed Tracing:** Implementação de Correlation IDs para rastreabilidade de ponta a ponta em sistemas distribuídos.
* **Spring Boot 3.2.0:** Framework base para inversão de controle (IoC) e gerenciamento de injeção de dependências.
* **SpringDoc OpenAPI (Swagger):** Engine de documentação viva e interativa para a API.
* **Apache Kafka Integration:** Propagação de contexto em sistemas de mensageria assíncrona.
* **Docker & Docker Compose:** Containerização para garantir paridade entre ambientes de desenvolvimento e produção.
  

