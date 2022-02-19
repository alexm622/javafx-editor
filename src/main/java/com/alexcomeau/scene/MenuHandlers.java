package com.alexcomeau.scene;

import java.io.File;
import java.io.FileWriter;

import org.fxmisc.richtext.CodeArea;

import javafx.event.Event;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.FileChooser;

public class MenuHandlers {
    public static void addTab(SceneBuilder sb) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new java.io.File("."));
        File f;
        // add button to open file
        f = fileChooser.showOpenDialog(null);

        for(TabObject to : SceneBuilder.tabs) {
            if(to.getFile() == null){
                continue;
            }else if(to.getFile().equals(f)) {
                SceneBuilder.tp.getSelectionModel().select(SceneBuilder.tabs.indexOf(to));
                return;
            }
        }

        try {
            sb.addTab(f.getPath(), f);
        } catch (Exception e) {
            e.printStackTrace();
            String name = "FileNotFound";
            sb.addTab(name, "the file was not found or something went well");
            //select the tab
            
        }
    }

    public static void closeTab(CodeArea ca, TabObject to, Event e) {
        if ((to.getName().equals("*new*") || !to.getStartingText().equals(ca.getText()))) {
            //if its a new file and it is empty discard
            if(to.getName().equals("*new*") && ca.getText().equals("")){
                
                return;
            }
            if(to.getName().equals("*new*")){
                to.setName("untitled");
            }
            saveDialog(ca, to, e);
        }

    }

    private static void saveDialog(CodeArea ca, TabObject to, Event e) {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Save");
        dialog.setContentText(
                "This tab is unsaved, if you close it you may lose any changes.\nWould you like to save?");
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.YES,
                ButtonType.NO,
                ButtonType.CANCEL);
        dialog.showAndWait().ifPresent(type -> {
            if (type == ButtonType.YES) {
                to.setText(ca.getText());
                if (to.getName().equals("untitled")) {
                    saveAs(to, e);
                } else {
                    save(to, e);
                }
            } else if (type == ButtonType.NO) {
               
            } else if (type == ButtonType.CANCEL) {
                e.consume();
            }
        });

        // write contents of tab to file

    }

    public static void saveAs(TabObject to, Event e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(new java.io.File("."));
        File f;
        // add button to open file
        f = fileChooser.showSaveDialog(null);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(to.getText());
            fw.close();
        } catch (Exception ee) {
            if(e == null){
                //this is intentional
                return;
            }
            Dialog<String> dialogErr = new Dialog<>();
            dialogErr.setTitle("something went wrong");
            dialogErr.setContentText("something went wrong");
            dialogErr.getDialogPane().getButtonTypes().addAll(
                    ButtonType.OK

            );
            dialogErr.showAndWait().ifPresent(responseErr -> {
                if (responseErr.equals(ButtonType.OK.toString())) {
                    e.consume();
                }
            });
        }
    }

    public static void save(TabObject to, Event e) {
        try {
            FileWriter fw = new FileWriter(to.getFile());
            fw.write(to.getText());
            fw.close();
        } catch (Exception ee) {
            Dialog<String> dialogErr = new Dialog<>();
            dialogErr.setTitle("something went wrong");
            dialogErr.setContentText("something went wrong");
            dialogErr.getDialogPane().getButtonTypes().addAll(
                    ButtonType.OK

            );
            dialogErr.showAndWait().ifPresent(responseErr -> {
                if (responseErr.equals(ButtonType.OK.toString())) {
                    e.consume();
                }
            });
        }
    }

}
