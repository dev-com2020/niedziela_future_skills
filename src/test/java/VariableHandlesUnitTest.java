import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import static org.junit.Assert.assertEquals;

public class VariableHandlesUnitTest {

    public int publicTestVariable = 1;
    private int privateTestVariable = 1;
    public int variableToSet = 1;
    public int variableToCompareAndSet = 1;
    public int variableToGetAndAdd = 0;
    public byte variableToBitwiseOr = 0;

    @Test
    public void whenVariableHandleForPublicVariableIsCreated_ThenItIsInitializedProperly() throws NoSuchFieldException, IllegalAccessException{
        VarHandle PUBLIC_TEST_VARIABLE = MethodHandles
                .lookup()
                .in(VariableHandlesUnitTest.class)
                .findVarHandle(VariableHandlesUnitTest.class, "publicTestVariable", int.class);

        assertEquals(1,PUBLIC_TEST_VARIABLE.coordinateTypes().size());
        assertEquals(VariableHandlesUnitTest.class, PUBLIC_TEST_VARIABLE.coordinateTypes().get(0));
    }
    @Test
    public void whenVariableHandleForPrivateVariableIsCreated_ThenItIsInitializedProperly() throws NoSuchFieldException, IllegalAccessException{
        VarHandle PRIVATE_TEST_VARIABLE = MethodHandles
                .privateLookupIn(VariableHandlesUnitTest.class,MethodHandles.lookup())
                .findVarHandle(VariableHandlesUnitTest.class, "privateTestVariable", int.class);

        assertEquals(1,PRIVATE_TEST_VARIABLE.coordinateTypes().size());
        assertEquals(VariableHandlesUnitTest.class, PRIVATE_TEST_VARIABLE.coordinateTypes().get(0));
    }
    @Test
    public void whenVariableHandleForArrayVariableIsCreated_ThenItIsInitializedProperly() throws NoSuchFieldException, IllegalAccessException{
        VarHandle arrayVarHandle = MethodHandles
                .arrayElementVarHandle(int[].class);

        assertEquals(2,arrayVarHandle.coordinateTypes().size());
        assertEquals(int[].class, arrayVarHandle.coordinateTypes().get(0));
    }

}