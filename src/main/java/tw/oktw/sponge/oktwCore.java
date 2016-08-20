package tw.oktw.sponge;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.nio.file.Path;

@Plugin(id = "tw.oktw.oktwcore", name = "OKTW Sponge Plugin", version = "0.1.2", description = "OKTW Sponge Plugin")
public class oktwCore {
    @Inject
    private Logger logger;
    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path configPath;

    private static tw.oktw.sponge.oktwCore oktwCore;
    private ConfigManager configManager;
    private DatabaseManager databaseManager;

    public static tw.oktw.sponge.oktwCore getOktwCore() {
        return oktwCore;
    }

    @Listener
    public void onGameInitialization(GameInitializationEvent event) {
        oktwCore = this;
        new CommandLoader().start();
        new EventerManager().start();
        configManager = new ConfigManager();
        databaseManager = new DatabaseManager();
        configManager.start();
        databaseManager.start();
        logger.info("Plugin loaded!");
    }

    Path getConfigPath() {
        return configPath;
    }

    public Logger getLogger() {
        return logger;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public ConfigurationNode getConfig() {
        return configManager.getConfig();
    }
}
