package devs.lair.nn.impl.factory;

import devs.lair.nn.impl.neurons.BasicNeuron;
import devs.lair.nn.impl.neurons.HistoricNeuron;
import devs.lair.nn.impl.neurons.TransparentNeuron;
import devs.lair.nn.intefaces.enums.NeuronType;
import devs.lair.nn.intefaces.model.NeuronModel;
import org.jetbrains.annotations.NotNull;

public class NeuronFactory {

    public static @NotNull NeuronModel createNeuron(@NotNull NeuronType type) {
        return switch (type) {
            case BASIC -> new BasicNeuron();
            case HISTORIC -> new HistoricNeuron();
            case TRANSPARENT -> TransparentNeuron.INSTANCE;
        };
    }
}
