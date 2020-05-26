package me.winiecki.duels;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Duel {

    private String player;
    private String enemy;
    private LocalDateTime duelTime;
    private DuelResult duelResult;

    public Duel(String player, String enemy, LocalDateTime duelTime) {
        this.player = player;
        this.enemy = enemy;
        this.duelTime = duelTime;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public LocalDateTime getDuelTime() {
        return duelTime;
    }

    public void setDuelTime(LocalDateTime duelTime) {
        this.duelTime = duelTime;
    }

    public DuelResult getDuelResult() {
        return duelResult;
    }

    public void setDuelResult(DuelResult duelResult) {
        this.duelResult = duelResult;
    }

    @Override
    public String toString() {

        if(duelResult.equals(DuelResult.PLAYER_WON)){
            return ChatColor.GOLD + "Duel: " + ChatColor.GREEN +  player +  ChatColor.GOLD + " VS " + ChatColor.RED + enemy + ChatColor.GOLD + " time: " + duelTime;
        }
        else {
            return ChatColor.GOLD + "Duel: " + ChatColor.RED + player +  ChatColor.GOLD + " VS " + ChatColor.GREEN + enemy + ChatColor.GOLD + " time: " + duelTime;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Duel)) return false;
        Duel duel = (Duel) o;
        return player.equals(duel.player) &&
                enemy.equals(duel.enemy) &&
                duelTime.equals(duel.duelTime) &&
                duelResult == duel.duelResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, enemy, duelTime, duelResult);
    }

    public void finishDuelIfTheNameMatches(Player deadPlayer, Iterator<Duel> iterator) {

        if (!(deadPlayer.getName().equals(getPlayer())) && !(deadPlayer.getName().equals(getEnemy()))) {
            return;
        } else if (deadPlayer.getName().equals(getPlayer())) {

            setDuelResult(DuelResult.ENEMY_WON);
            sendDuelInfo(true, Bukkit.getPlayer(getEnemy()));

        } else if (deadPlayer.getName().equals(getEnemy())) {

            setDuelResult(DuelResult.PLAYER_WON);
            sendDuelInfo(true, Bukkit.getPlayer(getPlayer()));

        }

        Objects.requireNonNull(Bukkit.getPlayer(getPlayer())).getInventory().setContents(DuelDatabase.temporaryEqHolder.get(getPlayer()).getContents());
        Objects.requireNonNull(Bukkit.getPlayer(getEnemy())).getInventory().setContents(DuelDatabase.temporaryEqHolder.get(getEnemy()).getContents());


        clearSnowmen(deadPlayer.getNearbyEntities(30, 15, 30));
        clearAllLists(iterator);
        sendDuelInfo(false, deadPlayer);

    }

    private void clearAllLists(Iterator<Duel> it) {
        it.remove();
        DuelDatabase.duelsHistory.add(this);
        DuelDatabase.fightingPlayers.remove((getPlayer()));
        DuelDatabase.fightingPlayers.remove((getEnemy()));
    }

    private void clearSnowmen(List<Entity> entities) {

        for (Entity entity : entities) {
            if (entity instanceof Snowman) {

                Mob snowman = (Mob) entity;

                if ((Objects.requireNonNull(snowman.getTarget()).getName().equals(getPlayer())) ||
                        (snowman.getTarget().getName().equals(getEnemy()))) {
                    snowman.setHealth(0);
                }
            }

        }
    }

    private void sendDuelInfo(boolean didWin, Player player) {

        if (didWin)
            player.sendMessage(ChatColor.GREEN + "Congratulations! You won!");
        else
            player.sendMessage(ChatColor.RED + "Too bad! You lost!");

    }
}
