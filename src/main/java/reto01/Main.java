package reto01;

import reto01.model.ClaseMala;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido a Software-Alchemist: Reto 01");
        ejecutarCosasDeLaClaseMala();
    }

    private static void ejecutarCosasDeLaClaseMala() {
        ClaseMala claseMala = new ClaseMala();

        Class<?> claseMalaClass = claseMala.getClass();

        Method[] claseMalaMetodos = claseMalaClass.getDeclaredMethods();

        try (ExecutorService threadPool = Executors.newCachedThreadPool()) {
            for (Method metodo : claseMalaMetodos) {
                threadPool.submit(()->{
                    try {
                        metodo.invoke(claseMala);
                    } catch (IllegalAccessException | InvocationTargetException exception) {
                        throw new RuntimeException(exception);
                    }
                });
            }
        }

    }
}
