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

public class DenseLayer extends BaseLayerModel
        implements NeuronParametersHolder {

    private final NeuronParameters neuronParameters;

    public DenseLayer() {
        super(LayerType.DENSE);
        this.neuronParameters = new BaseNeuronParameters();
    }

    public DenseLayer(int neuronCount) {
        this();
        addNeurons(neuronCount, NeuronType.BASIC);
    }

    public DenseLayer(int neuronCount, @NotNull NeuronType type) {
        this();
        addNeurons(neuronCount, type);
    }

    @Override
    public @NotNull DenseLayer addNeuron(@NotNull NeuronModel neuron) {
        super.addNeuron(neuron);
        return this;
    }

    public @NotNull DenseLayer addNeurons(@NotNull List<@NotNull NeuronModel> neurons) {
        neurons.forEach(this::addNeuron);
        return this;
    }

    public @NotNull DenseLayer addNeurons(int neuronCount, @NotNull NeuronType type) {
        for (int i = 0; i < neuronCount; i++) {
            addNeuron(NeuronFactory.createNeuron(type));
        }
        return this;
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
