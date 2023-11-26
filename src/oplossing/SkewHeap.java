package oplossing;

import opgave.PriorityQueue;
import opgave.QueueItem;

public class SkewHeap<P extends Comparable<P>, V> implements PriorityQueue<P,V> {
    private SkewElement<P,V> root;
    int size;
    int compareCount;

    @Override
    public QueueItem<P,V> add(Comparable priority, Object value) {
        SkewElement<P,V> newElement = new SkewElement<>((P) priority, (V)value,this);
        if(size==0){
            root = newElement;
        } else {
            root = merge(root, newElement);
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
        root.setQueue(null);
        QueueItem<P,V> oldRoot = root;
        if(root.getRightChild()==null){
            root = root.getLeftChild();
        } else {
            root = merge(root.getLeftChild(), root.getRightChild());
        }
        if(root!=null) {
            root.setParent(null);
        }
        size--;

        return oldRoot;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // normal recursive top down merge (theory)
    public SkewElement<P,V> merge(SkewElement<P,V> heap1, SkewElement<P,V> heap2){
        if (heap1 == null) {
            return heap2;
        }
        if (heap2 == null) {
            return heap1;
        }

        SkewElement<P,V> smallHeap;
        SkewElement<P,V> largeHeap;
        if(heap1.getPriority().compareTo(heap2.getPriority()) < 0){
            smallHeap = heap1;
            largeHeap = heap2;
        } else {
            smallHeap = heap2;
            largeHeap = heap1;
        }

        compareCount++;
        smallHeap.swapChildren();
        if(smallHeap.getLeftChild()==null){
            smallHeap.setLeftChild(largeHeap);
            largeHeap.setParent(smallHeap);
        } else {
            SkewElement<P,V> temp = merge(smallHeap.getLeftChild(),largeHeap);
            smallHeap.setLeftChild(temp);
            temp.setParent(smallHeap);
        }
        return smallHeap;
    }

    public void setRoot(SkewElement<P,V> newRoot){
        root=newRoot;
    }

    public SkewElement<P,V> getRoot(){
        return root;
    }

    public int getCompareCount(){
        return compareCount;
    }
}
