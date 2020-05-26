package me.winiecki.commands;

import me.winiecki.duels.Duel;
import me.winiecki.duels.DuelDatabase;
import me.winiecki.duels.DuelResult;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DuelStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            List<Duel> properDuels = filterDuelsByName(DuelDatabase.duelsHistory, player.getName());

            if (args.length == 0 || args[0].equalsIgnoreCase(player.getName())) {

                if (properDuels.size() > 0)
                    viewStats(properDuels, player.getName());
                else
                    player.sendMessage(ChatColor.GOLD + "You have never fought in a duel, challenge someone!");

            } else if (args.length == 1) {

                String enemyName = args[0];
                List<Duel> duelsWithThatEnemy = filterDuelsByName(properDuels, enemyName);

                if (duelsWithThatEnemy.size() > 0)
                    checkAndPrintDuelsResults(duelsWithThatEnemy, player.getName());
                else
                    player.sendMessage(ChatColor.RED + "You have never fought that player!");


            } else {
                player.sendMessage(ChatColor.RED + "Too many arguments!");
            }

        } else
            System.out.println("You have to be a player to check your stats!");

        return true;
    }

    private void viewStats(List<Duel> duels, String playerName) {

        checkAndPrintDuelsResults(duels, playerName);
        mostDuelsWithOnePlayer(duels, playerName);
        searchForLastDuel(duels, playerName);
    }

    private List<Duel> filterDuelsByName(List<Duel> duels, String name) {

        Predicate<Duel> checkIfNameChecks = duel -> (duel.getPlayer().equalsIgnoreCase(name) || duel.getEnemy().equalsIgnoreCase(name));

        return duels.stream().filter(checkIfNameChecks).collect(Collectors.toList());
    }

    private void checkAndPrintDuelsResults(List<Duel> duels, String name) {

        int won = 0;
        int lost = 0;
        double winRatio;

        for (Duel duel : duels) {
            if (name.equals(duel.getPlayer())) {

                if (duel.getDuelResult().equals(DuelResult.PLAYER_WON))
                    won++;
                else lost++;

            } else {

                if (duel.getDuelResult().equals(DuelResult.ENEMY_WON))
                    won++;
                else lost++;

            }
        }
        double sum = duels.size();

        winRatio = won / sum * 100;
        winRatio *= 100;
        winRatio = Math.round(winRatio);
        winRatio /= 100;

        Bukkit.getPlayer(name).sendMessage(ChatColor.DARK_PURPLE + "You fought in " + (int)sum + " duels");
        Bukkit.getPlayer(name).sendMessage(ChatColor.GREEN + "Duels won: " + won);
        Bukkit.getPlayer(name).sendMessage(ChatColor.RED + "Duels lost: " + lost);
        Bukkit.getPlayer(name).sendMessage(ChatColor.DARK_PURPLE + "Win Ratio: "
                + ChatColor.GOLD + winRatio + "%");
    }

    private void mostDuelsWithOnePlayer(List<Duel> duels, String name) {

        int max = 0;
        String enemy;
        List<String> mostDuelsPlayerName = new ArrayList<>();
        Map<String, Integer> enemiesMap = new HashMap<>();

        for (Duel duel : duels) {

            int count = 1;

            if (name.equals(duel.getPlayer())) {

                enemy = duel.getEnemy();

            } else {

                enemy = duel.getPlayer();

            }

            if (!enemiesMap.containsKey(enemy)) {

                enemiesMap.put(enemy, count);

            } else {

                count = enemiesMap.get(enemy) + 1;
                enemiesMap.replace(enemy, count);

            }

            if (count > max) {
                max = count;
                mostDuelsPlayerName.clear();
                mostDuelsPlayerName.add(enemy);
            } else if (count == max) {
                mostDuelsPlayerName.add(enemy);
            }
        }

        Bukkit.getPlayer(name).sendMessage(ChatColor.BLUE + "Most duels with: ");
        for (String s : mostDuelsPlayerName) {
            Bukkit.getPlayer(name).sendMessage(ChatColor.GOLD + s);
        }

    }

    private void searchForLastDuel(List<Duel> duels, String name) {

        duels.sort(Comparator.comparing(Duel::getDuelTime));
        Bukkit.getPlayer(name).sendMessage(ChatColor.BLUE + "Your last duel:");
        Bukkit.getPlayer(name).sendMessage(duels.get(duels.size() - 1).toString());

    }
}
