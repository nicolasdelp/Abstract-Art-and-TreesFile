package com.delplanquerideau;

/**
 * Enum représentant les couleurs disponibles
 */
public enum Colors {
    RED("FD151B"), BLUE("01295F"), YELLOW("FFB30F"), BLACK("11151C"), WHITE("FBFBFB");

    private String hexCode;
    
    private Colors(String hexCode){
        this.hexCode = hexCode;
    }

    public String getHexCode(){
        return this.hexCode;
    }
}
