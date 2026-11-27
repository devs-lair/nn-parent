package devs.lair.nn.intefaces.item;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ParentFor<T extends ChildOf<?>> extends ModelItem {
    @NotNull List<T> getChildren();

    default int indexOf(@NotNull T item) {
        return getChildren().indexOf(item);
    }
}
