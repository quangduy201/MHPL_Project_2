package com.example.project_2.components.date_range_chooser;

public class DateChooserAction {

    public DateRangeChooser getSource() {
        return source;
    }

    protected void setSource(DateRangeChooser source) {
        this.source = source;
    }

    public boolean isDayChanged() {
        return dayChanged;
    }

    protected void setDayChanged(boolean dayChanged) {
        this.dayChanged = dayChanged;
    }

    public boolean isMonthChanged() {
        return monthChanged;
    }

    protected void setMonthChanged(boolean monthChanged) {
        this.monthChanged = monthChanged;
    }

    public boolean isYearChanged() {
        return yearChanged;
    }

    protected void setYearChanged(boolean yearChanged) {
        this.yearChanged = yearChanged;
    }

    public int getChangeType() {
        return changeType;
    }

    public DateChooserAction(int changeType) {
        this.changeType = changeType;
    }

    public static final int USER_SELECT = 1;
    public static final int METHOD_SET = 2;
    private final int changeType;
    private DateRangeChooser source;
    boolean dayChanged;
    boolean monthChanged;
    boolean yearChanged;
}
