package devs.lair.nn.impl.check;

import devs.lair.nn.impl.layers.checks.LayerEmptyCheck;
import devs.lair.nn.impl.models.checks.NetworkEmptyCheck;
import devs.lair.nn.impl.models.checks.NetworkHasOutputLayerCheck;
import devs.lair.nn.impl.neurons.checks.HolderActivationFunctionSetCheck;
import devs.lair.nn.impl.neurons.checks.HolderWeightInitSetCheck;
import devs.lair.nn.intefaces.item.ChildOf;
import devs.lair.nn.intefaces.item.ModelItem;
import devs.lair.nn.intefaces.item.ParentFor;
import devs.lair.nn.intefaces.model.LayerModel;
import devs.lair.nn.intefaces.model.NetworkModel;
import devs.lair.nn.intefaces.model.NeuronParametersHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO : Make simple
public class ModelChecker {
    private static final Map<Class<?>, List<ItemCheck<?>>> chekersMap = new HashMap<>();

    static {
        putCheckers(NeuronParametersHolder.class, List.of(
                new HolderActivationFunctionSetCheck(),
                new HolderWeightInitSetCheck()));

        putCheckers(LayerModel.class, List.of(
                new LayerEmptyCheck()));

        putCheckers(NetworkModel.class, List.of(
                new NetworkEmptyCheck(),
                new NetworkHasOutputLayerCheck()));
    }

    private ModelChecker() {

    }

    public static <T extends ModelItem> @NotNull List<ItemCheckResult> check(@NotNull T modelItem) {
        return check(modelItem, null);
    }

    public static <T extends ModelItem> @NotNull List<ItemCheckResult> check(@NotNull T modelItem,
                                                                             @Nullable ItemCheckType type) {
        List<ItemCheckResult> checkResults = new ArrayList<>();
        return check(modelItem, type, checkResults);
    }

    private static <T extends ModelItem> @NotNull List<ItemCheckResult> check(@NotNull T modelItem,
                                                                              @Nullable ItemCheckType type,
                                                                              @NotNull List<ItemCheckResult> results) {
        if (modelItem instanceof NeuronParametersHolder holder) {
            List<ItemCheck<NeuronParametersHolder>> checks = getChecks(NeuronParametersHolder.class, type);
            checks.forEach(check -> results.add(check.runCheck(holder)));
        }

        if (modelItem instanceof LayerModel layer) {
            List<ItemCheck<LayerModel>> checks = getChecks(LayerModel.class, type);
            checks.forEach(check -> results.add(check.runCheck(layer)));
        }

        if (modelItem instanceof NetworkModel layer) {
            List<ItemCheck<NetworkModel>> checks = getChecks(NetworkModel.class, type);
            checks.forEach(check -> results.add(check.runCheck(layer)));
        }

        if (modelItem instanceof ParentFor<?> parent) {
            for (ChildOf<?> child : parent.getChildren()) {
                check(child, type, results);
            }
        }

        return results;
    }

    @SuppressWarnings("unchecked")
    private static <C> @NotNull List<ItemCheck<C>> getChecks(@NotNull Class<C> targetClas,
                                                             @Nullable ItemCheckType type) {
        List<ItemCheck<?>> itemChecks = chekersMap.get(targetClas);
        return itemChecks.stream()
                .filter(itemCheck -> (type == null || itemCheck.getType() == type)
                        && (itemCheck.getCheckClass() == targetClas))
                .map(itemCheck -> (ItemCheck<C>) itemCheck).toList();
    }

    private static <C> void putCheckers(@NotNull Class<C> checkClass, @NotNull List<ItemCheck<?>> checkers) {
        for (ItemCheck<?> checker : checkers) {
            if (checker.getCheckClass() != checkClass) {
                throw new IllegalArgumentException("Wrong checker class, expected %s, but got %s"
                        .formatted(checkClass.getSimpleName(), checker.getCheckClass().getSimpleName()));
            }
        }

        chekersMap.put(checkClass, checkers);
    }
}
