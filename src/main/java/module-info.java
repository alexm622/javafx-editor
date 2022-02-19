@SuppressWarnings("Java(8390069)")
module com.alexcomeau {

    requires transitive org.fxmisc.richtext;
    //require all the other modules
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.base;

    //this is going to have a warning no matter what because it is out of date
    requires transitive reactfx;

    //add language interface
    exports com.alexcomeau.lang;

    exports com.alexcomeau;
}