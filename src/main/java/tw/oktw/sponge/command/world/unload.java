package tw.oktw.sponge.command.world;

import org.spongepowered.api.Server;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.storage.WorldProperties;
import tw.oktw.sponge.oktwCore;

import javax.annotation.Nonnull;
import java.util.Collection;

public class unload implements CommandExecutor {
    private Server server = oktwCore.getOktwCore().getGame().getServer();

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        Collection<WorldProperties> world = args.getAll("world");
        world.forEach(world1 -> {
            if (server.unloadWorld(server.getWorld(world1.getUniqueId()).get())) {
                src.sendMessage(Text.of(TextColors.GREEN, "世界", world1.getWorldName(), "已經從記憶體中卸載！"));
            } else {
                src.sendMessage(Text.of(TextColors.RED, "世界", world1.getWorldName(), "卸載失敗"));
            }
        });
        return CommandResult.success();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("將世界從記憶體卸載"))
                .permission("oktw.command.world.unload")
                .arguments(GenericArguments.world(Text.of("world")))
                .executor(this)
                .build();
    }
}
