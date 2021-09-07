/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
/*		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");*/
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** created own custom test to test removefirst, and removelast. Use visualizer to see. */
	public static void removeTest() {
		System.out.println("Running removeandget test.");
		/*		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");*/
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;
		lld1.removeFirst();
		lld1.removeLast();
		lld1.addLast("hehe");
		lld1.addFirst("xD");
		lld1.addFirst("front");
		lld1.addLast("middle");
		String j = lld1.get(3);
		String k = lld1.getRecursive(3);
		String n = lld1.get(20);
		String l = lld1.get(5);
		String o = lld1.get(6);
		String a = lld1.getRecursive(5);
		String b = lld1.getRecursive(6);
		String c = lld1.getRecursive(4);
		String d = lld1.getRecursive(3);
		String e = lld1.getRecursive(2);
		String f = lld1.getRecursive(0);
		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

/*		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");*/
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void getTest() {

		System.out.println("Running getTest.");

		/*		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");*/
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

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

	public static void getRecursiveTest(){
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>();
		lld1.addFirst(1);
		lld1.addFirst(2);
		lld1.addFirst(3);
		lld1.addFirst(4);
		lld1.addFirst(5);
		lld1.addFirst(6);
		lld1.addFirst(7);
		lld1.addFirst(1);
		lld1.addFirst(2);
		lld1.addFirst(3);
		lld1.addFirst(4);
		lld1.addFirst(5);
		lld1.addFirst(6);
		lld1.addFirst(7);
		lld1.addFirst(1);
		lld1.addFirst(2);
		lld1.addFirst(3);
		lld1.addFirst(4);
		lld1.addFirst(5);
		lld1.addFirst(6);
		lld1.addFirst(7);
		int v = lld1.getRecursive(6);
		int j = lld1.getRecursive(11);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
/*		addIsEmptySizeTest();
		addRemoveTest();*/
		removeTest();
/*		getTest();*/
/*		getRecursiveTest();*/
	}
} 