package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttendantTest {
    @Test
    void shouldParkCarIfAvailable() throws CarIsAlreadyParkedException, ParkingNotAvailableException {
        Attendant attendant = new Attendant();
        Vehicle vehicle = new Vehicle();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assignParkingLot(parkingLot);
        attendant.park(vehicle);
        Assertions.assertTrue(true);
    }

    @Test
    void shouldThrowErrorIfParkingNotAvailable() throws CarIsAlreadyParkedException, ParkingNotAvailableException {
        Attendant attendant = new Attendant();
        Vehicle vehicle = new Vehicle();
        ParkingLot parkingLot = new ParkingLot(0);
        attendant.assignParkingLot(parkingLot);
        Assertions.assertThrows(ParkingNotAvailableException.class, () -> {attendant.park(vehicle);});
    }

    @Test
    void shouldParkCarInSecondParkingLot() throws CarIsAlreadyParkedException, ParkingNotAvailableException {
        Attendant attendant = new Attendant();
        Vehicle vehicle = new Vehicle();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);
        attendant.park(vehicle);
        Assertions.assertTrue(true);
    }

    @Test
    void shouldUnParkCarIfCarIsPark() throws CarIsNotParkedException, ParkingNotAvailableException, CarIsAlreadyParkedException {
        Attendant attendant = new Attendant();
        Vehicle vehicle = new Vehicle();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);
        attendant.park(vehicle);
        attendant.unPark(vehicle);
        Assertions.assertTrue(true);
    }

    @Test
    void shouldThrowErrorIfCarIsNotParked() throws ParkingNotAvailableException, CarIsAlreadyParkedException {
        Attendant attendant = new Attendant();
        Vehicle vehicle = new Vehicle();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);
        attendant.park(new Vehicle());
        Assertions.assertThrows(CarIsNotParkedException.class, () -> {attendant.unPark(vehicle);;});
    }
}
