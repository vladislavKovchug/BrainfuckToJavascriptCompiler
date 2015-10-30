package com.teamdev.brainfuck;

public class JavaScriptCompilerVisitor implements CommandVisitor {

    private CodeGenerator codeGenerator = new CodeGenerator();

    public JavaScriptCompilerVisitor(){
        codeGenerator.openFunction("payload");

        codeGenerator.addCommand("var result = ''");
        codeGenerator.addCommand("var arr = []");
        codeGenerator.addCommand("for(var i=0; i<30000; i++)arr[i]=0");
        codeGenerator.addCommand("var pointer = 0");
    }

    public String getJavaScript(){
        codeGenerator.addCommand("return result");
        codeGenerator.closeFunction();
        return codeGenerator.getCode();
    }

    @Override
    public void visit(MoveForwardCommand command) {
        codeGenerator.addCommand("pointer++");
    }

    @Override
    public void visit(MoveBackwardCommand command) {
        codeGenerator.addCommand("pointer--");
    }

    @Override
    public void visit(IncrementCommand command) {
        codeGenerator.addCommand("arr[pointer]++");
    }

    @Override
    public void visit(DecrementCommand command) {
        codeGenerator.addCommand("arr[pointer]--");
    }

    @Override
    public void visit(PrintCommand command) {
        codeGenerator.addCommand("result += String.fromCharCode(arr[pointer])");
    }

    @Override
    public void visit(LoopCommand command) {
        codeGenerator.openCycle("arr[pointer]");
        for (Command innerCommand : command.getInnerCommands()) {
            innerCommand.accept(this);
        }
        codeGenerator.closeCycle();
    }

    @Override
    public void visit(AddCommand command) {
        codeGenerator.addCommand("arr[pointer] += " + Integer.toString(command.getAddNum()));
    }

    @Override
    public void visit(MoveCommand command) {
        codeGenerator.addCommand("pointer += " + Integer.toString(command.getMoveNum()));
    }
}
