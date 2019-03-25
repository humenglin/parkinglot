package parkinglot;

public class ParkingRecord {

	private String carPlate;
	private String parkingPerson;

	public ParkingRecord(String parkingPerson, String carPlate) {
		this.parkingPerson = parkingPerson;
		this.carPlate = carPlate;
	}

	public String getParkingPerson() {
		return parkingPerson;
	}

	public String getCarPlate() {
		return carPlate;
	}

}
