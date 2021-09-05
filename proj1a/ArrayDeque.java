public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int head;
    private int tail;

/* casting one of the variables into a float to get float division. */

/*    private float usageratio = (float) size/items.length;*/

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;

/* didn't do head = 0 and tail = 1 to illustrate how this process will work for add/remove methods. */

        head = 1;
        tail = 2;
    }

/* For an array, length and size are both non-zero indexed. Did "items.length()" but this is incorrect even though .length is an array method, but technically arrays don't have methods, so we don't need ().

 if usage ratio is less than 25%, then the suggested capacity is halved. */


/* since we're just resizing inside this class and nowhere else, decided to make the class private static for memory purposes. However, private static methods cannot access non-static items/items from the outer private static method,
    so I can only convert it to a private non-static method. Also, removed "int capacity" to simply capacity = (length of array)*2. */

    private void resize(int startsource, int startdestination) {
        float usageratio = (float) size / items.length;
        int capacity = (int) (items.length*2);
        if (usageratio < 0.25) {
            capacity = capacity/2;
            System.out.println("Usage ratio is below 25%, so the multiplier effect has been halved.");
        }
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, startsource, temp, startdestination, capacity);
        items = temp;
    }

    private int minusOne(int index) {
        /* an array is 0 indexed based, while the length of an array is not, so if we want to reach into an array's index using an array length we need to do length - 1. */
        if (index - 1 < 0){
            return index = items.length - 1;
        }
            return index = (index - 1) % (items.length - 1);
    }

    private int plusOne(int index) {
        if (index + 1 > items.length - 1) {
            index = 0;
        }
            return index = (index + 1) % (items.length - 1);
    }

    public void addFirst(T item) {
        if (items.length == size) {
            resize(0, 0);
        }
        items[head] = item;
        head = minusOne(head);
        size ++;
    }

    public void addLast(T item) {
        if(size == items.length) {
            resize(0, 0);
        }
        items[tail] = item;
        tail = plusOne(tail);
        size ++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /* fix */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Null");
        }
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
            i++;
        }
    }


    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        head = plusOne(head);
        T temp = items[head];
        items[head] = null;
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        tail = minusOne(tail);
        T temp = items[tail];
        items[tail] = null;
        size -= 1;
        return temp;
    }

    public T get(int index) {
        return items[index];
    }

}
