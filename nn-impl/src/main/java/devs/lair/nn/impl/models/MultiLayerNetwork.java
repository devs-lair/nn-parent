package devs.lair.nn.impl.models;

import devs.lair.nn.impl.layers.OutputLayer;
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

public class MultiLayerNetwork extends BaseNetworkModel
        implements NeuronParametersHolder {

    private final NeuronParameters neuronParameters;

    public MultiLayerNetwork() {
        super(NetworkType.MULTILAYER);

        this.neuronParameters = new BaseNeuronParameters();
    }

    @Override
    public @NotNull MultiLayerNetwork addLayer(@NotNull LayerModel newLayer) {
        if (hasOutputLayer()) {
            throw new IllegalStateException("Can not add new layer after Output layer");
        }

        super.addLayer(newLayer);
        return this;
    }

    public MultiLayerNetwork addLayers(@NotNull List<@NotNull LayerModel> newLayers) {
        newLayers.forEach(this::addLayer);
        return this;
    }

    @Override
    public void setLayerAt(int index, @NotNull LayerModel layer) {
        if (layer instanceof OutputLayer && index != getLayers().size() - 1) {
            throw new IllegalArgumentException("Output layer can be set only on last position");
        }

        super.setLayerAt(index, layer);
    }

    //Or check getType() and return LayerModel
    public @Nullable OutputLayer getOutputLayer() {
        return !getLayers().isEmpty() && getLayers().getLast() instanceof OutputLayer outputLayer
                ? outputLayer
                : null;
    }

    public boolean hasOutputLayer() {
        return getOutputLayer() != null;
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
