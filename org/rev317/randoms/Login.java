package org.rev317.randoms;

import org.parabot.environment.randoms.Random;
import org.rev317.api.methods.Game;
import org.rev317.randoms.ui.Controller;

/**
 * User: Jeroen
 * Date: 20/02/14
 * Time: 00:49
 */
public class Login implements Random {
    @Override
    public boolean activate() {
        return !Game.isLoggedIn() && Controller.randoms.get("Auto login") == true;
    }

    @Override
    public void execute() {

    }
}

