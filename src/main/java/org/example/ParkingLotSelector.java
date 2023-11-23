package org.example;

import java.util.List;

public interface ParkingLotSelector {
    public  ParkingLot select(List<ParkingLot> parkingLotList) throws ParkingNotAvailableException;
}
