package br.com.dbengine.springb4.dbUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class Sysout {

    //private static final Boolean SHOW_SYSOUT = true ;
    private static final String UBUNTU = "rodrigo-U46E";
    public static void s(String texto) {
        //InetAddress.getByName(new URL(urlString).getHost());

        String localHostName;
        try {
            localHostName = InetAddress.getLocalHost().getHostName();
//            System.out.println("LOCAL CONFIG::" +    // Local address
//                    InetAddress.getLocalHost().getHostAddress() + " / " +
//                    InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

//        try {
//            System.out.println("REMOTE CONFIG::" +
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
}
