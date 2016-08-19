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
import org.spongepowered.api.world.TeleportHelper;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.storage.WorldProperties;
import tw.oktw.sponge.oktwCore;

import java.io.IOException;
import java.util.Optional;

public class WorldManager {
    private Logger logger = oktwCore.getOktwCore().getLogger();
    private Server server = Sponge.getServer();
    private TeleportHelper teleportHelper = Sponge.getGame().getTeleportHelper();

    public void onJoin(Player player) {
        String playerUUID = player.getUniqueId().toString().replaceAll("-", "");

        if (player.getWorld().getName().equals(playerUUID)) {
            player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
            return;
        }

        if (server.getWorldProperties(playerUUID).isPresent()) {
            Optional<World> world = server.loadWorld(playerUUID);
            player.setLocation(teleportHelper.getSafeLocation(world.get().getSpawnLocation(), 255, 0).get());
            player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
        } else {
            WorldProperties worldProperties = null;
            try {
                worldProperties = server.createWorldProperties(playerUUID, WorldArchetype.builder()
                        .enabled(true)
                        .loadsOnStartup(false)
                        .generateSpawnOnLoad(false)
                        .keepsSpawnLoaded(true)
                        .build(playerUUID, playerUUID)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                        Optional<World> world1 = server.loadWorld(playerUUID);
                        if (world1.isPresent()) {
                            player.setLocation(teleportHelper.getSafeLocation(world1.get().getSpawnLocation(), 255, 0).get());
                            player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
                        }
                    }))
                    .build()

            );
        }
    }
}
