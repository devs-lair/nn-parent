package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.base.proto.LayerWithParameters;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.LayerModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseNeuronModelTest {

    @Test
    @DisplayName("Create Instance")
    void createInstance() {
        BaseNeuronModel neuronModel = new BaseNeuronModel() {
        };

        assertThat(neuronModel.getParent()).isNull();
        assertThat(neuronModel.getActivationFunction()).isNull();
        assertThat(neuronModel.getWeightInitStrategy()).isNull();
        assertThat(neuronModel.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("Work with enabled")
    void workWithEnabled() {
        BaseNeuronModel neuronModel = new BaseNeuronModel() {
        };

        assertThat(neuronModel.isEnabled()).isTrue();

        neuronModel.setEnabled(false);
        neuronModel.setEnabled(false);

        assertThat(neuronModel.isEnabled()).isFalse();
    }

    @Test
    @DisplayName("Link to Layer")
    void linkToLayer() {
        BaseNeuronModel neuronModel = new BaseNeuronModel() {
        };

        BaseLayerModel layerModel = new BaseLayerModel(LayerType.DENSE) {
        };
        layerModel.addNeuron(neuronModel);

        assertThat(neuronModel.getParent()).isEqualTo(layerModel);

        neuronModel.setParent(null);
        neuronModel.setParent(null);
        assertThat(neuronModel.getParent()).isNull();
    }

    @Test
    @DisplayName("Link to Layer with parameters")
    void mergeParameters() {
        BaseNeuronModel neuronModel = new BaseNeuronModel() {
        };
        assertThat(neuronModel.getWeightInitStrategy()).isNull();

        neuronModel.setWeightInitStrategy(WeightInitStrategy.XAVIER);
        assertThat(neuronModel.getWeightInitStrategy()).isEqualTo(WeightInitStrategy.XAVIER);

        LayerWithParameters layerModel = new LayerWithParameters();
        layerModel.getNeuronParameters().setWeightInitStrategy(WeightInitStrategy.CUSTOM);

        BaseNetworkModel network = new BaseNetworkModel(NetworkType.MULTILAYER) {
        };
        network.addLayer(layerModel);

        layerModel.addNeuron(neuronModel);

        assertThat(neuronModel.getParent()).isEqualTo(layerModel);
        assertThat(neuronModel.getWeightInitStrategy()).isEqualTo(WeightInitStrategy.XAVIER);
    }

    @Test
    @DisplayName("Relationships")
    void directCaleSetParent() {
        LayerModel firstLayer = new BaseLayerModel(LayerType.DENSE) {
        };
        LayerModel secondLayer = new BaseLayerModel(LayerType.DENSE) {
        };

        BaseNeuronModel neuron = new BaseNeuronModel() {
        };

        firstLayer.addNeuron(neuron);

        assertThat(neuron.getParent()).isEqualTo(firstLayer);
        assertThat(firstLayer.indexOf(neuron)).isEqualTo(0);

        neuron.setParent(secondLayer);
        assertThat(neuron.getParent()).isEqualTo(secondLayer);
        assertThat(secondLayer.indexOf(neuron)).isEqualTo(0);
        assertThat(firstLayer.indexOf(neuron)).isLessThan(0);

    }
}