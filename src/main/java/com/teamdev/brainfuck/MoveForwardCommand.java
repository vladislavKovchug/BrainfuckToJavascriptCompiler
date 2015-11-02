package com.teamdev.brainfuck;


public class MoveForwardCommand implements OptimizableCommand {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean compatible(OptimizableCommand command) {
        if(command.getClass() == MoveForwardCommand.class || command.getClass() == MoveBackwardCommand.class || command.getClass() == MoveCommand.class){
            return true;
        }
        return false;
    }

    @Override
    public void changeValue(int value) {

    }
}
