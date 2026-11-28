package devs.lair.nn.impl.models;

import devs.lair.nn.intefaces.base.BaseNetworkModel;
import devs.lair.nn.intefaces.base.BaseNeuronParameters;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MultiLayerNetwork extends BaseNetworkModel implements
        NeuronParametersHolder {

    private final NeuronParameters neuronParameters;

    public MultiLayerNetwork() {
        super(NetworkType.MULTILAYER);

        this.neuronParameters = new BaseNeuronParameters();
    }

    @Override
    public @NotNull MultiLayerNetwork addLayer(@NotNull LayerModel newLayer) {
        super.addLayer(newLayer);
        return this;
    }

    public MultiLayerNetwork addLayers(@NotNull List<@NotNull LayerModel> newLayers) {
        newLayers.forEach(this::addLayer);
        return this;
    }

    @Override
    public @NotNull NeuronParameters getNeuronParameters() {
        return neuronParameters;
    }

    public MultiLayerNetwork setWeightInitStrategy(@NotNull WeightInitStrategy weightInitStrategy) {
        neuronParameters.setWeightInitStrategy(weightInitStrategy);
        return this;
    }

    public MultiLayerNetwork setActivationFunction(@Nullable ActivationFunctionName activationFunction) {
        neuronParameters.setActivationFunction(activationFunction);
        return this;
    }
}
