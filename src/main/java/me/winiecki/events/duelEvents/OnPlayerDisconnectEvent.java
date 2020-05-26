package me.winiecki.events.duelEvents;

import me.winiecki.duels.Duel;
import me.winiecki.duels.DuelDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Iterator;

public class OnPlayerDisconnectEvent implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e) {

        Player discPlayer = e.getPlayer();

        if (DuelDatabase.fightingPlayers.contains(discPlayer.getName())) {

            for (Iterator<Duel> it = DuelDatabase.currentDuels.iterator(); it.hasNext();) {

                Duel d = it.next();
                d.finishDuelIfTheNameMatches(discPlayer, it);

            }

        }

    }
}
