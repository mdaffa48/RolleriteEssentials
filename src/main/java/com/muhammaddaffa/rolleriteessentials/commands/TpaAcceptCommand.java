package com.muhammaddaffa.rolleriteessentials.commands;

import com.muhammaddaffa.rolleriteessentials.RolleriteEssentials;
import com.muhammaddaffa.rolleriteessentials.tpa.TpaRequest;
import com.muhammaddaffa.rolleriteessentials.utils.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TpaAcceptCommand implements CommandExecutor {

    public static void register(RolleriteEssentials plugin) {
        CommandExecutor command = new TpaAcceptCommand();

        plugin.getCommand("tpaccept").setExecutor(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            return true;
        }

        UUID request = TpaRequest.getRequest(player);

        if (request == null || Bukkit.getPlayer(request) == null) {
            RolleriteEssentials.DEFAULT_CONFIG.sendMessage(player, "messages.tpa-request-invalid");
            return true;
        }

        Player target = Bukkit.getPlayer(request);
        // Teleport player to the target location
        target.teleport(player.getLocation());

        // Sends message
        RolleriteEssentials.DEFAULT_CONFIG.sendMessage(player, "messages.tpa-accept", new Placeholder()
                .add("{player}", target.getName()));
        RolleriteEssentials.DEFAULT_CONFIG.sendMessage(target, "messages.tpa-accepted", new Placeholder()
                .add("{player}", player.getName()));

        return true;
    }

}
