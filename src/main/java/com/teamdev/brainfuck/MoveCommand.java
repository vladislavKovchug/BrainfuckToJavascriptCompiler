package com.teamdev.brainfuck;

public class MoveCommand implements Command {

    private int moveNum;

    public MoveCommand(int num){
        moveNum = num;
    }

    public int getMoveNum() {
        return moveNum;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
