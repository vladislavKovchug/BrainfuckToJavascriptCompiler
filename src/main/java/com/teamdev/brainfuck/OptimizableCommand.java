package com.teamdev.brainfuck;

public interface OptimizableCommand extends Command {

    boolean compatible(OptimizableCommand command);
    void changeValue(int value);

}
