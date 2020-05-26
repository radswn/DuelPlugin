package me.winiecki.commands;

import me.winiecki.duels.Duel;
import me.winiecki.duels.DuelDatabase;
import me.winiecki.itemModels.gui.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class DuelCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 1) {

                if (!args[0].equals(player.getName())) {

                    if (Bukkit.getPlayer(args[0]) != null) {

                        Player enemy = Bukkit.getPlayer(args[0]);

                        String playerName = player.getName();
                        String enemyName = enemy.getName();

                        if (DuelDatabase.fightingPlayers.contains(playerName)) {

                            player.sendMessage(ChatColor.RED + "You are already fighting in a duel!");


                        } else if (DuelDatabase.fightingPlayers.contains(enemyName)) {

                            player.sendMessage(ChatColor.RED + "Your enemy is already fighting in a duel! Wait for him to finish first.");
                            enemy.sendMessage(ChatColor.GOLD + playerName + ChatColor.RED + " is waiting to duel you!");

                        } else {

                            openMenu(player);
                            openMenu(enemy);

                            DuelDatabase.fightingPlayers.add(playerName);
                            DuelDatabase.fightingPlayers.add(enemyName);

                            Duel duel = new Duel(playerName, enemyName, LocalDateTime.now());
                            DuelDatabase.currentDuels.add(duel);

                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "That player does not exist!");
                    }
                } else {
                    player.sendMessage(ChatColor.GOLD + "If you want to fight your problems, go see a therapist, seriously");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You have to choose one enemy!");
            }
        } else {
            System.out.println("You need to be a player to duel someone!");
        }
        return true;
    }

    public void openMenu(Player player) {

        int menuSize = 27;
        Inventory duelMenu = Bukkit.createInventory(player, menuSize, ChatColor.BLACK + "Choose your weapon!");

        int fireBowSlot = new FireBowGUIItem().getSlot();
        int tntBowSlot = new TNTBowGUIItem().getSlot();
        int teleportBowSlot = new TeleportBowGUIItem().getSlot();
        int freezingBowSlot = new FreezingBowGUIItem().getSlot();

        List<Integer> usedSlots = Arrays.asList(fireBowSlot, tntBowSlot, teleportBowSlot, freezingBowSlot);

        for (int i = 0; i < menuSize; i++) {

            ItemStack itemTuPut = null;

            if (!usedSlots.contains(i)) {

                itemTuPut = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta putMeta = itemTuPut.getItemMeta();
                putMeta.setDisplayName(" ");
                itemTuPut.setItemMeta(putMeta);

            } else if (i == fireBowSlot) {

                itemTuPut = GUIItem.createGUIOption(new FireBowGUIItem());

            } else if (i == tntBowSlot) {

                itemTuPut = GUIItem.createGUIOption(new TNTBowGUIItem());

            } else if (i == teleportBowSlot) {

                itemTuPut = GUIItem.createGUIOption(new TeleportBowGUIItem());

            } else if (i == freezingBowSlot) {

                itemTuPut = GUIItem.createGUIOption(new FreezingBowGUIItem());

            }

            duelMenu.setItem(i, itemTuPut);

        }

        player.openInventory(duelMenu);

    }

}
