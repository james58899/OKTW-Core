package tw.oktw.sponge;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import tw.oktw.sponge.command.motd;
import tw.oktw.sponge.command.rules;

class CommadnManager {
    private CommandManager commadnManager = Sponge.getCommandManager();

    void start() {
        commadnManager.register(oktwCore.getOktwCore(), new motd().getSpec(), "motd");
        commadnManager.register(oktwCore.getOktwCore(), new rules().getSpec(), "rules");
    }
}
