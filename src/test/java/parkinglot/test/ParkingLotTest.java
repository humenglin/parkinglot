package parkinglot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import parkinglot.Car;
import parkinglot.GraduateParkingBoy;
import parkinglot.ParkingBoy;
import parkinglot.ParkingLot;
import parkinglot.ParkingLotIsFullException;
import parkinglot.ParkingManager;
import parkinglot.SmartParkingBoy;
import parkinglot.SuperParkingBoy;
import parkinglot.TheTicketIsValidException;
import parkinglot.Ticket;

public class ParkingLotTest {
	
	@Test
	public void should_return_a_ticket_when_park_given_parkinglot_with_a_space() throws ParkingLotIsFullException {
		ParkingLot parkingLot = new ParkingLot(1);
		assertNotNull(parkingLot.park(new Car()));
	}
	
	@Test(expected=ParkingLotIsFullException.class)
	public void should_return_null_when_park_given_parkinglot_without_any_space() throws ParkingLotIsFullException {
		ParkingLot parkingLot = new ParkingLot(0);
		parkingLot.park(new Car());
	}

	@Test
	public void should_return_car_when_pick_use_a_ticket_given_parkinglot_with_this_car() throws ParkingLotIsFullException, TheTicketIsValidException {
		ParkingLot parkingLot = new ParkingLot(10);
		Car car = new Car();
		Ticket ticket = parkingLot.park(car);
		assertEquals(car, parkingLot.pick(ticket));
	}
	
	@Test(expected=TheTicketIsValidException.class)
	public void should_return_exception_when_pick_use_a_valid_ticket_given_parkinglot() throws ParkingLotIsFullException, TheTicketIsValidException {
		ParkingLot parkingLot = new ParkingLot(10);
		Car car = new Car();
		parkingLot.park(car);
		Ticket theValidTicket = new Ticket();
		parkingLot.pick(theValidTicket);
	}
	
	@Test(expected=TheTicketIsValidException.class)
	public void should_return_exception_when_pick_twice_use_a_ticket_given_parkinglot_with_the_car() throws ParkingLotIsFullException, TheTicketIsValidException {
		ParkingLot parkingLot = new ParkingLot(10);
		Car car = new Car();
		Ticket ticket = parkingLot.park(car);
		parkingLot.pick(ticket);
		parkingLot.pick(ticket);
	}
	
	@Test
	public void should_return_ticket_when_a_graduate_parking_boy_parks_the_car_given_many_parkinglots() throws ParkingLotIsFullException {
		Car car = new Car();
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		ParkingLot parkingLotA = new ParkingLot(5);
		ParkingLot parkingLotB = new ParkingLot(0);
		ParkingLot parkingLotC = new ParkingLot(1);
		parkingLots.add(parkingLotA);
		parkingLots.add(parkingLotB);
		parkingLots.add(parkingLotC);
		GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();
		assertEquals(parkingLotA, graduateParkingBoy.chooseParkingLot(parkingLots));
		Ticket ticket = graduateParkingBoy.park(car, parkingLots);
	}
	
	@Test
	public void should_return_car_when_pick_use_ticket_when_after_a_graduate_parking_boy_park() throws ParkingLotIsFullException, TheTicketIsValidException {
		Car car = new Car();
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		ParkingLot parkingLotA = new ParkingLot(5);
		ParkingLot parkingLotB = new ParkingLot(0);
		ParkingLot parkingLotC = new ParkingLot(1);
		parkingLots.add(parkingLotA);
		parkingLots.add(parkingLotB);
		parkingLots.add(parkingLotC);
		GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();
		Ticket ticket = graduateParkingBoy.park(car, parkingLots);
		assertEquals(car, graduateParkingBoy.pick(ticket, parkingLots));
	}
	
	@Test
	public void should_return_ticket_when_a_smart_parking_boy_parks_the_car_given_many_parkinglots() throws ParkingLotIsFullException {
		Car car = new Car();
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		ParkingLot parkingLotA = new ParkingLot(3);
		parkingLotA.park(new Car());
		ParkingLot parkingLotB = new ParkingLot(3);
		ParkingLot parkingLotC = new ParkingLot(1);
		parkingLots.add(parkingLotA);
		parkingLots.add(parkingLotB);
		parkingLots.add(parkingLotC);
		SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
		assertEquals(parkingLotB, smartParkingBoy.chooseParkingLot(parkingLots));
		Ticket ticket = smartParkingBoy.park(car, parkingLots);
	}
	
	@Test
	public void should_return_ticket_when_a_super_parking_boy_parks_the_car_given_many_parkinglots() throws ParkingLotIsFullException {
		Car car = new Car();
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		ParkingLot parkingLotA = new ParkingLot(3);
		parkingLotA.park(new Car());
		parkingLotA.park(new Car());
		ParkingLot parkingLotB = new ParkingLot(3);
		parkingLotB.park(new Car());
		ParkingLot parkingLotC = new ParkingLot(1);
		parkingLots.add(parkingLotA);
		parkingLots.add(parkingLotB);
		parkingLots.add(parkingLotC);
		SuperParkingBoy superParkingBoy = new SuperParkingBoy();
		assertEquals(parkingLotC, superParkingBoy.chooseParkingLot(parkingLots));
		Ticket ticket = superParkingBoy.park(car, parkingLots);
	}
	
	@Test
	public void should_return_a_ticket_when_park_a_car_given_parking_manager_manages_parking_boys() {
		List<ParkingBoy> parkingBoys = new ArrayList<ParkingBoy>();
		ParkingManager parkingManager = new ParkingManager();
		GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();
		SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
		SuperParkingBoy superParkingBoy = new SuperParkingBoy();
		parkingBoys.add(parkingManager);
		parkingBoys.add(graduateParkingBoy);
		parkingBoys.add(smartParkingBoy);
		parkingBoys.add(superParkingBoy);
		parkingManager.manageParkingBoy(parkingBoys);
		assertTrue(parkingManager.manageParkingBoy(parkingBoys) instanceof ParkingBoy);
	}
	
}