package devs.lair.nn.impl.layers;

import devs.lair.nn.impl.neurons.BasicNeuron;
import devs.lair.nn.impl.neurons.HistoricNeuron;
import devs.lair.nn.impl.neurons.TransparentNeuron;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NeuronType;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.NeuronModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DenseLayerTest {

    @Test
    @DisplayName("Create instance")
    void createInstance() {
        DenseLayer denseLayer = new DenseLayer()
                .setActivationFunction(ActivationFunctionName.SIGMOID)
                .setWeightInitStrategy(WeightInitStrategy.XAVIER);

        assertThat(denseLayer.getNeuronParameters().getActivationFunction()).isEqualTo(ActivationFunctionName.SIGMOID);
        assertThat(denseLayer.getNeuronParameters().getWeightInitStrategy()).isEqualTo(WeightInitStrategy.XAVIER);

        assertThat(denseLayer.getType()).isEqualTo(LayerType.DENSE);
    }

    @Test
    @DisplayName("Add neurons")
    void differentConstructors() {
        DenseLayer emptyLayer = new DenseLayer();
        assertThat(emptyLayer.getNeuronsCount()).isZero();
        assertThat(emptyLayer.getNeurons()).isEmpty();

        DenseLayer treeNeuronLayer = new DenseLayer(3);
        assertThat(treeNeuronLayer.getNeuronsCount()).isEqualTo(3);
        assertThat(treeNeuronLayer.getNeuronAt(0).getType()).isEqualTo(NeuronType.BASIC);
        assertThat(treeNeuronLayer.getChildren()).hasSize(3);


        DenseLayer transparentLayer = new DenseLayer(10, NeuronType.TRANSPARENT);
        assertThat(transparentLayer.getNeuronsCount()).isEqualTo(10);
        assertThat(transparentLayer.getNeuronAt(0).getType()).isEqualTo(NeuronType.TRANSPARENT);
        assertThat(transparentLayer.getNeuronAt(2).getType()).isEqualTo(NeuronType.TRANSPARENT);
        assertThat(transparentLayer.getNeuronAt(5).getType()).isEqualTo(NeuronType.TRANSPARENT);
        assertThat(transparentLayer.getNeuronAt(9).getType()).isEqualTo(NeuronType.TRANSPARENT);
        assertThat(transparentLayer.getChildren()).hasSize(10);

        DenseLayer historic = new DenseLayer(10, NeuronType.HISTORIC);
        assertThat(historic.getNeuronsCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("Add neurons")
    void addNeurons() {
        DenseLayer emptyLayer = new DenseLayer();
        assertThat(emptyLayer.getNeuronsCount()).isZero();
        assertThat(emptyLayer.getNeurons()).isEmpty();

        emptyLayer.addNeuron(new BasicNeuron())
                .addNeurons(List.of(new HistoricNeuron(), new HistoricNeuron()))
                .addNeurons(3, NeuronType.TRANSPARENT)
                .setWeightInitStrategy(WeightInitStrategy.XAVIER);

        assertThat(emptyLayer.getNeurons()).hasSize(6);
        assertThat(emptyLayer.getNeuronAt(0)).isInstanceOf(BasicNeuron.class);
        assertThat(emptyLayer.getNeuronAt(1)).isInstanceOf(HistoricNeuron.class);
        assertThat(emptyLayer.getNeuronAt(2)).isInstanceOf(HistoricNeuron.class);
        assertThat(emptyLayer.getNeuronAt(3)).isInstanceOf(TransparentNeuron.class);
        assertThat(emptyLayer.getNeuronAt(4)).isInstanceOf(TransparentNeuron.class);
        assertThat(emptyLayer.getNeuronAt(5)).isInstanceOf(TransparentNeuron.class);

        NeuronModel neuron3 = emptyLayer.getNeuronAt(3);
        NeuronModel neuron4 = emptyLayer.getNeuronAt(4);
        NeuronModel neuron5 = emptyLayer.getNeuronAt(5);

        assertThat(neuron3).isSameAs(neuron4);
        assertThat(neuron4).isSameAs(neuron5);
        assertThat(neuron5).isSameAs(neuron3);
    }

    @Test
    @DisplayName("Crud neurons")
    void crudNeurons() {
        DenseLayer denseLayer = new DenseLayer(5);
        assertThat(denseLayer.getNeuronsCount()).isEqualTo(5);

        assertThat(denseLayer.getNeuronAt(0).getType()).isEqualTo(NeuronType.BASIC);
        denseLayer.setNeuronAt(0, TransparentNeuron.INSTANCE);
        assertThat(denseLayer.getNeuronAt(0).getType()).isEqualTo(NeuronType.TRANSPARENT);
    }
}