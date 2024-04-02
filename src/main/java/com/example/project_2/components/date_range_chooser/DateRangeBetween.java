package com.example.project_2.components.date_range_chooser;

import java.util.Date;

public class DateRangeBetween {
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public DateRangeBetween(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public DateRangeBetween() {
    }

    private Date fromDate;
    private Date toDate;

    public void fixDate() {
        if (fromDate.compareTo(toDate) == 1) {
            Date tempDate = fromDate;
            this.fromDate = toDate;
            this.toDate = tempDate;
        }
    }
}
