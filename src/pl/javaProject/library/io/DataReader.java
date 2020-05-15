package pl.javaProject.library.io;

import pl.javaProject.library.model.CD;
import pl.javaProject.library.model.DLC;

import java.util.Scanner;

public class DataReader {

    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter consolePrinter;

    public DataReader(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void close()
    {
        sc.close();
    }

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
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("Developer: ");
        String developer = sc.nextLine();
        System.out.println("Publisher: ");
        String publisher = sc.nextLine();
        System.out.println("CUSA number: ");
        String serialNumber = sc.nextLine();
        System.out.println("Language: ");
        String language = sc.nextLine();
        System.out.println("Release date: ");
        int releaseDate = sc.nextInt();

        CD cd = new CD(title , developer , publisher , serialNumber , language , releaseDate);
        return cd;
    }


    public DLC readAndCreateStore() {
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("DLC: ");
        String dlc = sc.nextLine();
        System.out.println("Day: ");
        int day = getInt();
        System.out.println("Month: ");
        int month = getInt();
        System.out.println("Year: ");
        int year = getInt();

        return new DLC(title , dlc , day , month , year);
    }
}
