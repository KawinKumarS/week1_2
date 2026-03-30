import java.util.*;

public class Plagiarism {

    private Map<String, Set<Integer>> index = new HashMap<>();

    public void addDocument(int id, List<String> words) {
        for (int i = 0; i + 4 < words.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++)
                sb.append(words.get(i + j)).append(" ");
            index.computeIfAbsent(sb.toString(), k -> new HashSet<>()).add(id);
        }
    }

    public int compare(List<String> words) {
        int matches = 0;
        for (int i = 0; i + 4 < words.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++)
                sb.append(words.get(i + j)).append(" ");
            if (index.containsKey(sb.toString())) matches++;
        }
        return matches;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Plagiarism p = new Plagiarism();

        System.out.print("Enter number of words in document: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<String> words = Arrays.asList(sc.nextLine().split(" "));
        p.addDocument(1, words);

        System.out.print("Enter words to compare: ");
        List<String> check = Arrays.asList(sc.nextLine().split(" "));
        System.out.println("Matches: " + p.compare(check));
    }
}