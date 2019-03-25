package parkinglot;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {

	public GraduateParkingBoy(String name, List<ParkingLot> parkingLots) {
		super(name, parkingLots);
	}

	@Override
	public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) throws ParkingLotIsFullException {
		int theParkingLotIndex = -1;
		for (int i = 0; i < parkingLots.size(); i++) {
			ParkingLot parkingLot = parkingLots.get(i);
			if (parkingLot.isNotFull()) {
				theParkingLotIndex = i;
				break;
			}
		}
		if (theParkingLotIndex < 0) {
			throw new ParkingLotIsFullException();
		}else {
			return parkingLots.get(theParkingLotIndex);
		}
	}
}
