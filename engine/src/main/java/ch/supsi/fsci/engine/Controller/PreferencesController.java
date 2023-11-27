package ch.supsi.fsci.engine.Controller;

import ch.supsi.fsci.engine.Data.PreferencesData;
import ch.supsi.fsci.engine.Model.PreferencesModel;

public class PreferencesController {

    private final PreferencesModel preferencesModel;
    private final PreferencesData preferencesData;

    public PreferencesController(PreferencesModel preferencesModel, PreferencesData preferencesData){
        this.preferencesData = preferencesData;
        this.preferencesModel = preferencesModel;
    }

    public String getPreference(String key) {
        return preferencesModel.getPreference(key);
    }

    public void savePreferences() {
        preferencesData.savePreferences(preferencesModel);
    }

    public void setPreferenceFilePath(String path) {
        preferencesData.setPreferenceFilePath(path);
    }
    public String getPreferenceFilePath() {
        return preferencesData.getPreferenceFilePath();
    }

    public void setPreference(String key, String value){
        preferencesModel.setPreference(key, value);
    }

    public PreferencesModel getPreferencesModel(){
        return preferencesModel;
    }
}
