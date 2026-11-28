package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.enums.NetworkType;
import devs.lair.nn.intefaces.item.NamedItem;
import devs.lair.nn.intefaces.item.UuidIdentity;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NetworkModel;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BaseNetworkModel implements NetworkModel, NamedItem, UuidIdentity {
    private final UUID uuid;
    private final NetworkType type;
    private final List<LayerModel> layers = new ArrayList<>();

    private String name = NetworkModel.UNNAMED;

    public BaseNetworkModel(@NotNull NetworkType type) {
        this.uuid = UUID.randomUUID();
        this.type = type;
    }

    @Override
    public @NotNull NetworkType getType() {
        return type;
    }

    @Override
    public @NotNull NetworkModel addLayer(@NotNull LayerModel newLayer) {
        layers.add(newLayer);
        newLayer.setParent(this);
        return this;
    }

    @Override
    public boolean removeLayer(@NotNull LayerModel layer) {
        boolean removed = layers.remove(layer);
        if (removed && layer.getParent() != null
                && layer.getParent().equals(this)) {

            layer.setParent(null);
        }
        return removed;
    }

    @Override
    public @NotNull List<LayerModel> getChildren() {
        return Collections.unmodifiableList(layers);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull BaseNetworkModel setName(@NotNull String newName) {
        if (!Objects.equals(this.name, newName)) {
            this.name = newName;
        }

        return this;
    }

    @Override
    public @NotNull UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return name;
    }
}
