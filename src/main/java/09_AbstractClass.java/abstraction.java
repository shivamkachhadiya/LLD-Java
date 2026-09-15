    //          Payment
    //       abstract pay()
    //            |
    //     ┌──────┴──────┐
    //     ↓             ↓
    //    UPI           Card
    //   pay()          pay()
abstract class Payment{
    abstract void pay();
    void recipt(){
        System.out.println("payment receipt generated..");
    }
}
class UPI extends Payment{
    @Override
    void pay(){
        System.out.println("Payment through upi..");
    }
}
class Card extends Payment{
    @Override
    void pay(){
        System.out.println("Payment through CARD.");
    }
}
public class abstraction{
    public static void main(String[]args){
        Payment p;
        p=new UPI();
        p.pay();
        p.recipt();
        p=new Card();
        p.pay();
        p.recipt();
    }
}