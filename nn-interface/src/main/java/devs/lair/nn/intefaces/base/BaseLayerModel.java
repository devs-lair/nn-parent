package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NetworkModel;
import devs.lair.nn.intefaces.model.NeuronModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BaseLayerModel implements LayerModel {
    private final LayerType type;
    private final List<NeuronModel> neurons = new ArrayList<>();
    private NetworkModel network;

    public BaseLayerModel(@NotNull LayerType type) {
        this.type = type;
    }

    @Override
    public @NotNull LayerType getType() {
        return type;
    }

    @Override
    public @NotNull LayerModel addNeuron(@NotNull NeuronModel neuron) {
        neurons.add(neuron);

        if (neuron.getParent() != this) {
            neuron.setParent(this);
        }

        return this;
    }

    @Override
    public boolean removeNeuron(@NotNull NeuronModel neuron) {
        boolean removed = neurons.remove(neuron);
        if (removed && neuron.getParent() != null
                && neuron.getParent().equals(this)) {
            neuron.setParent(null);
        }
        return removed;
    }

    @Override
    public @Nullable NetworkModel getParent() {
        return network;
    }

    @Override
    public void setParent(@Nullable NetworkModel newParent) {
        if (!Objects.equals(this.network, newParent)) {
            NetworkModel oldNetwork = this.network;
            this.network = newParent;

            //Not added to parent yet
            if (newParent != null && newParent.indexOf(this) < 0) {
                newParent.addLayer(this);
            }

            if (oldNetwork != null) {
                oldNetwork.removeLayer(this);
            }
        }
    }

    @Override
    public @NotNull List<NeuronModel> getChildren() {
        return Collections.unmodifiableList(neurons);
    }
}
