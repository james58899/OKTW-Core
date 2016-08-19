package tw.oktw.sponge;

import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {
    private DataSource dataSource;

    void start() {
        SqlService sql = Sponge.getServiceManager().provide(SqlService.class).get();
        ConfigurationNode config = oktwCore.getOktwCore().getConfig().getNode("mysql");

        String jdbcUrl = String.format("jdbc:mysql://%s:%S/%s?createDatabaseIfNotExistuser=true&user=%s&password=%s",
                config.getNode("host").getString(),
                config.getNode("port").getInt(),
                config.getNode("database").getString(),
                config.getNode("username").getString(),
                config.getNode("password").getString()
        );
        try {
            dataSource = sql.getDataSource(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
