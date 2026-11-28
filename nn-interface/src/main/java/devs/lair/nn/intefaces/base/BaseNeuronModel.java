package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.enums.NeuronType;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NeuronModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class BaseNeuronModel extends BaseNeuronParameters implements NeuronModel {
    public static final String UNPARENT_NEURON = "Unparent neuron";

    private final NeuronType type;

    private LayerModel layer;
    private boolean enabled = true;

    protected BaseNeuronModel(@NotNull NeuronType type) {
        this.type = type;
    }

    @Override
    public @NotNull NeuronType getType() {
        return type;
    }

    @Override
    public @NotNull NeuronModel setEnabled(boolean value) {
        if (!Objects.equals(this.enabled, value)) {
            this.enabled = value;
        }

        return this;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public @Nullable LayerModel getParent() {
        return layer;
    }

    @Override
    public void setParent(@Nullable LayerModel newParent) {
        if (!Objects.equals(this.layer, newParent)) {
            LayerModel oldParent = layer;
            this.layer = newParent;

            //Not added to parent yet
            if (newParent != null && newParent.indexOf(this) < 0) {
                newParent.addNeuron(this);
            }

            if (oldParent != null) {
                oldParent.removeNeuron(this);
            }
        }
    }

    @Override
    public String toString() {
        if (layer == null) {
            return UNPARENT_NEURON;
        }

        return "Neuron number " + layer.indexOf(this) + " of " + layer;
    }
}
