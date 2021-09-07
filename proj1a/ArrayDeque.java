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

        head = 0;
        tail = 1;
    }

/* For an array, length and size are both non-zero indexed. Did "items.length()" but this is incorrect even though .length is an array method, but technically arrays don't have methods, so we don't need ().

 if usage ratio is less than 25%, then the suggested capacity is halved. */


/* since we're just resizing inside this class and nowhere else, decided to make the class private static for memory purposes. However, private static methods cannot access non-static items/items from the outer private static method,
    so I can only convert it to a private non-static method. Also, removed "int capacity" to simply capacity = (length of array)*2. */

/*    private void resize(int startsource, int startdestination) {
        float usageratio = (float) size / items.length;
        int capacity = (int) (items.length*2);
        if (usageratio < 0.25) {
            capacity = items.length/4;
            System.out.println("Usage ratio is below 25%, so the multiplier effect is using a fraction and not an integer.");
        }
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, startsource, temp, startdestination, capacity);
        items = temp;
    }*/
    public void resize(int length) {
        T[] temp = (T[]) new Object[length];
        if (tail > head) {
            System.arraycopy(items, head+1, temp, 0, tail-head-1);
        }
/*        else if (head == tail && size != items.length) {
            System.arraycopy(items, head+1, temp, 0, items.length-head-1);
            System.arraycopy(items, 0, temp, items.length-head-1, tail);
        }*/
        else if (head == tail) {
            System.arraycopy(items, 0, temp, 0, items.length);
        }
        else { /* this is equivalent to the above head==tail but the size != items.length. This allows us to copy two parts of the array. */
            System.arraycopy(items, head+1, temp, 0, items.length-head-1);
            System.arraycopy(items, 0, temp, items.length-head-1, tail);
        }
        head = length-1;
        tail = size;
        items = temp;
}
/*    public void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, 0, temp, 1, size);
        head = 0;
        tail = size + 1;
        items = temp;
    }*/

    private int minusOne(int index) {
        /* an array is 0 indexed based, while the length of an array is not, so if we want to reach into an array's index using an array length we need to do length - 1. */
        if (index - 1 < 0){
            return index = items.length - 1;
        }
            return index = index - 1;
    }

    private int plusOne(int index) {
        if (index + 1 > items.length - 1) {
            return index = 0;
        }
            return index = index + 1;
    }

    public void addFirst(T item) {
        /* adding "head = plusOne(head)" before the resize, because the way we add is to increment size and head, so it's actually impossible for "head == tail && items.length." That is, if head==tail, that means
        items.length and size are one off from each other, and when items.length == size, then head and tail are one off from each other. If we don't increment before the resize, then we'll get the wrong resize condition.
         */
        if (items.length == size) {
            head = plusOne(head);
            resize(items.length*2);
        }
        items[head] = item;
        size ++;
        head = minusOne(head);
    }

    public void addLast(T item) {
        if(size == items.length) {
            tail = minusOne(tail);
            resize(items.length*2);
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
        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length/2);
        }
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

        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length/2);
        }
        return temp;
    }

    public T get(int index) {
/*        if (index <= items.length - 1 && index >= 0) {
            if (index == 0)
                return items[index + 1];
            if (index == 1) {
                return items[0];
            }
            else {
                return items[index]
            }
            return items[minusOne(index)];
        }*/
/*        if (index > head || index < tail) {
            return items[index];
        }*/
        if (index <= items.length - 1 && index >= 0) {
            return items[index];
        }
        return null;
    }

}
