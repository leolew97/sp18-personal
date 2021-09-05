public class LinkedListDeque<T> {

    /** since the class is private, access modifiers are irrelevant, so we can write public or private for the methods within the class and it wouldn't matter, since the overarching class is private, so none of the methods
     * or variables can be accessed since they're automatically considered private. We cannot make the IntList static, because we are passing the generic <T> from the outer class into the nested class. */

    /* Also notice that we don't have to have <> outside of the nested class declaration, but variables inside the nested class can still adopt the generic of the outer class. */
    private class GenList {
        private T first;
        private GenList next;
        private GenList prev;

        private GenList(T i, GenList j, GenList k) {
            first = i;
            next = j;
            prev = k;
        }
    }
    private T hold = (T) "filler";
    private GenList sentinel;
    private int size;

    /** When we instantiate a generic class, we need to use <>. However, when we have a nested generic class and we want to instantiate within an outer generic class that takes the same generic, then we don't need to
     * use <>. See https://github.com/Berkeley-CS61B/lectureCode-sp18/blob/master/lists3/SLList.java */

    public LinkedListDeque() {
        sentinel = new GenList(hold, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        GenList p = new GenList(item, sentinel.next, sentinel);
        sentinel.next = p;
        p.next.prev = p;
        size += 1;
    }

    /** I tried doing "sentinel.prev = new GenList(item, sentinel, sentinel.prev.prev)" but the problem is that sentinel.prev.prev isn't actually established, so it'll raise a nullpointerexception. */
    public void addLast(T item) {
        /* Minor nuances occurring here -- if we want to refer to a whole object, we should point to the whole object. If we want a specific variable in an object to point somewhere, access that variable with dot notation.
         *  We can think of accessing a variable in an object as accessing its "field" then pointing it somewhere. */

        /* create a new variable p to work with this new GenList as it gives more freedom than just working with sentinel methods. Its .next should be sentinel and its .prev should be whatever came before sentinel.prev.*/
        GenList p = new GenList(item, sentinel, sentinel.prev);

        /* set sentinel.prev to equal to the new item. */
        sentinel.prev = p;

        /* set the previous value to point to the new GenList. */

        /* with dot notation, the first dot accesses the instance's own variable, then the second dot accesses the instance the first dot is referring to. So, p.prev accesses its own prev variable "sentinel.next"
        * without actually going to "sentinel.next" then "p.prev.next" goes into sentinel.next and modifies its "next" variable. */
        p.prev.next = p;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        GenList p = sentinel.next;
        if (isEmpty()) {
            System.out.println("Null");
        }

        /* initially i did "p != hold" but we run into an infinite loop this way. This condition is not the same as "p.first != hold" */
        while (p.first != hold) {
            p = p.next;
            System.out.println(p);
        }
    }

    public T removeFirst() {
/*        if (sentinel.next == hold) {
            return null;
        }*/
        if (isEmpty()) {
            return null;
        }
        GenList p = sentinel.next;

        sentinel.next = p.next;
        p.next.prev = sentinel;

        if (size > 0) {
            size -= 1;
        }
        else {
            size = 0;
        }
        return p.first;
    }

    public T removeLast() {
/*        if (sentinel.prev.first == hold) {
            return null;
        }*/
        if (size == 0) {
            return null;
        }

        GenList p = sentinel.prev;

        sentinel.prev = p.prev;
        p.prev.next = sentinel.prev;

        if (size > 0) {
            size -= 1;
        }
        else {
            size = 0;
        }

        return p.first;
    }

    public T get(int index) {
        GenList p = sentinel.next;

/*        if (p.first == hold) {
            return null;
        }*/
        if (isEmpty()) {
            return null;
        }

        while (index != 0) {
            if (p.first == hold) {
                return null;
            }
            p = p.next;
            index --;
        }
        return p.first;
    }

    public T getRecursive(int index) {
        GenList p = sentinel.next;

/*        if (p.first == hold) {
            return null;
        }*/
        if (isEmpty() || p.first ==  hold) {
            return null;
        }
        if (index == 0) {
            return p.first;
        }
        p = p.next;
        index --;
        return getRecursive(index - 1);
    }

}
