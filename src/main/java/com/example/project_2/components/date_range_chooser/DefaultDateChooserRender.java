package com.example.project_2.components.date_range_chooser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultDateChooserRender implements DateChooserRender {

    @Override
    public String renderLabelCurrentDate(DateRangeChooser dateChooser, Date date) {
        return "Today : " + dateChooser.getDateFormat().format(date);
    }

    @Override
    public String renderTextFieldDate(DateRangeChooser dateChooser, Date date) {
        return dateChooser.getDateFormat().format(date);
    }

    @Override
    public String renderTextFieldDateBetween(DateRangeChooser dateChooser, DateRangeBetween dateBetween) {
        if (dateBetween.getToDate() != null) {
            if (dateBetween.getFromDate().compareTo(dateBetween.getToDate()) == 0) {
                return dateChooser.getDateFormat().format(dateBetween.getFromDate());
            } else {
                return dateChooser.getDateFormat().format(dateBetween.getFromDate()) + dateChooser.getBetweenCharacter() + dateChooser.getDateFormat().format(dateBetween.getToDate());
            }
        } else {
            return dateChooser.getDateFormat().format(dateBetween.getFromDate());
        }
    }

    @Override
    public String renderDateCell(DateRangeChooser dateChooser, Date date) {
        DateFormat df = new SimpleDateFormat("d");
        return df.format(date);
    }
}
