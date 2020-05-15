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


    /*Private because we don't use it anywhere else and no one outside
    this class needs to know that it exists at all.
    Because we have access to all fields and methods of this type from the code
    of the packaging class (LibraryControl), even private ones, we can opt out
    of the getValue () and getDescription () getters*/
    private enum Option {
        EXIT(0 , " - Exit the program"),
        ADD_DISC(1 , " - Adding a new disc"),
        ADD_DLC(2 , " - Adding a new dlc"),
        PRINT_DISC(3 , " - Display available discs"),
        PRINT_DLC(4 , " - Display available dlc");

        private int value;
        private String description;

        Option(int value , String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + "-" + description;
        }

        /*Here we're catching the ArrayIndexOutOfBoundsException exception.
        It can occur when someone gives e.g. a negative number or greater than 4.*/
        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No option with id " + option);
            }
        }

    }


}
