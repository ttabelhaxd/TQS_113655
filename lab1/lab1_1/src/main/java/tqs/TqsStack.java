package tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private final LinkedList<T> stack = new LinkedList<>();
    private final int maxSize;

    public TqsStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public TqsStack() {
        this.maxSize = Integer.MAX_VALUE;
    }

    public void push(T element) {
        if (stack.size() >= maxSize) {
            throw new IllegalStateException("Stack is full");
        }
        stack.push(element);
    }

    public T pop() {
        return stack.pop();
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.peek();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public T popTopN(int n) {
        T top = null;
        for (int i = 0; i < n; i++) {
            top = stack.removeFirst();
        }
        return top;
    }
}
