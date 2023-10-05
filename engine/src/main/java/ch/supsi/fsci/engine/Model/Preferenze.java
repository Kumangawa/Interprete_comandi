package ch.supsi.fsci.engine.Model;

public class Preferenze {
    private String language;
    private int prefCommandSpacerWidth;
    private int commandFieldPrefColumnCount;
    private int prefOutputAreaRowCount;
    private int prefInsetsSize;

    public String getLanguage() {
        return language;
    }

    public Preferenze setLanguage(String language) {
        this.language = language;
        return this;
    }

    public int getPrefCommandSpacerWidth() {
        return prefCommandSpacerWidth;
    }

    public Preferenze setPrefCommandSpacerWidth(int prefCommandSpacerWidth) {
        this.prefCommandSpacerWidth = prefCommandSpacerWidth;
        return this;
    }

    public int getCommandFieldPrefColumnCount() {
        return commandFieldPrefColumnCount;
    }

    public Preferenze setCommandFieldPrefColumnCount(int commandFieldPrefColumnCount) {
        this.commandFieldPrefColumnCount = commandFieldPrefColumnCount;
        return this;
    }

    public int getPrefOutputAreaRowCount() {
        return prefOutputAreaRowCount;
    }

    public Preferenze setPrefOutputAreaRowCount(int prefOutputAreaRowCount) {
        this.prefOutputAreaRowCount = prefOutputAreaRowCount;
        return this;
    }

    public int getPrefInsetsSize() {
        return prefInsetsSize;
    }

    public Preferenze setPrefInsetsSize(int prefInsetsSize) {
        this.prefInsetsSize = prefInsetsSize;
        return this;
    }
}
