package tw.oktw.sponge;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import tw.oktw.sponge.event.PlayerJoin;

class EventerManager {
    void start() {
        EventManager eventerManager = Sponge.getEventManager();
        eventerManager.registerListeners(oktwCore.getOktwCore(), new PlayerJoin());
    }
}
