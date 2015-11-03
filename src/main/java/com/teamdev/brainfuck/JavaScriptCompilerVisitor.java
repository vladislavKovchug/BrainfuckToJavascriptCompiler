package com.teamdev.brainfuck;

public class JavaScriptCompilerVisitor implements CommandVisitor {

    private CodeGenerator codeGenerator = new CodeGenerator();

    public JavaScriptCompilerVisitor(){
        codeGenerator.addBlock("function BrainfuckCode(resultCallBack)");
        codeGenerator.addBlock("return ");
        codeGenerator.addBlock("run : function()");
        codeGenerator.addCommand("var result = ''");
        codeGenerator.addCommand("var memory = Array.apply(null, new Array(30000)).map(Number.prototype.valueOf,0);");
        codeGenerator.addCommand("var pointer = 0");
    }

    public String getJavaScript(){
        codeGenerator.addCommand("resultCallBack(result)");
        codeGenerator.closeBlock();
        codeGenerator.closeBlock();
        codeGenerator.closeBlock();

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
        codeGenerator.addCommand("memory[pointer]++");
    }

    @Override
    public void visit(DecrementCommand command) {
        codeGenerator.addCommand("memory[pointer]--");
    }

    @Override
    public void visit(PrintCommand command) {
        codeGenerator.addCommand("result += String.fromCharCode(memory[pointer])");
    }

    @Override
    public void visit(LoopCommand command) {
        codeGenerator.openCycle("memory[pointer]");
        for (Command innerCommand : command.getInnerCommands()) {
            innerCommand.accept(this);
        }
        codeGenerator.closeCycle();
    }

    @Override
    public void visit(AddCommand command) {
        codeGenerator.addCommand("memory[pointer] += " + Integer.toString(command.getAddNum()));
    }

    @Override
    public void visit(MoveCommand command) {
        codeGenerator.addCommand("pointer += " + Integer.toString(command.getMoveNum()));
    }
}
