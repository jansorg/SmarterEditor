package com.ansorgit.smarter;

import com.intellij.psi.PsiElement;

import java.lang.reflect.Method;

/**
 * User: jansorg
 * Date: 13.06.11
 * Time: 01:46
 */
public class Reflective {
    private static final Class<?>[] NO_PARAM_TYPES = new Class<?>[0];
    private static final Object[] NO_PARAMS = new Object[0];

    private Object object;

    public static Reflective create(Object object) {
        return new Reflective(object);
    }

    public static Reflective create(Object object, Instance type) {
        if (!(object instanceof PsiElement) || !type.isInstance((PsiElement) object)) {
            throw new IllegalStateException("not a psi element or not of type " + type);
        }

        return new Reflective(object);
    }

    private Reflective(Object object) {
        this.object = object;
    }

    public <T> T invoke(String methodName) {
        Object result = null;

        try {
            Method method = object.getClass().getDeclaredMethod(methodName, NO_PARAM_TYPES);
            result = method.invoke(object, NO_PARAMS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (T) result;
    }
}
