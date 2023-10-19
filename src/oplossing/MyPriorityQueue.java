package oplossing;
import opgave.PriorityQueue;
import opgave.QueueItem;

public class MyPriorityQueue<P extends Comparable<P>, V> implements PriorityQueue<P, V> {
    private HeapElement root;
    @Override
    public QueueItem<P, V> add(P priority, V value) {
        return null;
    }

    @Override
    public QueueItem peek() {
        return null;
    }

    @Override
    public QueueItem poll() {
        return root;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
