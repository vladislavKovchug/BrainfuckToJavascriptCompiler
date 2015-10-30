package com.teamdev.brainfuck;

public class AddCommand implements Command {

    private int addNum;

    public AddCommand(int num){
        addNum = num;
    }

    public int getAddNum() {
        return addNum;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

}
