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
import parkinglot.ParkingReport;
import parkinglot.SmartParkingBoy;
import parkinglot.SuperParkingBoy;
import parkinglot.TheTicketIsValidException;
import parkinglot.Ticket;

public class ParkingLotTest {
	
	@Test
	public void should_return_a_ticket_when_park_given_parkinglot_with_a_space() throws ParkingLotIsFullException {
		ParkingLot parkingLot = new ParkingLot("", 1);
		
		assertNotNull(parkingLot.park(new Car("")));
	}
	
	@Test(expected=ParkingLotIsFullException.class)
	public void should_return_null_when_park_given_parkinglot_without_any_space() throws ParkingLotIsFullException {
		ParkingLot parkingLot = new ParkingLot("", 0);
		
		parkingLot.park(new Car(""));
	}

	@Test
	public void should_return_car_when_pick_use_a_ticket_given_parkinglot_with_this_car() throws ParkingLotIsFullException, TheTicketIsValidException {
		ParkingLot parkingLot = new ParkingLot("", 10);
		Car car = new Car("");
		Ticket ticket = parkingLot.park(car);
		
		assertEquals(car, parkingLot.pick(ticket));
	}
	
	@Test(expected=TheTicketIsValidException.class)
	public void should_return_exception_when_pick_use_a_valid_ticket_given_parkinglot() throws ParkingLotIsFullException, TheTicketIsValidException {
		ParkingLot parkingLot = new ParkingLot("", 10);
		Car car = new Car("");
		parkingLot.park(car);
		Ticket invalidTicket = new Ticket(parkingLot);
		
		parkingLot.pick(invalidTicket);
	}
	
	@Test(expected=TheTicketIsValidException.class)
	public void should_return_exception_when_pick_twice_use_a_ticket_given_parkinglot_with_the_car() throws ParkingLotIsFullException, TheTicketIsValidException {
		ParkingLot parkingLot = new ParkingLot("", 10);
		Car car = new Car("");
		Ticket ticket = parkingLot.park(car);
		parkingLot.pick(ticket);
		
		parkingLot.pick(ticket);
	}
	
	@Test
	public void should_return_ticket_when_a_graduate_parking_boy_parks_the_car_given_many_parkinglots() throws ParkingLotIsFullException {
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(new ParkingLot("A", 5));
		parkingLots.add(new ParkingLot("B", 5));
		parkingLots.add(new ParkingLot("C", 5));
		GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy("", parkingLots);
		Car car = new Car("");
		Ticket ticket = graduateParkingBoy.park(car);
		
		assertEquals("A", ticket.getOfParkingLot().getName());
	}
	
	@Test
	public void should_return_car_when_pick_use_ticket_when_after_a_graduate_parking_boy_park() throws ParkingLotIsFullException, TheTicketIsValidException {
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(new ParkingLot("A", 5));
		parkingLots.add(new ParkingLot("B", 5));
		parkingLots.add(new ParkingLot("C", 5));
		GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy("", parkingLots);
		Car car = new Car("");
		Ticket ticket = graduateParkingBoy.park(car);
		// parkingLots could be private
		assertEquals(car, graduateParkingBoy.pick(ticket));
	}
	
	@Test
	public void should_return_ticket_when_a_smart_parking_boy_parks_the_car_given_many_parkinglots() throws ParkingLotIsFullException {
		ParkingLot parkingLotA = new ParkingLot("A", 5);
		ParkingLot parkingLotB = new ParkingLot("B", 5);
		parkingLotB.park(new Car(""));
		ParkingLot parkingLotC = new ParkingLot("C", 5);
		parkingLotC.park(new Car(""));
		parkingLotC.park(new Car(""));
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(parkingLotA);
		parkingLots.add(parkingLotB);
		parkingLots.add(parkingLotC);
		SmartParkingBoy smartParkingBoy = new SmartParkingBoy("", parkingLots);
		Car car = new Car("");
		Ticket ticket = smartParkingBoy.park(car);
		
		assertEquals("A", ticket.getOfParkingLot().getName());
	}
	
	@Test
	public void should_return_ticket_when_a_super_parking_boy_parks_the_car_given_many_parkinglots() throws ParkingLotIsFullException {
		ParkingLot parkingLotA = new ParkingLot("A", 5);
		ParkingLot parkingLotB = new ParkingLot("B", 5);
		parkingLotB.park(new Car(""));
		ParkingLot parkingLotC = new ParkingLot("C", 5);
		parkingLotC.park(new Car(""));
		parkingLotC.park(new Car(""));
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(parkingLotA);
		parkingLots.add(parkingLotB);
		parkingLots.add(parkingLotC);
		SuperParkingBoy superParkingBoy = new SuperParkingBoy("", parkingLots);
		Car car = new Car("");
		Ticket ticket = superParkingBoy.park(car);
		
		assertEquals("A", ticket.getOfParkingLot().getName());
	}
	
	@Test
	public void should_return_a_ticket_when_park_a_car_given_parking_manager_finds_a_parking_boy_to_park() throws ParkingLotIsFullException {
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(new ParkingLot("A", 5));
		parkingLots.add(new ParkingLot("B", 5));
		parkingLots.add(new ParkingLot("C", 5));
		List<ParkingBoy> parkingBoys = new ArrayList<ParkingBoy>();
		parkingBoys.add(new GraduateParkingBoy("", parkingLots));
		parkingBoys.add(new SmartParkingBoy("", parkingLots));
		parkingBoys.add(new SuperParkingBoy("", parkingLots));
		ParkingManager parkingManager = new ParkingManager("", parkingLots);
		
		assertNotNull(parkingManager.findParkingBoyToPark(parkingBoys).park(new Car("")));
	}
	
	@Test
	public void should_return_a_ticket_when_park_a_car_given_parking_manager_park_by_himself() throws ParkingLotIsFullException {
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(new ParkingLot("A", 5));
		parkingLots.add(new ParkingLot("B", 5));
		parkingLots.add(new ParkingLot("C", 5));
		ParkingManager parkingManager = new ParkingManager("", parkingLots);
		
		assertNotNull(parkingManager.park(new Car("")));
	}
	
}