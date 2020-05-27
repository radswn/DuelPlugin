package me.winiecki.events.duelEvents;

import me.winiecki.duels.DuelDatabase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawnAfterDuelEvent implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){

        if(DuelDatabase.temporaryEqHolder.containsKey(e.getPlayer().getName())) {
            e.getPlayer().getInventory().setContents(DuelDatabase.temporaryEqHolder.get(e.getPlayer().getName()));
            DuelDatabase.temporaryEqHolder.remove(e.getPlayer().getName());
        }

    }
}
