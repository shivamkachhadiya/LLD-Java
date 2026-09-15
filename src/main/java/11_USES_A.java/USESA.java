class Vehicle{
    private String number;
    Vehicle(String number){
        this.number=number;
    }
    String getNumber(){
        return number;
    }
}
class ParkingSlot{
    private int slotNumber;
    ParkingSlot(int slotNumber){
        this.slotNumber=slotNumber;
    }
    void parkVehicle(Vehicle v){
        System.out.println("vehicle "+v.getNumber()+" parked in slot"+slotNumber);
    }
}
public class USESA{
    public static void main(String[]args){
        Vehicle v=new Vehicle("GJ01AB1234");
        ParkingSlot slot=new ParkingSlot(10);
        slot.parkVehicle(v);
    }
}