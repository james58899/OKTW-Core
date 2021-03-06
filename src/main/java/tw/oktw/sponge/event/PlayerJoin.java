package tw.oktw.sponge.event;

import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;
import tw.oktw.sponge.oktwCore;
import tw.oktw.sponge.world.WorldManager;

import java.io.IOException;

public class PlayerJoin {
    @Listener
    public void onPlauerJoin(ClientConnectionEvent.Join event) throws IOException {
        Player player = event.getTargetEntity();
        ConfigurationNode config = oktwCore.getOktwCore().getConfig();
        Text motd = TextSerializers.FORMATTING_CODE.deserialize(config.getNode("motd").getString());
        Text title = TextSerializers.FORMATTING_CODE.deserialize(config.getNode("title", "title").getString());
        Text subtitle = TextSerializers.FORMATTING_CODE.deserialize(config.getNode("title", "subtitle").getString());
        player.sendMessage(motd);
        player.sendTitle(Title.of(title, subtitle));

        new WorldManager().onJoin(player);
    }
}
