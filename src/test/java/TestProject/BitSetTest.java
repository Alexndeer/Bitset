package TestProject;

import Project.BitSet;

import static org.junit.Assert.*;

class BitSetTest {

    BitSet<String> bitSetS;
    BitSet<Integer> bitSetI;

    @org.junit.jupiter.api.Test
    void add() {
        bitSetS = new BitSet<String>(5);
        bitSetS.add("a", "b", "abc", "777");
        bitSetS.add("1000");
        assertEquals(5, bitSetS.elementCount());
        assertEquals("a", bitSetS.get(0));
        assertTrue(bitSetS.contains("1000"));
        assertArrayEquals(new Object[]{"a", "b", "abc", "777", "1000"}, bitSetS.getBitSet());
        bitSetS = new BitSet(10);
        bitSetS.add("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        assertArrayEquals(new Object[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}, bitSetS.getBitSet());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        bitSetS = new BitSet(10);
        bitSetS.add("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        bitSetS.remove("1", "3", "5");
        assertArrayEquals(new Object[]{"0", null, "2", null, "4", null, "6", "7", "8", "9"}, bitSetS.getBitSet());
    }

    @org.junit.jupiter.api.Test
    void intersect() {
        BitSet<Integer> bitSetI1 = new BitSet<>(5);
        bitSetS = new BitSet<>(5);
        bitSetS.add("1", "2", "3", "4", "5");
        bitSetI = new BitSet<>(5);
        bitSetI.add(1, 2, 3, 4, 5);
        bitSetI1.add(1, 2, 3);
        assertArrayEquals(new Object[]{1, 2, 3, null, null}, bitSetI.intersect(bitSetI1).getBitSet());
    }

    @org.junit.jupiter.api.Test
    void union() {
        BitSet<Integer> bitSetI1 = new BitSet<>(5);
        bitSetS = new BitSet<>(5);
        bitSetS.add("1", "2", "3", "4", "5");
        bitSetI = new BitSet<>(5);
        bitSetI.add(1, 2, 3, 4, 5);
        bitSetI1.add(1, 2, 3);
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 1, 2, 3, null, null}, bitSetI.union(bitSetI1).getBitSet());
    }

    @org.junit.jupiter.api.Test
    void addition() {
        BitSet<String> bitSetS1 = new BitSet<>(5);
        bitSetS1.add("1", "12", "3", "4", "5");
        bitSetS = new BitSet<>(5);
        bitSetS.add("1", "2", "3", "14", "5");
        assertArrayEquals(new Object[]{null, "2", null, "14", null, null, "12", null, "4", null}, bitSetS.addition(bitSetS1).getBitSet());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        bitSetS = new BitSet<>(5);
        bitSetS.add("a", "b", "c", "d", "e");
        assertTrue(bitSetS.contains("a"));
        assertFalse(bitSetS.contains("word"));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        bitSetS = new BitSet<>(5);
        bitSetS.add("Actually,","it","is","a","string");
        String s = "Project.BitSet{size=5, bitSet=[Actually,, it, is, a, string]}";
        assertEquals(s,bitSetS.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        BitSet<String> bitSetS1 = new BitSet<>(5);
        BitSet<String> bitSetS2 = new BitSet<>(5);
        bitSetS = new BitSet<>(5);
        bitSetS.add("hash", "Code");
        bitSetS1.add("hash", "Code");
        bitSetS2.add("Code", "hash");

        assertTrue(bitSetS.equals(bitSetS1));
        assertFalse(bitSetS1.equals(bitSetS2));
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        BitSet<String> bitSetS1 = new BitSet<>(5);
        BitSet<String> bitSetS2 = new BitSet<>(5);
        bitSetS = new BitSet<>(5);
        bitSetS.add("hash", "Code");
        bitSetS1.add("hash", "Code");
        bitSetS2.add("Code", "hash");

        assertEquals(bitSetS.hashCode(), bitSetS1.hashCode());
        assertNotEquals(bitSetS1.hashCode(), bitSetS2.hashCode());
    }
}