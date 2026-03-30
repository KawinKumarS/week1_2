import java.util.*;

public class Autocomplete {

    private Map<String, Integer> freq = new HashMap<>();

    public void addQuery(String query) {
        freq.put(query, freq.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {
        List<String> res = new ArrayList<>();

        for (String key : freq.keySet()) {
            if (key.startsWith(prefix)) res.add(key);
        }

        res.sort((a, b) -> freq.get(b) - freq.get(a));
        return res.size() > 10 ? res.subList(0, 10) : res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Autocomplete a = new Autocomplete();

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            a.addQuery(sc.nextLine());
        }

        System.out.print("Enter prefix: ");
        String prefix = sc.nextLine();

        System.out.println(a.search(prefix));
    }
}