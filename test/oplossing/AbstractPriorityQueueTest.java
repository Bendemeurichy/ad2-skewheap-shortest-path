package oplossing;

import opgave.PriorityQueue;
import opgave.QueueItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractPriorityQueueTest {

    private PriorityQueue<Integer, String> pq;
    private Random random;

    public abstract PriorityQueue<Integer, String> createPriorityQueue();

    @BeforeEach
    void setup () {
        this.pq = createPriorityQueue();
        this.random = new Random(42);
    }

    @Test
    public void testOne() {
        assertTrue(pq.isEmpty());
        assertNotNull(pq.add(1, "one"));
        pq.poll();
    }

    @Test
    public void testMultiple() {
        pq.add(1, "a");
        pq.add(2, "b");
        pq.add(2, "c");
        pq.add(20, "d");
        pq.add(3, "e");
        pq.add(2, "f");

        assertEquals(6, pq.size());

        assertEquals("a", pq.peek().getValue());
        assertEquals("a", pq.poll().getValue());

        String next = pq.poll().getValue();
        assertTrue(next.equals("b") || next.equals("c") || next.equals("f"));

        next = pq.poll().getValue();
        assertTrue(next.equals("b") || next.equals("c") || next.equals("f"));

        next = pq.poll().getValue();
        assertTrue(next.equals("b") || next.equals("c") || next.equals("f"));

        assertEquals("e", pq.poll().getValue());
        assertEquals("d", pq.poll().getValue());
    }

    @Test
    public void testDecreasePriorityShouldFail() {
        pq.add(1, "a");
        QueueItem<Integer, String> b = pq.add(2, "b");
        pq.add(3, "c");

        assertThrows(IllegalArgumentException.class, () -> b.decreaseKey(10));
    }

    @Test
    public void testIncreasePriority() {
        pq.add(1, "a");
        pq.add(3, "b");
        pq.add(5, "c");
        QueueItem<Integer, String> i = pq.add(20, "d");

        assertEquals("a", pq.poll().getValue());
        i.decreaseKey(4);

        assertEquals("b", pq.poll().getValue());
        assertEquals("d", pq.poll().getValue());

        assertEquals("c", pq.poll().getValue());
    }

    public String randomString(int n) {
        return IntStream.range(0, n).mapToObj(i -> Character.valueOf((char) ('A' - random.nextInt('z' - 'A'))).toString()).collect(Collectors.joining());
    }

    @Test
    public void testRandomOperations() {
        List<String> items = IntStream.range(0, 100).mapToObj(i -> UUID.randomUUID().toString()).toList();
        for (int i = 0; i < 200; i++) {
            pq.add(random.nextInt(50), items.get(random.nextInt(items.size())));
        }
        int total = 0;
        int prevPriority = -1;
        while(!pq.isEmpty()) {
            QueueItem<Integer, String> item = pq.peek();
            assertTrue(prevPriority <= item.getPriority());
            assertTrue(items.contains(item.getValue()));
            prevPriority = item.getPriority();

            if (item.getPriority() > 1 && random.nextBoolean()) {
                prevPriority /= 2;
                item.decreaseKey(prevPriority);
            } else {
                QueueItem<Integer, String> removed = pq.poll();
                assertEquals(item, removed);
                assertThrows(IllegalStateException.class, () -> item.decreaseKey(-1), "Calling increasePriority on a removed QueueItem should throw IllegalStateException");
                total += 1;
            }
        }
        assertEquals(total, 200);
    }
}
