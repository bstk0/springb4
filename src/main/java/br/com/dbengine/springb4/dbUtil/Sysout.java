package br.com.dbengine.springb4.dbUtil;

public class Sysout {

    private static final Boolean SHOW_SYSOUT = true ;
    public static void s(String texto) {
        if (SHOW_SYSOUT) {
            System.out.println(texto);
        }
    }
}
