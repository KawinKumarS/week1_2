import java.util.*;

public class Inventory {

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Queue<Integer>> waitlist = new HashMap<>();

    public synchronized void addProduct(String product, int qty) {
        stock.put(product, qty);
        waitlist.put(product, new LinkedList<>());
    }

    public synchronized String purchase(String product, int userId) {
        if (stock.getOrDefault(product, 0) > 0) {
            stock.put(product, stock.get(product) - 1);
            return "Success";
        } else {
            waitlist.get(product).offer(userId);
            return "Added to waitlist";
        }
    }

    public int checkStock(String product) {
        return stock.getOrDefault(product, 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inv = new Inventory();

        System.out.print("Enter product name: ");
        String product = sc.nextLine();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        inv.addProduct(product, qty);

        System.out.print("Enter number of users: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter user ID: ");
            int userId = sc.nextInt();
            System.out.println(inv.purchase(product, userId));
        }

        System.out.println("Remaining stock: " + inv.checkStock(product));
    }
}