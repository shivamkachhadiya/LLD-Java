package CAR_RENTAL;

import CAR_RENTAL.Product.Vehicle;

import java.util.List;

public class VehicleInventoryManagement {
    List<Vehicle>vehicles;
    VehicleInventoryManagement(List<Vehicle>vehicles){
        this.vehicles=vehicles;
    }
    public List<Vehicle>getVehicles(){
        return vehicles;
    }
    public void setVehicles(List<Vehicle>vehicles){
        this.vehicles=vehicles;
    }

    public List<Vehicle> getVehicle(){
        return vehicles;
    }
}
