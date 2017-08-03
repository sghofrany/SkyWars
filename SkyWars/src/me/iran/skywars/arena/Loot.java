package me.iran.skywars.arena;

import org.bukkit.inventory.ItemStack;

public class Loot {

	private ItemStack[] tier1;
	private ItemStack[] tier2;
	
	public Loot() {	}
	
	public Loot(ItemStack[] tier1, ItemStack[] tier2) {
		this.tier1 = tier1;
		this.tier2 = tier2;
	}
	
	public ItemStack[] getTier1() {
		return tier1;
	}
	public void setTier1(ItemStack[] tier1) {
		this.tier1 = tier1;
	}
	public ItemStack[] getTier2() {
		return tier2;
	}
	public void setTier2(ItemStack[] tier2) {
		this.tier2 = tier2;
	}
	
	
}
