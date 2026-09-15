// 1️⃣ Constructor → private
//    ↓
//    Bahar se `new` karke object nahi bana sakte.

// 2️⃣ Instance → private static
//    ↓
//    Class level par ONE shared reference.

// 3️⃣ getInstance() → public static
//    ↓
//    Agar instance null hai → object create karo.
//    Warna → existing object return karo.

// 4️⃣ Result
//    ↓
//    Puri application ko SAME object milega.

// class Singleton {

//     private static Singleton instance;

//     private Singleton() {}

//     public static Singleton getInstance() {

//         if (instance == null) {
//             instance = new Singleton();
//         }

//         return instance;
//     }
// }


class Logger {

    // 1. One shared reference
    private static Logger instance;

    // 2. Prevent outside object creation
    private Logger() {
    }

    // 3. Global access point
    public static Logger getInstance() {

        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

public class singleton {

    public static void main(String[] args) {

        Logger logger1 = Logger.getInstance();

        Logger logger2 = Logger.getInstance();

        logger1.log("User created");

        logger2.log("Payment successful");

        System.out.println(logger1 == logger2);
    }
}