package devs.lair.nn.intefaces.model;

import devs.lair.nn.intefaces.enums.NeuronType;
import devs.lair.nn.intefaces.item.ChildOf;
import org.jetbrains.annotations.NotNull;

public interface NeuronModel extends ChildOf<LayerModel> {
    @NotNull NeuronType getType();

    boolean isEnabled();

    @NotNull NeuronModel setEnabled(boolean value);
}
