package br.com.dbengine.springb4.test;

import java.util.*;

public class TestGeneric {
    private static class Foo {
        private String teste;
        public Foo() {
        }

        public String getTeste() {
            return teste;
        }

        public void setTeste(String teste) {
            this.teste = teste;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        List<Foo> foos = returnList(Foo.class);
        System.out.println(foos.get(0));
    }

    public static <T> List<T> returnList(Class<T> listItemType) {
        List<T> list = new ArrayList<>();
        try {
            T obj = listItemType.getConstructor().newInstance();
            list.add(obj);
        } catch (Exception ex) {
            //Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
}
