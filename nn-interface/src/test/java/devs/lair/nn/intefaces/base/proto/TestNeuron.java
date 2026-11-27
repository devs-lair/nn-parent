package devs.lair.nn.intefaces.base.proto;

import devs.lair.nn.intefaces.base.BaseNeuronModel;
import devs.lair.nn.intefaces.enums.NeuronType;

public class TestNeuron extends BaseNeuronModel {

    public TestNeuron() {
        super(NeuronType.BASIC);
    }
}
