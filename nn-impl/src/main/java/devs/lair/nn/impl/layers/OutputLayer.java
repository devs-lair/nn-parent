package devs.lair.nn.impl.layers;

import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.LossFunction;
import org.jetbrains.annotations.NotNull;

public class OutputLayer extends BasicLayer {
    private LossFunction lossFunction;

    public OutputLayer(@NotNull LossFunction lossFunction) {
        super(LayerType.OUTPUT);
        this.lossFunction = lossFunction;
    }

    public LossFunction getLossFunction() {
        return lossFunction;
    }

    public void setLossFunction(LossFunction lossFunction) {
        this.lossFunction = lossFunction;
    }
}
