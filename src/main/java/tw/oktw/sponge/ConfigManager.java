package tw.oktw.sponge;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ConfigManager {
    private Logger logger = oktwCore.getOktwCore().getLogger();
    private Path configPath = oktwCore.getOktwCore().getConfigPath();
    private ConfigurationLoader<ninja.leaping.configurate.commented.CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setPath(configPath).build();
    private ConfigurationNode config;

    void start() {
        if (!Files.exists(configPath)) {
            config = loader.createEmptyNode(ConfigurationOptions.defaults());
            config.getNode("motd").setValue("這是motd文字\n第二行\n第三行");
            config.getNode("rules").setValue("這是rules文字\n第二行\n第三行");
            config.getNode("title", "title").setValue("&e歡迎加入伺服器！");
            config.getNode("title", "subtitle").setValue("&b這是子標題");
            config.getNode("mysql", "host").setValue("localhost");
            config.getNode("mysql", "port").setValue(3306);
            config.getNode("mysql", "username").setValue("root");
            config.getNode("mysql", "password").setValue("password");
            config.getNode("mysql", "database").setValue("oktw");
            try {
                loader.save(config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else try {
            config = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ConfigurationNode getConfig() {
        return config;
    }
}
