package tw.oktw.sponge.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class spawn implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        return null;
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("回到世界重生點"))
                .permission("oktw.command.spawn")
                .executor(this)
                .build();
    }
}
