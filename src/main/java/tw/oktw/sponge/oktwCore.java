package tw.oktw.sponge;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(id = "tw.oktw.oktwcore", name = "OKTW Sponge Plugin", version = "0.1.1", description = "OKTW Sponge Plugin")
public class oktwCore {
    private static tw.oktw.sponge.oktwCore oktwCore;
    private ConfigLoader configLoader;

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configDir;

    public static tw.oktw.sponge.oktwCore getOktwCore() {
        return oktwCore;
    }

    @Listener
    public void onGameInitialization(GameInitializationEvent event) {
        oktwCore = this;
        new CommadnManager().start();
        new EventerManager().start();
        configLoader = new ConfigLoader();
        try {
            configLoader.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Plugin loaded!");
    }

    public Logger getLogger() {
        return logger;
    }

    public Game getGame() {
        return game;
    }

    public ConfigurationNode getConfig() {
        return configLoader.getConfig();
    }

    Path getConfigDir() {
        return configDir;
    }
}
