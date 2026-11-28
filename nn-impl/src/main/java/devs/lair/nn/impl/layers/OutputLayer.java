package devs.lair.nn.impl.layers;

import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.LossFunction;
import devs.lair.nn.intefaces.enums.NeuronType;
import org.jetbrains.annotations.NotNull;

public class OutputLayer extends BasicLayer {
    private LossFunction lossFunction;

    public OutputLayer(@NotNull LossFunction lossFunction) {
        super(LayerType.OUTPUT);
        this.lossFunction = lossFunction;
    }

    public OutputLayer(int neuronCount,
                       @NotNull LossFunction lossFunction) {
        this(lossFunction);
        addNeurons(neuronCount, NeuronType.BASIC);
    }

    public OutputLayer(int neuronCount,
                       @NotNull NeuronType type,
                       @NotNull LossFunction lossFunction) {

        this(lossFunction);
        addNeurons(neuronCount, type);
    }

    public LossFunction getLossFunction() {
        return lossFunction;
    }

    public void setLossFunction(LossFunction lossFunction) {
        this.lossFunction = lossFunction;
    }
}
