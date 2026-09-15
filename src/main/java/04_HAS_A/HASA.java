class Address{
    private String city;
    private String state;
    Address(String city,String state){
        this.city=city;
        this.state=state;
    }
    String getCity(){
        return city;
    }
    String getState(){
        return state;
    }
}
class Employee{
    private int id;
    private String name;
    private Address a;
    Employee(int id,String name,Address a){
        this.id=id;
        this.name=name;
        this.a=a;
    }
    int getId(){return id;}
    String getName(){return name;}
    void addressDisplay(){
        System.out.println("city is.."+a.getCity());
        System.out.println("state is.."+a.getState());
    }
    void display(){
        System.out.println("id is.."+getId());
        System.out.println("name is.."+getName());
        System.out.println("address is..");
        addressDisplay();
    }
}

public class HASA{
    public static void main(String[]args){
        Address a1=new Address("rajkot","gujarat");
        Employee e1=new Employee(101,"shivam",a1);
        e1.display();
    }
}