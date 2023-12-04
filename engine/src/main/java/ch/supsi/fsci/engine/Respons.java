package ch.supsi.fsci.engine;

public class Respons {

    public String onlyKey(String key){
        return String.format(Localization.getSingleton().localize(key));
    }

    public String keyAndOneParameter(String key, String parameter){
        return String.format(Localization.getSingleton().localize(key), parameter);
    }

    public String keyAndTwoParameter(String key, String first, String second){
        return String.format(Localization.getSingleton().localize(key), first, second);
    }
}
