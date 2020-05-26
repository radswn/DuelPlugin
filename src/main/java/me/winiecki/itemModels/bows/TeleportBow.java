package me.winiecki.itemModels.bows;

import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.List;

public class TeleportBow extends Bow{

    private final ChatColor color = ChatColor.LIGHT_PURPLE;
    private final String name = color + "Teleport Bow";
    private final String description = color + "Attack from behind!";
    private final List<String> lore = Collections.singletonList(color + description);

    public TeleportBow() {
        super.setName(name);
        super.setLore(lore);
    }

}
