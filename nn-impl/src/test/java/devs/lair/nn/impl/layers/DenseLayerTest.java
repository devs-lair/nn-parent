package devs.lair.nn.impl.layers;

import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DenseLayerTest {

    @Test
    @DisplayName("Create instance")
    void createInstance() {
        DenseLayer denseLayer = new DenseLayer();
        denseLayer.setActivationFunction(ActivationFunctionName.SIGMOID);
    }
}