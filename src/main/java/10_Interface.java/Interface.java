interface Payment{
    void pay();
}
class UPI implements Payment{
    @Override
    public void pay(){
        System.out.println("Payment through upi");
    }
}
class Card implements Payment{
    @Override
    public void pay(){
        System.out.println("payment through card...");
    }
}
public class Interface{
    public static void main(String[]args){
        Payment p;
        p=new UPI();
        p.pay();
        p=new Card();
        p.pay();
    }
}