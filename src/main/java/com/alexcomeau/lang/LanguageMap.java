package com.alexcomeau.lang;

import java.util.HashMap;

import com.alexcomeau.lang.langdefs.Java;

public class LanguageMap {
    public static HashMap<String, Language> getLanguages(){
        HashMap<String, Language> languages = new HashMap<String, Language>();
        languages.put("java", new Java());
        return languages;
    }
}
