package tw.oktw.sponge.command;

import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;
import tw.oktw.sponge.oktwCore;

import javax.annotation.Nonnull;

public class CommandMotd implements CommandExecutor {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        ConfigurationNode config = oktwCore.getOktwCore().getConfig();
        Text motd = TextSerializers.FORMATTING_CODE.deserialize(config.getNode("motd").getString());
        src.sendMessage(motd);
        return CommandResult.success();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("顯示MOTD"))
                .permission("oktw.command.motd")
                .executor(this)
                .build();
    }
}
