package com.muhammaddaffa.rolleriteessentials.commands;

import com.muhammaddaffa.rolleriteessentials.RolleriteEssentials;
import com.muhammaddaffa.rolleriteessentials.utils.Common;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

public class FixCommand implements CommandExecutor {

    public static void register(RolleriteEssentials plugin) {
        CommandExecutor command = new FixCommand();

        plugin.getCommand("fix").setExecutor(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            return true;
        }

        if (!sender.hasPermission("rollerite.fix")) {
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.no-permission");
            return true;
        }

        ItemStack stack = player.getInventory().getItemInMainHand();
        // Check if the item can be repaired
        if (stack.getItemMeta() instanceof Damageable damageable) {
            // Reset the damage
            damageable.setDamage(0);
            // Set the item meta
            stack.setItemMeta(damageable);
            // Sends message
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.fix");
        }

        return true;
    }
}
