package CAR_RENTAL;

import CAR_RENTAL.Product.Vehicle;
import CAR_RENTAL.Product.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Store {
    int sotreId;
    VehicleInventoryManagement inventoryManagement;
    Location_ storeLocation;
    List<Reservation> reservations = new ArrayList<>();

    public List<Vehicle> getVehicles(VehicleType vehicleType) {
        return inventoryManagement.getVehicle();
    }

    public void setStoreId(int id) {
        this.sotreId = id;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        inventoryManagement = new VehicleInventoryManagement(vehicles);
    }

    public boolean completeReservation(int reservation) {
        return true;
    }

    public Reservation createReservation(Vehicle vehicle, User user) {
        Reservation reservation = new Reservation();
        reservation.createReservation(user, vehicle);
        reservations.add(reservation);
        return reservation;
    }
}