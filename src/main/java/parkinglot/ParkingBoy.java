package parkinglot;

import java.util.List;

public class ParkingBoy {
	
	public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) throws ParkingLotIsFullException {
		return null;
	}
	
	public Ticket park(Car car, List<ParkingLot> parkingLots) throws ParkingLotIsFullException {
		ParkingLot parkingLot = chooseParkingLot(parkingLots);
		return parkingLot.park(car);
	}
	
	public Car pick(Ticket ticket, List<ParkingLot> parkingLots) throws TheTicketIsValidException {
		int theValidTicketFlag = -1;
		for (int i = 0; i < parkingLots.size(); i++) {
			ParkingLot parkingLot = parkingLots.get(i);
			if (parkingLot.parkedCars.containsKey(ticket)) {
				theValidTicketFlag = i;
			} 
		}
		if (theValidTicketFlag < 0) {
			throw new TheTicketIsValidException();
		}else {
			return parkingLots.get(theValidTicketFlag).pick(ticket);
		}
	}
	
}
