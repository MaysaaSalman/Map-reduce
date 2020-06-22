package injection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Import implements Serializable {
    private static final long serialVersionUID = 99181276757570L;


    private ArrayList<Library> libraries;

    public Import() {
        libraries = new ArrayList<>(getDefault());
    }

    public Import(List<Library> libraries) {
        this();
        if (libraries != null) this.libraries.addAll(libraries);
    }

    //TODO Add any default imports you need all the times here!
    private List<Library> getDefault() {
        return Arrays.asList(
                new Library("java.util.ArrayList"),
                new Library("injection.NodeEngine"),
                new Library("node.NodeProperties"),
                new Library("injection.Parameter"),
                new Library("mapreduce.TaskTracker"),
                new Library("mapreduce.Context"),
                new Library("mapreduce.TaskInfo"),
                new Library("mapreduce.Record"),
                new Library("mapreduce.InputSplit"),
                new Library("java.io.IOException")
        );
    }

    public ArrayList<Library> getLibraries() {
        return new ArrayList<>(libraries);
    }

    public void setLibraries(ArrayList<Library> libraries) {
        this.libraries = libraries;
    }

    public void addLibrary(String libraryPath) {
        libraries.add(new Library(libraryPath));
    }
}