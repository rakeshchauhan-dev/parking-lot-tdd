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
        Optional<ParkingLot> optionalParkingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.isParkingAvailable())
                .findFirst();

        if(optionalParkingLot.isPresent()){
            optionalParkingLot.get().park(vehicle);
        }else{
            throw new ParkingNotAvailableException();
        }
    }
}
