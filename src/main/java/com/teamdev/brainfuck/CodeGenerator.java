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

    public void addCommand(String command){
        code += String.format("%s%s;\r\n", getTabs(), command );
    }

    public void openCycle(String command){
        code += String.format("%swhile(%s){\r\n", getTabs(), command );
        tabsCount++;
    }

    public void closeCycle(){
        tabsCount--;
        code += String.format("%s}\r\n", getTabs());
    }

    public void openFunction(String name){
        code += String.format("%sfunction %s(){\r\n", getTabs(), name );
        tabsCount++;
    }

    public void closeFunction(){
        tabsCount--;
        code += String.format("%s}\r\n", getTabs());
    }

}
