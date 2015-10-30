package com.teamdev.brainfuck;

import java.util.ArrayList;
import java.util.List;

public class OptimizationVisitor implements CommandVisitor {

    private final List<Command> optimizedCommands = new ArrayList<>();

    public List<Command> getOptimizedCommands() {
        return optimizedCommands;
    }

    @Override
    public void visit(MoveForwardCommand command) {
        optimizedCommands.add(command);
    }

    @Override
    public void visit(MoveBackwardCommand command) {
        optimizedCommands.add(command);
    }

    @Override
    public void visit(IncrementCommand command) {
        optimizedCommands.add(command);
    }

    @Override
    public void visit(DecrementCommand command) {
        optimizedCommands.add(command);
    }

    @Override
    public void visit(PrintCommand command) {
        optimizedCommands.add(command);
    }

    @Override
    public void visit(LoopCommand command) {
        optimizedCommands.add(command);

        final List<Command> innerCommands = command.getInnerCommands();
        // todo: optimize!
    }
}
