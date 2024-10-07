package br.com.dbengine.springb4.dbUtil;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Sysout {

    //private static final Boolean SHOW_SYSOUT = true ;
    public static String CANONIC_KEY;
    public static String HARPER_KEY;
    public static String HASURA_KEY;
    public static String RESTDB_KEY;
    //public static String ADM_USER;
    //public static String ADM_PASS;

    private static final String UBUNTU = "rodrigo-U46E";
    public static void s(String texto) {
        //InetAddress.getByName(new URL(urlString).getHost());

        String localHostName;
        try {
            localHostName = InetAddress.getLocalHost().getHostName();
//            Sysout.s("LOCAL CONFIG::" +    // Local address
//                    InetAddress.getLocalHost().getHostAddress() + " / " +
//                    InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

//        try {
//            Sysout.s("REMOTE CONFIG::" +
//                    InetAddress.getLoopbackAddress().getHostAddress() + " / " +
//                    InetAddress.getLoopbackAddress().getHostName());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        //if (SHOW_SYSOUT) {
        //if (InetAddress.getLoopbackAddress().getHostName() == "localhost") {
        if (UBUNTU.equals(localHostName)) {
            System.out.println(texto);
        }
    }

    @NotNull
    public static Double getaDouble(String strVl) {
        Double dbVl = (double) 0;
        if (! "".equals(strVl) && strVl != null) {
            dbVl = Double.valueOf(strVl);
        }
        return dbVl;
    }

    public static long dateStringtoUnix(String strDate) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(strDate, formatter);

        ZonedDateTime dateTime = date.atStartOfDay(ZoneId.systemDefault());

        long unixTimestamp = dateTime.toEpochSecond();
        s(" >> Parsed date-time " + dateTime + " Unix timestamp " + unixTimestamp);
        return unixTimestamp;
    }

}
