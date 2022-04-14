package com.alexcomeau.config.theme;

import com.alexcomeau.App;
import com.alexcomeau.config.Config;

public class ThemeReader {
    public ThemeReader(){
        System.out.println("ThemeReader Created");
    }

    public void readTheme(){
        System.out.println("ThemeReader readTheme");
        Config conf = App.config;
        String theme = conf.getThemeFile();

    }


    
}
