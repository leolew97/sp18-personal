public class ArrayDeque<Bloop> {

    private Bloop[] items;
    private int size;
    private int head;
    private int tail;

    /* casting one of the variables into a float to get float division. */
    private float usageratio = (float) size/items.length;

    public ArrayDeque() {
        items = (Bloop[]) new Object[8];
        size = 0;

        /* didn't do head = 0 and tail = 1 to illustrate how this process will work for add/remove methods. */
        head = 1;
        tail = 2;
    }

    /* For an array, length and size are both non-zero indexed. Did "items.length()" but this is incorrect even though .length is an array method, but technically arrays don't have methods, so we don't need (). */
    /* if usage ratio is less than 25%, then the suggested capacity is halved. */

    /* since we're just resizing inside this class and nowhere else, decided to make the class private static for memory purposes. However, private static methods cannot access non-static items/items from the outer private static method,
    so I can only convert it to a private non-static method. Also, removed "int capacity" to simply capacity = (length of array)^2. */
    private void resize(int startsource, int startdestination) {
        int capacity = items.length*items.length;
        if (usageratio < 0.25) {
            capacity = capacity/2;
            System.out.println("Usage ratio is below 25%, so the multiplier effect has been halved.");
        }
        Bloop[] temp = (Bloop[]) new Object[capacity];
        System.arraycopy(items, startsource, temp, startdestination, capacity);
        items = temp;
    }

    public void addFirst(Bloop item) {
        if (items.length == size) {
            resize(0, 0);
        }
        items[head] = item;
        head = head % items.length;
        size += 1;
    }

    public void addLast(Bloop item) {
        if(size == items.length) {
            resize(0, 0);
        }
        items[tail] = item;
        tail = tail % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (items[head] == null) {
            System.out.println("Null");
        }
        while (head != tail) {
            System.out.println(items[head]);
            head += 1;
        }
    }

    public Bloop removeFirst() {
        if (items[head] == null) {
            return null;
        }
        Bloop temp = items[head];
        items[head] = null;
        size -= 1;
        return temp;
    }

    public Bloop removeLast() {
        if (items[tail] == null) {
            return null;
        }
        Bloop temp = items[tail];
        items[tail] = null;
        size -= 1;
        return temp;
    }

    public Bloop get(int index) {
        return items[index];
    }

}
