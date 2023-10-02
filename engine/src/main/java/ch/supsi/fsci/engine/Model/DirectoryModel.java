package ch.supsi.fsci.engine.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryModel that = (DirectoryModel) o;
        return Objects.equals(name, that.name) && Objects.equals(dir, that.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dir);
    }
}