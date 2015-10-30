package com.teamdev.brainfuck;


import java.util.List;

public class LoopCommand implements Command {

    private final List<Command> innerCommands;

    public LoopCommand(List<Command> innerCommands) {
        this.innerCommands = innerCommands;
    }

    public List<Command> getInnerCommands() {
        return innerCommands;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

}
