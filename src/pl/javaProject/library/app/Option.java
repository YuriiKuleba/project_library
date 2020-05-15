package pl.javaProject.library.app;
import pl.javaProject.library.exeptions.*;

public enum Option {
    EXIT(0 , " - Exit the program"),
    ADD_DISC(1 , " - Adding a new disc"),
    ADD_DLC(2 , " - Adding a new dlc"),
    PRINT_DISC(3 , " - Display available discs"),
    PRINT_DLC(4 , " - Display available dlc");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

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
