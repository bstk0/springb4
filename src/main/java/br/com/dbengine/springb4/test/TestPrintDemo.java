package br.com.dbengine.springb4.test;

// Java program to demonstrate scope
// of static method in Interface.

interface PrintDemo {

    // Static Method
    static void hello()
    {
        System.out.println("Called from Interface PrintDemo");
    }
}

public class TestPrintDemo implements PrintDemo {

    public static void main(String[] args)
    {

        // Call Interface method as Interface
        // name is preceding with method
        PrintDemo.hello();

        // Call Class static method
        //hello();
    }

    // Class Static method is defined
    // COMENTEI E NAO DEU ERRO DE FALTA DE IMPLEMENTACAO ...
//    static void hello()
//    {
//        System.out.println("Called from Class");
//    }
}
