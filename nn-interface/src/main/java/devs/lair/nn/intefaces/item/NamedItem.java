package devs.lair.nn.intefaces.item;

import org.jetbrains.annotations.NotNull;

public interface NamedItem extends ModelItem {
    @NotNull String getName();

    @NotNull NamedItem setName(@NotNull String name);
}
