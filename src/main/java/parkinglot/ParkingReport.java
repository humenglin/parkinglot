package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingReport {

	public static List<ParkingRecord> parkingRecords = new ArrayList<ParkingRecord>();
	
	public static void record(ParkingRecord parkingRecord) {
		parkingRecords.add(parkingRecord);
	}
	
}
