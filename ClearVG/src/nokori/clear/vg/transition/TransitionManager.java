package nokori.clear.vg.transition;

import java.util.ArrayList;

public class TransitionManager {
	private static ArrayList<Transition> activeTransitions = new ArrayList<>();
	
	static void add(Transition transition) {
		activeTransitions.add(transition);
	}
	
	static boolean remove(Transition transition) {
		return activeTransitions.remove(transition);
	}
	
	public static void tick() {
		for (int i = 0; i < activeTransitions.size(); i++) {
			Transition t = activeTransitions.get(i);
			t.tick(t.getProgress());
			
			if (t.isFinished()) {
				
				if (t.completedCallback != null) {
					t.completedCallback.callback(t);
				}
				
				activeTransitions.remove(i);
				i--;
			}
		}
	}
}
