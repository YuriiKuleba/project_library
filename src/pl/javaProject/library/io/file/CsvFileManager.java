package pl.javaProject.library.io.file;

import pl.javaProject.library.exeptions.DataExportException;
import pl.javaProject.library.exeptions.DataImportException;
import pl.javaProject.library.exeptions.InvalidDataException;
import pl.javaProject.library.model.Game;
import pl.javaProject.library.model.DLC;
import pl.javaProject.library.model.Edition;
import pl.javaProject.library.model.Library;

import java.io.*;
import java.util.Scanner;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Library.csv";

    @Override
    public void exportData(Library library) {
        //We are getting all available publications from the library.
        Edition[] editions = library.getEditions();
        try (FileWriter fileWriter = new FileWriter(FILE_NAME) ;
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
        {
            /*In the for-each loop, we go through each object, replace it with the CSV format and save it in the file,
            adding an additional enter character each time to have each record on a separate line.*/
            for (Edition edition : editions) {
                bufferedWriter.write(edition.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Error writing data to file " + FILE_NAME);
        }
    }

    @Override
    public Library importData() {
        Library library = new Library();
        try (Scanner fileReader = new Scanner(new File(FILE_NAME))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Edition edition = createObjectFromString(line);
                library.addEditions(edition);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportException("No file " + FILE_NAME);
        }

        return library;
    }

    private Edition createObjectFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if (Game.TYPE.equals(type)) {
            return createCD(split);
        } else if (DLC.TYPE.equals(type)) {
            return createDLC(split);
        }
        throw new InvalidDataException("Unknown publication type: " + type);
    }

    private Game createCD(String[] data) {
        String title = data[1];
        String publisher = data[2];
        String developer = data[3];
        String serialNumber = data[4];
        String language = data[5];
        int year = Integer.parseInt(data[6]);

        return new Game(title , publisher , developer , serialNumber , language , year);
    }

    private DLC createDLC(String[] data) {
        String title = data[1];
        String dlc = data[2];
        int day = Integer.parseInt(data[3]);
        int month = Integer.parseInt(data[4]);
        int year = Integer.parseInt(data[5]);

        return new DLC(title , dlc , day , month , year);
    }
}
