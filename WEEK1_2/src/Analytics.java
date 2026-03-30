import java.util.*;

public class Analytics {

    private Map<String, Integer> views = new HashMap<>();
    private Map<String, Set<String>> uniqueUsers = new HashMap<>();
    private Map<String, Integer> sources = new HashMap<>();

    public void process(String url, String userId, String source) {
        views.put(url, views.getOrDefault(url, 0) + 1);
        uniqueUsers.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    public List<Map.Entry<String, Integer>> topPages() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(views.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Analytics a = new Analytics();

        System.out.print("Enter number of events: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String url = sc.nextLine();
            String user = sc.nextLine();
            String source = sc.nextLine();
            a.process(url, user, source);
        }

        System.out.println(a.topPages());
    }
}git