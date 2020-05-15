package pl.javaProject.library.app;

import pl.javaProject.library.exeptions.NoSuchOptionException;
import pl.javaProject.library.io.ConsolePrinter;
import pl.javaProject.library.io.DataReader;
import pl.javaProject.library.model.CD;
import pl.javaProject.library.model.DLC;
import pl.javaProject.library.model.Edition;
import pl.javaProject.library.model.Library;

import java.util.InputMismatchException;

class LibraryControl {

    // variables to control the program
    private final static int EXIT = 0;
    private final static int ADD_DISC = 1;
    private final static int ADD_DLC = 2;
    private final static int PRINT_DISC = 3;
    private final static int PRINT_DLC = 4;

    // variable to communicate with the user
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private DataReader dataReader = new DataReader(consolePrinter);

    // "library" storing data
    private Library library = new Library();

    //The main program method that allows selection of options and interaction

    public void controlLoop()
    {
        Option option;

        do {
            printOptions();
            option = getOption();
                    switch (option) {
                        case ADD_DISC:
                            addDisc();
                            break;
                        case ADD_DLC:
                            addDLC();
                            break;
                        case PRINT_DISC:
                            printDisc();
                            break;
                        case PRINT_DLC:
                            printDLC();
                            break;
                        case EXIT:
                            exit();
                            break;
                        default:
                            System.out.println("Unknown option.Try again.");
                    }
        } while (option != Option.EXIT);
    }


    private Option getOption() {
        boolean optionOK = false;
        Option option = null;
        while (!optionOK) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOK = true;
            } catch (NoSuchOptionException e) {
                consolePrinter.printLine(e.getMessage() + ", please provide again:");
            } catch (InputMismatchException e) {
                consolePrinter.printLine("You entered a value that is not a number, please enter again:");
            }
        }
        return option;
    }


    public void printOptions() {
        System.out.println("Choose the option: ");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }

    private void addDisc() {
        try {
            CD cd = dataReader.readAndCreateDisc();
            library.addDisc(cd);
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Failed to create CD, invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            consolePrinter.printLine("Capacity limit reached, no more CD can be added");
        }
    }

    private void printDisc()
    {
        Edition[] editions = library.getEditions();
        consolePrinter.printDiscs(editions);
    }

    private void addDLC() {
        try {
            DLC dlc = dataReader.readAndCreateStore();
            library.addDLC(dlc);
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Failed to create DLC, invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            consolePrinter.printLine("Capacity limit reached, no more DLC can be added");
        }
    }

    private void printDLC() {

        Edition[] editions = library.getEditions();
        consolePrinter.printDLC(editions);
    }

    private void exit()
    {
        System.out.println("End of the program.Bye!");
        dataReader.close();
    }


}
