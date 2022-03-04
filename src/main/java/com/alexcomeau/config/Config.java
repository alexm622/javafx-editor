package com.alexcomeau.config;

public abstract class Config {
    //these will be written to be overridden by commandline args
    // TODO add command line configuration of the below two variables
    public static String CONFIG_FOLDER = "config"; /** the config folder  (can be overridden by cmdline options)*/
    public static String CONFIG_FILE = "config.json"; /** the config file name (can be overridden by cmdline options) */
    
    /** the theme css file with relative path to config.json */
    private String themeFile = "theme.css";

    private String langauagesFolder = "lang";

    private String themeFolder = "themes";

    //could load from json without having these set
    //if these are set then load only these
    private String [] language_configs = {"none"};

    //getters and setters

    public String getThemeFile() {
        return this.themeFile;
    }

    public void setThemeFile(String themeFile) {
        this.themeFile = themeFile;
    }

    public String getLangauagesFolder() {
        return this.langauagesFolder;
    }

    public void setLangauagesFolder(String langauagesFolder) {
        this.langauagesFolder = langauagesFolder;
    }

    public String getThemeFolder() {
        return this.themeFolder;
    }

    public void setThemeFolder(String themeFolder) {
        this.themeFolder = themeFolder;
    }

    public String[] getLanguage_configs() {
        return this.language_configs;
    }

    public void setLanguage_configs(String[] language_configs) {
        this.language_configs = language_configs;
    }


    
}
