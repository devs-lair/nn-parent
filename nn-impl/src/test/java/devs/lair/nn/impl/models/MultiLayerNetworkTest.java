package devs.lair.nn.impl.models;

import devs.lair.nn.impl.layers.DenseLayer;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.LayerModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MultiLayerNetworkTest {

    @Test
    @DisplayName("Create")
    void create() {
        MultiLayerNetwork network = new MultiLayerNetwork()
                .addLayer(new DenseLayer(3).setWeightInitStrategy(WeightInitStrategy.XAVIER))
                .addLayer(new DenseLayer(5)).setActivationFunction(ActivationFunctionName.SIGMOID)
                .addLayer(new DenseLayer())
                .addLayers(List.of(new DenseLayer(1), new DenseLayer(2)))
                .setActivationFunction(ActivationFunctionName.CUSTOM)
                .setWeightInitStrategy(WeightInitStrategy.XAVIER);


        assertThat(network.getLayersCount()).isEqualTo(5);
        assertThat(network.getNeuronParameters().getActivationFunction()).isEqualTo(ActivationFunctionName.CUSTOM);
        assertThat(network.getNeuronParameters().getWeightInitStrategy()).isEqualTo(WeightInitStrategy.XAVIER);

        LayerModel emptyLayer = network.getLayerAt(2);
        assertThat(emptyLayer.getNeurons()).isEmpty();
    }
}