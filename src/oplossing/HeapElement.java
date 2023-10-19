package oplossing;

import opgave.QueueItem;
public class HeapElement<P extends Comparable<P>,V> implements QueueItem<P,V> {

    private P priority;
    private V value;

    public HeapElement(P priority, V value) {
        this.priority = priority;
        this.value = value;
    }
    @Override
    public P getPriority() {
        return null;
    }

    @Override
    public V getValue() {
        return null;
    }

    @Override
    public void decreaseKey(P newPriority) {

    }
}
