package com.example.project_2.components.date_range_chooser;

import java.util.Date;

public interface DateChooserRender {
    public String renderLabelCurrentDate(DateRangeChooser dateChooser, Date date);

    public String renderTextFieldDate(DateRangeChooser dateChooser, Date date);

    public String renderTextFieldDateBetween(DateRangeChooser dateChooser, DateRangeBetween dateBetween);

    public String renderDateCell(DateRangeChooser dateChooser, Date date);
}
