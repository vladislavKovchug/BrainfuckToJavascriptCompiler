package com.teamdev.brainfuck;

public class ExecutionVisitor implements CommandVisitor {

    final private ExecutionContext context = new ExecutionContext();

    @Override
    public void visit(MoveForwardCommand command) {
        context.movePointerForward();
    }

    @Override
    public void visit(MoveBackwardCommand command) {
        context.movePointerBackward();
    }

    @Override
    public void visit(IncrementCommand command) {
        context.incrementCurrentValue();
    }

    @Override
    public void visit(DecrementCommand command) {
        context.decrementCurrentValue();
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

}
