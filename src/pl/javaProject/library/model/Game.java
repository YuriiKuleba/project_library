package pl.javaProject.library.model;

import java.util.Objects;

// CD games
public class Game extends Edition {

    public static final String TYPE = "Game";

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                developer + ";" +
                publisher + ";" +
                serialNumber + ";" +
                language + ";" +
                getYear() + "";
    }

    private String developer;
    private String publisher;
    private String serialNumber;
    private String language;

    public Game(String title , String developer , String publisher , String serialNumber , String language , int year) {
        super(title , year);
        this.developer = developer;
        this.publisher = publisher;
        this.serialNumber = serialNumber;
        this.language = language;
    }


    public String getDeveloper()
    {
        return developer;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }


    @Override
    public String toString() {
        return getTitle() + "; " + developer + "; " + publisher
                + "; " + serialNumber + "; " + language + "; " + getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Game game = (Game) o;
        return Objects.equals(getDeveloper() , game.getDeveloper()) &&
                Objects.equals(getPublisher() , game.getPublisher()) &&
                Objects.equals(getSerialNumber() , game.getSerialNumber()) &&
                Objects.equals(getLanguage() , game.getLanguage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode() , getDeveloper() , getPublisher() , getSerialNumber() , getLanguage());
    }

    /*discs[0] = new Disc("Batman Return to Arkham" , "Rocksteady Studios" , "Warner Bros. Interactive Entertainment"
                , "CUSA 04607" , "Rus-subtitles" , 2015);
        discs[1] = new Disc("Bloodborne GOTY" , "FromSoftware" , "Sony Computer Entertainment"
                , "CUSA 03173" , "Rus-subtitles" , 2015);
        discs[2] = new Disc("Detroit Become Human" , "Quantic Dream" , "Sony Interactive Entertainment"
                , "CUSA 08308" , "RUS-dubbing" , 2018);
        discs[3] = new Disc("God of War" , "SIE Santa Monica Studio" , "Sony Interactive Entertainment"
                , "CUSA 07412" , "RUS-dubbing" , 2018);
        discs[4] = new Disc("Tomb Raider: Definitive Edition" , "Crystal Dynamics" , "Square Enix"
                , "CUSA 00109" , "RUS-dubbing" , 2014);*/
}
