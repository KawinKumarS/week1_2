import java.util.*;

public class RateLimiter {

    class Bucket {
        int tokens;
        long lastRefill;

        Bucket(int tokens, long lastRefill) {
            this.tokens = tokens;
            this.lastRefill = lastRefill;
        }
    }

    private Map<String, Bucket> map = new HashMap<>();
    private int MAX = 5;

    public boolean allow(String clientId) {
        long now = System.currentTimeMillis() / 1000;

        map.putIfAbsent(clientId, new Bucket(MAX, now));
        Bucket b = map.get(clientId);

        if (now - b.lastRefill >= 10) {
            b.tokens = MAX;
            b.lastRefill = now;
        }

        if (b.tokens > 0) {
            b.tokens--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RateLimiter r = new RateLimiter();

        System.out.print("Enter client id: ");
        String id = sc.nextLine();

        for (int i = 0; i < 10; i++) {
            System.out.println(r.allow(id));
        }
    }
}