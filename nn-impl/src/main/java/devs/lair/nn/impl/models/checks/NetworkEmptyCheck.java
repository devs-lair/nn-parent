package devs.lair.nn.impl.models.checks;

import devs.lair.nn.impl.check.CriticalCheck;
import devs.lair.nn.impl.check.ItemCheckResult;
import devs.lair.nn.impl.check.ItemCheckResultLevel;
import devs.lair.nn.intefaces.model.NetworkModel;
import org.jetbrains.annotations.NotNull;

public class NetworkEmptyCheck extends CriticalCheck<NetworkModel> {

    @Override
    public @NotNull ItemCheckResult runCheck(@NotNull NetworkModel network) {
        if (network.getLayers().isEmpty()) {
            return new ItemCheckResult(this, network, ItemCheckResultLevel.ERROR,
                    network + " is empty, no layers");
        }

        return new ItemCheckResult(this, network, ItemCheckResultLevel.OK,
                network + " has %d layers".formatted(network.getLayersCount()));
    }

    @Override
    public @NotNull String getName() {
        return "Is Network empty?";
    }

    @Override
    public @NotNull Class<NetworkModel> getCheckClass() {
        return NetworkModel.class;
    }
}
