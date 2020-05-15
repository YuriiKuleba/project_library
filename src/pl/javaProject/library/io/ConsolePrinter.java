package pl.javaProject.library.io;

import pl.javaProject.library.model.CD;
import pl.javaProject.library.model.DLC;
import pl.javaProject.library.model.Edition;

public class ConsolePrinter {

    public void printDiscs(Edition[] editions)
    {
        int countCD = 0;
        for (Edition edition : editions) {
            if (edition instanceof CD) {
                printLine(edition.toString());
                countCD++;
            }
        }
        if (countCD == 0)
            printLine("CD library is empty");
    }


    public void printDLC(Edition[] editions) {
        int countDLC = 0;
        for (Edition edition : editions) {
            if (edition instanceof DLC) {
                printLine(edition.toString());
                countDLC++;
            }
        }
        if (countDLC == 0)
            printLine("DLC library is empty");
    }


    public void printLine(String text) {
        System.out.println(text);
    }
}