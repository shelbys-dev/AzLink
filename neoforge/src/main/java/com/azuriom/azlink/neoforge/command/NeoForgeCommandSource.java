package com.azuriom.azlink.neoforge.command;

import com.azuriom.azlink.common.command.CommandSender;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class NeoForgeCommandSource implements CommandSender {

    private static final Permission PERMISSION_LEVEL_OWNERS = new Permission.HasCommandLevel(PermissionLevel.OWNERS);

    private final CommandSourceStack source;

    public NeoForgeCommandSource(CommandSourceStack source) {
        this.source = source;
    }

    @Override
    public String getName() {
        return this.source.getTextName();
    }

    @Override
    public UUID getUuid() {
        Entity entity = this.source.getEntity();

        return entity != null
                ? entity.getUUID()
                : UUID.nameUUIDFromBytes(this.source.getTextName().getBytes());
    }

    @Override
    public void sendMessage(String message) {
        this.source.sendSystemMessage(ComponentAdapter.toComponent(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.source.permissions().hasPermission(PERMISSION_LEVEL_OWNERS);
    }
}
