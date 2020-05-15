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

    // variable to communicate with the user
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);

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
                            printer.printLine("Unknown option.Try again.");
                    }
        } while (option != Option.EXIT);
    }

    /*Thanks to this, we can ask the user
    over and over to enter the correct value.*/
    private Option getOption() {
        boolean optionOK = false;
        Option option = null;
        while (!optionOK) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOK = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", please provide again:");
            } catch (InputMismatchException e) {
                printer.printLine("You entered a value that is not a number, please enter again:");
            }
        }
        return option;
    }


    public void printOptions() {
        printer.printLine("Choose the option: ");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void addDisc() {
        try {
            CD cd = dataReader.readAndCreateDisc();
            library.addDisc(cd);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create CD, invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Capacity limit reached, no more CD can be added");
        }
    }

    private void printDisc()
    {
        Edition[] editions = library.getEditions();
        printer.printDiscs(editions);
    }

    private void addDLC() {
        try {
            DLC dlc = dataReader.readAndCreateStore();
            library.addDLC(dlc);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create DLC, invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Capacity limit reached, no more DLC can be added");
        }
    }

    private void printDLC() {

        Edition[] editions = library.getEditions();
        printer.printDLC(editions);
    }

    private void exit()
    {
        printer.printLine("End of the program.Bye!");
        dataReader.close();
    }


}
