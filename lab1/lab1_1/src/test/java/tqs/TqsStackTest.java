package tqs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TqsStackTest {

    private TqsStack<Integer> stack;

    @BeforeEach
    void setup() {
        stack = new TqsStack<>();
    }

    @DisplayName("Stack should be empty on construction")
    @Test
    public void testStackIsEmptyOnConstruction() {
        assertTrue(stack.isEmpty(), "Stack should be empty on construction");
    }

    @Test
    public void testStackHasSizeZeroOnConstruction() {
        assertEquals(0, stack.size(), "Stack should have size 0 on construction");
    }

    @Test
    public void testPushToEmptyStack() {
        stack.push(1);
        assertFalse(stack.isEmpty(), "Stack should not be empty after push");
        assertEquals(1, stack.size(), "Stack size should be 1 after one push");
    }

    @Test
    public void testPushThenPop() {
        stack.push(1);
        assertEquals(1, stack.pop(), "Popped value should be the same as pushed value");
    }

    @Test
    public void testPushThenPeek() {
        stack.push(1);
        assertEquals(1, stack.peek(), "Peeked value should be the same as pushed value");
        assertEquals(1, stack.size(), "Stack size should remain the same after peek");
    }

    @Test
    public void testPopUntilEmpty() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
        assertEquals(0, stack.size(), "Stack size should be 0 after popping all elements");
    }

    @Test
    public void testPopFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.pop(), "Popping from empty stack should throw NoSuchElementException");
    }

    @Test
    public void testPeekIntoEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.peek(), "Peeking into empty stack should throw NoSuchElementException");
    }

    @Test
    public void testPushToFullStack() {
        TqsStack<Integer> boundedStack = new TqsStack<>(1);
        boundedStack.push(1);
        assertThrows(IllegalStateException.class, () -> boundedStack.push(2), "Pushing onto a full stack should throw IllegalStateException");
    }

    @Test
    public void testPopTopN() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.popTopN(1), "Top element should be 3");
        assertEquals(2, stack.popTopN(1), "Top element should be 2");
        assertEquals(1, stack.popTopN(1), "Top element should be 1");
    }
}