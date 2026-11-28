package devs.lair.nn.impl.check;

import org.jetbrains.annotations.NotNull;

public class ItemCheckResult {

    private final ItemCheck<?> check;
    private final ItemCheckResultLevel level;
    private final String message;
    private final Object target;

    public <T> ItemCheckResult(
            @NotNull ItemCheck<T> check,
            @NotNull T target,
            @NotNull ItemCheckResultLevel level,
            @NotNull String message) {

        this.check = check;
        this.level = level;
        this.message = message;
        this.target = target;
    }

    public ItemCheck<?> getCheck() {
        return check;
    }

    public ItemCheckResultLevel getResultLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public Object getTarget() {
        return target;
    }

    public boolean isOk() {
        return level == ItemCheckResultLevel.OK;
    }

    public boolean isError() {
        return level == ItemCheckResultLevel.ERROR;
    }

    @Override
    public String toString() {
        return "[%s] (%s) : %s".formatted(level, check.getName(), message);
    }
}
