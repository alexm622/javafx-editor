package com.alexcomeau.scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.alexcomeau.App;
import com.alexcomeau.lang.Language;
import com.alexcomeau.lang.Styler;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.reactfx.Subscription;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

/**
 * SceneBuilder
 * @author Alex Comeau
 */
public class SceneBuilder {
    
    static TabPane tp = new TabPane();
    static ArrayList<TabObject> tabs = new ArrayList<TabObject>();
    public static ArrayList<ExecutorService> executors = new ArrayList<ExecutorService>();
    public static ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
    int width, height;
    static Scene scene;
    
    /**
     * initialize object with width and height
     * @param width
     * @param height
     */
    public SceneBuilder(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * build the scene and return it
     */
    public Scene buildScene() {
        VBox vbox = new VBox();
        scene = new Scene(vbox, width, height);
        vbox.getChildren().addAll(buildMenuBar(), tp);

        addEventHandlers(scene);



        return scene;
    }
    
    /**
     * add event handlers to the scene
     * @param scene the scene to add the event handlers to
     */
    private void addEventHandlers(Scene scene){
        scene.setOnKeyPressed(handler->{
            if(KeyCombinations.SAVE.match(handler)){
                if (tabs.size() == 0) {
                        
                } else {
                    
                    tabs.get(tp.getSelectionModel().getSelectedIndex())
                            .setText(tabs.get(tp.getSelectionModel().getSelectedIndex()).getCa().getText());
                    if (tabs.get(tp.getSelectionModel().getSelectedIndex()).getFile() == null) {
                        
                        MenuHandlers.saveAs(tabs.get(tp.getSelectionModel().getSelectedIndex()), null);
                    } else {
                        
                        MenuHandlers.save(tabs.get(tp.getSelectionModel().getSelectedIndex()), null);
                    }
                }
            }
            else if(KeyCombinations.SAVE_AS.match(handler)){
                if(tabs.size() == 0){

                }else{
                    MenuHandlers.saveAs(tabs.get(tp.getSelectionModel().getSelectedIndex()), null);
                }
            }
            else if(KeyCombinations.NEW_FILE.match(handler)){
                addTab("*new*");
            }
            else if(KeyCombinations.OPEN.match(handler)){
                MenuHandlers.addTab(this);
            }
            else if(KeyCombinations.CLOSE.match(handler)){
                if (tabs.size() == 0) {
                    // exit gracefully
                    System.exit(1);

                } else {
                    EventHandler<Event> tabHandler = tp.getSelectionModel().getSelectedItem().getOnCloseRequest();
                    tabHandler.handle(null);
                    tp.getTabs().remove(tp.getSelectionModel().getSelectedIndex());
                }
            }
            else if(KeyCombinations.CLOSE_ALL.match(handler)){
                while(tabs.size() != 0){
                    EventHandler<Event> tabHandler = tp.getSelectionModel().getSelectedItem().getOnCloseRequest();
                    tabHandler.handle(null);
                    tp.getTabs().remove(tp.getSelectionModel().getSelectedIndex());
                }
            }
            else if(KeyCombinations.EXIT.match(handler)){
                while(tabs.size() != 0){
                    EventHandler<Event> tabHandler = tp.getSelectionModel().getSelectedItem().getOnCloseRequest();
                    tabHandler.handle(null);
                    tp.getTabs().remove(tp.getSelectionModel().getSelectedIndex());
                    // exit gracefully
                    System.exit(1);
                }
            }
            else if(KeyCombinations.UNDO.match(handler)){
                if (tabs.size() == 0) {

                }else{
                    tabs.get(tp.getSelectionModel().getSelectedIndex()).getCa().undo();
                }
            }
            else if(KeyCombinations.REDO.match(handler)){
                if(tabs.size() == 0){
                }else{
                    tabs.get(tp.getSelectionModel().getSelectedIndex()).getCa().redo();
                }
            }
            else if(KeyCombinations.FIND.match(handler)){
                //find only
            }
            else if(KeyCombinations.REPLACE.match(handler)){
                //this will be find and replace
            }
            else if(KeyCombinations.COMMENT.match(handler)){
                //TODO implement comment
            }
            else if(KeyCombinations.UNCOMMENT.match(handler)){
                //TODO implement uncomment;
            }
            else if(KeyCombinations.COMPILE.match(handler)){
                /*
                * TODO implement compile
                * currently this is not at the top of the list of priorities
                * this and following functions may be implemented later if time permits
                */
            }
            else if(KeyCombinations.RUN.match(handler)){
                //same as compile
            }
            else if(KeyCombinations.DEBUG.match(handler)){
                //same as compile
            }
            else if(KeyCombinations.STOP.match(handler)){
                //same as compile
            }
            else if(KeyCombinations.STEP.match(handler)){
                //same as compile
            }
            else if(KeyCombinations.STEP_OVER.match(handler)){
                //same as compile
            }
            else if(KeyCombinations.STEP_OUT.match(handler)){
                //same as compile
            }
        });
    }

    /**
     * build the menu bar
     */
    private MenuBar buildMenuBar() {
        MenuBar menuBar = new MenuBar();
        final Menu file = new Menu("File");
        // add open and save to file
        file.getItems().addAll(
                buildMenuItem("New (Ctrl-n)", handler -> {
                    // add a new tab
                    addTab("*new*");
                }),
                buildMenuItem("Open (ctrl-o)", handler -> {
                    MenuHandlers.addTab(this);
                }),
                buildMenuItem("save (ctrl-s)", handler -> {
                    if (tabs.size() == 0) {
                        
                    } else {
                        
                        tabs.get(tp.getSelectionModel().getSelectedIndex())
                                .setText(tabs.get(tp.getSelectionModel().getSelectedIndex()).getCa().getText());
                        if (tabs.get(tp.getSelectionModel().getSelectedIndex()).getFile() == null) {
                            
                            MenuHandlers.saveAs(tabs.get(tp.getSelectionModel().getSelectedIndex()), null);
                        } else {
                            
                            MenuHandlers.save(tabs.get(tp.getSelectionModel().getSelectedIndex()), null);
                        }
                    }
                }),
                buildMenuItem("save as (ctrl-shift-s)", handler -> {
                    if (tabs.size() == 0) {
                        
                    } else {
                        
                        tabs.get(tp.getSelectionModel().getSelectedIndex())
                                .setText(tabs.get(tp.getSelectionModel().getSelectedIndex()).getCa().getText());
                        MenuHandlers.saveAs(tabs.get(tp.getSelectionModel().getSelectedIndex()), null);
                    }
                }),
                buildMenuItem("close (ctrl-w)", handler -> {
                    if (tabs.size() == 0) {
                        // exit gracefully
                        System.exit(1);

                    } else {
                        EventHandler<Event> tabHandler = tp.getSelectionModel().getSelectedItem().getOnCloseRequest();
                        tabHandler.handle(null);
                        tp.getTabs().remove(tp.getSelectionModel().getSelectedIndex());
                    }
                }));

        menuBar.getMenus().add(file);
        return menuBar;
    }

    /**
     * build a menu item
     *
     * @param name
     * @param handler
     * @return 
     */
    private MenuItem buildMenuItem(String text, EventHandler<ActionEvent> handler) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction(handler);
        return menuItem;
    }

    /**
     * add a tab to the tab pane
     * @param title the title of the tab
     */
    public void addTab(String title){
        
        CodeArea ca = new CodeArea("");
        
        
        ca.setPrefHeight(scene.getHeight());
        ca.setPrefWidth(scene.getWidth());
        
        TabObject to = new TabObject(null, "txt", true, title, ca);
        to.setStartingText("");
        tabs.add(to);

        Tab tab = new Tab(title, ca);
        tp.getTabs().add(tab);
        tp.getSelectionModel().select(tab);
        
        addStyling(ca, to);

        int index = tp.getTabs().size() - 1;
        tab.onCloseRequestProperty().set(event -> {
            MenuHandlers.closeTab(ca, to, event);
            if(event == null || !event.isConsumed()){
                tabs.remove(to);
                Subscription sub = subscriptions.get(index);
                subscriptions.remove(index);
                sub.unsubscribe();
                ExecutorService exe = executors.get(index);
                executors.remove(exe);
                exe.shutdown();
            }
        });

        
    }

    /**
     * add a tab to the tab pane
     * 
     * @param title
     * @param text
     */
    public void addTab(String title, String text) {

        CodeArea ca = new CodeArea(text);
        TabObject to = new TabObject(null, "txt", true, title, ca);
        to.setStartingText(text);
        tabs.add(to);
        ca.setPrefHeight(scene.getHeight());
        ca.setPrefWidth(scene.getWidth());
        Tab tab = new Tab(title, ca);
        addStyling(ca, to);
        int index = tp.getTabs().size() - 1;
        tab.onCloseRequestProperty().set(event -> {
            MenuHandlers.closeTab(ca, to, event);
            if(event == null || !event.isConsumed()){
                tabs.remove(to);
                Subscription sub = subscriptions.get(index);
                subscriptions.remove(index);
                sub.unsubscribe();
                ExecutorService exe = executors.get(index);
                executors.remove(exe);
                exe.shutdown();
            }
        });
        tp.getTabs().add(tab);
        tp.getSelectionModel().select(tab);
    }

    /**
     * add a tab to the tabpane
     * 
     * @param title the title of the tab
     * @param f     the file to be opened
     * @throws FileNotFoundException
     */
    public void addTab(String title, File f) throws FileNotFoundException {

        // read from the file
        StringBuilder sb = new StringBuilder();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            sb.append(s.nextLine() + "\n");
        }
        s.close();

        //create the text area
        CodeArea ca = new CodeArea(sb.toString());
        String extension;
        if (!f.getName().contains(".")) {
            extension = "txt";
        } else {
            extension = f.getName().substring(f.getName().lastIndexOf(".") + 1);
        }

        ca.setPrefHeight(scene.getHeight());
        ca.setPrefWidth(scene.getWidth());

        //add the tab
        TabObject to = new TabObject(f, extension, true, title, ca);
        to.setStartingText(sb.toString());

        tabs.add(to);

        Tab tab = new Tab(title, ca);

        tp.getTabs().add(tab);
        tp.getSelectionModel().select(tab);

        //add the highlighting
        System.out.println("extension: " + to.getExtension());
        addStyling(ca, to);

        //close handler
        int index = tp.getTabs().size() - 1;
        tab.onCloseRequestProperty().set(event -> {
            MenuHandlers.closeTab(ca, to, event);
            if(event == null || !event.isConsumed()){
                tabs.remove(to);
                Subscription sub = subscriptions.get(index);
                subscriptions.remove(index);
                sub.unsubscribe();
                ExecutorService exe = executors.get(index);
                executors.remove(exe);
                exe.shutdown();
            }
        });


        
    }

    /**
     * build a button
     * @param text the text of the button
     * @param handler the handler for the button
     * @return
     */
    public static Button buildButton(String text, EventHandler<ActionEvent> handler) {
        Button b = new Button(text);
        b.setOnAction(handler);
        return b;
    }

    /**
     * add styling to the code area
     * @param ca the code area
     * @param to the tab object
     */
    private static void addStyling(CodeArea ca, TabObject to) {
        System.out.println("adding styling");
        ca.setParagraphGraphicFactory(LineNumberFactory.get(ca));

        ExecutorService exe = Executors.newSingleThreadExecutor();
        executors.add(exe);

        //set styler
        Styler s;
        Language l;
        if(App.languages.containsKey(to.getExtension())){
            System.out.println("extension: " + to.getExtension());
            l = App.languages.get(to.getExtension());
            System.out.println(l.getCss());
        }else{
            System.out.println("using text");
            l = App.languages.get("txt");
        }

        s = new Styler(ca, l, exe);
      
        Subscription cleanupWhenDone = ca.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .retainLatestUntilLater(exe)
                .supplyTask(s::computeHighlightingAsync)
                .awaitLatest(ca.multiPlainChanges())
                .filterMap(t -> {
                    Optional<StyleSpans<Collection<String>>> o;
                    if (t.isSuccess()) {
                        o = Optional.of(t.get());
                    } else {
                        o = Optional.empty();
                    }
                    return o;
                })
                .subscribe(s::applyHighlighting);
        subscriptions.add(cleanupWhenDone);
        ca.getStylesheets().removeAll();
        ca.getStylesheets().addAll(new CodeArea().getStylesheets());
        //TODO so this is adding properly, but for some reason the matching isn't working
        ca.getStylesheets().add(new File(l.getCss()).toURI().toString());
        ca.getStylesheets().forEach(System.out::println);
    }
    
    /**
     * reapply the styling to the code area
     * @param to the tab object
     */
    public static void reapplyStyling(TabObject to){

        //we also need to remove the executor and subscription
        int index = tabs.indexOf(to);
        Subscription sub = subscriptions.get(index);
        subscriptions.remove(index);
        sub.unsubscribe();
        
        ExecutorService exe = executors.get(index);
        executors.remove(index);
        exe.shutdown();

        addStyling(to.getCa(), to);
    }

}
