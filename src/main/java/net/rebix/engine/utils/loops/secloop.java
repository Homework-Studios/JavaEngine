package net.rebix.engine.utils.loops;

import net.rebix.engine.EPlayer;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.modifier.Modifier;

public class secloop {
    public secloop() {
        new Thread(() -> {
            while (JavaEngine.plugin.isEnabled()) {
                try {
                    Thread.sleep(1000);
                    Modifier.updateForPlayers();
                    EPlayer.players.values().forEach(EPlayer::updateStats);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
