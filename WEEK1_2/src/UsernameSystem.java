import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UsernameSystem {


    private ConcurrentHashMap<String, Integer> userMap;


    private ConcurrentHashMap<String, Integer> attemptCount;

    public UsernameSystem() {
        userMap = new ConcurrentHashMap<>();
        attemptCount = new ConcurrentHashMap<>();
    }

    public void registerUser(String username, int userId) {
        userMap.put(username.toLowerCase(), userId);
    }

    // Check availability (O(1))
    public boolean checkAvailability(String username) {
        username = username.toLowerCase();

        // Track attempts
        attemptCount.put(username, attemptCount.getOrDefault(username, 0) + 1);

        return !userMap.containsKey(username);
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        username = username.toLowerCase();

        // Append numbers
        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;
            if (!userMap.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // Replace underscore with dot
        String alt = username.replace("_", ".");
        if (!userMap.containsKey(alt)) {
            suggestions.add(alt);
        }

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {
        String maxUser = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : attemptCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxUser = entry.getKey();
            }
        }

        return maxUser + " (" + maxCount + " attempts)";
    }

    // Main method (testing)
    public static void main(String[] args) {
        UsernameSystem system = new UsernameSystem();

        system.registerUser("john_doe", 1);
        system.registerUser("alice", 2);

        System.out.println(system.checkAvailability("john_doe")); // false
        System.out.println(system.checkAvailability("jane_smith")); // true

        System.out.println(system.suggestAlternatives("john_doe"));

        // Simulate attempts
        system.checkAvailability("admin");
        system.checkAvailability("admin");
        system.checkAvailability("admin");

        System.out.println(system.getMostAttempted());
    }
}