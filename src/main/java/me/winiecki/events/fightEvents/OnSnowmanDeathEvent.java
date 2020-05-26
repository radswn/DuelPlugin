package me.winiecki.events.fightEvents;

import org.bukkit.ChatColor;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnSnowmanDeathEvent implements Listener {

    @EventHandler
    public void onSnowmanDeath(EntityDeathEvent e) {

        if (e.getEntity() instanceof Snowman && e.getEntity().getCustomName()
                .equalsIgnoreCase(ChatColor.AQUA + "SnowSoldier")) {

            e.getDrops().clear();

        }

    }

}
