package algorithm10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Warrior {
	
	private List<String> achievements;
	
	private final List<String> responses;

	private int experience;
	
	private final String LOSE_MESSAGE_IN_BATTLE;
	
	private final String INVALID_LEVEL_MESSAGE;
	
	private final String CANCEL_TRAINING_MESSAGE;
	
	private final int MAX_EXPERIENCE;
	
	public Warrior() {
		achievements = new ArrayList<String>();
		responses = Arrays.asList("Easy fight", "A good fight", "An intense fight");
		experience = 100;
		LOSE_MESSAGE_IN_BATTLE = "You've been defeated";
		CANCEL_TRAINING_MESSAGE = "Not strong enough";
		MAX_EXPERIENCE = 10000;
		INVALID_LEVEL_MESSAGE = "Invalid level";
	}

	public String training(String description, int experienceToGet, int minLevelRequirement) {
		if(level() >= minLevelRequirement) {
			increaseExp(experienceToGet);
			achievements.add(description);
			return description;
		}
		
		return CANCEL_TRAINING_MESSAGE;
	}
	
	private void increaseExp(int experienceToGet) {
		experience = Math.min(experience + experienceToGet, MAX_EXPERIENCE);
	}
	
	public String battle(int enemyLevel) {
		if(enemyLevel < 1 || enemyLevel > 100) return INVALID_LEVEL_MESSAGE;
		
		int diff = enemyLevel - level();

		if(diff >= 5 && level() / 10 < enemyLevel / 10) return LOSE_MESSAGE_IN_BATTLE;
		
		int expToGet = diff > 0 ? 20 * diff * diff : Math.max(0, 10 + 5 * diff);
		int responseInd = diff == -1 ? 1
						: diff < 0 ? 0
						: diff > 0 ? 2 : 1;		
	
		increaseExp(expToGet);
		return responses.get(responseInd);
	}
	
	public int level() {
		return (experience / 100);
	}
	
	public int experience() {
		return experience;
	}
	
	public String rank() {
		return RankType.values()[level() / 10].toString();
	}
	
	public List<String> achievements() {
		return achievements;
	}
}
 