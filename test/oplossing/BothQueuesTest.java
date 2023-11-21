package oplossing;

import org.junit.jupiter.api.Test;

public class BothQueuesTest {
    PriorityQueueTest skewHeapTest = new PriorityQueueTest(new SkewHeapFactory());
    PriorityQueueTest leftistHeapTest = new PriorityQueueTest(new MyPriorityQueueFactory());

    @Test
    public void testCreateSkewheap() {
        skewHeapTest.testCreateQueue();
    }

    @Test
    public void testAddSkewheap() {
        skewHeapTest.testAdd();
    }

    @Test
    public void testAddMultipleSkewheap() {
        skewHeapTest.testAddMultiple();
    }

    @Test
    public void testDecreaseKeySkewheap() {
        skewHeapTest.testDecreaseKey();
    }

    @Test
    public void testRandomOperationsSkewheap() {
        skewHeapTest.testrandomOperations();
    }

    @Test
    public void testCreateLeftistheap() {
        leftistHeapTest.testCreateQueue();
    }

    @Test
    public void testAddLeftistheap() {
        leftistHeapTest.testAdd();
    }

    @Test
    public void testAddMultipleLeftistheap() {
        leftistHeapTest.testAddMultiple();
    }

    @Test
    public void testDecreaseKeyLeftistheap() {
        leftistHeapTest.testDecreaseKey();
    }

    @Test
    public void testRandomOperationsLeftistheap() {
        leftistHeapTest.testrandomOperations();
    }
}
