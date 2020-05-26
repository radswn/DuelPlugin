package me.winiecki.events.fightEvents;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnSnowballHitPlayerEvent implements Listener {

    @EventHandler
    public void onSnowballPlayerHit(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Snowman && e.getEntity() instanceof Player) {

            e.setDamage(1.5);

        }

    }

}
