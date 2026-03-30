import java.util.*;

public class DNSCache {

    class Entry {
        String ip;
        long expiry;

        Entry(String ip, long expiry) {
            this.ip = ip;
            this.expiry = expiry;
        }
    }

    private Map<String, Entry> cache = new HashMap<>();
    private int TTL = 300;

    public String resolve(String domain) {
        long now = System.currentTimeMillis() / 1000;

        if (cache.containsKey(domain)) {
            Entry e = cache.get(domain);
            if (now < e.expiry) return e.ip;
            else cache.remove(domain);
        }

        String ip = "1.1.1.1";
        cache.put(domain, new Entry(ip, now + TTL));
        return ip;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DNSCache dns = new DNSCache();

        System.out.print("Enter number of queries: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter domain: ");
            String domain = sc.nextLine();
            String ip = dns.resolve(domain);
            System.out.println("IP Address: " + ip);
        }
    }
}