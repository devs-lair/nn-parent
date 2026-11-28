package devs.lair.nn.impl.neurons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NeuronTest {

    @Test
    @DisplayName("Transparent neuron test")
    void transparentNeuron() {
        TransparentNeuron transparentNeuron = TransparentNeuron.INSTANCE;

        assertThat(transparentNeuron.isEnabled()).isTrue();
        assertThrows(UnsupportedOperationException.class, () -> transparentNeuron.setEnabled(false));
    }
}