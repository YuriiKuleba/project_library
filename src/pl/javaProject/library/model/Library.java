package pl.javaProject.library.model;

import java.io.Serializable;

public class Library implements Serializable {

    private final static int MAX_EDITIONS = 200;
    private int editionNumber;
    private Edition[] editions = new Edition[MAX_EDITIONS];

    // a getter that shows us a copy of the array omitting the null value
    public Edition[] getEditions() {
        Edition[] result = new Edition[editionNumber];
        for (int i = 0 ; i < editionNumber ; i++) {
            result[i] = editions[i];
        }
        return result;
    }

    //can really be removed, but we leave it for readability.
    public void addDisc(CD cd) {
        addEditions(cd);
    }

    //can really be removed, but we leave it for readability.
    public void addDLC(DLC dlc) {
        addEditions(dlc);

    }

    // Method that add a new Edition
    private void addEditions(Edition edition) {
        if (editionNumber > MAX_EDITIONS) {
            throw new ArrayIndexOutOfBoundsException("Max editions exceeded "
                    + MAX_EDITIONS);
        }
        editions[editionNumber] = edition;
        editionNumber++;
    }
}
