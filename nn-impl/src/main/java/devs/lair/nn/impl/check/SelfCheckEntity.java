package devs.lair.nn.impl.check;

//import devs.lair.nn.entity.Entity;
//import devs.lair.nn.entity.EntityContainer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public interface SelfCheckEntity<T extends Entity> {
//    List<EntityCheck<T>> getCheckers();
//
//    default List<EntityCheckResult> selfCheck() {
//        List<EntityCheckResult> result = new ArrayList<>();
//
//        if (this instanceof EntityContainer<?> container) {
//            for (Entity item : container.getItems()) {
//                if (item instanceof SelfCheckEntity<?> selfCheckItem) {
//                    result.addAll(selfCheckItem.selfCheck());
//                }
//            }
//        }
//
//        result.addAll(getCheckers().stream().map(entityCheck ->
//                entityCheck.runCheck((T) this)).toList());
//
//        return result;
//    }
//}
