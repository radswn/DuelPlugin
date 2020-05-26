package me.winiecki;

import me.winiecki.commands.DuelCommand;
import me.winiecki.commands.DuelStatsCommand;
import me.winiecki.events.duelEvents.OnDuelEndEvent;
import me.winiecki.events.duelEvents.OnPlayerDisconnectEvent;
import me.winiecki.events.duelEvents.OnPlayerKickEvent;
import me.winiecki.events.fightEvents.OnArrowHitEvent;
import me.winiecki.events.duelEvents.OnWeaponChooseEvent;
import me.winiecki.events.fightEvents.OnSnowballHitPlayerEvent;
import me.winiecki.events.fightEvents.OnSnowmanDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DuelPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("duel").setExecutor(new DuelCommand());
        getCommand("duelstats").setExecutor(new DuelStatsCommand());

        getServer().getPluginManager().registerEvents(new OnWeaponChooseEvent(), this);
        getServer().getPluginManager().registerEvents(new OnArrowHitEvent(), this);
        getServer().getPluginManager().registerEvents(new OnDuelEndEvent(), this);
        getServer().getPluginManager().registerEvents(new OnSnowballHitPlayerEvent(), this);
        getServer().getPluginManager().registerEvents(new OnSnowmanDeathEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDisconnectEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerKickEvent(), this);

    }

}
