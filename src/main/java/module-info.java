module com.alexcomeau {

    requires org.fxmisc.richtext;
    //require all the other modules
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.base;

    //add language interface
    exports com.alexcomeau.lang;

    exports com.alexcomeau;
}