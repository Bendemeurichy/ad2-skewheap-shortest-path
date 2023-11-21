package oplossing;

import opgave.QueueItem;

public class SkewElement<P extends Comparable<P>,V> implements QueueItem<P, V> {
    SkewHeap<P,V> queue;
    SkewElement<P,V> leftChild;
    SkewElement<P,V> rightChild;
    SkewElement<P,V> parent;

    protected P priority;
    protected V value;


    public SkewElement(P priority, V value) {
        this.priority = priority;
        this.value = value;
    }


    public SkewElement(P priority, V value, SkewHeap<P,V> queue) {
        this.priority = priority;
        this.value = value;
        this.queue=queue;
    }

    @Override
    public P getPriority() {
        return priority;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void decreaseKey(P newPriority) {
        if(queue==null){
            throw new IllegalStateException("This element is no longer in a queue");
        }
        if (newPriority.compareTo(priority) > 0) {
            throw new IllegalArgumentException("New priority is larger than current priority");
        }
        this.priority = newPriority;

        if(this.equals(queue.getRoot())){
            SkewElement<P,V> leftChild = this.getLeftChild();
            this.setLeftChild(null);
            this.swapChildren();
            queue.setRoot(queue.merge(leftChild,this));
        } else {
            SkewElement<P,V> current = this;
            while(current.equals(queue.getRoot())){
                if(current.getParent().getRightChild()!=null && current.equals(current.getParent().getLeftChild())){
                    current.getParent().swapChildren();
                }
                current=current.getParent();
            }

            if(this.equals(this.getParent().getLeftChild())){
                this.getParent().setLeftChild(null);
            } else {
                this.getParent().setRightChild(null);
            }
            this.parent=null;
            queue.setRoot(queue.merge(this, queue.getRoot()));
        }
    }

    public void setQueue(SkewHeap<P,V> newQueue){
        this.queue=newQueue;
    }


    public SkewElement<P,V> getLeftChild() {
        return leftChild;
    }

    public SkewElement<P,V> getRightChild() {
        return rightChild;
    }

    public SkewElement<P,V> getParent (){ return parent; }

    public void setParent(SkewElement<P,V> newParent){
        this.parent=newParent;
    }

    public void setLeftChild(SkewElement<P,V> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(SkewElement<P,V> rightChild) {
        this.rightChild = rightChild;
    }


    public void swapChildren(){
        SkewElement<P,V> temp = leftChild;
        leftChild = rightChild;
        rightChild = temp;
    }
}
