package sources;

public class MainApp {
	public static void main(String[] av) {
		if (av.length != 1) {
			System.err.println("Error : bad usage, \"java MainApp pathToSimFile\".");
			System.exit(0);
		}
		FileParser parser = new FileParser();
		parser.parseFile(av[0]);
	}
}