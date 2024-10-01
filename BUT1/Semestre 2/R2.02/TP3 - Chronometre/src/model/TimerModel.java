package model;

/**
* A class representing time as mm:ss
*
* @author Abdelbadie Belmouhcine
*/

/*
imports for SimpleIntegerProperty and IntegerProperty
*/
import javafx.beans.property.*;

public class TimerModel {

    /*We represent min and sec as IntegerProperty, 
    so that we can associate "javafx.beans.InvalidationListener" to them*/
    private IntegerProperty min = new SimpleIntegerProperty(); //Attribute representing minutes
    private IntegerProperty sec = new SimpleIntegerProperty(); //Attribute representing seconds

    /*getters for values*/
    public int getMin() {
        return min.get(); //the get() method of IntegerProperty returns the Integer value associated with that property
    }

    public int getSec() {
        return sec.get(); //the get() method of IntegerProperty returns the Integer value associated with that property
    }

    /*getters for properties*/
    public IntegerProperty getMinProperty() {
        return min;
    }

    public IntegerProperty getSecProperty() {
        return sec;
    }

    /*This method increases the timer by 1 second*/
    public void next() {
        int temp = sec.get(); //get the old value of seconds
        temp=(temp+1)%60; // increase it in a circular way (modulo 60)
        if (temp == 0)
            min.set(min.get() + 1); //increase minutes if the number of second reaches 0, and set that as the new value of min property
        sec.set(temp); //set the new value of seconds as the new value for sec property
    }
    /*This method reset the timer to 00:00*/
    public void res() {
        sec.set(0); //set the value of sec to 0
        min.set(0); //set the value of min to 0
    }
}