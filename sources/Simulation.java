package sources;

public class Simulation {
	private int runtime;
	private WeatherTower tower;

	public Simulation() {
		runtime = 0;
		tower = new WeatherTower();
	}

	public void addRuntime(int time) {
		runtime = time;
	}

	public void addFlyable(Flyable fly) {
		tower.register(fly);
	}
}
