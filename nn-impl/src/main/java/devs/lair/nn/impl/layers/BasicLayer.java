package devs.lair.nn.impl.layers;

import devs.lair.nn.impl.factory.NeuronFactory;
import devs.lair.nn.intefaces.base.BaseLayerModel;
import devs.lair.nn.intefaces.base.BaseNeuronParameters;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NeuronType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.NeuronModel;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BasicLayer extends BaseLayerModel
        implements NeuronParametersHolder {

    private final NeuronParameters neuronParameters;

    public BasicLayer(@NotNull LayerType type) {
        super(type);
        this.neuronParameters = new BaseNeuronParameters();
    }

    @Override
    public @NotNull BasicLayer addNeuron(@NotNull NeuronModel neuron) {
        super.addNeuron(neuron);
        return this;
    }

    public @NotNull BasicLayer addNeurons(@NotNull List<@NotNull NeuronModel> neurons) {
        neurons.forEach(this::addNeuron);
        return this;
    }

    public @NotNull BasicLayer addNeurons(int neuronCount, @NotNull NeuronType type) {
        for (int i = 0; i < neuronCount; i++) {
            addNeuron(NeuronFactory.createNeuron(type));
        }
        return this;
    }

    @Override
    public @NotNull NeuronParameters getNeuronParameters() {
        return neuronParameters;
    }

    public BasicLayer setWeightInitStrategy(@Nullable WeightInitStrategy weightInitStrategy) {
        neuronParameters.setWeightInitStrategy(weightInitStrategy);
        return this;
    }

    public BasicLayer setActivationFunction(@Nullable ActivationFunctionName activationFunction) {
        neuronParameters.setActivationFunction(activationFunction);
        return this;
    }
}
