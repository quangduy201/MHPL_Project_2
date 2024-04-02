package com.example.project_2.components.date_range_chooser;

import java.util.Date;

public interface DateChooserListener {
    public void dateChanged(Date date, DateChooserAction action);

    public void dateBetweenChanged(DateRangeBetween date, DateChooserAction action);
}
