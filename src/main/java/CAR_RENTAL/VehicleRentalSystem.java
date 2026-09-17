package CAR_RENTAL;

import java.util.List;

public class VehicleRentalSystem {
    List<Store> stores;
    List<User> users;

    public VehicleRentalSystem(List<Store> stores, List<User> users) {
        this.stores = stores;
        this.users = users;
    }

    public Store getStore(Location_ location) {
        // Return store logic based on location
        return stores.get(0);
    }
}