package com.alexcomeau.lang.langdefs;

import java.util.regex.Pattern;

import com.alexcomeau.lang.Language;

public class Java implements Language {
    private String extension = "java";
    // keywords
    private String[] KEYWORDS = {
            "abstract", "continue", "for", "new", "switch", "assert", "default", "goto", "package", "synchronized",
            "boolean", "do", "if", "private", "this", "break", "double", "implements", "protected", "throw", "byte",
            "else", "import", "public", "throws", "case", "enum", "instanceof", "return", "transient", "catch",
            "extends", "int", "short", "try", "char", "final", "interface", "static", "void", "class", "finally",
            "long", "strictfp", "volatile", "const", "float", "native", "super", "while"
    };

    private static String KEYWORD_PATTERN;
    private static String PAREN_PATTERN = "\\(|\\)";
    private static String BRACE_PATTERN = "\\{|\\}";
    private static String BRACKET_PATTERN = "\\[|\\]";
    private static String SEMICOLON_PATTERN = "\\;";
    private static String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

    private Pattern pattern;

    public Java() {
        KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        pattern = Pattern.compile(
                "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                        + "|(?<PAREN>" + PAREN_PATTERN + ")"
                        + "|(?<BRACE>" + BRACE_PATTERN + ")"
                        + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                        + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                        + "|(?<STRING>" + STRING_PATTERN + ")"
                        + "|(?<COMMENT>" + COMMENT_PATTERN + ")");
    }

    public Pattern getPattern() {
        return this.pattern;
    }


    @Override
    public String getExtension() {
        return this.extension;
    }


    @Override
    public String[] getKeywords() {
        return this.KEYWORDS;
    }

    @Override 
    public String getCss(){
        
        return "java.css";
    }


}