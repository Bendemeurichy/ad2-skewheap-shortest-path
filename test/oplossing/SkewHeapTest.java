package oplossing;

import opgave.PriorityQueue;

public class SkewHeapTest extends AbstractPriorityQueueTest {

    @Override
    public PriorityQueue<Integer, String> createPriorityQueue() {
        return new SkewHeap<>();
    }
}
