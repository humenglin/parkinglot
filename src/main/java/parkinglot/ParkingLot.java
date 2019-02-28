package parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
	private final int space;
	protected Map<Ticket, Car> parkedCars = new HashMap<Ticket, Car>();

	public ParkingLot(int space) {
		this.space = space;
	}
	
	public Ticket park(Car car) throws ParkingLotIsFullException {
		if (!isNotFull()) {
			throw new ParkingLotIsFullException();
		}
		Ticket ticket = new Ticket();
		parkedCars.put(ticket, car);
		return ticket;
	}

	public Car pick(Ticket ticket) throws TheTicketIsValidException {
		if (!parkedCars.containsKey(ticket)) {
			throw new TheTicketIsValidException();
		}
		
		Car thePickedCar = parkedCars.get(ticket);
		parkedCars.remove(ticket);
		return thePickedCar;
	}

	public boolean isNotFull() {
		if (parkedCars.size() < space) {
			return true;
		}
		return false;
	}
	
	public int emptyParkingSpace() {
		return (space - parkedCars.size());
	}
	
	public double vacancyRate() {
		return (emptyParkingSpace()/(double)space) ;
		
	}
}
