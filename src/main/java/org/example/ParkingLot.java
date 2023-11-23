package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParkingLot implements ParkingLotObservable {

    int capacity;
    HashSet<Vehicle> carParked;
    List<ParkingLotObserver> observers;
    public ParkingLot(int capacity) {

        this.capacity = capacity;
        this.carParked = new HashSet<Vehicle>();
        this.observers = new ArrayList<>();
    }

    public void park(Vehicle vehicle) throws ParkingNotAvailableException, CarIsAlreadyParkedException {
            if (this.carParked.size() >= this.capacity){
                throw new ParkingNotAvailableException();
            } else if (this.carParked.contains(vehicle)) {
                throw new CarIsAlreadyParkedException();
            } else {
                this.carParked.add(vehicle);
                if (this.carParked.size() == this.capacity){
                    observers.forEach(observer -> observer.notifyParkingFull());
                }
            }
    }

    public void unPark(Vehicle vehicle) throws  CarIsNotParkedException {
        boolean isRemoved = this.carParked.remove(vehicle);
        if (!isRemoved) {
            throw new CarIsNotParkedException();
        } else {
            if (this.carParked.size() == this.capacity - 1){
                observers.forEach(observer -> observer.notifyParkingAvailable());
            }
        }
    }

    public boolean isParkingAvailable() {
        return (this.carParked.size() < this.capacity);
    }
    @Override
    public void addObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

}
