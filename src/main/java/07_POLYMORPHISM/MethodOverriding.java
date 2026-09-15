//Runtime Polymorphism — Overriding

class Notification {
    void send(String message) {
        System.out.println("Generic notification: " + message);
    }
}

class EmailNotification extends Notification {
    @Override
    void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SMSNotification extends Notification {
    @Override
    void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotification extends Notification {
    @Override
    void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}

public class Runtime {
    public static void main(String[] args) {
        Notification notification;

        notification = new EmailNotification();
        notification.send("Order placed");

        notification = new SMSNotification();
        notification.send("Order shipped");

        notification = new PushNotification();
        notification.send("Order delivered");
    }
}