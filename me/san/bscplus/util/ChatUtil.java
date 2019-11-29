package me.san.bscplus.util;

import net.md_5.bungee.api.ChatColor;

public class ChatUtil {
	
	public static String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
