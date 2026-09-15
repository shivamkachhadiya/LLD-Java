// STRATEGY PATTERN
//
// Same job → multiple ways
//
// 1. Interface      = WHAT
// 2. Implementations = HOW
// 3. Context         = USES strategy
// 4. Client          = WHICH strategy

interface PaymentStrategy {
    void pay(double amount);
}

class CardPayment implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Card");
    }
}

class UPIPayment implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI");
    }
}

class BankPayment implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Net Banking");
    }
}

// CONTEXT
class Payment {

    private PaymentStrategy strategy;

    Payment(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void makePayment(double amount) {
        strategy.pay(amount);
    }
}

public class Strategy {

    public static void main(String[] args) {

        // Client chooses strategy
        Payment p1 = new Payment(new UPIPayment());
        p1.makePayment(9999);

        Payment p2 = new Payment(new CardPayment());
        p2.makePayment(2000);
    }
}