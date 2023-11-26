package oplossing;

import opgave.QueueItem;
public class HeapElement<P extends Comparable<P>,V> implements QueueItem<P,V> {
    int npl;
    private P priority;
    private V value;
    MyPriorityQueue<P,V> queue;
    HeapElement<P,V> leftChild;
    HeapElement<P,V> rightChild;
    HeapElement<P,V> parent;

    public HeapElement(P priority, V value,int npl) {
        this.priority = priority;
        this.value = value;
        this.npl=npl;
    }
    @Override
    public P getPriority() {
        return priority;
    }

    @Override
    public V getValue() {
        return value;
    }

    //implementation pretty standard: remove from queue, fix leftist property, merge with root
    @Override
    public void decreaseKey(P newPriority) {
        //haskell implementation found: https://www.classes.cs.uchicago.edu/archive/2016/winter/22300-1/lectures/LeftistHeaps/index.html
        if(queue==null){
            throw new IllegalStateException("This element is no longer in a queue");
        }

        if (newPriority.compareTo(priority) > 0) {
            throw new IllegalArgumentException("New priority is larger than current priority");
        }
        this.priority = newPriority;

        if(!this.equals(this.queue.getRoot())){
            if (this.equals(this.getParent().getLeftChild())) {
                this.getParent().setLeftChild(null);
            } else {
                this.getParent().setRightChild(null);
            }
            this.queue.repairLeftistProperty(this.parent);
            this.parent = null;

            this.queue.setRoot(this.queue.merge(this.queue.getRoot(), this));
        }

    }



    public HeapElement<P,V> getLeftChild() {
        return leftChild;
    }

    public HeapElement<P,V> getRightChild() {
        return rightChild;
    }

    public HeapElement<P,V> getParent() {
        return parent;
    }

    public void setLeftChild(HeapElement<P,V> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(HeapElement<P,V> rightChild) {
        this.rightChild = rightChild;
    }

    public void setParent(HeapElement<P,V> parent) {
        this.parent = parent;
    }

    public void swapChildren() {
        HeapElement<P,V> temp = leftChild;
        leftChild = rightChild;
        rightChild = temp;
    }

    public void setQueue(MyPriorityQueue<P,V> queue) {
        this.queue = queue;
    }


    public int getNpl() {
        return npl;
    }

    public void setNpl(int i) {
        this.npl=i;
    }
}
