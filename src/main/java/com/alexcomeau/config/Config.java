package com.alexcomeau.config;

import java.io.Serializable;

public class Config implements Serializable{
    public Config(){}

    //these will be written to be overridden by commandline args
    // TODO add command line configuration of the below two variables
    public static String CONFIG_FOLDER = "./config"; /** the config folder  (can be overridden by cmdline options)*/
    public static String CONFIG_FILE = "config.json"; /** the config file name (can be overridden by cmdline options) */
    
    /** the theme css file with relative path to config.json */
    private String themeFile = "theme.css";

    private String languagesFolder = "lang";

    private String stylesFolder = "styles";

    private String themeFolder = "themes";

    //could load from json without having these set
    //if these are set then load only these
    private String [] language_configs = {"none"};

    //getters and setters

    public String getThemeFile() {
        return this.themeFile;
    }

    public void setThemeFile(String themeFile) {
        if(themeFile == null){
            //do nothing
        }else{
        this.themeFile = themeFile;
        }
    }

    public String getLanguagesFolder() {
        return this.languagesFolder;
    }

    public void setLanguagesFolder(String languagesFolder) {
        if(languagesFolder == null){
            //do nothing
        }else{
            this.languagesFolder = languagesFolder;
        }
    }

    public String getThemeFolder() {
        return this.themeFolder;
    }

    public String getStylesFolder() {
        return this.stylesFolder;
    }

    public void setStyesFolder(String stylesFolder) {
        if(stylesFolder == null){
            //do nothing
        }else{
            this.stylesFolder = stylesFolder;
        }
    }

    public void setThemeFolder(String themeFolder) {
        this.themeFolder = themeFolder;
    }

    public String[] getLanguage_configs() {
        return this.language_configs;
    }

    public void setLanguage_configs(String[] language_configs) {
        if(language_configs == null || language_configs.length == 0 || language_configs.hashCode() == this.language_configs.hashCode()){
            //do nothing if empty, or if same as default
        }else{
            //strip .json from each element
            for(int i = 0; i < language_configs.length; i++){
                language_configs[i] = language_configs[i].replace(".json", "");
            }
            this.language_configs = language_configs;
        }
    }

    public String toString(){
        
        String confs = "";
        for(String s : language_configs){
            confs += s + ", ";
        }
        confs = confs.substring(0, confs.length() - 2);
    
        StringBuilder sb = new StringBuilder();
        sb.append("Config: \n");
        sb.append("\tconfigFolder: " + CONFIG_FOLDER + "\n");
        sb.append("\tconfigFile: " + CONFIG_FILE + "\n");
        sb.append("\tlangauagesFolder: " + languagesFolder + "\n");
        sb.append("\tstylesFolder: " + stylesFolder + "\n");
        sb.append("\tthemeFolder: " + themeFolder + "\n");
        sb.append("\tlanguage_configs: " + confs + "\n");
        return sb.toString();
    }


    
}
