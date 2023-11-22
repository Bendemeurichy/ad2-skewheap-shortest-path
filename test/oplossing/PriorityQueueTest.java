package oplossing;

import opgave.PriorityQueue;
import opgave.PriorityQueueFactory;
import opgave.QueueItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    int size = 10_000_000;
    PriorityQueueFactory factory;

    PriorityQueueTest(PriorityQueueFactory factory){
        this.factory = factory;
    }
    public PriorityQueue<Integer, String> createPriorityQueue() {
        return factory.create();
    }

    @Test
    public void testCreateQueue() {
        PriorityQueue<Integer, String> queue = createPriorityQueue();
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testAdd() {
        PriorityQueue<Integer, String> queue = createPriorityQueue();
        QueueItem<Integer, String> item = queue.add(1, "one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        item = queue.peek();
        assertEquals("one", item.getValue());
        assertEquals(1, item.getPriority());
    }

    @Test
    public void testAddMultiple(){
        PriorityQueue<Integer, String> queue = createPriorityQueue();
        QueueItem<Integer, String>[] items = new QueueItem[size];
        for (int i = 0; i < size; i++) {
            items[i]=(queue.add(i, "value" + i));
        }
        assertEquals(size, queue.size());
        assertFalse(queue.isEmpty());
        for (int i = 0; i < size; i++) {
            QueueItem<Integer, String> item = queue.peek();
            assertEquals("value" + i, item.getValue());
            assertEquals(i, item.getPriority());
            queue.poll();
        }
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDecreaseKey() {
        PriorityQueue<Integer, String> queue = createPriorityQueue();
        QueueItem<Integer, String>[] items = new QueueItem[size];
        for (int i = 0; i < size; i++) {

            items[i]=(queue.add(size+i, "value" + i));

        }

        for (int i = 0; i < size; i++) {
            items[i].decreaseKey( i);
        }

        for (int i = 0; i < size; i++) {
            assertEquals("value" + i, queue.poll().getValue());
        }

    }

    @Test
    public void testrandomOperations() {

        PriorityQueue<Integer, String> queue = createPriorityQueue();
        QueueItem<Integer, String>[] items = new QueueItem[size];
        for (int i = 0; i < size; i++) {
            items[i]=(queue.add(size+i, "value" + i));
        }

        for (int i = 0; i < size; i++) {
            QueueItem<Integer, String> val = items[i];
            if(i%2==0) {
                val.decreaseKey(size-i);
                assertEquals("value" + i, val.getValue());
            }
            else{
                val.decreaseKey(size-i);
                queue.poll();
                assertThrows(IllegalStateException.class, () -> val.decreaseKey(-1), "Calling increasePriority on a removed QueueItem should throw IllegalStateException");

            }

        }

        assertEquals(size/2, queue.size());

    }

}
