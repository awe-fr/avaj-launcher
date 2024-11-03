package sources;

public final class AircraftFactory {
	private static AircraftFactory instance;

	private AircraftFactory() {

	}

	public static AircraftFactory getInstance() {
		if (instance == null) {
			instance = new AircraftFactory();
		}
		return instance;
	}

	// Flyable newAircraft(String p_type, String p_name, Coordinates p_Coordinates) {

	// }
}
