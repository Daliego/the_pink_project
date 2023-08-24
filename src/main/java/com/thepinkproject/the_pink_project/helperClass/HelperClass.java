package com.thepinkproject.the_pink_project.helperClass;

import java.time.LocalDate;

public class HelperClass {
   public boolean isGonnaPassDayPay(LocalDate maximunDate, Integer mounths) {
        LocalDate futureDate = LocalDate.now().plusMonths(mounths);

       boolean result;
       result = futureDate.compareTo(maximunDate) < 0;
       return result;
   }
}
