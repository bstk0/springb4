package br.com.dbengine.springb4.test;

import br.com.dbengine.springb4.dbUtil.*;

import java.text.*;
import java.util.*;

public class TestCANONICDate {

    public static void main(String[] args) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat outputFormatBR = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inputFormat.parse("2024-01-09T19:55:13.795Z");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String output = outputFormat.format(date);
        System.out.println(output);  // Outputs: "01-01-2022"

        String outputBR = outputFormatBR.format(date);
        System.out.println(outputBR);  // Outputs: "01-01-2022"
    }

}
