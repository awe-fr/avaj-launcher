package sources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Helicopter extends Aircraft {
	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("simulation.txt", true));
			writer.write("Tower: Helicopter#" + p_name + "(" + p_id + ") registered to weather tower.\n");
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
			writer.write("Helicopter#" + name + "(" + id + "): ");
			switch (nWeather) {
				case "RAIN":
					x += 5;
					writer.write("It's rainy out there\n");
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "FOG":
					x += 1;
					writer.write("Oh no there is skibidifog\n");
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "SUN":
					x += 10;
					z += 2;
					if (z > 100) {
						z = 100;
					}
					writer.write("This is hot.\n");
					writer.close();
					coordinates = new Coordinates(x, y, z);
					break;
				case "SNOW":
					z -= 12;
					writer.write("My rotor is going to freeze!\n");
					if (z <= 0) {
						z = 0;
						writer.write("Helicopter#" + name + "(" + id + "): Landing.\n");
						writer.write("Tower: Helicopter#" + name + "(" + id + ") unregistered to weather tower.\n");
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
