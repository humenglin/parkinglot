package parkinglot;

public class Ticket {
	
	private ParkingLot ofParkingLot;

	
	public Ticket(ParkingLot ofParkingLot) {
		this.ofParkingLot = ofParkingLot;
	}

	public ParkingLot getOfParkingLot() {
		return ofParkingLot;
	}

	public ParkingRecord getParkingRecord() {
		return null;
	}

}
