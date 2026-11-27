package devs.lair.nn.intefaces.model;

import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface NeuronParameters {
    @Nullable WeightInitStrategy getWeightInitStrategy();

    @NotNull NeuronParameters setWeightInitStrategy(@Nullable WeightInitStrategy weightInitStrategy);

    @Nullable ActivationFunctionName getActivationFunction();

    @NotNull NeuronParameters setActivationFunction(@Nullable ActivationFunctionName activationFunction);

    @NotNull NeuronParameters setCustomParameter(@NotNull String key, @Nullable Object value);

    @NotNull NeuronParameters merge(@NotNull NeuronParameters other);

    @Nullable Object getCustomParameter(@NotNull String key);

    @NotNull Map<String, Object> getCustomParameters();

    interface Keys {
        String CUSTOM_WEIGHT_INIT_STRATEGY = "CUSTOM_WEIGHT_INIT";
        String CUSTOM_ACTIVATION_FUNCTION = "CUSTOM_ACTIVATION_FUNCTION";
    }

    interface Defaults {
        WeightInitStrategy DEFAULT_WEIGHT_INIT_STRATEGY = WeightInitStrategy.XAVIER;
        ActivationFunctionName DEFAULT_ACTIVATION_FUNCTION = ActivationFunctionName.SIGMOID;
    }
}
