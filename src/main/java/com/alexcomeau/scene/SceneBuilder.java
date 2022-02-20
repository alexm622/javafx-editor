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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;

public class SceneBuilder {
    final KeyCombination save = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    final KeyCombination saveAs = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    final KeyCombination newFile = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    final KeyCombination open = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
    final KeyCombination close = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
    final KeyCombination closeAll = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    final KeyCombination exit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
    final KeyCombination undo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    final KeyCombination redo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    final KeyCombination paste = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
    final KeyCombination selectAll = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    final KeyCombination find = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
    final KeyCombination replace = new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN);
    final KeyCombination comment = new KeyCodeCombination(KeyCode.SLASH, KeyCombination.CONTROL_DOWN);
    final KeyCombination uncomment = new KeyCodeCombination(KeyCode.SLASH, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    final KeyCombination compile = new KeyCodeCombination(KeyCode.F9);
    final KeyCombination run = new KeyCodeCombination(KeyCode.F5);
    final KeyCombination debug = new KeyCodeCombination(KeyCode.F6);
    final KeyCombination stop = new KeyCodeCombination(KeyCode.F6, KeyCombination.SHIFT_DOWN);
    final KeyCombination step = new KeyCodeCombination(KeyCode.F7);
    final KeyCombination stepOver = new KeyCodeCombination(KeyCode.F8);
    final KeyCombination stepOut = new KeyCodeCombination(KeyCode.F8, KeyCombination.SHIFT_DOWN);


    static TabPane tp = new TabPane();
    static ArrayList<TabObject> tabs = new ArrayList<TabObject>();
    public static ArrayList<ExecutorService> executors = new ArrayList<ExecutorService>();
    public static ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
    int width, height;
    static Scene scene;

    public SceneBuilder(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene buildScene() {
        VBox vbox = new VBox();
        scene = new Scene(vbox, width, height);
        vbox.getChildren().addAll(buildMenuBar(), tp);

        addEventHandlers(scene);

        return scene;
    }

    private void addEventHandlers(Scene scene){
        scene.setOnKeyPressed(handler->{
            if(save.match(handler)){
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
            else if(saveAs.match(handler)){
                if(tabs.size() == 0){

                }else{
                    MenuHandlers.saveAs(tabs.get(tp.getSelectionModel().getSelectedIndex()), null);
                }
            }
            else if(newFile.match(handler)){
                addTab("*new*");
            }
            else if(open.match(handler)){
                MenuHandlers.addTab(this);
            }
            else if(close.match(handler)){
                if (tabs.size() == 0) {
                    // exit gracefully
                    System.exit(1);

                } else {
                    EventHandler<Event> tabHandler = tp.getSelectionModel().getSelectedItem().getOnCloseRequest();
                    tabHandler.handle(null);
                    tp.getTabs().remove(tp.getSelectionModel().getSelectedIndex());
                }
            }
            else if(closeAll.match(handler)){
                while(tabs.size() != 0){
                    EventHandler<Event> tabHandler = tp.getSelectionModel().getSelectedItem().getOnCloseRequest();
                    tabHandler.handle(null);
                    tp.getTabs().remove(tp.getSelectionModel().getSelectedIndex());
                }
            }
            else if(exit.match(handler)){
                while(tabs.size() != 0){
                    EventHandler<Event> tabHandler = tp.getSelectionModel().getSelectedItem().getOnCloseRequest();
                    tabHandler.handle(null);
                    tp.getTabs().remove(tp.getSelectionModel().getSelectedIndex());
                    // exit gracefully
                    System.exit(1);
                }
            }
            else if(undo.match(handler)){
                if (tabs.size() == 0) {

                }else{
                    tabs.get(tp.getSelectionModel().getSelectedIndex()).getCa().undo();
                }
            }
            else if(redo.match(handler)){
                if(tabs.size() == 0){
                }else{
                    tabs.get(tp.getSelectionModel().getSelectedIndex()).getCa().redo();
                }
            }
            else if(find.match(handler)){
                //find only
            }
            else if(replace.match(handler)){
                //this will be find and replace
            }
            else if(comment.match(handler)){
                //TODO implement comment
            }
            else if(uncomment.match(handler)){
                //TODO implement uncomment;
            }
            else if(compile.match(handler)){
                //we're not going to compile anything, this is too complicated at the moment
                //TODO implement compile
            }
            else if(run.match(handler)){
                //same as compile
            }
            else if(debug.match(handler)){
                //same as compile
            }
            else if(stop.match(handler)){
                //same as compile
            }
            else if(step.match(handler)){
                //same as compile
            }
            else if(stepOver.match(handler)){
                //same as compile
            }
            else if(stepOut.match(handler)){
                //same as compile
            }
        });
    }

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

    private MenuItem buildMenuItem(String text, EventHandler<ActionEvent> handler) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction(handler);
        return menuItem;
    }

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
            if(!event.isConsumed()){
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
            if(!event.isConsumed()){
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
        addStyling(ca, to);

        //close handler
        int index = tp.getTabs().size() - 1;
        tab.onCloseRequestProperty().set(event -> {
            MenuHandlers.closeTab(ca, to, event);
            if(!event.isConsumed()){
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

    public static Button buildButton(String text, EventHandler<ActionEvent> handler) {
        Button b = new Button(text);
        b.setOnAction(handler);
        return b;
    }

    private static void addStyling(CodeArea ca, TabObject to) {
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
        ca.getStylesheets().add(l.getCss());
    }

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
