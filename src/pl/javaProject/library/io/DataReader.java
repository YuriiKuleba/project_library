package pl.javaProject.library.io;

import pl.javaProject.library.model.CD;
import pl.javaProject.library.model.DLC;

import java.util.Scanner;

public class DataReader {

    private Scanner sc = new Scanner(System.in);

    /*Displaying messages now through a ConsolePrinter object,
    not directly by the printer.printLine () method.*/
    private ConsolePrinter printer;

    /*We pass the ConsolePrinter object in the constructor,
    instead of creating it manually. As a result,
    there will be only one ConsolePrinter object in the application
    that we create in the LibraryControl class.*/
    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public String getString() {
        return sc.nextLine();
    }

    public void close()
    {
        sc.close();
    }

    /*The finally block is needed because regardless of whether
    someone enters the correct number or something incorrect,
    we must get rid of the input stream or the enter character,
    or an invalid value.
    The try-finally block used is simply a shortened version of such try-catch block:

    public int getInt() {
    try {
        return sc.nextInt();
    } catch (InputMismatchException e) {
        throw e;
    } finally {
        sc.nextLine();
    }
}*/
    public int getInt()
    {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public CD readAndCreateDisc()
    {
        printer.printLine("Title: ");
        String title = sc.nextLine();
        printer.printLine("Developer: ");
        String developer = sc.nextLine();
        printer.printLine("Publisher: ");
        String publisher = sc.nextLine();
        printer.printLine("CUSA number: ");
        String serialNumber = sc.nextLine();
        printer.printLine("Language: ");
        String language = sc.nextLine();
        printer.printLine("Release date: ");
        int releaseDate = getInt();

        CD cd = new CD(title , developer , publisher , serialNumber , language , releaseDate);
        return cd;
    }


    public DLC readAndCreateStore() {
        printer.printLine("Title: ");
        String title = sc.nextLine();
        printer.printLine("DLC: ");
        String dlc = sc.nextLine();
        printer.printLine("Day: ");
        int day = getInt();
        printer.printLine("Month: ");
        int month = getInt();
        printer.printLine("Year: ");
        int year = getInt();

        return new DLC(title , dlc , day , month , year);
    }
}
