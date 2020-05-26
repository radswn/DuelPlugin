package me.winiecki.itemModels.gui;

import me.winiecki.itemModels.bows.FireBow;
import org.bukkit.Material;

import java.util.List;

public class FireBowGUIItem extends GUIItem {

    private final int slot = 10;
    private final Material material = Material.BLAZE_POWDER;
    private final String name = new FireBow().getName();
    private final List<String> lore = new FireBow().getLore();

    public FireBowGUIItem() {
        super.setSlot(slot);
        super.setMaterial(material);
        super.setName(name);
        super.setLore(lore);
    }
}
