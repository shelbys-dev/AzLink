package com.azuriom.azlink.neoforge.command;

import com.azuriom.azlink.common.command.CommandSender;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;

import java.util.UUID;

public class NeoForgePlayer implements CommandSender {

    private static final Permission PERMISSION_LEVEL_OWNERS = new Permission.HasCommandLevel(PermissionLevel.OWNERS);

    private final ServerPlayer player;

    public NeoForgePlayer(ServerPlayer player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return this.player.getName().getString();
    }

    @Override
    public UUID getUuid() {
        return this.player.getUUID();
    }

    @Override
    public void sendMessage(String message) {
        this.player.sendSystemMessage(ComponentAdapter.toComponent(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.player.permissions().hasPermission(PERMISSION_LEVEL_OWNERS);
    }
}
