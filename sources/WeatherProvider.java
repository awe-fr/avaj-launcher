package sources;

import java.util.Random;

public class WeatherProvider {
	private static WeatherProvider instance;
	private static String[] weather;
	private static int wRand;

	private WeatherProvider() {
		Random rand = new Random();

		weather = new String[]{"RAIN", "FOG", "SUN", "SNOW"};

		wRand = rand.nextInt(weather.length);
	}

	public static WeatherProvider getInstance() {
		if (instance == null) {
			instance = new WeatherProvider();
		}

		return instance;
	}

	public String getCurrentWeather(Coordinates p_coordinates) {
		int x = p_coordinates.getLongitude();
		int y = p_coordinates.getLatitude();
		int z = p_coordinates.getHeight();

		int index = (x + y + z + wRand) % weather.length;
		
		return weather[index];
	}

	public void changeWeather() {
		Random rand = new Random();

		wRand = rand.nextInt(weather.length);
	}
}
