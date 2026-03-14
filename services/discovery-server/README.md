# 🔍 Discovery Server (Netflix Eureka)

## 🇧🇷 Português | 🇺🇸 English

---

### 🌐 Pilar: Infraestrutura Distribuída
**Pillar: Distributed Infrastructure**

O **Discovery Server** é o "cérebro" da orquestração. Ele permite que os microserviços se comuniquem de forma dinâmica, eliminando a necessidade de gerenciar endereços IP estáticos ou portas manuais. Em um ambiente cloud-native, ele é o diretório vivo que mantém a topologia da rede atualizada.
*The **Discovery Server** is the "brain" of orchestration. It allows microservices to communicate dynamically, eliminating the need for static IP addresses or manual port management. In a cloud-native environment, it is the living directory that keeps the network topology updated.*

---

### 🛠️ Funções Core | Core Functions

* **Service Registry:** Registro automático e em tempo real de cada instância que sobe no ecossistema. / *Real-time automatic registration of every instance in the ecosystem.*
* **Health Monitoring:** Monitoramento de vitalidade (Heartbeats) integrado ao *Circuit Breaker* para isolar instâncias instáveis. / *Vitality monitoring (Heartbeats) integrated with Circuit Breakers.*
* **High Availability (HA):** Configurado para operar em cluster, garantindo que a descoberta de serviços nunca seja um ponto único de falha. / *Cluster configuration to ensure service discovery is never a single point of failure.*

---

### ☕ Conceitos Java & Programação Aplicados

* **Self-Preservation Mode:** Um algoritmo inteligente que detecta falhas de rede em massa. Se o Eureka parar de receber muitos *heartbeats* ao mesmo tempo, ele entra em modo de auto-preservação para não remover instâncias saudáveis por erro de rede.
* **Client-Side Load Balancing:** Integração com o *Spring Cloud LoadBalancer*. O cliente (ex: Gateway) baixa a lista de instâncias e decide localmente para qual IP enviar a requisição, reduzindo o gargalo no servidor.
* **Service-Level Annotations:** Uso de `@EnableEurekaServer` no lado do servidor e `@EnableDiscoveryClient` nos microserviços para automação de rede via Spring Boot.



---

### ⚙️ Funcionamento do Código | How it Works

O ciclo de vida de um serviço no Discovery Server segue três etapas críticas:
1.  **Registration:** Ao iniciar, o microserviço envia seus metadados (IP, Porta, Nome) para o Eureka.
2.  **Renewal (Heartbeat):** A cada 30 segundos, o serviço envia um sinal "UP". Se o Eureka não receber este sinal por 90 segundos, ele assume que o serviço morreu.
3.  **Discovery:** Quando o **Gateway** precisa do "Motor de Cálculo", ele pergunta ao Eureka: *"Onde estão as instâncias de CALC-SERVICE?"* e recebe a lista atualizada.

---
