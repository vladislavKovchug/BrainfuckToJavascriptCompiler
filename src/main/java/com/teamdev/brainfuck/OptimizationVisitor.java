package com.teamdev.brainfuck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class OptimizationVisitor implements CommandVisitor {
    private static final int COMMAND_NONE = 0;
    private static final int COMMAND_ADD = 1;
    private static final int COMMAND_MOVE = 2;


    //private final List<Command> optimizedCommands = new ArrayList<>();
    private final Deque<List<Command>> optimizedCommandsStack = new ArrayDeque<>();

    private int optimizingCommandsCount;
    private int currentOptimizingCommandType;
    private int currentOptimizingAddValue;
    private Command currentOptimizingCommand;


    public OptimizationVisitor() {
        currentOptimizingCommandType = COMMAND_NONE;
        currentOptimizingAddValue = 0;
        optimizedCommandsStack.push(new ArrayList<Command>());
    }

    private Command makeOptimizedCommand() {
        if (currentOptimizingCommandType == COMMAND_ADD) {
            return new AddCommand(currentOptimizingAddValue);
        } else {
            return new MoveCommand(currentOptimizingAddValue);
        }

    }

    private void pushOptimizedCommand() {
        if (currentOptimizingCommandType != COMMAND_NONE) {
            if (optimizingCommandsCount > 1) {
                optimizedCommandsStack.peek().add(makeOptimizedCommand());
            } else if (optimizingCommandsCount == 1) {
                optimizedCommandsStack.peek().add(currentOptimizingCommand);
            }
        }

        optimizingCommandsCount = 0;
        currentOptimizingCommand = null;
        currentOptimizingCommandType = COMMAND_NONE;
        currentOptimizingAddValue = 0;
    }

    public List<Command> getOptimizedCommands() {
        return optimizedCommandsStack.pop();
    }

    @Override
    public void visit(MoveForwardCommand command) {
        if (currentOptimizingCommandType != COMMAND_MOVE) {
            pushOptimizedCommand();
            currentOptimizingCommandType = COMMAND_MOVE;
            currentOptimizingCommand = command;
        }
        currentOptimizingAddValue++;
        optimizingCommandsCount++;
    }

    @Override
    public void visit(MoveBackwardCommand command) {
        if (currentOptimizingCommandType != COMMAND_MOVE) {
            pushOptimizedCommand();
            currentOptimizingCommandType = COMMAND_MOVE;
            currentOptimizingCommand = command;
        }
        currentOptimizingAddValue--;
        optimizingCommandsCount++;
    }

    @Override
    public void visit(IncrementCommand command) {
        if (currentOptimizingCommandType != COMMAND_ADD) {
            pushOptimizedCommand();
            currentOptimizingCommandType = COMMAND_ADD;
            currentOptimizingCommand = command;
        }
        currentOptimizingAddValue++;
        optimizingCommandsCount++;
    }

    @Override
    public void visit(DecrementCommand command) {
        if (currentOptimizingCommandType != COMMAND_ADD) {
            pushOptimizedCommand();
            currentOptimizingCommandType = COMMAND_ADD;
            currentOptimizingCommand = command;
        }
        currentOptimizingAddValue--;
        optimizingCommandsCount++;
    }

    @Override
    public void visit(PrintCommand command) {
        pushOptimizedCommand();
        optimizedCommandsStack.peek().add(command);
    }

    @Override
    public void visit(LoopCommand command) {
        pushOptimizedCommand();
        optimizedCommandsStack.push(new ArrayList<Command>());

        final List<Command> innerCommands = command.getInnerCommands();
        for (Command innerCommand : command.getInnerCommands()) {
            innerCommand.accept(this);
        }
        pushOptimizedCommand();
        LoopCommand loop = new LoopCommand(optimizedCommandsStack.pop());
        optimizedCommandsStack.peek().add(loop);
    }

    @Override
    public void visit(AddCommand command) {
        pushOptimizedCommand();
        optimizedCommandsStack.peek().add(command);
    }

    @Override
    public void visit(MoveCommand command) {
        pushOptimizedCommand();
        optimizedCommandsStack.peek().add(command);
    }

}
