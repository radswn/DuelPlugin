package me.winiecki.itemModels.gui;

import me.winiecki.itemModels.bows.TNTBow;
import org.bukkit.Material;

import java.util.List;

public class TNTBowGUIItem extends GUIItem {

    private final int slot = 12;
    private final Material material = Material.TNT;
    private final String name = new TNTBow().getName();
    private final List<String> lore = new TNTBow().getLore();

    public TNTBowGUIItem() {
        super.setSlot(slot);
        super.setMaterial(material);
        super.setName(name);
        super.setLore(lore);
    }
}
