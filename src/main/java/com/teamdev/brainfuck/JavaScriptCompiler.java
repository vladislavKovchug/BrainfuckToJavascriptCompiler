package com.teamdev.brainfuck;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JavaScriptCompiler {

    public static final String PROGRAM = "++++++++[>++++[>++>+++>+++>+<<<<-]" +
            ">+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------" +
            ".>>+.>++.";

   public void compile(String brainfuckProgram, File outputJavaScriptFile) {

       final List<Command> commands = new Parser().parse(brainfuckProgram);

       final OptimizationVisitor optimizationVisitor = new OptimizationVisitor();

       for (Command command : commands) {
           command.accept(optimizationVisitor);
       }

       final List<Command> optimizedCommands =
               optimizationVisitor.getOptimizedCommands();

       // Just for fun!
       final ExecutionVisitor executionVisitor = new ExecutionVisitor();
       for (Command command : optimizedCommands) {
           command.accept(executionVisitor);
       }

       final JavaScriptCompilerVisitor javaScriptCompilerVisitor = new JavaScriptCompilerVisitor();
       for (Command command : optimizedCommands) {
           command.accept(javaScriptCompilerVisitor);
       }

       try {
           outputJavaScriptFile.createNewFile();
           PrintWriter writer = new PrintWriter(outputJavaScriptFile);
           writer.println(javaScriptCompilerVisitor.getJavaScript());
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }


    public static void main(String[] args) {
        final JavaScriptCompiler compiler = new JavaScriptCompiler();
        compiler.compile(PROGRAM, new File("./brainfuck.js"));
    }

}