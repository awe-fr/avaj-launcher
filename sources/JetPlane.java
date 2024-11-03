package sources;

public class JetPlane extends Aircraft {
	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void updateConditions() throws WrongTypeException, LandException {
		Coordinates oCoordinates = coordinates;
		WeatherProvider louisBodin = WeatherProvider.getInstance();
		String nWeather = louisBodin.getCurrentWeather(oCoordinates);
		int x = oCoordinates.getLongitude();
		int y = oCoordinates.getLatitude();
		int z = oCoordinates.getHeight();
		switch (nWeather) {
			case "RAIN":
				y += 5;
				coordinates = new Coordinates(x, y, z);
				break;
			case "FOG":
				y += 1;
				coordinates = new Coordinates(x, y, z);
				break;
			case "SUN":
				y += 10;
				z += 2;
				if (z > 100) {
					z = 100;
				}
				coordinates = new Coordinates(x, y, z);
				break;
			case "SNOW":
				z -= 7;
				if (z <= 0) {
					z = 0;
					throw new LandException("Landing\n");
				}
				coordinates = new Coordinates(x, y, z);
				break;
			default:
				throw new WrongTypeException("This weather dosen't exist.\n");
		}
	}
}
