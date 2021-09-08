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

/*    private void resize(int length) {
        T[] temp = (T[]) new Object[length];
        if (tail > head) {
            System.arraycopy(items, head+1, temp, 0, tail-head-1);
        }
*//*        else if (head == tail && size != items.length) {
            System.arraycopy(items, head+1, temp, 0, items.length-head-1);
            System.arraycopy(items, 0, temp, items.length-head-1, tail);*//*
     *//*        }*//*
        else if (head == tail) {
            System.arraycopy(items, 0, temp, 0, items.length);
        }
        else {  *//*this is equivalent to the above head==tail but the size != items.length. This allows us to copy two parts of the array.*//*
            System.arraycopy(items, head+1, temp, 0, items.length-head-1);
            System.arraycopy(items, 0, temp, items.length-head-1, tail);
        }
        head = length-1;
        tail = size;
        items = temp;
}*/


/*    private void resize(int length) {
        T[] temp = (T[]) new Object[length];
        if (tail > head) {
            for (int i = 0, j = 1; i < tail-head-1 < i++, j++) {
                temp[i] = items[j];
            }
        }
    }*/

    private void resize(int capacity) {

        T[] newItems = (T[]) new Object[capacity];
        //  0  1  2  3  4  5  6  7
        //       [x][x][x][x]       items (copy array)
        // [ ][ ][ ][ ][ ][ ][ ][ ] newItems
        //    [x][x][x]   if initSize is an odd number
        // [ ][ ][ ][ ][ ][ ]
        int startPos = newItems.length / 2 - size / 2; /* draw a picture */
        /** Copy */
  /* // stupid way
  int oldIndex = (nextFirst + 1 > size - 1) ? (0) : (nextFirst + 1);
  // if nextF points at the last position, it means we need to start from 0
  */
        int oldIndex = head + 1; /* Use MOD operation instead */
        int newIndex = startPos; /* newIndex for newItems */
        int count = 0;
        while (count < size) { /* yes! the original size! */
            newItems[newIndex] = items[oldIndex % items.length]; /* 3 + 1 = 4, 4 % 4 = 0 */
            oldIndex++;
            newIndex++;
            count++;
        }
        /** Set new fields */
        items = newItems;
        // size = capacity; bug! we don't need to change size!
        head = startPos - 1;
        tail = newIndex; /* or nextFirst + size */
    }


    private int minusOne(int index) {
        /* an array is 0 indexed based, while the length of an array is not, so if we want to reach into an array's index using an array length we need to do length - 1. */
        if (index - 1 < 0) {
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
            /*          head = plusOne(head);*/
            resize(items.length * 2);
        }
        items[head] = item;
        size++;
        /* instead of the minusOne helper function, we could also do head = ((head - 1) + items.length) % items.length. We do not need to ever make a full round trip without resizing, so the case of head = 0 means we
        get head = ((0 - 1) + 8) / 8 = 7 / 8 = index 7. We need to do " + items.length " because in Java "-5 % 6" = -5, so we need to add the items.length to wrap around the end of the list.
         */
        head = minusOne(head);
    }

    public void addLast(T item) {
        if (size == items.length) {
            /*            tail = minusOne(tail);*/
            resize(items.length * 2);
        }
        items[tail] = item;
        /* instead of the plusOne helper function, we could also do tail = (tail + 1) % items.length. We need to don't need to add the items.length, because we aren't subtracting any values from tail, so it has no
        possibility of becoming negative. Also, remember that 6%6 = 0, because there are no remainders if two integers are divisible.
         */
        tail = plusOne(tail);
        size++;
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
        if (isEmpty()) {
            return null;
        }
        head = plusOne(head);
        T temp = items[head];
        items[head] = null;
        size -= 1;

        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length / 2);
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
            resize(items.length / 2);
        }
        return temp;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        /*                 +1 because head is pointing at a null box. */
        int Tindex = head + 1;
        while (index != 0) {
            Tindex = plusOne(Tindex);
            index--;
        }
        return items[Tindex];
        /*    public T get(int index) {
         *//* "index >= size" is a better way to say "items.length - 1" because arrays are zero indexed, so if we're given an index equal to the size, then we know the index is already +1 out of bounds. *//*
        if (index < 0 || index >= size) {
            return null;
        }

        *//* +1 because head is pointing at a null box. *//*
        int oldIndex = head + 1;
        while (index > 0) {
            oldIndex++;
            index--;
        }
        return items[oldIndex % items.length];
    }*/


    }
}