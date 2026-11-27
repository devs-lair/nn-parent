package devs.lair.nn.intefaces.base.proto;

import devs.lair.nn.intefaces.base.BaseNetworkModel;
import devs.lair.nn.intefaces.base.BaseNeuronParameters;
import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.model.NeuronParameters;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;

public class NetworkWithParameters extends BaseNetworkModel implements NeuronParametersHolder {
    private final NeuronParameters neuronParameters;

    public NetworkWithParameters() {
        super(NetworkType.MULTILAYER);

        this.neuronParameters = new BaseNeuronParameters();
    }

    @Override
    public @NotNull NeuronParameters getNeuronParameters() {
        return neuronParameters;
    }
}
