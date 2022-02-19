package com.alexcomeau.lang;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.alexcomeau.lang.langdefs.Java;

public class LanguageMap {
    public static HashMap<String, Language> getLanguages(){
        HashMap<String, Language> languages = new HashMap<String, Language>();
        languages.put("java", new Java());
        languages.put("txt", new Language() {
            @Override
            public Pattern getPattern() {
                return Pattern.compile("");
            }

            @Override
            public String getExtension() {
                return "txt";
            }

            @Override
            public String[] getKeywords() {
                return new String[0];
            }
            
            @Override
            public String getCss() {
                return "";
            }
        });
        return languages;
    }
}
