package org.rev317.api.methods;

import org.parabot.environment.api.utils.Time;
import org.rev317.api.interfaces.Condition;

/**
 *
 * @author Paradox, Mrsdefnerd
 *
 */
public class Misc {
    /**
     * Sleeps for a certain time with a certain condition
     * @param con
     * @param time
     */
    public static void sleep(Condition con, int time) {
        long start = System.currentTimeMillis();
        while (con.validate() && System.currentTimeMillis() - start < time)
            Time.sleep(15);
        }
}
