package com.alexcomeau.lang;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import javafx.concurrent.Task;

public class Styler {
    private Language language;
    private CodeArea ca;
    private Executor executor;
    public Styler(CodeArea ca, Language language, Executor exe) {
        this.language = language;
        this.ca = ca;
        this.executor = exe;
    }
    public Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = ca.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    public void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        ca.setStyleSpans(0, highlighting);
    }

    //TODO this needs to be redone to better accomodate configurable languages and matching groups
    private StyleSpans<Collection<String>> computeHighlighting(String txt){
        Matcher m = language.getPattern().matcher(txt);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(m.find()){
            String styleClass = 
                m.group("KEYWORD") != null ? "keyword" :
                m.group("PAREN") != null ? "paren" :
                m.group("BRACE") != null ? "brace" :
                m.group("BRACKET") != null ? "bracket" :
                m.group("SEMICOLON") != null ? "semicolon" :
                m.group("STRING") != null ? "string" :
                m.group("COMMENT") != null ? "comment" :
                null; assert styleClass != null; //unknown style
            System.out.println("styleclass: " + styleClass);
            spansBuilder.add(Collections.emptyList(), m.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), m.end() - m.start());
            lastKwEnd = m.end();
        }
        spansBuilder.add(Collections.emptyList(), txt.length() - lastKwEnd);
        return spansBuilder.create();
    }
}
