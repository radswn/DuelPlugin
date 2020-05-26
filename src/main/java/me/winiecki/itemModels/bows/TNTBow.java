package me.winiecki.itemModels.bows;

import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.List;

public class TNTBow extends Bow{

    private final ChatColor color = ChatColor.DARK_RED;
    private final String name = color + "Exploding Bow";
    private final String description = color + "BOOM BOOM!";
    private final List<String> lore = Collections.singletonList(description);

    public TNTBow() {
        super.setName(name);
        super.setLore(lore);
    }

}
