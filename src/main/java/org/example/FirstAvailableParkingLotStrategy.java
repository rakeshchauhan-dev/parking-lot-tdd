package org.example;

import java.util.List;
import java.util.Optional;

public class FirstAvailableParkingLotStrategy implements ParkingLotSelector {
    public ParkingLot select(List<ParkingLot> parkingLotList) throws ParkingNotAvailableException {
        Optional<ParkingLot> optionalParkingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.isParkingAvailable())
                .findFirst();
        if(optionalParkingLot.isPresent()){
            return optionalParkingLot.get();
        }else{
            throw new ParkingNotAvailableException();
        }
    }
}
