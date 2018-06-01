package me.sopelplyt.sautomsg;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static List<String> msgs;
	public static int interval;
	public static String tag;
	public static String perm;
	public static int lastMsg = 0;

	public void onEnable(){
		saveDefaultConfig();
		msgs = getConfig().getStringList("messages");
		tag = getConfig().getString("tag");
		interval = (getConfig().getInt("interval")) * 1000;
		perm = getConfig().getString("admin-permission");
		translateColor();
		start();
	}

	public static void start(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Random rand = new Random();
				int index = rand.nextInt(msgs.size());
				if(index != lastMsg){
					String m = msgs.get(index);
					m = ChatColor.translateAlternateColorCodes('&', m);
					Bukkit.broadcastMessage(tag + " " + m);
					lastMsg = index;
				}else{
					Random r = new Random();
					int i = r.nextInt(msgs.size());
					
					String me = msgs.get(i);
					me = ChatColor.translateAlternateColorCodes('&', me);
					Bukkit.broadcastMessage(tag + " " + me);
					lastMsg = i;
				}
				
			}
		}, interval, interval);
	}
	
	private void translateColor(){
		tag = ChatColor.translateAlternateColorCodes('&', tag);
	}
	
	@SuppressWarnings("unused")
	private String colorMessage(String m){
		if(m != null){
			m = ChatColor.translateAlternateColorCodes('&', m);
			return m;
		}
		return null;
	}
}
