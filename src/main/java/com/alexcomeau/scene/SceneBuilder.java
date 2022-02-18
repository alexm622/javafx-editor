package com.alexcomeau.scene;



import org.fxmisc.richtext.CodeArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.VBox;

public class SceneBuilder {
    static CodeArea ca = new CodeArea();
    public Scene buildScene(int width, int height) {
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, width, height);
        //use a text area to display the text
        CodeArea ca = new CodeArea();
        ca.setPrefSize(width, height);
       

    
        vbox.getChildren().addAll(buildMenuBar(), ca);

        return scene;
    }

    private MenuBar buildMenuBar(){
        MenuBar menuBar = new MenuBar();
        final Menu file = new Menu("File");
        //add open and save to file
        file.getItems().addAll(
                buildMenuItem("Open (ctrl-o)", handler -> {
                    System.out.println("Open");
                }),
                buildMenuItem("save (ctrl-s)", handler -> {
                    System.out.println("Save");
                })
        );
        
        menuBar.getMenus().add(file);
        return menuBar;
    }

    private MenuItem buildMenuItem(String text, EventHandler<ActionEvent> handler){
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction(handler);
        return menuItem;
    }

    

    


}
