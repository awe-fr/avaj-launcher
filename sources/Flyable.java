package sources;

public abstract class Flyable {
	protected WeatherTower weatherTower;

	public abstract void updateConditions() throws WrongTypeException, LandException;

	public void registerTower(WeatherTower p_tower) {
		weatherTower = p_tower;
	}
}
