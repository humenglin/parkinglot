package parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
	private final int space;
	private String name;
	protected Map<Ticket, Car> parkedCars = new HashMap<Ticket, Car>();

	public ParkingLot(String name, int space) {
		this.name = name;
		this.space = space;
	}
	
	public String getName() {
		return name;
	}

	public Ticket park(Car car) throws ParkingLotIsFullException {
		if (!isNotFull()) {
			throw new ParkingLotIsFullException();
		}
		Ticket ticket = new Ticket(this);
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
