package me.san.bscplus.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.san.bscplus.BungeeStaffChat;

public class SaveConfig {
	
	BungeeStaffChat plugin;

	public SaveConfig(BungeeStaffChat plugin) {
		this.plugin = plugin;
	}
	public void saveResource(String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }
        InputStream in = plugin.getResourceAsStream(resourcePath = resourcePath.replace('\\', '/'));
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in ");
        }
        File outFile = new File(plugin.getDataFolder(), resourcePath);
        int lastIndex = resourcePath.lastIndexOf(47);
        File outDir = new File(plugin.getDataFolder(), resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        try {
            if (!outFile.exists() || replace) {
                int len;
                FileOutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) > 0) {
                    ((OutputStream)out).write(buf, 0, len);
                }
                ((OutputStream)out).close();
                in.close();
            }
            System.out.println(ChatUtil.format("&a[BungeeStaffChat] " + resourcePath + " loaded!"));
        }
        catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
}
