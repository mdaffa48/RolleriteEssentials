package com.muhammaddaffa.rolleriteessentials.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Common {

    private static final Pattern HEX_PATTERN = Pattern.compile("(?:&#|#)([A-Fa-f0-9]{6})");

    public static void sendMessage(CommandSender sender, List<String> messages) {
        sendMessage(sender, messages, null);
    }

    public static void sendMessage(CommandSender sender, List<String> messages, Placeholder placeholder) {
        messages.forEach(message -> sendMessage(sender, message, placeholder));
    }

    public static void sendMessage(CommandSender sender, String message) {
        sendMessage(sender, message, null);
    }

    public static void sendMessage(CommandSender sender, String message, Placeholder placeholder) {
        if (message == null || message.isEmpty()) {
            return;
        }
        if (placeholder != null) {
            message = placeholder.translate(message);
        }
        sender.sendMessage(color(message));
    }

    public static List<String> color(List<String> messages) {
        return messages.stream().map(Common::color).collect(Collectors.toList());
    }

    public static String color(final String message) {
        final char colorChar = ChatColor.COLOR_CHAR;

        final Matcher matcher = HEX_PATTERN.matcher(message);
        final StringBuilder buffer = new StringBuilder(message.length() + 4 * 8);

        while (matcher.find()) {
            final String group = matcher.group(1);

            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        String result = matcher.appendTail(buffer).toString();
        return ChatColor.translateAlternateColorCodes('&', result);
    }

}
