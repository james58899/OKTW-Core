package tw.oktw.sponge;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import tw.oktw.sponge.command.*;

class CommandLoader {
    private CommandManager commadnManager = Sponge.getCommandManager();

    void start() {
        commadnManager.register(oktwCore.getOktwCore(), new CommandMotd().getSpec(), "motd");
        commadnManager.register(oktwCore.getOktwCore(), new CommandRules().getSpec(), "rules");
        commadnManager.register(oktwCore.getOktwCore(), new CommandSpawn().getSpec(), "spawn");
        commadnManager.register(oktwCore.getOktwCore(), new CommandWorld().getSpec(), "world");
        commadnManager.register(oktwCore.getOktwCore(), new CommandTPS().getSpec(), "tps");
    }
}
