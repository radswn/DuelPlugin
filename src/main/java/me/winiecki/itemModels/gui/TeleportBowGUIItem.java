package me.winiecki.itemModels.gui;

import me.winiecki.itemModels.bows.TeleportBow;
import org.bukkit.Material;

import java.util.List;

public class TeleportBowGUIItem extends GUIItem {

    private final int slot = 14;
    private final Material material = Material.ENDER_PEARL;
    private final String name = new TeleportBow().getName();
    private final List<String> lore = new TeleportBow().getLore();

    public TeleportBowGUIItem() {
        super.setSlot(slot);
        super.setMaterial(material);
        super.setName(name);
        super.setLore(lore);
    }
}
