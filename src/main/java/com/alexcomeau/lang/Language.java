package com.alexcomeau.lang;

import java.util.regex.Pattern;

// an example of implementation
public interface Language {
    public Pattern getPattern();
    public String getExtension();
    public String[] getKeywords();
    public String getCss();
}
