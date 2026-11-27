package devs.lair.nn.intefaces.item;

import org.jetbrains.annotations.Nullable;

public interface ChildOf<T extends ParentFor<?>> extends ModelItem {
    @Nullable T getParent();

    void setParent(@Nullable T parent);
}
