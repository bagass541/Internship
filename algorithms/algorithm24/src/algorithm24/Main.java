package algorithm24;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<String, Integer> recipe = new HashMap<String, Integer>();
		recipe.put("flour", 500);
		recipe.put("sugar", 200);
		recipe.put("eggs", 1);

		Map<String, Integer> available = new HashMap<String, Integer>();
		available.put("flour", 1200);
		available.put("sugar", 1200);
		available.put("eggs", 5);
		available.put("milk", 200);

		System.out.println(cakes(recipe, available));

		Map<String, Integer> recipe2 = Map.of("flour", 500, "sugar", 200, "eggs", 1, "cinnamon", 300);
		Map<String, Integer> available2 = Map.of("flour", 1200, "sugar", 1200, "eggs", 5, "milk", 200);
		
		System.out.println(cakes(recipe2, available2));

	}

	public static int cakes(Map<String, Integer> recipe, Map<String, Integer> available) {
		int count = Integer.MAX_VALUE;
		for (Map.Entry<String, Integer> entry : recipe.entrySet()) {
			Integer food = available.get(entry.getKey());
			Integer availableFood = food == null ? 0 : food;
			count = Math.min(availableFood / entry.getValue(), count);
		}

		return count;
	}
}
