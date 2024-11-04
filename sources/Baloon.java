package sources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Baloon extends Aircraft {
	public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("simulation.txt", true));
			writer.write("Tower: Baloon#" + p_name + "(" + p_id + ") registered to weather tower.\n");
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
			writer.write("Baloon#" + name + "(" + id + "): ");
			switch (nWeather) {
				case "RAIN":
					z -= 5;
					writer.write("Damn you rain! You messed up my baloon.\n");
					if (z <= 0) {
						z = 0;
						writer.write("Baloon#" + name + "(" + id + "): Landing.\n");
						writer.write("Tower: Baloon#" + name + "(" + id + ") unregistered to weather tower.\n");
						writer.close();
						throw new LandException("Landing\n");
					}
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "FOG":
					z -= 3;
					writer.write("Wow, i'm in a cloud!\n");
					if (z <= 0) {
						z = 0;
						writer.write("Baloon#" + name + "(" + id + "): Landing.\n");
						writer.write("Tower: Baloon#" + name + "(" + id + ") unregistered to weather tower.\n");
						writer.close();
						throw new LandException("Landing\n");
					}
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "SUN":
					x += 2;
					z += 4;
					if (z > 100) {
						z = 100;
					}
					writer.write("Let's enjoy the good weather and take some pics.\n");
					coordinates = new Coordinates(x, y, z);
					writer.close();
					break;
				case "SNOW":
					z -= 15;
					writer.write("It's snowing. We're gonna crash\n");
					if (z <= 0) {
						z = 0;
						writer.write("Baloon#" + name + "(" + id + "): Landing.\n");
						writer.write("Tower: Baloon#" + name + "(" + id + ") unregistered to weather tower.\n");
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
