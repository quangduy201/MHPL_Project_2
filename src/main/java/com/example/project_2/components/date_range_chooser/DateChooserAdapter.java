package com.example.project_2.components.date_range_chooser;

import java.util.Date;

public abstract class DateChooserAdapter implements DateChooserListener {

    @Override
    public void dateChanged(Date date, DateChooserAction action) {
    }

    @Override
    public void dateBetweenChanged(DateRangeBetween date, DateChooserAction action) {
    }
}
