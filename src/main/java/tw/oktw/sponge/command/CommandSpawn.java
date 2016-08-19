package tw.oktw.sponge.command;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.TeleportHelper;

import javax.annotation.Nonnull;

public class CommandSpawn implements CommandExecutor {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        TeleportHelper teleportHelper = Sponge.getGame().getTeleportHelper();
        Player player = args.<Player>getOne("player").get();
        player.setLocation(teleportHelper.getSafeLocation(player.getWorld().getSpawnLocation(), 255, 0).get());
        return CommandResult.success();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("回到世界重生點"))
                .permission("oktw.command.spawn")
                .arguments(GenericArguments.playerOrSource(Text.of("player")))
                .executor(this)
                .build();
    }
}
