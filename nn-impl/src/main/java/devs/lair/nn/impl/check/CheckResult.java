package devs.lair.nn.impl.check;

import org.jetbrains.annotations.NotNull;

public class CheckResult {

    private final String checkName;
    private final String message;
    private final ResultLevel level;

    public CheckResult(@NotNull ResultLevel level,
                       @NotNull String checkName,
                       @NotNull String message) {

        this.checkName = checkName;
        this.level = level;
        this.message = message;
    }

    public String getCheckName() {
        return checkName;
    }

    public ResultLevel getResultLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + level + "] " + checkName + ": " + message;
    }
}
