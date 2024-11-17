package com.muhammaddaffa.rolleriteessentials.commands;

import com.muhammaddaffa.rolleriteessentials.RolleriteEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class TrashCommand implements CommandExecutor {

    public static void register(RolleriteEssentials plugin) {
        CommandExecutor command = new TrashCommand();

        plugin.getCommand("trash").setExecutor(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        if (!sender.hasPermission("rollerite.trash")) {
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.no-permission");
            return true;
        }

        // Create an empty inventory for trash can
        Inventory inventory = Bukkit.createInventory(null, 27, "Trash Bin");
        // Open the inventory to the player
        player.openInventory(inventory);

        return true;
    }

}
