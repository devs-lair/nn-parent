package devs.lair.nn.impl.check;

import devs.lair.nn.intefaces.item.ModelItem;

public interface ItemCheck<T extends ModelItem> {
    CheckResult runCheck(T entity);
}