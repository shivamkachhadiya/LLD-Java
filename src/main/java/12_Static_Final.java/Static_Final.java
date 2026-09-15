class ParkingLot{
    static int totalSlots = 100;

    int slotNumber;

    ParkingLot(int slotNumber){
        this.slotNumber=slotNumber;
    }

    void display(){
        System.out.println("Slot: " + slotNumber);
        System.out.println("Total slots: " + totalSlots);
    }
}

public class StaticFinal{
    public static void main(String[]args){
        ParkingLot p1=new ParkingLot(1);
        ParkingLot p2=new ParkingLot(2);

        p1.display();
        p2.display();

        System.out.println("Total slots: " + ParkingLot.totalSlots);
    }
}