package sources;

public final class AircraftFactory {
	private static AircraftFactory instance;
	private static int idCount;

	private AircraftFactory() {
		idCount = 0;
	}
	
	public static AircraftFactory getInstance() {
		if (instance == null) {
			instance = new AircraftFactory();
		}
		return instance;
	}

	Flyable newAircraft(String p_type, String p_name, Coordinates p_Coordinates) throws WrongTypeException {
		idCount += 1;
		switch (p_type) {
			case "Helicopter":
				return new Helicopter(idCount, p_name, p_Coordinates);
			case "JetPlane":
				return new JetPlane(idCount, p_name, p_Coordinates);
			case "Baloon":
				return new Baloon(idCount, p_name, p_Coordinates);
			default:
				throw new WrongTypeException("Aircraft type have to be a Helicopter, JetPlane or a Baloon.\n");
		}
	}
}
