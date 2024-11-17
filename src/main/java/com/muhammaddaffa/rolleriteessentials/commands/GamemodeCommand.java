package com.muhammaddaffa.rolleriteessentials.commands;

import com.muhammaddaffa.rolleriteessentials.RolleriteEssentials;
import com.muhammaddaffa.rolleriteessentials.utils.Common;
import com.muhammaddaffa.rolleriteessentials.utils.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamemodeCommand implements TabExecutor {

    public static void register(RolleriteEssentials plugin) {
        TabExecutor command = new GamemodeCommand();

        plugin.getCommand("gamemode").setExecutor(command);
        plugin.getCommand("gamemode").setTabCompleter(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("rollerite.gamemode")) {
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.no-permission");
            return true;
        }

        if (args.length < 1) {
            Common.sendMessage(sender, "&cUsage: /gamemode <type> [player]");
            return true;
        }

        if (args.length == 1) {
            if (!(sender instanceof Player player)) {
                Common.sendMessage(sender, "&cUsage: /gamemode <type> <player>");
                return true;
            }
            GameMode mode = this.getGameMode(args[0]);
            if (mode == null) {
                RolleriteEssentials.DEFAULT_CONFIG.sendMessage(player, "messages.invalid-mode");
                return true;
            }
            this.setGameMode(player, mode);
        }

        if (args.length == 2) {
            GameMode mode = this.getGameMode(args[0]);
            if (mode == null) {
                RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.invalid-mode");
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                RolleriteEssentials.DEFAULT_CONFIG.sendMessage(sender, "messages.target-not-found");
                return true;
            }
            this.setGameMode(target, mode);
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.stream(GameMode.values()).map(mode -> mode.name().toLowerCase()).toList(), new ArrayList<>());
        }
        if (args.length == 2) {
            return null;
        }
        return List.of();
    }

    private void setGameMode(Player player, GameMode mode) {
        // Set the gamemode of player
        player.setGameMode(mode);
        // Sends message
        RolleriteEssentials.DEFAULT_CONFIG.sendMessage(player, "messages.set-gamemode", new Placeholder()
                .add("{mode}", mode.name()));
    }

    @Nullable
    private GameMode getGameMode(String name) {
        try {
            return GameMode.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

}
