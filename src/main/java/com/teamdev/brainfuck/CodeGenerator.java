package com.teamdev.brainfuck;


public class CodeGenerator {

    private String code = new String();
    private int tabsCount;

    private String getTabs(){
        String result = new String();
        for(int i=0; i<tabsCount; i++){
            result += "\t";
        }
        return result;
    }

    public String getCode(){
        return code;
    }

    public void addBlock(String text){
        code += String.format("%s%s{\r\n", getTabs(), text);
        addTab();
    }

    public void closeBlock(){
        removeTab();
        code += String.format("%s}\r\n", getTabs());
    }

    public void addCommand(String command){
        code += String.format("%s%s;\r\n", getTabs(), command );
    }

    public void openCycle(String command){
        addBlock(String.format("while(%s)\r\n", command ));
    }

    public void closeCycle(){
        closeBlock();
    }

    public void addTab(){
        tabsCount++;
    }

    public void removeTab(){
        tabsCount--;
    }

}
