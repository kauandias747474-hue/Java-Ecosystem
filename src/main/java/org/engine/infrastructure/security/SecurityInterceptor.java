import java.util.Objects;

public class SecurityInterceptor {

    private final RateLimiter limiter;


    public SecurityInterceptor(RateLimiter limiter) {
        this.limiter = Objects.requireNonNull(limiter, "RateLimiter não pode ser nulo.");
    }

    public boolean handleRequest(String ip, String token, String payload) {

        if (!limiter.isAllowed(ip)) {

            System.out.println("[SECURITY] Bloqueio preventivo: IP já consta na Blacklist -> " + ip);
            return false;
        }


        if (!isTokenValid(token)) {
            System.out.println("[SECURITY] Token inválido detectado para o IP: " + ip);
            return false;
        }


        if (containsMaliciousPattern(payload)) {
            System.err.println("[ALERT] Tentativa de ataque detectada! Banindo IP: " + ip);


            limiter.block(ip);
            return false;
        }

        System.out.println("[SECURITY] Requisição higienizada e autorizada.");
        return true;
    }

    private boolean isTokenValid(String token) {
        return token != null && token.startsWith("BEARER_");
    }

    private boolean containsMaliciousPattern(String payload) {
        if (payload == null) return false;
        String p = payload.toUpperCase();
        return p.contains("DROP TABLE") || p.contains("<SCRIPT>") || p.contains("OR 1=1");
    }
}
