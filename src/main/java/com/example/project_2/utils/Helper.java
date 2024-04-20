/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.utils;

import com.example.project_2.components.date_range_chooser.DateRangeBetween;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Hung
 */
public class Helper {
    public static DateRange convertDateBetweenToLocalDateTime(DateRangeBetween dateRangeBetween) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String dateFrom = df.format(dateRangeBetween.getFromDate());
        String dateTo = df.format(dateRangeBetween.getToDate());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        LocalDateTime startTime = startDate.atStartOfDay();
        LocalDateTime endTime = endDate.atTime(LocalTime.MAX);

        return new DateRange(startTime, endTime);
    }
}
