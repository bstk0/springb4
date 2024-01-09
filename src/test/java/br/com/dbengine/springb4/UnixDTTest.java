package br.com.dbengine.springb4;

import br.com.dbengine.springb4.dbUtil.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UnixDTTest {

    public static void main(String[] args) {
        //Unix Timestamp in milliseconds
        /*
        long unixSeconds = 1690915266437; //1372339860;
        // convert seconds to milliseconds
        Date date = new java.util.Date(unixSeconds*1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
// give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(date);
        Sysout.s(formattedDate);
        */

        String x = "1690920568566";

        getBRDateTime(x);
        getBRDateTime("1690915266437");
    }

    private static void getBRDateTime(String x) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");

        long milliSeconds= Long.parseLong(x);
        System.out.println(milliSeconds);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        System.out.println(formatter.format(calendar.getTime()));
    }


}
