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

public class OpenInvCommand implements TabExecutor {

    public static void register(RolleriteEssentials plugin) {
        TabExecutor command = new OpenInvCommand();

        plugin.getCommand("openinv").setExecutor(command);
        plugin.getCommand("openinv").setTabCompleter(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("rollerite.openinv")) {
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.no-permission");
            return true;
        }

        if (!(sender instanceof Player player)) {
            return true;
        }

        // Open their own ender chest if there is no argument
        if (args.length == 0) {
            player.openInventory(player.getInventory());
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                RolleriteEssentials.DEFAULT_CONFIG.sendMessage(player, "messages.target-not-found");
                return true;
            }
            // Open the target ender chest inventory
            player.openInventory(target.getInventory());
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
