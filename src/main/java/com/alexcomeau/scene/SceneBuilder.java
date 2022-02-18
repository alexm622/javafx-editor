package com.alexcomeau.scene;



import java.util.ArrayList;

import org.fxmisc.richtext.CodeArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class SceneBuilder {
    static TabPane tp = new TabPane();
    static ArrayList<TabObject> tabs = new ArrayList<TabObject>();
    int width, height;
    static Scene scene;
    public SceneBuilder(int width, int height){
        this.width = width;
        this.height = height;
    }
    public Scene buildScene() {
        VBox vbox = new VBox();
        scene = new Scene(vbox, width, height);
        //use a text area to display the text
        CodeArea ca = new CodeArea();
        ca.setPrefSize(width, height);
       

    
        vbox.getChildren().addAll(buildMenuBar(), tp);

        return scene;
    }

    private MenuBar buildMenuBar(){
        MenuBar menuBar = new MenuBar();
        final Menu file = new Menu("File");
        //add open and save to file
        file.getItems().addAll(
                buildMenuItem("Open (ctrl-o)", handler -> {
                    MenuHandlers.addTab(this);
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



    public void addTab(String title, String path){
        
        CodeArea ca = new CodeArea(path);
        TabObject to = new TabObject(null, "java", true, title);
        tabs.add(to);
        ca.setPrefHeight(scene.getHeight());
        ca.setPrefWidth(scene.getWidth());
        Tab tab = new Tab(title, ca);
        tab.onCloseRequestProperty().set(event -> {
            tabs.remove(to);
        });
        tp.getTabs().add(tab);
    }

}
