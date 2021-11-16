package markup;

public class MutableRange {
    public int from;
    public int to;

    public MutableRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public MutableRange copy() {
        return new MutableRange(from, to);
    }
}
