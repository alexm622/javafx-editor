package com.alexcomeau;

import java.util.HashMap;

import com.alexcomeau.lang.Language;
import com.alexcomeau.lang.LanguageMap;
import com.alexcomeau.scene.SceneBuilder;

import javafx.application.Application;

import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static HashMap<String,Language> languages = new HashMap<String, Language>();

    @Override
    public void start(Stage stage) {
        languages = LanguageMap.getLanguages();
        
        stage.setTitle("jfx-edit");
        SceneBuilder sb = new SceneBuilder(700, 700);

        stage.setScene(sb.buildScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}