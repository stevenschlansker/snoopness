package ness.snoopness;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mogwee.executors.Executors;

public class Snoopness
{
    private final ExecutorService executor;
    private final ScheduledExecutorService scheduler;
    private Main main;
    private final SlotList slotList = new SlotList();

    Snoopness() {
        executor = Executors.newCachedThreadPool("snoopness");
        scheduler = Executors.newScheduledThreadPool(1, "scheduler");
    }

    void setMain(Main main)
    {
        this.main = main;
    }

    void start() {
        scheduler.scheduleWithFixedDelay(new OrionCrawler(), 0, 100, TimeUnit.MILLISECONDS);
    }

    public SlotList getSlotList() {
        return slotList;
    }

    class OrionCrawler implements Runnable {
        @Override
        public void run()
        {
        }
    }
}
