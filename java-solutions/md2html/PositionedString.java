package md2html;

public class PositionedString {
    private final String data;
    private int pos;

    public PositionedString(String data) {
        this.data = data;
        pos = 0;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void incPos(int delta) {
        this.pos += delta;
    }

    public void incPos() {
        incPos(1);
    }

    public String getData() {
        return data;
    }

    public boolean isValidOpenTag(String tag) {
        return pos + tag.length() < data.length()
                && ("\n\r ").indexOf(data.charAt(pos + tag.length())) == -1
                && !(pos > 0 && data.charAt(pos - 1) == '\\')
                && data.startsWith(tag, pos);
    }

    public boolean isValidCloseTag(String tag) {
        return pos > 0 && pos < data.length() && data.startsWith(tag, pos) &&
                ("\n\r ").indexOf(data.charAt(pos - 1)) == -1 &&
                data.charAt(pos - 1) != '\\';
    }

    public boolean isTag(String tag) {
        return !isExhausted() && data.startsWith(tag, pos) &&
                !(pos > 0 && data.charAt(pos - 1) == '\\');
    }

    public boolean startsWith(String... substr) {
        for (final String i : substr) {
            if (data.startsWith(i, pos)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExhausted() {
        return pos >= data.length();
    }

    public char getChar() {
        return data.charAt(pos);
    }
}
