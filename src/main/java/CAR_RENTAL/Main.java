package CAR_RENTAL;

import CAR_RENTAL.Product.Car;
import CAR_RENTAL.Product.Vehicle;
import CAR_RENTAL.Product.VehicleType;

import java.util.ArrayList;
import java.util.List;

// DO NOT import javax.xml.stream.Location;
public class Main {

    public static void main(String[] args) {
        List<User> users = addUsers();
        List<Vehicle> vehicles = addVehicles();
        List<Store> stores = addStores(vehicles);

        VehicleRentalSystem rentalSystem = new VehicleRentalSystem(stores, users);

        // 0. User comes
        User user = users.get(0);

        // 1. User searches store based on location
        Location_ location = new Location_(200, "Bangalore", "Bangalore", "Karnataka", "India", 20000);
        Store store = rentalSystem.getStore(location);
        System.out.println("Found Store ID: " + store.sotreId);

// 2. Get vehicles
        List<Vehicle> storeVehicles = store.getVehicles(VehicleType.CAR);
        System.out.println("Available vehicles: " + storeVehicles.size());

// 3. Create reservation
        Reservation reservation = store.createReservation(storeVehicles.get(0), user);
        System.out.println("Reservation created!");

// 4 & 5. Bill and Payment
        Bill bill = new Bill(reservation);
        Payment payment = new Payment();
        payment.payBill(bill); // Add System.out.println inside payBill()



        // 6. Trip completed, submit the vehicle and close the reservation
        store.completeReservation(reservation.getReservationId());
    }

    public static List<Vehicle> addVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle vehicle1 = new Car();
        vehicle1.setVehicleID(1);
        vehicle1.setVehicleType(VehicleType.CAR);

        Vehicle vehicle2 = new Car();
        vehicle2.setVehicleID(2); // Fixed copy-paste bug (was vehicle1)
        vehicle2.setVehicleType(VehicleType.CAR); // Fixed copy-paste bug (was vehicle1)

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        return vehicles;
    }

    public static List<User> addUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUserId(1);

        users.add(user1);
        return users;
    }

    public static List<Store> addStores(List<Vehicle> vehicles) {
        List<Store> stores = new ArrayList<>();
        Store store1 = new Store();
        store1.setStoreId(1);
        store1.setVehicles(vehicles);

        stores.add(store1);
        return stores;
    }
}