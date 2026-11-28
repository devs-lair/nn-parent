package devs.lair.nn.impl.check;

import org.jetbrains.annotations.NotNull;

public abstract class PedanticCheck<T> implements ItemCheck<T> {

    @Override
    public @NotNull ItemCheckType getType() {
        return ItemCheckType.PEDANTIC;
    }
}
