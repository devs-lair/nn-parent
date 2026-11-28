package devs.lair.nn.impl.neurons;

import devs.lair.nn.impl.check.ItemCheckResult;
import devs.lair.nn.impl.check.ItemCheckResultLevel;
import devs.lair.nn.impl.check.ItemCheckType;
import devs.lair.nn.impl.check.ModelChecker;
import devs.lair.nn.impl.neurons.checks.HolderActivationFunctionSetCheck;
import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NeuronCheckTest {

    @Test
    @DisplayName("Holder checks")
    void holderChecks() {
        BasicNeuron neuron = new BasicNeuron();

        List<ItemCheckResult> checkResults = ModelChecker.check(neuron);
        assertThat(checkResults).hasSize(2);

        ItemCheckResult first = checkResults.getFirst();
        assertThat(first.getCheck()).isInstanceOf(HolderActivationFunctionSetCheck.class);
        assertThat(first.getResultLevel()).isEqualTo(ItemCheckResultLevel.INFO);
        assertThat(first.getMessage()).isNotBlank();
        assertThat(first.getTarget()).isEqualTo(neuron);
        assertThat(first.getCheck().getName()).isNotNull();
        assertThat(first.toString()).isNotNull();

        ItemCheckResult second = checkResults.get(1);
        assertThat(second.getCheck().getName()).isNotNull();



        List<ItemCheckResult> check = ModelChecker.check(neuron, ItemCheckType.CRITICAL);
        assertThat(check).isEmpty();

        neuron.setActivationFunction(ActivationFunctionName.CUSTOM);
        neuron.setWeightInitStrategy(WeightInitStrategy.XAVIER);

        List<ItemCheckResult> newChecks = ModelChecker.check(neuron);
        long okCount = newChecks.stream().filter(ItemCheckResult::isOk).count();

        assertThat(okCount).isEqualTo(2);
    }
}