package me.winiecki.events.duelEvents;

import me.winiecki.itemModels.bows.FireBow;
import me.winiecki.itemModels.bows.FreezingBow;
import me.winiecki.itemModels.bows.TNTBow;
import me.winiecki.itemModels.bows.TeleportBow;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static me.winiecki.itemModels.bows.Bow.createBow;


public class OnWeaponChooseEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLACK + "Choose your weapon!")) {

            e.setCancelled(true);

            ItemStack clickedItem = e.getCurrentItem();
            ItemStack bow;

            Player player = (Player) e.getWhoClicked();

            if (clickedItem == null)
                return;

            switch (clickedItem.getType()) {

                case BLAZE_POWDER:
                    bow = createBow(new FireBow());
                    break;

                case TNT:
                    bow = createBow(new TNTBow());
                    break;

                case ENDER_PEARL:
                    bow = createBow(new TeleportBow());
                    player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
                    break;

                case ICE:
                    bow = createBow(new FreezingBow());
                    break;

                default:
                    return;
            }

            player.getInventory().addItem(bow);
            player.getInventory().addItem(new ItemStack(Material.ARROW));

            player.closeInventory();

        }

    }

}
