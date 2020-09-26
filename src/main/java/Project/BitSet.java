package Project;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BitSet<T> {

    private int size;
    public Object[] bitSet;

    public BitSet(int size) {
        this.size = size;
        this.bitSet = new Object[size];
    }

    public int elementCount() {
        int n = 0;
        for (Object o : bitSet) {
            if (o != null) {
                n++;
            }
        }
        return n;
    }

    public int getSize() {
        return size;
    }

    public Object[] getBitSet() {
        return bitSet;
    }

    public T get(int i) {
        return (T) bitSet[i];
    }

    public boolean add(T elem) {
        if (elem == null) {
            return false;
        }
        if (isFull()) {
            expand();
        }
        for (int i = 0; i < size; i++) {
            if (bitSet[i] == null) {
                bitSet[i] = elem;
                break;
            }
        }
        return true;
    }

    public void add(T... elems) {
        for (int i = 0; i < elems.length; i++) {
            add(elems[i]);
        }
    }

    private void removeByIndex(int index) {
        bitSet[index] = null;
    }

    public void remove(T elem) {
        if (isEmpty()) throw new NoSuchElementException();
        for (int i = 0; i < size; i++) {
            if (bitSet[i] == elem) {
                bitSet[i] = null;
            }
        }
    }

    public void remove(T... elems) {
        for (int i = 0; i < elems.length; i++) {
            remove(elems[i]);
        }
    }

    public BitSet<T> intersect(BitSet bitSet2) {
        if (!bitSet.getClass().equals(bitSet2.bitSet.getClass())) throw new IllegalArgumentException();
        int minimal = Math.min(size, bitSet2.size);
        BitSet intersectArr = new BitSet(minimal);
        BitSet clone = new BitSet(bitSet2.size);
        System.arraycopy(bitSet2.bitSet, 0, clone.bitSet, 0, bitSet2.size);
        for (int i = 0; i < minimal; i++) {
            for (int j = 0; j < clone.size; j++) {
                if (bitSet[i].equals(clone.bitSet[j])) {
                    intersectArr.add(bitSet[i]);
                    clone.removeByIndex(j);
                    break;
                }
            }
        }
        return intersectArr;
    }

    public BitSet<T> union(BitSet bitSet2) {
        BitSet unionArr = new BitSet(size + bitSet2.size);
        System.arraycopy(bitSet, 0, unionArr.bitSet, 0, size);
        System.arraycopy(bitSet2.bitSet, 0, unionArr.bitSet, size, bitSet2.size);
        return unionArr;
    }

    public BitSet<T> addition(BitSet bitSet2) {
        BitSet additionArr = this.union(bitSet2);
        additionArr.remove(this.intersect(bitSet2).bitSet);
        return additionArr;
    }

    public boolean contains(T... elem) {
        for (T t : elem) {
            if (!contains(t)) return false;
        }
        return true;
    }

    public boolean contains(T elem) {
        if (elem == null) throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (bitSet[i].equals(elem)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            if (bitSet[i] == null) return false;
        }
        return true;
    }

    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            if (bitSet[i] != null) return false;
        }
        return true;
    }

    public BitSet<T> correct() {
        BitSet result = new BitSet(this.elementCount());
        result.add(this.bitSet);
        return result;
    }

    public void expand() {
        Object[] newSet = new Object[size * 2];
        System.arraycopy(bitSet, 0, newSet, 0, size);
        this.size = newSet.length;
        bitSet = newSet;
    }

    @Override
    public String toString() {
        return "Project.BitSet{size=" + size +
                ", bitSet=" + Arrays.toString(bitSet) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSet<?> bitSet1 = (BitSet<?>) o;
        return Arrays.equals(bitSet, bitSet1.bitSet);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bitSet);
    }
}