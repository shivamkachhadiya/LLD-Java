// 🏭 Factory — Quick Revision

// Idea:

// Object creation ki responsibility Factory ko do.

// Client → Factory → Object
// Template
// interface Product {
//     void operation();
// }

// class A implements Product {
//     public void operation() {}
// }

// class B implements Product {
//     public void operation() {}
// }

// class Factory {
//     static Product create(String type) {
//         if (type.equals("A")) return new A();
//         if (type.equals("B")) return new B();
//         return null;
//     }
// }
// Interview points
// Problem: Client directly new kar raha hai.
// Solution: Factory object create karegi.
// Benefit: Loose coupling + creation logic centralized.
// Client depends on: Interface.
// Factory decides: Kaunsa concrete object banana hai.
// Singleton: ONE object.
// Factory: WHICH object?
interface notification{
    void send(String message);
}

//"Agar tum Notification ho, toh tumhe send() provide karna padega." CONTRACT

class EmailNotification implements notification{
    public void send(String message){
        System.out.println("Sending Email..."+message);
    }
}
class SMSNotification implements notification {

    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
class PushNotification implements notification {

    public void send(String message) {
        System.out.println("Sending Push: " + message);
    }
}

class NotificationFactory{
    static notification createNotification(String type){
        if(type.equals("EMAIL")){
            return new EmailNotification();
        }else if(type.equals("SMS")){
            return new SMSNotification();
        }else if(type.equals("PUSH")){
            return new PushNotification();
        }
        return null;
    }
}
public class Factory{
    public static void main(String[]args){
       notification n;
       n=NotificationFactory.createNotification("EMAIL"); 
       n.send(" i am mail ");
       n=NotificationFactory.createNotification("SMS");
       n.send(" i am sms ");
       n=NotificationFactory.createNotification("PUSH");
       n.send(" i am push ");
    }
}