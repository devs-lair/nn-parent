package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.base.proto.LayerWithParameters;
import devs.lair.nn.intefaces.enums.LayerType;
import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NetworkModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BaseNetworkModelTest {

    @Test
    @DisplayName("Create instance, check basic params")
    void getType() {
        BaseNetworkModel network = new BaseNetworkModel(NetworkType.MULTILAYER) {
        };

        assertThat(network.getName()).isEqualTo(NetworkModel.UNNAMED);
        assertThat(network.getUuid()).isNotNull().isInstanceOf(UUID.class);
        assertThat(network.getType()).isEqualTo(NetworkType.MULTILAYER);

        String myName = "MyName";
        network.setName(myName);
        network.setName(myName);

        //assertThrows(IllegalArgumentException.class, () -> network.setName(null));
        assertThat(network.getName()).isEqualTo(myName);
    }

    @Test
    void addLayer() {
        BaseNetworkModel network = new BaseNetworkModel(NetworkType.MULTILAYER) {
        };
        LayerModel firstLayer = new BaseLayerModel(LayerType.DENSE) {
        };
        LayerModel secondLayer = new BaseLayerModel(LayerType.DENSE) {
        };

        network.addLayer(firstLayer)
                .addLayer(secondLayer);

        assertThat(network.getChildren()).isNotNull().hasSize(2);
        assertThat(network.getLayers()).isNotNull().hasSize(2);
        assertThat(network.getLayerAt(0)).isEqualTo(firstLayer);
        assertThat(network.getLayerAt(1)).isEqualTo(secondLayer);
        assertThat(network.getLayersCount()).isEqualTo(2);

        assertThat(firstLayer.getParent()).isEqualTo(network);
        assertThat(secondLayer.getParent()).isEqualTo(network);
    }

    @Test
    void removeLayer() {
        BaseNetworkModel network = new BaseNetworkModel(NetworkType.MULTILAYER) {
        };
        LayerModel firstLayer = new BaseLayerModel(LayerType.DENSE) {
        };
        LayerModel secondLayer = new BaseLayerModel(LayerType.DENSE) {
        };

        network.addLayer(firstLayer)
                .addLayer(secondLayer);

        assertThat(network.removeLayer(firstLayer)).isTrue();
        assertThat(network.getChildren()).isNotNull().hasSize(1);
        assertThat(network.getLayers()).isNotNull().hasSize(1);
        assertThat(network.getLayerAt(0)).isEqualTo(secondLayer);
        assertThat(network.getLayersCount()).isEqualTo(1);
        assertThat(network.removeLayer(new LayerWithParameters())).isFalse();

        assertThat(firstLayer.getParent()).isNull();
        assertThat(secondLayer.getParent()).isEqualTo(network);
    }

    @Test
    @DisplayName("Set Layer At")
    void setLayerAt() {
        BaseNetworkModel network = new BaseNetworkModel(NetworkType.MULTILAYER) {};

        BaseLayerModel layer1 = new LayerWithParameters();
        BaseLayerModel layer2 = new LayerWithParameters();

        network.addLayer(layer1);
        network.addLayer(layer2);

        BaseLayerModel layer3 = new LayerWithParameters();

        network.setLayerAt(1, layer3);
        network.setLayerAt(0, layer3);

        assertThat(layer1.getParent()).isNull();
        assertThat(layer2.getParent()).isNull();
        assertThat(layer3.getParent()).isEqualTo(network);
        assertThat(network.getLayerAt(1)).isEqualTo(layer3);
        assertThat(network.getLayerAt(1)).isEqualTo(layer3);

    }
}