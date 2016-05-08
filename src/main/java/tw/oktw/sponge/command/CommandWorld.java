package tw.oktw.sponge.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import tw.oktw.sponge.command.world.*;

import javax.annotation.Nonnull;

public class CommandWorld implements CommandExecutor {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        return CommandResult.success();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .description(Text.of("世界管理指令"))
                .permission("oktw.command.world")
                .executor(this)
                .child(new delete().getSpec(), "delete", "del")
                .child(new list().getSpec(), "list", "ls")
                .child(new teleport().getSpec(), "teleport", "tp")
                .child(new unload().getSpec(), "unload")
                .child(new info().getSpec(), "info")
                .build();
    }
}
