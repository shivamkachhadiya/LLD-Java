package CAR_RENTAL;

import CAR_RENTAL.Product.Vehicle;

import javax.xml.stream.Location;
import java.util.*;

public class Reservation {
    int reservationId;
    User user;
    Vehicle vehicle;
    Date dateBookingDate;
    Date dateBookedFrom;
    Date dateBookedTo;
    Long fromTimeStamp;
    Long toTimeStamp;
    Location pickUpLocation;
    Location dropLocation;
    ReservationType reservationType;
    ReservationStatus reservationStatus;
    Location location;
    public int createReservation(User user,Vehicle vehicle){
        reservationId=12322;
        this.user=user;
        this.vehicle=vehicle;
        reservationType=ReservationType.DAILY;
        reservationStatus=ReservationStatus.SCHEDULED;
        return reservationId;

    }

    public int getReservationId(){
        return reservationId;
    }
}
