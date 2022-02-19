package com.alexcomeau.scene;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.fxmisc.richtext.CodeArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
                buildMenuItem("New (Ctrl-n)", handler -> {
                    //add a new tab
                    addTab("*new*");
                }),
                buildMenuItem("Open (ctrl-o)", handler -> {
                    MenuHandlers.addTab(this);
                }),
                buildMenuItem("save (ctrl-s)", handler -> {
                    System.out.println("Save");
                }),
                buildMenuItem("save as (ctrl-shift-s)", handler -> {
                    System.out.println("Save as");
                }),
                buildMenuItem("close (ctrl-w)", handler -> {
                    System.out.println("Close");
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



    public void addTab(String title){
        
        CodeArea ca = new CodeArea("");
        TabObject to = new TabObject(null, "java", true, title);
        to.setStartingText("");
        tabs.add(to);
        ca.setPrefHeight(scene.getHeight());
        ca.setPrefWidth(scene.getWidth());
        Tab tab = new Tab(title, ca);
        tab.onCloseRequestProperty().set(event -> {
            MenuHandlers.closeTab(ca, to, event);
            tabs.remove(to);
        });
        tp.getTabs().add(tab);
        tp.getSelectionModel().select(tab);
    }
    /**
     * add a tab to the tab pane
     * @param title
     * @param text
     */
    public void addTab(String title, String text){
        
        CodeArea ca = new CodeArea(text);
        TabObject to = new TabObject(null, "java", true, title);
        to.setStartingText(text);
        tabs.add(to);
        ca.setPrefHeight(scene.getHeight());
        ca.setPrefWidth(scene.getWidth());
        Tab tab = new Tab(title, ca);
        tab.onCloseRequestProperty().set(event -> {
            MenuHandlers.closeTab(ca, to, event);
            tabs.remove(to);
        });
        tp.getTabs().add(tab);
        tp.getSelectionModel().select(tab);
    }
    /**
     * add a tab to the tabpane
     * @param title the title of the tab
     * @param f the file to be opened
     * @throws FileNotFoundException
     */
    public void addTab(String title, File f) throws FileNotFoundException{
        //the text to display to the code area
        StringBuilder sb = new StringBuilder();
        //read from the file
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            sb.append(s.nextLine() + "\n");
        }
        //close resource
        s.close();
        //create the code area and add a tab object
        CodeArea ca = new CodeArea(sb.toString());
        TabObject to = new TabObject(f, "java", true, title);
        to.setStartingText(sb.toString());
        //add the tab
        tabs.add(to);
        //set the height and width of the code area
        ca.setPrefHeight(scene.getHeight());
        ca.setPrefWidth(scene.getWidth());
        //create the tab 
        Tab tab = new Tab(title, ca);
        //add the close event handler 
        tab.onCloseRequestProperty().set(event -> {
            MenuHandlers.closeTab(ca, to, event);
            tabs.remove(to);
        });
        //add the tab to the tab pane
        tp.getTabs().add(tab);
        //select tab
        tp.getSelectionModel().select(tab);
    }

    public static Button buildButton(String text, EventHandler<ActionEvent> handler){
        Button b = new Button(text);
        b.setOnAction(handler);
        return b;
    }

}
