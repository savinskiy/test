/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.reflections;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DimaZ
 */
public class MyReflections implements Reflections {

    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
        Class c = object.getClass();
        Field field = c.getDeclaredField(fieldName);
        Object result = null;
        try {
            field.setAccessible(true);
            result = field.get(object);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Can't get access to field" + fieldName, ex);
        }
        return result;
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) {
        Set<String> result = new HashSet<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (Modifier.isProtected(m.getModifiers())) {
                result.add(m.getName());
            }
        }
        return result;
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        Set<Method> result = new HashSet<>();
        Class superClass = clazz;
        do {
            Method[] methods = superClass.getDeclaredMethods();
            result.addAll(Arrays.asList(methods));
        } while ((superClass = superClass.getSuperclass()) != null);
        return result;
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) {
        List<Class> result = new ArrayList<>();
        Class superClass = clazz;
        do {
            result.add(superClass);
        } while ((superClass = superClass.getSuperclass()) != null);
        return result;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        List<Class> interfaces = Arrays.asList(clazz.getInterfaces());
        return new HashSet<>(interfaces);
    }

    @Override
    public List<Class> getThrownExceptions(Method method) {
        List<Class> result = new ArrayList<Class>();
        Collections.addAll(result, method.getExceptionTypes());
        return result;
    }

    public String getFooFunctionResultForDefaultConstructedClass() {
        Class clazz = SecretClass.class;
        Method foo = null;
        String result = null;
        final String methodName = "foo";
        SecretClass secret = null;
        try {
            foo = clazz.getDeclaredMethod(methodName, (Class[]) null);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Отсутствует метод" + methodName, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            secret = (SecretClass) clazz.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Can't create instance of SecretClass", ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Can't get access to SercretClass", ex);
        }
        foo.setAccessible(true);
        try {
            result = (String) foo.invoke(secret, (Object[]) null);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Can't get access to method" + methodName, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Wrong arguments of method" + methodName, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        Class clazz = SecretClass.class;
        Method foo = null;
        String result = null;
        final String methodName = "foo";
        SecretClass secret = null;
        try {
            clazz.getDeclaredMethod(methodName, new Class[]{String.class});
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Отсутствует метод" + methodName, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, null, ex);
        }
        Constructor<SecretClass> constructor = null;
        try {
            constructor = clazz.getConstructor(String.class);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Отсутствует конструктор с параметром String", ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            secret = constructor.newInstance(constructorParameter);
        } catch (InstantiationException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Не получилось создать конструктор с параметром String", ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Can't get access to constructor", ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Передан неверный аргумент конструктору", ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, null, ex);
        }
        foo.setAccessible(true);
        try {
            result = (String) foo.invoke(secret, new Object[]{string, integers});
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Can't get access to method" + methodName, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, "Передан неверный аргумент методу" + methodName, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MyReflections.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
