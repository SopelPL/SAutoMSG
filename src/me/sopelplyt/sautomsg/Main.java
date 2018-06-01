package me.sopelplyt.sautomsg;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.InventoryClickListener;

public class Main extends JavaPlugin{
	
	public static Main inst;
	
	private static Timer timer = new Timer();
	private static TimerTask task;
	private static List<String> msgs;
	private static int interval;
	private static String tag;
	private static String perm;
	private static int lastMsg = 0;
	public static boolean sendMsg;
	private static Random rand = new Random();
	private static Random r = new Random();
	
	public Inventory gui = Bukkit.createInventory(null, 18, colorMessage("&a&lSAutoMSG"));

	public void onEnable(){
		inst = this;
		saveDefaultConfig();
		msgs = getConfig().getStringList("messages");
		tag = getConfig().getString("tag");
		interval = (getConfig().getInt("interval")) * 1000;
		perm = getConfig().getString("admin-permission");
		sendMsg = getConfig().getBoolean("status");
		tag = colorMessage(tag);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		if(sendMsg)
			start();
	}
	
	public void onDisable(){
		getConfig().set("status", sendMsg);
		saveConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("smsg")){
			if(sender instanceof Player){
				if (sender.hasPermission(perm)){
					if(args.length == 0){
						openGUI((Player)sender);
						return true;
					}
				}else{
					sender.sendMessage(colorMessage("&cNie masz uprawnien!"));
					return true;
				}
			}else {
				return true;
			}
		}
		return false;
	}

	public static void start(){
		task = new TimerTask() {
			
			@Override
			public void run() {
				int index = rand.nextInt(msgs.size());
				if(index != lastMsg){
					String m = msgs.get(index);
					m = ChatColor.translateAlternateColorCodes('&', m);
					Bukkit.broadcastMessage(tag + " " + m);
					lastMsg = index;
				}else{
					int i = r.nextInt(msgs.size());
					String me = msgs.get(i);
					me = ChatColor.translateAlternateColorCodes('&', me);
					Bukkit.broadcastMessage(tag + " " + me);
					lastMsg = i;
				}
				
			}
		};
		timer.scheduleAtFixedRate(task, interval, interval);
	}
	
	public static void stop(){
		task.cancel();
		task = null;
		return;
	}
	
	public ItemStack createItem(Material item, int dataTag){
		ItemStack is = new ItemStack(item, 1, (byte)dataTag);
		return is;
	}
	
	@SuppressWarnings("deprecation")
	public void openGUI(Player p){
		for(int i = 0; i < 17; i++){
			if(i != 2 || i != 4 || i != 6 || i != 9 || i != 13 || i != 17){
				gui.setItem(i, changeMeta(createItem(Material.getMaterial(102)), colorMessage("")));
			}
		}
		gui.setItem(9, changeMeta(createItem(Material.PAPER), colorMessage("&6&lWiadomosci wysylane sa co: &c" + (interval / 1000) + " &6&lsekund!")));
		gui.setItem(2, changeMeta(createItem(Material.EMERALD_BLOCK), colorMessage("&a&lON")));
		if(sendMsg){
			gui.setItem(4, changeMeta(createItem(Material.GRASS), colorMessage("&c>> &6Status: &a&lON")));
		}else{
			gui.setItem(4, changeMeta(createItem(Material.DIRT), colorMessage("&c>> &6Status: &4&lOFF")));
		}
		gui.setItem(6, changeMeta(createItem(Material.REDSTONE_BLOCK), colorMessage("&c&lOFF")));
		gui.setItem(13, changeMeta(createItem(Material.TNT), colorMessage("&4&lZAMKNIJ")));
		gui.setItem(17, changeMeta(createItem(Material.PAPER), colorMessage("&6&lPermisja do panelu: &c" + perm)));
		p.openInventory(gui);
		return;
	}
	
	public ItemStack createItem(Material item){
		ItemStack is = new ItemStack(item, 1);
		return is;
	}
	
	public ItemStack changeMeta(ItemStack is, String newName){
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(newName);
		is.setItemMeta(im);
		return is;
	}
	
	public String colorMessage(String m){
		if(m != null){
			m = ChatColor.translateAlternateColorCodes('&', m);
			return m;
		}
		return null;
	}
	
	public static Main getInst(){
		return inst;
	}
}
