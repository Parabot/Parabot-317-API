package org.rev317.randoms;

import org.parabot.environment.randoms.Random;
import org.rev317.api.methods.Game;
import org.rev317.randoms.ui.controllers.UIController;

/**
 * User: Jeroen
 * Date: 20/02/14
 * Time: 00:49
 */
public class Login implements Random {

    public static String username;
    public static String password;

    @Override
    public boolean activate() {
        return !Game.isLoggedIn() && UIController.randoms.get("Auto login") == true;
    }

    @Override
    public void execute() {

    }
}

