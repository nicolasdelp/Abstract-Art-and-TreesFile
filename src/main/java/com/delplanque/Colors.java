package com.delplanque;

public enum Color {
    RED("FD151B"), BLUE("01295F"), YELLOW("FFB30F"), BLACK("11151C"), WHITE("FBFBFB");

    private String codeHex;
    private Color(String codeHex){
        this.codeHex = codeHex;
    }

    public String getCodeHex(){
        return this.codeHex;
    }
}
