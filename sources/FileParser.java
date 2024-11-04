package sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {
	private Simulation sim;

	public void parseFile (String path) {
		sim = new Simulation();
		try {
			File file = new File(path);
			Scanner reader = new Scanner(file);
			checkRuntime(reader, file);
			while (reader.hasNextLine()) {
				checkHairplane(reader, file);
			}
			reader.close();
			sim.lunch();
		} catch (FileNotFoundException e) {
			System.out.println("Error : file not found");
			e.printStackTrace();
			sim.deleteFile();
			System.exit(0);
		}

	}

	public void checkRuntime(Scanner reader, File file) {
		try {
			String line = reader.nextLine();
			int time =Integer.parseInt(line);
			if (time <= 0) {
				System.err.println("Error : Runtime have to be highter than 0.");
				System.err.println(line);
				sim.deleteFile();
				System.exit(0);
			}
			sim.addRuntime(time);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			sim.deleteFile();
			System.exit(0);
		}
	}

	public void checkHairplane(Scanner reader, File file) {
		try {
			AircraftFactory factory = AircraftFactory.getInstance();
			String line = reader.nextLine();
			String[] infos = line.split(" ");
			if (infos.length == 5) {
				int x =Integer.parseInt(infos[2]);
				int y =Integer.parseInt(infos[3]);
				int z =Integer.parseInt(infos[4]);
				if (z > 100) {
					z = 100;
				} else if (z <= 0) {
					System.err.println("Error : Height axis have to be highter than 0.");
					System.err.println(line);
					sim.deleteFile();
					System.exit(0);
				}
				Coordinates coor = new Coordinates(x, y, z);
				Flyable fly = factory.newAircraft(infos[0], infos[1], coor);
				sim.addFlyable(fly);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			sim.deleteFile();
			System.exit(0);
		} catch (WrongTypeException e) {
			e.printStackTrace();
			sim.deleteFile();
			System.exit(0);
		}
	}
}
