package tw.oktw.sponge.world;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.storage.WorldProperties;
import tw.oktw.sponge.oktwCore;

import javax.sql.DataSource;

class RegisterWorld {
    private DataSource dataSource = oktwCore.getOktwCore().getDatabaseManager().getDataSource();

    void register(WorldProperties worldProperties, Player player) {
        //TODO 將世界資訊記錄至資料庫
    }
}
