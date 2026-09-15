import java.util.*;

class Customer {
    private int customerId;
    private String name;
    private String address;

    Customer(int id, String n, String a) {
        this.customerId = id;
        this.name = n;
        this.address = a;
    }
}

class MenuItem {
    private int itemId;
    private String name;
    private double price;

    MenuItem(int id, String name, double price) {
        this.itemId = id;
        this.name = name;
        this.price = price;
    }

    double getPrice() {
        return price;
    }
}

// MenuItem = restaurant ka actual food item
// OrderItem = customer ne kitni quantity order ki

class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    OrderItem(MenuItem menu, int quantity) {
        this.menuItem = menu;
        this.quantity = quantity;
    }

    double getTotal() {
        return menuItem.getPrice() * quantity;
    }
}

class Restaurant {
    private int restaurantId;
    private String name;
    private String address;
    private List<MenuItem> menu;

    Restaurant(int id, String name, String add) {
        this.restaurantId = id;
        this.name = name;
        this.address = add;
        this.menu = new ArrayList<>();
    }

    void addMenuItem(MenuItem item) {
        menu.add(item);
    }
}

// Order
// Customer selected items ke saath order place karega.

class Order {
    private int orderId;
    private Customer customer;
    private Restaurant restaurant;
    private List<OrderItem> items;

    private Payment payment;

    private DeliveryPartner deliveryPartner;

    Order(int orderId, Customer customer, Restaurant restaurant) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
    }

    void setPayment(Payment payment) {
        this.payment = payment;
    }

    void assignDeliveryPartner(DeliveryPartner partner) {
        this.deliveryPartner = partner;
    }

    void pay() {
        payment.makePayment(getTotalAmount());
    }

    void addItems(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }

    double getTotalAmount() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }
}

// ================= STRATEGY =================

interface PaymentStrategy {
    void pay(double amount);
}

class UPIPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI");
    }
}

class CardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Card");
    }
}

class CashPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Cash");
    }
}

// ================= PAYMENT =================

class Payment {
    private PaymentStrategy strategy;

    Payment(PaymentStrategy str) {
        this.strategy = str;
    }

    void makePayment(double amount) {
        strategy.pay(amount);
    }
}

interface DeliveryPartner {
    void deliver();
}

class BikeDeliveryPartner implements DeliveryPartner {
    public void deliver() {
        System.out.println("Delivery by Bike");
    }
}

class CarDeliveryPartner implements DeliveryPartner {
    public void deliver() {
        System.out.println("Delivery by Car");
    }
}

class CycleDeliveryPartner implements DeliveryPartner {
    public void deliver() {
        System.out.println("Delivery by Cycle");
    }
}

class DeliveryFactory {

    DeliveryPartner createDelivery(String type) {

        if (type.equals("BIKE")) {
            return new BikeDeliveryPartner();
        }
        else if (type.equals("CAR")) {
            return new CarDeliveryPartner();
        }
        else if (type.equals("CYCLE")) {
            return new CycleDeliveryPartner();
        }

        throw new IllegalArgumentException("Invalid delivery type");
    }
}


// ================= MAIN =================

public class Food {

    public static void main(String[] args) {

        Customer c1 = new Customer(1, "Shivam", "Vellore");

        Restaurant r1 = new Restaurant(101, "Food Hub", "Katpadi");

        MenuItem idli = new MenuItem(1, "Idli", 50);

        MenuItem dosa = new MenuItem(2, "Dosa", 80);

        MenuItem vada = new MenuItem(3, "Medu Vada", 60);

        r1.addMenuItem(idli);
        r1.addMenuItem(dosa);
        r1.addMenuItem(vada);

        // Create Order
        Order order = new Order(1001, c1, r1);

        // Add food
        order.addItems(dosa, 2);
        order.addItems(idli, 1);

        // Calculate total
        System.out.println("Total order is -> ₹" + order.getTotalAmount());

        // Select payment strategy
        PaymentStrategy strategy = new UPIPayment();

        // Create Payment
        Payment payment = new Payment(strategy);

        // Attach payment to order
        order.setPayment(payment);

        // Pay
        order.pay();

        DeliveryFactory factory = new DeliveryFactory();

        DeliveryPartner partner = factory.createDelivery("BIKE");

        order.assignDeliveryPartner(partner);

        partner.deliver();
    }
}