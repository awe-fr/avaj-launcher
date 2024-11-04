package sources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JetPlane extends Aircraft {
	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("simulation.txt", true));
			writer.write("Tower: JetPlane#" + p_name + "(" + p_id + ") registered to weather tower.\n");
			writer.close();
		} catch (IOException e) {
			File fichier = new File("simulation.txt");
			fichier.delete();
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void updateConditions() throws WrongTypeException, LandException {
		Coordinates oCoordinates = coordinates;
		WeatherProvider louisBodin = WeatherProvider.getInstance();
		String nWeather = louisBodin.getCurrentWeather(oCoordinates);
		int x = oCoordinates.getLongitude();
		int y = oCoordinates.getLatitude();
		int z = oCoordinates.getHeight();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("simulation.txt", true));
			writer.write("JetPlane#" + name + "(" + id + "): ");
			switch (nWeather) {
				case "RAIN":
					y += 5;
					writer.write("It's raining. Better watch out for lightings.\n");
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "FOG":
					y += 1;
					writer.write("No more visibility.\n");
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "SUN":
					y += 10;
					z += 2;
					if (z > 100) {
						z = 100;
					}
					writer.write("What a beautiful weather.\n");
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "SNOW":
					z -= 7;
					writer.write("OMG! Winter is coming!\n");
					if (z <= 0) {
						z = 0;
						writer.write("JetPlane#" + name + "(" + id + "): Landing.\n");
						writer.write("Tower: JetPlane#" + name + "(" + id + ") unregistered to weather tower.\n");
						writer.close();
						throw new LandException("Landing\n");
					}
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				default:
					writer.close();
					throw new WrongTypeException("This weather dosen't exist.\n");
			}
		} catch (IOException e) {
			File fichier = new File("simulation.txt");
			fichier.delete();
			e.printStackTrace();
			System.exit(0);
		}
	}
}
