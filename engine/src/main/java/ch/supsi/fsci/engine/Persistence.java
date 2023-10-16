package ch.supsi.fsci.engine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Persistence {
    public static String PREFERENCE_FILE_PATH = System.getProperty("user.home") + File.separator + "GUI" + File.separator + "preferences.txt";
    private Preference preference;

    // Constructor
    public Persistence(){
        PREFERENCE_FILE_PATH = System.getProperty("user.home") + File.separator + "GUI" + File.separator + "preferences.txt";
        this.preference = new Preference();
    }

    // private
    private synchronized static void createFolderGUI() {
        try {
            Path defaultPath = Paths.get(PREFERENCE_FILE_PATH).getParent();
            Files.createDirectories(defaultPath);
            Files.setAttribute(defaultPath, "dos:hidden", true);
        } catch (IOException e) {
            System.out.println("createFolderGUI: " + e.getMessage());
        }
    }

    private void savePreference() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PREFERENCE_FILE_PATH))) {
            writer.write("language: " + preference.getLanguage() + "\n");
            writer.write("prefCommandSpacerWidth: " + preference.getPrefCommandSpacerWidth() + "\n");
            writer.write("commandFieldPrefColumnCount: " + preference.getCommandFieldPrefColumnCount() + "\n");
            writer.write("prefOutputAreaRowCount: " + preference.getPrefOutputAreaRowCount() + "\n");
            writer.write("prefInsetsSize: " + preference.getPrefInsetsSize() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public
    public void initializeExplicit(){
        File preferenceFile = new File(PREFERENCE_FILE_PATH);
        if (preferenceFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(preferenceFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        preference.setPreference(key, value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createFolderGUI();
        }
        savePreference();
    }

    public HashMap<String, String> getPreference() {
        HashMap<String, String> preferencesData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PREFERENCE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    preferencesData.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return preferencesData;
    }

    public void setPreferenceFilePath(String path){
        PREFERENCE_FILE_PATH = path;
    }

    public String getPreferenceFilePath(){
        return PREFERENCE_FILE_PATH;
    }
}
