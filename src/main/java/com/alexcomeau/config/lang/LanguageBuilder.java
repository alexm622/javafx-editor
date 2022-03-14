package com.alexcomeau.config.lang;

import java.util.regex.Pattern;

import com.alexcomeau.lang.Language;

public class LanguageBuilder {
    private LanguageConfig lc;
    public LanguageBuilder(LanguageConfig lc){
        this.lc = lc;
    }

    public Language buildLanguage(){
        return new Language() {
            @Override
            public Pattern getPattern() {
                return buildPattern();
            }

            @Override
            public String[] getExtension() {
                return lc.getExtensions();
            }

            @Override
            public String getCss() {
                return lc.getCssFile();
            }
        };
    }

    private Pattern buildPattern(){
        String pattern = "";
        
        for(String key : lc.getPatternMap().keySet()){
            if(pattern.length() > 0){
                pattern += "|";
            }
            pattern += "(?<" + key.toUpperCase() + ">";
            pattern += patternArrayToString(lc.getPatternMap().get(key));
            pattern += ")";
        }
        return Pattern.compile(pattern);
    }

    private String patternArrayToString(String[] strings){
        Pattern wordPattern = Pattern.compile("([a-z]*)");
        String pattern = "";
        boolean isWord = true;
        for(String s: strings){
            if(!wordPattern.matcher(s).matches()){
                isWord = false;
                break;
            }
        }
        if(isWord){
            pattern += "\\b(";
        }
        for(String s: strings){
            if(pattern.length() > 0 | (isWord && pattern.length() > "\\b(".length())){
                pattern += "|";
            }
            pattern += s;
        }
        if(isWord){
            pattern += ")\\b";
        }
        return pattern;
    }
}
