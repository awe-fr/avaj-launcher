package sources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Simulation {
	private WeatherTower tower;
	private int runtime;

	public Simulation() {
		runtime = 0;
		tower = new WeatherTower();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("simulation.txt", false));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void addRuntime(int time) {
		runtime = time;
	}

	public void addFlyable(Flyable fly) {
		tower.register(fly);
	}

	public void lunch() {
		for(int i = 0; i < runtime; i++) {
			tower.changeWeather();
		}
	}

	public void deleteFile() {
		File fichier = new File("simulation.txt");
		fichier.delete();
	}
}
