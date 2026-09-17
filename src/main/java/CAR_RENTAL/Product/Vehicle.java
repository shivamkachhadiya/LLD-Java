package CAR_RENTAL.Product;

import ELEVETOR.Status;

import java.util.Date;

public class Vehicle {
    int vehicleId;
    int vehicleNumber;
    VehicleType vehicleType;
    String companyName;
    String modelName;
    int kmDriven;
    Date manufacturDate;
    int avrage;
    int cc;
    int dailyRentalCost;
    int hourlyRentalCost;
    int noOfSeat;
    Status status;

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public void setVehicleID(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    public void setManufacturDate(Date manufacturDate) {
        this.manufacturDate = manufacturDate;
    }

    public void setAvrage(int avrage) {
        this.avrage = avrage;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setNoOfSeat(int noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    public void setDailyRentalCost(int dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }

    public void setHourlyRentalCost(int hourlyRentalCost) {
        this.hourlyRentalCost = hourlyRentalCost;
    }

    public Date getManufacturDate() {
        return manufacturDate;
    }

    public int getAvrage() {
        return avrage;
    }

    public int getCc() {
        return cc;
    }

    public int getDailyRentalCost() {
        return dailyRentalCost;
    }

    public int getHourlyRentalCost() {
        return hourlyRentalCost;
    }

    public int getNoOfSeat() {
        return noOfSeat;
    }

    public Status getStatus() {
        return status;
    }


    public int getVehicleId(){
        return vehicleId;
    }
}
