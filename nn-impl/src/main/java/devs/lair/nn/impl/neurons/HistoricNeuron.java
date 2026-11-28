package devs.lair.nn.impl.neurons;

import devs.lair.nn.intefaces.base.BaseNeuronModel;
import devs.lair.nn.intefaces.enums.NeuronType;

public class HistoricNeuron extends BaseNeuronModel {
    private boolean holdHistory = true;

    public HistoricNeuron() {
        super(NeuronType.HISTORIC);
    }

    public boolean isHoldHistory() {
        return holdHistory;
    }

    public void setHoldHistory(boolean holdHistory) {
        this.holdHistory = holdHistory;
    }
}
