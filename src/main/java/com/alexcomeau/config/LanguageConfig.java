package com.alexcomeau.config;

public abstract class LanguageConfig {
    private String name;    
    private String[] extensions;

    private String cssFile;

    private String[] keywords;

    private String stringPattern;
    private String commentPattern;
    private String[] multilineCommentPattern; // {start, end}
    private String numberPattern;
    private String booleanPattern;
    private String nullPattern;
    private String lineEnder;
    private String[] specialChars; // {list of special chars, like '\n'}
    
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

    public String[] getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getStringPattern() {
        return this.stringPattern;
    }

    public void setStringPattern(String stringPattern) {
        this.stringPattern = stringPattern;
    }

    public String getCommentPattern() {
        return this.commentPattern;
    }

    public void setCommentPattern(String commentPattern) {
        this.commentPattern = commentPattern;
    }

    public String[] getMultilineCommentPattern() {
        return this.multilineCommentPattern;
    }

    public void setMultilineCommentPattern(String[] multilineCommentPattern) {
        this.multilineCommentPattern = multilineCommentPattern;
    }

    public String getNumberPattern() {
        return this.numberPattern;
    }

    public void setNumberPattern(String numberPattern) {
        this.numberPattern = numberPattern;
    }

    public String getBooleanPattern() {
        return this.booleanPattern;
    }

    public void setBooleanPattern(String booleanPattern) {
        this.booleanPattern = booleanPattern;
    }

    public String getNullPattern() {
        return this.nullPattern;
    }

    public void setNullPattern(String nullPattern) {
        this.nullPattern = nullPattern;
    }

    public String getLineEnder() {
        return this.lineEnder;
    }

    public void setLineEnder(String lineEnder) {
        this.lineEnder = lineEnder;
    }

    public String[] getSpecialChars() {
        return this.specialChars;
    }

    public void setSpecialChars(String[] specialChars) {
        this.specialChars = specialChars;
    }

}
