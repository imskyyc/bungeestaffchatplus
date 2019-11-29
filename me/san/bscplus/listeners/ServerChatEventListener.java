package me.san.bscplus.listeners;

import me.san.bscplus.BungeeStaffChat;
import me.san.bscplus.commands.CMDStaffChatToggle;
import me.san.bscplus.placeholders.InternalPlaceholders;
import me.san.bscplus.util.ChatUtil;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerChatEventListener implements Listener{

	CMDStaffChatToggle sct;
	InternalPlaceholders ip;
	BungeeStaffChat bsc;
	
	public ServerChatEventListener(CMDStaffChatToggle sct, InternalPlaceholders ip, BungeeStaffChat bsc) {
		this.sct = sct;
		this.ip = ip;
		this.bsc = bsc;
	}
	@EventHandler
	public void chatEvent(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		e.setCancelled(true);
		if(sct.chatToggled.containsKey(p)) {
			if(e.getMessage().equalsIgnoreCase("/sct")) {
				sct.execute(p, null);
				return;
			}
			for(ProxiedPlayer pl : BungeeCord.getInstance().getPlayers()) {
				if(pl.hasPermission("staffchat.notify") || pl.hasPermission("staffchat.*")) {
					pl.sendMessage(new TextComponent(ip.convert(p, ChatUtil.format(bsc.config.getString("general.prefix") + bsc.config.getString("general.format") + e.getMessage()))));
				}
			}
		}else {
			e.setCancelled(false);
		}
	}
}
