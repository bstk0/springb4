package br.com.dbengine.springb4.test;

import java.util.*;

import static java.util.Map.*;

public class TestMAP {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        //Map.Entry<String, String> entry : map.get

        System.out.println("using entrySet and toString");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
        System.out.println();

        System.out.println("using entrySet and manual string creation");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println();

        System.out.println("using keySet");
        for (String key : map.keySet()) {
            System.out.println(key + "=" + map.get(key));
        }
        System.out.println();

//        Map.Entry<String, String> singletonMap;
//        singletonMap = (Map.Entry<String, String>) of("key1", "value");
//        System.out.println( singletonMap.getKey() + "/" + singletonMap.getValue());

        String[] numArray = {"one","two", "three", "four", "five"};
        System.out.println( ">> " + numArray[1]);
        //numArray.add("six");
        //System.out.println( ">> " + numArray[7]);

        //List<String> where = new ArrayList<String>();
        //where.add( ContactsContract.Contacts.HAS_PHONE_NUMBER+"=1" );
        //where.add( ContactsContract.Contacts.IN_VISIBLE_GROUP+"=1" );

        List<String> gfg = new ArrayList<String>(
                Arrays.asList("one","two", "three", "four", "five"));

        // print ArrayList
        System.out.println("ArrayList : " + gfg);
        System.out.println( ">> " + gfg.get(1));
        gfg.add("six");
        System.out.println( ">> " + gfg.get(5));
    }
}
