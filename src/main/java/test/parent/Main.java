package test.parent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Test parent running");
        Class<?> clazz = Class.forName("test.child.Task");
        Method multiplyMethod = clazz.getMethod("multiply", int.class, int.class);
        Integer result = (Integer) multiplyMethod.invoke(null, 3,3);
        System.out.println("Multiply result: " + result);
    }
}
