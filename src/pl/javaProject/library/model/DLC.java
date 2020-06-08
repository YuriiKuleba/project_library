package pl.javaProject.library.model;

import java.util.Objects;

// DLC for games
public class DLC extends Edition {

    public static final String TYPE = "GameDLC";

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                dlc + ";" +
                day + ";" +
                month + ";" +
                getYear() + "";
    }

    private String dlc;
    private int day;
    private int month;

    public DLC(String title , String dlc , int day , int month , int year) {
        super(title , year);
        this.day = day;
        this.month = month;
        this.dlc = dlc;
    }

    public String getDlc() {
        return dlc;
    }

    public void setDlc(String dlc) {
        this.dlc = dlc;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return getTitle() + "; " + dlc + "; " + day + "; " + month + "; " + getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DLC dlc = (DLC) o;
        return getDay() == dlc.getDay() &&
                getMonth() == dlc.getMonth() &&
                Objects.equals(getDlc() , dlc.getDlc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode() , getDlc() , getDay() , getMonth());
    }
}
