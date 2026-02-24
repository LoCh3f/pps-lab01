package tdd.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final int FIRST_ELEMENT = 1;
    private static final int EMPTY_SIZE = 0;
    private static final int EXPECTED_SIZE = 2;
    private MinMaxStackImpl minMaxStack;
    private MinMaxStack stack;
    @BeforeEach
    void BeforeEach() {
        this.stack = new MinMaxStackImpl();
    }
    @Test
    public void testPushPop() {
        stack.push(FIRST_ELEMENT);
        assertEquals(FIRST_ELEMENT, this.stack.pop());
    }
    @Test
    public void testPopOnEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }
    @Test
    public void testPeek() {
        this.stack.push(FIRST_ELEMENT);
        assertEquals(FIRST_ELEMENT, this.stack.peek());
    }
    @Test
    public void testPeekOnEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.peek());
    }
    @Test
    public void testMinMax() {
        this.stack.push(FIRST_ELEMENT);
        assertEquals(FIRST_ELEMENT, this.stack.getMin());
        assertEquals(FIRST_ELEMENT, this.stack.getMax());
    }
    @Test
    public void testMinMaxOnEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.getMin());
        assertThrows(IllegalStateException.class, () -> stack.getMax());
    }
    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        this.stack.push(FIRST_ELEMENT);
        assertFalse(this.stack.isEmpty());
    }
    @Test
    public void testStackSize(){
        assertEquals(EMPTY_SIZE, this.stack.size());
        this.stack.push(FIRST_ELEMENT);
        this.stack.push(FIRST_ELEMENT);
        assertEquals(EXPECTED_SIZE, this.stack.size());
    }
}