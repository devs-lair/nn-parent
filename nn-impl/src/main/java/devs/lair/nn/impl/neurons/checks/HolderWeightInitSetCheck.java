package devs.lair.nn.impl.neurons.checks;

import devs.lair.nn.impl.check.ItemCheckResult;
import devs.lair.nn.impl.check.ItemCheckResultLevel;
import devs.lair.nn.impl.check.PedanticCheck;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

@Nls
public class HolderWeightInitSetCheck extends PedanticCheck<NeuronParametersHolder> {

    @Override
    public @NotNull ItemCheckResult runCheck(@NotNull NeuronParametersHolder holder) {
        if (holder.getNeuronParameters().getWeightInitStrategy() == null) {
            return new ItemCheckResult(this, holder, ItemCheckResultLevel.INFO,
                    "Weight init strategy not set in " + holder + ". Will be use value from parent or default = "
                            + NeuronParameters.Defaults.DEFAULT_WEIGHT_INIT_STRATEGY);
        }

        return new ItemCheckResult(this, holder, ItemCheckResultLevel.OK,
                holder + " weight init strategy set to " + holder.getNeuronParameters().getWeightInitStrategy());
    }

    @Override
    public @NotNull String getName() {
        return "Is Weight init strategy set in parameters holder?";
    }

    @Override
    public @NotNull Class<NeuronParametersHolder> getCheckClass() {
        return NeuronParametersHolder.class;
    }
}
