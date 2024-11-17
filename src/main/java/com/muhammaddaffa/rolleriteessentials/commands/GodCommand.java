package com.muhammaddaffa.rolleriteessentials.commands;

import com.muhammaddaffa.rolleriteessentials.RolleriteEssentials;
import com.muhammaddaffa.rolleriteessentials.utils.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GodCommand implements TabExecutor {

    public static void register(RolleriteEssentials plugin) {
        TabExecutor command = new GodCommand();

        plugin.getCommand("god").setExecutor(command);
        plugin.getCommand("god").setTabCompleter(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("rollerite.god")) {
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.no-permission");
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                Common.sendMessage(sender, "&cUsage: /god <player>");
                return true;
            }
            this.toggleGod(player);
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.target-not-found");
                return true;
            }
            // Toggle the god status of the target
            this.toggleGod(target);
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

    private void toggleGod(Player player) {
        if (player.getPersistentDataContainer().has(RolleriteEssentials.GOD_KEY)) {
            player.getPersistentDataContainer().remove(RolleriteEssentials.GOD_KEY);
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(player, "messages.god-off");
        } else {
            player.getPersistentDataContainer().set(RolleriteEssentials.GOD_KEY, PersistentDataType.STRING, "god is good");
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(player, "messages.god-on");
        }
    }

}
