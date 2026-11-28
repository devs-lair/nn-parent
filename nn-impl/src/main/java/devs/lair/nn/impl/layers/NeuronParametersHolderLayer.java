package devs.lair.nn.impl.layers;

import devs.lair.nn.intefaces.base.BaseLayerModel;
import devs.lair.nn.intefaces.base.BaseNeuronParameters;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class NeuronParametersHolderLayer extends BaseLayerModel
        implements NeuronParametersHolder {

    private final NeuronParameters neuronParameters;

    public NeuronParametersHolderLayer(@NotNull LayerType type) {
        super(type);

        this.neuronParameters = new BaseNeuronParameters();
    }

    @Override
    public @NotNull NeuronParameters getNeuronParameters() {
        return neuronParameters;
    }
}
