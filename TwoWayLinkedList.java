import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public TwoWayLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == size) {
            add(e);
            return;
        }
        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } else {
            Node<E> current = getNodeAt(index);
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;
        }
        size++;
    }


    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return getNodeAt(index).element;
    }


    public E set(int index, E e) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> node = getNodeAt(index);
        E oldElement = node.element;
        node.element = e;
        return oldElement;
    }


    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> node = getNodeAt(index);
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }
        size--;
        return node.element;
    }


    public int size() {
        return size;
    }


    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(0);
    }


    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        return new ListIteratorImpl(index);
    }

    private Node<E> getNodeAt(int index) {
        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    private class ListIteratorImpl implements ListIterator<E> {
        private Node<E> current;
        private int index;

        ListIteratorImpl(int index) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();
            this.index = index;
            current = (index == size) ? null : getNodeAt(index);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E element = current.element;
            current = current.next;
            index++;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = (current == null) ? tail : current.previous;
            E element = current.element;
            index--;
            return element;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (current == null) throw new IllegalStateException();
            current.element = e;
        }

        @Override
        public void add(E e) {
            Node<E> newNode = new Node<>(e);
            if (current == null) {
                add(e);
            } else if (index == 0) {
                newNode.next = head;
                head.previous = newNode;
                head = newNode;
            } else {
                newNode.next = current;
                newNode.previous = current.previous;
                if (current.previous != null) {
                    current.previous.next = newNode;
                }
                current.previous = newNode;
                if (index == size) {
                    tail = newNode;
                }
            }
            size++;
            index++;
        }
    }
}
