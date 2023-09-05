package com.thepinkproject.the_pink_project.helperClass;

import java.time.DateTimeException;
import java.time.LocalDate;

public class HelperClass {
    public static boolean isGonnaPassDayPay(LocalDate expirationDate, Integer numberOfMonths) {
        LocalDate maximunDate = LocalDate.now().plusMonths(numberOfMonths);

        if (!expirationDate.isBefore(maximunDate)) {
            return false;
        }

        return true;
   }
}
