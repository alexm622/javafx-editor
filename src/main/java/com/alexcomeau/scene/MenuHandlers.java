package com.alexcomeau.scene;

public class MenuHandlers {
    public static void addTab(SceneBuilder sb){
        double r = Math.random();
        String name = "Tab " + r;
        sb.addTab(name, "some random stuff");

    }
}
