package org.rev317.api.methods;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.rev317.Loader;
import org.rev317.api.wrappers.hud.Interface;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.scene.SceneObject;

import java.util.ArrayList;

/**
 *
 * @author Everel
 *
 */
public class Bank {

    public static final int BANK_INTERFACE = 23350;
    public static final int ITEM_INTERFACE = 5382;

    public static final int[] BANKERS = new int[] { 44, 45, 494, 495, 498, 499,
            909, 958, 1036, 2271, 2354, 2355, 3824, 5488, 5901, 4456, 4457,
            4458, 4459, 5912, 5913, 6362, 6532, 6533, 6534, 6535, 7605, 8948,
            9710, 14367 };
    public static final int[] BANKS = new int[] { 782, 2213, 2995, 5276, 6084,
            10517, 11402, 11758, 12759, 14367, 19230, 20325, 24914, 25808,
            26972, 29085, 52589, 34752, 35647, 36786, 2012, 2015, 2019, 693,
            4483, 12308, 20607, 21301, 27663, 42192 };

    /**
     * Gets nearest banker
     *
     * @return nearest banker
     */
    public static Npc getBanker() {
        return Npcs.getNearest(new Filter<Npc>() {

            @Override
            public boolean accept(Npc f) {
                for (int id : BANKERS) {
                    if (id == f.getDef().getId()) {
                        return true;
                    }
                }
                return false;
            }

        })[0];
    }

    /**
     * Gets nearest bank booths
     *
     * @return bank booths
     */
    public static SceneObject[] getNearestBanks() {
        return SceneObjects.getNearest(BANKS);
    }

    /**
     * Gets nearest bank booths
     *
     * @return bank booth
     */
    public static SceneObject getBank() {
        SceneObject[] banks = getNearestBanks();
        if (banks != null && banks[0] != null) {
            return banks[0];
        }
        return null;
    }

    /**
     * Opens bank using banker or bank booth
     *
     * @return <b>true</b> if successfully interacted
     */
    public static boolean open() {

        if (isOpen()) {
            return false;
        }
        SceneObject bank = getBank();
        Npc banker = getBanker();

        if (bank != null) {
            bank.interact("Use-quickly");
            return true;
        } else if (banker != null) {
            banker.interact("Bank");
            return true;
        }

        return false;

    }

    /**
     * Withdraws items at bank based on given parameters
     *
     * @param id
     * @param amount
     */
    public static void withdraw(int id, int amount, int sleep) {

        if (!isOpen()) {
            return;
        }

        Item b = getItem(id);

        if(b == null) {
            return;
        }

        if (amount == 1) {
            Mouse.getInstance().click(b.getScreenLocation());
        } else if (amount == 5) {
            b.interact("Withdraw 5");
        } else if (amount == 10) {
            b.interact("Withdraw 10");
        } else if (amount == 0) {
            b.interact("Withdraw All");
        } else {
            b.interact("Withdraw x");
            Time.sleep(1500 + sleep);
            Keyboard.getInstance().sendKeys("" + amount);
        }
    }

    @Deprecated
    public static void withdraw(int id, int amount) {

        if (!isOpen()) {
            return;
        }

        Item b = getItem(id);

        if(b == null) {
            return;
        }

        if (amount == 1) {
            Mouse.getInstance().click(b.getScreenLocation());
        } else if (amount == 5) {
            b.interact("Withdraw 5");
        } else if (amount == 10) {
            b.interact("Withdraw 10");
        } else if (amount == 0) {
            b.interact("Withdraw All");
        } else {
            b.interact("Withdraw x");
            Time.sleep(1500);
            Keyboard.getInstance().sendKeys("" + amount);
        }
    }

    /**
     * Gets bank item with given id
     *
     * @param id
     * @return bank item
     */
    public static Item getItem(int id) {

        if (!isOpen()) {
            return null;
        }

        for (Item i : Bank.getBankItems()) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Counts the amount of items with given id in bank
     *
     * @param id
     * @return count
     */
    public static int getCount(int id) {
        if (!isOpen()) {
            return 0;
        }
        return getItem(id).getStackSize();
    }

    /**
     * Opens the bank
     *
     * @param bank
     *            booth
     */
    public static void open(SceneObject bank) {

        if (isOpen()) {
            return;
        }

        if (bank.getLocation().distanceTo() > 8) {
            bank.getLocation().clickMM();
            return;
        }
        if (!bank.isOnScreen()) {
            Camera.turnTo(bank);
            return;
        }
        bank.interact("Use-quickly");
    }

    /**
     * Deposits all items in inventory
     */
    public static void depositAll() {
        if (!isOpen()) {
            return;
        }
        Interfaces.get(23412).click();
    }

    /**
     * Closes the bank interface
     */
    public static void close() {
        if (!isOpen()) {
            return;
        }
        Interfaces.get(5384).click();
    }

    /**
     * Deposits all items except the given ids
     *
     * @param exceptions
     *            the item indexes that will be ignored.
     */
    public static void depositAllExcept(int... exceptions) {
        if (Bank.isOpen()) {
            final ArrayList<Integer> ignored = new ArrayList<Integer>();
            for (int i : exceptions) {
                ignored.add(i);
            }
            for (Item i : Inventory.getItems()) {
                if (!ignored.contains(i.getId())) {
                    while (Bank.isOpen() && Inventory.getCount(i.getId()) > 0) {
                        i.interact("Store All");
                        ignored.add(i.getId());
                        Time.sleep(50);
                    }
                }
            }
        }
    }

    /**
     * Gets all bank item ids in bank
     *
     * @return bank items
     */
    public static int[] getBankItemIDs() {
        if (!isOpen()) {
            return null;
        }
        Interface bank = Interfaces.get(5382);
        return bank.getItems();
    }

    /**
     * Gets all stack sizes in bank
     *
     * @return stack sizes
     */
    public static int[] getBankStacks() {
        if (!isOpen()) {
            return null;
        }
        Interface bank = Interfaces.get(5382);
        return bank.getStackSizes();
    }

    /**
     * Gets all bank items in bank
     *
     * @return bank items
     */
    public static Item[] getBankItems() {
        if (!isOpen()) {
            return null;
        }
        ArrayList<Item> items = new ArrayList<Item>();
        int[] ids = getBankItemIDs();
        int[] stacks = getBankStacks();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] > 0) {
                items.add(new Item(ids[i], stacks[i], i, 1));
            }
        }
        return (Item[]) items.toArray(new Item[items.size()]);
    }

    /**
     * Counts total amount of items in bank
     *
     * @return total amount of items
     */
    public static int getBankCount() {
        if (!isOpen()) {
            return 0;
        }
        return getBankItemIDs().length;
    }

    /**
     * Determines if bank is open
     *
     * @return <b>true</b> if bank is open
     */
    public static boolean isOpen() {
        return Loader.getClient().getOpenInterfaceId() == 23350;
    }

    /**
     * Determines if noting is enable
     *
     * @return <b>true</b> if noting is enabled
     */
    public static boolean isNoting() {
        return Settings.getInstance().getSetting(115) == 1;
    }

}
