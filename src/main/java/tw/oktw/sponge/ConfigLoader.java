package tw.oktw.sponge;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class ConfigLoader {
    private Path defaultConfig = Paths.get(oktwCore.getOktwCore().getConfigDir().toString(), "config.conf");
    private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setPath(defaultConfig).build();
    private ConfigurationNode config = loader.createEmptyNode(ConfigurationOptions.defaults());

    void start() throws IOException {
        if (!defaultConfig.toFile().exists()) {
            config.getNode("motd").setValue("這是motd文字\n第二行\n第三行");
            config.getNode("rules").setValue("這是rules文字\n第二行\n第三行");
            config.getNode("title", "title").setValue("&e歡迎加入伺服器！");
            config.getNode("title", "subtitle").setValue("&b這是子標題");
            config.getNode("mysql", "host").setValue("localhost");
            config.getNode("mysql", "port").setValue(3306);
            config.getNode("mysql", "username").setValue("root");
            config.getNode("mysql", "password").setValue("password");
            config.getNode("mysql", "database").setValue("oktw");
            loader.save(config);
        }
        config = loader.load();
    }

    ConfigurationNode getConfig() {
        return config;
    }
}
