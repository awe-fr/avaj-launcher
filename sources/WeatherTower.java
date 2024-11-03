package sources;

public class WeatherTower extends Tower {
    private static WeatherProvider EvelyneDheliat;

	public String getWeather(Coordinates p_Coordinates) {
        if (EvelyneDheliat == null) {
            EvelyneDheliat = WeatherProvider.getInstance();
        }
        return EvelyneDheliat.getCurrentWeather(p_Coordinates);
    }

    public void changeWeather() {
        if (EvelyneDheliat == null) {
            EvelyneDheliat = WeatherProvider.getInstance();
        }
        EvelyneDheliat.changeWeather();
        conditionChanged();
    }
}
