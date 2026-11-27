package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.NeuronParameters;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BaseNeuronParameters implements NeuronParameters {
    private Map<String, Object> customParameters;
    private WeightInitStrategy weightInitStrategy;
    private ActivationFunctionName activationFunction;

    @Override
    public @Nullable WeightInitStrategy getWeightInitStrategy() {
        return weightInitStrategy;
    }

    @Override
    public @NotNull NeuronParameters setWeightInitStrategy(@Nullable WeightInitStrategy weightInitStrategy) {
        if (!Objects.equals(this.weightInitStrategy, weightInitStrategy)) {
            this.weightInitStrategy = weightInitStrategy;
        }

        return this;
    }

    @Override
    public @Nullable ActivationFunctionName getActivationFunction() {
        return activationFunction;
    }

    @Override
    public @NotNull NeuronParameters setActivationFunction(@Nullable ActivationFunctionName activationFunction) {
        if (!Objects.equals(this.activationFunction, activationFunction)) {
            this.activationFunction = activationFunction;
        }

        return this;
    }

    @Override
    public @NotNull NeuronParameters setCustomParameter(@NotNull String key, @Nullable Object value) {
        if (customParameters == null) {
            customParameters = new HashMap<>();
        }

        customParameters.put(key, value);
        return this;
    }

    @Override
    public @Nullable Object getCustomParameter(@NotNull String key) {
        return customParameters == null ? null : customParameters.get(key);
    }

    @Override
    public @NotNull Map<String, Object> getCustomParameters() {
        return customParameters == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(customParameters);
    }

    @Override
    public @NotNull NeuronParameters merge(@NotNull NeuronParameters other) {
        if (weightInitStrategy == null) {
            setWeightInitStrategy(other.getWeightInitStrategy());
        }

        if (activationFunction == null) {
            setActivationFunction(other.getActivationFunction());
        }

        return this;
    }
}
