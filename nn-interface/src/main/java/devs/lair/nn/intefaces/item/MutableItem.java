package devs.lair.nn.intefaces.item;

@Deprecated
public interface MutableItem {
    boolean hasChanges();

    void applyChanges();
}
