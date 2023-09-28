package ch.supsi.fsci.engine;

import java.util.ArrayList;
import java.util.List;

class DirectoryModel {
    private final String name;
    private final List<DirectoryModel> dir;

    public DirectoryModel(final String name, final List<DirectoryModel> dir) {
        this.name = name;
        this.dir = dir;
    }

    public DirectoryModel(final String name){
        this.name=name;
        this.dir = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<DirectoryModel> getDir() {
        return dir;
    }
}