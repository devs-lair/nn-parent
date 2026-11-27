package devs.lair.nn.impl.checkers;

//public class NetworkModelWeightInitCheck implements ItemCheck<NetworkModel> {
//    public final static String CHECK_NAME = "Check weight init";
//
//    @Override
//    public CheckResult runCheck(@NotNull NetworkModel networkModel) {
//        WeightInitStrategy weightInitStrategy = networkModel.getWeightInitStrategy();
//
//        if (weightInitStrategy == null) {
//            return new CheckResult(
//                    ResultLevel.INFO,
//                    CHECK_NAME,
//                    "Weight init strategy of network not set, will be used default type: " + NetworkModel.DEFAULT_WEIGHT_INIT_STRATEGY);
//        }
//
//        return new CheckResult(
//                ResultLevel.OK,
//                CHECK_NAME,
//                "Network weight init strategy set to: " + weightInitStrategy);
//    }
//}
