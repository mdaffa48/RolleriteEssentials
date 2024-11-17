package com.muhammaddaffa.rolleriteessentials.commands;

import com.muhammaddaffa.rolleriteessentials.RolleriteEssentials;
import com.muhammaddaffa.rolleriteessentials.utils.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderChestCommand implements TabExecutor {

    public static void register(RolleriteEssentials plugin) {
        TabExecutor command = new EnderChestCommand();

        plugin.getCommand("enderchest").setExecutor(command);
        plugin.getCommand("enderchest").setTabCompleter(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("rollerite.enderchest")) {
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.no-permission");
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                Common.sendMessage(sender, "&cUsage: /enderchest <player>");
                return true;
            }
            player.openInventory(player.getEnderChest());
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.target-not-found");
                return true;
            }
            // Open the target ender chest inventory
            target.openInventory(target.getEnderChest());
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return null;
        }
        return List.of();
    }

}
