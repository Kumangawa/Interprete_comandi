package ch.supsi.fsci.engine.Controller;

import ch.supsi.fsci.engine.Interface.PreferencesInterface;
import ch.supsi.fsci.engine.Model.PreferencesModel;

public class PreferencesController {

    private final PreferencesInterface preferencesModel;

    public PreferencesController(final PreferencesInterface preferencesModel){
        this.preferencesModel = preferencesModel;
    }

    public String getPreference(String key) {
        return preferencesModel.getPreference(key);
    }

    public void setPreferenceFilePath(String path) {
       preferencesModel.setPreferenceFilePath(path);
    }
    public String getPreferenceFilePath() {
        return preferencesModel.getPreferenceFilePath();
    }

    public void setPreference(String key, String value){
        preferencesModel.setPreference(key, value);
    }

    public PreferencesInterface getPreferencesModel(){
        return preferencesModel;
    }
}
