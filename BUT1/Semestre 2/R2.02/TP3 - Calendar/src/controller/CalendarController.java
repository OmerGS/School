package controller;

import java.util.Arrays;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import view.CalendarSheet;
import model.Calendar;

public class CalendarController {
    private Calendar calendar;
    private CalendarSheet calendarSheet;

    public CalendarController(Scene scene, CalendarSheet calendarSheet) {
        this.calendar = new Calendar();
        this.calendarSheet = calendarSheet;


        scene.setOnKeyPressed(event -> {
            try{
                if (event.getCode() == KeyCode.LEFT) {
                    System.out.println("Previous Day");
                    updateDate("previous");

                } else if (event.getCode() == KeyCode.RIGHT) {
                    System.out.println("Next Day");
                    updateDate("next");
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        });
    }

    public String getCurrentDayName() {
        return this.calendar.getDayName();
    }

    public int getCurrentDayNumber() {
        return this.calendar.getDayNumber();
    }

    public String getCurrentMonthName() {
        return this.calendar.getMonthName();
    }

    public int getCurrentYear() {
        return this.calendar.getYear();
    }

    public String[] getMonthsNames() {
        return this.calendar.getMonthsNames();
    }

    public void updateMonth(int month) {
        this.calendar.setMonth(month);
        this.calendarSheet.updateDate();
    }    

    public void updateDate(String how) throws Exception {
        if (how.equals("next")) {
            calendar.nextDay();
            this.calendarSheet.updateDate();
        } else if (how.equals("previous")) {
            int currentMonth = calendar.getMonthNumber();
            int currentDay = calendar.getDayNumber();
    
            calendar.previousDay();
    
            if (currentDay == 1) {
                if (currentMonth == 1) {
                } else {
                    calendar.setMonth(currentMonth - 1);
                }
            }
            this.calendarSheet.updateDate();
        } else {
            throw new Exception("Vous pouvez uniquement avancer ou reculer dans le calendrier.");
        }
    }      

    public void setupMonthComboBox(ComboBox<String> monthComboBox) {
    monthComboBox.setOnAction(e -> {
        String selectedMonth = monthComboBox.getSelectionModel().getSelectedItem();
        int monthIndex = Arrays.asList(getMonthsNames()).indexOf(selectedMonth);
        updateMonth(monthIndex + 1);
    });
}

}