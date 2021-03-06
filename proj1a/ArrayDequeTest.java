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

    public static void addFirst() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addFirst("first");
        test.addFirst("middle");
        test.addFirst("last");
        test.addFirst("first");
        test.addFirst("middle");
        test.addFirst("last");
        test.removeFirst();
        test.addFirst("first");
        test.addFirst("middle");
        test.addFirst("last");

        /* becomes full */
        test.addFirst("first");
        test.addFirst("middle");
        test.addFirst("last");
        String j = test.get(5);
    }

    public static void addLast() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addLast("first");
        test.addLast("middle");
        test.addLast("last");
        test.addLast("first");
        String q = test.get(0);

        test.addFirst("middle");
        test.addLast("last");
        test.removeFirst();
        test.addLast("first");
        test.addLast("middle");

        String j = test.get(0);
        String k = test.get(7);
        String n = test.get(3);
        test.addLast("last");

        test.addLast("first");
        String a = test.get(0);
        String b = test.get(7);
        String c = test.get(3);
        /* becomes full */
        test.addLast("middle");
        test.addLast("last");
    }

    public static void getTest() {

        System.out.println("Running getTest.");

        /*		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");*/
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);
        lld1.addFirst(8);
        int b = lld1.get(9);

        lld1.addFirst(10);
        int j = lld1.removeLast();


        int k = lld1.get(0);
        int v = lld1.get(3);
        int a= lld1.get(8);

    }

    public static void getTest2() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(0);
        int j = lld1.get(0);
    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
/*        addRemove();*/
        getTest();
/*        getTest2();*/
/*        addFirst();*/
/*        addLast();*/
    }
}
