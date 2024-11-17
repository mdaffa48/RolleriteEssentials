package com.muhammaddaffa.rolleriteessentials.tpa;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TpaRequest {

    private static final Map<UUID, UUID> tpaRequests = new HashMap<>();

    @Nullable
    public static UUID getRequest(Player player) {
        return getRequest(player.getUniqueId());
    }

    @Nullable
    public static UUID getRequest(UUID uuid) {
        return tpaRequests.remove(uuid);
    }

    public static void request(Player receiver, Player requester) {
        request(receiver.getUniqueId(), requester.getUniqueId());
    }

    public static void request(UUID receiver, UUID requester) {
        tpaRequests.put(receiver, requester);
    }

}
