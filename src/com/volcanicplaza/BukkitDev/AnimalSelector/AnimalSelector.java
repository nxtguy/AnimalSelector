package com.volcanicplaza.BukkitDev.AnimalSelector;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AnimalSelector extends JavaPlugin {
	
	public static HashMap<String, Entity> selectedEntity = new HashMap<String, Entity>();
	private JavaPlugin plugin = this;
	AnimalSelector AnimalSelector;
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		plugin = this;
		AnimalSelector = this;
		Bukkit.getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been enabled.");
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = getDescription();
		Bukkit.getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been disabled.");
		selectedEntity.clear();
	}
	
	//Returns Entity variable.
	public Entity getPlayerSelectedEntity(String string){
		if (!(selectedEntity.containsKey(string))){
			return null;
		}
		return selectedEntity.get(string);
	}
	
	//Returns player selected entity readable name.
	public String getPlayerSelectedEntityName(String string) {
		if (!(selectedEntity.containsKey(string))){
			return null;
		}
		
		Entity entity = selectedEntity.get(string);
		String entityName = entity.getType().toString().toLowerCase();
		entityName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);
		return entityName;
	}
	
}
