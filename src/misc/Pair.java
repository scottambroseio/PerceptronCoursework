/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author Scott
 */
public class Pair<A, B> {

    private A first;
    private B last;

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
