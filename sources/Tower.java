package sources;

import java.util.*;

public class Tower {
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable p_flyable) {
		observers.add(p_flyable);
	}

	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
	}

	protected void conditionChanged() {
		for (int i = 0; i < observers.size(); i++) {
			try {
				observers.get(i).updateConditions();
			} catch (WrongTypeException e) {
				e.printStackTrace();
			} catch (LandException e) {
				unregister(observers.get(i));
			}
		}
	}
}
