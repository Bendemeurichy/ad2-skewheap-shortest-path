package oplossing;

import opgave.PriorityQueue;
import opgave.PriorityQueueFactory;

public class MyPriorityQueueFactory implements PriorityQueueFactory {

    @Override
    public <P extends Comparable<P>, V> PriorityQueue<P, V> create() {
        return new MyPriorityQueue<>();
    }
}
