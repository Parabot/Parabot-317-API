package org.rev317.api.methods;

import org.parabot.environment.input.Mouse;
import org.rev317.api.wrappers.hud.Region;
import org.rev317.api.wrappers.hud.Tab;

import java.awt.*;

public enum Prayer {

    THICK_SKIN(2, 83), BURST_OF_STRENGTH(4, 84), CLARITY_OF_THOUGHT(6, 85), SHARP_EYE(
            8, 101), MYSTIC_WILL(10, 102), ROCK_SKIN(12, 86), SUPERHUMAN_STRENGTH(
            14, 87), IMPROVED_REFLEXES(16, 88), RAPID_RESTORE(18, 89), RAPID_HEAL(
            20, 90), PROTECT_ITEMS(22, 91), HAWK_EYE(24, 103), MYSTIC_LORE(26,
            104), STEEL_SKIN(28, 92), ULTIMATE_STRENGTH(30, 93), INCREDIBLE_REFLEXES(
            32, 94), PROTECT_FROM_MELEE(40, 97), PROTECT_FROM_MAGIC(36, 95), PROTECT_FROM_MISSILES(
            38, 96), EAGLE_EYE(42, 105), MYSTIC_MIGHT(44, 106), RETRIBUTION(46,
            98), REDEMPTION(48, 99), SMITE(50, 100), RAPID_RENEWAL(54, 111), PIETY(56, 109), CHIVALRY(
            52, 107), RIGOUR(58, 112), AUGURY(60, 113);

    @Override
    public String toString() {
        return name().charAt(0)+name().substring(1).toLowerCase().replace("_", " ");
    }

    private int id;
    private int settingId;

    Prayer(int id, int settingId) {
        this.id = id;
        this.settingId = settingId;
    }

    private static final int PARENT_ID = 5608;

    public void click() {
        if (Tab.PRAYER.isOpen()) {
            if (Interfaces.getInterface(PARENT_ID, id) != null) {
                Point p = new Point((int) Interfaces.getInterface(PARENT_ID, id).getPoint(Region.TAB).getX() + 10,(int) Interfaces.getInterface(PARENT_ID, id).getPoint(Region.TAB).getY() + 10);
                Mouse.getInstance().click(p);
            }
        }
    }

    public String getName() {
        return toString();
    }

    public int getComponentId() {
        return id;
    }

    public boolean isActive() {
        return Settings.getInstance().getSetting(settingId) == 1;
    }

}