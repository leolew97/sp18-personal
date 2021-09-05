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

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addRemove();

    }
}
