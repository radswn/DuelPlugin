package me.winiecki.events.duelEvents;

import me.winiecki.duels.Duel;
import me.winiecki.duels.DuelDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.Iterator;

public class OnPlayerKickEvent implements Listener {

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {

        Player kickedPlayer = e.getPlayer();

        if (DuelDatabase.fightingPlayers.contains(kickedPlayer.getName())) {

            for (Iterator<Duel> it = DuelDatabase.currentDuels.iterator(); it.hasNext();) {

                Duel d = it.next();
                d.finishDuelIfTheNameMatches(kickedPlayer, it);

            }

        }

    }
}
