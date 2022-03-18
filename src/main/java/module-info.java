@SuppressWarnings("Java(8390069)")
module com.alexcomeau {

    requires transitive org.fxmisc.richtext;
    //require all the other modules
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.base;

    //this is going to have a warning no matter what because it is out of date
    requires transitive reactfx;

    //include jackson and jackson-databind
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;

    //add language interface
    exports com.alexcomeau.lang;

    //include config and required classes
    exports com.alexcomeau.config;
    exports com.alexcomeau.config.lang;

    //opens config and languageconfig
    opens com.alexcomeau.config;
    opens com.alexcomeau.lang;

    //apache commons
    requires transitive org.apache.commons.lang3;
    requires transitive org.apache.commons.text;






    exports com.alexcomeau;
}