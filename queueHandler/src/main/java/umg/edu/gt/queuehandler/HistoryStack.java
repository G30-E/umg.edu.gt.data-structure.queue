package umg.edu.gt.queuehandler;
public class HistoryStack {

    private static final class Node {
        final Song value;
        Node next;
        Node(Song value) { this.value = value; }
    }

    private Node top;
    private int size;

    public void push(Song song) {
        Node n = new Node(song);
        n.next = top;
        top = n;
        size++;
    }

    public Song pop() {
        if (isEmpty()) return null;
        Song v = top.value;
        top = top.next;
        size--;
        return v;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public String peekLastPlayed() {
        return top == null ? "(none)" : top.value.toString();
    }
}
