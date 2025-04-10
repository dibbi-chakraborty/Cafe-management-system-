import java.util.*;

// Main Class
public class CafeManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== Welcome to Java Cafe Management System ===");
        Cafe cafe = new Cafe();
        cafe.start();
    }
}

// Cafe System Controller
class Cafe {
    private UserManager userManager = new UserManager();
    private MenuManager menuManager = new MenuManager();
    private OrderManager orderManager = new OrderManager(menuManager);
    private BillingSystem billingSystem = new BillingSystem(menuManager);
    private CustomerManager customerManager = new CustomerManager();
    private ReportSystem reportSystem = new ReportSystem();
    private NotificationSystem notificationSystem = new NotificationSystem();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Login");
            System.out.println("2. View Menu");
            System.out.println("3. Place Order");
            System.out.println("4. View Bill");
            System.out.println("5. Register Customer");
            System.out.println("6. Reports");
            System.out.println("7. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> userManager.login();
                case 2 -> menuManager.showMenu();
                case 3 -> orderManager.placeOrder();
                case 4 -> billingSystem.generateBill(orderManager.getCurrentOrder());
                case 5 -> customerManager.registerCustomer();
                case 6 -> reportSystem.generateDailyReport();
                case 7 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

// User Manager
class UserManager {
    public void login() {
        System.out.println("User login feature (placeholder)");
        // User authentication logic here
    }
}

// Menu Manager
class MenuManager {
    private Map<String, Double> menuItems = new HashMap<>();

    public MenuManager() {
        menuItems.put("Coffee", 3.5);
        menuItems.put("Tea", 2.0);
        menuItems.put("Sandwich", 5.0);
        menuItems.put("Cake", 4.0);
    }

    public void showMenu() {
        System.out.println("=== Menu ===");
        for (Map.Entry<String, Double> entry : menuItems.entrySet()) {
            System.out.println(entry.getKey() + " - $" + entry.getValue());
        }
    }

    public boolean isItemAvailable(String item) {
        return menuItems.containsKey(item);
    }

    public double getItemPrice(String item) {
        return menuItems.getOrDefault(item, 0.0);
    }

    public Set<String> getMenuItems() {
        return menuItems.keySet();
    }
}

// Order Manager
class OrderManager {
    private List<String> currentOrder = new ArrayList<>();
    private MenuManager menuManager;
    private Scanner scanner = new Scanner(System.in);

    public OrderManager(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    public void placeOrder() {
        currentOrder.clear();
        System.out.println("Enter items to order (type 'done' to finish):");
        while (true) {
            String item = scanner.nextLine();
            if (item.equalsIgnoreCase("done")) break;
            if (menuManager.isItemAvailable(item)) {
                currentOrder.add(item);
                System.out.println(item + " added to order.");
            } else {
                System.out.println("Item not available.");
            }
        }
    }

    public List<String> getCurrentOrder() {
        return currentOrder;
    }
}

// Billing System
class BillingSystem {
    private MenuManager menuManager;

    public BillingSystem(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    public void generateBill(List<String> order) {
        if (order == null || order.isEmpty()) {
            System.out.println("No order placed yet.");
            return;
        }

        double total = 0;
        System.out.println("\n=== Billing ===");
        for (String item : order) {
            double price = menuManager.getItemPrice(item);
            System.out.println(item + ": $" + price);
            total += price;
        }
        System.out.println("Total Amount: $" + total);
        System.out.println("Payment Status: Successful (simulated)");
    }
}

// Customer Manager
class CustomerManager {
    private List<String> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void registerCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        customers.add(name);
        System.out.println("Customer '" + name + "' registered.");
    }
}

// Report System
class ReportSystem {
    public void generateDailyReport() {
        System.out.println("=== Daily Report ===");
        System.out.println("Orders Today: 10");
        System.out.println("Total Sales: $150.00");
        System.out.println("Top Selling Item: Coffee");
        System.out.println("(Simulated Report)");
    }
}

// Notification System
class NotificationSystem {
    public void sendNotification(String message) {
        System.out.println("Notification Sent: " + message);
    }
}
