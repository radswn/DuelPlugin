package me.winiecki.events.duelEvents;

import me.winiecki.duels.Duel;
import me.winiecki.duels.DuelDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Iterator;

public class OnDuelEndEvent implements Listener {

    @EventHandler
    public void onDuelEnd(PlayerDeathEvent e) {

        Player deadPlayer = e.getEntity();
        String deadPlayerName = deadPlayer.getName();

        if (DuelDatabase.fightingPlayers.contains(deadPlayerName)) {

            for (Iterator<Duel> it = DuelDatabase.currentDuels.iterator(); it.hasNext();) {

                Duel d = it.next();
                d.finishDuelIfTheNameMatches(deadPlayer, it);

            }

        }

    }

}
