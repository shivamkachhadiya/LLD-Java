import java.util.*;

class Product {

    private String id;
    private double price;

    public Product(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}

class Inventory {

    private Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public void removeProduct(String productId) {
        products.remove(productId);
    }
}

class VendingMachine {

    private VendingMachineState state;
    private double balance;
    private Inventory inventory;
    private Product selectedProduct;

    public VendingMachine() {
        state = new IdleState();
        balance = 0;
        inventory = new Inventory();
    }

    public void insertMoney(double amount) {
        state.insertMoney(this, amount);
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void selectProduct(String productId) {
        state.selectProduct(this, productId);
    }

    public void dispenseProduct() {
        state.dispenseProduct(this);
    }

    public void refund() {
        state.refund(this);
    }

    public void addProduct(Product product) {
        inventory.addProduct(product);
    }

    public Product getProduct(String productId) {
        return inventory.getProduct(productId);
    }

    public void removeProduct(String productId) {
        inventory.removeProduct(productId);
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }
}

interface VendingMachineState {

    void insertMoney(VendingMachine machine, double amount);

    void selectProduct(VendingMachine machine, String productId);

    void dispenseProduct(VendingMachine machine);

    void refund(VendingMachine machine);
}

class IdleState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Money inserted: " + amount);
        machine.addBalance(amount);
        machine.setState(new HasMoneyState());
    }

    @Override
    public void selectProduct(VendingMachine machine, String productId) {
        System.out.println("Please insert money first.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please insert money first.");
    }

    @Override
    public void refund(VendingMachine machine) {
        System.out.println("No money to refund.");
    }
}

class HasMoneyState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("More money accepted: " + amount);
        machine.addBalance(amount);
    }

    @Override
    public void selectProduct(VendingMachine machine, String productId) {

        Product product = machine.getProduct(productId);

        if (product == null) {
            System.out.println("Product not available.");
            return;
        }

        System.out.println("Product selected: " + productId);

        machine.setSelectedProduct(product);
        machine.setState(new DispensingState());
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void refund(VendingMachine machine) {
        System.out.println("Refunding: " + machine.getBalance());
        machine.setBalance(0);
        machine.setState(new IdleState());
    }
}

class DispensingState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Please wait.");
    }

    @Override
    public void selectProduct(VendingMachine machine, String productId) {
        System.out.println("Product already selected.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {

        Product product = machine.getSelectedProduct();

        double price = product.getPrice();

        if (machine.getBalance() < price) {
            System.out.println("Insufficient balance.");
            return;
        }

        System.out.println("Dispensing: " + product.getId());

        double change = machine.getBalance() - price;

        System.out.println("Change: " + change);

        machine.setBalance(0);

        machine.removeProduct(product.getId());

        machine.setSelectedProduct(null);

        machine.setState(new IdleState());
    }

    @Override
    public void refund(VendingMachine machine) {
        System.out.println("Cannot refund now.");
    }
}

public class Vending {

    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine();

        Product coke = new Product("COKE", 30);

        machine.addProduct(coke);

        machine.insertMoney(50);

        machine.selectProduct("COKE");

        machine.dispenseProduct();
    }
}