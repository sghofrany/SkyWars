package me.iran.skywars.gametype;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GameType {

	private String name;
	private String permission;
	
	private ItemStack display = new ItemStack(Material.DIAMOND_SWORD);
	
	private ItemStack[] armor;
	private ItemStack[] inv;

	private ItemStack[] loot;
	
	public GameType(String name) {
		this.setName(name);
		
		this.setPermission("skywars." + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public ItemStack getDisplay() {
		return display;
	}

	public void setDisplay(ItemStack display) {
		this.display = display;
	}

	public ItemStack[] getArmor() {
		return armor;
	}

	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}

	public ItemStack[] getInv() {
		return inv;
	}

	public void setInv(ItemStack[] inv) {
		this.inv = inv;
	}

	public ItemStack[] getLoot() {
		return loot;
	}

	public void setLoot(ItemStack[] loot) {
		this.loot = loot;
	}
	
}
