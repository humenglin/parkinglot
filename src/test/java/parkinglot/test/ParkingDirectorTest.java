package parkinglot.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import parkinglot.Car;
import parkinglot.GraduateParkingBoy;
import parkinglot.ParkingBoy;
import parkinglot.ParkingLot;
import parkinglot.ParkingLotIsFullException;
import parkinglot.ParkingReport;

public class ParkingDirectorTest {
	@Test
	public void should_have_a_report_with_cars_managed_by_a_parking_boy() throws ParkingLotIsFullException {
		Car car = new Car("abc123");
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(new ParkingLot("P1", 1));
		ParkingBoy parkingBoy = new GraduateParkingBoy("Tom", parkingLots);
		parkingBoy.park(car);
		
		assertEquals("Tom", ParkingReport.parkingRecords.get(0).getParkingPerson());
		assertEquals("abc123", ParkingReport.parkingRecords.get(0).getCarPlate());
	}
}
