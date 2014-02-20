package org.rev317.api.methods;

import org.rev317.Loader;

/**
 * 
 * @author Dane
 * 
 */
public enum Skill
{

	ATTACK(0), DEFENSE(1), STRENGTH(2), HITPOINTS(3), CONSTITUTION(3), RANGE(4), PRAYER(5), MAGIC(6), COOKING(7), WOODCUTTING(8), FLETCHING(9), FISHING(10), FIREMAKING(11), CRAFTING(12), SMITHING(13), MINING(14), HERBLORE(15), HERBLAW(15), AGILITY(16), THIEVING(17), SLAYER(18), FARMING(19), RUNECRAFTING(20), HUNTER(21), CONSTRUCTION(22), SUMMONING(23), DUNGEONEERING(24);

	private static final int[] EXPERIENCE = { 0, 0, 83, 174, 276, 388, 512, 650, 801, 969, 1154, 1358, 1584, 1833, 2107, 2411, 2746, 3115, 3523, 3973, 4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833, 16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224, 41171, 45529, 50339, 55649, 61512, 67983, 75127, 83014, 91721, 101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742, 302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895, 1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594, 3597792, 3972294, 4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629, 11805606, 13034431, 14391160, 15889109, 17542976, 19368992, 21385073, 23611006, 26068632, 28782069, 31777943, 35085654, 38737661, 42769801, 47221641, 52136869, 57563718, 63555443, 70170840, 77474828, 85539082, 94442737, 104273167 };

	private int index;

	private Skill(int index) {
		this.index = index;
	}

	/**
	 * Returns the skill's index.
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Returns the name of the skill.
	 */
	public final String getName() {
		return Character.toUpperCase(name().charAt(0)) + name().toLowerCase().substring(1);
	}

	/**
	 * Returns the current experience.
	 */
	public final int getExperience() {
		return Skill.getCurrentExperience(this.getIndex());
	}

	/**
	 * Returns the real level.
	 */
	public final int getRealLevel() {
		return Skill.getRealLevel(this.getIndex());
	}

	/**
	 * Returns the current level. (For example, if you've been de-buffed by a spell, or drank a beer that buffs the stat; it will return the buffed level.)
	 */
	public final int getLevel() {
		return Skill.getCurrentLevel(this.getIndex());
	}

	/**
	 * Returns the remaining experience until the next level.
	 */
	public final int getRemaining() {
		return Skill.getRemainingExperience(this.getIndex());
	}

	/**
	 * Returns the percentage until the next level.
	 */
	public final int getPercentage() {
		return Skill.getPercentToNextLevel(this.getIndex());
	}

	/**
	 * Returns '[Name, Level / RealLevel]'
	 */
	@Override
	public final String toString() {
		return "Skill: [" + this.getName() + ": " + this.getLevel() + " / " + this.getRealLevel() + "]";
	}

	/**
	 * Returns the experience of the provided skill.
	 * 
	 * @param index
	 *            the skill index.
	 * @return the experience.
	 */
	public static final int getCurrentExperience(int index) {
		return Loader.getClient().getCurrentExp()[index];
	}

	/**
	 * Returns the real level of the provided skill.
	 * 
	 * @param index
	 *            the skill index.
	 * @return the real skill level.
	 */
	public static final int getRealLevel(int index) {
		return getLevelByExperience(getCurrentExperience(index));
	}

	/**
	 * Returns the current level of the provided skill. (Will return de-buffed/buffed levels)
	 * 
	 * @param index
	 *            the skill index.
	 * @return the current skill level.
	 */
	public static final int getCurrentLevel(int index) {
		return Loader.getClient().getCurrentStats()[index];
	}

	/**
	 * Returns the exact experience at the provided level.
	 * 
	 * @param level
	 *            the level.
	 * @return the experience at the provided level.
	 */
	public static final int getExperienceByLevel(int level) {
		if (level > 99 || level < 1) {
			return 0;
		}
		return EXPERIENCE[level];
	}

	/**
	 * Returns the exact level with the provided experience.
	 * 
	 * @param experience
	 *            the experience.
	 * @return the level at the provided experience.
	 */
	public static final int getLevelByExperience(int experience) {
		for (int i = EXPERIENCE.length - 1; i > 0; i--) {
			if (experience > EXPERIENCE[i]) {
				return i;
			}
		}
		return 1;
	}

	/**
	 * Returns the remaining experience for the provided skill to level up.
	 * 
	 * @param index
	 *            the skill index.
	 * @return the remaining experience.
	 */
	public static final int getRemainingExperience(int index) {
		int level = getLevelByExperience(getCurrentExperience(index));
		if (level >= 99 || level < 1) {
			return 0;
		}
		return EXPERIENCE[(level + 1)] - getCurrentExperience(index);
	}

	/**
	 * Returns the percentage to the next level for the provided skill.
	 * 
	 * @param index
	 *            the skill index.
	 * @return the remaining percentage.
	 */
	public static final int getPercentToNextLevel(int index) {
		int currentLevel = getLevelByExperience(getCurrentExperience(index));
		int nextLevel = currentLevel + 1;
		if (currentLevel == 99 || nextLevel > 99 || currentLevel < 1 || nextLevel < 1) {
			return 0;
		}
		return (int) (100f * ((float) getCurrentExperience(index) / (float) EXPERIENCE[nextLevel]));
	}

	/**
	 * Returns the total skill level.
	 * 
	 * @return
	 */
	public static final String getTotalLevel() {
		return Interfaces.get(3984).getText();
	}
}
