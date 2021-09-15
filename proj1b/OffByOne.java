public class OffByOne implements CharacterComparator {

    /* to calculate the difference between two chars, simply compute their difference in Java. */
    @Override
    public boolean equalChars(char x, char y) {

        /* we can't do 'x' - 'y' == 1, because it is taking the char X and Y instead of what X and Y are inputted as. */
        if (x - y == 1 || x - y == -1) {
            return true;
        }
        return false;
    }
}
