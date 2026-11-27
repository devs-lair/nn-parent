package devs.lair.nn.intefaces.base.proto;

import devs.lair.nn.intefaces.base.BaseLayerModel;
import devs.lair.nn.intefaces.base.BaseNeuronParameters;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.model.NetworkModel;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LayerWithParameters extends BaseLayerModel implements NeuronParametersHolder {
    private final NeuronParameters neuronParameters;

    public LayerWithParameters() {
        super(LayerType.DENSE);

        this.neuronParameters = new BaseNeuronParameters();
    }

    @Override
    public @NotNull NeuronParameters getNeuronParameters() {
        return neuronParameters;
    }

    @Override
    public void setParent(@Nullable NetworkModel newParent) {
        super.setParent(newParent);
    }
}
