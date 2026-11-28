package devs.lair.nn.impl.layers;

import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NeuronType;
import org.jetbrains.annotations.NotNull;

public class DenseLayer extends BasicLayer {

    public DenseLayer() {
        super(LayerType.DENSE);
    }

    public DenseLayer(int neuronCount) {
        this();
        addNeurons(neuronCount, NeuronType.BASIC);
    }

    public DenseLayer(int neuronCount, @NotNull NeuronType type) {
        this();
        addNeurons(neuronCount, type);
    }
}