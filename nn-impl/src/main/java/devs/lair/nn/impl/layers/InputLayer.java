package devs.lair.nn.impl.layers;

import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NeuronType;
import org.jetbrains.annotations.NotNull;

public class InputLayer extends BasicLayer {

    public InputLayer() {
        super(LayerType.INPUT);
      }

    public InputLayer(int neuronCount) {
        this();
        addNeurons(neuronCount, NeuronType.TRANSPARENT);
    }

    public InputLayer(int neuronCount, @NotNull NeuronType type) {
        this();
        addNeurons(neuronCount, type);
    }
}
