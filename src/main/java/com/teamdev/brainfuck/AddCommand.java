package com.teamdev.brainfuck;

public class AddCommand implements OptimizableCommand {

    private int addNum;

    public int getAddNum() {
        return addNum;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean compatible(OptimizableCommand command) {
        if(command.getClass() == IncrementCommand.class || command.getClass() == DecrementCommand.class || command.getClass() == MoveCommand.class){
            return true;
        }
        return false;
    }

    @Override
    public void changeValue(int value) {
        addNum += value;
    }
}
