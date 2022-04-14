package com.alexcomeau;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import com.alexcomeau.config.Config;
import com.alexcomeau.config.ConfigReader;
import com.alexcomeau.config.lang.LanguageConfReader;
import com.alexcomeau.lang.Language;
import com.alexcomeau.lang.LanguageMap;
import com.alexcomeau.lang.langdefs.Java;
import com.alexcomeau.scene.SceneBuilder;

import org.apache.commons.text.StringEscapeUtils;

import javafx.application.Application;

import javafx.stage.Stage; 

/**
 * JavaFX App
 */
public class App extends Application {
    public static HashMap<String,Language> languages = new HashMap<String, Language>();
    public static Config config;

    @Override
    /**
     * start the application
     */
    public void start(Stage stage) {
       
        config = ConfigReader.readConfig(Config.CONFIG_FOLDER + "/" + Config.CONFIG_FILE);

        //read the languages
        languages = new LanguageMap(LanguageConfReader.readLanguages()).getLangMap();
        languages.forEach((k,v) -> {
            System.out.println("language" + k + " : " + v.getCss());

        });

        //test to see what the matchers are for each language, for some reason it isn't
        System.out.println("java matcher: " + StringEscapeUtils.escapeJava(new Java().getPattern().toString()));
        //System.out.println("json matcher: " + StringEscapeUtils.escapeJava(languages.get("java").getPattern().toString()));
        
        stage.setTitle("jfx-edit");
        SceneBuilder sb = new SceneBuilder(700, 700);

        stage.setScene(sb.buildScene());
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        for(ExecutorService ex : SceneBuilder.executors){
            ex.shutdown();
        }
        super.stop();
    }

    public static void main(String[] args) {
        
        launch();
    }

}
