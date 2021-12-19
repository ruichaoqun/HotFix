package com.ruichaoqun.hotfix;

import java.lang.reflect.Field;

public class ReflectUtils {
    public static Class getClass(String className){
        try {
            return Class.forName("dalvik.system.BaseDexClassLoader");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getField(Object object,String fieldName){
        try {
            Class clz = object.getClass();
            Field field = null;
            field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getField(Object object,String className,String fieldName){
        try {
            Class clz = Class.forName(className);
            Field field = null;
            field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setField(Object object,String fieldName,Object arg){
        try {
            Class clz = object.getClass();
            Field field = null;
            field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object,arg);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
