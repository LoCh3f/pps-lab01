package tdd.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    private final Stack<Integer> stack;
    private record MinMax(int min, int max) {}
    private final List<MinMax> minMaxValues;

    public MinMaxStackImpl() {
        this.stack = new Stack<>();
        this.minMaxValues = new LinkedList<>();
    }
    @Override
    public void push(final int value) {
        if(this.minMaxValues.isEmpty()){
            this.minMaxValues.add(new MinMax(value,value));
        } else if (lastMinMaxPair().min > value) {
            this.minMaxValues.add(new MinMax(value, lastMinMaxPair().max));
        } else if (lastMinMaxPair().max < value ) {
            this.minMaxValues.add(new MinMax(lastMinMaxPair().min,value));
        } else {
            this.minMaxValues.add(lastMinMaxPair());
        }
        stack.push(value);


    }

    @Override
    public int pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException();
        }
        this.minMaxValues.remove(this.lastMinMaxPair());
        return stack.pop();
    }

    @Override
    public int peek() {
        if (this.minMaxValues.isEmpty()){
            throw new IllegalStateException();
        }
        return stack.peek();
    }

    @Override
    public int getMin() {
        if (this.minMaxValues.isEmpty()){
            throw new IllegalStateException();
        }
        return this.lastMinMaxPair().min;
    }

    @Override
    public int getMax() {
        if (this.minMaxValues.isEmpty()){
            throw new IllegalStateException();
        }
        return this.lastMinMaxPair().max;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    private MinMax lastMinMaxPair() {
        return this.minMaxValues.get(this.minMaxValues.size()-1);
    }
}
