package com.jabbour.ems.backend.service.renderer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

public class ClassConfig {
    Class clazz;
    String fieldName;
    String label;
    boolean hidden;
    boolean editable;
    long position;


    public <T> ClassConfig(Class<T> clazz,String fieldName, String label, boolean hidden, boolean editable, long position) {
        this.fieldName = fieldName;
        this.label = label;
        this.hidden = hidden;
        this.editable = editable;
        this.position = position;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getLabel() {
        return label;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isEditable() {
        return editable;
    }

    public long getPosition() {
        return position;
    }

    public Field getField() throws NoSuchFieldException {
        return this.clazz.getField(fieldName);
    }

    public Object getSetter(Object o, Object o1) {
        Method[] methods = this.clazz.getMethods();
        for (Method method : methods) {
            if(method.getName().toLowerCase(Locale.ROOT).equals("set"+fieldName))
                return method;
        }
        return null;
//        throw new NoSuchMethodException();
    }

    public Object getGetter(Object o) {
        Method[] methods = this.clazz.getMethods();
        for (Method method : methods) {
            if(method.getName().toLowerCase(Locale.ROOT).equals("get"+fieldName))
                return method;
        }
        return null;
//        throw new NoSuchMethodException();
    }

}
