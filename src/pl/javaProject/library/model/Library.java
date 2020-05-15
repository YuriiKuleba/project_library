package pl.javaProject.library.model;

public class Library {

    private final static int MAX_EDITIONS = 200;
    private int editionNumber;
    private Edition[] editions = new Edition[MAX_EDITIONS];

    /*private final static int MAX_DISC = 100;
    private CD[] cdS = new CD[MAX_DISC];
    private int cdNumber = 0;

    private final static int MAX_DLC = 100;
    private StoreDLC[] dlcS = new StoreDLC[MAX_DLC];
    private int dlcNumber = 0;*/

    public Edition[] getEditions() {
        Edition[] result = new Edition[editionNumber];
        for (int i = 0 ; i < editionNumber ; i++) {
            result[i] = editions[i];
        }
        return result;
    }

    public void addDisc(CD cd) {
        addEditions(cd);
    }

    public void addDLC(DLC dlc) {
        addEditions(dlc);

    }


    private void addEditions(Edition edition) {
        if (editionNumber > MAX_EDITIONS) {
            throw new ArrayIndexOutOfBoundsException("Max editions exceeded " + MAX_EDITIONS);
        }
        editions[editionNumber] = edition;
        editionNumber++;
    }
}
