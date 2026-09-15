class Notification {
    void send(String message) {
        System.out.println("Sending: " + message);
    }
}

class EmailNotification extends Notification {
    void send(String message) {
        System.out.println("Email: " + message);
    }

    void send(String message, String email) {
        System.out.println("Email to " + email + ": " + message);
    }
}

public class CompileTime {
    public static void main(String[] args) {
        EmailNotification email = new EmailNotification();

        email.send("Interview tomorrow");
        email.send("Interview tomorrow", "shivam@gmail.com");
    }
}