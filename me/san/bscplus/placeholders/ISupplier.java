package me.san.bscplus.placeholders;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public interface ISupplier {
	
	public String get(Plugin plugin, ProxiedPlayer player);
	
}
