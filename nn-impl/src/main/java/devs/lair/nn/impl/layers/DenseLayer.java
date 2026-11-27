package devs.lair.nn.impl.layers;

import devs.lair.nn.intefaces.base.BaseLayerModel;
import devs.lair.nn.intefaces.base.BaseNeuronParameters;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.NetworkModel;
import devs.lair.nn.intefaces.model.NeuronModel;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DenseLayer extends BaseLayerModel implements NeuronParametersHolder {
    private final NeuronParameters neuronParameters;

    public DenseLayer() {
        super(LayerType.DENSE);

        this.neuronParameters = new BaseNeuronParameters();
    }

    @Override
    public @NotNull DenseLayer addNeuron(@NotNull NeuronModel neuron) {
        super.addNeuron(neuron);
        return this;
    }

    public @NotNull DenseLayer addAllNeurons(@NotNull List<@NotNull NeuronModel> neurons) {
        neurons.forEach(this::addNeuron);
        return this;
    }

    @Override
    public void setParent(@Nullable NetworkModel newParent) {
        super.setParent(newParent);

        if (newParent instanceof NeuronParametersHolder holder) {
            neuronParameters.merge(holder.getNeuronParameters());
        }
    }

    @Override
    public @NotNull NeuronParameters getNeuronParameters() {
        return neuronParameters;
    }

    public DenseLayer setWeightInitStrategy(@Nullable WeightInitStrategy weightInitStrategy) {
        neuronParameters.setWeightInitStrategy(weightInitStrategy);
        return this;
    }

    public DenseLayer setActivationFunction(@Nullable ActivationFunctionName activationFunction) {
        neuronParameters.setActivationFunction(activationFunction);
        return this;
    }
}
