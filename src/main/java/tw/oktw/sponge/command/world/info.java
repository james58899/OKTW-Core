package tw.oktw.sponge.command.world;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.storage.WorldProperties;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class info implements CommandExecutor {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        PaginationService paginationService = Sponge.getServiceManager().provide(PaginationService.class).get();
        WorldProperties worldProperties = args.<WorldProperties>getOne("world").get();
        List<Text> worldInfo = new ArrayList<>();

        worldInfo.add(Text.of(TextColors.BLUE, "Name: ", TextColors.NONE, worldProperties.getWorldName()));
        worldInfo.add(Text.of(TextColors.BLUE, "UUID: ", TextColors.NONE, worldProperties.getUniqueId().toString()));
        worldInfo.add(Text.of(TextColors.BLUE, "Enabled: ", TextColors.NONE, worldProperties.isEnabled()));
        worldInfo.add(Text.of(TextColors.BLUE, "Dimension Type: ", TextColors.NONE, worldProperties.getDimensionType().getName()));
        worldInfo.add(Text.of(TextColors.BLUE, "Seed: ", TextColors.NONE, worldProperties.getSeed()));
        worldInfo.add(Text.of(TextColors.BLUE, "Gamemode: ", TextColors.NONE, worldProperties.getGameMode().getName()));
        worldInfo.add(Text.of(TextColors.BLUE, "Difficulty: ", TextColors.NONE, worldProperties.getDifficulty().getName()));
        worldInfo.add(Text.of(TextColors.BLUE, "Spawn Position: ", TextColors.NONE, worldProperties.getSpawnPosition().toString()));
        worldInfo.add(Text.of(TextColors.BLUE, "PVP: ", TextColors.NONE, worldProperties.isPVPEnabled()));
        worldInfo.add(Text.of(TextColors.BLUE, "CommandBlcok: ", TextColors.NONE, worldProperties.areCommandsAllowed()));
        worldInfo.add(Text.of(TextColors.BLUE, "Hardcore: ", TextColors.NONE, worldProperties.isHardcore()));
        worldInfo.add(Text.of(TextColors.BLUE, "Keep Spawn Loaded: ", TextColors.NONE, worldProperties.doesKeepSpawnLoaded()));
        worldInfo.add(Text.of(TextColors.BLUE, "World Border: ", TextColors.NONE, worldProperties.getWorldBorderDiameter(), worldProperties.getWorldBorderCenter().toString()));
        worldInfo.add(Text.of(TextColors.BLUE, "Load On Start: ", TextColors.NONE, worldProperties.loadOnStartup()));

        paginationService.builder()
                .contents(worldInfo)
                .title(Text.of(TextColors.GREEN, "World Info"))
                .sendTo(src);

        return CommandResult.success();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("顯示世界詳細資訊"))
                .permission("oktw.command.world.info")
                .arguments(GenericArguments.world(Text.of("world")))
                .executor(this)
                .build();
    }
}
