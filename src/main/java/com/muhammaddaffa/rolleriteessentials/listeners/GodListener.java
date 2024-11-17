package com.muhammaddaffa.rolleriteessentials.listeners;

import com.muhammaddaffa.rolleriteessentials.RolleriteEssentials;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class GodListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player &&
                this.hasGod(player)) {

            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onFoodChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player player &&
                this.hasGod(player)) {

            event.setCancelled(true);
        }
    }

    private boolean hasGod(Player player) {
        return player.getPersistentDataContainer().has(RolleriteEssentials.GOD_KEY);
    }

}
