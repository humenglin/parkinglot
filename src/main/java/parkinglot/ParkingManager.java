package parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingManager extends ParkingBoy {
	
	public ParkingBoy manageParkingBoy(List<ParkingBoy> parkingBoys) {
		int index = (int) (Math.random()*parkingBoys.size());
		return parkingBoys.get(index);
	}

	@Override
	public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) throws ParkingLotIsFullException {
		List<Double> vacancyRates = new ArrayList<Double>();
		for(int i = 0; i < parkingLots.size(); i++) {
			ParkingLot parkingLot = parkingLots.get(i);
			vacancyRates.add(parkingLot.vacancyRate());
		}
		double theMaxVacancyRate = Collections.max(vacancyRates);
		int theMaxIndex = vacancyRates.indexOf(theMaxVacancyRate);

		return parkingLots.get(theMaxIndex);
	}

	
}
