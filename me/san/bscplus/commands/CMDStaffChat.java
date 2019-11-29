package me.san.bscplus.commands;

import com.google.common.base.Joiner;

import me.san.bscplus.BungeeStaffChat;
import me.san.bscplus.placeholders.InternalPlaceholders;
import me.san.bscplus.util.ChatUtil;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDStaffChat extends Command{

	BungeeStaffChat bsc;
	InternalPlaceholders ip;
	public CMDStaffChat(BungeeStaffChat bsc, InternalPlaceholders ip) {
		super("sc");
		this.bsc = bsc;
		this.ip = ip;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new TextComponent(ChatUtil.format("&cOnly players make execute StaffChat commands!")));
		}
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if(p.hasPermission("staffchat.use") || p.hasPermission("staffchat.*")) {
			if(args.length == 0) {
				//TODO config prefix
				p.sendMessage(new TextComponent(ChatUtil.format(bsc.config.getString("general.prefix") + "&c Error: You must send a message with the &c/sc command!")));
				p.sendMessage(new TextComponent(ChatUtil.format("&cUsage: /sc (message)")));
				return;
			}else if(args.length > 0) {
				String message = Joiner.on(" ").join(args);
				for(ProxiedPlayer pl : BungeeCord.getInstance().getPlayers()) {
					if(pl.hasPermission("staffchat.notify") || pl.hasPermission("staffchat.*")) {
						pl.sendMessage(new TextComponent(
								ChatUtil.format
								(ip.convert
										(p, bsc
												.config
												.getString
												("general.prefix")
												+ bsc
												.config
												.getString
												("general.format")
												+ message))));
					}
				}
			}
		}
	}
}
