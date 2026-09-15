import java.util.ArrayList;


// ============================================================
// VEHICLE TYPE
// ============================================================

enum VehicleType {
    CAR,
    TRUCK,
    BUS
}


// ============================================================
// VEHICLE
// ============================================================

class Vehicle {

    private String number;
    private VehicleType type;

    Vehicle(String num, VehicleType t) {
        this.number = num;
        this.type = t;
    }

    String getNumber() {
        return number;
    }

    VehicleType getVehicleType() {
        return type;
    }
}


// ============================================================
// VEHICLE FACTORY
// ============================================================

class VehicleFactory {

    // Factory is responsible for creating Vehicle objects.
    // Client does not need to use "new Vehicle()" directly.

    static Vehicle createVehicle(String number, VehicleType type) {

        // Currently Vehicle is one generic class,
        // so Factory creates Vehicle using the given type.

        return new Vehicle(number, type);
    }
}


// ============================================================
// PARKING SLOT
// ============================================================

class ParkingSlot {

    private int slotNo;
    private Vehicle v;

    ParkingSlot(int sno) {
        this.slotNo = sno;
    }

    boolean isAvailable() {
        return v == null;
    }

    void parkVehicle(Vehicle v) {

        if (isAvailable()) {
            this.v = v;
        }
    }

    void removeVehicle() {

        if (!isAvailable()) {
            this.v = null;
        }
    }
}


// ============================================================
// PARKING LOT
// ============================================================

class ParkingLot {

    // Singleton instance
    private static ParkingLot instance;

    private ArrayList<ParkingSlot> slots = new ArrayList<>();


    // Private constructor:
    // Nobody outside this class can directly create ParkingLot.
    private ParkingLot(int size) {

        for (int i = 1; i <= size; i++) {
            slots.add(new ParkingSlot(i));
        }
    }


    // Singleton access point
    public static ParkingLot getInstance(int size) {

        if (instance == null) {
            instance = new ParkingLot(size);
        }

        return instance;
    }


    // Find first available slot and park vehicle
    boolean parkVehicle(Vehicle v) {

        for (ParkingSlot s : slots) {

            if (s.isAvailable()) {

                s.parkVehicle(v);
                return true;
            }
        }

        return false;
    }
}


// ============================================================
// MAIN
// ============================================================

public class parkinglot {

    public static void main(String[] args) {


        // ====================================================
        // VEHICLES CREATED USING FACTORY
        // ====================================================

        Vehicle v1 = VehicleFactory.createVehicle(
                "GJ01AB1234",
                VehicleType.CAR
        );

        Vehicle v2 = VehicleFactory.createVehicle(
                "GJ01AB5678",
                VehicleType.BUS
        );

        Vehicle v3 = VehicleFactory.createVehicle(
                "GJ01AB9999",
                VehicleType.TRUCK
        );


        // ====================================================
        // PARKING SLOT TEST
        // ====================================================

        System.out.println("===== Parking Slot Test =====");

        ParkingSlot ps = new ParkingSlot(101);

        if (ps.isAvailable()) {

            System.out.println("Slot is available");

            ps.parkVehicle(v1);

            System.out.println("Vehicle parked successfully");

        } else {

            System.out.println("Slot is occupied");
        }


        if (ps.isAvailable()) {

            System.out.println("Slot is available");

        } else {

            System.out.println("Slot is occupied");
        }


        ps.removeVehicle();


        if (ps.isAvailable()) {

            System.out.println("Slot is available again");

        } else {

            System.out.println("Slot is occupied");
        }


        // ====================================================
        // PARKING LOT TEST
        // ====================================================

        System.out.println("\n===== Parking Lot Test =====");


        // Singleton:
        // getInstance() returns the same ParkingLot object.
        ParkingLot lot = ParkingLot.getInstance(2);


        // Vehicle 1
        if (lot.parkVehicle(v1)) {

            System.out.println("v1 parked");

        } else {

            System.out.println("Parking Full");
        }


        // Vehicle 2
        if (lot.parkVehicle(v2)) {

            System.out.println("v2 parked");

        } else {

            System.out.println("Parking Full");
        }


        // Vehicle 3
        // Lot size = 2, so this should fail.
        if (lot.parkVehicle(v3)) {

            System.out.println("v3 parked");

        } else {

            System.out.println("Parking Full");
        }
    }
}