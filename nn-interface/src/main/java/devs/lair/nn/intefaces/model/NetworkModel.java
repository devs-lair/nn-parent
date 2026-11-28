package devs.lair.nn.intefaces.model;

import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.item.ParentFor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface NetworkModel extends ParentFor<LayerModel> {
    String UNNAMED = "Unnamed Network Model";

    @NotNull NetworkType getType();

    @NotNull NetworkModel addLayer(@NotNull LayerModel layer);

    boolean removeLayer(@NotNull LayerModel layer);

    void setLayerAt(int index, @NotNull LayerModel layer);

    default @NotNull List<@NotNull LayerModel> getLayers() {
        return getChildren();
    }

    default @NotNull LayerModel getLayerAt(int index) {
        return getChildren().get(index);
    }

    default int getLayersCount() {
        return getChildren().size();
    }
}
