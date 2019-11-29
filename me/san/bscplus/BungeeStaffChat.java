package me.san.bscplus;

import java.io.File;
import java.io.IOException;

import me.san.bscplus.commands.CMDStaffChat;
import me.san.bscplus.commands.CMDStaffChatToggle;
import me.san.bscplus.listeners.ServerChatEventListener;
import me.san.bscplus.placeholders.InternalPlaceholders;
import me.san.bscplus.util.ChatUtil;
import me.san.bscplus.util.SaveConfig;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeStaffChat extends Plugin{

	ServerChatEventListener scel;
	CMDStaffChatToggle t;
	SaveConfig sc;
	public net.md_5.bungee.config.Configuration config;
	InternalPlaceholders ip;
	
	public void onEnable() {
		loadConfig();
		loadCommands();
		initClasses();
		getLogger().info(ChatUtil.format("&aPlugin loaded successfully!"));
		
	}
	
	private void loadConfig() {
		if(!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
		this.sc = new SaveConfig(this);
		sc.saveResource("config.yml", false);
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadCommands() {
		this.t = new CMDStaffChatToggle(this);
		this.ip = new InternalPlaceholders(this);
		getProxy().getPluginManager().registerCommand(this, new CMDStaffChat(this, ip));
		getProxy().getPluginManager().registerCommand(this, t);
	}
	
	private void initClasses() {
		getProxy().getPluginManager().registerListener(this, new ServerChatEventListener(t, ip, this));
	}
	
	public void onDisable() {
		getLogger().info(ChatUtil.format("&aPlugin disabled successfully!"));
	}
}
