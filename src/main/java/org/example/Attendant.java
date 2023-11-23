package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Attendant {
    List<ParkingLot> parkingLotList;

    public Attendant() {
        this.parkingLotList = new ArrayList<>();
    }

    public void assignParkingLot(ParkingLot parkingLot) {
       this.parkingLotList.add(parkingLot);
    }

    public void park(Vehicle vehicle) throws CarIsAlreadyParkedException, ParkingNotAvailableException {
        if (isCarAlreadyParked(vehicle)){
            throw new CarIsAlreadyParkedException();
        }
        FirstAvailableParkingLotStrategy firstAvailableParkingLotStrategy = new FirstAvailableParkingLotStrategy();
        ParkingLot availableParkingLot = firstAvailableParkingLotStrategy.select(parkingLotList);
        availableParkingLot.park(vehicle);
    }

    public void unPark(Vehicle vehicle) throws CarIsNotParkedException {
        Optional<ParkingLot> optionalParkingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.isPark(vehicle))
                .findFirst();
        if(optionalParkingLot.isPresent()){
            optionalParkingLot.get().unPark(vehicle);
        }else{
            throw new CarIsNotParkedException();
        }
    }

    public boolean isCarAlreadyParked(Vehicle vehicle){
        Optional<ParkingLot> optionalParkingLot = this.parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.isPark(vehicle))
                .findFirst();

        return optionalParkingLot.isPresent();
    }
}
