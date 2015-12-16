package misc;

/**
 *
 * @author 6523617
 * @param <A>
 * @param <B>
 */
public class Pair<A, B> {

    private final A first;
    private final B last;

    public Pair(A first, B last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", last=" + last + '}';
    }

    public A getFirst() {
        return first;
    }

    public B getLast() {
        return last;
    }
}
