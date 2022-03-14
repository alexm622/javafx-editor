package com.alexcomeau.config.lang;

import java.util.Map;

public class LanguageConfig {
    private String name;    
    private String[] extensions;

    private String cssFile;
    

    private  Map<String, String[]> patternMap;
    
    //getters and setters

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getExtensions() {
        return this.extensions;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }

    public String getCssFile() {
        return this.cssFile;
    }

    public void setCssFile(String cssFile) {
        this.cssFile = cssFile;
    }

    public Map<String, String[]> getPatternMap() {
        return this.patternMap;
    }

    public void setPatternMap(Map<String, String[]> patternMap) {
        this.patternMap = patternMap;
    }


}
