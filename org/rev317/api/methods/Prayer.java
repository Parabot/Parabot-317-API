package org.rev317.api.methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.rev317.api.wrappers.hud.Tab;

/**
 * 
 * @author Uavix, Clisprail
 * 
 */
public final class Prayer
{

	public interface PrayerBook
	{

		public int getX();

		public int getY();

		public int levelRequired();

	}

	public static enum Prayer377 implements PrayerBook
	{
		THICK_SKIN(589, 230, 1), BURST_OF_STRENGTH(630, 230, 4), CLARITY_OF_THOUGHT(671, 230, 7), ROCK_SKIN(712, 230, 10), SUPERHUMAN_STRENGTH(589, 265, 13), IMPROVED_REFLEXES(630, 265, 16), RAPID_RESTORE(671, 265, 19), RAPID_HEAL(712, 265, 22), PROTECT_ITEM(589, 300, 25), STEEL_SKIN(630, 300, 28), ULTIMATE_STRENGTH(671, 300, 31), INCREDIBLE_REFLEXES(712, 300, 34), PROTECT_FROM_MAGIC(589, 335, 37), PROTECT_FROM_MISSILES(630, 335, 40), PROTECT_FROM_MELEE(671, 335, 43), RETRIBUTION(712, 335, 46), REDEMPTION(589, 370, 49), SMITE(630, 370, 52);

		int x, y, level;

		Prayer377(int x, int y, int level)
		{
			this.x = x;
			this.y = y;
			this.level = level;
		}

		@Override
		public int getX() {
			return x;
		}

		@Override
		public int getY() {
			return y;
		}

		@Override
		public int levelRequired() {
			return level;
		}

	}

	public static enum Prayer474 implements PrayerBook
	{
	;	

		@Override
		public int getX() {
			return 0;
		}

		@Override
		public int getY() {
			return 0;
		}

		@Override
		public int levelRequired() {
			return 0;
		}

	}

	/**
	 * Checks if the player has the prayer level required to activate a prayer.
	 * 
	 * @param prayer
	 * @return <b>true</b> if the player can activate the prayer.
	 */
	public static final boolean hasPrayerLevelReq(PrayerBook prayer) {
		if (Skill.PRAYER.getRealLevel() >= prayer.levelRequired()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks how many prayer points a player has.
	 * 
	 * @return prayer points.
	 */
	public static final int getPrayerPoints() {
		return Skill.PRAYER.getLevel();
	}

	/**
	 * Activates a prayer.
	 * 
	 * @param prayer
	 */
	public static final void activatePrayer(PrayerBook prayer) {
		if (!Tab.PRAYER.isOpen()) {
			Tab.PRAYER.open();
			Time.sleep(200, 500);
		}
		Mouse.getInstance().click(prayer.getX(), prayer.getY(), true);
	}

	/**
	 * Deactivates a prayer.
	 * 
	 * @param prayer
	 */
	public static final void deactivatePrayer(PrayerBook prayer) {
		if (!Tab.PRAYER.isOpen()) {
			Tab.PRAYER.open();
			Time.sleep(200, 500);
		}
		Mouse.getInstance().click(prayer.getX(), prayer.getY(), true);
	}

}

