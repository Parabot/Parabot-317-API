package org.rev317.api.methods;

import java.awt.Point;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.scene.GroundItem;
import org.rev317.api.wrappers.scene.SceneObject;

/**
 * 
 * @author Uavix, Clisprail
 * 
 */
public final class Magic
{

	public interface SpellBook
	{

		public int getX();

		public int getY();

		public int levelRequired();
	}

	/*	public static enum StandardMagic377 implements SpellBook {
			HOME_TELEPORT(570, 239, 0),
			WIND_STRIKE(593, 239, 1),
			CONFUSE(616, 239, 3),
			WATER_STRIKE(639, 239, 5),
			LEVEL_1_ENCHANT(662, 239, 7),
			EARTH_STRIKE(685, 239, 9),
			WEAKEN(708, 239, 11),
			FIRE_STRIKE(570, 264, 13),
			BONES_TO_BANANAS(593, 264, 15),
			WIND_BOLT(616, 264, 17),
			CURSE(639, 264, 19),
			BIND(662, 264, 20),
			LOW_LEVEL_ALCHEMY(685, 264, 21),
			WATER_BOLT(708, 264, 23),
			VARROCK_TELEPORT(570, 286, 25),
			LEVEL_2_ENCHANT(593, 286, 27),
			EARTH_BOLT(616, 286, 29),
			LUMBRIDGE_TELEPORT(639, 286, 31),
			TELEKINETIC_GRAB(662, 286, 33),
			FIRE_BOLT(685, 286, 35),
			FALADOR_TELEPORT(708, 286, 37),
			CRUMBLE_UNDEAD(570, 310, 39),
			WIND_BLAST(593, 310, 41),
			SUPERHEAT_ITEM(616, 310, 43),
			CAMELOT_TELEPORT(639, 310, 45),
			WATER_BLAST(662, 310, 47),
			LEVEL_3_ENCHANT(685, 310, 49),
			IBAN_BLAST(708, 310, 50),
			SNARE(570, 335, 50),
			MAGIC_DART(593, 335, 50),
			ARDOUGNE_TELEPORT(616, 335, 51),
			EARTH_BLAST(639, 335, 53),
			HIGH_LEVEL_ALCHEMY(662, 335, 55),
			CHARGE_WATER_ORB(685, 335, 56),
			LEVEL_4_ENCHANT(708, 335, 57),
			WATCHTOWER_TELEPORT(570, 360, 58),
			FIRE_BLAST(593, 360, 59),
			CHARGE_EARTH_ORB(616, 360, 60),
			BONES_TO_PEACHES(639, 360, 60),
			SARADOMIN_STRIKE(662, 360, 60),
			CLAWS_OF_GUTHIX(685, 360, 60),
			FLAMES_OF_ZAMORAK(708, 360, 60),
			TROLLHEIM_TELEPORT(570, 385, 61),
			WIND_WAVE(593, 385, 62),
			CHARGE_FIRE_ORB(616, 385, 63),
			TELEPORT_TO_APE_ATOLL(639, 385, 64),
			WATER_WAVE(662, 385, 65),
			CHARGE_AIR_ORB(685, 385, 66),
			VULNERABILITY(708, 385, 66),
			LEVEL_5_ENCHANT(570, 408, 68),
			EARTH_WAVE(593, 408, 70),
			ENFEEBLE(616, 408, 73),
			TELEOTHER_LUMBRIDGE(639, 408, 74),
			FIRE_WAVE(662, 408, 75),
			ENTANGE(685, 408, 79),
			STUN(708, 408, 80),
			CHARGE(570, 432, 80),
			TELEOTHER_FALADOR(593, 432, 82),
			TELE_BLOCK(616, 432, 85),
			LEVEL_6_ENCHANT(639, 432, 87),
			TELEOTHER_CAMELOT(662, 432, 90);
			
			private final int x, y, level;
			
			StandardMagic377(final int x, final int y, final int level) {
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
			
		}*/

	public static enum StandardMagic377 implements SpellBook
	{
		WIND_STRIKE(570, 239, 1), CONFUSE(593, 239, 3), WATER_STRIKE(616, 239, 5), LEVEL_1_ENCHANT(639, 239, 7), EARTH_STRIKE(662, 239, 9), WEAKEN(685, 239, 11), FIRE_STRIKE(708, 239, 13), BONES_TO_BANANAS(570, 264, 15), WIND_BOLT(593, 264, 17), CURSE(616, 264, 19), BIND(639, 264, 20), LOW_LEVEL_ALCHEMY(662, 264, 21), WATER_BOLT(685, 264, 23), VARROCK_TELEPORT(708, 264, 25), LEVEL_2_ENCHANT(570, 286, 27), EARTH_BOLT(593, 286, 29), LUMBRIDGE_TELEPORT(616, 286, 31), TELEKINETIC_GRAB(639, 286, 33), FIRE_BOLT(662, 286, 35), FALADOR_TELEPORT(685, 286, 37), CRUMBLE_UNDEAD(708, 286, 39), WIND_BLAST(570, 310, 41), SUPERHEAT_ITEM(593, 310, 43), CAMELOT_TELEPORT(616, 310, 45), WATER_BLAST(639, 310, 47), LEVEL_3_ENCHANT(662, 310, 49), IBAN_BLAST(685, 310, 50), SNARE(708, 310, 50), MAGIC_DART(570, 335, 50), ARDOUGNE_TELEPORT(593, 335, 51), EARTH_BLAST(616, 335, 53), HIGH_LEVEL_ALCHEMY(639, 335, 55), CHARGE_WATER_ORB(662, 335, 56), LEVEL_4_ENCHANT(685, 335, 57), WATCHTOWER_TELEPORT(708, 335, 58), FIRE_BLAST(570, 360, 59), CHARGE_EARTH_ORB(593, 360, 60), BONES_TO_PEACHES(616, 360, 60), SARADOMIN_STRIKE(639, 360, 60), CLAWS_OF_GUTHIX(662, 360, 60), FLAMES_OF_ZAMORAK(685, 360, 60), TROLLHEIM_TELEPORT(708, 360, 61), WIND_WAVE(570, 385, 62), CHARGE_FIRE_ORB(593, 385, 63), TELEPORT_TO_APE_ATOLL(616, 385, 64), WATER_WAVE(639, 385, 65), CHARGE_AIR_ORB(662, 385, 66), VULNERABILITY(685, 385, 66), LEVEL_5_ENCHANT(708, 385, 68), EARTH_WAVE(570, 408, 70), ENFEEBLE(593, 408, 73), TELEOTHER_LUMBRIDGE(616, 408, 74), FIRE_WAVE(639, 408, 75), ENTANGE(662, 408, 79), STUN(685, 408, 80), CHARGE(708, 408, 80), TELEOTHER_FALADOR(570, 432, 82), TELE_BLOCK(593, 432, 85), LEVEL_6_ENCHANT(616, 432, 87), TELEOTHER_CAMELOT(639, 432, 90);

		private final int x, y, level;

		StandardMagic377(final int x, final int y, final int level)
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

	public static enum AncientMagic377 implements SpellBook
	{
		HOME_TELEPORT(575, 227, 0), SMOKE_RUSH(620, 227, 50), SHADOW_RUSH(665, 227, 52), PADDEWWA_TELEPORT(710, 227, 54), BLOOD_RUSH(575, 254, 56), ICE_RUSH(620, 254, 58), SENNTISTEN_TELEPORT(665, 254, 60), MIASMIC_RUSH(710, 254, 61), SMOKE_BURST(575, 283, 62), SHADOW_BURST(620, 283, 64), KHARYLL_TELEPORT(665, 283, 66), BLOOD_BURST(710, 283, 68), ICE_BURST(575, 312, 70), LASSAR_TELEPORT(620, 312, 72), MIASMIC_BURST(665, 312, 73), SMOKE_BLITZ(710, 312, 74), SHADOW_BLITZ(575, 342, 76), DAREEYAK_TELEPORT(610, 342, 78), BLOOD_BLITZ(665, 342, 80), ICE_BLITZ(710, 342, 82), CARRALLANGAR_TELEPORT(575, 366, 84), MIASMIC_BLITZ(610, 366, 85), SMOKE_BARRAGE(665, 366, 86), SHADOW_BARRAGE(710, 366, 88), ANNAKARL_TELEPORT(575, 396, 90), BLOOD_BARRAGE(610, 396, 92), ICE_BARRAGE(665, 396, 94), GHORROCK_TELEPORT(710, 396, 96);

		private final int x, y, level;

		AncientMagic377(final int x, final int y, final int level)
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

	public static enum AncientMagic525 implements SpellBook
	{
		HOME_TELEPORT(575, 227, 0), SMOKE_RUSH(620, 227, 50), SHADOW_RUSH(665, 227, 52), PADDEWWA_TELEPORT(710, 227, 54), BLOOD_RUSH(575, 254, 56), ICE_RUSH(620, 254, 58), SENNTISTEN_TELEPORT(665, 254, 60), MIASMIC_RUSH(710, 254, 61), SMOKE_BURST(575, 283, 62), SHADOW_BURST(620, 283, 64), KHARYLL_TELEPORT(665, 283, 66), BLOOD_BURST(710, 283, 68), ICE_BURST(575, 312, 70), LASSAR_TELEPORT(620, 312, 72), MIASMIC_BURST(665, 312, 73), SMOKE_BLITZ(710, 312, 74), SHADOW_BLITZ(575, 342, 76), DAREEYAK_TELEPORT(610, 342, 78), BLOOD_BLITZ(665, 342, 80), ICE_BLITZ(710, 342, 82), CARRALLANGAR_TELEPORT(575, 366, 84), MIASMIC_BLITZ(610, 366, 85), SMOKE_BARRAGE(665, 366, 86), SHADOW_BARRAGE(710, 366, 88), ANNAKARL_TELEPORT(575, 396, 90), BLOOD_BARRAGE(610, 396, 92), ICE_BARRAGE(665, 396, 94), GHORROCK_TELEPORT(710, 396, 96), MIASMIC_BARRAGE(575, 425, 97);

		private final int x, y, level;

		AncientMagic525(final int x, final int y, final int level)
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

	/**
	 * Checks if the player has the magic level required to cast the spell.
	 * 
	 * @param spell
	 * @return <b>true</b> if the player can cast the spell.
	 */
	public static final boolean hasMagicLevelReq(SpellBook spell) {
		if (Skill.MAGIC.getRealLevel() < spell.levelRequired()) {
			return true;
		}
		return false;
	}

	/**
	 * Clicks a spell.
	 * 
	 * @param spell
	 */
	public static final void clickSpell(SpellBook spell) {
		if (Tab.getOpened() != Tab.MAGIC) {
			Tab.MAGIC.open();
			Time.sleep(500);
		}
		Time.sleep(500);
		Mouse.getInstance().click(new Point(spell.getX(), spell.getY()));
	}

	/**
	 * Casts a spell on an npc.
	 * 
	 * @param spell
	 * @param npc
	 */
	public static final void castSpell(SpellBook spell, Npc npc) {
		if (Tab.getOpened() != Tab.MAGIC) {
			Tab.MAGIC.open();
			Time.sleep(400, 500);
		}
		Mouse.getInstance().click(spell.getX(), spell.getY(), true);
		Time.sleep(400, 500);
		final Point npcLoc = npc.getCenterPointOnScreen();
		Mouse.getInstance().click(npcLoc.x, npcLoc.y, true);
		final long timeOut = System.currentTimeMillis() + 4000;
		while (System.currentTimeMillis() < timeOut && Players.getLocal().getAnimation() != -1) {
			Time.sleep(100);
		}
	}

	/**
	 * Casts a spell on an item.
	 * 
	 * @param spell
	 * @param item
	 */
	public static final void castSpell(SpellBook spell, Item item) {
		if (Tab.getOpened() != Tab.MAGIC) {
			Tab.MAGIC.open();
			Time.sleep(400, 500);
		}
		Mouse.getInstance().click(spell.getX(), spell.getY(), true);
		Time.sleep(400, 500);
		Mouse.getInstance().click(item.getScreenLocation().x, item.getScreenLocation().y, true);
		final long timeOut = System.currentTimeMillis() + 4000;
		while (System.currentTimeMillis() < timeOut && Players.getLocal().getAnimation() != -1) {
			Time.sleep(100);
		}
	}

	/**
	 * Casts a spell on a grounditem.
	 * 
	 * @param spell
	 * @param item
	 */
	public static final void castSpell(SpellBook spell, GroundItem item) {
		if (Tab.getOpened() != Tab.MAGIC) {
			Tab.MAGIC.open();
			Time.sleep(400, 500);
		}
		Mouse.getInstance().click(spell.getX(), spell.getY(), true);
		Time.sleep(400, 500);
		Mouse.getInstance().click(item.getCenterPointOnScreen());
		final long timeOut = System.currentTimeMillis() + 4000;
		while (System.currentTimeMillis() < timeOut && Players.getLocal().getAnimation() != -1) {
			Time.sleep(100);
		}
	}

	/**
	 * Casts a spell on an object.
	 * 
	 * @param spell
	 * @param object
	 */
	public static final void castSpell(SpellBook spell, SceneObject object) {
		if (Tab.getOpened() != Tab.MAGIC) {
			Tab.MAGIC.open();
			Time.sleep(500);
		}
		Mouse.getInstance().click(spell.getX(), spell.getY(), true);
		Time.sleep(400, 500);
		Mouse.getInstance().click(object.getCenterPointOnScreen());
		final long timeOut = System.currentTimeMillis() + 4000;
		while (System.currentTimeMillis() < timeOut && Players.getLocal().getAnimation() != -1) {
			Time.sleep(100);
		}
	}

}

