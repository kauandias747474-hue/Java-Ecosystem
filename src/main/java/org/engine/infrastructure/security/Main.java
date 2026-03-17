void main() {

    var limiter = new RateLimiter();
    var interceptor = new SecurityInterceptor(limiter);

    System.out.println("=== SENTINEL ENGINE ONLINE ===\n");


    interceptor.handleRequest("192.168.1.10", "BEARER_TOKEN_VALIDO", "{\"user\": \"fulano\"}");


    interceptor.handleRequest("10.0.0.50", "BEARER_TOKEN_HACKER", "DROP TABLE users;");


    if (!interceptor.handleRequest("10.0.0.50", "BEARER_TOKEN_NOVO", "{\"msg\": \"entrar\"}")) {
        System.out.println(">>> Resultado: O sistema barrou o IP por estar na Blacklist.");
    }


    String spammerIP = "172.16.0.1";
    for (int i = 1; i <= 105; i++) {
        if (!interceptor.handleRequest(spammerIP, "BEARER_VALID", "ping")) {
            System.out.println(">>> Sucesso: IP banido na tentativa: " + i);
            break;
        }
    }
}
