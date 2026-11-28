package devs.lair.nn.impl.layers;

import devs.lair.nn.impl.check.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LayerCheckTest {

    @Test
    @DisplayName("Layers Check")

    void layersChecks() {
        DenseLayer layer = new DenseLayer();

        List<ItemCheckResult> check = ModelChecker.check(layer);
        assertThat(check).hasSize(3);

        long criticalCount = check.stream()
                .filter(result -> result.getCheck() instanceof CriticalCheck<?>).count();
        assertThat(criticalCount).isEqualTo(1);

        long pedanticCount = check.stream()
                .filter(result -> result.getCheck() instanceof PedanticCheck<?>).count();
        assertThat(pedanticCount).isEqualTo(2);

        ItemCheckResult criticalResult = check.stream()
                .filter(result -> result.getCheck() instanceof CriticalCheck<?>).findFirst()
                .orElse(null);

        assertThat(criticalResult).isNotNull();
        assertThat(criticalResult.isError()).isTrue();
        assertThat(criticalResult.getCheck().getType()).isEqualTo(ItemCheckType.CRITICAL);

        DenseLayer withNeurons = new DenseLayer(2);
        List<ItemCheckResult> results = ModelChecker.check(withNeurons);

        assertThat(results).hasSize(7);

        ItemCheckResult itemCheckResult = results.stream()
                .filter(result -> result.getCheck() instanceof CriticalCheck<?>).findFirst()
                .orElse(null);

        assertThat(itemCheckResult).isNotNull();
        assertThat(itemCheckResult.getCheck().getName()).isNotNull();
        assertThat(itemCheckResult.getResultLevel()).isEqualTo(ItemCheckResultLevel.OK);
    }
}
