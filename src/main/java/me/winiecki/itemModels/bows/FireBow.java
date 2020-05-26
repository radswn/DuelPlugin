package me.winiecki.itemModels.bows;

import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.List;

public class FireBow extends Bow{

    private final ChatColor color = ChatColor.GOLD;
    private final String name = color + "Fire Bow";
    private final String description = color + "Set your enemies on fire!";
    private final List<String> lore = Collections.singletonList(description);

    public FireBow() {
        super.setName(name);
        super.setLore(lore);
    }

}
