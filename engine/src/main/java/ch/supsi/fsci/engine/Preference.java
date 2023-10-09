package ch.supsi.fsci.engine;

public class Preference {
    private String language;
    private int prefCommandSpacerWidth;
    private int commandFieldPrefColumnCount;
    private int prefOutputAreaRowCount;
    private int prefInsetsSize;

    public Preference() {
        this.language = "en";
        this.prefCommandSpacerWidth = 11;
        this.commandFieldPrefColumnCount = 80;
        this.prefOutputAreaRowCount = 25;
        this.prefInsetsSize = 7;
    }

    public String getLanguage() {
        return language;
    }

    public int getPrefCommandSpacerWidth() {
        return prefCommandSpacerWidth;
    }

    public int getCommandFieldPrefColumnCount() {
        return commandFieldPrefColumnCount;
    }

    public int getPrefOutputAreaRowCount() {
        return prefOutputAreaRowCount;
    }

    public int getPrefInsetsSize() {
        return prefInsetsSize;
    }

    // Metodo per impostare i valori delle preferenze
    public void setPreference(String key, String value) {
        switch (key) {
            case "language":
                this.language = value;
                break;
            case "prefCommandSpacerWidth":
                this.prefCommandSpacerWidth = Integer.parseInt(value);
                break;
            case "commandFieldPrefColumnCount":
                this.commandFieldPrefColumnCount = Integer.parseInt(value);
                break;
            case "prefOutputAreaRowCount":
                this.prefOutputAreaRowCount = Integer.parseInt(value);
                break;
            case "prefInsetsSize":
                this.prefInsetsSize = Integer.parseInt(value);
                break;
        }
    }
}
