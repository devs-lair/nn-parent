//package devs.lair.nn.model;
//
//import devs.lair.nn.check.*;
//import devs.lair.nn.entity.Entity;
//import devs.lair.nn.entity.EntityContainer;
//import devs.lair.nn.intefaces.check.SelfCheckEntity;
//import devs.lair.nn.intefaces.base.BaseLayerModel;
//import devs.lair.nn.intefaces.enums.NetworkType;
//import devs.lair.nn.intefaces.enums.WeightInitStrategy;
//import devs.lair.nn.model.checkers.NetworkModelTypeCheck;
//import devs.lair.nn.model.checkers.NetworkModelWeightInitCheck;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.Collections;
//import java.util.List;
//
//public class NetworkModel implements EntityContainer<BaseLayerModel>, SelfCheckEntity<NetworkModel>, Entity {
//
//    public final static NetworkType DEFAULT_NETWORK_TYPE = NetworkType.MULTILAYER;
//    public final static WeightInitStrategy DEFAULT_WEIGHT_INIT_STRATEGY = WeightInitStrategy.XAVIER;
//
//    private String name;
//    private NetworkType type;
//    private WeightInitStrategy weightInitStrategy;
//    private List<BaseLayerModel> layers;
//    private int seed;
//
//    public NetworkModel() {
//        this.name = "Unnamed Network";
//    }
//
//    public NetworkModel(@NotNull String name) {
//        setName(name);
//    }
//
//    @Override
//    public @NotNull String getName() {
//        return name;
//    }
//
//    public NetworkModel setName(@NotNull String name) {
//        this.name = name;
//        return this;
//    }
//
//    public NetworkModel setSeed(int seed) {
//        this.seed = seed;
//        return this;
//    }
//
//    public NetworkModel setWeightInit(@NotNull WeightInitStrategy weightInitStrategy) {
//        this.weightInitStrategy = weightInitStrategy;
//        return this;
//    }
//
//    public int getSeed() {
//        return seed;
//    }
//
//    public @Nullable WeightInitStrategy getWeightInitStrategy() {
//        return weightInitStrategy;
//    }
//
//    public @Nullable NetworkType getType() {
//        return type;
//    }
//
//    public NetworkModel addLayer(@NotNull BaseLayerModel layer) {
//        layers.add(layer);
//        return this;
//    }
//
//    public @NotNull List<@NotNull BaseLayerModel> getLayers() {
//        return getItems();
//    }
//
//    @Override
//    public @NotNull List<@NotNull BaseLayerModel> getItems() {
//        return Collections.unmodifiableList(layers);
//    }
//
//    public List<EntityCheck<NetworkModel>> getCheckers() {
//        return List.of(
//                new NetworkModelTypeCheck(),
//                new NetworkModelWeightInitCheck());
//    }
//}
