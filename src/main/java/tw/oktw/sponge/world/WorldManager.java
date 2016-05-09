package tw.oktw.sponge.world;

import org.slf4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldCreationSettings;
import org.spongepowered.api.world.storage.WorldProperties;
import tw.oktw.sponge.oktwCore;

import java.util.Optional;

public class WorldManager {
    private Logger logger = oktwCore.getOktwCore().getLogger();
    private Server server = oktwCore.getOktwCore().getGame().getServer();

    public void onJoin(Player player) {
        if (server.getWorldProperties(player.getName()).isPresent()) {
            if (!player.getWorld().getName().equals(player.getName())) {
                teleport(player);
            }
        } else {
            createWorld(player);
        }
    }

    private void createWorld(Player player) {
        logger.info("生成新世界中...");
        WorldProperties worldProperties = server.createWorldProperties(
                WorldCreationSettings.builder()
                        .name(player.getName())
                        .enabled(true)
                        .keepsSpawnLoaded(false)
                        .loadsOnStartup(false)
                        .build()
        ).get();
        Optional<World> world = server.loadWorld(worldProperties);
        logger.info("世界生成完畢");
        server.unloadWorld(world.get());
        worldProperties.setWorldBorderDiameter(100);
        worldProperties.setWorldBorderCenter(worldProperties.getSpawnPosition().getX(), worldProperties.getSpawnPosition().getZ());
        player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&c專屬世界生成完畢！\n&9請重新登入"));
    }

    private void teleport(Player player) {
        logger.info("傳送玩家至專屬世界");
        Optional<World> world = server.loadWorld(player.getName());
        player.setLocationSafely(world.get().getSpawnLocation());
    }
}
