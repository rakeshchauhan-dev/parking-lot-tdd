package org.example;

import java.util.HashSet;

public class ParkingLot {

    int capacity;
    HashSet<Vehicle> carParked;
    ParkingOwner parkingOwner;
    public ParkingLot(int capacity) {

        this.capacity = capacity;
        this.carParked = new HashSet<Vehicle>();
        this.parkingOwner = new ParkingOwner(false);
    }

    public void park(Vehicle vehicle) throws ParkingNotAvailableException, CarIsAlreadyParkedException {
            if (this.carParked.size() >= this.capacity){
                throw new ParkingNotAvailableException();
            } else if (this.carParked.contains(vehicle)) {
                throw new CarIsAlreadyParkedException();
            } else {
                this.carParked.add(vehicle);
                if (this.carParked.size() == this.capacity){
                    this.parkingOwner.notifyParkingOwner(true);
                }
            }
    }

    public void unPark(Vehicle vehicle) throws  CarIsNotParkedException {
        boolean isRemoved = this.carParked.remove(vehicle);
        if (!isRemoved) {
            throw new CarIsNotParkedException();
        } else {
            if (this.carParked.size() == this.capacity - 1){
                this.parkingOwner.notifyParkingOwner(false);
            }
        }
    }
}
