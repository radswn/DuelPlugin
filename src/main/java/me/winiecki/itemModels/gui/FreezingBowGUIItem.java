package me.winiecki.itemModels.gui;

import me.winiecki.itemModels.bows.FreezingBow;
import org.bukkit.Material;

import java.util.List;

public class FreezingBowGUIItem extends GUIItem {

    private final int slot = 16;
    private final Material material = Material.ICE;
    private final String name = new FreezingBow().getName();
    private final List<String> lore = new FreezingBow().getLore();

    public FreezingBowGUIItem() {
        super.setSlot(slot);
        super.setMaterial(material);
        super.setName(name);
        super.setLore(lore);
    }
}
