package devs.lair.nn.intefaces.base;

import devs.lair.nn.intefaces.enums.ActivationFunctionName;
import devs.lair.nn.intefaces.enums.WeightInitStrategy;
import devs.lair.nn.intefaces.model.NeuronParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseNeuronParametersTest {

    @Test
    @DisplayName("Create instance")
    void createInstance() {
        BaseNeuronParameters params = new BaseNeuronParameters();

        assertThat(params.getWeightInitStrategy()).isNull();
        assertThat(params.getActivationFunction()).isNull();
        assertThat(params.getCustomParameters()).isEmpty();
    }


    @Test
    @DisplayName("Set by flow")
    void setByFlow() {
        String myParameter = "MyParameter";

        NeuronParameters params = new BaseNeuronParameters()
                .setActivationFunction(ActivationFunctionName.SIGMOID)
                .setWeightInitStrategy(WeightInitStrategy.CUSTOM)
                .setCustomParameter(NeuronParameters.Keys.CUSTOM_ACTIVATION_FUNCTION, Object.class)
                .setCustomParameter(myParameter, 1);

        assertThat(params.getActivationFunction()).isEqualTo(ActivationFunctionName.SIGMOID);
        assertThat(params.getWeightInitStrategy()).isEqualTo(WeightInitStrategy.CUSTOM);
        assertThat(params.getCustomParameter(NeuronParameters.Keys.CUSTOM_ACTIVATION_FUNCTION)).isEqualTo(Object.class);
        assertThat(params.getCustomParameter(NeuronParameters.Keys.CUSTOM_WEIGHT_INIT_STRATEGY)).isNull();
        assertThat(params.getCustomParameter(myParameter)).isEqualTo(1);
        assertThat(params.getCustomParameters()).isNotEmpty();
    }

    @Test
    @DisplayName("Merge with other")
    void mergeWithOther() {
        NeuronParameters params = new BaseNeuronParameters();
        NeuronParameters other = new BaseNeuronParameters()
                .setActivationFunction(ActivationFunctionName.SIGMOID)
                .setWeightInitStrategy(WeightInitStrategy.XAVIER);

        other.merge(params).merge(params);

        assertThat(other.getWeightInitStrategy()).isEqualTo(WeightInitStrategy.XAVIER);
        assertThat(other.getActivationFunction()).isEqualTo(ActivationFunctionName.SIGMOID);

        params.merge(other);

        assertThat(params.getWeightInitStrategy()).isEqualTo(WeightInitStrategy.XAVIER);
        assertThat(params.getActivationFunction()).isEqualTo(ActivationFunctionName.SIGMOID);
    }

    @Test
    @DisplayName("Double set")
    void doubleSets() {
        NeuronParameters params = new BaseNeuronParameters();

        params.setActivationFunction(ActivationFunctionName.SIGMOID);
        params.setActivationFunction(ActivationFunctionName.SIGMOID);
        assertThat(params.getActivationFunction()).isEqualTo(ActivationFunctionName.SIGMOID);

        params.setActivationFunction(null);
        params.setActivationFunction(null);
        assertThat(params.getActivationFunction()).isNull();

        params.setWeightInitStrategy(WeightInitStrategy.XAVIER);
        params.setWeightInitStrategy(WeightInitStrategy.XAVIER);
        assertThat(params.getWeightInitStrategy()).isEqualTo(WeightInitStrategy.XAVIER);

        params.setWeightInitStrategy(null);
        params.setWeightInitStrategy(null);
        assertThat(params.getWeightInitStrategy()).isNull();

        assertThat(params.getCustomParameter(NeuronParameters.Keys.CUSTOM_WEIGHT_INIT_STRATEGY)).isNull();
    }

    @Test
    @DisplayName("Check defaults")
    void checkDefaults() {
        assertThat(NeuronParameters.Defaults.DEFAULT_ACTIVATION_FUNCTION)
                .isEqualTo(ActivationFunctionName.SIGMOID);
        assertThat(NeuronParameters.Defaults.DEFAULT_WEIGHT_INIT_STRATEGY)
                .isEqualTo(WeightInitStrategy.XAVIER);
    }


}