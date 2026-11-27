package devs.lair.nn.intefaces.model;

import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.item.ChildOf;
import devs.lair.nn.intefaces.item.ParentFor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface LayerModel extends ChildOf<NetworkModel>, ParentFor<NeuronModel> {
    @NotNull LayerType getType();

    @NotNull LayerModel addNeuron(@NotNull NeuronModel neuron);

    boolean removeNeuron(@NotNull NeuronModel neuron);

    default @NotNull List<@NotNull NeuronModel> getNeurons() {
        return getChildren();
    }

    default @NotNull NeuronModel getNeuronAt(int index) {
        return getChildren().get(index);
    }

    default int getNeuronsCount() {
        return getChildren().size();
    }
}
