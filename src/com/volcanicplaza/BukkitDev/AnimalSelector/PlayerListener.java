package com.volcanicplaza.BukkitDev.AnimalSelector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerListener implements Listener {
	
	@EventHandler (priority = EventPriority.HIGHEST)
	//Listen for selecting a entity.
	 public void onEntityInteract(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		if (entity instanceof LivingEntity){
			if (player.isSneaking()){
				//Convert all capital entity name to nice proper name.
				String entityName = entity.getType().toString().toLowerCase();
				entityName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);
				
				//Send player a message then add the entity id to the global List.
				player.sendMessage("You have selected a " + ChatColor.DARK_GREEN + entityName + ChatColor.GREEN + ".");
				AnimalSelector.selectedEntity.put(player.getName(), entity);
				event.setCancelled(true);
				Location loc = player.getLocation();
				loc.setPitch(player.getLocation().getPitch());
				loc.setYaw(player.getLocation().getYaw());
				player.teleport(loc);
				
				player.playSound(player.getLocation(), Sound.ITEM_BREAK, 2, 2);
			}
		}
    }
}
