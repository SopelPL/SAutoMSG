package Listeners;

import me.sopelplyt.sautomsg.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener{

	@SuppressWarnings({ "deprecation", "static-access" })
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); 
		ItemStack clicked = event.getCurrentItem(); 
		Inventory inventory = event.getInventory(); 
		if (inventory.getName().equals(Main.getInst().gui.getName())) {
			if (clicked.getType() == Material.EMERALD_BLOCK) {
				event.setCancelled(true);
				player.closeInventory();
				if(!Main.sendMsg){
					Main.start();
					Main.sendMsg = true;
					Main.getInst().openGUI(player);
					player.sendMessage(Main.colorMessage("&2&lWlaczyles &aautomatyczne wiadomosci!"));
					return;
				}else{
					Main.getInst().openGUI(player);
					player.sendMessage(Main.getInst().colorMessage("&cWiadomosci sa juz &a&lwlaczone&c!"));
					return;
				}
			}else if (clicked.getType() == Material.REDSTONE_BLOCK){
				event.setCancelled(true); 
				player.closeInventory();
				if(Main.sendMsg){
					Main.stop();
					Main.sendMsg = false;
					Main.getInst().openGUI(player);
					player.sendMessage(Main.getInst().colorMessage("&4&lWylaczyles &aautomatyczne wiadomosci!"));
					return;
				}else{
					Main.getInst().openGUI(player);
					player.sendMessage(Main.getInst().colorMessage("&cWiadomosci sa juz &4&lwylaczone&c!"));
					return;
				}
			}else if (clicked.getType() == Material.GRASS || clicked.getType() == Material.DIRT){
				returnInventory(event, player);
				return;
			}else if (clicked.getType() == Material.PAPER){
				returnInventory(event, player);
				return;
			}else if (clicked.getType() == Material.TNT){
				event.setCancelled(true);
				player.closeInventory();
				return;
			}else if (clicked.getType() == Material.getMaterial(102)){
				returnInventory(event, player);
				return;
			}else
				return;
		}
	}
	
	private void returnInventory(InventoryClickEvent e, Player player){
		e.setCancelled(true);
		player.closeInventory();
		Main.getInst().openGUI(player);
		return;
	}
}
