package md2html;

public class MutableInt {
    public int val;

    public MutableInt(int val) {
        this.val = val;
    }

    public MutableInt copy() {
        return new MutableInt(val);
    }
}
