package oplossing;
import org.junit.jupiter.api.Test;

public class BothPathTests {
    ShortestPathTest skewTest = new ShortestPathTest(new SkewHeapFactory());
    ShortestPathTest leftistTest = new ShortestPathTest(new MyPriorityQueueFactory());

    @Test
    public void testShortestPathSimple() {
        skewTest.testShortestPathSimple();
        leftistTest.testShortestPathSimple();
    }

    @Test
    public void testSimpleGraph() {
        skewTest.testShortestPathSimpleGraph();
        leftistTest.testShortestPathSimpleGraph();
    }

    @Test
    public void testShortestPathNoPath() {
        skewTest.testShortestPathNoPath();
        leftistTest.testShortestPathNoPath();
    }

    @Test
    public void testShortestPathSameStartAndend() {
        skewTest.testShortestPathSameStartAndEnd();
        leftistTest.testShortestPathSameStartAndEnd();
    }

    @Test
    public void testLargeGraph() {
        skewTest.testShortestPathWithLargeGraph();
        leftistTest.testShortestPathWithLargeGraph();
    }
}
