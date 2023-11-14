package oplossing;

import opgave.PriorityQueue;

public class MyPriorityQueueTest extends AbstractPriorityQueueTest {
    @Override
    public PriorityQueue<Integer, String> createPriorityQueue() {
        return new MyPriorityQueue<>();
    }
}
