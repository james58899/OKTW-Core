package tw.oktw.sponge.command.world;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.storage.WorldProperties;

import java.util.Optional;

public class teleport implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Optional<Player> player = args.getOne("player");
        Optional<WorldProperties> world = args.getOne("world");
        if (!player.isPresent()) {
            src.sendMessage(Text.of(TextColors.RED, "未指定玩家"));
            return CommandResult.empty();
        } else if (!world.isPresent()) {
            src.sendMessage(Text.of(TextColors.RED, "世界不存在"));
            return CommandResult.empty();
        } else {
            player.get().transferToWorld(world.get().getUniqueId(), world.get().getSpawnPosition().toDouble());
            return CommandResult.success();
        }
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("傳送到世界"))
                .permission("oktw.command.world.tp")
                .arguments(GenericArguments.playerOrSource(Text.of("player")), GenericArguments.world(Text.of("world")))
                .executor(this)
                .build();
    }
}
