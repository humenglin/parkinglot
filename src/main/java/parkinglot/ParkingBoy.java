package parkinglot;

import java.util.List;

public abstract class ParkingBoy {
	private String parkingBoyName;
	protected List<ParkingLot> parkingLots; 
	
	public ParkingBoy(String parkingBoyName, List<ParkingLot> parkingLots) {
		this.parkingBoyName = parkingBoyName;
		this.parkingLots = parkingLots;
	}

	public String getParkingBoyName() {
		return parkingBoyName;
	}

	public abstract ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) throws ParkingLotIsFullException;
	
	public Ticket park(Car car) throws ParkingLotIsFullException {
		ParkingRecord parkingRecord = new ParkingRecord(parkingBoyName, car.getCarPlate());
		ParkingLot parkingLot = chooseParkingLot(parkingLots);
		ParkingReport.record(parkingRecord);
		return parkingLot.park(car);
	}
	
	public Car pick(Ticket ticket) throws TheTicketIsValidException {
		return ticket.getOfParkingLot().pick(ticket);
	}
	
}
