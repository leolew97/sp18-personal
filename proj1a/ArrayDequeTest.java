public class ArrayDequeTest {

    public static void addRemove() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addFirst("first");
        test.addFirst("middle");
        test.addFirst("last");
        test.removeFirst();
        test.addFirst("first");
        test.removeLast();
        test.addLast("last");
        System.out.println("passed");
    }

    public static void getTest() {

        System.out.println("Running getTest.");

        /*		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");*/
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);
        int v = lld1.get(5);
        int j = lld1.get(0);
        org.junit.Assert.assertEquals(2, v);
        org.junit.Assert.assertEquals(7, j);
    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
/*        addRemove();*/
        getTest();

    }
}
