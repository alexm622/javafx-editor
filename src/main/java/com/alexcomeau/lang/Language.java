package com.alexcomeau.lang;

// an example of implementation
public interface Language {
    public String extension = "";
    public String[ ] keywords = {};
    public String highlightText(String text);
}
