package com.alexcomeau.scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * key combinations for editor
 * @author Alex Comeau
 */
public class KeyCombinations {
    public static final KeyCombination SAVE = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination SAVE_AS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination NEW_FILE = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination OPEN = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination CLOSE = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination CLOSE_ALL = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination EXIT = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination UNDO = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination REDO = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination PASTE = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination SELECT_ALL = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination FIND = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination REPLACE = new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination COMMENT = new KeyCodeCombination(KeyCode.SLASH, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination UNCOMMENT = new KeyCodeCombination(KeyCode.SLASH, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination COMPILE = new KeyCodeCombination(KeyCode.F9);
    public static final KeyCombination RUN = new KeyCodeCombination(KeyCode.F5);
    public static final KeyCombination DEBUG = new KeyCodeCombination(KeyCode.F6);
    public static final KeyCombination STOP = new KeyCodeCombination(KeyCode.F6, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination STEP = new KeyCodeCombination(KeyCode.F7);
    public static final KeyCombination STEP_OVER = new KeyCodeCombination(KeyCode.F8);
    public static final KeyCombination STEP_OUT = new KeyCodeCombination(KeyCode.F8, KeyCombination.SHIFT_DOWN);
    
}
