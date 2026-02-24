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
        this.minMaxValues.remove(this.lastMinMaxPair());
        return stack.pop();
    }

    @Override
    public int peek() {
        return stack.peek();
    }

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    private MinMax lastMinMaxPair() {
        return this.minMaxValues.get(this.minMaxValues.size()-1);
    }
}
