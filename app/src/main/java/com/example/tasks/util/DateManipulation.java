package com.example.tasks.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateManipulation {

    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public static String manipulateDate(String dueDate) {
        String temp = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dueDate);
            if (date != null) {
                temp = mDateFormat.format(date);
            }
        } catch (ParseException e) {
            temp = "--";
        }
        return temp;
    }
}
