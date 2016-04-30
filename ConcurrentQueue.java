import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Syril on 30-04-2016.
 */
public class ConcurrentQueue<E> {

    private volatile Node<E> first, last;

    private class Node<E> {
        volatile E item;
        volatile Node<E> next;
    }

    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enQueue(E data) {
        Lock lock = new ReentrantLock();
        checkNotNull(data);
        Node oldLast = last;
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                last = new Node();

                last.item = data;
                last.next = null;
                if (isEmpty()) {
                    first = last;
                } else
                    oldLast.next = last;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is Empty");
        }

        E item = null;

        Lock lock = new ReentrantLock();
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Node<E> oldFirst = first;
                item = first.item;
                first = first.next;
                oldFirst = null;
                if (isEmpty())
                    last = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return item;
    }

    public E peek() {
        if (first == null) {
            throw new NoSuchElementException("Queue is Empty");
        } else
            return first.item;
    }

    public Iterator<E> iterator() {
        return new ListIterator<E>(first);
    }

    private class ListIterator<E> implements Iterator {

        Node<E> current;

        public ListIterator(Node<E> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


}
