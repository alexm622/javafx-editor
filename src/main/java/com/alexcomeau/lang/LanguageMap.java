package com.alexcomeau.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.alexcomeau.lang.langdefs.Java;

public class LanguageMap {
    public static HashMap<String, Language> getLanguages(){
        HashMap<String, Language> languages = new HashMap<String, Language>();
        for(String s : new Java().getExtension()){
            languages.put(s, new Java());
        }
        
        languages.put("txt", new Language() {
            @Override
            public Pattern getPattern() {
                return Pattern.compile("");
            }

            @Override
            public String[] getExtension() {
                return new String[] {"txt"};
            }

            @Override
            public String[] getKeywords() {
                return new String[0];
            }
            
            @Override
            public String getCss() {
                return "nothing.css";
            }
        });
        return languages;
    }
    private ArrayList<Language> languages;
    public LanguageMap(ArrayList<Language> langs) {
        this.languages = langs;
    }
    
    public HashMap<String, Language> getLangMap(){
        HashMap<String, Language> langMap = new HashMap<String, Language>();
        for(Language l : languages){
            for(String s : l.getExtension()){
                langMap.put(s, l);
            }
        }
        langMap.put("txt", new Language() {
            @Override
            public Pattern getPattern() {
                return Pattern.compile("");
            }

            @Override
            public String[] getExtension() {
                return new String[] {"txt"};
            }

            @Override
            public String[] getKeywords() {
                return new String[0];
            }
            
            @Override
            public String getCss() {
                return "nothing.css";
            }
        });
        return langMap;
    }
}
