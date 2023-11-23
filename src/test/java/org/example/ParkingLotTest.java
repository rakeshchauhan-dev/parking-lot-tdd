package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ParkingLotTest {
    private ParkingLotObserver observer;

    @BeforeEach
    void setUp() {
        observer = mock(ParkingLotObserver.class);
    }
    @Test
    void shouldThrowErrorWhenParkingIsNotAvailable() {
        ParkingLot parking = new ParkingLot(0);
        Assertions.assertThrows(ParkingNotAvailableException.class, () -> {parking.park(new Vehicle());});
    }

    @Test
    void shouldNotThrowErrorWhenParkingIsAvailable() throws ParkingNotAvailableException, CarIsAlreadyParkedException {
        ParkingLot parking = new ParkingLot(1);
        parking.park(new Vehicle());
        Assertions.assertTrue(true);
    }

    @Test
    void shouldThrowErrorIfCarIsAlreadyParked() throws ParkingNotAvailableException, CarIsAlreadyParkedException {
        ParkingLot parking = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        Assertions.assertThrows(CarIsAlreadyParkedException.class, () -> {parking.park(vehicle);});
    }

    @Test
    void shouldUnParkVehicleIfItsAlreadyPresent() throws ParkingNotAvailableException, CarIsAlreadyParkedException, CarIsNotParkedException {
        ParkingLot parking = new ParkingLot(10);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
        parking.unPark(vehicle);
        Assertions.assertTrue(true);
    }

    @Test
    void shouldThrowErrorIfVehicleIsNotPresentInParking() throws ParkingNotAvailableException, CarIsAlreadyParkedException, CarIsNotParkedException {
        ParkingLot parking = new ParkingLot(10);
        Vehicle vehicle = new Vehicle();
        Assertions.assertThrows(CarIsNotParkedException.class, () -> {parking.unPark(vehicle);;});
    }

    @Test
    void shouldNotifyOwnerIfParkingIsFull() throws ParkingNotAvailableException, CarIsAlreadyParkedException {
        ParkingLot parking = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();
        parking.park(vehicle);
    }

    @Test
    void shouldNotifyOwnerIfParkingBecomeAvailable() throws ParkingNotAvailableException, CarIsAlreadyParkedException, CarIsNotParkedException {
        ParkingLot parking = new ParkingLot(2);
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        parking.addObserver(observer);
        parking.park(vehicle1);
        parking.park(vehicle2);
        verify(observer, times(1)).notifyParkingFull();
        parking.unPark(vehicle2);
        verify(observer, times(1)).notifyParkingAvailable();
        parking.unPark(vehicle1);
    }
}
