package me.winiecki.events.fightEvents;

import me.winiecki.duels.Duel;
import me.winiecki.duels.DuelDatabase;
import me.winiecki.itemModels.bows.FireBow;
import me.winiecki.itemModels.bows.FreezingBow;
import me.winiecki.itemModels.bows.TNTBow;
import me.winiecki.itemModels.bows.TeleportBow;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class OnArrowHitEvent implements Listener {

    @EventHandler
    public void onArrowHit(ProjectileHitEvent e) {

        if (e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Arrow) {

            Player player = (Player) e.getEntity().getShooter();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (itemInHand.getType().equals(Material.BOW) && Objects.requireNonNull(itemInHand.getItemMeta()).getLore() != null) {

                Location loc = e.getEntity().getLocation();
                String descriptionFromLore = itemInHand.getItemMeta().getLore().get(0);

                String fireBowDesc = new FireBow().getLore().get(0);
                String tntBowDesc = new TNTBow().getLore().get(0);
                String teleportBowDesc = new TeleportBow().getLore().get(0);
                String freezingBowDesc = new FreezingBow().getLore().get(0);

                if (descriptionFromLore.equalsIgnoreCase(fireBowDesc)) {

                    setBlocksOnFire(loc);

                } else if (descriptionFromLore.equalsIgnoreCase(tntBowDesc)) {

                    explode(loc, 2);

                } else if (descriptionFromLore.equalsIgnoreCase(teleportBowDesc)) {

                    teleportToArrow(player, loc);

                } else if (descriptionFromLore.equalsIgnoreCase(freezingBowDesc)) {

                    createAnIcyArea(player, loc);

                }

                e.getEntity().remove();

            } else return;

        } else return;

    }

    private void setBlocksOnFire(Location loc) {

        int x = loc.getBlockX() - 1;
        int y = loc.getBlockY() - 1;
        int z = loc.getBlockZ() - 1;

        loc.getWorld().playSound(loc, Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f);

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                for (int k = 0; k < 3; k++) {

                    if (loc.getWorld().getBlockAt(x, y, z).getType().isAir())
                        loc.getWorld().getBlockAt(x, y, z).setType(Material.FIRE);
                    z += 1;
                }
                z = loc.getBlockZ() - 1;
                x += 1;
            }
            x = loc.getBlockX() - 1;
            y += 1;
        }

    }

    private void explode(Location loc, float power) {

        loc.getBlock().getWorld().createExplosion(loc, power, false, false);

    }

    private void teleportToArrow(Player p, Location loc) {

        p.teleport(loc);
        loc.getWorld().spawnParticle(Particle.PORTAL, loc,100);
        loc.getWorld().playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);

    }

    private void createAnIcyArea(Player shooter, Location loc) {

        int x = loc.getBlockX() - 2;
        int y = loc.getBlockY() - 1;
        int z = loc.getBlockZ() - 2;

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                Block block = loc.getWorld().getBlockAt(x, y, z);

                if (block.getType().isSolid() || block.isLiquid())
                    block.setType(Material.ICE);

                z += 1;
            }

            z = loc.getBlockZ() - 2;
            x += 1;
        }

        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();

        for (int i = 0; i < 5; i++) {

            loc.getWorld().getBlockAt(x, y, z).setType(Material.ICE);

            y += 1;
        }

        loc.setX(x + 2);
        loc.setZ(z + 2);

        Mob snowman = (Mob) loc.getWorld().spawnEntity(loc, EntityType.SNOWMAN);
        snowman.setCustomName(ChatColor.AQUA + "SnowSoldier");
        snowman.setCustomNameVisible(false);
        snowman.setAI(true);
        snowman.setAware(true);
        snowman.setTarget(findEnemy(shooter));

    }

    public Player findEnemy(Player player){

        Player enemy = null;
        String nameToSearch = player.getName();

        for(Duel d: DuelDatabase.currentDuels){

            if(nameToSearch.equals(d.getPlayer()))
                enemy = Bukkit.getPlayer(d.getEnemy());
            else if(nameToSearch.equals(d.getEnemy()))
                enemy = Bukkit.getPlayer(d.getPlayer());
        }

        return enemy;
    }
}
