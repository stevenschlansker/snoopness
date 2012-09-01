package ness.snoopness;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SlotList
{
    private final CopyOnWriteArrayList<SlotInfo> slots = new CopyOnWriteArrayList<SlotInfo>();

    {
        slots.add(new SlotInfo());
        slots.add(new SlotInfo());
    }

    public List<SlotInfo> getSlots()
    {
        return slots;
    }
}
