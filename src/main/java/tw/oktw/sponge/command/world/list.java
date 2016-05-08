package tw.oktw.sponge.command.world;

import org.spongepowered.api.Server;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import tw.oktw.sponge.oktwCore;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class list implements CommandExecutor {
    private Server server = oktwCore.getOktwCore().getGame().getServer();

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        PaginationService paginationService = oktwCore.getOktwCore().getGame().getServiceManager().provide(PaginationService.class).get();
        List<Text> worlds = new ArrayList<>();

        server.getWorlds().forEach(world -> worlds.add(Text.of(
                TextActions.showText(Text.of("顯示詳細資訊")),
                TextActions.runCommand("/world info " + world.getName()),
                TextStyles.UNDERLINE,
                world.getName())));
        server.getUnloadedWorlds().forEach(worldProperties -> worlds.add(Text.of(
                TextActions.showText(Text.of("顯示詳細資訊")),
                TextActions.runCommand("/world info " + worldProperties.getWorldName()),
                TextColors.GRAY,
                TextStyles.UNDERLINE,
                worldProperties.getWorldName())));

        paginationService.builder()
                .contents(worlds)
                .title(Text.of(TextColors.GREEN, "World List"))
                .sendTo(src);

        return CommandResult.success();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("列出所有世界"))
                .permission("oktw.command.world.list")
                .executor(this)
                .build();
    }
}
