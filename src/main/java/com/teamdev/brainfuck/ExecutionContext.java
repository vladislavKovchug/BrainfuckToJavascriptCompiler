package com.teamdev.brainfuck;

public class ExecutionContext {

    public static final int MEMORY_SIZE = 30000;

    private final byte[] memory = new byte[MEMORY_SIZE];
    private int pointer;

    public void movePointer(int num){
        pointer += num;
        if(pointer >= MEMORY_SIZE || pointer < 0){
            throw new IllegalStateException("Pointer is out of memory: " + pointer);
        }
    }

    public void addToCurrentValue(int num){
        memory[pointer] += num;
    }


    public byte getCurrentValue() {
        return memory[pointer];
    }
}
