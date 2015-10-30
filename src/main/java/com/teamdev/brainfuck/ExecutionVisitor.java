package com.teamdev.brainfuck;

public class ExecutionVisitor implements CommandVisitor {

    final private ExecutionContext context = new ExecutionContext();

    @Override
    public void visit(MoveForwardCommand command) {
        context.movePointer(1);
    }

    @Override
    public void visit(MoveBackwardCommand command) { context.movePointer(-1); }

    @Override
    public void visit(IncrementCommand command) {
        context.addToCurrentValue(1);
    }

    @Override
    public void visit(DecrementCommand command) {
        context.addToCurrentValue(-1);
    }

    @Override
    public void visit(PrintCommand command) {
        System.out.print((char) context.getCurrentValue());
    }

    @Override
    public void visit(LoopCommand command) {
        while (context.getCurrentValue() != 0) {
            for (Command innerCommand : command.getInnerCommands()) {
                innerCommand.accept(this);
            }
        }
    }

    @Override
    public void visit(AddCommand command){ context.addToCurrentValue(command.getAddNum()); }

    @Override
    public void visit(MoveCommand command){ context.movePointer(command.getMoveNum()); }

}
