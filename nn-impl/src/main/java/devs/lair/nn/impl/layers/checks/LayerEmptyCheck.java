package devs.lair.nn.impl.layers.checks;

import devs.lair.nn.impl.check.CriticalCheck;
import devs.lair.nn.impl.check.ItemCheckResult;
import devs.lair.nn.impl.check.ItemCheckResultLevel;
import devs.lair.nn.intefaces.model.LayerModel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

@Nls
public class LayerEmptyCheck extends CriticalCheck<LayerModel> {

    @Override
    public @NotNull ItemCheckResult runCheck(@NotNull LayerModel layer) {
        if (layer.getNeurons().isEmpty()) {
            return new ItemCheckResult(this, layer, ItemCheckResultLevel.ERROR,
                    layer + "is empty, do not have neurons" );
        }

        return new ItemCheckResult(this, layer, ItemCheckResultLevel.OK,
                layer + " has %d neurons".formatted(layer.getNeuronsCount()));
    }

    @Override
    public @NotNull String getName() {
        return "Is Layer empty?";
    }

    @Override
    public @NotNull Class<LayerModel> getCheckClass() {
        return LayerModel.class;
    }
}
