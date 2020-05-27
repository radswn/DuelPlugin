package me.winiecki.duels;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DuelDatabase {

    public static List<String> fightingPlayers = new ArrayList<>();
    public static List<Duel> currentDuels = new ArrayList<>();
    public static List<Duel> duelsHistory = new ArrayList<>();
    public static Map<String, ItemStack[]> temporaryEqHolder = new HashMap<>();

}
