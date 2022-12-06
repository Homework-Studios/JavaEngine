package net.rebix.engine.utils.loops;

import net.rebix.engine.JavaEngine;

public class tensecloop {
    public tensecloop() {
        new Thread(() -> {
            while (JavaEngine.plugin.isEnabled()) {
                try {
                    String supposedStatus = JavaEngine.DATABASE.getStringByString("servertypestatus", "test_servers", "servertype", "status");
                    if ("off".equals(supposedStatus)) {
                        JavaEngine.plugin.getServer().shutdown();
                    }
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
