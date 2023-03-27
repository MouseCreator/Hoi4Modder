package com.example.hoi4modder.model.files.properties.transform;

import com.example.hoi4modder.model.files.properties.lists.BlockCollection;
import com.example.hoi4modder.model.files.properties.lists.SavedList;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldInjection {

    private static final FieldInjection injector = new FieldInjection();

    public static FieldInjection getInjector() {
        return injector;
    }

    public Object constructor(Class<?> clazz, SavedList source) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        Object obj = createInstance( clazz);
        for (Field field : fields) {
            if (field.isAnnotationPresent(Declarable.class)) {
                String search = field.getAnnotation(Declarable.class).name();
                field.setAccessible(true);
                if (field.getType().equals(BlockCollection.class)) {
                    //field.set(obj, constructor(field.getGenericType().getClass(), source.findBlock(search)));
                } else {
                    //source.findValue(search);
                }
            }
        }
        return obj;
    }
    public void constructor(Object obj)  {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Declarable.class)) {
                String search = field.getAnnotation(Declarable.class).name();
                field.setAccessible(true);
                if (field.getType().equals(BlockCollection.class)) {
                    //field.set(obj, constructor(field.getGenericType().getClass(), source.findBlock(search)));
                } else {
                    //source.findValue(search);
                }
            }
        }
    }
    private Object createInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (InvocationTargetException |
                 IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
