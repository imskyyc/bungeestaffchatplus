package me.san.bscplus.placeholders;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public enum Placeholders {

	STAFFCHAT_SENDER((plugin, p) ->{
		return p.getDisplayName();
	}),
	STAFFCHAT_SERVER((plugin, p)->{
		return p.getServer().getInfo().getName();
	});
	
	private ISupplier function;
	
	Placeholders(ISupplier function){
		this.function = function;
	}
	
	public String test(Plugin plugin, ProxiedPlayer p) {
		return function.get(plugin, p);
	}
}
