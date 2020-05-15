package pl.javaProject.library.model;


import java.util.Objects;

// Editions: GOTY, Gold, OneDay , SteelBook, Deluxe, Digital etc.
public abstract class Edition {

    private String title;
    private int year;

    Edition(String title , int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        String s = title;
        if (year != 0) {
            s = s + "; " + year;
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edition edition = (Edition) o;
        return getYear() == edition.getYear() &&
                Objects.equals(getTitle() , edition.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle() , getYear());
    }
}
