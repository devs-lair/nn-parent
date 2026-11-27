package devs.lair.nn.impl.layers;

import devs.lair.nn.impl.model.Neuron;
import devs.lair.nn.intefaces.base.BaseLayerModel;
import devs.lair.nn.intefaces.base.BaseNeuronParameters;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.NeuronModel;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DenseNeuronLayer extends BaseLayerModel implements NeuronParametersHolder {
    private final NeuronParameters neuronParameters;

    public DenseNeuronLayer() {
        super(LayerType.DENSE_WITH_NEURONS);
        this.neuronParameters = new BaseNeuronParameters();
    }

    public DenseNeuronLayer(int neuronCount) {
        this();

        for (int i = 0; i < neuronCount; i++) {
            addNeuron(new Neuron());
        }
    }

    @Override
    public @NotNull DenseNeuronLayer addNeuron(@NotNull NeuronModel neuron) {
        super.addNeuron(neuron);
        return this;
    }

    public @NotNull DenseNeuronLayer addAllNeurons(@NotNull List<@NotNull NeuronModel> neurons) {
        neurons.forEach(this::addNeuron);
        return this;
    }

    @Override
    public @NotNull NeuronParameters getNeuronParameters() {
        return neuronParameters;
    }

    public DenseNeuronLayer setWeightInitStrategy(@Nullable WeightInitStrategy weightInitStrategy) {
        neuronParameters.setWeightInitStrategy(weightInitStrategy);
        return this;
    }

    public DenseNeuronLayer setActivationFunction(@Nullable ActivationFunctionName activationFunction) {
        neuronParameters.setActivationFunction(activationFunction);
        return this;
    }
}
