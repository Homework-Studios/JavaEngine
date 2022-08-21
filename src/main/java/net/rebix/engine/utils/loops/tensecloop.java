package net.rebix.engine.utils.loops;

import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.modifier.Modifier;

public class tensecloop {
    public tensecloop() {
        new Thread(() -> {
            while (JavaEngine.plugin.isEnabled()) {
                try {
                    Thread.sleep(1000);
                    EItem.updateForPlayers();
                    Modifier.updateForPlayers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
