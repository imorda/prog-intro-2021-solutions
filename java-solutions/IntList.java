import java.util.Arrays;

public class IntList {
    private int[] buffer;
    private int size = 0;

    public IntList() {
        buffer = new int[10];
    }

    public IntList(int capacity) {
        buffer = new int[capacity];
        size = capacity;
    }

    public int get(int pos) throws IndexOutOfBoundsException {
        if (pos >= size) {
            throw new IndexOutOfBoundsException("Array index is out of bounds");
        }
        return buffer[pos];
    }

    public void set(int pos, int val) throws IndexOutOfBoundsException {
        if (pos >= size) {
            throw new IndexOutOfBoundsException("Array index is out of bounds");
        }
        buffer[pos] = val;
    }

    public void reserve(int capacity) throws OutOfMemoryError {
        if (capacity > buffer.length) {
            buffer = Arrays.copyOf(buffer, capacity);
        }
    }

    public void add(int value) throws OutOfMemoryError {
        if (size >= buffer.length) {
            reserve(buffer.length * 2 + 1);
        }
        size++;
        set(size - 1, value);
    }

    public int length() {
        return size;
    }
}