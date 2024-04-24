package br.com.dbengine.springb4.dbUtil;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.*;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;

public class UtilsJSON {

    public static String validaAtributo(Object o) {
        if (o == null) {
            return "";
        }
        if (o.getClass().getSimpleName().equals("Long")) {
            return Long.toString((Long) o);
        } else {
            //String
            return (String) o.toString();
        }
    }

    public static Integer parseAttrToInteger(Object o) {
        if (o == null) {
            return 0;
        }
        //Sysout.s(">>> parseAttrToInteger : " + o.getClass().getSimpleName());
        if (o.getClass().getSimpleName().equals("Long")) {
            return Math.toIntExact((long) o);
        }
        if (o.getClass().getSimpleName().equals("int")) {
            return (Integer) o;
        } else {
            return 0;
        }
    }

    public static String parseAttrToDateTimeBR(Object o) {
        if (o == null) {
            return "";
        }
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
        long milliSeconds = Long.parseLong(unixmilliseconds);
        //Sysout.s(milliSeconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        //Sysout.s(formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }

    public static String parseAttrToDateBR(Object o) {
        if (o == null) {
            return "";
        }
        if (o.getClass().getSimpleName().equals("Long")) {
            Long aux = (Long) o;
            return getBRDate(Long.toString(aux));
        } else {
            // TODO: voltar current date?
            return "";
        }
    }

    private static String getBRDate(String unixmilliseconds) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        long milliSeconds = Long.parseLong(unixmilliseconds);
        //Sysout.s(" >> milliSeconds : " + milliSeconds);
        Date date = new Date(milliSeconds * 1000);
        String java_date = formatter.format(date);
        return java_date;
    }

    public static String cvtUTCDateToBr(Object o) {
        String inputData = (String) o;
        return cvtUTCDateToBr(inputData);
    }

    public static String cvtUTCDateToBr(String utcDate) {
        //String inputData = (String) o;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatBR = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String outputBR = "";
        if (utcDate == null) return "";
        if (!utcDate.equals("")) {
            try {
                //Sysout.s("cvtUTCDateToBr - ANTES:" + utcDate );
                date = inputFormat.parse(utcDate);
                outputBR = outputFormatBR.format(date);
                //Sysout.s("cvtUTCDateToBr - DEPOIS:" + outputBR );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return outputBR;
    }


    public static String cvtBRDateToUTC(String brDate) {
        //VOLTANDO A DATA ....
        SimpleDateFormat inputFormatBR = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormatUTC = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String output = "";
        if (brDate == null) return "";
        if (!brDate.equals("")) {
            try {
                //Sysout.s("cvtBRDateToUTC - ANTES:" + brDate );
                date = inputFormatBR.parse(brDate);
                output = outputFormatUTC.format(date);
                //Sysout.s("cvtBRDateToUTC - DEPOIS:" + output );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return output;
    }


    public static <T> List<T> getListFromJSON(JSONArray results, Class<T> listItemType) {
        List<T> retorno = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        results.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            T objItem = null;
            try {
                objItem = listItemType.getConstructor().newInstance();
                objItem = objectMapper.readValue(obj.toString(), listItemType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            retorno.add(objItem);
        });
        return retorno;
    }
}
