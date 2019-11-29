package me.san.bscplus.placeholders;

import java.util.HashMap;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class InternalPlaceholders {	
	
	private Plugin plugin;
	
	private HashMap<String, Placeholders> placeholders = new HashMap<>();
	
	public InternalPlaceholders(Plugin plugin) {
		this.plugin = plugin;
			for(Placeholders placeholder : Placeholders.values()) {
				placeholders.put(placeholder.toString().toLowerCase(), placeholder);
			}
	}
	
	public String convert(ProxiedPlayer p, String str) {
		boolean flag = false;
		String ret = "";
		String word = "";
		for(int i = 0 ; i < str.length(); i++) {
			if(str.charAt(i) == '%') {
				flag = !flag;
				if(!flag && placeholders.containsKey(word)) {
					ret+=placeholders.get(word).test(plugin, p);
					word = "";
				}else if(!flag) {
					ret+=word;
					word = "";
				}
				continue;
			}
			if(!flag) {
				ret+=String.valueOf(str.charAt(i));
			}else {
				word+= String.valueOf(str.charAt(i));
			}
		}
		return ret;
	}

}
