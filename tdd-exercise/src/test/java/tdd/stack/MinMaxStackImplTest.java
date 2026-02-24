package tdd.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final int FIRST_ELEMENT = 1;
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
    public void testPeek() {
        this.stack.push(FIRST_ELEMENT);
        assertEquals(FIRST_ELEMENT, this.stack.peek());
    }
}