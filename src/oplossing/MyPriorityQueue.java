package oplossing;
import opgave.PriorityQueue;
import opgave.QueueItem;

public class MyPriorityQueue<P extends Comparable<P>, V> implements PriorityQueue<P, V> {
    //leftist heap
    private HeapElement<P,V> root;
    int size =0;
    int compareCount=0;
    @Override
    public QueueItem<P, V> add(P priority, V value) {
        HeapElement<P,V> newElement = new HeapElement<>(priority,value,0);
        newElement.setQueue(this);
        if (size==0){
            root = newElement;
        } else {

            root = merge(root,newElement);
        }
        size++;
        return newElement;
    }

    @Override
    public QueueItem<P,V> peek() {
        return root;
    }

    @Override
    public QueueItem<P,V> poll() {
        HeapElement<P,V> res= root;
        root.setQueue(null);
        root=merge(root.getLeftChild(),root.getRightChild());
        size--;
        return res;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public HeapElement<P,V> merge(HeapElement<P,V> heap1, HeapElement<P,V> heap2){
        if (heap1 == null) {
            return heap2;
        }
        if (heap2 == null) {
            return heap1;
        }
        HeapElement<P,V> smallHeap;
        HeapElement<P,V> largeHeap;
        if(heap1.getPriority().compareTo(heap2.getPriority())<0){
            smallHeap=heap1;
            largeHeap=heap2;
        } else {
            smallHeap=heap2;
            largeHeap=heap1;
        }

        compareCount++;


        smallHeap.setRightChild(merge(smallHeap.getRightChild(),largeHeap));
        smallHeap.getRightChild().setParent(smallHeap);

        int nplLeft = smallHeap.getLeftChild() != null ? smallHeap.getLeftChild().getNpl() : 0;
        int nplRight = smallHeap.getRightChild() != null ? smallHeap.getRightChild().getNpl() : 0;
        smallHeap.setNpl(Math.min(nplLeft, nplRight) + 1);
        if(smallHeap.getLeftChild()==null || (nplLeft<nplRight)){
            smallHeap.swapChildren();
        }

        compareCount++;

        return smallHeap;
    }

    public void setRoot(HeapElement<P, V> newRoot) {
        this.root=newRoot;
    }

    public HeapElement<P,V> getRoot() {
        return root;
    }

    public void repairLeftistProperty(HeapElement<P, V> current) {
        if(current.getLeftChild()==null || (current.getRightChild()!=null && current.getLeftChild().getNpl()<current.getRightChild().getNpl())){
            current.swapChildren();
        }
        if(current.getRightChild()==null){
            current.setNpl(0);
        } else {
            current.setNpl(Math.min(current.getLeftChild().getNpl(),current.getRightChild().getNpl())+1);
        }

        if(current.equals(root)){
            return;
        }

        if(current.equals(current.getParent().getLeftChild()) && (current.getParent().getRightChild()==null || current.getNpl()>=current.getParent().getRightChild().getNpl())){
            return;
        }
        compareCount++;
        repairLeftistProperty(current.getParent());


    }

    public int getCompareCount(){
        return compareCount;
    }
}
