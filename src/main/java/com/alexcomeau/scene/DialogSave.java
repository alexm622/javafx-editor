package com.alexcomeau.scene;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DialogSave extends Application{
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(new Group(), 300, 250));
        primaryStage.show();
    }
    
}
