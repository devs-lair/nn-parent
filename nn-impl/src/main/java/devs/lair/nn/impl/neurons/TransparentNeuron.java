package devs.lair.nn.impl.neurons;

import devs.lair.nn.intefaces.enums.NeuronType;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NeuronModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TransparentNeuron implements NeuronModel {
    public static TransparentNeuron INSTANCE = new TransparentNeuron();

    private TransparentNeuron() {
    }

    @Override
    public @NotNull NeuronType getType() {
        return NeuronType.TRANSPARENT;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public @NotNull NeuronModel setEnabled(boolean value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @Nullable LayerModel getParent() {
        return null;
    }

    @Override
    public void setParent(@Nullable LayerModel parent) {
        //no op
    }
}
