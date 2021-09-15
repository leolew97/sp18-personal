

public class Palindrome extends OffByOne {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> holder = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            /* charAt(i) takes the i-th character of the string and returns it. It is a 0th index method. */
            holder.addLast(word.charAt(i));
        }
        return holder;
    }

    /** Palindrome method. */
    public boolean isPalindrome(String word) {
        Deque<Character> actual = wordToDeque(word);
        while (actual.size() > 1) {
            /* "Remember: == and != operators compare memory boxes to see if the comparison comes from the same instance. So even if they're the same but are instantiated separately, it'll
            register as false. == and != operators work well with primitive types (since we're comparing the bits in the memory boxes), but not for reference types since they're comparing
            the addresses and not the actual values. == returns true if two reference objects are pointing to the same address. */

            /* Objects have ".equals" implemented. Interfaces are below Objects in the hierarchy. In short, == and != are address comparisons, while .equals() does content comparison. */

            /* Below works even with generics, because we know the input for this Palindrome is always going to be a string. If it were some unknown generic, this wouldn't work. */
            if (actual.removeFirst() != actual.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /* Note: characters are delineated in Java by single quotes, while strings are delineated by double quotes. */
    /** OffByOne Palindrome method. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> actual = wordToDeque(word);
        while (actual.size() > 1) {
            if (!cc.equalChars(actual.removeFirst(), actual.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
