import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
    private final Set<String> blacklistedIPs = ConcurrentHashMap.newKeySet();


    private static final int LIMIT = 100;

    private final ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();

    public boolean isAllowed(String ip) {
        if (blacklistedIPs.contains(ip)) {
            return false;
        }


        AtomicInteger count = requestCounts.computeIfAbsent(ip, _ -> new AtomicInteger(0));

        if (count.get() >= LIMIT) {
            block(ip);
            return false;
        }

        count.incrementAndGet();
        return true;
    }

    public void block(String ip) {
        blacklistedIPs.add(ip);
        requestCounts.remove(ip);
    }
}
