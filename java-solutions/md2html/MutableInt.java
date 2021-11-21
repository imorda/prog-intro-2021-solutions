package md2html;

public class MutableInt {
    private int val;

    public MutableInt(int val) {
        this.val = val;
    }

    public MutableInt copy() {
        return new MutableInt(get());
    }

    public int get() {
        return val;
    }

    public void set(int val) {
        this.val = val;
    }
}
