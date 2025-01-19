package org.example;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class GetAndAddExample {
    private int value = 44;
    private static int staticValue = 100;

    public static void main(String[] args) throws Throwable {
        GetAndAddExample example = new GetAndAddExample();
//        tworzę varhandle dla value
        VarHandle handle = MethodHandles
                .lookup()
                .findVarHandle(GetAndAddExample.class, "value", int.class);
        VarHandle handle2 = MethodHandles
                .lookup()
                .findStaticVarHandle(GetAndAddExample.class, "staticValue", int.class);

//        dodaje 10 do wartości i zwracam poprzednią

        int oldValue = (int) handle.getAndAdd(example, 10);
        System.out.println("poprzednia wartość: " + oldValue);
        System.out.println("Nowa wartość: " + handle.get(example));

        int oldValue2 = (int) handle2.getAndAdd(20);
        System.out.println("poprzednia wartość: " + oldValue2);
        System.out.println("Nowa wartość: " + handle2.get());
    }
}
