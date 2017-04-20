package nl.gewoonhdgaming.ess.listeners.adminmode;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import nl.gewoonhdgaming.ess.utils.ChatUtils;
import nl.gewoonhdgaming.ess.utils.ServerUtils;
	
public class CompassClick implements Listener {
	
	public ItemStack Item_Skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
    SkullMeta Meta_Skull = (SkullMeta) Item_Skull.getItemMeta();
    ArrayList<String> Lore_Skull = new ArrayList<>();
    int players = 0;
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){
	    Player p = event.getPlayer();
	    if(p.getItemInHand().getType() == Material.COMPASS && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("speler kompas")){
	    	Inventory inv = Bukkit.createInventory(null, ServerUtils.allPlayers.size(), ChatColor.AQUA + "Spelers");
	    	for(Player pl : ServerUtils.allPlayers) {
	    		players += 1;
	    		Meta_Skull.setOwner(pl.getName());
	    		Lore_Skull.clear();
	    		Lore_Skull.add(null);
	    		Lore_Skull.add(ChatColor.GRAY + "Klik om naar dit persoon te teleporteren.");
	    		Meta_Skull.setLore(Lore_Skull);
	    		Meta_Skull.setDisplayName(pl.getName());
	    		Item_Skull.setItemMeta(Meta_Skull);
	    		inv.setItem(players, Item_Skull);	
	    	}
	    }
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(ChatColor.AQUA + "Spelers")) {
			int slot = e.getSlot();
			  ItemStack item = e.getWhoClicked().getInventory().getItem(slot);
			  Player t = Bukkit.getPlayer(item.getItemMeta().getDisplayName());
			  if(ServerUtils.allPlayers.contains(item.getItemMeta().getDisplayName())) {
				  
				  e.setCancelled(true);
				  p.teleport(t.getLocation());
				  p.sendMessage(ChatUtils.prefix + "Je bent geteleporteerd naar: " + t.getName());
				  p.closeInventory();
			  } else {
				  p.sendMessage(ChatUtils.prefix + t.getName() + " is offline gegaan!");
			  }
		}

	}

}
