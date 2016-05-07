package tw.oktw.sponge.event;

import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Server;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldCreationSettings;
import tw.oktw.sponge.oktwCore;

public class PlayerJoin {
    @Listener
    public void onPlauerJoin(ClientConnectionEvent.Join event) {
        Player player = event.getTargetEntity();
        ConfigurationNode config = oktwCore.getOktwCore().getConfig();
        Text motd = TextSerializers.FORMATTING_CODE.deserialize(config.getNode("motd").getString());
        Text title = TextSerializers.FORMATTING_CODE.deserialize(config.getNode("title", "title").getString());
        Text subtitle = TextSerializers.FORMATTING_CODE.deserialize(config.getNode("title", "subtitle").getString());
        player.sendMessage(motd);
        player.sendTitle(Title.of(title, subtitle));
        Server server = oktwCore.getOktwCore().getGame().getServer();
        server.createWorldProperties(
                WorldCreationSettings.builder()
                        .name(player.getName())
                        .enabled(true)
                        .keepsSpawnLoaded(false)
                        .loadsOnStartup(false)
                        .build()
        ).get().setWorldBorderDiameter(100);
        server.loadWorld(player.getName());
        Location<World> worldLocation = server.getWorld(player.getName()).get().getSpawnLocation();
        player.setLocation(worldLocation);
    }
}
