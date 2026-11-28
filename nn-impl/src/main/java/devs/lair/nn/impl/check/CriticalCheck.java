package devs.lair.nn.impl.check;

import org.jetbrains.annotations.NotNull;

public abstract class CriticalCheck<T> implements ItemCheck<T>{

    @Override
    public @NotNull ItemCheckType getType() {
        return ItemCheckType.CRITICAL;
    }
}
