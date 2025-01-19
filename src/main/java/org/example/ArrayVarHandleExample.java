package org.example;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class ArrayVarHandleExample {
    public static void main(String[] args) {
        int[] array = {10,20,30,40};

//        VarHandle dla elementów int[]
        VarHandle handle = MethodHandles.arrayElementVarHandle(int[].class);

        System.out.println("Element w index 2: " + handle.get(array,2));

//        zmiana elementów w tablicy
        handle.set(array,2,100);
        System.out.println("Nowy element w index 2: " + handle.get(array,2));

//        Operacja CAS
        boolean casSuccess = handle.compareAndSet(array,2,123,1000);
        System.out.println("CAS ok: " + casSuccess);
        System.out.println("Po operacji CAS " + handle.get(array,2));
    }
}
