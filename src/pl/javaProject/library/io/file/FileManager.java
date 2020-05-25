package pl.javaProject.library.io.file;

import pl.javaProject.library.model.Library;

public interface FileManager {

    Library importData();
    void exportData(Library library);

}
