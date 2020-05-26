package me.winiecki.itemModels.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class GUIItem {

    private int slot;
    private Material material;
    private String name;
    private List<String> lore;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public GUIItem(){}

    public static ItemStack createGUIOption(GUIItem item){

        ItemStack finalItem = new ItemStack(item.getMaterial());
        ItemMeta meta = finalItem.getItemMeta();

        meta.setDisplayName(item.getName());

        meta.setLore(item.getLore());
        finalItem.setItemMeta(meta);
        return finalItem;
    }
}
