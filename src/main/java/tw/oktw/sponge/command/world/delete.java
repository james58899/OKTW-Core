package tw.oktw.sponge.command.world;

import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
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

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class delete implements CommandExecutor {
    private Server server = Sponge.getServer();

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        Collection<WorldProperties> world = args.getAll("world");
        world.forEach(world1 -> {
            String worldName = world1.getWorldName();
            try {
                if (server.deleteWorld(world1).get()) {
                    src.sendMessage(Text.of(TextColors.GREEN, "世界", worldName, "已刪除！"));
                } else {
                    src.sendMessage(Text.of(TextColors.RED, "世界", worldName, "刪除失敗"));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        return CommandResult.success();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("刪除世界"))
                .permission("oktw.command.world.delete")
                .arguments(GenericArguments.world(Text.of("world")))
                .executor(this)
                .build();
    }
}
