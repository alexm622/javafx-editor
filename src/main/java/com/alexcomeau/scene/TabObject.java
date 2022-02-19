package com.alexcomeau.scene;

import java.io.File;

import org.fxmisc.richtext.CodeArea;

public class TabObject{
    private String name;
    private String text;
    private String startingText;
    private File file;
    private String extension;
    private boolean isSaved;
    final private CodeArea ca;
    public TabObject(File f, String ext, boolean isSaved, String name, CodeArea ca){
        this.ca = ca;
        this.file = f;
        this.extension = ext;
        this.isSaved = isSaved;
        this.name = name;
    }

    public CodeArea getCa(){
        return ca;
    }
    

    public String getStartingText() {
        return startingText;
    }

    public void setStartingText(String startingText) {
        this.startingText = startingText;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isIsSaved() {
        return this.isSaved;
    }

    public boolean getIsSaved() {
        return this.isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }


    
}