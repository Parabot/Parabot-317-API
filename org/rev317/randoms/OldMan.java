package org.rev317.randoms;

import org.parabot.environment.randoms.Random;
import org.rev317.api.methods.Npcs;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.interactive.Npc;

/**
 * User: Jeroen
 * Date: 20/02/14
 * Time: 20:57
 */
public class OldMan implements Random{

    Npc oldMan;

    @Override
    public boolean activate() {
        if (Npcs.getNearest("") != null){
            if (Npcs.getNearest("").length > 0){
                for (Npc n : Npcs.getNearest("")){
                    if (n.isOnScreen() && n.getDisplayedText().contains(Players.getLocal().getName())){
                        oldMan = n;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if (oldMan != null){
            oldMan.interact("Talk");
        }
    }
}
