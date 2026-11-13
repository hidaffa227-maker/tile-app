package com.reeds.sensortile;
import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class BaseTile extends TileService {
    abstract String key();
    abstract int normal();
    abstract int crossed();
    static final String S = "sh /data/adb/modules/sensortoggle/system/bin/togglesensor ";

    static String su(String c) {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"su", "-c", c});
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String l = r.readLine();
            p.waitFor();
            return l == null ? "" : l.trim();
        } catch (Exception e) { return ""; }
    }

    void refresh() {
        new Thread(() -> {
            boolean b = su(S + key() + " status").equals("blocked");
            Tile t = getQsTile();
            if (t == null) return;
            t.setIcon(Icon.createWithResource(this, b ? crossed() : normal()));
            t.setState(b ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
            t.updateTile();
        }).start();
    }

    @Override public void onStartListening() { refresh(); }

    @Override public void onClick() {
        new Thread(() -> { su(S + key()); refresh(); }).start();
    }
}
