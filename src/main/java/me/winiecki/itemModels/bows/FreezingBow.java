package me.winiecki.itemModels.bows;

import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.List;

public class FreezingBow extends Bow {

    private final ChatColor color = ChatColor.AQUA;
    private final String name = color + "Freezing Bow";
    private final String description = color + "Become the master of ice and snow!";
    private final List<String> lore = Collections.singletonList(description);

    public FreezingBow() {
        super.setName(name);
        super.setLore(lore);
    }

}
