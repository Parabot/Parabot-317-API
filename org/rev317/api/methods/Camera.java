package org.rev317.api.methods;

import org.parabot.environment.input.Keyboard;
import org.rev317.api.interfaces.Locatable;
import org.rev317.api.wrappers.scene.Tile;
import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail, Unknown
 * 
 */
public final class Camera {

	/**
	 * Rotates the camera to the given locatable
	 * @param locatable
	 * @param pitchUp
	 */
	public static final void turnTo(final Locatable locatable, boolean pitchUp) {
		int angle = getTileAngle(locatable.getLocation());
		setRotation(angle);
		setPitch(false);
	}

	/**
	 * Rotatas the camera to the given locatable
	 * @param locatable
	 */
	public static final void turnTo(final Locatable locatable) {
		turnTo(locatable, false);
	}

	/**
	 * Gets the camera X position
	 * 
	 * @return camera X position
	 */
	public static final int getX() {
		return Loader.getClient().getCameraX();
	}

	/**
	 * Gets the camera Y position
	 * 
	 * @return camera Y position
	 */
	public static final int getY() {
		return Loader.getClient().getCameraY();
	}

	/**
	 * Gets the camera Z position
	 * 
	 * @return camera Z position
	 */
	public static final int getZ() {
		return Loader.getClient().getCameraZ();
	}

	/**
	 * Gets the camera curve X position
	 * 
	 * @return camera curve X position
	 */
	public static final int getCurveX() {
		return Loader.getClient().getCameraPitch();
	}

	/**
	 * Gets the camera curve Y position
	 * 
	 * @return camera curve Y position
	 */
	public static final int getCurveY() {
		return Loader.getClient().getCameraYaw();
	}

	/**
	 * Calculates camera angle
	 * 
	 * @return angle of camera
	 */
	public static final int getAngle() {
		double mapAngle = getCurveX();
		mapAngle /= 2040D;
		mapAngle *= 360D;
		return (int) mapAngle;
	}

	/**
	 * Calculates angle to given tile
	 * 
	 * @param tile
	 * @return angle
	 */
	private static final int getTileAngle(Tile t) {
		int a = (Calculations.angleToTile(t) - 90) % 360;
		return a < 0 ? a + 360 : a;
	}

	/**
	 * Moves the camera to a given amount of degrees
	 * 
	 * @param degrees
	 */
	public static final void setRotation(int degrees) {
		char left = '%';
		char right = '\'';
		char whichDir = left;
		int start = getAngle();
		if (start < 180)
			start += 360;
		if (degrees < 180)
			degrees += 360;
		if (degrees > start) {
			if (degrees - 180 < start)
				whichDir = right;
		} else if (start > degrees && start - 180 >= degrees)
			whichDir = right;
		degrees %= 360;
		Keyboard.getInstance().pressKey(whichDir);
		int timeWaited = 0;
		while (getAngle() > degrees + 5 || getAngle() < degrees - 5) {
			sleep(10);
			if ((timeWaited += 10) > 500) {
				int time = timeWaited - 500;
				if (time == 0)
					Keyboard.getInstance().pressKey(whichDir);
				else if (time % 40 == 0)
					Keyboard.getInstance().pressKey(whichDir);
			}
		}
		Keyboard.getInstance().releaseKey(whichDir);
	}

	/**
	 * Sets the compass angle
	 * 
	 * @param direction
	 */
	public static final void setCompass(char direction) {
		switch (direction) {
		case 110: // 'n'
			setRotation(359);
			break;

		case 101: // 'e'
			setRotation(89);
			break;

		case 115: // 's'
			setRotation(179);
			break;

		case 119: // 'w'
			setRotation(269);
			break;

		default:
			setRotation(359);
			break;
		}
	}

	/**
	 * Moves camera fully up or down
	 * 
	 * @param up
	 * @return <b>true</b> if camera was moved successfully
	 */
	public static final boolean setPitch(boolean up) {
		try {
			char key = up ? '&' : '(';
			Keyboard.getInstance().pressKey(key);
			sleep(nextInt(1000, 1500));
			Keyboard.getInstance().releaseKey(key);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Changes the camera altitude
	 * 
	 * @param altPercent
	 * @return <b>true</b> if camera altitude was successfully set
	 */
	public static final boolean setAltitude(double altPercent) {
		int alt = (int) ((altPercent / 100D) * -1237D - 1226D);
		int curAlt = getZ();
		int lastAlt = 0;
		if (curAlt == alt)
			return true;
		if (curAlt > alt) {
			Keyboard.getInstance().pressKey('&');
			for (long start = System.currentTimeMillis(); curAlt > alt
					&& System.currentTimeMillis() - start < 30L; curAlt = getZ()) {
				if (lastAlt != curAlt)
					start = System.currentTimeMillis();
				lastAlt = curAlt;
				sleep(1);
			}

			Keyboard.getInstance().releaseKey('&');
			return true;
		}
		Keyboard.getInstance().pressKey('(');
		for (long start = System.currentTimeMillis(); curAlt < alt
				&& System.currentTimeMillis() - start < 30L; curAlt = getZ()) {
			if (lastAlt != curAlt)
				start = System.currentTimeMillis();
			lastAlt = curAlt;
			sleep(1);
		}

		Keyboard.getInstance().releaseKey('(');
		return true;
	}

	/**
	 * Randomizes number between minimum and maximum
	 * 
	 * @param min
	 * @param max
	 * @return randomized number
	 */
	private static final int nextInt(int min, int max) {
		return (int) (Math.random() * (double) (max - min)) + min;
	}

	/**
	 * Sleeps for an amount of given ms
	 * 
	 * @param time
	 *            in ms
	 */
	private static final void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
