package tw.oktw.sponge;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

@Plugin(id = "tw.oktw.oktwcore", name = "OKTW Sponge Plugin", version = "0.1.1", description = "OKTW Sponge Plugin")
public class oktwCore {
    private static tw.oktw.sponge.oktwCore oktwCore;
    private ConfigLoader configLoader;
    private DatabaseManager databaseManager;

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configDir;

    public static tw.oktw.sponge.oktwCore getOktwCore() {
        return oktwCore;
    }

    @Listener
    public void onGameInitialization(GameInitializationEvent event) {
        oktwCore = this;
        new CommandLoader().start();
        new EventerManager().start();
        configLoader = new ConfigLoader();
        databaseManager = new DatabaseManager();
        try {
            configLoader.start();
            databaseManager.start();
            logger.info("Plugin loaded!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public ConfigurationNode getConfig() {
        return configLoader.getConfig();
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    Path getConfigDir() {
        return configDir;
    }
}
