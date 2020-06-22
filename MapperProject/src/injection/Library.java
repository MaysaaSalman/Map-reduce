package injection;

import java.io.Serializable;

public class Library implements Serializable {
    private static final long serialVersionUID = 99111176957470L;

    private String libraryPath;

    public Library() {
    }

    public Library(String libraryPath) {
        this.libraryPath = libraryPath;
    }

    public String getLibraryPath() {
        return libraryPath;
    }

    public void setLibraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
    }
}