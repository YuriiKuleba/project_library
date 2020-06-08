package pl.javaProject.library.io.file;

import pl.javaProject.library.exeptions.NoSuchFileTypeException;
import pl.javaProject.library.io.ConsolePrinter;
import pl.javaProject.library.io.DataReader;

public class FileManagerBuilder {

    private ConsolePrinter printer; //for reading data
    private DataReader reader;      //for displaying messages.

    public FileManagerBuilder(ConsolePrinter printer , DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    //build() is responsible for creating the SerializableFileManager object and returning it.
    public FileManager build() {
        printer.printLine("Select the data format:");
        FileType fileType = getFileType();
        switch (fileType) {
            case CSV:
                return new CsvFileManager();
            case SERIAL:
                return new SerializableFileManager();
            default:
                throw new NoSuchFileTypeException("Unsupported data type");
        }
    }

    /*The role of the getFileType () method is to convert the string loaded from the user to a FileType value.
    The user can enter "serial", "SERIAL" or this word in any case, because we use the toUpperCase () method
    of the String class.*/
    private FileType getFileType() {
        boolean typeOK = false;
        FileType result = null;
        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOK = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Unsupported data type, try again");
            }
        } while (!typeOK);

        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }
}
