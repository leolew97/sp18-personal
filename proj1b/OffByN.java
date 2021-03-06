public class OffByN implements CharacterComparator {

    private int value;

    public OffByN(int N) {
        value = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == value || x - y == -value) {
            return true;
        }
        return false;
    }
}
