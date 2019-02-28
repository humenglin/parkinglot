package parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

	@Override
	public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) throws ParkingLotIsFullException {
		List<Integer> emptyParkingSpaces = new ArrayList<Integer>();
		for(int i = 0; i < parkingLots.size(); i++) {
			ParkingLot parkingLot = parkingLots.get(i);
			emptyParkingSpaces.add(parkingLot.emptyParkingSpace());
		}
		int theMaxEmptyParkingSpace = Collections.max(emptyParkingSpaces);
		int theMaxIndex = emptyParkingSpaces.indexOf(theMaxEmptyParkingSpace);

		return parkingLots.get(theMaxIndex);
	}

}
