package tw.oktw.sponge.world;

import org.slf4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldCreationSettings;
import org.spongepowered.api.world.storage.WorldProperties;
import tw.oktw.sponge.oktwCore;

import java.util.Optional;

public class WorldManager {
    private Logger logger = oktwCore.getOktwCore().getLogger();
    private Server server = Sponge.getServer();

    public void onJoin(Player player) {
        String playerUUID = player.getUniqueId().toString().replaceAll("-", "");

        if (player.getWorld().getName().equals(playerUUID)) {
            player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
            return;
        }

        if (server.getWorldProperties(playerUUID).isPresent()) {
            Optional<World> world = server.loadWorld(playerUUID);
            player.setLocationSafely(world.get().getSpawnLocation().add(0, 4, 0));
            player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
        } else {
            WorldProperties worldProperties = server.createWorldProperties(WorldCreationSettings.builder()
                    .name(playerUUID)
                    .enabled(true)
                    .loadsOnStartup(false)
                    .generateSpawnOnLoad(false)
                    .keepsSpawnLoaded(true)
                    .build()
            ).get();
            Optional<World> world = server.loadWorld(worldProperties);
            if (world.isPresent()) {
                double x, z;
                x = world.get().getSpawnLocation().getX();
                z = world.get().getSpawnLocation().getZ();
                server.unloadWorld(world.get());
                worldProperties.setWorldBorderCenter(x, z);
            }
            new RegisterWorld().register(worldProperties, player);

            player.sendMessage(Text.builder("專屬世界生成完畢！")
                    .color(TextColors.GREEN)
                    .style(TextStyles.UNDERLINE)
                    .onHover(TextActions.showText(Text.of(TextColors.AQUA, "點我傳送到專屬世界")))
                    .onClick(TextActions.executeCallback(commandSource -> {
                        Optional<World> world1 = server.loadWorld(worldProperties);
                        if (world1.isPresent()) {
                            player.setLocationSafely(world1.get().getSpawnLocation().add(0, 4, 0));
                            player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
                        }
                    }))
                    .build()
            );
        }
    }
}
