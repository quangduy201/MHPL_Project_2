package com.example.project_2.components.date_range_chooser;

class ErrorCheck {
    public static boolean checkDate(RDate date) throws DateRangeChooserException {
        if (date.getMonth() > 12) {
            throw new DateRangeChooserException("Invalid month " + date.getMonth() + ">12");
        } else if (date.getMonth() < 1) {
            throw new DateRangeChooserException("Invalid month " + date.getMonth() + "<1");
        } else if (date.getYear() < 1) {
            throw new DateRangeChooserException("Invalid year " + date.getYear() + "<1");
        }
        return true;
    }
}
