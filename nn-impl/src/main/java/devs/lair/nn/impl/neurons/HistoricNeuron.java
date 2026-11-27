package devs.lair.nn.impl.neurons;

import devs.lair.nn.intefaces.base.BaseNeuronModel;
import devs.lair.nn.intefaces.enums.NeuronType;

public class HistoricNeuron extends BaseNeuronModel {

    public HistoricNeuron() {
        super(NeuronType.HISTORIC);
    }
}
