package me.winiecki.itemModels.bows;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class Bow {

    private List<String> lore;
    private String name;

    public Bow() {}

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ItemStack createBow(Bow bow){

        ItemStack finalBow = new ItemStack(Material.BOW);
        ItemMeta meta = finalBow.getItemMeta();

        meta.setDisplayName(bow.getName());
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(bow.getLore());
        finalBow.setItemMeta(meta);

        return finalBow;
    }
}
