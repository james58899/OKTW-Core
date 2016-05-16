package tw.oktw.sponge;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.Task.Builder;

import java.util.concurrent.TimeUnit;

public class SchedulerManager {
    private Scheduler scheduler = Sponge.getScheduler();
    private Builder builder = scheduler.createTaskBuilder();

    void start() {
        scheduler.createTaskBuilder()
                .async()
                .interval(5, TimeUnit.MINUTES)
                .execute(() -> {
                    //TODO 定時執行的東西
                }).submit(oktwCore.getOktwCore());
    }
}
