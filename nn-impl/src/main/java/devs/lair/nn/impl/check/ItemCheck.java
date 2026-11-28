package devs.lair.nn.impl.check;

import org.jetbrains.annotations.NotNull;

public interface ItemCheck<T> {
    String UNNAMED_CHECK = "Unnamed check";

    @NotNull ItemCheckResult runCheck(@NotNull T entity);
    @NotNull ItemCheckType getType();
    @NotNull String getName();
    @NotNull Class<T> getCheckClass();
}