class Vehicle{
    protected String brand;
    Vehicle(String name){
        this.brand=name;
    }
    void start(){
        System.out.println(brand+" vehicle is starting..");
    }
}

class Car extends Vehicle{
    Car(String brand){
        super(brand);
    }
    void drive(){
        System.out.println(brand+" car is driving..");
    }
}

class Cycle extends Vehicle{
    Cycle(String brand){
        super(brand);
    }
    void ride(){
        System.out.println(brand+" cycle is riding..");
    }
}

public class inheritance{
    public static void main(String[]args){
        Car c=new Car("Mahindra Thar");
        c.drive();
        Cycle cycle=new Cycle("A1");
        cycle.ride();
    }
}