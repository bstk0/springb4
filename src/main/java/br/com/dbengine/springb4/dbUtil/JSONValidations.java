package br.com.dbengine.springb4.dbUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JSONValidations {

    public static String validaAtributo(Object o) {
        if (o == null) { return ""; }
        if (o.getClass().getSimpleName().equals("Long")) {
            return Long.toString((Long) o);
        } else {
            //String
            return (String) o.toString();
        }
    }

    public static Integer parseAttrToInteger(Object o) {
        if (o == null) { return 0; }
        if (o.getClass().getSimpleName().equals("Long")) {
            return Math.toIntExact((long) o);
        } else {
            return 0;
        }
    }

    public static String parseAttrToDateBR(Object o) {
        if (o == null) { return ""; }
        if (o.getClass().getSimpleName().equals("Long")) {
            Long aux = (Long) o;
            return getBRDateTime(Long.toString(aux));
        } else {
            // TODO: voltar current date?
            return "";
        }
    }

    private static String getBRDateTime(String unixmilliseconds) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
        long milliSeconds= Long.parseLong(unixmilliseconds);
        //System.out.println(milliSeconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        //System.out.println(formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }

}