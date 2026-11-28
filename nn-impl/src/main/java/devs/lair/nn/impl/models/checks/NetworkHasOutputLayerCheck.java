package devs.lair.nn.impl.models.checks;

import devs.lair.nn.impl.check.CriticalCheck;
import devs.lair.nn.impl.check.ItemCheckResult;
import devs.lair.nn.impl.check.ItemCheckResultLevel;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.model.NetworkModel;
import org.jetbrains.annotations.NotNull;

public class NetworkHasOutputLayerCheck extends CriticalCheck<NetworkModel> {

    @Override
    public @NotNull ItemCheckResult runCheck(@NotNull NetworkModel network) {
        if (network.getLayers().isEmpty()) {
            return new ItemCheckResult(this, network, ItemCheckResultLevel.ERROR,
                    network + " is empty, no layers");
        }

        long count = network.getLayers().stream()
                .filter(layer -> layer.getType() == LayerType.OUTPUT).count();

        if (count == 0) {
            return new ItemCheckResult(this, network, ItemCheckResultLevel.ERROR,
                    network + " has not Output layer");
        }

        if (count == 1 && network.getLayers().getLast().getType() != LayerType.OUTPUT) {
            return new ItemCheckResult(this, network, ItemCheckResultLevel.ERROR,
                    network + " has Output layer not on last");
        }

        if (count > 1) {
            return new ItemCheckResult(this, network, ItemCheckResultLevel.ERROR,
                    network + " has more one Output layer");
        }

        return new ItemCheckResult(this, network, ItemCheckResultLevel.OK,
                network + " has Output layer at last");
    }

    @Override
    public @NotNull String getName() {
        return "Has Network Output layer at last?";
    }

    @Override
    public @NotNull Class<NetworkModel> getCheckClass() {
        return NetworkModel.class;
    }
}
