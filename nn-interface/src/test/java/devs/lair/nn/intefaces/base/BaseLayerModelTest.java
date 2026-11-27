package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.base.proto.LayerWithParameters;
import devs.lair.nn.intefaces.base.proto.NetworkWithParameters;
import devs.lair.nn.intefaces.base.proto.TestNeuron;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NetworkModel;
import devs.lair.nn.intefaces.model.NeuronModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseLayerModelTest {

    @Test
    @DisplayName("Instance create and checkType")
    void getType() {
        LayerModel layer = new LayerWithParameters();
        assertThat(layer.getType()).isEqualTo(LayerType.DENSE);
    }

    @Test
    void addNeuron() {
        LayerModel layer = new LayerWithParameters();

        TestNeuron first;
        TestNeuron second;
        layer.addNeuron(first = new TestNeuron()).addNeuron(second = new TestNeuron());

        List<NeuronModel> items = layer.getChildren();
        assertThrows(UnsupportedOperationException.class, () -> items.add(new TestNeuron()));

        assertThat(items).isNotEmpty().hasSize(2);
        assertThat(layer.getNeuronAt(0)).isEqualTo(first);
        assertThrows(IndexOutOfBoundsException.class, () -> layer.getNeuronAt(3));
        assertThat(layer.indexOf(second)).isEqualTo(1);
        assertThat(layer.indexOf(new TestNeuron())).isEqualTo(-1);
        assertThat(layer.getNeuronsCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("Work with parent")
    void setParent() {
        NetworkWithParameters network = new NetworkWithParameters();
        network.getNeuronParameters().setWeightInitStrategy(WeightInitStrategy.CUSTOM);
        network.getNeuronParameters().setActivationFunction(ActivationFunctionName.CUSTOM);

        LayerWithParameters layer = new LayerWithParameters();
        layer.getNeuronParameters().setActivationFunction(ActivationFunctionName.SIGMOID);

        network.addLayer(layer);

        //self, not override
        assertThat(layer.getNeuronParameters().getActivationFunction()).isEqualTo(ActivationFunctionName.SIGMOID);
        assertThat(layer.getNeuronParameters().getWeightInitStrategy()).isNull();

        assertThat(layer.getParent()).isEqualTo(network);
        layer.setParent(network);

        assertThat(layer.getParent()).isEqualTo(network);
        assertThat(network.indexOf(layer)).isEqualTo(0);
    }

    @Test
    @DisplayName("Remove neuron")
    void removeNeuron() {
        LayerModel layerModel = new BaseLayerModel(LayerType.DENSE) {
        };

        TestNeuron first;
        layerModel.addNeuron(first = new TestNeuron());
        layerModel.addNeuron(new TestNeuron());

        assertThat(first.getParent()).isEqualTo(layerModel);

        assertThat(layerModel.getNeuronsCount()).isEqualTo(2);

        assertThat(layerModel.removeNeuron(first)).isTrue();
        assertThat(layerModel.getNeuronsCount()).isEqualTo(1);
        assertThat(layerModel.getNeuronAt(0)).isNotEqualTo(first);
        assertThat(layerModel.removeNeuron(new TestNeuron())).isFalse();

        assertThat(first.getParent()).isNull();
    }

    @Test
    @DisplayName("Get children")
    void getItems() {
        LayerModel layerModel = new BaseLayerModel(LayerType.DENSE) {
        };

        assertThat(layerModel.getChildren()).isEmpty();
        assertThrows(IndexOutOfBoundsException.class,
                () -> assertThat(layerModel.getNeuronAt(0)));

        layerModel.addNeuron(new TestNeuron());
        layerModel.addNeuron(new TestNeuron());

        assertThat(layerModel.getNeurons()).hasSize(2);
        assertThat(layerModel.getNeuronAt(0)).isNotNull();
    }

    @Test
    @DisplayName("Relationships")
    void directCaleSetParent() {
        NetworkModel firstModel = new BaseNetworkModel(NetworkType.MULTILAYER);
        NetworkModel secondModel = new BaseNetworkModel(NetworkType.MULTILAYER);

        LayerModel layer = new BaseLayerModel(LayerType.DENSE) {
        };

        firstModel.addLayer(layer);

        assertThat(layer.getParent()).isEqualTo(firstModel);
        assertThat(firstModel.indexOf(layer)).isEqualTo(0);

        layer.setParent(secondModel);
        assertThat(layer.getParent()).isEqualTo(secondModel);
        assertThat(secondModel.indexOf(layer)).isEqualTo(0);
        assertThat(firstModel.indexOf(layer)).isLessThan(0);

    }
}