package com.alexcomeau.scene;

import java.io.File;

//bonus! records
public record TabObject(File f, String extension, boolean saved, String filename){}