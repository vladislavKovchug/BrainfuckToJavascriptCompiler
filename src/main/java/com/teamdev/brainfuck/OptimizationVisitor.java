package com.teamdev.brainfuck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class OptimizationVisitor implements CommandVisitor {
    private final Deque<List<Command>> optimizedCommandsStack = new ArrayDeque<>();

    private int optimizingCommandsCount;
    private Command lastCommand;
    private OptimizableCommand currentOptimizingCommand;


    public OptimizationVisitor() {
        optimizingCommandsCount = 0;
        currentOptimizingCommand = null;
        lastCommand = null;
        optimizedCommandsStack.push(new ArrayList<Command>());
    }

    private void pushOptimizedCommand() {
        if (optimizingCommandsCount > 1) {
            optimizedCommandsStack.peek().add(currentOptimizingCommand);
        } else if (optimizingCommandsCount == 1) {
            optimizedCommandsStack.peek().add(lastCommand);
        }

        optimizingCommandsCount = 0;
        currentOptimizingCommand = null;
        lastCommand = null;
    }

    public List<Command> getOptimizedCommands() {
        return optimizedCommandsStack.pop();
    }

    @Override
    public void visit(MoveForwardCommand command) {
        if(currentOptimizingCommand == null || !currentOptimizingCommand.compatible(command)){
            pushOptimizedCommand();
            currentOptimizingCommand = new MoveCommand();
        }
        lastCommand = command;
        currentOptimizingCommand.changeValue(+1);
        optimizingCommandsCount++;
    }

    @Override
    public void visit(MoveBackwardCommand command) {
        if(currentOptimizingCommand == null || !currentOptimizingCommand.compatible(command)){
            pushOptimizedCommand();
            currentOptimizingCommand = new MoveCommand();
        }
        lastCommand = command;
        currentOptimizingCommand.changeValue(-1);
        optimizingCommandsCount++;
    }

    @Override
    public void visit(MoveCommand command) {
        if(currentOptimizingCommand == null || !currentOptimizingCommand.compatible(command)){
            pushOptimizedCommand();
            currentOptimizingCommand = new MoveCommand();
        }
        lastCommand = command;
        currentOptimizingCommand.changeValue(command.getMoveNum());
        optimizingCommandsCount++;
    }

    @Override
    public void visit(IncrementCommand command) {
        if(currentOptimizingCommand == null || !currentOptimizingCommand.compatible(command)){
            pushOptimizedCommand();
            currentOptimizingCommand = new AddCommand();
        }
        lastCommand = command;
        currentOptimizingCommand.changeValue(+1);
        optimizingCommandsCount++;
    }

    @Override
    public void visit(DecrementCommand command) {
        if(currentOptimizingCommand == null || !currentOptimizingCommand.compatible(command)){
            pushOptimizedCommand();
            currentOptimizingCommand = new AddCommand();
        }
        lastCommand = command;
        currentOptimizingCommand.changeValue(-1);
        optimizingCommandsCount++;
    }

    @Override
    public void visit(AddCommand command) {
        if(currentOptimizingCommand == null || !currentOptimizingCommand.compatible(command)){
            pushOptimizedCommand();
            currentOptimizingCommand = new AddCommand();
        }
        lastCommand = command;
        currentOptimizingCommand.changeValue(command.getAddNum());
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
}
