package tw.oktw.sponge;

import org.spongepowered.api.Sponge;
import tw.oktw.sponge.command.motd;
import tw.oktw.sponge.command.rules;
import tw.oktw.sponge.command.spawn;

class CommandLoader {
    private org.spongepowered.api.command.CommandManager commadnManager = Sponge.getCommandManager();

    void start() {
        commadnManager.register(oktwCore.getOktwCore(), new motd().getSpec(), "motd");
        commadnManager.register(oktwCore.getOktwCore(), new rules().getSpec(), "rules");
        commadnManager.register(oktwCore.getOktwCore(), new spawn().getSpec(), "spawn");
    }
}
