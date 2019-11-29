package me.san.bscplus.commands;

import java.util.HashMap;

import me.san.bscplus.BungeeStaffChat;
import me.san.bscplus.util.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDStaffChatToggle extends Command{

	public HashMap<ProxiedPlayer, Boolean> chatToggled = new HashMap<>();
	
	BungeeStaffChat bsc;
	public CMDStaffChatToggle(BungeeStaffChat bsc) {
		super("sct");
		this.bsc = bsc;
	}
	
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new TextComponent(ChatUtil.format("&cOnly players make execute StaffChat commands!")));
			return;
		}
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if(p.hasPermission("staffchat.use") && p.hasPermission("staffchat.toggle") || p.hasPermission("staffchat.*")) {
			if(!chatToggled.containsKey(p)) {
				p.sendMessage(new TextComponent(ChatUtil.format(bsc.config.getString("general.prefix") + "&a Chat toggled on!")));
				chatToggled.put(p, true);
				return;
			}
			if(chatToggled.containsKey(p)) {
				p.sendMessage(new TextComponent(ChatUtil.format(bsc.config.getString("general.prefix") + "&a Chat toggled off!")));
				chatToggled.remove(p);
				return;
			}
		}
		
	}
}
