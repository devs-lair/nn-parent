package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.base.proto.LayerWithParameters;
import devs.lair.nn.intefaces.base.proto.NetworkWithParameters;
import devs.lair.nn.intefaces.base.proto.TestNeuron;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.enums.NeuronType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NetworkModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseNeuronModelTest {

    @Test
    @DisplayName("Create Instance")
    void createInstance() {
        BaseNeuronModel neuronModel = new BaseNeuronModel(NeuronType.BASIC) {
        };

        assertThat(neuronModel.getParent()).isNull();
        assertThat(neuronModel.getActivationFunction()).isNull();
        assertThat(neuronModel.getWeightInitStrategy()).isNull();
        assertThat(neuronModel.isEnabled()).isTrue();
        assertThat(neuronModel.getType()).isEqualTo(NeuronType.BASIC);
        assertThat(neuronModel.getNeuronParameters()).isNotNull();
    }

    @Test
    @DisplayName("Work with enabled")
    void workWithEnabled() {
        BaseNeuronModel neuronModel = new BaseNeuronModel(NeuronType.BASIC) {
        };

        assertThat(neuronModel.isEnabled()).isTrue();

        neuronModel.setEnabled(false);
        neuronModel.setEnabled(false);

        assertThat(neuronModel.isEnabled()).isFalse();
    }

    @Test
    @DisplayName("Link to Layer")
    void linkToLayer() {
        BaseNeuronModel neuronModel = new BaseNeuronModel(NeuronType.BASIC) {
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
        BaseNeuronModel neuronModel = new BaseNeuronModel(NeuronType.BASIC) {
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

        BaseNeuronModel neuron = new BaseNeuronModel(NeuronType.BASIC) {
        };

        firstLayer.addNeuron(neuron);

        assertThat(neuron.getParent()).isEqualTo(firstLayer);
        assertThat(firstLayer.indexOf(neuron)).isZero();

        neuron.setParent(secondLayer);
        assertThat(neuron.getParent()).isEqualTo(secondLayer);
        assertThat(secondLayer.indexOf(neuron)).isZero();
        assertThat(firstLayer.indexOf(neuron)).isLessThan(0);
    }

    @Test
    @DisplayName("toString()")
    void toStringTest() {
        TestNeuron testNeuron = new TestNeuron();
        assertThat(testNeuron).hasToString(BaseNeuronModel.UNPARENT_NEURON);

        LayerModel layerModel = new LayerWithParameters();
        assertThat(layerModel).hasToString(BaseLayerModel.UNPARENT_LAYER);

        BaseNetworkModel networkModel = new NetworkWithParameters();
        assertThat(networkModel).hasToString(NetworkModel.UNNAMED);

        String myNetwork = "MyNetwork";
        networkModel.setName(myNetwork);
        assertThat(networkModel).hasToString(myNetwork);

        layerModel.addNeuron(testNeuron);
        assertThat(testNeuron).hasToString("Neuron number 0 of " + BaseLayerModel.UNPARENT_LAYER);

        networkModel.addLayer(layerModel);
        assertThat(layerModel).hasToString("Layer number 0 of " + myNetwork);

        assertThat(testNeuron).hasToString("Neuron number 0 of Layer number 0 of " + myNetwork);
    }
}