package com.alexcomeau.config.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.alexcomeau.App;
import com.alexcomeau.lang.Language;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LanguageConfReader {
    //for now this will only read the languages listed in the config file
    public static ArrayList<Language> readLanguages() {
        ArrayList<Language> languages = new ArrayList<Language>();
        ArrayList<LanguageConfig> language_configs = new ArrayList<LanguageConfig>();
        for(String path : App.config.getLanguage_configs()){
            language_configs.add(readLanguageConfig("config/" + App.config.getLanguagesFolder() + "/" + path));
        }

        //we need to map the language configs into a language object
        for(LanguageConfig lc : language_configs){
            languages.add(new LanguageBuilder(lc).buildLanguage());
        }

        return languages;
    }

    private static LanguageConfig readLanguageConfig(String path){
        LanguageConfig lc = new LanguageConfig();
        ObjectMapper om = new ObjectMapper();
        try{
            File f = new File(path);
            if(f.exists()){
                lc = om.readValue(f, LanguageConfig.class);
            }else if(new File(path + ".json").exists()){
                lc = om.readValue(new File(path + ".json"), LanguageConfig.class);
            }else{
                throw new FileNotFoundException(String.format("Language config file %s not found", path));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return lc;
    }
}
