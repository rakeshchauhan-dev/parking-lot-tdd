package org.example;

public class ParkingOwner {

    boolean parkingIsFull;

    public ParkingOwner( boolean parkingIsFull) {
        this.parkingIsFull = parkingIsFull;
    }

    public void notifyParkingOwner(boolean parkingIsFull){
        this.parkingIsFull = parkingIsFull;
    }
}
