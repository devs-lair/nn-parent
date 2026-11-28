package devs.lair.nn.impl.neurons.checks;

import devs.lair.nn.impl.check.ItemCheckResult;
import devs.lair.nn.impl.check.ItemCheckResultLevel;
import devs.lair.nn.impl.check.PedanticCheck;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

@Nls
public class HolderActivationFunctionSetCheck extends PedanticCheck<NeuronParametersHolder> {

    @Override
    public @NotNull ItemCheckResult runCheck(@NotNull NeuronParametersHolder holder) {
        if (holder.getNeuronParameters().getWeightInitStrategy() == null) {
            return new ItemCheckResult(this, holder, ItemCheckResultLevel.INFO,
                    "Activation function not set in " + holder + ". Will be use value from parent or default = "
                            + NeuronParameters.Defaults.DEFAULT_ACTIVATION_FUNCTION);
        }

        return new ItemCheckResult(this, holder, ItemCheckResultLevel.OK,
                holder + " activation function set to " + holder.getNeuronParameters().getActivationFunction());
    }

    @Override
    public @NotNull String getName() {
        return "Is Activation function set in holder?";
    }

    @Override
    public @NotNull Class<NeuronParametersHolder> getCheckClass() {
        return NeuronParametersHolder.class;
    }
}
